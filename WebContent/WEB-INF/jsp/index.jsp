<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="templates/head.jsp"/>
	<div style="text-align: center">
		<h2>
			Bienvenue sur short url
		</h2>
		<h3>
		Inscription
		</h3>
		<div>
		<span class="errorInscription"></span>
		<form:form modelAttribute="user" method="post" name="inscription" class="inscription">
		  Email : <form:input path="email" class="emailIns"/>
		  Mot de passe : <form:password path="mdp" class="mdpIns"/>
		  Confirmation mot de passe : <form:password path="cmdp" class="cmdpIns"/>
		  <input type="submit" value="Inscription"/>
		</form:form>
		</div>
		<h3>
		Connexion
		</h3>
		<div>
		<span class="errorConnexion"></span>
		<form:form modelAttribute="user" method="post" name="connexion" class="connexion">
		  Email : <form:input path="email" class="emailCo"/>
		  Mot de passe : <form:password path="mdp" class="mdpCo"/>
		  <input type="submit" class="connexion" value="connexion"/>
		</form:form>
		</div>
	</div>
<jsp:include page="templates/footer.jsp"/>
	