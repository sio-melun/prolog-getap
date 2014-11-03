<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${etat <= 4 }">
	<h3 class="titre3">Modifier ma Demande</h3>
	
	<form:form modelAttribute="formDemandeConsoTempsAccPers" action="doedit"
	  method="post">
	  <form:errors path="*" cssClass="errors" />
	
	  <div class="section">
	    <fieldset>
	      <div class="form-row">
	        <label for="datepicker" >Date de l'action :</label>
	        <div class="input">
	          <form:input id="datepicker" path="dateAction" />
	        </div>
	      </div>
	      
	      <div class="form-row">
	        <label for="minutes">Temps d'aide personalisée :</label>
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
  
	      <div class="form-row">
	       <label for="profId">Les professeurs :</label>
	       <form:select path="profId" items="${lesProfs}" itemValue="id" itemLabel="nom"></form:select>
	      </div>
	      
	      <div class="form-row">
				<label for="accPersId">Type d'aide personnalisée : </label> <select
					id="accPersId" name="accPersId" onchange="testAcc()">
					<c:forEach items="${lesAP}" var="ap">
						<option value="${ap.id}" label="${ap.nom}">${ap.nom}</option>
					</c:forEach>
					<option value="0" label="Autre">Autre</option>
				</select>
			</div>

			<div class="from-row" id="inputAcc"
				style="display: none; visibility: hidden;">
				<label for="accPersNom">Aide personnalisée : </label>
				<form:input path="accPersNom" />
			</div>

	    </fieldset>
	
	        
	          <form:hidden path="id" />
	    <br/>
	
	    <div id="buttonGroup">
	    <a href="<c:url value="/app/eleve/mesdctap" />"
		style="text-decoration: none"><input type="button" value="Retour">
		</a>
	      <input type="submit" value="Modifier" />
	    </div>
	  </div>
	</form:form>
</c:if>
<c:if test="${etat > 4 }">
	<script type="text/javascript">
		window.location="mesdctap"
	</script>
</c:if>