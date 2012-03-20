<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Mes demandes de consommation de TAP</h1>

<c:if test="${empty mesdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty mesdctaps}">
	<ul>
		<c:forEach items="${mesdctaps}" var="dctap">
			<li>${dctap.dateAction} (${dctap.prof.nom}) 
					<a href="<c:url value="/app/eleve/delete/${dctap.id}" />">suppression		  					
				</a> -- 
				<a href="<c:url value="/app/eleve/edit?id=${dctap.id}" />">modification         
        </a>
			</li>
		</c:forEach>
	</ul>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/eleve/index" />">
		retour
	</a>
</div>
