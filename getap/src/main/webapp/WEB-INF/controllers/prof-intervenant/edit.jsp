<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			
          <select name="minutes" id="minutes">
                  <%-- end="10*6*4" --%>
          <c:forEach var="i" begin="0" end="240" step="10">
             <option value="${i}" <c:if test="${i == minute}"> selected </c:if> >
             <c:choose>
               <c:when test="${(i%60) == 0}">
                <fmt:parseNumber var="mn" integerOnly="true" 
                  type="number" value="${i/60}" />
                 0${mn} h 00 minute
              </c:when>
              <c:otherwise>
                <fmt:parseNumber var="hh" integerOnly="true" 
                   type="number" value="${i/60}" />
                <fmt:parseNumber var="mn" integerOnly="true" 
                   type="number" value="${i%60}" />
                 0${(hh)} h ${mn}  minutes
            </c:otherwise>
            </c:choose>
          </option>
          </c:forEach>
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