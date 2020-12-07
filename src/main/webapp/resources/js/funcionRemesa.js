
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
//var _csrf_param_name = '${_csrf.parameterName}';

var listaEntidadOk=new Array();
var listaEntidadError=new Array();
var flagEncontrado;
var ejecuta;

jQuery(document).ready(function($) {
	$("#formEntidad").submit(function(event) {
		event.preventDefault();
		funcionRemesa();
	});

});


function funcionRemesa() {
	var lista=new Array();
	
    $('input[type=checkbox]:checked').each(function(){
    	 if (this.checked) {
    	        lista.push($(this).val());
    	      }
    });
    if (lista.length === 0) {
        var modalDialog = getModalDialog();
                        getModalDialog().querySelector('.modal-content').innerHTML = "Seleccione alguna Entidad";
                        jQuery(modalDialog).modal('show');
        }else{
			$.ajax({
				type : "GET",
				async:true,
				url : "funcionRemesas",
				data:'id='+lista,
				dataType: "text",
				timeout : 200000,
				beforeSend: function(xhr) {
		         xhr.setRequestHeader(header,token);
				   $("#pleaseWaitDialog").modal("show");
		         },
				success : function(respuesta) {
					var listaEntidadFail=new Array();
					var registros;
					if(respuesta===null||respuesta===undefined||respuesta===''){
						var modalDialog = getModalDialog();
                        getModalDialog().querySelector('.modal-content').innerHTML = "Sin respuesta, valide nuevamente";
                        jQuery(modalDialog).modal('show');
					}else{
						try{
							
						registros = JSON.parse(respuesta);
					    var msgException=new Array();
					for (x of registros) {
  							
					       if(x.msgExceptionDao!=null){
						       document.getElementById(x.entidadID).checked = false;
						       
                               msgException.push("En la entidad "+x.entidadID+" ocurrio: "+x.msgExceptionDao+"<br>");
						       var modalDialog = getModalDialog();
                               getModalDialog().querySelector('.modal-content').innerHTML =msgException;
                               jQuery(modalDialog).modal('show');
					         }

					       if(x.estatusServicio==='200'){
							   listaEntidadOk.push(x.entidadID);
				               document.getElementById(x.entidadID).disabled = true;
                               
							}
							if(x.estatusServicio==='601'){
								listaEntidadFail.push(x.entidadID+" "+x.mensajeSalida);
								document.getElementById(x.entidadID).checked = false; 
								
							}
						
						}
					if(listaEntidadOk.length===0){
					}
					
					if(listaEntidadFail.length===0){
					}else{
						var modalDialog = getModalDialog();
                        getModalDialog().querySelector('.modal-content').innerHTML = "mensaje: La Entidad "+listaEntidadFail;
                        jQuery(modalDialog).modal('show');
					}
					flagEncontrado=true;
				    bandera(flagEncontrado);

						}catch(e){
							console.log("la cadena no se puede parsear: "+e);
						}
					}
				},
   			    complete:function(data){
    				$("#pleaseWaitDialog").modal("hide");
   				},
				error: function (jqXHR, ajaxOptions, thrownError) {
					var msg = '';
                                              if (jqXHR.status === 0) {
                                                  msg = 'Not connect.n Verify Network.';
                                              } else if (jqXHR.status == 404) {
                                                  msg = 'Requested page not found. [404]';
                                              } else if (jqXHR.status == 500) {
                                                  msg = 'Internal Server Error [500].';
                                              } else if (exception === 'parsererror') {
                                                  msg = 'Requested JSON parse failed.';
                                              } else if (exception === 'timeout') {
                                                  msg = 'Time out error.';
                                              } else if (exception === 'abort') {
                                                  msg = 'Ajax request aborted.';
                                              } else {
                                                  msg = 'Uncaught Error.n' + jqXHR.responseText;
                                              }
                                          
					console.log('Mensaje: ' +msg + ' : ' + thrownError +' fin: '+ajaxOptions);
					
				     var modalDialog = getModalDialog();
                     getModalDialog().querySelector('.modal-content').innerHTML = msg;
                     jQuery(modalDialog).modal('show');
		        },
				done : function(e) {
					console.log("DONE");
				}
			});
        }

}



function consultaEstatusMultiple() {

  var arrayDiferentes = [];
  
  for (var i = 0; i < listaEntidadOk.length; i++) {
    var igual=false;
     for (var j = 0; j < listaEntidadError.length & !igual; j++) {
         if(listaEntidadOk[i] == listaEntidadError[j])
                 igual=true;
     }
    if(!igual)arrayDiferentes.push(listaEntidadOk[i]);
    }

  
  if(arrayDiferentes.length===0){
      stopMultiStatus();
    }else{
      solicitudAjax(arrayDiferentes);
          }

 }   




function solicitudAjax(arrayDiferentes){
	$.ajax({
		type : "POST",
		url : "estatusMulti",
		data:'entidad='+arrayDiferentes,
		dataType: "text",
		timeout : 200000,
		beforeSend: function(xhr) {
		         xhr.setRequestHeader(header,token);
                // $("#pleaseWaitDialog").modal("show");
		         },
		success : function(data) {
			var datos;
			
			if(data===null||data===undefined||data===''){
						var modalDialog = getModalDialog();
                        getModalDialog().querySelector('.modal-content').innerHTML = "Sin respuesta, valide nuevamente";
                        jQuery(modalDialog).modal('show');
					}else{
						try{
						datos = JSON.parse(data);
						for (x of datos) {
				            if(x.msgException!=null){
						       document.getElementById(x.idEntidad).checked = false;
						       listaEntidadError.push(x.idEntidad);
                               var msj = "En la entidad "+x.idEntidad+" ocurrio: "+x.msgException;
						       var modalDialog = getModalDialog();
                               getModalDialog().querySelector('.modal-content').innerHTML =msj;
                               jQuery(modalDialog).modal('show');
					         }
						  dibujaCirculoDinamico(x);
						}
						}catch(e){
							console.log("la cadena no se puede parsear: "+e);
						}
					}
			
			
		},
		 complete:function(data){
   				},
		error : function(e) {
			console.log("ERROR: ", e);
			var msg ="Error en el servicio multigrafica"+e;
			var modalDialog = getModalDialog();
                     getModalDialog().querySelector('.modal-content').innerHTML = msg;
                     jQuery(modalDialog).modal('show');
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function dibujaCirculoDinamico(data) {
   
    var capa = document.getElementById("contieneCanvas");

    if(document.getElementById("canvasEntidad"+data.idEntidad)&&
       document.getElementById("divEntidad"+data.idEntidad)) {
	
         var care=document.getElementById("canvasEntidad"+data.idEntidad);
         var divRem=document.getElementById("divEntidad"+data.idEntidad);

          if(document.getElementById("botonEntidad"+data.idEntidad)){
             var botonRem=document.getElementById("botonEntidad"+data.idEntidad);
             divRem.removeChild(botonRem);
           }

         var ctx = care.getContext("2d");
         ctx.clearRect(0, 0,500,300);
         var capa = document.getElementById("contieneCanvas");
         divRem.removeChild(care);
	     capa.removeChild(divRem);
    }

    var div = document.createElement("div");
    div.setAttribute("id", "divEntidad"+data.idEntidad);
    div.style.background="grey";
    div.setAttribute("class","round3");

	var canva = document.createElement("canvas");
	canva.setAttribute("id", "canvasEntidad"+data.idEntidad);
   	canva.width = 500;
   	canva.height =300;
    

    var ctx = canva.getContext("2d");

	var start_angle = 0;
	var categoria;

	var indexe=new Array();
	var colorFondo=new Array();
	
    $.each(data.idOperaciones, function(index,value) {
        indexe.push(value);
        
    });
    

    var porcentaje = 127;
    
	var sum =0;
	var msjError=data.messaje;
	var labelText;
	var labelTextExito;
	var totalIdOperaciones = indexe.length;

	
	for(categoria=0;categoria<=indexe.length;categoria++){
	  	var slice_angle = 2 * Math.PI * categoria / porcentaje;
	  	var incre = categoria/porcentaje;
	  	sum = incre * 100;
	    
        
	    drawPieSlice(labelText,totalIdOperaciones,msjError,ctx,500/2,
	    		300/2,
	    		Math.min(500/2,300/2), 
	    		start_angle,
	    		start_angle+slice_angle);
	}
	
	var entero = sum.toFixed(); 
       ctx.beginPath();
         ctx.fillStyle = "white";
         ctx.font = "bold 20px Arial";

         ctx.fillText("En la Entidad "+data.idEntidad,10,30);
         ctx.closePath(); 
         ctx.fill();
         ctx.beginPath();
         ctx.fillStyle = "white";
         ctx.font = "bold 14px Arial";
         if(msjError!==null){
	       ctx.fillText(" procesados "+entero+"%", 250,280);
           }else{
         ctx.fillText(" procesando "+entero+"%", 250,280);
           }
         ctx.closePath(); 
         ctx.fill();
 
	if(msjError!==null){
		
	   var remueveEntidad = data.idEntidad;
       listaEntidadError.push(remueveEntidad);
    
      document.getElementById(remueveEntidad).checked = false;
      document.getElementById(remueveEntidad).disabled = false;
	  var boton = document.createElement("button");
      boton.setAttribute("id", "botonEntidad"+data.idEntidad);
      boton.setAttribute("class","btn btn-danger btn-xs");

      boton.innerHTML ='<span class="rounded-circle"><img class="rounded-sm" alt="10x10" width="20" height="20"src="resources/images/Button-Close-icon.png" /> Detalle del error';

      boton.onclick = function(){
	    var modalReady = getModalCanvas();

  		// Inicializa el modal durante la ejecucion
 		 if (!modalReady) {
      			 modalReady = initModal(); 
   				}
     
      var html =
         '<div class="modal-header">' +
          '<h5 class="modal-title" id="exampleModalLabel">Detalle del error</h5>' +
          '<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
          '<span aria-hidden="true">&times;</span>' +
          '</button>' +
          '</div>' +
          '<div class="modal-body" style="height: 150px;overflow-y: auto;">' +
            msjError +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
            '</div>';

        setModalContent(html);

        jQuery(modalReady).modal('show');
  
             }
		
	   div.appendChild(boton);
	}
	div.appendChild(canva);
	capa.appendChild(div);

}

function drawPieSlice(labelText,totalIdOperaciones,msjError,ctx,centerX,centerY,radius,startAngle,endAngle){

    ctx.fillStyle = "#1BB219"; //#000080
  
    ctx.beginPath();
   // ctx.moveTo(centerX,centerY);
    ctx.arc(centerX,centerY,radius,startAngle,endAngle,false);
    ctx.closePath();
    ctx.fill();
	ctx.lineWidth = 2;
    ctx.strokeStyle ="#1BB219";//#4388cc
    ctx.stroke();

     if (msjError != "" && msjError !== null) {
    
         var oprecaionesFaltantes = totalIdOperaciones-127;
         var restoPorcentaje = 2 * Math.PI * oprecaionesFaltantes /127;
         ctx.fillStyle = "#FE5200";//red
    	 ctx.beginPath();
    	 ctx.arc(centerX, centerY, radius, startAngle,0+restoPorcentaje,true);
         ctx.lineWidth = 3;
         ctx.strokeStyle = "#FE5200";//red
         ctx.fill();
         ctx.stroke();
         ctx.closePath(); 

         ctx.beginPath();
         ctx.fillStyle = "white";
         ctx.font = "bold 14px Arial";

         labelText = Math.round(100 * oprecaionesFaltantes / 127);
         ctx.fillText("Error "+labelText+"%",150,80);
         ctx.closePath(); 
         ctx.fill();
         
        flagEncontrado = false;
        bandera(flagEncontrado);
  
    }else{
	     var oprecaionesFaltantes = totalIdOperaciones-127;
         var restoPorcentaje = 2 * Math.PI * oprecaionesFaltantes /127;
         ctx.fillStyle = "#C6EA00";//red
    	 ctx.beginPath();
    	 ctx.arc(centerX, centerY, radius, startAngle,0+restoPorcentaje,true);
         ctx.lineWidth = 3;
         ctx.strokeStyle = "#C6EA00";//red
         ctx.fill();
         ctx.stroke();
         ctx.closePath(); 

         ctx.beginPath();
         ctx.fillStyle = "white";
         ctx.font = "bold 14px Arial";

         labelText = Math.round(100 * oprecaionesFaltantes / 127);
         ctx.fillText("Pendiente "+labelText+"%",150,80);
         ctx.closePath(); 
         ctx.fill();
        }
    


}


function bandera(flagEncontrado){
        ejecuta = setInterval(function(){
        consultaEstatusMultiple();
        },60000); 
}

function stopMultiStatus() {
  clearInterval(ejecuta);
}



function getModalCanvas() {
  return document.getElementById('mod');
}


function setModalContent(html) {
  getModalCanvas().querySelector('.modal-content').innerHTML = html;
}

function getModalDialog() {
  return document.getElementById('dialog');
}

function initModal() {

  var modal = document.createElement('div');
  modal.classList.add('modal', 'fade');
  modal.setAttribute('id', 'mod');
  modal.setAttribute('tabindex', '-1');
  modal.setAttribute('role', 'dialog');
  modal.setAttribute('aria-labelledby', 'Detalles');
  modal.setAttribute('aria-hidden', 'true');
  modal.innerHTML =
        '<div class="modal-dialog" role="document">' +
          '<div class="modal-content"></div>' +
        '</div>';
  document.body.appendChild(modal);
  return modal;
}














