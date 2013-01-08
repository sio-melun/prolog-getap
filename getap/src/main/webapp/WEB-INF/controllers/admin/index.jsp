<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						src="<c:url value="../../images/AjouterUser.png"/>" width="64"
						height="68" />
						<div>Ajouter un Utilisateur</div> </a></td>
				<td><a href="<c:url value="/app/admin/ajoutUsers" />"><img
						src="<c:url value="../../images/importcsv.png"/>" width="64"
						height="64" />
						<div>Import CSV d'élèves</div> </a></td>
				<td><a href="<c:url value="/app/admin/exportUserCsv" />"><img
						src="<c:url value="../../images/exportcsv.png"/>" width="64"
						height="64" />
						<div>Export CSV des élèves</div> </a></td>
				<td><a href="<c:url value="/app/admin/exportUserPdf" />"><img
						src="<c:url value="../../images/pdfdl.png"/>" width="64"
						height="64" />
						<div>Export PDF des élèves</div> </a></td>
				<td><a href="<c:url value="/app/admin/exportProfPdf" />"><img
						src="<c:url value="../../images/pdfdl.png"/>" width="64"
						height="64" />
						<div>Export PDF des prof</div> </a></td>
						
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Gestion des utilisateurs</a>
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
	<h3>
		<a href="#">Liste de tous les élèves</a>
	</h3>
	<div>
		<table>
			<tr>
				<table class="display dataTable">
					<thead>
						<tr class="header">
							<th>Detail</th>
							<th>Nom/Prenom</th>
							<th>Classe</th>
							<th>Temps consommé</th>
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
								<td class="${classPercentZero}"><fmt:formatNumber
										value="${(eleve.dureeTotal/60)-((eleve.dureeTotal%60)/60)}"
										pattern="#00" />h<fmt:formatNumber
										value="${eleve.dureeTotal%60}" pattern="#00" /> (<fmt:formatNumber
										value="${(eleve.dureeTotal/(72*60)*100)}" pattern="#00.00" />%)
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</tr>
		</table>
	</div>
	<h3>
		<a href="#">Liste de tous les professeurs</a>
	</h3>
	<div>
		<table>
			<tr>
				<table class="display dataTable">
					<thead>
						<tr class="header">
							<th>Detail</th>
							<th>Nom/Prenom</th>
							<th>Professeur</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lesProfs}" var="prof">
							<tr>
								<td><a
									href="<c:url value="/app/admin/detailUser?id=${prof.id}" />"><img
										src="../../images/detail.png"
										onmouseover="this.src='../../images/detailHover.png';"
										onmouseout="this.src='../../images/detail.png';" /> </a></td>
								<td style="text-align: left;"><a
									href="<c:url value="/app/admin/detailUser?id=${prof.id}" />">${prof.nom}
										${prof.prenom}</a>
								</td>
								<td><c:if test="${prof.role == 'prof-principal'}">Principal</c:if>
									<c:if test="${prof.role == 'prof-intervenant'}">Intervenant</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</tr>
		</table>
	</div>
</div>
