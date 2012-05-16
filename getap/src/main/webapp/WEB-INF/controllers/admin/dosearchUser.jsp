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

<h1>Résultat</h1>
<c:if test="${empty users}">
	Aucun utilisateur trouvé.
	<br/>
	<br/>
</c:if>

	<c:if test="${not empty users}">
		<table>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.nom} (${user.prenom}, ${user.role})" /></td>
					<td><a href="<c:url value="/app/admin/editUser?id=${user.id}" />" ><input type="button" value="Editer"></a></td>
					<td><a href=""
						onclick="if(confirm('Voulez-vous vraiment supprimer cet utilisateur ?')){window.location.href='delUser/${user.id}';}"><input type="button" value="Supprimer"></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<div class="buttonGroup">
		
		
		<a href="<c:url value="/app/admin/index" />"style="text-decoration: none">
		 	<input type="button" value="Retour à l'index">
		</a>
		
		<a href="<c:url value="/app/admin/searchUser" />"style="text-decoration: none">
		 	<input type="button" value="Nouvelle recherche">
		</a>
		
		
	</div>

</body>
</html>