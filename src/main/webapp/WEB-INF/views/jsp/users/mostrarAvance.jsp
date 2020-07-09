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
			</div>
			<div class="column">
				<h2>Geoprocesamiento de la Remesa de Actualización Cartográfica</h2>
				<form id="registro" action="#" class="form-inline">
					Entidad: <input type="text" name="entidad" id="entidad" required>
					Remesa: <input type="text" name="remesa" id="remesa" required> <br>
					
					<input type="submit" class="btn btn-primary" value="Consulta">
				</form>
				<div>
					<h1>Estatus:</h1>
					<div id="feedback"></div>
					<canvas id="myCanvas" width="500" height="300"></canvas>
				</div>
			</div>
		</fieldset>
	</div>
<script type="text/javascript" >
var _csrf_token = /*[[${_csrf.token}]]*/ '${_csrf.token}';
var _csrf_param_name = /*[[${_csrf.parameterName}]]*/ '${_csrf.parameterName}';


jQuery(document).ready(function($) {

	$("#registro").submit(function(event) {
		event.preventDefault();
		searchViaAjax();
	});

});

function searchViaAjax() {
	var requestData = {
		    'entidad': $("#entidad").val(),
		    'remesa': $("#remesa").val(),
		};
	
	requestData[_csrf_param_name] = _csrf_token;

	$.ajax({
		type : "POST",
		url : "estatus",
		 data: requestData ,
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
		
			var datos = JSON.parse(data);
			if(datos.length>0){
				console.log("mayor a cero: ", datos.length);
				document.getElementById("feedback").style.display = "none";
			   display(data);
			}else{
				console.log("menor a cero: ", datos.length);
				$('#feedback').html("<h1>inserte las remesas o ejecute proceso</h1> ");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			$('#feedback').html("<h1>Web service esta fuera de servicio contacte</h1> ");
			clearcanvas();
		},
		done : function(e) {
			console.log("DONE");
		}
	});

}

function display(data) {
	var val = JSON.parse(data);
// 	$('#feedback').html(data);
	var idOpe=new Array();
	var indexe=new Array();
	var colorFondo=new Array();
    $.each(val, function(index, el) {
        console.log("element at " + index + ": " + el); 
        idOpe.push(el);
        indexe.push(index);
        var o = Math.round, r = Math.random, s = 255;
        colorFondo.push('rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + r().toFixed(1) + ')');

    });
	var ctx = document.getElementById('myCanvas').getContext('2d');

	if (window.grafica) {
		window.grafica.clear();
		window.grafica.destroy();
	}
// 	window.grafica = new //// var chart
		window.grafica = new Chart(ctx, {
	    type: 'doughnut',
	    data:{
		datasets: [{
			data: idOpe,
			backgroundColor:colorFondo,
			label: 'Estatus de generar remesa'}],
			labels:indexe},
	    options: {responsive: true}
	});
}

function clearcanvas() {
	//elimina todo lo del canvas --->
	var ctx = document.getElementById('myCanvas').getContext('2d');

	if (window.grafica) {
		window.grafica.clear();
		window.grafica.destroy();
	}
}	

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