<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>


<div class="container">			

<h4>Liste des Exemplaires</h4>

<form id="form1" action="<%=request.getContextPath()%>/exemplaire" method="post">
<input type="hidden" name="methode" value="POST" />
<input type="hidden" name="id" value="0" />

	<table class="table table-striped">
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
			<c:forEach items="${exemplaires}" var="liste">
				<tr>
					<td>${liste.idExemplaire}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${liste.dateAchat}"/></td>
					<td>${liste.status}</td>
					<td>${liste.isbn}</td>

 					<td><input type="button" class="btn btn-success" onClick="modifier(this.form,'${liste.idExemplaire}')" value="Modifier"/></td>
					<td><input type="button" class="btn btn-danger delete" onClick="supprimer(this.form,'${liste.idExemplaire}')" value="Supprimer"/></td>
 					
				</tr>			
			</c:forEach>
		</tbody>		
	</table>
	
	<div>	
		<!--input type="button" class="btn btn-dark text-white" onClick="window.history.back();" value="Retour"-->
		<input type="button" class="btn btn-dark text-white" onClick="document.location.href='<%=request.getContextPath()%>/'" value="Retour">
		<input type="submit" class="btn btn-primary" value="Ajouter">
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
	if(confirm("Confirmez-vous la suppression ?")){
		form.methode.value="DELETE";
		form.id.value=id;
//alert(form.methode.value+"="+form.id.value);	
		form.submit();			
	}
}

</script>
</body>
</html>