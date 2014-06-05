<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<br />
<table class="tableStats">
	<tr>
		<td class="tdStatsTitle" colspan="2">Statistiques des professeurs</td>
	</tr>
	<tr>
		<td class="tdStats">Demandes validées</td>
		<td class="tdStats">${demandeValProfs}</td>
	</tr>
	<tr>
		<td class="tdStats">Demandes en attente</td>
		<td class="tdStats">${demandeAttProfs}</td>
	</tr>
	<tr>
		<td class="tdStats">Demandes refusées</td>
		<td class="tdStats">${demandeRefProfs}</td>
	</tr>
	<tr>
		<td class="tdStats">Demandes reçues</td>
		<td class="tdStats">${demandeTTProfs}</td>
	</tr>
</table>
<br>

<h3 style="position: relative; top: 10px;">Liste des professeurs solicités : </h3>

<table class="display dataTable" >
	<thead>
		<tr class=header>
			<th> PROFESSEURS</th>
			<th> AP VALIDÉES</th>
			<th> AP EN ATTENTE</th>
			<th> AP REFUSÉES</th>
			<th> TOTAL</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${eachProf}" var="prof">
		
			<tr>
				<td class="tdStatsIdentity">${prof.nom} ${prof.prenom}</td>
				<td class="tdStatsProf">${prof.dctapval}</td>
				<td class="tdStatsProf">${prof.dctapatt}</td>
				<td class="tdStatsProf">${prof.dctapref}</td>
				<td class="tdStatsProf">${prof.countap}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
