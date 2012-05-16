<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout utilisateurs</title>
</head>
<body>
	<h3>Importation d'une liste d'utilisateur</h3>
	<form:form modelAttribute="formAjoutUsers" action="doajouts"
		method="post">
		<form:errors path="*" cssClass="errors" />

		<div class="section">
			<fieldset>
				<div class="form-row">
					<label for="nom">Fichier : </label>
					<div class="input">
						<form:input properties="file" path="nom" size="75"/>
					</div>
					<br>
				</div>
			</fieldset>
			<br>
				
			<div id="buttonGroup">
				<a href="<c:url value="/app/admin/index" />"
					style="text-decoration: none"><input type="button"
					value="Retour"> 
				</a> 
				<input type="submit" value="Ajouter" />
			</div>
		</div>
	</form:form>

</body>
</html>