<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	
	<script type="text/javascript">
		function envoye() {
			document.getElementById("loader").style.display = "block";
			document.getElementById("loader").style.visibility = "visible";
		}
	</script>
	<h3 class="titre3">Importation d'une liste d'utilisateurs</h3>
	<form:form commandName="formAjoutUsers" enctype="multipart/form-data"
		action="doajouts" method="post">
		<form:errors path="*" cssClass="errors" />

		<div class="section">
			<fieldset>
				<p>
					Ligne type : <strong>Nom;Prénom;idEtab;Division</strong><br><br>
					
					Représentation graphique sous excel (schéma a respecter en pointillés) :<br>
					<table class="stats">
						<tr>
							<td class="ligneTypeExcel"></td>
							<td class="ligneTypeExcel">A</td>
							<td class="ligneTypeExcel">B</td>
							<td class="ligneTypeExcel">C</td>
							<td class="ligneTypeExcel">D</td>
						</tr>
						<tr>
							<td class="ligneTypeExcel">1</td>
							<td class="ligneType">ABADE</td>
							<td class="ligneType">Mathilde</td>
							<td class="ligneType">AZ04005182M</td>
							<td class="ligneType">1CTA1</td>
						</tr>
						<tr>
							<td class="ligneTypeExcel">2</td>
							<td class="ligneType">ABDALLAH</td>
							<td class="ligneType">Florence</td>
							<td class="ligneType">BR06022996Y</td>
							<td class="ligneType">1SCT</td>
						</tr>
					</table>
					/!\ : Le nom de votre fichier déterminera le rôle des utilisateurs envoyés !<br>Pensez donc bien à nommer vos fichiers eleve.csv, prof-principal.csv ou admin.csv. (L'export le fera automatiquement)
				</p>
				<div class="form-row">
					<label for="file">Fichier : </label>
					<div class="input">
						<form:input type="file" path="file" size="75" />
					</div>
					<br>
				</div>
			</fieldset>
			<br>
			<div id="loader">
				<img src="../../images/ajax-loader.gif" alt="chargement"
					width="350px" height="20px" />
			</div>
			<div id="buttonGroup">
				<a href="<c:url value="/app/admin/index" />"><input
					type="button" value="Retour"> </a> <input type="submit"
					value="Ajouter" onclick="envoye()" />
			</div>
		</div>
	</form:form>