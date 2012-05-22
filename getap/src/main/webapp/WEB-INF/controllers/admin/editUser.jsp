<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="formEditUser" action="doEditUser"
		method="post">
		<form:errors path="*" cssClass="errors" />

		<div class="section">
			<fieldset>
				<div class="form-row">
					<label for="id">ID : </label>
					<div class="input">
						<form:input path="id" disabled="true"/>
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
					<label for="role">Role :</label> <select id="roleNom"
						name="roleNom" onchange="testRole()">
						<c:forEach items="${lesRoles}" var="role">
							<option value="${role.nom}" label="${role.nom}">${role.nom}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-row" id="inputDiscipline" style="display: none;visibility: hidden;">
					<label for="disciplineId">Discipline :</label>
					<div>
						<form:select path="disciplineId" items="${lesDisciplines}" itemValue="id"
							itemLabel="nom">
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
				<div class="form-row" id="inputLesClasse" style="display: none;visibility: hidden;">
					<label for="classeId">Les Classes :</label><br>
					<div>
						<table>
							<%
								int begin = 0;
								int end = 3;
							
							 	for (int i=0;i<50;i++){ 
							 %>
									<tr>
									
										<c:forEach items="${lesClasses}" var="classe" begin="<%=begin%>" end="<%=end%>">
											
												<td>
												<form:checkbox path="classe" name="${classe.nom}"
														value="${classe.id}" id="${classe.nom}"/></td><td> <label for="${classe.nom}" class="checkbox">${classe.nom}</label></td>
											
										</c:forEach>
										
									</tr>
							<% 
									begin += 4; 
									end +=4; 
								}
							%>
						</table>		
					</div>
				</div>
			</fieldset><br>
			
			<form:hidden path="id" />
			
			<div id="buttonGroup">
				<input type="submit" value="Editer" />
			</div>
		</div>
	</form:form>
</body>
</html>