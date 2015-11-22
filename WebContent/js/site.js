function inscription() {
	if ($(".emailIns").val() !== "" && $(".mdpIns").val() !== ""&& $(".cmdpIns").val() !== "") {
		if($(".mdpIns").val() === $(".cmdpIns").val()){
			if(isAdressMail($(".emailIns").val())){
				if((".mdpIns").val().length >= 6 && (".mdpIns").val().length <= 254){
					$.ajax({
						type : "post",
						url : "/shortUrl/pages/inscription",
						data : "email=" + $(".emailIns").val() + "&mdp=" + $(".mdpIns").val()
								+ "&cmdp=" + $(".cmdpIns").val(),
						success : function(t) {
							t = JSON.parse(t);
							if (t.objetResult == "redirect") {
								window.location.href = t.redirect;
							} else if (t.objetResult == "message") {
								$(".errorInscription").html(t.message);
							}
						}
					});
				}else{
					$(".errorInscription").html("Le mot de passe doit être compris entre 6 et 254 caratères.");
				}
			}else{
				$(".errorInscription").html("Cette adresse email n'est pas valide.");
			}
		}else{
			$(".errorInscription").html("Les mots de passe ne sont pas identiques.");
		}
	}else{
		$(".errorInscription").html("Aucun champ ne doit être vide.");
	}
}

function connexion() {
	if ($(".emailCo").val() !== "" && $(".mdpCo").val() !== "") {
			$.ajax({
				type : "post",
				url : "/shortUrl/pages/connexion",
				data : "email=" + $(".emailCo").val() + "&mdp=" + $(".mdpCo").val(),
				success : function(t) {
					t = JSON.parse(t);
					if (t.objetResult == "redirect") {
						window.location.href = t.redirect;
					} else if (t.objetResult == "message") {
						$(".errorConnexion").html(t.message);
					}
				}
			});
	}else{
		$(".errorConnexion").html("Aucun champ ne doit être vide.");
	}
}

function isAdressMail(email){
	var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
	if(reg.test(email)){
		return true;
	}else{
		return false;
	}
}

$(document).ready(function() {
	$(".inscription").submit(function() {
		inscription();
		return false;
	});
	$(".connexion").submit(function() {
		connexion();
		return false;
	});
});