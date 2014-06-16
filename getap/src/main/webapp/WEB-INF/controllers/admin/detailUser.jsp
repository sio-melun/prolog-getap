<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:set var="timeTotEffec" value="0" />
<c:set var="timeVal" value="0" />
<c:set var="timeRef" value="0" />
<c:set var="timeAtt" value="0" />

<h3 class="titre3">Détail des demandes de ${utilisateur.nom}
	${utilisateur.prenom}</h3>
<div id="accordion">
	<h3>
		<a href="#">Demandes Validées (${etat1 + etat32})</a>
	</h3>
	<table class="display dataTable">
		<thead>
			<tr class="header">
				<c:if test="${utilisateur.role == 'eleve'}">
					<th>Nom du Professeur</th>
				</c:if>
				<c:if
					test="${utilisateur.role == 'prof-internant' or utilisateur.role == 'prof-principal'}">
					<th>Nom de l'élève</th>
				</c:if>
				<th>Type d'accompagnement</th>
				<th>Temps</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${utilisateur.role == 'eleve'}">
				<c:forEach items="${sesDCTAPeleve}" var="dctap">
					<c:if test="${dctap.etat != 8 && dctap.etat != 2 && dctap.etat != 64}">
						<c:set var="timeTotEffec" value="${timeTotEffec + dctap.minutes}" />
					</c:if>
					<c:if test="${dctap.etat == 0 || dctap.etat == 4 || dctap.etat > 1023}">
						<c:set var="timeAtt" value="${timeAtt + dctap.minutes}"/>
					</c:if>
					<c:if test="${dctap.etat == 1 || dctap.etat == 32 }">
						<tr>
							<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
							<td>${dctap.accPers.nom}</td>
							<td><fmt:formatNumber
									value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#00" />h<fmt:formatNumber
									value="${dctap.minutes%60}" pattern="#00" /></td>
							<td>${dctap.dateAction}</td>
						</tr>
						<c:set var="timeVal" value="${timeVal + dctap.minutes}" />
					</c:if>
				</c:forEach>
			</c:if>
			<c:if
				test="${utilisateur.role == 'prof-internant' or utilisateur.role == 'prof-principal'}">
				<c:forEach items="${sesDCTAPprof}" var="dctap">
					<c:if test="${dctap.etat != 8 && dctap.etat != 2 && dctap.etat != 64}">
						<c:set var="timeTotEffec" value="${timeTotEffec + dctap.minutes}" />
					</c:if>
					<c:if test="${dctap.etat == 0 || dctap.etat == 4 || dctap.etat > 1023}">
						<c:set var="timeAtt" value="${timeAtt + dctap.minutes}"/>
					</c:if>
					<c:if test="${dctap.etat == 1 || dctap.etat == 32 }">
						<tr>
							<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
							<td>${dctap.accPers.nom}</td>
							<td><fmt:formatNumber
									value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#00" />h<fmt:formatNumber
									value="${dctap.minutes%60}" pattern="#00" /></td>
							<td>${dctap.dateAction}</td>
						</tr>
						<c:set var="timeVal" value="${timeVal + dctap.minutes}" />
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<c:if test="${utilisateur.role == 'eleve'}">
		<script>
			$(document).ready(function() {
				 $("#progressbar").progressbar({ value: ${timeVal/(72*60)*100} });
			});
		</script>
	</c:if>

	<h3>
		<a href="#">Demandes en attente (${etat0 + etat4 + etatsup1000})</a>
	</h3>
	<table class="display dataTable">
		<thead>
			<tr class="header">
				<c:if test="${utilisateur.role == 'eleve'}">
					<th>Nom du Professeur</th>
				</c:if>
				<c:if
					test="${utilisateur.role == 'prof-internant' or utilisateur.role == 'prof-principal'}">
					<th>Nom de l'élève</th>
				</c:if>
				<th>Type d'accompagnement</th>
				<th>Temps</th>
				<th>Date</th>
				<th>Cause</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${utilisateur.role == 'eleve'}">
				<c:forEach items="${sesDCTAPeleve}" var="dctap">
					<c:if
						test="${dctap.etat == 0 || dctap.etat == 4 || dctap.etat > 1023}">
						<tr>
							<td>${dctap.prof.nom} ${dctap.prof.nom}</td>
							<td>${dctap.accPers.nom}</td>
							<td><fmt:formatNumber
									value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#00" />h<fmt:formatNumber
									value="${dctap.minutes%60}" pattern="#00" /></td>
							<td>${dctap.dateAction}</td>
							<c:if test="${dctap.etat == 0}">
								<td>Non traité</td>
							</c:if>
							<c:if test="${dctap.etat == 4}">
								<td>Non traité (Modifié)</td>
							</c:if>
							<c:if test="${dctap.etat > 1023}">
								<td>En attente de l'élève</td>
							</c:if>
						</tr>
						<c:if test="${dctap.etat != 8}">
							<c:set var="timeRef" value="${timeRef + dctap.minutes}" />
						</c:if>

					</c:if>
				</c:forEach>
			</c:if>
			<c:if
				test="${utilisateur.role == 'prof-internant' or utilisateur.role == 'prof-principal'}">
				<c:forEach items="${sesDCTAPprof}" var="dctap">
					<c:if
						test="${dctap.etat == 0 || dctap.etat == 4 || dctap.etat > 1023}">
						<tr>
							<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
							<td>${dctap.accPers.nom}</td>
							<td><fmt:formatNumber
									value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#00" />h<fmt:formatNumber
									value="${dctap.minutes%60}" pattern="#00" /></td>
							<td>${dctap.dateAction}</td>
							<c:if test="${dctap.etat == 0}">
								<td>Non traité</td>
							</c:if>
							<c:if test="${dctap.etat == 4}">
								<td>Non traité (Modifié)</td>
							</c:if>
							<c:if test="${dctap.etat > 1023}">
								<td>En attente de l'élève</td>
							</c:if>
						</tr>
						<c:if test="${dctap.etat != 8}">
							<c:set var="timeRef" value="${timeRef + dctap.minutes}" />
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<h3>
		<a href="#">Demandes Refusées (${etat2 + etat8 + etat64})</a>
	</h3>
	<table class="display dataTable">
		<thead>
			<tr class="header">
				<c:if test="${utilisateur.role == 'eleve'}">
					<th>Nom du Professeur</th>
				</c:if>
				<c:if
					test="${utilisateur.role == 'prof-internant' or utilisateur.role == 'prof-principal'}">
					<th>Nom de l'élève</th>
				</c:if>
				<th>Type d'accompagnement</th>
				<th>Temps</th>
				<th>Date</th>
				<th>Cause</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${utilisateur.role == 'eleve'}">
				<c:forEach items="${sesDCTAPeleve}" var="dctap">
					<c:if
						test="${dctap.etat == 2 || dctap.etat == 64 || dctap.etat == 8}">
						<tr>
							<td>${dctap.prof.nom} ${dctap.prof.nom}</td>
							<td>${dctap.accPers.nom}</td>
							<td><fmt:formatNumber
									value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#00" />h<fmt:formatNumber
									value="${dctap.minutes%60}" pattern="#00" /></td>
							<td>${dctap.dateAction}</td>
							<c:if test="${dctap.etat == 2}">
								<td>Refus élève</td>
							</c:if>
							<c:if test="${dctap.etat == 8}">
								<td>Annulé</td>
							</c:if>
							<c:if test="${dctap.etat == 64}">
								<td>Refus prof</td>
							</c:if>
						</tr>
						<c:if test="${dctap.etat != 8}">
							<c:set var="timeRef" value="${timeRef + dctap.minutes}" />
						</c:if>

					</c:if>
				</c:forEach>
			</c:if>
			<c:if
				test="${utilisateur.role == 'prof-internant' or utilisateur.role == 'prof-principal'}">
				<c:forEach items="${sesDCTAPprof}" var="dctap">
					<c:if
						test="${dctap.etat == 2 || dctap.etat == 64 || dctap.etat == 8}">
						<tr>
							<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
							<td>${dctap.accPers.nom}</td>
							<td><fmt:formatNumber
									value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#00" />h<fmt:formatNumber
									value="${dctap.minutes%60}" pattern="#00" /></td>
							<td>${dctap.dateAction}</td>
							<c:if test="${dctap.etat == 2}">
								<td>Refus élève</td>
							</c:if>
							<c:if test="${dctap.etat == 8}">
								<td>Annulé</td>
							</c:if>
							<c:if test="${dctap.etat == 64}">
								<td>Refus prof</td>
							</c:if>
						</tr>
						<c:if test="${dctap.etat != 8}">
							<c:set var="timeRef" value="${timeRef + dctap.minutes}" />
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<h3>
		<c:if test="${utilisateur.role == 'eleve'}">
			<a href="#">Statistiques de l'élève</a>
		</c:if>
		<c:if
			test="${utilisateur.role == 'prof-internant' or utilisateur.role == 'prof-principal'}">
			<a href="#">Statistiques du professeur</a>
		</c:if>
	</h3>
	<table class="display" id="stats">
		<thead>
			<tr>
				<th>Temps total effectué</th>
				<th>Temps total validé</th>
				<th>Temps total en attente</th>
				<th>Temps total refusé</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td rowspan="2">
					<fmt:formatNumber value="${timeTotEffec/60-(timeTotEffec%60/60)}"
						pattern="#00" />h<fmt:formatNumber value="${timeTotEffec%60}"
						pattern="#00" />
				</td>
				<td><fmt:formatNumber value="${timeVal/60-(timeVal%60/60)}"
						pattern="#00" />h<fmt:formatNumber value="${timeVal%60}"
						pattern="#00" /></td>
				<td><fmt:formatNumber value="${timeAtt/60-(timeAtt%60/60)}"
						pattern="#00" />h<fmt:formatNumber value="${timeAtt%60}"
						pattern="#00" /></td>
				<td><fmt:formatNumber value="${timeRef/60-(timeRef%60/60)}"
						pattern="#00" />h<fmt:formatNumber value="${timeRef%60}"
						pattern="#00" /></td>
			</tr>
			<tr>
				<td id="statsValide"><fmt:formatNumber
						value="${timeVal/(timeTotEffec+timeRef)*100}" pattern="#0.00" />%</td>
				<td id="statsAttente"><fmt:formatNumber
						value="${timeAtt/(timeTotEffec+timeRef)*100}" pattern="#0.00" />%</td>
				<td id="statsRefuse"><fmt:formatNumber
						value="${timeRef/(timeTotEffec+timeRef)*100}" pattern="#0.00" />%</td>
			</tr>
			<tr>
				<td colspan="4" class="hrStats"></td>
			</tr>
				<tr>
					<td colspan="2">Dernière connexion :</td>
					<td colspan="2">${lastlog}</td>
				</tr>
				<tr>
					<td colspan="2">Nombre de connexion :</td>
					<td colspan="2">${countlog}</td>
				</tr>
		</tbody>
	</table>
</div>
<table class="legend2">
	<tr>
		<td><a
			href="<c:url value="/app/admin/exportStats/${utilisateur.id}" />"><img
				src="<c:url value="../../images/pdfdl.png"/>" width="64"
				height="64" />
				<div>Export PDF statistiques</div> </a></td>
		<td><a href="<c:url value="/app/admin/exportDemandeCsv/${utilisateur.id}" />"><img
				src="<c:url value="../../images/exportcsv.png"/>" width="64"
				height="64" />
				<div>Export CSV des demandes</div> </a></td>
	</tr>
</table>
