<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<br/>
	<% Object user = session.getAttribute("user");  %>
   <div style="text-align: right;">	
	 <c:if test="${not empty user}">
	   ${user.prenom} ${user.nom} (${user.role})
	 </c:if> 	
	</div>
</div>

