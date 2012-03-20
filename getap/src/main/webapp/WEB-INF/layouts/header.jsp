<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="welcome">
	Bienvenue sur l'application GeTAP 
	<% Object user = session.getAttribute("user");  %>
   <div class="right">	
	 <c:if test="${not empty user}">
	   ${user.prenom}
	   ${user.nom}
	    (${user.role}) : 
	   	<a style="color: white;" href="<c:url value="/app/login/logout" />">
		 Déconnexion
	    </a>
	 </c:if> 	
	</div>
</div>
<div id="branding" class="spring">
	<img src="<c:url value="/images/header2.jpg"/>" />
</div>
