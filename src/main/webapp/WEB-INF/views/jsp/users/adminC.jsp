<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page session="true"%>
<!DOCTYPE html>

<html lang="en">

<jsp:include page="../fragments/headerRemesa.jsp" />
<spring:url value="welcome" var="urlWelcome" />
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
          <link rel="stylesheet" href="resources/css/adminremesa.css" type="text/css" />
   
<body>

	<div class="container" align="center">
	<fieldset>
  <legend><h3>Instituto Nacional Electoral </h3></legend>
	
	<div   align="right" style="text-transform: capitalize;">
	
			<tr>
				</h2>
				<td><h4> Usuario: ${firstname}</h4>
				</td>
			</tr>
			<div class="message">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<h4> Fecha Actual:</h4><h4><fmt:formatDate type="both" value="${now}"/></h4>
			</div>
	</div>
	
	
		<div class="column">
		 	<c:if test="${not empty mensaje}">
			  	<div class="alert alert-success">${mensaje}
    				<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   				 	<strong>Success!</strong> Remesa Insertada Correctamente
   				</div>
			  
			 </c:if>
			 <c:if test="${not empty mensaje2}">
			  <div class="alert alert-info">${mensaje2}
    				<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 <strong>Atencion!</strong> Funcion Remesa no realizada
   				    </div>
			  
			 </c:if>
		
			<h2>
				Geoprocesamiento de la Remesa de Actualización Cartográfica
			</h2>
			
			
			
			<div class="navbar" align="left">
					<td><h4>Buscar Usuarios Activos</h4></td>
					<form class="form-inline">
			<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search for names.." class="form-control">
			</form>	
			</div>
			<div class="table-wrapper-scroll-y my-custom-scrollbar">

			<table id="myTable" class="table table-bordered table-hover responsive nowrap">
              <thead>
              <tr  class="header">
                 <th style="width:30%;">USUARIO</th>
                <th style="width:27%;">CORREO</th>
                 <th style="width:27%;">CARGO</th>
                 <th style="width:27%;">ENTIDAD</th>
                 <th style="width:27%;">ACTIVO</th>
              </tr>
              </thead>
              <tbody style="height: 30px !important; overflow: scroll; ">
  				<c:forEach var="dato" items="${lista}">
                <tr>
                <td>${dato.usuario}</td>
                <td>${dato.correo}</td>
                <td>${dato.cargo}</td>
                <td>${dato.entidad}</td>
                <td>${dato.activo}</td>
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