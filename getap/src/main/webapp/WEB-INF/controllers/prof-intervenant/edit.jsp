<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Edition</h1>

<form:form modelAttribute="formListConsoForProfInter" action="doedit"
  method="post">
  <form:errors path="*" cssClass="errors" />

  <div class="section">
    <fieldset>
      <div class="form-row">
        <label for="dateaction">Date de l'action:</label>
        <div class="input">
          <form:input path="dateAction" />
        </div>
      </div>
      <div class="form-row">
        <label for="minutes">Temps d'aide personnalisée :</label>
        <div class="input">
          <form:input path="minutes" />
        </div>
        <label for="accPersId">Type d'aide personnalisée :</label><br>
        <form:select path="accPersId" items="${lesAP}" itemValue="id" itemLabel="nom"></form:select>
      </div>

    </fieldset>

        
          <form:hidden path="id" />
        

    <div id="buttonGroup">
      <input type="submit" value="Modifier" />
    </div>
  </div>
</form:form>