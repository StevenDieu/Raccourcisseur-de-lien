<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="edu.fges.shorturl.domain.Url"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="templates/head.jsp" />

<form:form modelAttribute="url" name="addUrl" class="addUrl">
	Coller votre longue url ici : <br />
	<br />

	<form:input path="urlBase" />
	<form:button type="submit" name="Raccourcir">Raccourcir</form:button>
	<br />
	<span class="errorUrl"></span>
</form:form>
<c:set var="listeUrl" value="${urls}" />
<table border="1px">
	<thead>
		<tr>
			<th class="text-left">URL longue</th>
			<th class="text-left">URL courte</th>
		</tr>
	</thead>
	<tbody class="table-hover">
	<c:forEach var="url" items="${listeUrl}">
		<tr class="${url.id}">
			<td><c:out value="${url.urlBase}"/></td>
			<td><a href="${url.urlShort}" target="_BLANK"><c:out value="${url.urlShort}"/></a></td>
		</tr>
	</c:forEach>
</table>
<a href="deconnexion">Se deconnecter</a>

<jsp:include page="templates/footer.jsp" />
