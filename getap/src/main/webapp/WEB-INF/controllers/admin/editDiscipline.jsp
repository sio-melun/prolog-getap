<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<h3 class="titre3">Edition d'une discipline</h3>
<form:form modelAttribute="formAjoutDiscipline" action="doEditDiscipline" method="post"
	id="formulaireEditDiscipline">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			<div class="form-row">
				<label for="nom">Nom de la Discipline :</label>
				<div class="input">
					<form:input path="nom" size="50"/>
				</div>
			</div>
		</fieldset>
		<form:hidden path="id" />
		<div id="buttonGroup">
			<a href="logiciel"><input
				type="button" value="Retour">
			</a> <input type="submit" value="Modifier" />

		</div>
	</div>
</form:form>