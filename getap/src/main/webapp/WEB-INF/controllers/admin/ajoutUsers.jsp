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
	<script type="text/javascript">
		function envoye() {
			document.getElementById("loader").style.display = "block";
			document.getElementById("loader").style.visibility = "visible";
		}
	</script>
	<h3>Importation d'une liste d'utilisateur</h3>
	<form:form commandName="formAjoutUsers" enctype="multipart/form-data"
		action="doajouts" method="post">
		<form:errors path="*" cssClass="errors" />

		<div class="section">
			<fieldset>
				<p>
					Ligne type : Nom;Prénom;INE;Division<br>
					<br> ABADE;Mathilde;2404005482M;1CTA1<br>
					ABDALLAH;Florence;2406025996Y;1SCT<br>
				</p>
				<div class="form-row">
					<label for="file">Fichier : </label>
					<div class="input">
						<form:input type="file" path="file" size="75" />
					</div>
					<br>
				</div>
			</fieldset>
			<br>
			<div id="loader">
				<img src="../../images/ajax-loader.gif" alt="chargement"
					width="350px" height="20px" />
			</div>
			<div id="buttonGroup">
				<a href="<c:url value="/app/admin/index" />"><input
					type="button" value="Retour"> </a> <input type="submit"
					value="Ajouter" onclick="envoye()" />
			</div>
		</div>
	</form:form>

</body>
</html>