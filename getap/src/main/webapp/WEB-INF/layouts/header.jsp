<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	Bienvenue sur GeTAP
	<% Object user = session.getAttribute("user");  %>
   <div style="text-align: right;">	
	 <c:if test="${not empty user}">
	   ${user.prenom} ${user.nom} (${user.role})
	   	<a href="<c:url value="/app/login/logout" />">
		 Déconnexion
	    </a>
	 </c:if> 	
	</div>
</div>


