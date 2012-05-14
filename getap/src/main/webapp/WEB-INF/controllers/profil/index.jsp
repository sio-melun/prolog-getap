<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<br/>
	<h3>Profil</h3>
<br/>

<a href="<c:url value="/app/profil/edit?id=${user.id}" />"><input type="button" value="Editer mon profil"/></a>