<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>

<html lang="en"
	xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<meta charset="utf-8">

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
<spring:url value="agregar" var="urlAgregar" />
<spring:url value="/resources/css/admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet" />
<body>



	<div class="container py-4">
	<c:if test="${not empty msg}">
			<div class="alert alert-success alert-dismissible" align="center">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong> Usuario Eliminado Correctamente ${msg}
			</div>

		</c:if>
		<c:if test="${not empty msg1}">

			<div class="alert alert-info alert-dismissible" align="center">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>ERROR!</strong> EL usuario generador de Remesa aun es
				referenciado por la tabla control. ${msg1}
			</div>

		</c:if>
		<c:if test="${not empty CustomSessionAttribute}">

			<div class="alert alert-danger alert-dismissible" align="center">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>MESSAGE : </strong> ${CustomSessionAttribute}
			</div>

		</c:if>
	<section id="contenido" >
	
		<div id="informacion">
			<div
				class="card  col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 .img-fluid" style="min-width: 300px;">
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
		<aside >
		<article >
		<h2 align="center">Geoprocesamiento de la Remesa de
							Actualizacion Cartografica</h2>
							<p>Este Usuario tiene Permisos para Administrar y Procesar la generacion de Remesa.</p>
		</article>
		</aside>
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

				<h4 class="card-title">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<a href="${urlAgregar}" class="btn btn-primary btn-xs">Crear
							Cliente</a>
					</sec:authorize>

				</h4>
				<div class="table-wrapper-scroll-y my-custom-scrollbar">
					<table class="table table-striped table-bordered">
						<thead class="thead-dark">
							<tr>
								<th>ID</th>
								<th>NOMBRE</th>
								<th>APELLIDO PATERNO</th>
								<th>APELLIDO MATERNO</th>
								<th>CORREO</th>
								<th>ENTIDAD</th>
								<th>ELIMINAR</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach var="dato" items="${lista}">
								<tr>
									<td>${dato.id_usuario}</td>
									<td>${dato.nombre}</td>
									<td>${dato.ape_pat}</td>
									<td>${dato.ape_mat}</td>
									<td>${dato.correo}</td>
									<td>${dato.entidad}</td>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<td><a class="btn btn-danger"
											Onclick="return ConfirmDelete();" type="submit"
											name="actiondelete" value="1"
											href="delete?correo=${dato.correo }">Eliminar </a></td>
									</sec:authorize>

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