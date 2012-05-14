<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${etat < 2 }">
	<h1>Modifier ma TAP</h1>
	
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
	      
	     <br/>
	      
	      <div class="form-row">
	        <label for="minutes">Temps d'aide personalisée :</label>
	        <div class="input">
	          <form:input path="minutes" />
	        </div>
	      </div>
	      
	      <br/>
	      
	      <div class="form-row">
	       <label for="profId">Les professeurs :</label><br>
	       <form:select path="profId" items="${lesProfs}" itemValue="id" itemLabel="nom"></form:select>
	      </div>
	      
	      <br/>
	      
	      
	      <div class="form-row">
	       <label for="accPersId">Types d'aide personnalisée :</label><br>
	       <form:select path="accPersId" items="${lesAP}" itemValue="id" itemLabel="nom"></form:select>
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
<c:if test="${etat > 1 }">
	<script type="text/javascript">
		window.location="mesdctap"
	</script>
</c:if>