<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Welcome in your home !</h1>

<a href="<c:url value="/app/eleve/mesdctap" />">voir mes demandes de consommation de TAP</a><br>
<a href="<c:url value="/app/eleve/ajoutdctap" />">Ajouter une demande de consommation de TAP</a>