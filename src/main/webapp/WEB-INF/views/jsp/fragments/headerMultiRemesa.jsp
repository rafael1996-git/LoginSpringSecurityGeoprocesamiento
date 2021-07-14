<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en" >
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%-- <spring:url value="/resources/css/jquery-ui.min.css" var="jquery-uiCss" /> --%>
<%-- <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" /> --%>
<%-- <link href="${bootstrapCss}" rel="stylesheet" /> --%>
<%-- <link href="${jquery-uiCss}" rel="stylesheet" /> --%>
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link href="${coreCss}" rel="stylesheet" />
<sec:authentication var="user" property="principal" />
</head>
<style> 
body { 
 	background-color: rgb(255, 247, 255); 
} 

 .container { 
 	color: black; 
 } 

 th { 
 	color: #333333; 
 } 

 .message { 
 	color: black; 
} 

p { 
 	color: black; 
 } 

 .input-group.md-form.form-sm.form-1 input{
  border: 1px solid #bdbdbd;
  border-top-right-radius: 0.25rem;
  border-bottom-right-radius: 0.25rem;
}
.input-group.md-form.form-sm.form-2 input {
  border: 1px solid #bdbdbd;
  border-top-left-radius: 0.25rem;
  border-bottom-left-radius: 0.25rem;
}
.input-group.md-form.form-sm.form-2 input.red-border {
  border: 1px solid #ef9a9a;
}
.input-group.md-form.form-sm.form-2 input.lime-border {
  border: 1px solid #cddc39;
}
.input-group.md-form.form-sm.form-2 input.amber-border {
  border: 1px solid #ffca28;
}
 </style> 
<body>
<spring:url value="viewAdmin" var="urlHome" />
<spring:url value="welcome" var="urlRemesa" />
<spring:url value="MultiProcesos" var="urlMulti" />
<spring:url value="/logout" var="logoutUrl" />
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
  <a class="navbar-brand" href="#">Ine Cartografia</a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb" aria-expanded="true">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div id="navb" class="navbar-collapse collapse hide">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link"  href="${urlHome}">Home</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${urlRemesa}">Generar Remesa</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${urlMulti}">Generar Multiremesa</a>
      </li>
    </ul>
 
    <ul class="nav navbar-nav navbar-right" >
      <li class="nav-item active">
        <a class="nav-link" href="javascript:formSubmit()"><span class="fas fa-sign-in-alt"></span> Login</a>
      </li>
    </ul>
     <form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
  </div>
 
</nav>

</body>
<!-- <script src="resources/js/jquery-3.2.1.min.js" type="text/javascript"></script> -->
<!-- <script src="resources/js/popper.min.js" type="text/javascript"></script> -->
<!-- <script src="resources/js/bootstrap.min.js" type="text/javascript"></script> -->
<!-- <script src="resources/js/jquery-ui.min.js" type="text/javascript"></script> -->
</html>