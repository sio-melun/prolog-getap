<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3 class="titre3">Modifier mon profil</h3>

<form:form modelAttribute="formEditProfil" action="doedit"
  method="post">
  <form:errors path="*" cssClass="errors" />

  <div class="section">
    <fieldset>
      <div class="form-row">
        <label for="login">Login :</label>
        <div class="input">
          <form:input path="login" size="50px"/>
        </div>
      </div>

      <div class="form-row">
        <label for="oldPass">Ancien mot de passe :</label>
        <div class="password">
          <form:password path="oldPass" size="50px"/>
        </div>
      </div>

      <div class="form-row">
        <label for="firstPass">Nouveau mot de passe :</label>
        <div class="password">
          <form:password path="firstPass" size="50px"/>
        </div>
      </div>

      <div class="form-row">
        <label for="secondPass">Retaper le nouveau mot de passe :</label>
        <div class="password">
          <form:password path="secondPass" size="50px"/>
        </div>
      </div>
      
      <div class="form-row">
       <label for="mail">E-Mail :</label>
		<div class="input">
          <form:input path="mail" size="75px"/>
        </div>
      </div>
    </fieldset>

    <div id="buttonGroup">
    <a href="<c:url value="/app/profil/index" />"><input type="button" value="Retour">
	</a>
      <input type="submit" value="Sauvegarder" />
    </div>
  </div>
</form:form>