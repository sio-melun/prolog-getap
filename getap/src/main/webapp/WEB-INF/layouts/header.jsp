<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div>
	<br/>
	<% Object user = session.getAttribute("user");  %>
   <div style="text-align: right;">	
	 <c:if test="${not empty user}">
	 	<c:choose>
	 		<c:when test="${user.role == 'eleve' }">
	 			${user.prenom} ${user.nom} (Elève)
	 		</c:when>
	 		<c:when test="${user.role == 'admin' }">
	 			${user.prenom} ${user.nom} (Admin)
	 		</c:when>
	 		<c:otherwise>
	 			${user.prenom} ${user.nom} (Professeur)
	 		</c:otherwise>
	 	</c:choose>
	 </c:if> 	
	</div>
</div>

