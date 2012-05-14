<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Mes demandes de validation</h1>

<c:if test="${empty listdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty listdctaps}">
	<% int cpt = 0; %>
	<table border=1 align="center" class="sortable" id="youhou">
		<thead>
		<tr>
			<th height=25 class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Eleves</a></th>
			<th class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Date</a></th>
			<th class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Temps (min)</a></th>
			<th class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Type d'aide</a></th>
		</tr>
		</thead>
		<c:forEach items="${listdctaps}" var="dctap">
			<% if (cpt % 2 == 0) { %>
				<tr bgcolor="#a4a4a4">
			<% } else { %>
				<tr bgcolor="#d6d6d6">
			<% } %>
					<td class="Ftext">${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
					<td class="Ftext">${dctap.dateAction}</td>
					<td class="Ftext">${dctap.minutes}</td>
					<td class="Ftext">${dctap.accPers.nom}</td>
					<td class="Fbutton" bgcolor="#e4e1d9"><a
						href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />"><input
							type="button" value="Modifier"></a></td>
					<td class="Fbutton" bgcolor="#e4e1d9"><a
						href="<c:url value="/app/prof-intervenant/valid/${dctap.id}" />"><input
							type="button" value="Valider"></a></td>
					<td class="Fbutton" bgcolor="#e4e1d9"><a href=""
						onclick="if(confirm('Voulez-vous vraiment refuser cette demande ?')){window.location.href='refuse/${dctap.id}';}"><input
							type="button" value="Refuser"></a></td>
				</tr>
			<% cpt++; %>
		</c:forEach>
	</table>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/prof-intervenant/index" />"
		style="text-decoration: none"> <input type="button" value="Retour">
	</a>
</div>