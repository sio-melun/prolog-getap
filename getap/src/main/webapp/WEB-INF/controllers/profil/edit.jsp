<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h3 class="titre3">Modifier mon profil</h3>

<form:form modelAttribute="formEditProfil" action="doedit"
  method="post">
  <form:errors path="*" cssClass="errors" />

  <div class="section">
    <fieldset>
      <div class="form-row">
        <label for="login">Login :</label>
        <div class="input">
          <form:input path="login" size="50px" disabled="true"/>
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
      <input type="submit" value="Sauvegarder" />
    </div>
  </div>
</form:form>