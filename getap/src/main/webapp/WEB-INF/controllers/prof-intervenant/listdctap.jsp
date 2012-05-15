<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty listdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty listdctaps}">
<div id="accordion">
	<h3><a href="#">Demandes reçues</a></h3>
		<div>
			<table id="attente" class="tablesorter">
				<thead>
				<tr class="header">
					<th>Eleves</th>
					<th>Date</th>
					<th>Temps (min)</th>
					<th>Type d'aide</th>
				</tr>
				</thead>
				<tbody>
				    <c:forEach items="${listdctaps}" var="dctap">
						<c:if test="${dctap.etat == 0 or dctap.etat == 3}">
						    <tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td>${dctap.minutes}</td>
								<td>${dctap.accPers.nom}</td>
								<td><a
									href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />"><input
										type="button" value="Modifier"></a></td>
								<td><a
									href="<c:url value="/app/prof-intervenant/valid/${dctap.id}" />"><input
										type="button" value="Valider"></a></td>
								<td><a href=""
									onclick="if(confirm('Voulez-vous vraiment refuser cette demande ?')){window.location.href='refuse/${dctap.id}';}"><input
										type="button" value="Refuser"></a></td>
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
					<th>Eleves</th>
					<th>Date</th>
					<th>Temps (min)</th>
					<th>Type d'aide</th>
				</tr>
				</thead>
				<tbody>
				    <c:forEach items="${listdctaps}" var="dctap">
						<c:if test="${dctap.etat == 4}">
						    <tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td>${dctap.minutes}</td>
								<td>${dctap.accPers.nom}</td>
								<td><a
									href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />"><input
										type="button" value="Modifier"></a></td>
								<td><a href=""
									onclick="if(confirm('Voulez-vous vraiment refuser cette demande ?')){window.location.href='refuse/${dctap.id}';}"><input
										type="button" value="Refuser"></a></td>
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
					<th>Eleves</th>
					<th>Date</th>
					<th>Temps (min)</th>
					<th>Type d'aide</th>
				</tr>
				</thead>
				<tbody>
				    <c:forEach items="${listdctaps}" var="dctap">
						<c:if test="${dctap.etat == 1 or dctap.etat == 5}">
						    <tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
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
					<th>Eleves</th>
					<th>Date</th>
					<th>Temps (min)</th>
					<th>Type d'aide</th>
				</tr>
				</thead>
				<tbody>
				    <c:forEach items="${listdctaps}" var="dctap">
						<c:if test="${dctap.etat == 2 or dctap.etat == 6}">
						    <tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
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

<div class="buttonGroup">
	<a href="<c:url value="/app/prof-intervenant/index" />"> <input type="button" value="Retour"></a>
</div>