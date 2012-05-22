<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<form:form modelAttribute="formAjoutDctap" action="doajout"
         method="post" id="formulaireAjoutDCTAP">
         <form:errors path="*" cssClass="errors" />

         <div class="section">
                  <fieldset>
                           <div class="form-row">
                                    <div class="input">
                                             <label for="anneeScolaire">Année scolaire courante :</label>
                                             <form:input path="anneeScolaire" disabled="true" />
                                    </div>
                           </div>

                           <br />


                           <div class="form-row">
                                    <label for="datepicker">Date : </label>
                                    <div class="input">
                                             <form:input id="datepicker" path="date" />
                                    </div>
                           </div>

                           <br />

                           <div class="form-row">
                                    <label for="minutes">Temps d'aide personnalisée (minutes) :
                                    </label>
                                    <div class="input">
                                             <form:input path="minutes" />
                                    </div>
                           </div>

                           <br />


                           <div class="form-row">
                                    <label for="profs">Les professeurs :</label>
                                    <form:select path="profId" items="${lesProfs}" itemValue="id"
                                             itemLabel="nom"></form:select>
                           </div>

                           <br />

                           <div class="form-row">
                                    <label for="accPers">Type d'aide personnalisée : </label>
                                    <select id="accPersId" name="accPersId" onchange="testAcc()">
                                             <c:forEach items="${lesAP}" var="ap">
                                                      <option value="${ap.id}" label="${ap.nom}">${ap.nom}</option>
                                             </c:forEach>
                                             <option value="0" label="Autre">Autre</option>
                                    </select>
                           </div>
                           <br />
                           
                           <div class="from-row" id="inputAcc" style="display: none;visibility: hidden;">
                                    <label for="accPersNom">Aide personnalisée : </label><br>
                                    <form:input path="accPersNom"/>
                           </div>
                  </fieldset>

                  <form:hidden path="eleveId" />
                  <form:hidden path="etat" />

                  <br />

                  <div id="buttonGroup">
                           <a href="index" style="text-decoration: none"><input
                                    type="button" value="Retour"></a> <input type="submit"
                                    value="Ajouter" />

                  </div>
         </div>
</form:form>
