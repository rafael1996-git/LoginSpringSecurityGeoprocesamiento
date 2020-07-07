<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
     

<head>
<title>ERROR</title>
</head>

<body>

	<div class="container">

		<h1>Error Page</h1>
		<h1></h1>
<h3><a href="welcome">Vuelve a Intentarlo Aqui</a></h3>
		<p>${exception.message}</p>
		<!-- Exception: ${exception.message}.
		  	<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
				${stackTrace} 
			</c:forEach>
	  	-->

	</div>
   <footer><div id="systeminfo" align="center"><p>© Derechos Reservados, Instituto Nacional Electoral, México.</div></footer>

<%-- 	<jsp:include page="fragments/footer.jsp" /> --%>

</body>
</html>