<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="templates/head.jsp" />

<form:form modelAttribute="url" name="addUrl" class="addUrl">
	Coller votre longue url ici : <br />
	<br />

	<form:input path="url_base" />
	<form:button type="submit" name="Raccourcir">Raccourcir</form:button>
	<br />
	<span class="errorUrl"></span>
</form:form>

<c:set var="listeUrl" value="${urls}" />
<c:forEach var="url" items="${listeUrl}">
	<tr class="${url.id}">
		<td><c:out value="${url.url_base}"/></td>
		<td><c:out value="${url.url_short}"/></td>
	</tr>
</c:forEach>
<a href="deconnexion">Se deconnecter</a>

<jsp:include page="templates/footer.jsp" />
