<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>

<html xmlns="https://www.thymeleaf.org">
<meta charset="ISO-8859-1">

<spring:url value="viewAdmin" var="urlWelcome" />
<jsp:include page="../fragments/header.jsp" />
<style>
body {
	background-color: rgb(255, 247, 255);
}

.container {
	color: black;
}

th {
	color: #FFFFFF;
}

.message {
	color: black;
}

p {
	color: black;
}

tr.header {
	background-color: rgb(0, 0, 0);
}
</style>
<body>

	<div class="container" align="center">
		<fieldset>
			<legend>
				<h3>Instituto Nacional Electoral</h3>
			</legend>
			<div class="info" align="right" style="text-transform: capitalize;">
				<td><h4>Administrador : ${firstname}</h4></td>

				<div class="message">

					<div class="message">
						<c:set var="now" value="<%=new java.util.Date()%>" />
						<h4>Fecha Actual:</h4>
						<h4>
							<fmt:formatDate type="both" value="${now}" />
						</h4>
					</div>
				</div>
			</div>
			<br> <br> <br>
			<div class="column">

				<h2>Geoprocesamiento de la Remesa de Actualización Cartográfica</h2>
				<c:if test="${not empty msg}">
			  		<div class="alert alert-success">${msg}
    					<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>Success!</strong> Usuario Eliminado Correctamente
   					</div>
			  
			 	</c:if>
			 	<c:if test="${not empty msg1}">
			  		<div class="alert alert-info">${msg1}
    					<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>ERROR!</strong>EL usuario generador de Remesa aun es referenciado por la tabla control.
   					</div>
			  
			 	</c:if>
			 	
				<br> 
				<br>


				<div class="navbar" align="left">
					<td><h4>Buscar Usuarios de control</h4></td>
					<form class="form-inline">
						<input type="text" id="myInput" onkeyup="myFunction()"
							placeholder="Search for names.." class="form-control">
					</form>
				</div>
				<div class="table-wrapper-scroll-y my-custom-scrollbar">

					<table id="myTable"
						class="table table-bordered table-hover responsive nowrap">
						<thead>
							<tr class="header">
								<th style="width: 27%;">ID</th>
								<th style="width: 30%;">NOMBRE</th>
								<th style="width: 27%;">APELLIDO PATERNO</th>
								<th style="width: 27%;">APELLIDO MATERNO</th>
								<th style="width: 27%;">CORREO</th>
								<th style="width: 27%;">ENTIDAD</th>
								<th style="width: 27%;">ELIMINAR</th>
							</tr>
						</thead>
						<tbody style="height: 10px !important; overflow: scroll;">
							<c:forEach var="dato" items="${lista}">
								<tr>
							     	<td>${dato.id_usuario}</td>
									<td>${dato.nombre}</td>
									<td>${dato.ape_pat}</td>
									<td>${dato.ape_mat}</td>
									<td>${dato.correo}</td>
									<td>${dato.entidad}</td>
									<td><a class="btn btn-danger"
										Onclick="return ConfirmDelete();" type="submit"
										name="actiondelete" value="1"
										href="delete?correo=${dato.correo }">Eliminar </a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
		</fieldset>
	</div>
	<script>
		function myFunction() {
			// Declare variables
			var input, filter, table, tr, td, i;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");

			// Loop through all table rows, and hide those who don't match the search query
			for (i = 0; i < tr.length; i++) {
				if (!tr[i].classList.contains('header')) {
					td = tr[i].getElementsByTagName("td"), match = false;
					for (j = 0; j < td.length; j++) {
						if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
							match = true;
							break;
						}
					}
					if (!match) {
						tr[i].style.display = "none";
					} else {
						tr[i].style.display = "";
					}
				}
			}
		}
	</script>

	<script>
		function ConfirmDelete() {
			var x = confirm("Estas seguro de Borrar el Usuario?");
			if (x)
				return true;
			else
				return false;
		}
	</script>
</body>
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