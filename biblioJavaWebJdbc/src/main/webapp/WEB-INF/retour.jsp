<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>


<div class="container">			

<h2>Retour</h2>

<form id="form1" action="<%=request.getContextPath()%>/retour" method="post">
<input type="hidden" name="idExemplaire" value="${idExemplaire}" />

<div id="erreur" class="bg-warning text-dark">${erreur}</div>
<div id="erreur" class="bg-success text-dark">${messageOK}</div>

<c:if test="${empty idExemplaire}">
	<fieldset class="form-group">
	<label for="idex">Identifiant Exemplaire</label>
	<input type="text" class="form-control" name="idex" id="idex" value="" placeholder="" required/>
	</fieldset>
</c:if>

<c:if test="${not empty idExemplaire}">
	<table class="table table-striped">
	<!--  -->
	<caption class="bg-success text-white">${message}</caption>
		<thead>
			<tr>
				<th>idExemplaire</th>
				<th>dateAchat</th>
				<th>status</th>
				<th>isbn</th>
			</tr>
		</thead>		
		<tbody>
			<tr>
				<td>${exemplaire.idExemplaire}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${exemplaire.dateAchat}"/></td>
				<td>${exemplaire.status}</td>
				<td>${exemplaire.isbn}</td>
			</tr>			
		</tbody>		
	</table>	
</c:if>


<!--input type="button" class="btn btn-dark text-white" onClick="window.history.back()" value="Retour" /-->
<input type="button" class="btn btn-dark text-white" onClick="document.location.href='<%=request.getContextPath()%>/'" value="Retour" />
<c:if test="${not empty idExemplaire}">
<input type="button" class="btn btn-warning" onClick="annuler(this.form);" value="Annuler" />
</c:if>
<input type="submit" class="btn btn-primary" value="Valider"/>
</form>

</div>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
function annuler(form){
	form.idExemplaire.value="";
	form.method="get";
//alert(form.methode.value+"="+form.id.value);	
	form.submit();	
}
</script>
</body>
</html>