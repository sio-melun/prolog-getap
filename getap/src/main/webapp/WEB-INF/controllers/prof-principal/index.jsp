<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h3 class="titre3">Mes classes</h3>

<div id="accordion">
	<c:forEach items="${lesClasses}" var="classe">
		<h3>
			<a href="#"> <b>${classe.nom}</b> </a>
		</h3>
		<div id="demo">
			<table class="display dataTable">
				<thead>
					<tr class="header">
						<th>Detail</th>
						<th>Élèves</th>
						<th>Temps Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lesEleves}" var="eleve">
						<c:if test="${classe.nom == eleve.classe.nom}">
							<c:set value="" var="classPercentZero" />
							<c:if test="${eleve.dureeTotal == 0 }">
								<c:set value="percentZero" var="classPercentZero" />
							</c:if>
							<tr>
								<td class="${classPercentZero}"><a
									href="<c:url value="/app/prof-principal/detailUser?id=${eleve.id}" />">
										<img src="../../images/detail.png" width="24" height="24"
										onmouseover="this.src='../../images/detailHover.png';"
										onmouseout="this.src='../../images/detail.png';" /> </a></td>
								<td class="${classPercentZero}" style="text-align:left;"><a href="<c:url value="/app/admin/detailUser?id=${eleve.id}" />">${eleve.nom}
									${eleve.prenom}</a></td>
								<td class="${classPercentZero}"><fmt:formatNumber
										value="${(eleve.dureeTotal/60)-((eleve.dureeTotal%60)/60)}"
										pattern="#00" />h<fmt:formatNumber
										value="${eleve.dureeTotal%60}" pattern="#00" /> (<fmt:formatNumber
										value="${(eleve.dureeTotal/(72*60)*100)}" pattern="#00.00" />%)</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</div>
