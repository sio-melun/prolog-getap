<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br/><h3>Administrateur</h3><br/>

<div class="centerButton">
	<a href="<c:url value="/app/admin/ajoutUser" />" style="text-decoration: none"><img class="ajoutUser" src="<c:url value="../../images/AjouterUser.png"/>" /></a>
	<a href="<c:url value="/app/admin/ajoutUsers" />" style="text-decoration: none"><img class="ajoutUser" src="<c:url value="../../images/AjouterUser.png"/>" /></a>
	<a href="<c:url value="/app/admin/searchUser" />" style="text-decoration: none"><img class="searchUser" src="<c:url value="../../images/RechercherUser.png"/>" /></a>
</div>