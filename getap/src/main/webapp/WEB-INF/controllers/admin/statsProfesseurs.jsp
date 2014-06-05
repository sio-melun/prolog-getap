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
		<td class="tdStats">Demandes reçues</td>
		<td class="tdStats">${demandeTTProfs}</td>
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
</table>

<table class="tableStats">
	<tr>
		<td class="tdStatsTitle" colspan="6">Liste des professeurs solicités</td>
	</tr>
	<tr>
		<td class="tdStatsIdentityPrimary" colspan="2">PROFESSEURS</td>
		<td class="tdStatsProf">AP VALIDÉES</td>
		<td class="tdStatsProf">AP EN ATTENTE</td>
		<td class="tdStatsProf">AP REFUSÉES</td>
		<td class="tdStatsProf">TOTAL</td>
	</tr>
	<c:forEach items="${eachProf}" var="prof">
	<tr>
		<td class="tdStatsIdentity" colspan="2">&nbsp;&nbsp;${prof.nom} ${prof.prenom}</td>
		<td class="tdStatsProf">${prof.dctapval}</td>
		<td class="tdStatsProf">${prof.dctapatt}</td>
		<td class="tdStatsProf">${prof.dctapref}</td>
		<td class="tdStatsProf">${prof.countap}</td>
	</tr>
	</c:forEach>
</table>