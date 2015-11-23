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

<a href="deconnexion">Se deconnecter</a>

<jsp:include page="templates/footer.jsp" />
