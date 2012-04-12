<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Mes demandes de consommation de TAP</h1>

<c:if test="${empty mesdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty mesdctaps}">
	<table>
		<c:forEach items="${mesdctaps}" var="dctap">
			<tr>
				<td>${dctap.dateAction} (${dctap.prof.nom}) </td>
				<td><a href="javascript:if(confirm('')){window.location.href='delete/${dctap.id}';}" style="text-decoration: none"><input type="button" value="Supprimer"></a></td>
				<c:if test="${dctap.etat < 2 }">
					<td><a href="<c:url value="/app/eleve/edit?id=${dctap.id}" />" style="text-decoration: none"><input type="button" value="Modifier"></a></td>
				</c:if>
				<c:if test="${dctap.etat > 1 }">
					<td><input title="Modifié par le professeur" type="button" value="Modifier" disabled="true"></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/eleve/index" />"
		style="text-decoration: none"><input type="button" value="Retour">
	</a>
</div>

<script type="text/javascript">
function confirmSupp(){
	if(confirm('EXIT  NOW??????')){
		window.location.href='delete/'${dctap.id};
	}
}
</script>
