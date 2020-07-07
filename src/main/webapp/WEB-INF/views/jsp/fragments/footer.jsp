<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
	<hr>
	<footer>
		<p>&copy; Derechos Reservados, Instituto Nacional Electoral, México.</p>
	</footer>
</div>

<spring:url value="/resources/core/js/jquery.min.js" />
<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


