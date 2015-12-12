<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="edu.fges.shorturl.domain.Url"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="templates/head.jsp" />

<form:form modelAttribute="url" name="addUrl" class="addUrl" style="margin:0 auto;">
	<p class="mesP" style="margin:1em 0">Url à raccourcir : </p>

	<form:input path="urlBase" class="les-input" style="width:30%; height:3.45em; margin-right:-1%;" />
	<form:button type="submit" name="Raccourcir" class="bouton">Raccourcir</form:button>
	<br />
	<span class="messsageUrlAjax"></span>
</form:form>
<c:set var="listeUrl" value="${urls}" />
<table border="1px" class="tableUrl">
	<thead>
		<tr class="header-tab">
			<th class="text-left interne allCheck"><input type="checkbox" value="${url.id}"></th>
			<th class="text-left interne" style="width:68%">URL longue</th>
			<th class="text-left,interne" style="width:30%">URL courte</th>
		</tr>
	</thead>
	<tbody class="table-hover">
	<c:forEach var="url" items="${listeUrl}">
		<tr class="${url.id}">
			<td class="interne"><input type="checkbox"  class="checkboxUrl" value="${url.id}"></td>
			<td class="interne"><c:out value="${url.urlBase}"/></td>
			<td class="interne"><a href="${url.urlShort}" class="lien"><c:out value="${url.urlShort}"/></a></td>
		</tr>
	</c:forEach>
</table>
<input type="button" class="deleteUrl bouton" value="Supprimer"/><br/><br/>
<a href="deconnexion" class="deconnection">Se deconnecter</a>

<jsp:include page="templates/footer.jsp" />
