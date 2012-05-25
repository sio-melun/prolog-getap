<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />

<h5 style="position: relative; top: 35px;">Administration
	logistique</h5>
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
		<a href="#">Gestion de recherche d'utilisateurs</a>
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
									<input type="image" src="../../images/search.png"
										alt="Rechercher"
										onmouseover="this.src='../../images/searchHover.png';"
										onmouseout="this.src='../../images/search.png';" />
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
									<input type="image" src="../../images/search.png"
										alt="Rechercher"
										onmouseover="this.src='../../images/searchHover.png';"
										onmouseout="this.src='../../images/search.png';" />
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
									<input type="image" src="../../images/search.png"
										alt="Rechercher"
										onmouseover="this.src='../../images/searchHover.png';"
										onmouseout="this.src='../../images/search.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Recherche des demandes</a>
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
								<label for="query">Recherche par élève :</label>
								<div class="input">
									<form:input path="query" />
									<input type="image" src="../../images/search.png"
										alt="Rechercher"
										onmouseover="this.src='../../images/searchHover.png';"
										onmouseout="this.src='../../images/search.png';" />
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
									<input type="image" src="../../images/search.png"
										alt="Rechercher"
										onmouseover="this.src='../../images/searchHover.png';"
										onmouseout="this.src='../../images/search.png';" />
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
									<input type="image" src="../../images/search.png"
										alt="Rechercher"
										onmouseover="this.src='../../images/searchHover.png';"
										onmouseout="this.src='../../images/search.png';" />
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</tr>
		</table>
	</div>
</div>
<h5>Administration logiciel</h5>
<div id="accordion2">
	<h3>
		<a href="#">Gestion des aides personnalisées</a>
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
								<label for="nom">Nouvelle aide personnalisée :</label>
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
					<td><img src="../../images/modif.png" /> : Modifier</td>
					<td><img src="../../images/suppr.png" /> : Supprimer</td>
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
									<td>Elève</td>
								</c:if>
								<td><a
									href="<c:url value="/app/admin/editAp?id=${ap.id}" />"><img
										src="../../images/modif.png"
										onmouseover="this.src='../../images/modifHover.png';"
										onmouseout="this.src='../../images/modif.png';" /> </a></td>
								<td><a 
									href="<c:url value="/app/admin/deleteAP/${ap.id}"/>"><img
										src="../../images/suppr.png"
										onmouseover="this.src='../../images/supprHover.png';"
										onmouseout="this.src='../../images/suppr.png';" /> </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Gestion des disciplines</a>
	</h3>
	<div>
		<form:form modelAttribute="formAjoutDiscipline"
			action="doajoutDiscipline" method="post">
			<form:errors path="*" cssClass="errors" />

			<div class="section">
				<fieldset>
					<div class="form-row">
						<label for="nom">Nouvelle Discipline :</label>
						<div class="input">
							<form:input path="nom" /> 
							<input type="submit" value="Ajouter" />
						</div>
					</div>
				</fieldset>
			</div>
		</form:form>
		<table class="legend2">
			<tr>
				<td><img src="../../images/modif.png" /> : Modifier</td>
				<td><img src="../../images/suppr.png" /> : Supprimer</td>
			</tr>
		</table>
		<tr>
			<table id="2" class="tablesorter">
				<thead>
					<tr class="header">
						<th>Nom</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lesDisciplines}" var="dis">
						<tr>
							<td>${dis.nom}</td>
							<td><a
								href="<c:url value="/app/admin/editDiscipline?id=${dis.id}" />"><img
									src="../../images/modif.png"
									onmouseover="this.src='../../images/modifHover.png';"
									onmouseout="this.src='../../images/modif.png';" /> </a></td>
							<td><a
								href="<c:url value="/app/admin/deleteDiscipline/${dis.id}"/>"><img
									src="../../images/suppr.png"
									onmouseover="this.src='../../images/supprHover.png';"
									onmouseout="this.src='../../images/suppr.png';" /> </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</tr>
	</div>
</div>