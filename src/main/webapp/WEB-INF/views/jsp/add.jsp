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

	<div class="container">
	<c:if test="${not empty mensage}">
			  	
   					<div class="alert alert-success alert-dismissible" align="center">
    					 <button type="button" class="close" data-dismiss="alert" >&times;</button>
   						 <strong>Success!</strong> Usuario Agregado Correctamente
   					 ${mensage}
  					</div>
			  
			 	</c:if>
			 	<c:if test="${not empty msg}">
			  
   					<div class="alert alert-info alert-dismissible" align="center">
    					 <button type="button" class="close" data-dismiss="alert" >&times;</button>
   						 <strong>Atencion!</strong> El Usuario Ya se Encuentra Registrado
   					 ${msg}
  					</div>
			  
			 	</c:if>
			 	
			 	<div class="row-fluid">
			 	<div class="col-sm-12">
			 	<div class="row">
				    <div class="col-sm-4">
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
				    </div>
				    <div class="col-sm-8" align="center">
				    <h2 align="center" style="margin-top: 130px;">Geoprocesamiento de la Remesa de
							Actualizacion Cartografica</h2>
							<p>Agregar Usuarios para el Geoprocesamiento de la Remesa de  Actualizacion Cartografica<br /> ? Intituto Nacional Electoral 2021.</p>
				    </div>
					</div>
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
								<th>ID</th>
								<th>USUARIO</th>
								<th>CORREO</th>
								<th>ENTIDAD</th>
								<th>GREGAR</th>  
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach var="dato" items="${lista}">
								<tr>
									<td>${dato.id_rol}</td>
									<td>${dato.usuario}</td>
									<td>${dato.correo}</td>
									<td>${dato.entidad}</td>
									 <td>
               
                	<a href="register?correo=${dato.correo}" class="btn btn-primary" Onclick="return ConfirmDelete();" type="submit" name="actiondelete" value="1">Agregar
                </a>
				</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
				<!--        <nav th:replace="paginator-nav :: paginator"></nav> -->
			</div>



		</div>
					
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
  $(document).ready(function(){
      $("#tableSearch").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });
    });
  </script>
  
</body>
<br>




<div class="container-xl mt-3 border  p-3 my-3 bg-dark text-white" align="center">
	<jsp:include page="fragments/footer.jsp" />
</div>

</html>