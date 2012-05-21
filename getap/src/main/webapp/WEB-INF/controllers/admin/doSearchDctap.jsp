<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:if test="${empty dctap}">
	Aucune demande trouvée.
	<br>
	<br/>
</c:if>

	<c:if test="${not empty dctap}">
		<table id="search" class="tablesorter">
			<thead>
				<tr class="header">
					<th>Elève</th>
					<th>Professeur</th>
					<th>Date</th>
					<th>Durée (minute)</th>
					<th>Etat</th>
					<th>Nature</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${dctap}" var="dc">
				<tr>
					<td>${dc.eleve.nom} ${dc.eleve.prenom}</td>
					<td>${dc.prof.nom} ${dc.prof.prenom}</td>
					<td>${dc.dateAction}</td>
					<td>${dc.minutes}</td>
					<td>${dc.etat}</td>
					<td>${dc.accPers.nom}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>

	<div class="buttonGroup">
		<a href="<c:url value="/app/admin/index" />">
		 	<input type="button" value="Retour à l'index">
		</a>
	</div>
