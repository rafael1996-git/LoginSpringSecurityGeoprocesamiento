<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Instituto Nacional Electoral</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />



<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/adminremesa.css"
	type="text/css" />
</head>
<body>
	<spring:url value="welcome.do" var="urlWelcome" />
	<spring:url value="remesa" var="urlAddRemesa" />
	<spring:url value="Avance.do" var="urlAvance" />
	<spring:url value="/logout" var="logoutUrl" />


	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<nav class="navbar navbar-expand-sm navbar-inverse">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="<c:url value="/resources/images/img_0_0_0.jpg"/>" alt="Logo"
				style="width: 85px;">
			</a>
			<ul class="nav navbar-nav">
				<li class="active"><a class="navbar-brand" ></a></li>
				<li class="active"><a class="navbar-brand" href="${urlWelcome}"><span
						class="label label-default"> Inicio</span></a></li>
				<li class="active"><a class="navbar-brand"
					href="${urlAddRemesa}"><span
						class="label label-default">Generar Remesa</span></a></li>
				<li class="active"><a class="navbar-brand" href="${urlAvance}"><span
						class="label label-default">Mostrar Avance </span></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">

				<li class="active"><a class="navbar-brand"
					href="javascript:formSubmit()"><span
						class="label label-default"> Logout</span></a></li>
						<li class="active"><a class="navbar-brand" ></a></li>
			</ul>
			
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</nav>


</body>
</html>


