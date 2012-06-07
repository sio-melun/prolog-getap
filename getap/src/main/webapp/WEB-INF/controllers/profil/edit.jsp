<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:set var="timeTT" value="0" />
<c:set var="timeVal" value="0" />
<c:set var="timeRef" value="0" />
<c:set var="timeAtt" value="0" />

<h3 class="titre3">Modifier mon profil</h3>

<form:form modelAttribute="formEditProfil" action="doedit" method="post">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			<div class="form-row">
				<label for="login">Login :</label>
				<div class="input">
					<form:input path="login" size="50px" disabled="true" />
				</div>
			</div>

			<div class="form-row">
				<label for="oldPass">Ancien mot de passe :</label>
				<div class="password">
					<form:password path="oldPass" size="50px" />
				</div>
			</div>

			<div class="form-row">
				<label for="firstPass">Nouveau mot de passe :</label>
				<div class="password">
					<form:password path="firstPass" size="50px" />
				</div>
			</div>

			<div class="form-row">
				<label for="secondPass">Retaper le nouveau mot de passe :</label>
				<div class="password">
					<form:password path="secondPass" size="50px" />
				</div>
			</div>

			<div class="form-row">
				<label for="mail">E-Mail :</label>
				<div class="input">
					<form:input path="mail" size="75px" />
				</div>
			</div>
		</fieldset>

		<div id="buttonGroup">
			<input type="submit" value="Sauvegarder" />
		</div>
	</div>
</form:form>

<c:if test="${utilisateur.role == 'eleve' }">
	<c:forEach items="${sesDCTAPeleve}" var="dctap">
		<c:set var="timeTT" value="${timeTT + dctap.minutes}" />
		<c:if test="${dctap.etat == 1 || dctap.etat == 32 }">
			<c:set var="timeVal" value="${timeVal + dctap.minutes}" />
		</c:if>
	</c:forEach>
	<c:forEach items="${sesDCTAPeleve}" var="dctap">
		<c:if test="${dctap.etat == 2 || dctap.etat == 64 || dctap.etat == 8}">
			<c:set var="timeRef" value="${timeRef + dctap.minutes}" />
		</c:if>
	</c:forEach>
	<c:forEach items="${sesDCTAPeleve}" var="dctap">
		<c:if
			test="${dctap.etat == 0 || dctap.etat == 4 || dctap.etat > 1023 }">
			<c:set var="timeAtt" value="${timeAtt + dctap.minutes}" />
		</c:if>
	</c:forEach>
	
	<div id="accordion">
		<h3>
			<a href="#">Mes statistiques</a>
		</h3>
		<table class="display" id="stats">
			<thead>
				<tr>
					<th>Temps total validé</th>
					<th>Temps total en attente</th>
					<th>Temps total refusé</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><fmt:formatNumber value="${timeVal/60-(timeVal%60/60)}"
							pattern="#00" />h<fmt:formatNumber value="${timeVal%60}"
							pattern="#00" /></td>
					<td><fmt:formatNumber value="${timeAtt/60-(timeAtt%60/60)}"
							pattern="#00" />h<fmt:formatNumber value="${timeAtt%60}"
							pattern="#00" /></td>
					<td><fmt:formatNumber value="${timeRef/60-(timeRef%60/60)}"
							pattern="#00" />h<fmt:formatNumber value="${timeRef%60}"
							pattern="#00" /></td>
				</tr>
				<tr>
					<td id="statsValide"><fmt:formatNumber
							value="${timeVal/timeTT*100}" pattern="#0.00" />%</td>
					<td id="statsAttente"><fmt:formatNumber
							value="${timeAtt/timeTT*100}" pattern="#0.00" />%</td>
					<td id="statsRefuse"><fmt:formatNumber
							value="${timeRef/timeTT*100}" pattern="#0.00" />%</td>
				</tr>
			</tbody>
		</table>
	</div>
</c:if>