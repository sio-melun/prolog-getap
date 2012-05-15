<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout utilisateur</title>
</head>
<body>

	<form:form modelAttribute="formAjoutUser" action="doajout"
		method="post">
		<form:errors path="*" cssClass="errors" />

		<div class="section">
			<fieldset>
				<div class="form-row">
					<label for="id">ID : </label>
					<div class="input">
						<form:input path="id" />
					</div>
					<br>
				</div>
				<div class="form-row">
					<label for="prenom">Prénom : </label>
					<div class="input">
						<form:input path="prenom" />
					</div>
					<br>
				</div>
				<div class="form-row">
					<label for="nom">Nom : </label>
					<div class="input">
						<form:input path="nom" />
					</div>
					<br>
				</div>
				<div class="form-row">
					<label for="classeId">Les classes :</label><br>
					<form:select path="classeId" items="${lesClasses}" itemValue="id"
						itemLabel="nom"></form:select>
				</div>
				<br>
				<div class="form-row">
					<label for="role">Role : </label><br>
					<form:select path="roleNom" items="${lesRoles}" itemValue="nom"
						itemLabel="nom"></form:select>
				</div>
			</fieldset>
			<br>

			<div id="buttonGroup">
				<a href="<c:url value="/app/admin/index" />"
					style="text-decoration: none"><input type="button"
					value="Retour"> 
				</a> 
				<input type="submit" value="Ajouter"/>
			</div>
		</div>
	</form:form>

</body>
</html>