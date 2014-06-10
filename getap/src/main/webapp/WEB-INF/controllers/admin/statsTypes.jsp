<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<br />
<table class="tableStats">
	<tr>
		<td class="tdStatsTitle" colspan="2">Statistiques par AP</td>
	</tr>
	<tr>
		<td class="tdStatsProf">MATIERE</td>
		<td class="tdStatsProf">NOMBRE D'AP</td>
	</tr>
	<c:forEach items="${eachType}" var="type">
		<tr>
			<td class="tdStatsIdentity">${type.libelle}</td>
			<td class="tdStatsProf">${type.countap}</td>
		</tr>
	</c:forEach>
</table>