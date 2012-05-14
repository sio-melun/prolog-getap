<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout d'une DCTAP</title>
</head>
<body>

	<h1>Ajouter une demande</h1>

	<form:form modelAttribute="formAjoutDctap" action="doajout"
		method="post">
		<form:errors path="*" cssClass="errors" />

		<div class="section">
			<fieldset>
				<div class="form-row">
					<div class="input">
						<form:label path="anneeScolaire">Année scolaire courante : </form:label>
						<br>
						<form:input path="anneeScolaire" disabled="true" />
					</div>
				</div>
				
				<br/>                                             
				
				
				<div class="form-row">
					<label for="date">Date (AAAA-MM-JJ) : </label>
					<div class="input">
						<form:input path="date" />
					</div>
				</div>
				
				<br/>
				
				<div class="form-row">
					<label for="minutes">Temps d'aide personnalisée (minutes) : </label>
					<div class="input">
						<form:input path="minutes" />
					</div>
				</div>
				
				<br/>
				
			
				<div class="form-row">
					<label for="profs">Les professeurs :</label><br>
					<form:select path="profId" items="${lesProfs}" itemValue="id"
						itemLabel="nom"></form:select>
				</div>
				
				<br/>
				
				<div class="form-row">
					<label for="accPers">Type d'aide personnalisée : </label><br>
					<form:select path="accPersId" items="${lesAP}" itemValue="id"
						itemLabel="nom"></form:select>
				</div>
			</fieldset>

			<form:hidden path="eleveId" />
			<form:hidden path="etat" />
			
			<br/>
			
			<div id="buttonGroup">
				<a href="index" style="text-decoration:none"><input type="button" value="Retour"></a>
				<input type="submit" value="Ajouter" />
				
			</div>
		</div>
	</form:form>

</body>
</html>