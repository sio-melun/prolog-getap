<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.ldv.sio.getap.app.service.impl.DBManagerGeTAP" %>

<h3>Mes demandes de consommation de TAP</h3>

<c:if test="${empty mesdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty mesdctaps}">
	<% int cpt = 0; %>
	<table border=1 align="center" class="sortable" id="youhou">
		<tr>
			<th height=25 class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Professeurs</a></th>
			<th class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Date</a></th>
			<th class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Temps (min)</a></th>
			<th class="Ftext"><a href="#" onclick="ts_resortTable(this);return false;">Type d'aide</a></th>
		</tr>
		<c:forEach items="${mesdctaps}" var="dctap">
			<% if(cpt%2==0){ %>
				<tr bgcolor="#a4a4a4">
			<% } else { %>
				<tr bgcolor="#d6d6d6">
			<% } %>
					<td class="Ftext">${dctap.prof.nom} ${dctap.prof.prenom}</td>
					<td class="Ftext">${dctap.dateAction}</td>
					<td class="Ftext">${dctap.minutes}</td>
					<td class="Ftext">${dctap.accPers.nom}</td>
					<c:if test="${dctap.etat < 2 }">
						<td class="Fbutton" bgcolor="#e4e1d9"><a href="<c:url value="/app/eleve/edit?id=${dctap.id}" />"><input type="button"
								value="Modifier"></a></td>
					</c:if>
					<c:if test="${dctap.etat > 1 }">
						<td class="Fbutton" bgcolor="#e4e1d9"><input title="Modifié par le professeur" type="button"
							value="Modifier" disabled="true"></td>
					</c:if>
					<td class="Fbutton" bgcolor="#e4e1d9"><a href=""
						onclick="if(confirm('Voulez-vous vraiment supprimer cette demande ?')){window.location.href='delete/${dctap.id}';}"><input type="button"
							value="Supprimer"></a></td>
				</tr>
			<% cpt++; %>
		</c:forEach>
	</table>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/eleve/index" />"
		style="text-decoration: none"><input type="button" value="Retour">
	</a>
</div>
