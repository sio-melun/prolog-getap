<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Login</h1>

<form:form modelAttribute="userLoginCriteria" action="authenticate"
	method="post">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			<div class="form-row">
				<label for="login">Identifiant:</label>
				<div class="input">
					<form:input path="login" />
				</div>
			</div>
			<div class="form-row">
				<label for="password">Mot de passe :</label>
				<div class="password">
					<form:password path="password" />
				</div>
			</div>

		</fieldset>
		<div id="buttonGroup">
			<input type="submit" value="Se connecter" />
		</div>
	</div>
</form:form>
