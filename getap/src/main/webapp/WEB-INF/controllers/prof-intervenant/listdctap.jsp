<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Validation de TAP</h1>

<c:if test="${empty listdctaps}">
	Il n'y a encore aucune demande. 
</c:if>

<c:if test="${not empty listdctaps}">
	<ul>
		<c:forEach items="${listdctaps}" var="dctap">
			<li>${dctap.dateAction} (${dctap.eleve.nom})
				<a href="<c:url value="/app/prof-intervenant/edit?id=${dctap.id}" />">modification         
        		</a>  --
				<a href="<c:url value="/app/prof-intervenant/valid/${dctap.id}" />">valider		  					
				</a> 
				
			</li>
		</c:forEach>
	</ul>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/prof-intervenant/index" />">
		retour
	</a>
</div>