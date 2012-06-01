<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="nav">
	<div class="table">

		<c:if test="${user.role == 'eleve'}">
			<ul class="select">
				<li><a href="<c:url value="/app/eleve/index" />" target="_self"><b>Menu</b> </a></li>
			</ul>
		</c:if>


		<c:if test="${user.role == 'prof-principal'}">
			<ul class="select">
				<li><a href="<c:url value="/app/prof-principal/index" />"
					target="_self"><b>Mes classes</b></a></li>
			</ul>
		</c:if>


		<c:if
			test="${user.role == 'prof-intervenant' or user.role == 'prof-principal'}">
			<ul class="select">
				<li><a href="<c:url value="/app/prof-intervenant/index" />"
					target="_self"><b>Mes demandes</b></a></li>
			</ul>
		</c:if>


		<c:if test="${user.role == 'admin'}">
			<ul class="select">
				<li><a href="<c:url value="/app/admin/index" />" target="_self"><b>Gestion d'utilisateur</b>
				</a></li>
				<li><a href="<c:url value="/app/admin/logiciel" />" target="_self"><b>Administration</b>
				</a></li>
			</ul>
		</c:if>


		<c:if test="${user.role == null}">
<%-- 			<ul class="select">
				<li><a class="co"  href="<c:url value="/app/login/index" />" target="_self">
						<img src="<c:url value="../../images/CoTransparent.png"/>" /> </a>
				</li>
			</ul> --%>


			<form:form modelAttribute="userLoginCriteria" action="authenticate" id="connexion"
				method="post">
						
				<label for="login">Identifiant :</label>
				<form:input path="login" />

				<label for="password">&nbsp Mot de passe :</label>
				<form:password path="password" />
				
				
				<input  class="co" src="<c:url value="../../images/CoTransparent.png"/>" type=image Value=submit>
				<div id="erreur"><form:errors path="*" cssClass="errors" /></div>
					
			</form:form>
		</c:if>

		<c:if test="${user.role != null}">
			<ul class="select">
				<li><a href="<c:url value="/app/profil/edit?id=${user.id}"/>"
					taget="_self"><b>Mon compte</b></a></li>
				<li><a class="deco" href="<c:url value="/app/login/logout" />"
					target="_self"><img
						src="<c:url value="../../images/CoTransparent.png"/>"  width="131" height="23"/> </a></li>

				<%-- <li><img class="deco" src="<c:url value="../../images/BoutonTransparent.png"/>" /></li> --%>
			</ul>
		</c:if>

	</div>
</div>

