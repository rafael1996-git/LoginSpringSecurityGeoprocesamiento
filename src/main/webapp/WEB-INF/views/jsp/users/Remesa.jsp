<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html lang="en">

<jsp:include page="../fragments/headerRemesa.jsp" />
<spring:url value="welcome" var="urlWelcome" />
<spring:url value="Avance.do" var="urlAvance" />
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
				<td><h4>Usuario: ${firstname}</h4>
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
       			 <c:if test="${not empty mensaje}">
			  	<div class="alert alert-success">${mensaje}
    				<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   				 	<strong>¡Success!</strong> Remesa Realizada Correctamente
   				</div>
			  
			 </c:if>
			 <c:if test="${not empty mensaje2}">
			  <div class="alert alert-info">${mensaje2}
    				<a href="${urlWelcome}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 <strong>Atencion!</strong>"La remesa no puede procesarse, ya que aun no se concluye la carga al SIIRFE."
   				    </div>
			  
			 </c:if>
			 <c:if test="${not empty mensajerror}">
			  		<div class="alert alert-info">${mensajerror}
    					<a href="${urlAvance}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>SUCCESS!</strong>proceso funcionando sin error , verifique denuevo la consulta de avance ene el geoprocesamiento de su grafica.
   					</div>
			  
			 	</c:if>
				 
				
        </div>
		</fieldset> 
			
 </div>
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
<footer>
	<div align="center">
		<h4>© Derechos Reservados, Instituto Nacional Electoral, México.</h4>
	</div>
</footer>
</html>
