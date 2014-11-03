<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<label for="nomProf">Professeur intervenant :</label>
				<form:input path="profNom" disabled="true" />
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
     <select name="minutes" id="minutes">
                  <%-- end="10*6*4" --%>
          <c:forEach var="i" begin="0" end="240" step="10">
             <option value="${i}" <c:if test="${i == minute}"> selected </c:if> >
             <c:choose>
               <c:when test="${(i%60) == 0}">
                <fmt:parseNumber var="mn" integerOnly="true" 
                  type="number" value="${i/60}" />
                 0${mn} h 00 minute
              </c:when>
              <c:otherwise>
                <fmt:parseNumber var="hh" integerOnly="true" 
                   type="number" value="${i/60}" />
                <fmt:parseNumber var="mn" integerOnly="true" 
                   type="number" value="${i%60}" />
                 0${(hh)} h ${mn}  minutes
            </c:otherwise>
            </c:choose>
          </option>
          </c:forEach>
      </select>
			</div>

			<div class="form-row">
				<label for="accPersId">Type d'accompagnement personnalisé : </label> 
				<select path = "accPersId"
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