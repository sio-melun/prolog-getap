<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <a href="javascript:if(windows.alert('Utilisateur ajouté.')){window.location.href='admin/index.jsp';}"></a> -->
<script>alert('L\'utilisateur a bien été enregistré\n\nLogin : ${userAjoute.login} \n\nMot de passe :${userAjoute.pass}')</script>
<a href="<c:url value="/app/admin/index" />">Revenir à ma page</a>