<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- <a href="javascript:if(windows.alert('Utilisateur ajouté.')){window.location.href='admin/index.jsp';}"></a> -->
<script>alert('L\'utilisateur a bien été enregistré\n\nLogin : ${userAjoute.login} \n\nMot de passe :${userAjoute.pass}')</script>
<script>
	/* vous pouvez aussi mettre http://www.monsite.com */
	document.location.href="index" 
</script>