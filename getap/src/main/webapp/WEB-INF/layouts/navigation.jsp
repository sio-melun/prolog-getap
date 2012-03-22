<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="nav">
	<div class="table">
		
			<c:if test="${user.role == 'eleve'}">
			<ul class="select">
				<li><a href="<c:url value="/app/eleve/index" />">Elèves</a>
				</li>
				</ul>
			</c:if>
		
		
			<c:if test="${user.role == 'prof-intervenant'}">
			<ul class="select">
				<li><a href="<c:url value="/app/prof-intervenant/index" />">Prof
						intervenant</a>
				</li>
				</ul>
			</c:if>
		
		
			<c:if test="${user.role == 'prof-principal'}">
			<ul class="select">
				<li><a href="<c:url value="/app/prof-principal/index" />">Prof
						principal</a>
				</li>
			</ul>
			<ul class="select">
				<li><a href="<c:url value="/app/prof-intervenant/index" />">Prof
						intervenant</a>
				</li>
			</ul>
			</c:if>
		
		
			<c:if test="${user.role == 'admin'}">
			<ul class="select">
				<li><a href="<c:url value="/app/admin/index" />">Administrateur</a>
				</li>
			</ul>
			<ul class="select">
				<li><a href="<c:url value="/app/hotels/index" />">Hotels</a>
				</li>
			</ul>
			</c:if>

	
			<c:if test="${user.role == null}">
			<ul class="select">
				<li><a href="<c:url value="/app/login/index" />">Connexion</a>
				</li>
			</ul>
			</c:if>
		
	</div>
</div>
