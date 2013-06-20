<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	
<h3 class="titre3">Création d'une liste<br/>de prevalidation</h3>

<form:form modelAttribute="formAjoutDctap" action="ajouteleves"
	method="post" id="formulaireAjoutPrevalidation" name="ajoutprevalidation">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			<div class="form-row">
				<label for="anneeScolaire">Année scolaire courante :</label>
				<div class="input">
					<form:input path="anneeScolaire" disabled="true" />
				</div>
			</div>

			<div class="form-row">
				<label for="profNom">Professeur intervenant :</label>
				<div class="input">
					<form:input path="profNom" disabled="true" />
				</div>
			</div>

			<div class="form-row">
				<label for="datepicker">Date : </label>
				<div class="input">
					<form:input id="datepicker" path="date" />
				</div>
			</div>

			<div class="form-row">
				<label for="minutes">Durée de l'activité :
				</label>
				<%-- <div class="input">
					<form:input path="minutes" />
				</div> --%>
				<select name="minutes" id="minutes">
				<% for(int i=5; i<=300; i+=5) {
					if( (int) i/60 != 0 ){%>
						<option value="<%=i%>"><%=(int)i/60 %>h <%=(int)i%60 %>min</option>
					<% } else { %>
						<option value="<%=i%>"><%=(int)i%60 %>min</option>
					<% } 
				}%>
				</select>
			</div>

			<div class="form-row">
				<label for="accPersId">Type d'accompagnement personnalisé : </label> <select
					id="accPersId" name="accPersId" onchange="testAcc()">
					<c:forEach items="${lesAP}" var="ap">
						<option value="${ap.id}" label="${ap.nom}">${ap.nom}</option>
					</c:forEach>
					<option value="0" label="Autre">Autre</option>
				</select>
			</div>
		</fieldset>

		<form:hidden path="etat" />

		<div id="buttonGroup">
			<a href="index" style="text-decoration: none"><input
				type="button" value="Retour"> </a>
			<input
				type="submit" method="post" value="Ajouter des élèves">

		</div>
	</div>
</form:form>