<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<form:form modelAttribute="formAjoutAp" action="doEditAP" method="post"
	id="formulaireEditAP">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			<div class="form-row">
				<label for="nom">Nom de l'aide personnalisée :</label>
				<div class="input">
					<form:input path="nom" />
				</div>
			</div>
		</fieldset>
		<form:hidden path="id" />
		<div id="buttonGroup">
			<a href="index"><input
				type="button" value="Retour">
			</a> <input type="submit" value="Modifier" />

		</div>
	</div>
</form:form>