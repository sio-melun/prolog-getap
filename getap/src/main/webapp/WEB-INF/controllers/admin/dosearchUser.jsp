<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<h3 class="titre3">Recherche d'utilisateur</h3>

<c:if test="${empty users}">
	Aucun utilisateur trouvé.
</c:if>

<c:if test="${not empty users}">
	<div id="demo">
	<table class="display dataTable">
		<thead>
			<tr class="header">
				<th>Détail</th>
				<th>Nom/Prénom</th>
				<th>Role</th>
				<th>Classe</th>
				<th>Modifier</th>
				<th>Supprimer</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><a
						href="<c:url value="/app/admin/detailUser?id=${user.id}" />"><img
							src="../../images/detail.png"
							onmouseover="this.src='../../images/detailHover.png';"
							onmouseout="this.src='../../images/detail.png';" /></a></td>
					<td style="text-align:left;"><a href="<c:url value="/app/admin/detailUser?id=${user.id}" />">${user.nom} ${user.prenom}</a></td>
					<td>${user.role}</td>
					<td>${user.classe.nom}</td>
					<td><a
						href="<c:url value="/app/admin/editUser?id=${user.id}" />"><img
							src="../../images/modif.png"
							onmouseover="this.src='../../images/modifHover.png';"
							onmouseout="this.src='../../images/modif.png';" /></a></td>
					<td>
						<a
						href="<c:url value="/app/admin/delUser/${user.id}" />"
						onclick="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?')">
						<img
							src="../../images/suppr.png"
							onmouseover="this.src='../../images/supprHover.png';"
							onmouseout="this.src='../../images/suppr.png';" />
							<form method="post">
							<input name=2 type="hidden" value=2 />
							</form>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/admin/index" />"> <input type="button"
		value="Retour à l'index">
	</a>
</div>