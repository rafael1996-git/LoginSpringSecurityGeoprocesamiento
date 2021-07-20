<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>

<html lang="en" 
     xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<jsp:include page="fragments/HeaderAdmin.jsp" />
 
<style>
.my-custom-scrollbar {
	position: relative;
	height: 200px;
	overflow: auto;
}

.table-wrapper-scroll-y {
	display: block;
}
</style>
<spring:url value="welcome" var="urlAgregar" />
<spring:url value="/resources/css/admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet" />
<body>
<spring:url value="Avance" var="urlavance" />
<spring:url value="remesa" var="urlAddRemesa" />
	<div class="container py-4">
		<c:if test="${not empty mensajerror}">
			<div class="alert alert-success alert-dismissible" align="center">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>SUCCESS!</strong> proceso funcionando sin error , verifique
				denuevo la consulta de avance . ${mensajerror}
			</div>

		</c:if>
		<c:if test="${not empty mensaje}">
			<div class="alert alert-success alert-dismissible" align="center">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>¡Success!</strong> Remesa Realizada Correctamente ${mensaje}
			</div>
		</c:if>

		<c:if test="${not empty mensaje2}">
			<div class="alert alert-info alert-dismissible" align="center">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>¡Atencion!</strong> "La remesa no puede procesarse, ya que
				aun no se concluye la carga al SIIRFE." ${mensaje2}
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
					
				</article>
			</aside>
		</div>
		<div id="CardEntidadesRemesa">

			<div class="card">
				<div class="card-body">
					<h4 class="card-title">Generacion de Remesa</h4>
					<p class="card-text">Este Usuario tiene Permisos para Administrar y Procesar la
						generacion de Remesa</p>
					 <a href="${urlAddRemesa}" class="btn btn-primary">Generar Remesa</a>
					  <a href="${urlavance}"  class="btn btn-primary">Mostrar Avance</a>
				</div>
			</div>
		</div>


		<br>

		<div class="input-group md-form form-sm form-1 pl-0">
			<div class="input-group-prepend">
				<span class="input-group-text cyan lighten-2"><i
					class="fas fa-search text-white" aria-hidden="true"></i></span>
			</div>
			<input class="form-control my-0 py-1" id="tableSearch" type="text"
				placeholder="Search" aria-label="Search">
		</div>
		<br>

		<div class="card bg-light">

			<div class="card-body">

				<div class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-striped table-bordered">
						<thead class="thead-dark">
							<tr>
								<th>ID_ROL</th>
								<th>USUARIO</th>
								<th>CORREO</th>
								<th>ENTIDAD</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach var="dato" items="${lista}">
								<tr>
									<td>${dato.id_rol}</td>
									<td>${dato.usuario}</td>
									<td>${dato.correo}</td>
									<td>${dato.entidad}</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
				<!--        <nav th:replace="paginator-nav :: paginator"></nav> -->
			</div>



		</div>
	</div>

	<script>
		function ConfirmDelete() {
			var x = confirm("Estas seguro de Borrar el Usuario?");
			if (x)
				return true;
			else
				return false;
		}
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#tableSearch")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$("#myTable tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});
	</script>
</body>
<br>




<div class="container-xl mt-3 border  p-3 my-3 bg-dark text-white"
	align="center">
	<jsp:include page="fragments/footer.jsp" />
</div>

</html>