<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Administrateur</h1>

<p><a href="<c:url value="/app/admin/ajoutUser" />"style="text-decoration: none">
		 <input type="button" value="Ajouter un utilisateur">
	</a>
</p>
		 

<p><a href="<c:url value="/app/admin/ajoutUsers" />"style="text-decoration: none">
		 <input type="button" value="Ajouter plusieurs utilisateurs">
	</a>
</p>

		 
<p><a href="<c:url value="/app/admin/searchUser" />"style="text-decoration: none">
	<input type="button" value="Rechercher un utilisateur">
	</a>
</p>