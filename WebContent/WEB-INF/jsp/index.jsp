<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="../../../css/site.css" rel="stylesheet" type="text/css">
<jsp:include page="templates/head.jsp"/>
		<h2>
			Bienvenue sur short url
		</h2>
	<div class="bloc">
		<h3>
		Inscription
		</h3>
		<div>
		<span class="errorInscription"></span>
		<form:form modelAttribute="user" method="post" name="inscription" class="inscription">
		  <p class="mesP">Email <form:input path="email" class="emailIns les-input"/></p>
		  <p class="mesP">Mot de passe <form:password path="pwd" class="pwdIns les-input"/></p>
		  <p class="mesP">Confirmation mot de passe <form:password path="cpwd" class="cpwdIns les-input"/></p>
		  <p><input type="submit" value="Inscription" class="bouton"/></p>
		</form:form>
		</div>
	</div>
	<div class="bloc">
		<h3>
		Connexion
		</h3>
		<div>
		<span class="errorConnexion"></span>
		<form:form modelAttribute="user" method="post" name="connexion" class="connexion">
		  <p class="mesP">Email <form:input path="email" class="emailCo les-input"/></p>
		  <p class="mesP">Mot de passe <form:password path="pwd" class="pwdCo les-input"/></p>
		  <p><input type="submit" class="connexion bouton" value="connexion"/></p>
		</form:form>
		</div>
	</div>
<jsp:include page="templates/footer.jsp"/>
	