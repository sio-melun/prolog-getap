<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Edition</h1>

<form:form modelAttribute="formDemandeConsoTempsAccPers" action="doedit"
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
        <label for="profNom">Professeur :</label>
        <div class="input">
          <form:input path="profNom" />
        </div>
        <label for="profId">id prof (pour info-debug) :</label>
        <div class="input">
          <form:input path="profId" />
        </div>
      </div>

    </fieldset>

        
          <form:hidden path="id" />
        

    <div id="buttonGroup">
      <input type="submit" value="Modifier" />
    </div>
  </div>
</form:form>