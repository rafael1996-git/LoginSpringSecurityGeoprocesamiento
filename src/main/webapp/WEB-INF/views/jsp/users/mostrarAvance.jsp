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
   					 	<strong>SUCCESS!</strong>proceso funcionando sin error , verifique denuevo la consulta de avance ene el geoprocesamiento de su grafica.
   					</div>
			  
			 	</c:if>
			</div>
			<div class="row align-items-center">
			<div class="col align-self-center">
				<h2>Geoprocesamiento de la Remesa de Actualización Cartográfica</h2>
				<form id="registro" action="#" class="form-inline">
					<input type="submit" class="btn btn-primary" value="Consulta">
				</form>
				<div class="col align-self-center">
					<h1>Estatus:</h1>

					<canvas id="myCanvas" width="500" height="300"></canvas>
					<div  id="leyenda"></div>
					<div  id="msjError"></div>
					<div  id="msjErrorrafa"></div>
				</div>
				
			
				<div class="navbar" align="left">
			
					<div class="col-sm-offset-2 col-sm-10">
										<a class="btn-lg btn-primary pull-right"
										type="submit"
										value="1"
										href="statusError">StatusError </a>
					</div>
				</div>
				
				
				<div class="table-wrapper-scroll-y my-custom-scrollbar">

					<table id="myTable"
						class="table table-bordered table-hover responsive nowrap">
						<thead>
							<tr class="header">
								<th style="width: 15%;">entidad</th>
								<th style="width: 15%;">remesa</th>
								<th style="width: 20%;">fecha</th>
								<th style="width: 100%;">error</th>
							</tr>
						</thead>
						<tbody style="height: 10px !important; overflow: scroll;">
							<c:forEach var="dato" items="${lista}">
								<tr>
							     	<td>${dato.entidad}</td>
									<td>${dato.remesa}</td>
									<td>${dato.fecha}</td>
									<td>${dato.error}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
		timeout : 200000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			var datos=0;
			if(data === 'undefined'||data===null||data===""){
				console.log("data no esta definido o es nulo");
				$('#msjError').html("<h1>el estatus devuelve nulo</h1> ");
			}else{
				 datos = JSON.parse(data);
				if(datos.length==0){
					$('#msjError').html("<h1>Para verificar el status de avance dr click en el evento Consulta</h1> ");
				}else{
					console.log("mayor a cero: ", datos.length);
					document.getElementById("msjError").style.display = "none";
					var alejandro = datos.alejandro;
					var error = datos.error; 
					dibujaCirculo(alejandro);
					
					console.log("menor a cero: ", datos.length);
				}if (error===null||error===""||error.length==0) {
					$('#msjErrorrafa').html("<h1>el estatus error devuelve nulo</h1> ");
				} else {
					document.getElementById("msjError").style.display = "none";
					pintarError(error);
				}
			}
	
		},
		error : function(e) {
			console.log("ERROR: ", e);
			$('#msjError').html("<h1>Web service esta fuera de servicio contacte</h1> ");
// 			clearcanvas();
		},
		done : function(e) {
			console.log("DONE");
		}
	});

}

function pintarError(error){
	alert(error);
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