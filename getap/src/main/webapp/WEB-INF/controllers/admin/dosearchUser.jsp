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

<h1>Resultat</h1>
<c:if test="${empty users}">
	No users found
</c:if>

	<c:if test="${not empty users}">
		<ul>
			<c:forEach items="${users}" var="user">
				<li><%-- <a href="<c:url value="/app/admin/details?id=${user.id}" />"> --%> 
					<c:out value="${user.nom} (${user.prenom}, ${user.role})" />
					<%-- </a>--%>
				</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="buttonGroup">
		<a href="<c:url value="/app/admin/searchUser" />"> New Search </a>
	</div>

</body>
</html>