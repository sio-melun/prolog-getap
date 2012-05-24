<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.ldv.sio.getap.app.service.impl.DBManagerGeTAP"%>

<h3 class="titre3">Mes demandes de validations</h3>

<table class="legend">
	<tr>
		<td>
			<img src="../../images/buttonValide.png"/> : Confirmer
		</td>
		<td>
			<img src="../../images/buttonModif.png"/> : Modifier
		</td>
		<td>
			<img src="../../images/buttonSuppr.png"/> : Supprimer/Refuser
		</td>
	</tr>
</table>

<c:if test="${empty mesdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty mesdctaps}">
	<div id="accordion">
		<h3><a href="#">Demandes non traitees (${etat0 + etat3})</a></h3>
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
									href="<c:url value="/app/eleve/edit?id=${dctap.id}" />"><img src="../../images/buttonModifHover.png" onmouseover="this.src='../../images/buttonModif.png';" onmouseout="this.src='../../images/buttonModifHover.png';" /></a></td>
								<td><a href=""
									onclick="if(confirm('Voulez-vous vraiment supprimer cette demande ?')){window.location.href='delete/${dctap.id}';}"><img src="../../images/buttonSupprHover.png" onmouseover="this.src='../../images/buttonSuppr.png';" onmouseout="this.src='../../images/buttonSupprHover.png';" /></a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3><a href="#">Demandes modifiees par le professeur (${etat41+etat42+etat43+etat44+etat45+etat46+etat47})</a></h3>
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
						<c:if test="${dctap.etat > 40}">
							<tr>
								<td>${dctap.prof.nom} ${dctap.prof.prenom}</td>
								<c:if test="${dctap.etat == 41}">
									<td class="isUpdate">${dctap.dateAction}</td>
									<td>${dctap.minutes}</td>
									<td>${dctap.accPers.nom}</td>
								</c:if>
								<c:if test="${dctap.etat == 42}">
									<td>${dctap.dateAction}</td>
									<td class="isUpdate">${dctap.minutes}</td>
									<td>${dctap.accPers.nom}</td>
								</c:if>
								<c:if test="${dctap.etat == 43}">
									<td>${dctap.dateAction}</td>
									<td>${dctap.minutes}</td>
									<td class="isUpdate">${dctap.accPers.nom}</td>
								</c:if>
								<c:if test="${dctap.etat == 44}">
									<td class="isUpdate">${dctap.dateAction}</td>
									<td class="isUpdate">${dctap.minutes}</td>
									<td>${dctap.accPers.nom}</td>
								</c:if>
								<c:if test="${dctap.etat == 45}">
									<td>${dctap.dateAction}</td>
									<td class="isUpdate">${dctap.minutes}</td>
									<td class="isUpdate">${dctap.accPers.nom}</td>
								</c:if>
								<c:if test="${dctap.etat == 46}">
									<td class="isUpdate">${dctap.dateAction}</td>
									<td>${dctap.minutes}</td>
									<td class="isUpdate">${dctap.accPers.nom}</td>
								</c:if>
								<c:if test="${dctap.etat == 47}">
									<td class="isUpdate">${dctap.dateAction}</td>
									<td class="isUpdate">${dctap.minutes}</td>
									<td class="isUpdate">${dctap.accPers.nom}</td>
								</c:if>
								<td><a
									href="<c:url value="/app/eleve/valid/${dctap.id}" />"><img src="../../images/buttonValideHover.png" onmouseover="this.src='../../images/buttonValide.png';" onmouseout="this.src='../../images/buttonValideHover.png';" /></a></td>
								<td><a
									href="<c:url value="/app/eleve/refuse/${dctap.id}" />"><img src="../../images/buttonSupprHover.png" onmouseover="this.src='../../images/buttonSuppr.png';" onmouseout="this.src='../../images/buttonSupprHover.png';" /></a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3><a href="#">Demandes validees (${etat1 + etat5})</a></h3>
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
		<h3><a href="#">Demandes refusees par le professeur (${etat6})</a></h3>
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
						<c:if test="${dctap.etat == 6 }">
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
		<h3><a href="#">Demandes refusees par vous-meme (${etat2})</a></h3>
		<div>
			<table id="refuse2" class="tablesorter">
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
						<c:if test="${dctap.etat == 2}">
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
