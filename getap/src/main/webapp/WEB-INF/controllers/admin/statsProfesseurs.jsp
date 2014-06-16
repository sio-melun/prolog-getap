<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript"> 
function redirect() {
	window.location='statsProfesseurs?annee='+document.getElementById('idAnnee').value+'';
}
</script>

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
Visualiser l'année : 
	<select id="idAnnee" name="idAnnee">
		<c:forEach items="${allYears}" var="years">
			<c:choose>
				<c:when test="${years.anneescolaire == anneeCourante}">
					<option value="${years.anneescolaire}" selected>${years.anneescolaire}</option>
				</c:when>
				<c:otherwise>
					<option value="${years.anneescolaire}">${years.anneescolaire}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<input type="submit" value="Go" onclick="redirect();">

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

<br><br><br>

<center><a href="<c:url value="/app/admin/exportStatsProfesseurCSV" />"><img
						src="<c:url value="../../images/exportcsv.png"/>" width="64"
						height="64" />
						<div>Export CSV des profs</div></a><br><br></center>
<i>Notes : Lors de l'ouverture du fichier CSV, pensez à décocher "Séparer par les espaces" et ne laisser cocher que "Séparer par un point virgule".</i>

