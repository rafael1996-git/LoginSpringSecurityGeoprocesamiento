<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>

<html lang="en">
<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<jsp:include page="../fragments/headerMultiRemesa.jsp" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<spring:url value="MultiAvance.do" var="urlWelcome" />
<link rel="stylesheet" href="resources/css/estilos.css">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/adminremesa.css"
	type="text/css" />
		<spring:url value="MultiRemesa" var="urlAddRemesa" />
		<spring:url value="Multiproceso" var="multiproceso" />
<style>
* {
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    margin: 0;
    padding: 0;
}
 
 
.wrap {
    width: 90%;
    max-width: 1000px;
    margin: 0 20px;
    /*margin: auto;*/
}
 
.formulario h2 {
    font-size: 16px;
    color: #000000;
    margin-bottom: 20px;
    margin-left: 20px;
}
 
.formulario > div {
    padding: 20px 0;
    border-bottom: 1px solid #ccc;
}

</style>
<body>

	<div class="container" align="center">
		<fieldset>
			<legend>
				<h3>Instituto Nacional Electoral</h3>
			</legend>

			<div align="right" style="text-transform: capitalize;">

				<tr>
					</h2>
					<td><h4>Usuario: ${firstname}</h4></td>
				</tr>
				<div class="message">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<h4>Fecha Actual:</h4>
					<h4>
						<fmt:formatDate type="both" value="${now}" />
					</h4>
				</div>
			</div>


			<div class="column">
						<c:if test="${not empty multimensaje1}">
			  		<div class="alert alert-info">${multimensaje1}
    					<a href="${multiproceso}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 	<strong>Error!</strong>
   					</div>
			  
			 	</c:if>
			 		 <c:if test="${not empty multimensaje2}">
			  <div class="alert alert-info">${multimensaje2}
    				<a href="${multiproceso}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 <strong>Atencion!</strong>"La remesa no puede procesarse, ya que aun no se concluye la carga al SIIRFE en alguna de las entidades seleccionadas."
   				    </div>
			  
			 </c:if>
			 <c:if test="${not empty multimsj}">
			  <div class="alert alert-info">${multimsj}
    				<a href="${multiproceso}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   					 <strong>Error!</strong>
   				    </div>
			  
			 </c:if>
			  <c:if test="${not empty Multimensaje}">
			  	<div class="alert alert-success">${Multimensaje}
    				<a href="${multiproceso}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   				 	<strong>¡Success!</strong> Multi Procesamiento Funcionando  Correctamente
   				</div>
			  
			 </c:if>
		<div  class="wrap">
			<div class="info">
				<h1>Geoprocesamiento de la Remesa de Actualización Cartográfica</h1>
				<h2>Selección de Entidades a Procesar</h2>
			</div>
<!-- 			<div class="table-wrapper-scroll-y my-custom-scrollbar" > -->
			<form:form  modelAttribute="valor" class="formulario" action="multiremesaExecute" >
			<div class="checkbox">
				<br>
				<input type="checkbox" name="num1" id="1"value="1" >
				<label for="1">1</label>
<!-- 				<br> -->
				<input type="checkbox" name="num2" id="2" value="2">
				<label for="2">2</label>
<!-- 				<br> -->
				<input type="checkbox" name="num3" id="3" value="3">
				<label for="3">3</label>
<!-- 				<br> -->
				<input type="checkbox" name="num4" id="4" value="4">
				<label for="4">4</label>
<!-- 				<br> -->
				<input type="checkbox" name="num5" id="5" value="5">
				<label for="5">5</label>
<!-- 				<br> -->
				<input type="checkbox" name="num6" id="6" value="6">
				<label for="6">6</label>
<!-- 				<br> -->
				<input type="checkbox" name="num7" id="7" value="7">
				<label for="7">7</label>
<!-- 				<br> -->
				<input type="checkbox" name="num8" id="8" value="8">
				<label for="8">8</label>
<!-- 				<br> -->
				<input type="checkbox" name="num9" id="9" value="9" >
				<label for="9">9</label>
<!-- 				<br> -->
				<input type="checkbox" name="num10" id="10" value="10">
				<label for="10">10</label>
<!-- 				<br> -->
				<input type="checkbox" name="num11" id="11" value="11">
				<label for="11">11</label>
<!-- 				<br> -->
				<input type="checkbox" name="num12" id="12" value="12" >
				<label for="12">12</label>
<!-- 				<br> -->
				<input type="checkbox" name="num13" id="13" value="13">
				<label for="13">13</label>
<!-- 				<br> -->
				<input type="checkbox" name="num14" id="14" value="14">
				<label for="14">14</label>
<!-- 				<br> -->
				<input type="checkbox" name="num15" id="15" value="15" >
				<label for="15">15</label>
<!-- 				<br> -->
				<input type="checkbox" name="num16" id="16" value="16">
				<label for="16">16</label>
<!-- 				<br> -->
				<input type="checkbox" name="num17" id="17" value="17">
				<label for="17">17</label>
<!-- 				<br> -->
				<input type="checkbox" name="num18" id="18" value="18">
				<label for="18">18</label>
<!-- 				<br> -->
				<input type="checkbox" name="num19" id="19" value="19" >
				<label for="19">19</label>
<!-- 				<br> -->
				<input type="checkbox" name="num20" id="20" value="20">
				<label for="20">20</label>
<!-- 				<br> -->
				<input type="checkbox" name="num21" id="21" value="21">
				<label for="21">21</label>
<!-- 				<br> -->
				<input type="checkbox" name="num22" id="22" value="22" >
				<label for="22">22</label>
<!-- 				<br> -->
				<input type="checkbox" name="num23" id="23" value="23">
				<label for="23">23</label>
<!-- 				<br> -->
				<input type="checkbox" name="num24" id="24" value="24">
				<label for="24">24</label>
<!-- 				<br> -->
				<input type="checkbox" name="num25" id="25" value="25">
				<label for="25">25</label>
<!-- 				<br> -->
				<input type="checkbox" name="num26" id="26" value="26">
				<label for="26">26</label>
<!-- 				<br> -->
				<input type="checkbox" name="num27" id="27" value="27">
				<label for="27">27</label>
<!-- 				<br> -->
				<input type="checkbox" name="num28" id="28" value="28">
				<label for="28">28</label>
<!-- 				<br> -->
				<input type="checkbox" name="num29" id="29" value="29">
				<label for="29">29</label>
<!-- 				<br> -->
				<input type="checkbox" name="num30" id="30" value="30">
				<label for="30">30</label>
				<input type="checkbox" name="num31" id="31" value="31">
				<label for="31">31</label>
<!-- 				<br> -->
				<input type="checkbox" name="num32" id="32" value="32">
				<label for="32">32</label>
<!-- 				<br> -->
				
			
			</div>
			<button  class="btn btn-primary"  type="submit">Ejecuta MultiProcesos</button>
			</form:form>
				
<!-- 			</div> -->
		</div>

</body>
<script type="text/javascript">
const form=document.getElementById('form');

form.addEventListener('submit',(event) =>{
 const fd= new FormData(form);
   
  console.log(fd.getAll('check'));
  event.preventDefault();
});
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

<footer>
	<div align="center">
		<h4>© Derechos Reservados, Instituto Nacional Electoral, México.</h4>
	</div>
</footer>

</html>