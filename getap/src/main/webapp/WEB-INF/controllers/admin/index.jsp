<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />
<br />
<div id="accordion">
	<h3>
		<a href="#">Gestion d'utilisateur</a>
	</h3>
	<div>
		<table>
			<tr>
				<td><a href="<c:url value="/app/admin/ajoutUser" />"><img
						src="<c:url value="../../images/AjouterUser.png"/>" />
						<div>Ajouter un Utilisateur</div> </a></td>
				<td><a href="<c:url value="/app/admin/ajoutUsers" />"><img
						src="<c:url value="../../images/AjouterUser.png"/>" />
						<div>Import Utilisateur via CSV</div> </a></td>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Gestion de recherche</a>
	</h3>
	<div>
		<table>
			<tr>
				<form:form modelAttribute="userSearchCriteria" action="dosearchUser"
					method="get">
					<form:errors path="*" cssClass="errors" />
					<div class="section">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche d'utilisateurs :</label>
								<div class="input">
									<form:input path="query" />
									<input type="image" src="../../images/buttonSearch.png" alt="Rechercher" 
										onmouseover="this.src='../../images/buttonSearchHover.png';"
										onmouseout="this.src='../../images/buttonSearch.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
			<tr>
				<form:form modelAttribute="userSearchCriteria" action="dosearchProf"
					method="get">
					<form:errors path="*" cssClass="errors" />
					<div class="section">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche de professeurs :</label>
								<div class="input">
									<form:input path="query" />
									<input type="image" src="../../images/buttonSearch.png" alt="Rechercher" 
										onmouseover="this.src='../../images/buttonSearchHover.png';"
										onmouseout="this.src='../../images/buttonSearch.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
			<tr>
				<form:form modelAttribute="userSearchCriteria"
					action="dosearchForClasse" method="get">
					<form:errors path="*" cssClass="errors" />
					<div class="section">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche par classe :</label>
								<div>
									<form:select path="query" items="${lesClasses}" itemValue="nom"
										itemLabel="nom"></form:select>
									<input type="image" src="../../images/buttonSearch.png" alt="Rechercher" 
										onmouseover="this.src='../../images/buttonSearchHover.png';"
										onmouseout="this.src='../../images/buttonSearch.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Gestion des demandes faites par les �l�ves</a>
	</h3>
	<div>
		<table>
			<tr>
				<form:form modelAttribute="userSearchCriteria"
					action="doSearchDctap" method="get">
					<form:errors path="*" cssClass="errors" />
					<div class="section">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche par �l�ve :</label>
								<div class="input">
									<form:input path="query" />
									<input type="image" src="../../images/buttonSearch.png" alt="Rechercher" 
										onmouseover="this.src='../../images/buttonSearchHover.png';"
										onmouseout="this.src='../../images/buttonSearch.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
			<tr>
				<form:form modelAttribute="userSearchCriteria"
					action="doSearchDctap" method="get">
					<form:errors path="*" cssClass="errors" />
					<div class="section">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche par professeur :</label>
								<div class="input">
									<form:input path="query" />
									<input type="image" src="../../images/buttonSearch.png" alt="Rechercher" 
										onmouseover="this.src='../../images/buttonSearchHover.png';"
										onmouseout="this.src='../../images/buttonSearch.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
			<tr>
				<form:form modelAttribute="userSearchCriteria"
					action="doSearchDctapClasse" method="get">
					<form:errors path="*" cssClass="errors" />
					<div class="section">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche par classe :</label>
								<div>
									<form:select path="query" items="${lesClasses}" itemValue="nom"
										itemLabel="nom"></form:select>
									<input type="image" src="../../images/buttonSearch.png" alt="Rechercher" 
										onmouseover="this.src='../../images/buttonSearchHover.png';"
										onmouseout="this.src='../../images/buttonSearch.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Gestion des aides personnalis�es</a>
	</h3>
	<div>
		<table>
			<tr>
				<form:form modelAttribute="formAjoutAp" action="doajoutAP"
					method="post" id="formulaireAjoutAP">
					<form:errors path="*" cssClass="errors" />

					<div class="section">
						<fieldset>
							<div class="form-row">
								<label for="nom">Nouvelle aide personnalis�e :</label>
								<div class="input">
									<form:input path="nom" />
									<input type="submit" value="Ajouter" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
			<table class="legend2">
				<tr>
					<td><img src="../../images/buttonModifHover.png" /> :
						Modifier</td>
					<td><img src="../../images/buttonSupprHover.png" /> :
						Supprimer</td>
				</tr>
			</table>
			<tr>
				<table id="attente" class="tablesorter">
					<thead>
						<tr class="header">
							<th>Nom</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lesAP}" var="ap">
							<tr>
								<td>${ap.nom}</td>
								<c:if test="${ap.origineEtat == 1}">
									<td>El�ve</td>
								</c:if>
								<td><a
									href="<c:url value="/app/admin/editAp?id=${ap.id}" />"><img
										src="../../images/buttonModifHover.png"
										onmouseover="this.src='../../images/buttonModif2.png';"
										onmouseout="this.src='../../images/buttonModifHover.png';" />
								</a></td>
								<td><a href=""
									onclick="if(confirm('Voulez-vous vraiment supprimer cette aide personnalis�e ?')){window.location.href='deleteAP/${ap.id}';}"><img
										src="../../images/buttonSupprHover.png"
										onmouseover="this.src='../../images/buttonSupp.png';"
										onmouseout="this.src='../../images/buttonSupprHover.png';" />
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</tr>
		</table>
	</div>
</div>