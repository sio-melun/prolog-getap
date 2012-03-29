<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<h1>Recherche d'un User</h1>

<form:form modelAttribute="userSearchCriteria" action="dosearchUser"
	method="get">
	<form:errors path="*" cssClass="errors" />
	<fieldset>
		<div class="input">
			<form:input path="query" />
		</div>
	</fieldset>
	<div id="buttonGroup">
		<input type="submit" value="Search" />
	</div>
</form:form>

