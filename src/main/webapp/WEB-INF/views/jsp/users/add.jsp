<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<spring:url value="agregar" var="urlWelcome" />
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
          <link rel="stylesheet" href="resources/css/adminremesa.css" type="text/css" />
<style>
body {
  background-color: 	rgb(255, 247, 255);
  
 
}
h1{
color: black;
}
td{
color: black;
}
p{
color: black;
}

</style>
<body>
 <div class="container" align="center">
  <fieldset>
  <legend><h3>Instituto Nacional Electoral </h3> </legend>
  <div  align="right" style="text-transform: capitalize;">
			<tr>
				</h2>
				<td><h4>Administrador Usuario: ${firstname}</h4>
				</td>
			</tr>
			<div class="message">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<h4> Fecha Actual:</h4><h4><fmt:formatDate type="both" value="${now}"/></h4>
			</div>
	</div>
 
 	<br>
<br>
<br>
		 <div class="column">
       			 <h2> Geoprocesamiento de la Remesa de Actualización Cartográfica</h2>
       			 <c:if test="${not empty mensage}">
			  		<div class="alert alert-success">${mensage}
    					<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>Success!</strong> Usuario Agregado Correctamente
   					</div>
			  
			 	</c:if>
			 	<c:if test="${not empty msg}">
			  		<div class="alert alert-info">${msg}
    					<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>Atencion!</strong> El Usuario Ya se Encuentra Registrado o La llave (entidad) no está presente en la tabla «entidad» Por lo tanto no se completo la operacion
   					</div>
			  
			 	</c:if>
       			 
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
                 <th style="width:27%;">GREGAR</th>                 
              </tr>
              </thead>
              <tbody style="height: 10px !important; overflow: scroll; ">
  				<c:forEach var="dato" items="${lista}">
                <tr>
                <td>${dato.usuario}</td>
                <td>${dato.correo}</td>
                <td>${dato.cargo}</td>
                <td>${dato.entidad}</td>
                <td>${dato.activo}</td>
                <td>
               
                <a href="register?correo=${dato.correo}" class="btn btn-primary" Onclick="return ConfirmDelete();" type="submit" name="actiondelete" value="1">Agregar
                </a>
				</td>
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
<script type="text/javascript">
function ConfirmDelete()
{
  var x = confirm("Estas Seguro de Agregar el Nuevo Usuario Generador de Remesa?");
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
