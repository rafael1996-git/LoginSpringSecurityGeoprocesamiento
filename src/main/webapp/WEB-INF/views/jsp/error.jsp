<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
     

<head>
<title>ERROR</title>
</head>

<body>

	<div class="container">

		<h1>Error</h1>
		<h1></h1>
<h3><a href="javascript:formSubmit()">Vuelve a Intentarlo Aqui</a></h3>
		<p>${exception.message}</p>
		<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

	</div>
   <footer><div id="systeminfo" align="center"><p>© Derechos Reservados, Instituto Nacional Electoral, México.</div></footer>

<%-- 	<jsp:include page="fragments/footer.jsp" /> --%>

</body>
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
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