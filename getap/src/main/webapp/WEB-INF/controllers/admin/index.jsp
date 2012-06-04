<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />

<h5 style="position: relative; top: 35px;">Gestion utilisateur</h5>
<div id="accordion">
	<h3>
		<a href="#">Import/Export d'utilisateurs</a>
	</h3>
	<div>
		<table>
			<tr>
				<td><a href="<c:url value="/app/admin/ajoutUser" />"><img
						src="<c:url value="../../images/AjouterUser.png"/>" width="64" height="68"/>
						<div>Ajouter un Utilisateur</div> </a>
				</td>
				<td><a href="<c:url value="/app/admin/ajoutUsers" />"><img
						src="<c:url value="../../images/importcsv.png"/>" width="64" height="64"/>
						<div>Import CSV d'élèves</div> </a>
				</td>
				<td><a href="<c:url value="/app/admin/exportUserCsv" />"><img
						src="<c:url value="../../images/exportcsv.png"/>"  width="64" height="64"/>
						<div>Export CSV d'élèves</div> </a>
				</td>
					<td><a href="<c:url value="/app/admin/exportUserPdf" />"><img
						src="<c:url value="../../images/pdfdl.png"/>"  width="64" height="64"/>
						<div>Export PDF d'utilisateurs</div> </a>
				</td>
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
					<div class="section inputPerso">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche d'élèves :</label>
								<div class="input">
									<form:input path="query" />
									<input type="image" src="../../images/search.png"
										alt="Rechercher" width="24" height="24"
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
					<div class="section inputPerso">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche de professeurs :</label>
								<div class="input">
									<form:input path="query" />
									<input type="image" src="../../images/search.png"
										alt="Rechercher" width="24" height="24"
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
					<div class="section inputPerso">
						<fieldset>
							<div class="form-row">
								<label for="query">Recherche par classe :</label>
								<div>
									<form:select path="query" items="${lesClasses}" itemValue="nom"
										itemLabel="nom"></form:select>
									<input type="image" src="../../images/search.png"
										alt="Rechercher" width="24" height="24"
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