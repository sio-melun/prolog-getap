<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    <div id="accordion">
    <c:forEach items="${listClasse}" var="classe">
    	<h3><a href="#">${classe.nom}</a></h3>
		<div>
			<table id="attente" class="tablesorter">
				<thead>
				<tr class="header">
					<th>Eleves</th>
					<th>Nombre d'actions par type</th>
					<th>Temps Total</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${listdctaps}" var="dctap">
					<c:if test="${dctap.etat == 0 or dctap.etat == 3}">
						    <tr>
								<td>${dctap.eleve.nom} ${dctap.eleve.prenom}</td>
								<td>${dctap.dateAction}</td>
								<td>${dctap.minutes}</td>
								<td>${dctap.accPers.nom}</td>
								<td><a
									href="<c:url value="/app/prof-intervenant/valid/${dctap.id}" />"><img src="../../images/buttonValideHover.png" onmouseover="this.src='../../images/buttonValide.png';" onmouseout="this.src='../../images/buttonValideHover.png';" /></a></td>
								<td><a
									href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />"><img src="../../images/buttonModifHover.png" onmouseover="this.src='../../images/buttonModif.png';" onmouseout="this.src='../../images/buttonModifHover.png';" /></a></td>
								<td><a href=""
									onclick="if(confirm('Voulez-vous vraiment refuser cette demande ?')){window.location.href='refuse/${dctap.id}';}"><img src="../../images/buttonSupprHover.png" onmouseover="this.src='../../images/buttonSuppr.png';" onmouseout="this.src='../../images/buttonSupprHover.png';" /></a></td>
							</tr>
						</c:if>
				    </c:forEach>
				</tbody>
			</table>
		</div>
    </c:forEach>
    </div>