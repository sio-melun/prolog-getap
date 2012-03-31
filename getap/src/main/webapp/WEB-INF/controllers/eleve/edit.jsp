<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${etat < 2 }">
	<h1>Edition</h1>
	
	<form:form modelAttribute="formDemandeConsoTempsAccPers" action="doedit"
	  method="post">
	  <form:errors path="*" cssClass="errors" />
	
	  <div class="section">
	    <fieldset>
	      <div class="form-row">
	        <label for="dateaction">Date de l'action :</label>
	        <div class="input">
	          <form:input path="dateAction" />
	        </div>
	      </div>
	      <div class="form-row">
	        <label for="minutes">Temps d'aide personalisée :</label>
	        <div class="input">
	          <form:input path="minutes" />
	        </div>
	      </div>
	      <div class="form-row">
	       <label for="profId">Les professeurs :</label><br>
	       <form:select path="profId" items="${lesProfs}" itemValue="id" itemLabel="nom"></form:select>
	      </div>
	      <div class="form-row">
	       <label for="accPersId">Types d'aide personnalisée :</label><br>
	       <form:select path="accPersId" items="${lesAP}" itemValue="id" itemLabel="nom"></form:select>
	      </div>
	      <%--
	      <div class="form-row">
	        <label for="profNom">Professeur :</label>
	        <div class="input">
	          <form:input path="profNom" />
	        </div>
	        <label for="profId">id prof (pour info-debug) :</label>
	        <div class="input">
	          <form:input path="profId" />
	        </div>
	      </div>
	 --%>
	    </fieldset>
	
	        
	          <form:hidden path="id" />
	        
	
	    <div id="buttonGroup">
	      <input type="submit" value="Modifier" />
	      <a href="mesdctap" style="text-decoration:none"><input type="button" value="Retour"></a>
	    </div>
	  </div>
	</form:form>
</c:if>
<c:if test="${etat > 1 }">
	<SCRIPT LANGUAGE="JavaScript"/>
	window.location="mesdctap"
	</SCRIPT>
</c:if>