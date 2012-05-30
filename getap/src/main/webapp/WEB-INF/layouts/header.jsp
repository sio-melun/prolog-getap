<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<br/>
	<% Object user = session.getAttribute("user");  %>
   <span style="position:relative; left:700px;">	
	 <c:if test="${not empty user}">
	   ${user.prenom} ${user.nom} (${user.role})
	 </c:if> 	
	</span>
</div>

