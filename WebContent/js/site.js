var waitProgres = true;

function inscription() {
	if ($(".emailIns").val() !== "" && $(".pwdIns").val() !== ""&& $(".cpwdIns").val() !== "") {
		if($(".pwdIns").val() === $(".cpwdIns").val()){
			if(isAdressMail($(".emailIns").val())){
				if($(".pwdIns").val().length >= 6 && $(".pwdIns").val().length <= 254){
		    		waitProgres = false;
					var self = this;
					$.ajax({
						type : "post",
						url : "/pages/inscription",
						data : "email=" + $(".emailIns").val() + "&pwd=" + $(".pwdIns").val()
								+ "&cpwd=" + $(".cpwdIns").val(),
						success : function(t) {
							t = JSON.parse(t);
							if (t.objetResult == "redirect") {
								window.location.href = t.redirect;
							} else if (t.objetResult == "message") {
								$(".errorInscription").html(t.message);
							}
							self.waitProgres = true;
						}
					});
				}else{
					$(".errorInscription").html("Le mot de passe doit \352tre compris entre 6 et 254 carat\350res.");
				}
			}else{
				$(".errorInscription").html("Cette adresse email n'est pas valide.");
			}
		}else{
			$(".errorInscription").html("Les mots de passe ne sont pas identiques.");
		}
	}else{
		$(".errorInscription").html("Aucun champ ne doit \352tre vide.");
	}
}

function connexion() {
	if ($(".emailCo").val() !== "" && $(".pwdCo").val() !== "") {
		waitProgres = false;
		var self = this;
			$.ajax({
				type : "post",
				url : "/pages/connexion",
				data : "email=" + $(".emailCo").val() + "&pwd=" + $(".pwdCo").val(),
				success : function(t) {
					t = JSON.parse(t);
					if (t.objetResult == "redirect") {
						window.location.href = t.redirect;
					} else if (t.objetResult == "message") {
						$(".errorConnexion").html(t.message);
					}
					self.waitProgres = true;
				}
			});
	}else{
		$(".errorConnexion").html("Aucun champ ne doit \352tre vide.");
	}
	return false;
}

function isAdressMail(email){
	var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
	if(reg.test(email)){
		return true;
	}else{
		return false;
	}
}

function addUrl(){
	if($("#urlBase").val() != ""){
		waitProgres = false;
		var self = this;
		$.ajax({
			type : "post",
			url : "/pages/ajouterUrl",
			data : "urlBase=" + $("#urlBase").val(),
			success : function(t) {
				t = JSON.parse(t);
				if (t.objetResult == "redirect") {
					window.location.href = t.redirect;
				} else if (t.objetResult == "message") {
					$(".messsageUrlAjax").html(t.message);
					if(t.codeError === 0){
						$(".tableUrl").append('<tr class="'+t.url.id+'"><td><input type="checkbox"  class="checkboxUrl checkboxUrl'+t.url.id+'" value="' + t.url.id + '"></td><td>' + $("#urlBase").val() + '</td><td><a href="'+ t.url.urlShort + '">' + t.url.urlShort + '</td></tr>');
					    $(".checkboxUrl" + t.url.id).on("click", function(){
					    	setCheckboxAllIfAllCheboxIsChek();
					    });
						$("#urlBase").val("");
					}
				}
				self.waitProgres = true;
			}
		});
	}
}

function deleteUrl(){
	if($(".checkboxUrl").is(":checked")){
		waitProgres = false;
		var self = this;
		var arrayCheckboxChecked = [];
		$(".checkboxUrl").each(function( index ) {
			  if($( this ).is(":checked")){
				  arrayCheckboxChecked.push($( this ).val());
			  }
		});
		$.ajax({
			type : "post",
			url : "/pages/supprimerUrl",
			data : "listUrlDelete=" + arrayCheckboxChecked,
			success : function(t) {
				t = JSON.parse(t);
				if (t.objetResult == "message") {
					$(".messsageUrlAjax").html(t.message);
					if(t.codeError === 0){
						$(".checkboxUrl").each(function( index ) {
							for(var i = 0; i <= arrayCheckboxChecked.length; i++){
								if($(this).val() === arrayCheckboxChecked[i]){
									$(this).parent().parent().remove()
								}
							}
						});
						setAllCheckAfterAllDelete();
					}
				}
				self.waitProgres = true;
			}
		});
	}
}

function setAllCheckAfterAllDelete(){
	if($(".checkboxUrl").length == 0){
		$(".allCheck").prop( "checked", false);
	}
}

function allCheck(){
	$(".checkboxUrl").prop( "checked", $(".allCheck").is(":checked"));
}

function setCheckboxAllIfAllCheboxIsChek(){
	var allIsNotChecked = true;
	$(".checkboxUrl").each(function( index ) {
		if(!$(this).is(":checked")){
			$(".allCheck").prop( "checked", false);
			allIsNotChecked = false;
		}
	});
	if(allIsNotChecked){
		$(".allCheck").prop( "checked", true);
	}
}


$(document).ready(function() {
    $('.inscription').on('submit', function(e) {
    	e.preventDefault();
    	if(waitProgres){
    		inscription();
    	}
	});
    $('.connexion').on('submit', function(e) {
    	e.preventDefault();
    	if(waitProgres){
    		connexion();
    	}
	});
    $('.addUrl').on('submit', function(e) {
    	e.preventDefault();
    	if(waitProgres){
    		addUrl();
    	}
	});
    $(".deleteUrl").on("click", function(){
    	if(waitProgres){
    		deleteUrl();
    	}
    })
    $(".allCheck").on("click", function(){
        allCheck();
    })
    $(".checkboxUrl").on("click", function(){
        setCheckboxAllIfAllCheboxIsChek();
    })
});