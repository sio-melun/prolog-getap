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