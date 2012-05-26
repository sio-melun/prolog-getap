<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<h3 class="titre3">Recherche d'utilisateur</h3>

<c:if test="${empty users}">
	Aucun utilisateur trouvé.
</c:if>

<c:if test="${not empty users}">
	<table class="legend2">
		<tr>
			<td><img src="../../images/modif.png" /> : Modifier</td>
			<td><img src="../../images/suppr.png" /> : Supprimer</td>
		</tr>
	</table>
	<table id="search" class="tablesorter">
		<thead>
			<tr class="header">
				<th>Nom/Prénom</th>
				<th>Login</th>
				<th>Mot de passe</th>
				<th>Role</th>
				<th>Classe</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td style="text-align:left;">${user.nom} ${user.prenom}</td>
					<td>${user.login}</td>
					<td>${user.pass}</td>
					<td>${user.role}</td>
					<td>${user.classe.nom}</td>
					<td><a
						href="<c:url value="/app/admin/editUser?id=${user.id}" />"><img
							src="../../images/modif.png"
							onmouseover="this.src='../../images/modifHover.png';"
							onmouseout="this.src='../../images/modif.png';" /></a></td>
					<td><a href=""
						onclick="if(confirm('Voulez-vous vraiment supprimer cet utilisateur ?')){window.location.href='delUser/${user.id}';}"><img
							src="../../images/suppr.png"
							onmouseover="this.src='../../images/supprHover.png';"
							onmouseout="this.src='../../images/suppr.png';" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/admin/index" />"> <input type="button"
		value="Retour à l'index">
	</a>
</div>