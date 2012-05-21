<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />
<br />
<div id="accordion">
	<h3><a href="#">Gestion d'utilisateur</a></h3>
	<div>
		<table>
			<tr>
				<td><a href="<c:url value="/app/admin/ajoutUser" />"><img
						src="<c:url value="../../images/AjouterUser.png"/>" />
						<div>Ajouter un Utilisateur</div></a></td>
				<td><a href="<c:url value="/app/admin/ajoutUsers" />"><img
						src="<c:url value="../../images/AjouterUser.png"/>" />
						<div>Import Utilisateur via CSV</div></a></td>
			</tr>
		</table>
	</div>
	<h3><a href="#">Gestion de recherche</a></h3>
	<div>
		<table>
			<tr>
				<td><a href="<c:url value="/app/admin/searchUser" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Utilisateur</div></a></td>
				<td><a href="<c:url value="/app/admin/searchProf" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Professeur</div></a></td>
				<td><a href="<c:url value="/app/admin/searchClasse" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Recherche par classe</div></a></td>
			</tr>
		</table>
	</div>
	<h3><a href="#">Gestion des demandes faites par les eleves</a></h3>
	<div>
		<table>
			<tr>
				<td><a href="<c:url value="/app/admin/searchDctapUser" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Utilisateur</div></a></td>
				<td><a href="<c:url value="/app/admin/searchDctapProf" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Professeur</div></a></td>
				<td><a href="<c:url value="/app/admin/searchDctapClasse" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Recherche par classe</div></a></td>
			</tr>
		</table>
	</div>
</div>