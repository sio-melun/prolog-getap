<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h3 class="titre3">Entrer votre E-mail</h3>
<p class="error">${error}</p><br>
<i>Pour accéder à GeTAP, il est essentiel de renseigner votre E-mail valide. Il sera utilisé pour vous envoyer les comptes rendus et les alertes des demandes vous concernant. Vous pourrez modifier cet E-mail à tout moment dans la gestion de votre compte.</i>
<br><br><br>
<center>
	<form action="mail" method="post">
		<input type="text" name="mail" placeholder="Entrez votre E-mail"> <input type="submit" value="Valider">
	</form>
</center>