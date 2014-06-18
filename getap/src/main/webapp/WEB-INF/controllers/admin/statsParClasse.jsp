<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript"> 
function redirect() {
	window.location='statsClasse?idClasse='+document.getElementById('idClasse').value+'';
}
</script>

<div>
	Choisissez une classe : 
	<select id="idClasse" name="idClasse">
		<c:forEach items="${allClasses}" var="classes">
			<option value="${classes.idClasse}">${classes.libelleClasse}</option>
		</c:forEach>
	</select>
	<input type="submit" value="Go" onclick="redirect();">
</div>

<c:if test="${nomClasse != null}" >
	<div>
		<table class="tableStats">
			<tr>
				<td colspan="2">Statistiques des élèves de ${nomClasse} </td>
			</tr>
			<tr>
				<td class="tdStats">Demandes validées</td>
				<td class="tdStats">${dctapvalideTotalElevesByClasse}</td>
			</tr>
			<tr>
				<td class="tdStats">Demandes en attente</td>
				<td class="tdStats">${dctapattenteTotalElevesByClasse}</td>
			</tr>
			<tr>
				<td class="tdStats">Demandes refusées</td>
				<td class="tdStats">${dctaprefuseTotalElevesByClasse}</td>
			</tr>
			<tr>
				<td class="tdStats">Demandes reçues</td>
				<td class="tdStats">${countapTotalElevesByClasse}</td>
			</tr>
		</table>
	</div>
	<br>
	<div>
		<table class="display dataTable" >
			<thead>
				<tr class=header>
					<th> DETAIL</th>
					<th> NOM</th>
					<th> PRENOM</th>
					<th> AP VALIDÉES</th>
					<th> AP EN ATTENTE</th>
					<th> AP REFUSÉES</th>
					<th> TOTAL</th>
					<th> DERNIÈRE CONNEXION</th>
					<th> NOMBRE DE CONNEXION</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${classeStats}" var="classe">
				<c:set value="" var="classPercentZero" />
					<tr>
						<td class="${classPercentZero}"><a
											href="<c:url value="/app/admin/detailUser?id=${classe.idEleveSQL}" />"><img
												src="../../images/detail.png"
												onmouseover="this.src='../../images/detailHover.png';"
												onmouseout="this.src='../../images/detail.png';" /> </a></td>
						<td>${classe.nomEleve}</td>
						<td>${classe.prenomEleve}</td>
						<td>${classe.dctapvalideEleve}</td>
						<td>${classe.dctapattenteEleve}</td>
						<td>${classe.dctaprefuseEleve}</td>
						<td>${classe.countapEleve}</td>
						<td>${classe.lastlogEleve}</td>
						<td>${classe.countlogEleve}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br>
	<div>
		<h3>Les professeurs intervenus dans cette classe </h3>
		<table class="display dataTable" >
			<thead>
				<tr class=header>
					<th> DETAIL</th>
					<th> NOM</th>
					<th> PRENOM</th>
					<th> AP VALIDÉES</th>
					<th> AP EN ATTENTE</th>
					<th> AP REFUSÉES</th>
					<th> TOTAL</th>
					<th> DERNIÈRE CONNEXION</th>
					<th> NOMBRE DE CONNEXION</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eachProfForEachClasse}" var="profParClasse">
				<c:set value="" var="classPercentZero" />
					<tr>
						<td class="${classPercentZero}"><a
											href="<c:url value="/app/admin/detailUser?id=${profParClasse.idProfSQL}" />"><img
												src="../../images/detail.png"
												onmouseover="this.src='../../images/detailHover.png';"
												onmouseout="this.src='../../images/detail.png';" /> </a></td>
						<td>${profParClasse.nomProf}</td>
						<td>${profParClasse.prenomProf}</td>
						<td>${profParClasse.dctapvalideProf}</td>
						<td>${profParClasse.dctapattenteProf}</td>
						<td>${profParClasse.dctaprefuseProf}</td>
						<td>${profParClasse.countapProf}</td>
						<td>${profParClasse.lastlogProf}</td>
						<td>${profParClasse.countlogProf}</td>
					</tr>
				</c:forEach>
					
			</tbody>
		</table>
	</div>
</c:if>
<br>
