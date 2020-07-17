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

<script src="resources/stilosjs/bootstrap.min.js" type="text/javascript"></script>
<script src="resources/stilosjs/jquery.min.js" type="text/javascript"></script>

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/adminremesa.css"
	type="text/css" />
</head>
<body>
<spring:url value="atras" var="urlHome" />
<spring:url value="register" var="urlAddUser" />
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
				<li class="active"><a class="navbar-brand" href="${urlHome}"><span
						class="label label-default	">Home</span></a></li>
				<li class="active"><a class="navbar-brand" href="${urlAddUser}"><span
						class="label label-default">Agregar Usuario </span></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">

				<li class="active"><a class="navbar-brand"
					href="javascript:formSubmit()"></span><span
						class="label label-default"> Login</span></a></li>
			</ul>
			
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</nav>


</body>
</html>



