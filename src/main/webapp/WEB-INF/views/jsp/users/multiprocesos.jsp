<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>

<html lang="en">
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<jsp:include page="../fragments/headerMultiRemesa.jsp" />
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/estilos.css">

<link rel="stylesheet" href="resources/css/adminremesa.css"
	type="text/css" />

<script src="resources/js/bootstrap.min.js"></script>
<style>
* {
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	margin: 0;
	padding: 0;
}

.wrap {
	width: 90%;
	max-width: 1000px;
	margin: 0 20px;
	/*margin: auto;*/
}

.formulario h2 {
	font-size: 16px;
	color: #000000;
	margin-bottom: 20px;
	margin-left: 20px;
}

.formulario>div {
	padding: 20px 0;
	border-bottom: 1px solid #ccc;
}

canvas {
	background: grey;
}

.modal-header {
	background-color: pink;
}

.modal-content {
	overflow: hidden;
}

.modal-body {
	word-break: break-all !important;
}

div.round3 {
	border: 1px solid red;
	border-radius: 12px;
}

.modal {
	bottom: unset !important;
}

.my-custom-scrollbar {
position: relative;
height: 200px;
overflow: auto;
}
.table-wrapper-scroll-y {
display: block;
}

</style>

<body>
	<div class="container">
		<fieldset>
			<legend>
				<h3>Instituto Nacional Electoral</h3>
			</legend>

			<div align="right" style="text-transform: capitalize;">
				<tr>
					</h2>
					<td><h4>Usuario: ${firstname}</h4></td>
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
				<c:if test="${not empty multimensaje1}">
					<div class="alert alert-info">${multimensaje1}
						<a href="${multiproceso}" class="close" data-dismiss="alert"
							aria-label="close">&times;</a> <strong>Error!</strong>
					</div>

				</c:if>
				<c:if test="${not empty multimensaje2}">
					<div class="alert alert-info">${multimensaje2}
						<a href="${multiproceso}" class="close" data-dismiss="alert"
							aria-label="close">&times;</a> <strong>Atencion!</strong>"La
						remesa no puede procesarse, ya que aun no se concluye la carga al
						SIIRFE en alguna de las entidades seleccionadas."
					</div>

				</c:if>
				<c:if test="${not empty multimsj}">
					<div class="alert alert-info">${multimsj}
						<a href="${multiproceso}" class="close" data-dismiss="alert"
							aria-label="close">&times;</a> <strong>Error!</strong>
					</div>

				</c:if>
				<c:if test="${not empty Multimensaje}">
					<div class="alert alert-success">${Multimensaje}
						<a href="${multiproceso}" class="close" data-dismiss="alert"
							aria-label="close">&times;</a> <strong>¡Success!</strong> Multi
						Procesamiento Funcionando Correctamente
					</div>

				</c:if>
			</div>
			<div class="info">
				<h1>Geoprocesamiento de la Remesa de Actualización Cartográfica</h1>
				<h2>Selección de Entidades a Procesar</h2>
			</div>
			<div  align="center">
			
			<form class="form-inline" action="#" id="formEntidad">
			<div class="table-wrapper-scroll-y my-custom-scrollbar" align="center">
				<table class="table table-hover table-bordered" >
					<c:forEach items="${entidadesActivas}" var="flagEntidades"
					varStatus="statusEntidad">
						 <tr> 
							<td><input type="checkbox" name="num${flagEntidades.key}"
							id="${flagEntidades.key}" value="${flagEntidades.key}" /><label
							for="${flagEntidades.value}">${flagEntidades.value}</label></td>
						 </tr> 
					</c:forEach>
				</table>
			 </div>
				<br>
				<button class="btn btn-primary" type="submit">Ejecuta
					MultiProcesos</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			</div>
	</div>

	<div class="col align-self-center" align="center">
		<h1>Estatus:</h1>
		<div id="contieneCanvas"></div>
	</div>
	</fieldset>
	</div>
	<div class="modal fade bd-example-modal-sm" id="pleaseWaitDialog"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<p>Se esta procesando la solicitud</p>
				</div>
				<div class="modal-body">
					<div class="text-center">
						<img src='resources/images/ajax-loader.gif'
							class="rounded mx-auto d-block" alt="..." />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog"
		id="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">cerrar</button>
			</div>
		</div>
	</div>
</body>
<script src="resources/js/funcionRemesa.js" type="text/javascript"></script>
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