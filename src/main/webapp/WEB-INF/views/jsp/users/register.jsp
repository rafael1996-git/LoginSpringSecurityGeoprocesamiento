<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<link rel="stylesheet" href="resources/css/stylesRegister.css" type="text/css" />
<body>


	<div class="container" align="center">
	<fieldset>
  <legend><h3>Instituto Nacional Electoral</h3> </legend>
	<div class="info" align="right" style="text-transform: capitalize;">
	<h4>
		Administrador ${firstname}
		</h4>
		<div class="message">
					<c:set var="now" value="<%=new java.util.Date()%>" />
						<h4> Fecha Actual:</h4><h4><fmt:formatDate type="both" value="${now}"/></h4>
			</div>
	</div>
		
		<h2>Agregar Usuario</h2>

		<form:form id="regForm" name="regForm" class="form-horizontal" modelAttribute="user"
			action="registerProcess" method="post" >

<!-- 			<h1> -->
<!-- 				Instituto Nacional Electoral<br /> -->
<!-- 			</h1> -->

			<spring:bind path="nombre">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Nombre</label>
					<div class="col-sm-10">
						<form:input path="nombre" type="text" class="form-control "
							id="nombre" placeholder="nombre" />
						<form:errors path="nombre" cssErrorClass="campoConError"
							class="col-sm-10" />
						<!-- 					class="control-label" -->
					</div>
				</div>
			</spring:bind>
			<spring:bind path="ape_pat">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Apellido Paterno</label>
					<div class="col-sm-10">
						<form:input path="ape_pat" type="text" class="form-control "
							id="ape_pat" placeholder="ape_pat" />
						<form:errors path="ape_pat" cssErrorClass="campoConError"
							class="col-sm-10" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="ape_mat">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Apellido Materno</label>
					<div class="col-sm-10">
						<form:input path="ape_mat" type="text" class="form-control "
							id="ape_mat" placeholder="ape_mat" />
						<form:errors path="ape_mat" cssErrorClass="campoConError"
							class="col-sm-10" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="puesto">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Puesto</label>
					<div class="col-sm-10">
						<form:input path="puesto" type="text" class="form-control "
							id="puesto" placeholder="puesto" />
						<form:errors path="puesto" cssErrorClass="campoConError"
							class="col-sm-10" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="entidad">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Numero de Entidad</label>
					<div class="col-sm-10">
						<form:input path="entidad" class="form-control " id="entidad"
							placeholder="Entidad llave foranea" />
						<form:errors path="entidad" cssErrorClass="campoConError"
							class="col-sm-10" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="id_tipo_usuario">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Id Tipo de Usuario</label>
					<div class="col-sm-10">
						<form:input path="id_tipo_usuario" class="form-control "
							id="id_tipo_usuario"
							placeholder="id tipo de usuario llave foranea" />
						<form:errors path="id_tipo_usuario" cssErrorClass="campoConError"
							class="col-sm-10" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="correo">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Correo</label>
					<div class="col-sm-10">
						<form:input path="correo" type="text" class="form-control "
							id="correo" placeholder="correo" />
						<form:errors path="correo" cssErrorClass="formFieldError"
							class="col-sm-10" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<form:input path="password" type="text" class="form-control "
							id="password" placeholder="password" />
						<form:errors path="password" cssErrorClass="campoConError"
							class="col-sm-10" />
					</div>
				</div>
			</spring:bind>

			

<!-- 				<div class="alert alert-success"> -->
<!-- 					<button type="button" class="close" data-dismiss="alert">&times;</button> -->
<!-- 					<strong>¡Success!</strong> Es muy importante que leas este mensaje -->
<!-- 					de alerta. -->
<!-- 				</div> -->
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">

								<button type="submit" value="Add"
								class="btn-lg btn-primary pull-right"onclick="Add()">Add</button>
					</div>
				</div>
			
		</form:form>
		</fieldset> 
	</div>
</body>
 <script>
 function Add() {
	 
	 
	 
	 if (document.regForm.ape_pat.value.length==0){
   		alert("!Debes de llenar todos los campos para su registro!")
   		document.regForm.ape_pat.focus()
   		return 0;
	} 
	 if (document.regForm.ape_mat.value.length==0){
	   		alert("!Debes de llenar todos los campos para su registro!")
	   		document.regForm.ape_mat.focus()
	   		return 0;
		} 
	 if (document.regForm.nombre.value.length==0){
	   		alert("!Debes de llenar todos los campos para su registro!")
	   		document.regForm.nombre.focus()
	   		return 0;
		} 
	 if (document.regForm.entidad.value.length==0){
	   		alert("!Debes de llenar todos los campos para su registro!")
	   		document.regForm.entidad.focus()
	   		return 0;
		} 
	 if (document.regForm.id_tipo_usuario.value.length==0){
	   		alert("!Debes de llenar todos los campos para su registro!")
	   		document.regForm.id_tipo_usuario.focus()
	   		return 0;
		} 
	 if (document.regForm.correo.value.length==0){
	   		alert("!Debes de llenar todos los campos para su registro!")
	   		document.regForm.correo.focus()
	   		return 0;
		}
	 var regexp = /^[0-9a-zA-Z._.-]+\@[0-9a-zA-Z._.-]+\.[0-9a-zA-Z]+$/;
	 correo=document.regForm.correo.value
	 
	  if ((regexp.test(correo) == 0) || (correo.length = 0)){
	   		alert("!El correo no cumple con el Formato Email!")
	   		document.regForm.correo.focus()
	   		return 0;
		} 
	 
	 if (document.regForm.password.value.length==0){
	   		alert("!Debes de llenar todos los campos para su registro!")
	   		document.regForm.password.focus()
	   		return 0;
		} 
	 
	 
	   	//el formulario se envia
	   	alert("Muchas gracias por enviar el formulario de  registro");
	   	document.regForm.submit();
	   
	}
	 
						
     
    </script>


<footer><div  align="center" ><h4>© Derechos Reservados, Instituto Nacional Electoral, México.</h4></div></footer>


</html>