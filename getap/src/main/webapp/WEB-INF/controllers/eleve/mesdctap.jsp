<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.ldv.sio.getap.app.service.impl.DBManagerGeTAP"%>

<c:if test="${empty mesdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty mesdctaps}">
	<div id="accordion">
		<h3><a href="#">Demandes en attentes</a></h3>
		<div>
			<table id="attente" class="tablesorter">
				<thead>
					<tr class="header">
						<th>Professeurs</th>
						<th>Date</th>
						<th>Temps (min)</th>
						<th>Type d'aide</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mesdctaps}" var="dctap">
					<c:if test="${dctap.etat == 0 or dctap.etat == 3 }">
						<tr>
							<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
							<td>${dctap.dateAction}</td>
							<td>${dctap.minutes}</td>
							<td>${dctap.accPers.nom}</td>
								<td><a
									href="<c:url value="/app/eleve/edit?id=${dctap.id}" />"><input
										type="button" value="Modifier"> </a></td>
								<td><a href=""
									onclick="if(confirm('Voulez-vous vraiment supprimer cette demande ?')){window.location.href='delete/${dctap.id}';}"><input
										type="button" value="Supprimer"> </a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3><a href="#">Demandes modifiees</a></h3>
		<div>
			<table id="modif" class="tablesorter">
				<thead>
					<tr class="header">
						<th>Professeurs</th>
						<th>Date</th>
						<th>Temps (min)</th>
						<th>Type d'aide</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mesdctaps}" var="dctap">
						<c:if test="${dctap.etat == 4}">
							<tr>
								<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td>${dctap.minutes}</td>
								<td>${dctap.accPers.nom}</td>
							
								<td><a
									href="<c:url value="/app/eleve/valid/${dctap.id}" />"><input
										type="button" value="Valider"> </a></td>
								<td><a
									href="<c:url value="/app/eleve/refuse/${dctap.id}" />"><input
										type="button" value="Refuser"> </a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3><a href="#">Demandes validees</a></h3>
		<div>
			<table id="valide" class="tablesorter">
				<thead>
					<tr class="header">
						<th>Professeurs</th>
						<th>Date</th>
						<th>Temps (min)</th>
						<th>Type d'aide</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mesdctaps}" var="dctap">
						<c:if test="${dctap.etat == 1 or dctap.etat == 5 }">
							<tr>
								<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td>${dctap.minutes}</td>
								<td>${dctap.accPers.nom}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3><a href="#">Demandes refusees</a></h3>
		<div>
			<table id="refuse" class="tablesorter">
				<thead>
					<tr class="header">
						<th>Professeurs</th>
						<th>Date</th>
						<th>Temps (min)</th>
						<th>Type d'aide</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mesdctaps}" var="dctap">
						<c:if test="${dctap.etat == 2 or dctap.etat == 6 }">
							<tr>
								<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td>${dctap.minutes}</td>
								<td>${dctap.accPers.nom}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:if>

<div class="buttonGroup" >
	<a href="<c:url value="/app/eleve/index"/>"><input type="button" value="Retour"></a>
</div>
