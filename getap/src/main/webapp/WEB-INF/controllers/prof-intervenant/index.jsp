<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	var checkflag = "false";
	function check(field) {
		if (checkflag == "false") {
			for (i = 0; i < field.length; i++) {
				field[i].checked = true;
			}
			checkflag = "true";
			return "Tout décocher";
		} else {
			for (i = 0; i < field.length; i++) {
				field[i].checked = false;
			}
			checkflag = "false";
			return "Tout cocher";
		}
	}
</script>

<h3 class="titre3">Mes demandes de validations</h3>

<c:if test="${empty listdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty listdctaps}">
	<table class="legend2">
		<tr>
			<td><img src="../../images/valid.png" width="24" height="24" />
				: Valider</td>
			<td><img src="../../images/modifValid.png" width="22"
				height="22" /> : Modifier</td>
			<td><img src="../../images/suppr.png" width="24" height="24" />
				: Refuser</td>
		</tr>
	</table>
	<h5 style="position: relative; top: 35px;">Demandes de validation
		en cours</h5>
	<div id="accordion">
		<h3>
			<a href="#">Demandes reçues (${etat0 + etat4})</a>
		</h3>
		<div id="demo">
			<form:form modelAttribute="formListIdDctap" action="sendId"
				method="post">
				<form:errors path="*" cssClass="errors" />
				<table class="display dataTable">
					<thead>
						<tr class="header">
							<th>Élève</th>
							<th>Classe</th>
							<th>Date</th>
							<th>Temps</th>
							<th>Type d'aide</th>
							<th></th>
							<th></th>
							<th></th>
							<th><input name="tout" type="checkbox" onClick="this.value=check(this.form);"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listdctaps}" var="dctap">
							<c:if test="${dctap.etat == 0 or dctap.etat == 4}">
								<tr>
									<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
									<td>${dctap.eleve.classe.nom}</td>
									<td>${dctap.dateAction}</td>
									<td><fmt:formatNumber
											value="${(dctap.minutes/60)-((dctap.minutes%60)/60)}"
											pattern="#00" />h<fmt:formatNumber
											value="${dctap.minutes%60}" pattern="#00" /></td>
									<td>${dctap.accPers.nom}</td>
									<td><a
										href="<c:url value="/app/prof-intervenant/valid/${dctap.id}" />"><img
											src="../../images/valid.png" width="24" height="24"
											onmouseover="this.src='../../images/validHover.png';"
											onmouseout="this.src='../../images/valid.png';" /> </a></td>
									<td><a
										href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />"><img
											src="../../images/modifValid.png" width="22" height="22"
											onmouseover="this.src='../../images/modifValidHover.png';"
											onmouseout="this.src='../../images/modifValid.png';" /> </a></td>
									<td><a href=""
										onclick="if(confirm('Voulez-vous vraiment refuser cette demande ?')){window.location.href='refuse/${dctap.id}';}"><img
											src="../../images/suppr.png" width="24" height="24"
											onmouseover="this.src='../../images/supprHover.png';"
											onmouseout="this.src='../../images/suppr.png';" /> </a></td>
									<td><input type="checkbox" name="ids" value="${dctap.id}" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<div style="text-align: right;">
					<input type="submit" name="send" value="Valider"> <input
						type="submit" name="send" value="Refuser">
				</div>
			</form:form>
		</div>
		<h3>
			<a href="#">Demandes en attentes de confirmations par l'élève
				(${etatsup1000})</a>
		</h3>
		<div id="demo">
			<form:form modelAttribute="formListIdDctap" action="sendId"
				method="post">
				<form:errors path="*" cssClass="errors" />
				<table class="display dataTable">
					<thead>
						<tr class="header">
							<th>Élèves</th>
							<th>Date</th>
							<th>Temps</th>
							<th>Type d'aide</th>
							<th></th>
							<th></th>
							<th><input name="tout" type="checkbox" onClick="this.value=check(this.form);"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listdctaps}" var="dctap">
							<c:if test="${dctap.etat>1023}">
								<tr>
									<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
									<c:if test="${dctap.dateModifiee}">
										<td class="isUpdate">${dctap.dateAction}</td>
									</c:if>
									<c:if test="${!dctap.dateModifiee}">
										<td>${dctap.dateAction}</td>
									</c:if>
									<c:if test="${dctap.dureeModifiee}">
										<td class="isUpdate"><fmt:formatNumber
												value="${(dctap.minutes/60)-((dctap.minutes%60)/60)}"
												pattern="#00" />h<fmt:formatNumber
												value="${dctap.minutes%60}" pattern="#00" /></td>
									</c:if>
									<c:if test="${!dctap.dureeModifiee}">
										<td><fmt:formatNumber
												value="${(dctap.minutes/60)-((dctap.minutes%60)/60)}"
												pattern="#00" />h<fmt:formatNumber
												value="${dctap.minutes%60}" pattern="#00" /></td>
									</c:if>
									<c:if test="${dctap.apModifiee}">
										<td class="isUpdate">${dctap.accPers.nom}</td>
									</c:if>
									<c:if test="${!dctap.apModifiee}">
										<td>${dctap.accPers.nom}</td>
									</c:if>
									<td><a
										href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />"><img
											src="../../images/modifValid.png" width="22" height="22"
											onmouseover="this.src='../../images/modifValidHover.png';"
											onmouseout="this.src='../../images/modifValid.png';" /> </a></td>
									<td><a href=""
										onclick="if(confirm('Voulez-vous vraiment refuser cette demande ?')){window.location.href='refuse/${dctap.id}';}"><img
											src="../../images/suppr.png" width="24" height="24"
											onmouseover="this.src='../../images/supprHover.png';"
											onmouseout="this.src='../../images/suppr.png';" /> </a></td>
									<td><input type="checkbox" name="ids" value="${dctap.id}" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<div style="text-align: right;">
					<input type="submit" name="send" value="Refuser">
				</div>
			</form:form>
		</div>
	</div>
	<h5>Demandes de validation terminées</h5>
	<div id="accordion2">
		<h3>
			<a href="#">Demandes validées (${etat1 + etat32})</a>
		</h3>
		<div id="demo">
			<table class="display dataTable">
				<thead>
					<tr class="header">
						<th>Élèves</th>
						<th>Date</th>
						<th>Temps</th>
						<th>Type d'aide</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listdctaps}" var="dctap">
						<c:if test="${dctap.etat == 1 or dctap.etat == 32}">
							<tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td><fmt:formatNumber
										value="${(dctap.minutes/60)-((dctap.minutes%60)/60)}"
										pattern="#00" />h<fmt:formatNumber
										value="${dctap.minutes%60}" pattern="#00" /></td>
								<td>${dctap.accPers.nom}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3>
			<a href="#">Demandes refusées par l'élève après modification de
				votre part (${etat2})</a>
		</h3>
		<div id="demo">
			<table class="display dataTable">
				<thead>
					<tr class="header">
						<th>Élèves</th>
						<th>Date</th>
						<th>Temps</th>
						<th>Type d'aide</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listdctaps}" var="dctap">
						<c:if test="${dctap.etat == 2}">
							<tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td><fmt:formatNumber
										value="${(dctap.minutes/60)-((dctap.minutes%60)/60)}"
										pattern="#00" />h<fmt:formatNumber
										value="${dctap.minutes%60}" pattern="#00" /></td>
								<td>${dctap.accPers.nom}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3>
			<a href="#">Vos demandes refusées (${etat64})</a>
		</h3>
		<div id="demo">
			<table class="display dataTable">
				<thead>
					<tr class="header">
						<th>Élèves</th>
						<th>Date</th>
						<th>Temps</th>
						<th>Type d'aide</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listdctaps}" var="dctap">
						<c:if test="${dctap.etat == 64}">
							<tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td><fmt:formatNumber
										value="${(dctap.minutes/60)-((dctap.minutes%60)/60)}"
										pattern="#00" />h<fmt:formatNumber
										value="${dctap.minutes%60}" pattern="#00" /></td>
								<td>${dctap.accPers.nom}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:if>