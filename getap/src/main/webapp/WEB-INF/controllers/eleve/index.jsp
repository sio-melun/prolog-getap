<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- <h1>Elève</h1>

<a href="<c:url value="/app/eleve/mesdctap" />" style="text-decoration:none"><input type="button" value="Voir et éditer"></a>
<a href="<c:url value="/app/eleve/ajoutdctap" />" style="text-decoration:none"><input type="button" value="Ajouter"></a>--%>

<br/>
	<h3>Gérer les demandes de TAP</h3>
<br/>

<div class="centerButton" >
	<a href="<c:url value="/app/eleve/ajoutdctap" />"><img class="creer" src="<c:url value="../../images/IconeDemandesTransparent.png"/>" /></a>
	<img class="ligne" src="<c:url value="../../images/ligne.png"/>" />
	<a href="<c:url value="/app/eleve/mesdctap" />"><img class="editer" src="<c:url value="../../images/IconeDemandesTransparent.png"/>" /></a>
</div>