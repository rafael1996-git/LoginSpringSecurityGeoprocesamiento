<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="resources/css/stylesheet.css" type="text/css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="resources/css/common.css" rel="stylesheet">
        <title>Instituto Nacional Electoral:</title>

    </head>
    <style>
body {
  background-color: 	rgb(255, 247, 255);

}
.form-heading{
	color: black;
}
.prompt{
color: black;
}

.p{
color: black;
}
.form-control{
color: black;
}
.validationDiv{
background-color: #d9edf7;

}
</style>
    <body>
    
     <div class="container ${status.error ? 'has-error' : ''}">
        <form name="loginForm"  action="<c:url value="/login" />" method="POST" class="form-signin">
        <h2 class="form-heading">${titulo}</h2>
        
           
            <div class="form-group " >
              
            <input name="username" type="text" class="form-control" placeholder="correo" autofocus="true" required/>
            <input name="password" type="password"class="form-control" placeholder="Password" required/>
         
          
                <label for="captchaCode" class="prompt">Ingresa los caracteres de la imagen.:</label>

                <botDetect:captcha id="securityCaptchaExample" userInputID="captchaCode" />
                
                <div class="validationDiv" >
                    <input type="text" id="captchaCode" name="captchaCode" class="form-control" placeholder="captchaCode" required/>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                
                <input type="hidden" name="type" value="loginForm" />

                <button class="btn btn-lg btn-primary btn-block" type="submit" value="Add" onclick="Add() ">Log In</button>
                

			</div>
				<c:if test="${not empty info}">
				 <div class="alert alert-info alert-dismissible">
    				<button type="button" class="close" data-dismiss="alert" style="align-content: center;">&times;</button>
    				<form:errors path="*" />
   					 <strong>info!</strong> 
   					 ${info}
  				</div>
				</c:if>
				<c:if test="${not empty error}">
				 <div class="alert alert-danger alert-dismissible">
    				<button type="button" class="close" data-dismiss="alert" style="align-content: center;">&times;</button>
    				<form:errors path="*" />
   					 <strong>Error!</strong> 
   					 ${error}
  				</div>
				</c:if>
				<c:if test="${not empty message}">
					<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert" style="align-content: center;">&times;</button>
					<strong>success!</strong>
					 ${message}
					</div>
				</c:if>
				<c:if test="${not empty info2}">
				 <div class="alert alert-info alert-dismissible">
    				<button type="button" class="close" data-dismiss="alert" style="align-content: center;">&times;</button>
    				<form:errors path="*" />
   					 <strong>info!</strong> 
   					 ${info2}
  				</div>
				</c:if>
			
        
        </form>
            

</div>
<script src="resources/core/js/bootstrap.min.js"></script>
<script src="resources/core/js/jquery.min.js"></script>

    </body>
    <script>
        function validate()
        {
            if($('#correo').val().trim().length == 0)
            {
                alert("${usernameRequired}");
                return false;
            }
            else if($('#password').val().trim().length == 0)
            {
                alert("${passwordRequired}");
                return false;
            }
        }
        
     
    </script>
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
