<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	
<h3 class="titre3">Récapitulatif</h3>

<form:form modelAttribute="formAjoutDctap" action="ajouteleves"
	method="post" id="formulaireAjoutPrevalidation" name="ajoutprevalidation">
	
	<div class="section">
		<fieldset>

			<div class="form-row">
				<label for="anneeScolaire">Année scolaire courante :</label>
				<div class="input">
					<form:input path="anneeScolaire" disabled="true" />
				</div>
			</div>
			
			<div class="form-row">
				<label for="datepicker">Date : </label>
				<div class="input">
					<form:input id="datepicker" path="date" disabled="true" />
				</div>
			</div>

			<div class="form-row">
				<label for="profId">Professeur intervenant :</label>
				<form:select disabled="true" path="profId" items="${lesProfs}" itemValue="id"
					itemLabel="nom"></form:select>
			</div>
			
			<div class="form-row">
				<label for="AccPersId">Type d'accompagnement personnalisé :</label>
				<form:select disabled="true" path="AccPersId" items="${lesAP}" itemValue="id"
					itemLabel="nom"></form:select>
			</div>
			
		</fieldset>
	</div>
			
</form:form>
	
<h3 class="titre3">Selection des<br/>élèves</h3>

<table class="display dataTable">
					<thead>
						<tr class="header">
							<th>Detail</th>
							<th>Nom/Prenom</th>
							<th>Classe</th>
							<th>Ajouter</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lesEleves}" var="eleve">
							<c:set value="" var="classPercentZero" />
							<c:if test="${eleve.dureeTotal == 0 }">
								<c:set value="percentZero" var="classPercentZero" />
							</c:if>
							<tr>
								<td class="${classPercentZero}"><a
									href="<c:url value="/app/admin/detailUser?id=${eleve.id}" />"><img
										src="../../images/detail.png"
										onmouseover="this.src='../../images/detailHover.png';"
										onmouseout="this.src='../../images/detail.png';" /> </a></td>
								<td class="${classPercentZero}" style="text-align:left;"><a href="<c:url value="/app/admin/detailUser?id=${eleve.id}" />">${eleve.nom}
									${eleve.prenom}</a></td>
								<td class="${classPercentZero}">${eleve.classe.nom}</td>
								<td class="${classPercentZero}">Ajouter
								</td>
							</tr>
						</c:forEach>
					</tbody>
</table>