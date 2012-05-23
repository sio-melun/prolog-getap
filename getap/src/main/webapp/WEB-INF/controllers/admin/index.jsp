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
						<div>Ajouter un Utilisateur</div> </a>
				</td>
				<td><a href="<c:url value="/app/admin/ajoutUsers" />"><img
						src="<c:url value="../../images/AjouterUser.png"/>" />
						<div>Import Utilisateur via CSV</div> </a>
				</td>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Gestion de recherche</a>
	</h3>
	<div>
		<table>
			<tr>
				<td><a href="<c:url value="/app/admin/searchUser" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Utilisateur</div> </a>
				</td>
				<td><a href="<c:url value="/app/admin/searchProf" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Professeur</div> </a>
				</td>
				<td><a href="<c:url value="/app/admin/searchClasse" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Recherche par classe</div> </a>
				</td>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Gestion des demandes faites par les eleves</a>
	</h3>
	<div>
		<table>
			<tr>
				<td><a href="<c:url value="/app/admin/searchDctapUser" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Utilisateur</div> </a>
				</td>
				<td><a href="<c:url value="/app/admin/searchDctapProf" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Rechercher un Professeur</div> </a>
				</td>
				<td><a href="<c:url value="/app/admin/searchDctapClasse" />"><img
						class="searchUser"
						src="<c:url value="../../images/RechercherUser.png"/>" />
						<div>Recherche par classe</div> </a>
				</td>
			</tr>
		</table>
	</div>
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
			<tr>
				<table class="legend">
					<tr>
						<td><img src="../../images/buttonModif.png" /> : Modifier</td>
						<td><img src="../../images/buttonSuppr.png" /> : Supprimer</td>
					</tr>
				</table>
			</tr>
			<tr>
				<td>
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
											src="../../images/buttonModifHover.png"
											onmouseover="this.src='../../images/buttonModif.png';"
											onmouseout="this.src='../../images/buttonModifHover.png';" />
									</a>
									</td>
									<td><a href=""
										onclick="if(confirm('Voulez-vous vraiment supprimer cette aide personnalisée ?')){window.location.href='deleteAP/${ap.id}';}"><img
											src="../../images/buttonSupprHover.png"
											onmouseover="this.src='../../images/buttonSuppr.png';"
											onmouseout="this.src='../../images/buttonSupprHover.png';" />
									</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table></td>
			</tr>
		</table>
	</div>
</div>