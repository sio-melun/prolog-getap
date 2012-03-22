<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="nav">
	<div class="table">

		<c:if test="${user.role == 'eleve'}">
			<ul class="select">
				<li><a href="<c:url value="/app/eleve/index" />" target="_self"><b>Elèves</b></a></li>
			</ul>
		</c:if>


		<c:if test="${user.role == 'prof-intervenant'}">
			<ul class="select">
				<li><a href="<c:url value="/app/prof-intervenant/index" />" target="_self"><b>Prof
						intervenant</b></a></li>
			</ul>
		</c:if>


		<c:if test="${user.role == 'prof-principal'}">
			<ul class="select">
				<li><a href="<c:url value="/app/prof-principal/index" />" target="_self"><b>Prof
						principal</b></a></li>
			</ul>
			<ul class="select">
				<li><a href="<c:url value="/app/prof-intervenant/index" />" target="_self"><b>Prof
						intervenant</b></a></li>
			</ul>
		</c:if>


		<c:if test="${user.role == 'admin'}">
			<ul class="select">
				<li><a href="<c:url value="/app/admin/index" />" target="_self"><b>Administrateur</b></a>
				</li>
			</ul>
			<ul class="select">
				<li><a href="<c:url value="/app/hotels/index" />" target="_self"><b>Hotels</b></a></li>
			</ul>
		</c:if>


		<c:if test="${user.role == null}">
			<ul class="select">
				<li><a href="<c:url value="/app/login/index" />" target="_self"><b>Connexion</b></a>
				</li>
			</ul>
		</c:if>

	</div>
</div>
