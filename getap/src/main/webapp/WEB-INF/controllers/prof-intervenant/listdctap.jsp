<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Mes demandes de validation</h1>

<c:if test="${empty listdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty listdctaps}">
	
	<table id="myTable" class="tablesorter">
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
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/prof-intervenant/index" />"
		style="text-decoration: none"> <input type="button" value="Retour">
	</a>
</div>