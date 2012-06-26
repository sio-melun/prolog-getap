<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	function testMdp(img1, img2) {
		if (document.getElementById('firstPass').value == document
				.getElementById('secondPass').value) {
			document.getElementById(img1).style.display = "inline";
			document.getElementById(img2).style.display = "none";
		} else {
			document.getElementById(img1).style.display = "none";
			document.getElementById(img2).style.display = "inline";
		}
	}
	
	function testEmail(){
		var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
		var email = document.getElementById('mail').value;
		
		if(reg.test(email))
		{
			document.getElementById('bon').style.display = "inline";
			document.getElementById('mauvais').style.display = "none";
		}
		else
		{
			document.getElementById('bon').style.display = "none";
			document.getElementById('mauvais').style.display = "inline";
		}
	}
</script>

<h3 class="titre3">Modifier mon profil</h3>

<form:form modelAttribute="formEditProfil" action="doedit" method="post">
	<form:errors path="*" cssClass="errors" />

	<div class="section">
		<fieldset>
			<div class="form-row">
				<label for="login">Login :</label>
				<div class="input">
					<form:input path="login" size="50px" disabled="true" />
				</div>
			</div>

			<div class="form-row">
				<label for="oldPass">Ancien mot de passe :</label>
				<div class="password">
					<form:password path="oldPass" size="50px" onclick="test()" />
				</div>
			</div>

			<div class="form-row">
				<label for="firstPass">Nouveau mot de passe :</label>
				<div class="password">
					<form:password path="firstPass" size="50px" />
				</div>
			</div>

			<div class="form-row">
				<label for="secondPass">Retaper le nouveau mot de passe :</label>
				<div class="password">
					<form:password path="secondPass" size="50px"
						onKeydown="testMdp('valide', 'invalide')" />
					<img src="../../images/valid.png" width="15" height="15"
						id="valide" style="display: none;" /><img
						src="../../images/suppr.png" width="15" height="15" id="invalide"
						style="display: none;" />
				</div>
			</div>

			<div class="form-row">
				<label for="mail">E-Mail :</label>
				<div class="input">
					<form:input path="mail" size="75px" onKeydown="testEmail()"/>
					<img src="../../images/valid.png" width="15" height="15"
						id="bon" style="display: none;" /><img
						src="../../images/suppr.png" width="15" height="15" id="mauvais"
						style="display: none;" />
				</div>
			</div>
		</fieldset>

		<div id="buttonGroup">
			<input type="submit" value="Sauvegarder" />
		</div>
	</div>
</form:form>