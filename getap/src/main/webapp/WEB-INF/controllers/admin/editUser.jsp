<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	
<h3 class="titre3">Edition d'un utilisateur</h3>
<form:form modelAttribute="formEditUser" action="doEditUser"
	method="post">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			   <label> </label>
				<div class="input">
					<form:hidden path="id" disabled="true" />
				</div>
			<div class="form-row">
				<label for="login"> Login : </label>
				<div class="input">
					<form:input path="login" disabled="true"  value='${user.login}'/>
				</div>
			</div>
			<div class="form-row">
				<label for="prenom">Prénom : </label>
				<div class="input">
					<form:input path="prenom" />
				</div>
			</div>
			<div class="form-row">
				<label for="nom">Nom : </label>
				<div class="input">
					<form:input path="nom" />
				</div>
			</div>
			<div class="form-row">
				<label for="role">Role :</label> <select id="roleNom" name="roleNom"
					onchange="testRole()">
					<c:forEach items="${lesRoles}" var="role">
						<c:if test="${role.nom != fonction}">
							<option value="${role.nom}" label="${role.nom}">${role.nom}</option>
						</c:if>
						<c:if test="${role.nom == fonction}">
							<option selected="selected" value="${role.nom}" label="${role.nom}">${role.nom}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-row" id="inputDiscipline"
				style="display: none; visibility: hidden;">
				<label for="disciplineId">Discipline :</label>
				<div>
					<form:select path="disciplineId" items="${lesDisciplines}"
						itemValue="id" itemLabel="nom">
					</form:select>
				</div>
			</div>
			<div class="form-row" id="inputClasse">
				<label for="classeId">La classe :</label>
				<div>
					<form:select path="classeId" items="${lesClasses}" itemValue="id"
						itemLabel="nom">
					</form:select>
				</div>
			</div>
			<div class="form-row" id="inputLesClasse"
				style="display: none; visibility: hidden;">
				<label for="classeId">Les Classes :</label><br>
				<div>
					<table>
						<%
							int ligne = 0; 
						%>
						<tr>
							<c:forEach items="${lesClasses}" var="classe" >
							   <% ligne++; %>
							   <c:if test="${nbLigne % 4 == 0}"> 
							   	  </tr><tr> 
							   	</c:if> 
							   
							   <c:set var="nbLigne" value="<%=ligne%>" />
							   
								<c:forEach items="${mesClasses}" var="maClasse">
									<c:if test="${maClasse.nom == classe.nom}">
									<td><form:checkbox path="classe" name="${classe.nom}"
										value="${classe.id}" id="${classe.nom}" checked="checked"/></td>
									</c:if>
								</c:forEach>								
								
								<%-- TODO: pourquoi cette deuxième itération ? ne pouvons nous
								           pas gérer cpt dans la première boucle ?
								--%>
								
								<% int cpt = 0; %>
								<c:forEach items="${mesClasses}" var="maClasse">
									<c:if test="${maClasse.nom == classe.nom}">
											<% cpt++; %>
									</c:if>
								</c:forEach>
																
								<c:set var="compteur" value="<%=cpt%>"/>
								<c:if test="${compteur == 0}">
							        <td><form:checkbox path="classe" name="${classe.nom}"
											value="${classe.id}" id="${classe.nom}" />
									</td>
								</c:if>
								
								<td><label for="${classe.nom}" class="checkbox">${classe.nom}</label>
								</td>
							</c:forEach>
						</tr>
						
					</table>
				</div>
			</div>
		</fieldset>
		<br>

		<form:hidden path="id" />

		<div id="buttonGroup">
			<input type="submit" value="Sauvegarder" />
		</div>
	</div>
</form:form>
<form:form modelAttribute="formEditUser" action="doEditPass"
	method="post" id="resetPass">
	<form:errors path="*" cssClass="errors" />
	<div class="form-row">
		<input type="image" name="recycled" id="recycled"
			onclick="alert('mot de passe initial : ${user.pass}')"
			src="../../images/recycled.png"
			onmouseover="this.src='../../images/recycledHover.png';"
			onmouseout="this.src='../../images/recycled.png';" />
			<br>
			<label for="recycled" id="lRecycled">Réinitialiser le mot de passe</label>
			 <pre style="font-size: 150%">${user.pass}</pre>
	</div>
	<form:hidden path="id" />
</form:form>
