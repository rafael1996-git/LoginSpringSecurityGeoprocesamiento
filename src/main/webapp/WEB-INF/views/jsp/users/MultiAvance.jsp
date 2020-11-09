<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<spring:url value="MultiAvance.do" var="urlAvance" />
</head>

<jsp:include page="../fragments/headerMultiRemesa.jsp" />
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
    					<a href="${urlmultiAvance}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>SUCCESS!</strong>proceso funcionando sin error , verifique denuevo la consulta de avance de geoprocesamiento de su grafica.
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
					<div  id="msjErrorrafa2"></div>
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
					$('#msjErrorrafa').html("<h1>SE ha encontrado un Error checkout</h1> ");
					
					$('#Table > tbody').empty();
					$.each(errordata, function (i, item) {
					    var rows = "<thead>"+
					    "<tr class='header'>"+
						"<th style='width: 6%;'>Entidad</th>"+
						"<th style='width: 7%;' >Remesa</th>"+
						"<th style='width: 10%;'>Fecha</th>"+
						"<th>Descripción</th>"+
						
					"</tr></thead><tr>" +
					        "<td id='entidad' >" + item.iden + "</td>" +
					        "<td id='remesa' >" + item.idre + "</td>" +
					        "<td id='fecha'  >" + item.idfe + "</td>" +
					        "<td name='mitextarea' id='error' cols='40' rows='5' style='resize: both;'>" + item.error+ "</td>" +
					        "</tr>";						     
					    $('#Table > tbody').append(rows);
					});
					
					console.log(errordata);
					
					
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