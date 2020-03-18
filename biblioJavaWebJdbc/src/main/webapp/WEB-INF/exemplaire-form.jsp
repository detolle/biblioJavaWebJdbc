<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>


<div class="container">			

<h4>Exemplaires</h4>

<form id="form1" action="<%=request.getContextPath()%>/exemplaireUpd" method="post">
<input type="hidden" name="idExemplaire" value="${exemplaire.idExemplaire}" />
<!-- ajout de l'hidden status car un input disable n'est pas renvoye par http -->
<input type="hidden" name="status" value="${exemplaire.status}" id="hstatus"/>
<div id="erreur" class="text-warning">${erreur}</div>

<fieldset class="form-group">
<label  for="isbn">ISBN</label>
<select name="isbn" class="form-control">
   <option value="" label="--- Select ---"/>
<c:forEach items="${livres}" var="livre">   
   <option value="${livre.isbn}" label="${livre.isbn}|${livre.titre}" <c:if test="${livre.isbn==exemplaire.isbn}">selected</c:if>/>
</c:forEach>   
</select>
</fieldset>

<fieldset class="form-group">
<label for="dateAchat">Date (à lancer dans un navigateur car l'input type=date HTML5 ne fonctionne pas dans eclipse)</label>
<input type="date" class="form-control" name="dateAchat" id="dateAchat" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${exemplaire.dateAchat}"/>"/>
</fieldset>

<fieldset class="form-group">
<label for="status">Statut</label>
<input type="text" class="form-control" name="status" id="status" value="${exemplaire.status}" placeholder="" required/>
</fieldset>

<!--input type="button" class="btn btn-dark text-white" onClick="window.history.back()" value="Retour" /-->
<input type="button" class="btn btn-dark text-white" onClick="document.location.href='<%=request.getContextPath()%>/exemplaire'" value="Retour" />
<input type="reset" class="btn btn-secondary" value="Réinitialiser " />
<input type="submit" class="btn btn-primary" value="Valider"/>
</form>

</div>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
document.getElementById("status").disabled = true;
</script>

</body>
</html>