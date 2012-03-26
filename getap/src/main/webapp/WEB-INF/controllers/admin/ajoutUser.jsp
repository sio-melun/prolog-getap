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
	<h1>Ajout d'un utilisateur</h1>

	<form:form modelAttribute="formAjoutUser"
		action="doajout" method="post">
		<form:errors path="*" cssClass="errors" />

		<div class="section">
			<fieldset>
				<div class="form-row">
					<label for="id">ID : </label>
					<div class="input">
						<form:input path="id" />
					</div>
				</div>
				<div class="form-row">
					<label for="prenom">Prénom : </label>
					<div class="input">
						<form:input path="prenom" />
					</div>
				</div>
				<div class="form-row">
					<label for="nom">Nom : </label>
					<div class="input">
						<form:input path="nom" />
					</div>
				</div>
				<div class="form-row">
					<label for="classe">Classe : </label>
					<div class="input">
						<form:input path="classe" />
					</div>
				</div>
				<div class="form-row">
					<label for="role">Role : </label>
					<div class="input">
						<form:input path="role" />
					</div>
				</div>
			</fieldset>


			<form:hidden path="id" />


			<div id="buttonGroup">
				<input type="submit" value="Ajouter" />
			</div>
		</div>
	</form:form>

</body>
</html>