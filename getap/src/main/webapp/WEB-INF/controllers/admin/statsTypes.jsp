<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

Note : des accompagnements personnalisés peuvent être ajoutés par les élèves.

<table class="display dataTable" >
	<thead>
		<tr class=header>
			<th> MATIERE</th>
			<th> DEMANDE D'AP</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${eachType}" var="type">
			<tr>
				<td>${type.libelle}</td>
				<td>${type.countap}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>