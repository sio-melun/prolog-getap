<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Mes demandes de validation</h1>

<c:if test="${empty listdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty listdctaps}">
	<table>
		<c:forEach items="${listdctaps}" var="dctap">
			<tr>
				<td>${dctap.dateAction} (${dctap.eleve.nom})</td>
				<td><a href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />"style="text-decoration:none"><input type="button" value="Modifier" ></a></td>
				<td><a href="<c:url value="/app/prof-intervenant/valid/${dctap.id}" />"style="text-decoration:none"><input type="button" value="Valider" ></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/prof-intervenant/index" />"style="text-decoration: none">
		<input type="button"
		value="Retour">
	</a>
</div>