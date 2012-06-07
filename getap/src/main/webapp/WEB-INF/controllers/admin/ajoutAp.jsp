<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<form:form modelAttribute="formAjoutAp" action="doajoutAP" method="post"
	id="formulaireAjoutAP">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			<div class="form-row">
				<label for="nom">Nom de la nouvelle aide personnalisée :</label>
				<div class="input">
					<form:input path="nom" />
				</div>
			</div>
		</fieldset>

		<div id="buttonGroup">
			<a href="index" style="text-decoration: none"><input
				type="button" value="Retour">
			</a> <input type="submit" value="Ajouter" />

		</div>
	</div>
</form:form>