<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recherche d'un utilisateur</title>
</head>
<body>
<table class="legend">
	<tr>
		<td>
			<img src="../../images/buttonModif.png"/> : Modifier
		</td>
		<td>
			<img src="../../images/buttonSuppr.png"/> : Supprimer/Refuser
		</td>
	</tr>
</table>
<c:if test="${empty users}">
	Aucun utilisateur trouvé.
	<br/>
	<br/>
</c:if>

	<c:if test="${not empty users}">
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
					<td>${user.nom} ${user.prenom}</td>
					<td>${user.login}</td>
					<td>${user.pass}</td>
					<td>${user.role}</td>
					<td>${user.classe.nom}</td>
					<td><a href="<c:url value="/app/admin/editUser?id=${user.id}" />" ><img src="../../images/buttonModif.png" onmouseover="this.src='../../images/buttonModifHover.png';" onmouseout="this.src='../../images/buttonModif.png';" /></a></td>
					<td><a href=""
						onclick="if(confirm('Voulez-vous vraiment supprimer cet utilisateur ?')){window.location.href='delUser/${user.id}';}"><img src="../../images/buttonSuppr.png" onmouseover="this.src='../../images/buttonSupprHover.png';" onmouseout="this.src='../../images/buttonSuppr.png';" /></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>

	<div class="buttonGroup">
		
		
		<a href="<c:url value="/app/admin/index" />">
		 	<input type="button" value="Retour à l'index">
		</a>
		
		<a href="<c:url value="/app/admin/searchUser" />">
		 	<input type="button" value="Nouvelle recherche">
		</a>
		
		
	</div>

</body>
</html>