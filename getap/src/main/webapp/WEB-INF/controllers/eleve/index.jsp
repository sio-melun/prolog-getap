<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Elève</h1>
<br/>
<h3>Gérer les demandes de TAP</h3>
<br/>

<a href="<c:url value="/app/eleve/mesdctap" />" style="text-decoration:none"><input type="button" value="Voir et éditer"></a>
<a href="<c:url value="/app/eleve/ajoutdctap" />" style="text-decoration:none"><input type="button" value="Ajouter"></a>