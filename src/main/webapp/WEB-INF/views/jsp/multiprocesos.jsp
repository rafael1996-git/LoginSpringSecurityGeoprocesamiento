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
<%-- <jsp:include page="fragments/HeaderAdmin.jsp" /> --%>
<jsp:include page="fragments/headerMultiRemesa.jsp" />
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<%-- <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" /> --%>
<%-- <link href="${bootstrapCss}" rel="stylesheet" /> --%>
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
<spring:url value="/resources/css/admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet" />
<body>

	<div class="container py-4">
		<c:if test="${not empty multimensaje1}">
			<div class="alert alert-info" align="center">${multimensaje1}
				<a href="${multiproceso}" class="close" data-dismiss="alert"
					aria-label="close">&times;</a> <strong>Error!</strong>
			</div>

		</c:if>
		<c:if test="${not empty multimensaje2}">
			<div class="alert alert-info" align="center">${multimensaje2}
				<a href="${multiproceso}" class="close" data-dismiss="alert"
					aria-label="close">&times;</a> <strong>Atencion!</strong>"La remesa
				no puede procesarse, ya que aun no se concluye la carga al SIIRFE en
				alguna de las entidades seleccionadas."
			</div>

		</c:if>
		<c:if test="${not empty multimsj}">
			<div class="alert alert-info" align="center">${multimsj}
				<a href="${multiproceso}" class="close" data-dismiss="alert"
					aria-label="close">&times;</a> <strong>Error!</strong>
			</div>

		</c:if>
		<c:if test="${not empty Multimensaje}">
			<div class="alert alert-success" align="center">${Multimensaje}
				<a href="${multiproceso}" class="close" data-dismiss="alert"
					aria-label="close">&times;</a> <strong>¡Success!</strong> Multi
				Procesamiento Funcionando Correctamente
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
		<div id="contenedor" align="center">
			<aside>
				<article>
					<h2 align="center">Geoprocesamiento de la Remesa de
						Actualizacion Cartografica</h2>
					<p>Este Usuario tiene Permisos para Administrar y Procesar la
						generacion de Remesa.</p>
				</article>
			</aside>
		</div>


		<br>
		<div id="CardEntidades">
			<div class="card " style="min-width: 300px;">
				<div class="card-header">
					<h4 class="card-title" align="center">Selección de Entidades a
						Procesar</h4>
				</div>
				<div class="card-body">
					<form class="form-inline" action="#" id="formEntidad">
						<div class="table-wrapper-scroll-y my-custom-scrollbar"
							style="width: 100%;">
							<table class="table table-striped table-bordered">
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
						<br> <br> <br>

						<button class="btn btn-primary" type="submit">Ejecuta
							MultiProcesos</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>

			</div>
		</div>



		<div class="col align-self-center" align="center">
			<h1>Estatus:</h1>
			<div id="contieneCanvas"></div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="pleaseWaitDialog" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
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
		   	<div class="modal fade" id="dialog" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content"></div>
							<div class="modal-header">
								<p>Se esta procesando la solicitud</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">cerrar</button>
							</div>
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



<div class="container-xl mt-3 border  p-3 my-3 bg-dark text-white"
	align="center">
	<jsp:include page="fragments/footer.jsp" />
</div>

</html>