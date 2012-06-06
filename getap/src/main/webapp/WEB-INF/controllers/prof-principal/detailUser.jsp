<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:set var="timeTT" value="0" />
<c:set var="timeVal" value="0" />
<c:set var="timeRef" value="0" />
<c:set var="timeAtt" value="0" />

<h3 class="titre3">Détail des demandes de ${utilisateur.nom} ${utilisateur.prenom}</h3>
<div id="accordion">
	<h3>
		<a href="#">Demandes Validées (${etat1 + etat32})</a>
	</h3>
	<table class="display dataTable">
		<thead>
			<tr class="header">
				<th>Nom du Professeur</th>
				<th>Type d'accompagnement</th>
				<th>Temps</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sesDCTAPeleve}" var="dctap">
				<c:set var="timeTT" value="${timeTT + dctap.minutes}" />
				<c:if test="${dctap.etat == 1 || dctap.etat == 32 }">
					<tr>
						<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
						<td>${dctap.accPers.nom}</td>
						<td><fmt:formatNumber
								value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#0" />h
							${dctap.minutes%60}min</td>
						<td>${dctap.dateAction}</td>
					</tr>
					<c:set var="timeVal" value="${timeVal + dctap.minutes}" />
				</c:if>
			</c:forEach>
		</tbody>
	</table>

	<script>
			$(document).ready(function() {
				 $("#progressbar").progressbar({ value: ${timeVal/(72*60)*100} });
			});
		</script>

	<h3>
		<a href="#">Demandes Refusées (${etat2 + etat8 + etat64})</a>
	</h3>
	<table class="display dataTable">
		<thead>
			<tr class="header">
				<th>Nom du Professeur</th>
				<th>Type d'accompagnement</th>
				<th>Temps</th>
				<th>Date</th>
				<th>Cause</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sesDCTAPeleve}" var="dctap">
				<c:if
					test="${dctap.etat == 2 || dctap.etat == 64 || dctap.etat == 8}">
					<tr>
						<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
						<td>${dctap.accPers.nom}</td>
						<td><fmt:formatNumber
								value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#0" />h
							${dctap.minutes%60}min</td>
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
					<c:set var="timeRef" value="${timeRef + dctap.minutes}" />
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<h3>
		<a href="#">Demandes en Cours (${etat0 + etat4 + etatsup1000})</a>
	</h3>
	<table class="display dataTable">
		<thead>
			<tr class="header">
				<th>Nom du Professeur</th>
				<th>Type d'accompagnement</th>
				<th>Temps</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sesDCTAPeleve}" var="dctap">
				<c:if
					test="${dctap.etat == 0 || dctap.etat == 4 || dctap.etat > 1023 }">
					<tr>
						<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
						<td>${dctap.accPers.nom}</td>
						<td><fmt:formatNumber
								value="${dctap.minutes/60-(dctap.minutes%60/60)}" pattern="#0" />h
							${dctap.minutes%60}min</td>
						<td>${dctap.dateAction}</td>
					</tr>
					<c:set var="timeAtt" value="${timeAtt + dctap.minutes}" />
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<h3>
		<a href="#">Statistiques de l'élève</a>
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
				<td><fmt:formatNumber value="${timeTT/60-(timeTT%60/60)}"
						pattern="#0" />h ${timeTT%60}min</td>
				<td><fmt:formatNumber value="${timeVal/60-(timeVal%60/60)}"
						pattern="#0" />h ${timeVal%60}min</td>
				<td><fmt:formatNumber value="${timeAtt/60-(timeAtt%60/60)}"
						pattern="#0" />h ${timeAtt%60}min</td>
				<td><fmt:formatNumber value="${timeRef/60-(timeRef%60/60)}"
						pattern="#0" />h ${timeRef%60}min</td>
			</tr>
			<tr>

				<td><div id="progressbar"></div> <fmt:formatNumber
						value="${timeVal/(72*60)*100}" pattern="#0.00" />% - 72h requises</td>
				<td id="statsValide"><fmt:formatNumber
						value="${timeVal/timeTT*100}" pattern="#0.00" />%</td>
				<td id="statsAttente"><fmt:formatNumber
						value="${timeAtt/timeTT*100}" pattern="#0.00" />%</td>
				<td id="statsRefuse"><fmt:formatNumber
						value="${timeRef/timeTT*100}" pattern="#0.00" />%</td>
			</tr>
		</tbody>
	</table>
</div>