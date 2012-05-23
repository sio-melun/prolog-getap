<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<table class="legend">
	<tr>
		<td>
			<img src="../../images/buttonModif.png"/> : Modifier
		</td>
		<td>
			<img src="../../images/buttonSuppr.png"/> : Supprimer
		</td>
	</tr>
</table>

<table id="attente" class="tablesorter">
	<thead>
		<tr class="header">
			<th>Nom</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${lesAP}" var="ap">
			<tr>
				<td>${ap.nom}</td>
				<c:if test="${ap.origineEtat == 1}">
					<td>Elève</td>
				</c:if>
				<td><a href="<c:url value="/app/admin/editAp?id=${ap.id}" />"><img
						src="../../images/buttonModifHover.png"
						onmouseover="this.src='../../images/buttonModif.png';"
						onmouseout="this.src='../../images/buttonModifHover.png';" /> </a></td>
				<td><a href=""
					onclick="if(confirm('Voulez-vous vraiment supprimer cette aide personnalisée ?')){window.location.href='deleteAP/${ap.id}';}"><img
						src="../../images/buttonSupprHover.png"
						onmouseover="this.src='../../images/buttonSuppr.png';"
						onmouseout="this.src='../../images/buttonSupprHover.png';" /> </a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>