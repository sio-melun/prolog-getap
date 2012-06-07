<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h3 class="titre3">Mes classes</h3>

<div id="accordion">
	<c:forEach items="${lesClasses}" var="classe">
		<h3>
			<a href="#"> <b>${classe.nom}</b>
			</a>
		</h3>
		<div id="demo">
			<table class="display dataTable">
				<thead>
					<tr class="header">
						<th>Detail</th>
						<th>Eleves</th>
						<th>Temps Total</th>
						<th>%</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lesEleves}" var="eleve">
						<c:if test="${classe.nom == eleve.classe.nom}">
							<tr>
								<td>
									<a href="<c:url value="/app/prof-principal/detailUser?id=${eleve.id}" />">
										<img src="../../images/detail.png" width="24" height="24"
											onmouseover="this.src='../../images/detailHover.png';"
											onmouseout="this.src='../../images/detail.png';" />
									</a>
								</td>
								<td style="text-align:left;"><a href="<c:url value="/app/admin/detailUser?id=${eleve.id}" />">${eleve.nom} ${eleve.prenom}</a></td>
								<td><fmt:formatNumber
										value="${(eleve.dureeTotal/60)-((eleve.dureeTotal%60)/60)}"
										pattern="#0" />h ${(eleve.dureeTotal%60)}min</td>
								<td><fmt:formatNumber
										value="${(eleve.dureeTotal/(72*60)*100)}"
										pattern="#0.00" />%</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</div>