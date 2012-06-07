<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<br/>
	<h3 class="titre3">MENU</h3>
<br/>

<div class="centerButton" >
	<a href="<c:url value="/app/eleve/ajoutdctap" />"><img class="creer" src="<c:url value="../../images/IconeDemandesTransparent.png"/>" width="241" height="307"/></a>
	<img class="ligne" src="<c:url value="../../images/ligne.png"/>" width="1" height="300"/>
	<a href="<c:url value="/app/eleve/mesdctap" />"><img class="editer" src="<c:url value="../../images/IconeDemandesTransparent.png"/>" width="241" height="307"/></a>
</div>