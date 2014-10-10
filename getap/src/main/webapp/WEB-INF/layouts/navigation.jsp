<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="nav">
	<div class="table">
	
  <c:if test="${not empty user}">
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
			<!-- 
			<ul class="select">
				<li><a href="<c:url value="/app/prof-intervenant/prevalidation" />"
					target="_self"><b>Mes Prevalidations</b></a></li>
			</ul>
			 -->
		</c:if>


		<c:if test="${user.role == 'admin'}">
			<ul class="select">
				<li><a href="<c:url value="/app/admin/index" />" target="_self"><b>Gestion d'utilisateur</b>
				</a></li>
				<li><a href="<c:url value="/app/admin/logiciel" />" target="_self"><b>Administration</b>
				</a></li>
			</ul>
		</c:if>
</c:if>

<c:if test="${empty user}">
    <%--c:if test="${user.role == null}">   --%>  
      <div class="menuLogin">
      <form:form modelAttribute="userLoginCriteria" action="authenticate" id="connexion"
        method="post">
            
        <!-- <label for="login">Identifiant :</label> -->
        <form:input path="login" value="Identifiant" onFocus="javascript:this.value=''" onBlur="javascript:if(this.value==''){this.value='Identifiant'}"/>

        <!-- <label for="password">&nbsp Mot de passe :</label> -->
        <form:password path="password" value="Mot de passe" onFocus="javascript:this.value=''" onBlur="javascript:if(this.value==''){this.value='Mot de passe'}"/>
        
        
        <input  class="co" src="<c:url value="../../images/CoTransparent.png"/>" type="image" value="submit">
        <div id="erreur"><form:errors path="*" cssClass="errors" /></div>
          
      </form:form>
      </div>
</c:if>

	</div>
</div>

