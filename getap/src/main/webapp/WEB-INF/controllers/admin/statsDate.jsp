<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript"> 
function redirect() {
	window.location='statsDate?mois='+document.getElementById('mois').value+'';
}
</script>

<h3><center>Statistiques par date</center></h3>

<div>
	Visualiser le mois : 
	<select id="mois" name="mois">
		<c:forEach items="${tousMois}" var="mois">
			<c:choose>
				<c:when test="${mois.numMois == moisCourante}">
					<option value="${mois.numMois}" selected>${mois.mois}</option>
				</c:when>
				<c:otherwise>
					<option value="${mois.numMois}">${mois.mois}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<input type="submit" value="Go" onclick="redirect();">
</div>

<div>
	<table class="display dataTable" >
		<thead>
			<tr class=header>
				<th>NOM</th>
				<th>PRENOM</th>
				<th>DATE</th>
				<th>TYPE D'AP</th>
				<th>NOMBRE DE PARTICIPANT(élève)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${typeApParMois}" var="tApParMois">
				<tr>
					<td>${tApParMois.nomProf}</td>
					<td>${tApParMois.prenomProf}</td>
					<td>${tApParMois.dateAP}</td>
					<td>${tApParMois.typeAP}</td>
					<td>${tApParMois.nbParticipant}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


