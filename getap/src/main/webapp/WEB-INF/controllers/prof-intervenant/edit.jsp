<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				<% for(int i=5; i<=300; i+=5) {%>
					<c:set var="valueI" value="<%=i%>"/>
					<% if( (int) i/60 != 0 ){%>
						<c:if test="${valueI == minute }">
							<option selected="selected" value="<%=i%>"><%=(int)i/60 %>h <%=(int)i%60 %>min</option>
						</c:if>
						<c:if test="${valueI != minute }">
							<option value="<%=i%>"><%=(int)i/60 %>h <%=(int)i%60 %>min</option>
						</c:if>
					<% } else { %>
						<c:if test="${valueI == minute }">
							<option selected="selected" value="<%=i%>"><%=(int)i%60 %>min</option>
						</c:if>
						<c:if test="${valueI != minute }">
							<option value="<%=i%>"><%=(int)i%60 %>min</option>
						</c:if>
					<% } 
				}%>
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