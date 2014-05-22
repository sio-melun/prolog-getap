<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h3 class="titre3">Modification d'une demande de validation</h3>
<form:form modelAttribute="formListConsoForProfInter" action="doedit"
	method="post">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>

			<div class="form-row">
				<label for="datepicker">Date de l'action :</label>
				<div class="input">
					<form:input id="datepicker" path="dateAction" />
				</div>
			</div>

			<div class="form-row">
				<label for="minutes">Temps d'aide personnalisée :</label>
				<%-- <div class="input">
					<form:input path="minutes" />
				</div> --%>
				<select name="minutes" id="minutes">
				<% 
				for(int i = 1; i <= 10; i++)
				{
				%>
					<option value="<%=i*30%>">
					<%
					if(i%2 == 0)
					{
					%>
						0<%=i/2%> h 00 minute
					<%
					}
					else
					{
						if((i-1) < 2)
						{
							%>
							00 h 30 minutes
							<%
						}
						else
						{
							%>
							0<%=((i-1)/2)%> h 30 minutes
							<%
						}
					}
					%>
					</option>
					<%
				}
				%>
				</select>
			</div>

			<div>
				<label for="accPersId">Type d'aide personnalisée :</label>
				<form:select path="accPersId" items="${lesAP}" itemValue="id"
					itemLabel="nom"></form:select>
			</div>

		</fieldset>

		<form:hidden path="id" />

		<div id="buttonGroup">
			<a href="<c:url value="/app/prof-intervenant/index" />"> <input type="button"
				value="Retour">
			</a> <input type="submit" value="Modifier" />
		</div>
	</div>
</form:form>