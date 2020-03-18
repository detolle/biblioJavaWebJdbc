<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>

<div class="container">			

<h4>Liste des Exemplaires</h4>

<form id="form1" action="<%=request.getContextPath()%>/exemplaire" method="post">
<input type="hidden" name="methode" value="POST" />
<input type="hidden" name="id" value="" />

	<table class="table table-striped">
	<!-- test d'une date -->
	<caption></caption>
		<thead>
			<tr>
				<th>idExemplaire</th>
				<th>dateAchat</th>
				<th>status</th>
				<th>isbn</th>
			</tr>
		</thead>		
		<tbody>
			<c:forEach items="${exemplaires}" var="exemplaire">
				<tr>
					<td>${exemplaire.idExemplaire}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${exemplaire.dateAchat}" /></td>
					<td>${exemplaire.status}</td>
					<td>${exemplaire.isbn}</td>

					<!--td><input type="button" class="btn btn-success" onClick="modifier(this.form,'${liste.nomEditeur}')" value="Modifier"/></td>
					<td><input type="button" class="btn btn-danger delete" onClick="supprimer(this.form,'${liste.nomEditeur}')" value="Supprimer"/></td-->
				</tr>			
			</c:forEach>
		</tbody>		
	</table>
	
	<div>	
		<input type="button" class="btn btn-dark text-white" onClick="window.history.back();" value="Back">
		<!--input type="submit" class="btn btn-primary" value="Ajouter"-->
	</div>

</form>

</div>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
function modifier(form, id){
	form.methode.value="PUT";
	form.id.value=id;
//alert(form.methode.value+"="+form.id.value);	
	form.submit();	
}
function supprimer(form, id){
	form.methode.value="DELETE";
	form.id.value=id;
//alert(form.methode.value+"="+form.id.value);	
	form.submit();	
}

</script>
</body>
</html>