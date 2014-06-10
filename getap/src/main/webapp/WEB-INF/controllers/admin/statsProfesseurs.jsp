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
			<th> DETAIL</th>
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
				<td><a
									href="<c:url value="/app/admin/detailUser?id=${prof.id}" />"><img
										src="../../images/detail.png"
										onmouseover="this.src='../../images/detailHover.png';"
										onmouseout="this.src='../../images/detail.png';" /> </a></td>
				<td>${prof.nom} ${prof.prenom}</td>
				<td>${prof.dctapvalide}</td>
				<td>${prof.dctapattente}</td>
				<td>${prof.dctaprefuse}</td>
				<td>${prof.countap}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

