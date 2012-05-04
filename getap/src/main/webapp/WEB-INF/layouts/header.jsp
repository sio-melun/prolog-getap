<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<br/>
	Bienvenue sur GeTAP
	<% Object user = session.getAttribute("user");  %>
   <div style="text-align: right;">	
	 <c:if test="${not empty user}">
	   ${user.prenom} ${user.nom} (${user.role})
	   	<%-- <a href="<c:url value="/app/login/logout" />"style="text-decoration: none">
		 <input type="button" value="Déconnexion">
	    </a> --%>
	 </c:if> 	
	</div>
</div>

