<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>

<div class="container">			

<h4>Liste des Editeurs</h4>

<form id="form1" action="<%=request.getContextPath()%>/editeur" method="post">
<input type="hidden" name="methode" value="POST" />
<input type="hidden" name="id" value="" />

	<table class="table table-striped">
	<!-- test d'une date -->
	<caption>Date: <fmt:formatDate pattern = "dd/MM/yyyy" value = "${madate}" /></caption>
		<thead>
			<tr>
				<th>Nom</th>
				<th>Ville</th>
			</tr>
		</thead>		
		<tbody>
			<c:forEach items="${editeurs}" var="liste">
				<tr>
					<td>${liste.nomEditeur}</td>

					<td>${liste.ville}</td>
					<td><input type="button" class="btn btn-success" onClick="modifier(this.form,'${liste.nomEditeur}')" value="Modifier"/></td>
					<td><input type="button" class="btn btn-danger delete" onClick="supprimer(this.form,'${liste.nomEditeur}')" value="Supprimer"/></td>
				</tr>			
			</c:forEach>
		</tbody>		
	</table>
	
	<div>	
		<input type="button" class="btn btn-dark text-white" onClick="window.history.back();" value="Back">
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
	form.methode.value="DELETE";
	form.id.value=id;
//alert(form.methode.value+"="+form.id.value);	
	form.submit();	
}

</script>
</body>
</html>