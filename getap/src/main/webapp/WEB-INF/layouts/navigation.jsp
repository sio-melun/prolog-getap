<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
	<c:if test="${user.role == 'eleve'}">
	<li><a href="<c:url value="/app/eleve/index" />">Elèves</a></li>
	</c:if>
	
	<c:if test="${user.role == 'prof-intervenant'}">
	<li><a href="<c:url value="/app/prof-intervenant/index" />">Prof intervenant</a></li>
	</c:if>
	
	<c:if test="${user.role == 'prof-principal'}">
	<li><a href="<c:url value="/app/prof-principal/index" />">Prof principal</a></li>
	<li><a href="<c:url value="/app/prof-intervenant/index" />">Prof intervenant</a></li>
	</c:if>
	
	<c:if test="${user.role == 'admin'}">
	<li><a href="<c:url value="/app/admin/index" />">Administrateur</a></li>
	<li><a href="<c:url value="/app/hotels/index" />">Hotels</a></li>
	</c:if>
	
	<c:if test="${user.role == null}">
	<li><a href="<c:url value="/app/login/index" />">Connexion</a></li>
	</c:if>
</ul>
