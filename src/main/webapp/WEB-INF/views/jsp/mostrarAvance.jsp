
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="resources/js/Chart.js"></script>
<script src = " https://unpkg.com/sweetalert/dist/sweetalert.min.js "> </script> 
</head>
<jsp:include page="fragments/HeaderAdmin.jsp" />
<style>
body {
	background-color: rgb(255, 247, 255);
}

.my-custom-scrollbar {
position: relative;
height: 200px;
overflow: auto;
}
.table-wrapper-scroll-y {
display: block;
}

td.error {
	width: 100px;
	height: 100px;
	word-wrap: break-word;
}

.modalDialog {
	position: fixed;
	font-family: Arial, Helvetica, sans-serif;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: rgba(0,0,0,0.8);
	z-index: 99999;
	opacity:0;
	-webkit-transition: opacity 400ms ease-in;
	-moz-transition: opacity 400ms ease-in;
	transition: opacity 400ms ease-in;
	pointer-events: none;
}
.modalDialog:target {
	opacity:1;
	pointer-events: auto;
}
.modalDialog > div {
	width: 1600px;
	position: relative;
	margin: 3% auto;
	padding: 5px 20px 13px 20px;
	border-radius: 10px;
	background: #fff;
	background: -moz-linear-gradient(#fff, #999);
	background: -webkit-linear-gradient(#fff, #999);
	background: -o-linear-gradient(#fff, #999);
  -webkit-transition: opacity 400ms ease-in;
-moz-transition: opacity 400ms ease-in;
transition: opacity 400ms ease-in;
}
.close {
	background: #ffc62b;
	color: #FFFFFF;
	line-height: 25px;
	position: absolute;
	right: -12px;
	text-align: center;
	top: -10px;
	width: 24px;
	text-decoration: none;
	font-weight: bold;
	-webkit-border-radius: 12px;
	-moz-border-radius: 12px;
	border-radius: 12px;
	-moz-box-shadow: 1px 1px 3px #000;
	-webkit-box-shadow: 1px 1px 3px #000;
	box-shadow: 1px 1px 3px #000;
}
.close:hover { background: #ff0000; }
</style>
<spring:url value="/resources/css/admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet" />
<body >
	<spring:url value="estatus" var="urlEstatus" />
	<div class="container py-4">
	<c:if test="${not empty mensaje3}">
			<div class="alert alert-info">${mensaje3}
				<a href="${urlAvance}" class="close" data-dismiss="alert"
					aria-label="close">&times;</a> <strong>SUCCESS!</strong>proceso
				funcionando sin error , verifique denuevo la consulta de avance de
				geoprocesamiento de su grafica.
			</div>

		</c:if>
		<section id="contenido">

			<div id="informacion">
				<div
					class="card  col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 .img-fluid"
					style="min-width: 300px;">
					<img src="<c:url value="/resources/images/avatar.jpg"/>"
						class="card-img-top" alt="Card image" />

					<div class="card-block">
						<h6 class="card-title">Nombre del Usuario Generador de la
							Remesa: ${firstname}</h6>
						<p>
							<c:set var="now" value="<%=new java.util.Date()%>" />
							Fecha Actual:
							<fmt:formatDate type="both" value="${now}" />
						</p>
					</div>
				</div>
			</div>

		</section>
		<div id="info2" align="center">
			<aside>
				<article>
					<h2 align="center">Geoprocesamiento de la Remesa de
						Actualizacion Cartografica</h2>
					<p>Este Usuario tiene Permisos para Administrar y Procesar la
						generacion de Remesa.</p>
				</article>
			</aside>
		</div>
		
	<div id="status" align="center">
	
	<div class="card ">
				<div class="card-header">
					<h4 class="card-title" align="center">Estatus:</h4>
				</div>
				<div class="card-body">
					<canvas id="myCanvas" width="500" height="300"></canvas>
				<div id="leyenda"></div>
				<div id="msjError"></div>
				<div id="msjErrorrafa"></div>
				<div id="msjErrorrafa2"></div>
				</div>
				<div id="card" class="card-footer">
				<form id="registro" action="#" class="form-inline" style="float: left;">
				<input type="submit" class="btn btn-primary openBtn"
					value="Consulta">
				</form>
				<a id="detalle" href="#openModal" class="btn btn-primary" style="float: right ;" >Detalle Error</a>
				</div>

			</div>

	
	</div>
	
	<div class="table-wrapper-scroll-y my-custom-scrollbar">

		<table id="Table"
			class="table table-bordered table-condensed table-hover responsive nowrap"
			style="width: 100%">
			<thead>
			</thead>
			<tbody style="height: 10px !important; overflow: scroll;">

			</tbody>
		</table>
	</div>
		<div id="openModal" class="modalDialog">
			<div>
				<a href="#close" title="Close" class="close" >X</a>
				<h2>Error</h2>
				<div id="datosError" align="center"></div>
			</div>
		</div>

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
		try
	      {
		$.ajax({
			type : "POST",
			url : "estatus",
			data: requestData ,
			timeout : 200000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				var datos=0;
				datos = JSON.parse(data);
				var alejandro = datos.alejandro;
				if(alejandro.length === 'undefined'||alejandro.length===null||alejandro.length===""){
					console.log("el estado no esta definido o es nulo");
					$('#msjError').html("<h2>el estatus devuelve nulo</h2> ");
				}else{
					if(alejandro.length<=0){
						document.getElementById("msjError").style.display = "none";
						dibujaCirculo(alejandro);
						console.log("menor a cero: ", alejandro.length);
					}else{
						console.log("mayor a cero: ", alejandro.length);
						document.getElementById("msjError").style.display = "none";
						dibujaCirculo(alejandro);
						
					}
				}
		
			},
			error: function (jqXHR, textStatus, errorThrown) {

                if (jqXHR.status === 0) {
                	swal("Oops!", "...Verify Network!" ,  "error");
//                     alert('Not connect: Verify Network.=(');

                } else if (jqXHR.status == 404) {
                	swal(" Oops!", "...Requested page not found [404]!" ,  "error");
//                     alert('Requested page not found [404].');

                } else if (jqXHR.status == 500) {
                	swal(" Oops!", "...Error Interno del Servidor [500]!" ,  "error");
//                     alert('Error Interno del Servidor [500].');

                } else if (textStatus === 'parsererror') {
                	swal(" Oops!", "...Error Requested JSON parse failed!" ,  "error");
//                     alert('Requested JSON parse failed.');

                } else if (textStatus === 'timeout') {
                	swal(" Oops!", "...Error Time out error!" ,  "error");
//                     alert('Time out error.');

                } else if (textStatus === 'abort') {
                	swal("Oops" , "...Ajax request aborted!" ,  "error");
//                     alert('Ajax request aborted.');

                } else {
    
                	swal(" Oops",+ jqXHR.responseText+" ocurrio un error" ,  "error" );
//                     alert('Uncaught Error: ' + jqXHR.responseText);

                }

            },
			done : function(e) {
				console.log("DONE");
				
			}
		});
	      } 
	      catch (err) 
	      {
	        alert(err);
	      }

	}

function dibujaCirculo(data) {

	getError();
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
    

    var porcentaje = 135;
    
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
<script src="resources/js/FuncionRemesaError.js" type="text/javascript"></script>

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

<div class="container-xl mt-3 border  p-3 my-3 bg-dark text-white"
	align="center">
	<jsp:include page="fragments/footer.jsp" />
</div>

</html>