<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/stilosjs/css/bootstrap.min.css">
<script src="resources/js/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/Chart.js"></script>
	<spring:url value="Avance.do" var="urlAvance" />

</head>

<jsp:include page="../fragments/headerRemesa.jsp" />
<style>
body {
	background-color: rgb(255, 247, 255);
}

h1 {
	color: black;
}

td {
	color: black;
}

p {
	color: black;
}
tr.header {
	background-color: rgb(0, 0, 0);
}
table, th, td {
  border: 1px solid black;
    border-collapse: collapse;
  
}
td.error {
 width: 100px;
   height:100px;
       word-wrap: break-word;
   
}
th, td {
  padding: 15px;
}
table {
  border-spacing: 5px;
}


textarea{  
  overflow:hidden;
  display:block;
  height:auto;
}

</style>
<body>
	<spring:url value="estatus" var="urlEstatus" />
	<div class="container" align="center">
		<fieldset>
			<legend>
				<h3>Instituto Nacional Electoral</h3>
			</legend>
			<div align="right" style="text-transform: capitalize;">
				<tr>
					</h2>
					<td><h4>Usuario: ${firstname}</h4>
				</tr>
				<div class="message">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<h4>Fecha Actual:</h4>
					<h4>
						<fmt:formatDate type="both" value="${now}" />
					</h4>
				</div>
					 <c:if test="${not empty mensaje3}">
			  		<div class="alert alert-info">${mensaje3}
    					<a href="${urlAvance}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>SUCCESS!</strong>proceso funcionando sin error , verifique denuevo la consulta de avance de geoprocesamiento de su grafica.
   					</div>
			  
			 	</c:if>
			</div>
			<div class="row align-items-center">
			<div class="col align-self-center">
				<h2>Geoprocesamiento de la Remesa de Actualización Cartográfica</h2>
				<form id="registro" action="#" class="form-inline">
					<input type="submit" class="btn btn-primary openBtn" value="Consulta">
				</form>
				<div class="col align-self-center">
					<h1>Estatus:</h1>

					<canvas id="myCanvas" width="500" height="300"></canvas>
					<div  id="leyenda"></div>
					<div  id="msjError"></div>
					<div  id="msjErrorrafa"></div>
					<div  id="msjErrorrafa2" >
					</div>
				</div>
			
			</div>
			</div>
			<div class="table-wrapper-scroll-y my-custom-scrollbar" >

					<table id="Table"
						class="table table-bordered table-condensed table-hover responsive nowrap" style="width:100%">
						<thead> 
						</thead>
						<tbody style="height: 10px !important; overflow: scroll;">
						
						</tbody>
					</table>
				</div>	
				<!-- Modal -->
				<div class="modal fade bd-example-modal-lg" tabindex="-1" id="myModal" 
				role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				    <div class="modal-dialog modal-lg" role="document">
				  
				        <div class="modal-content">
				            <div class="modal-header">
				                <h4 class="modal-title">Descripcion</h4>
				            </div>
				            <div class="modal-body" >
							<div id="pocos"></div>
							<div id="text" ></div>
				            </div>
				            <div class="modal-footer">
				               <button type="button" class="btn btn-secondary" data-dismiss="modal">Detalle</button>
				            </div>
				        </div>
				    </div>
				</div>
		</fieldset>
	</div>
	<script type="text/javascript">
var _csrf_token = /*[[${_csrf.token}]]*/ '${_csrf.token}';
var _csrf_param_name = /*[[${_csrf.parameterName}]]*/ '${_csrf.parameterName}';


jQuery(document).ready(function($) {

	$("#registro").submit(function(event) {
		event.preventDefault();
		
		searchViaAjax();
	});

});

function searchViaAjax() {
	var requestData= {};
	
	requestData[_csrf_param_name] = _csrf_token;

	$.ajax({
		type : "POST",
		url : "estatus",
		data: requestData ,
		dataType: "text",
		timeout : 200000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			var datos=0;
			datos = JSON.parse(data);
		
				 var alejandro = datos.alejandro;
				 var errordata = datos.errordata;
				if(alejandro.length>=0){
					console.log("mayor a cero: ", alejandro.length);
					document.getElementById("msjError").style.display = "none"; 
					dibujaCirculo(alejandro);
					console.log("menor a cero: ", alejandro.length);
				}else{
					$('#msjError').html("<h1> verifica el status,  da click en el evento Consulta</h1> ");
					dibujaCirculo(alejandro);

				}if (errordata.length>=0) {
					console.log("mayor a cero-rafa: ", errordata.length);
					document.getElementById("msjErrorrafa").style.display = "none";
					var newheight = 0;
					$('.autoheight').each(function(){ 
					   var h = $(this).height();
					   var s = $(this).prop('scrollHeight');
					   console.log(h + "-" + s);
					   if ( (h+s) > newheight )  {
					      newheight = h + s;      
					   }
					});
					$('.autoheight').height(newheight);
					$('#Table > tbody').empty();
					$.each(errordata, function (i, item) {
						var rows="";
// 					    var rows = "<thead>"+
// 					    "<tr class='header'>"+
// 						"<th style='width: 6%;'>Entidad</th>"+
// 						"<th style='width: 7%;' >Remesa</th>"+
// 						"<th style='width: 10%;'>Fecha</th>"+
// 						"<th>Descripción</th>"+
						
// 					"</tr></thead><tr>" +
// 					        "<td><textarea class='autoheight' name='entidad[]' >"+ item.iden + "</textarea></td>" +
// 					        "<td><textarea class='autoheight' name='remesa[]' >"+ item.idre + "</textarea></td>" +
// 					        "<td><textarea class='autoheight' name='fecha[]' >"+ item.idfe + "</textarea></td>" +
// 					        "<td><textarea class='autoheight' name='Descripción[]'>"+item.error+"</textarea></td>" +
// 					        "</tr>";	
									$('#msjErrorrafa2').html("<h1 style='color:red;'> Se ah encontrado un ERROR para mas detalle da click en CONSULTA</h1> ")
					        $('#myModal').blur(
					                function() {
					                    $('#text').load('statusError', 
					                                       "datoE="+$('#myModal').val())
					                }   
					            )
					        $('.openBtn').on('click',function(){
							    $('.modal-body').load('content.html',function(){
							    	 $('#pocos').html('<h1 style="color:red;">Error</h1>')
							    	 console.log('Tabla: '+rows);
							    	 $('#text').text(item.error);
							        $('#myModal').modal({show:true});
							    });
							    $('#myModal').on('show.bs.modal', function (event) {
									  var button = $(event.relatedTarget)
									  var recipient = button.data('whatever')
									  var modal = $(this)
									  modal.find('.modal-title').text(recipient)
									  

									});
							});
					    $('#Table > tbody').append(rows);
					});
					
				} else {
					console.log("menor a cero-rafa: ", errordata.length);

					$('#msjErrorrafa2').html("<h1>el estatus error devuelve nulo</h1> ");

				}
			
	
		},
		error : function(e) {
			console.log("ERROR: ", e);
			$('#msjError').html("<h1>Web service esta fuera de servicio contacte</h1> ");
		},
		done : function(e) {
			console.log("DONE");
		}
	});

}

function dibujaCirculo(data) {

	
	var myCanvas = document.getElementById("myCanvas");
	
	console.log("JSONparse valor: " + data); 

    var ctx = myCanvas.getContext("2d");
    
	var start_angle = 0;
	var categoria;

	var indexe=new Array();
	var colorFondo=new Array();
	
    $.each(data, function(index, el) {
        console.log("element at " + index + ": " + el); 
        indexe.push(index);
    });
    

    var porcentaje = 127;
    
	var sum =0;
	
	for(categoria=0;categoria<=indexe.length;categoria++){
	  	var slice_angle = 2 * Math.PI * categoria / porcentaje;
	  	var incre = categoria/porcentaje;
	  	sum = incre * 100;
	  	
	    drawPieSlice(ctx,500/2,
	    		300/2,
	    		Math.min(500/2,300/2), 
	    		start_angle,
	    		start_angle+slice_angle);
	    //,'rgba(70,130,180)'

	}
	var entero = sum.toFixed(); 
	$('#leyenda').html("<h1>Porcentaje: "+entero+" % </h1> ");

}

function drawPieSlice(ctx,centerX, centerY, radius, startAngle, endAngle){
	//color
	 
    ctx.fillStyle = "#000080";

    ctx.beginPath();
 
    ctx.moveTo(centerX,centerY);
 
    ctx.arc(centerX, centerY, radius, startAngle, endAngle);

    ctx.closePath();
 
    ctx.fill();
 
}

setInterval(searchViaAjax,60000);

</script>

</body>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<footer>
	<div align="center">
		<h4>© Derechos Reservados, Instituto Nacional Electoral, México.</h4>
	</div>
</footer>
</html>