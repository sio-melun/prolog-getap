<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />
<br />

<div class="centerButton">
	<table>
		<tr>
			<td><a href="<c:url value="/app/admin/ajoutUser" />"><img
					class="ajoutUser"
					src="<c:url value="../../images/AjouterUser.png"/>" />
					<div>Ajouter un Utilisateur</div></a></td>
			<td><a href="<c:url value="/app/admin/ajoutUsers" />"><img
					class="ajoutUser"
					src="<c:url value="../../images/AjouterUser.png"/>" />
					<div>Import Utilisateur via CSV</div></a></td>
			<td><a href="<c:url value="/app/admin/searchUser" />"><img
					class="searchUser"
					src="<c:url value="../../images/RechercherUser.png"/>" />
					<div>Rechercher un Utilisateur</div></a>
			<td>
		<tr>
	</table>
</div>