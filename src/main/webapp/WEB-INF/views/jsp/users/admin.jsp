<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page session="true"%>
<!DOCTYPE html>

<html lang="en">

<jsp:include page="../fragments/header.jsp" />
 <style>
body {
  background-color: 	rgb(255, 247, 255);
  
 
}
.container{
color: black;
}

.message{
color: black;
}
p{
color: black;
}

</style>
<body>

 <div class="container" align="center">
 <fieldset>
  <legend><h3>Instituto Nacional Electoral </h3></legend>
 <div class="info" align="right" style="text-transform: capitalize;">
			<td><h4>Administrador Usuario: ${firstname}</h4>
					
				</td>
       
        <div class="message">
           
            <div class="message">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<h4> Fecha Actual:</h4><h4><fmt:formatDate type="both" value="${now}"/></h4>
			</div>
        </div>
</div>
<br>
<br>
<br>
    <div class="column">
        <h2>Geoprocesamiento de la Remesa de Actualización Cartográfica</h2>
        

        
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
<br>
<br>
<br>
<br>



<footer><div  align="center" ><h4>© Derechos Reservados, Instituto Nacional Electoral, México.</h4></div></footer>

</html>