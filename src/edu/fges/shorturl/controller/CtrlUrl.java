package edu.fges.shorturl.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fges.shorturl.domain.Url;
import edu.fges.shorturl.service.UrlServiceInpl;

@Controller
public class CtrlUrl {

	@Autowired
	private UrlServiceInpl urlServiceInpl;

	@RequestMapping(value = "/accueil")
	public String home(@ModelAttribute Url url,HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") == null) {
			return "redirect:/pages/index";
		}
		return "accueil";	}
	
	@RequestMapping(value = "/ajouterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String addUrl(@ModelAttribute Url url,HttpServletRequest request, BindingResult results) {
		if (request.getSession().getAttribute("boolConnexion") == null) {
			return "{\"objetResult\": \"redirect\",\"redirect\":  \"/shortUrl/pages/index\" }";
		}
		if (results.hasErrors()) {
			return "{\"objetResult\": \"message\",\"message\":  \"Aucun champ ne doit �tre vide.\",\"codeError\": 0}";
		}
		if(!urlServiceInpl.urlIsValid(url.getUrl_base())){
			return "{\"objetResult\": \"message\",\"message\":  \"L'url n'est pas valide.\",\"codeError\": 0}";
		}
		
		urlServiceInpl.createUniKey(url);
		urlServiceInpl.addUrl(url);
		if(url.getId() > 0){
			return "{\"objetResult\": \"message\",\"message\":  \"Ajout de l'url.\",\"codeSuccess\": 1}";
		}
		
		return "{\"objetResult\": \"message\",\"message\":  \"Une erreure c'est produite, vueillez recommencer ou contater un administrateur.\",\"codeError\": 3}";
	}
	
	@ModelAttribute("urls")
	public List<Url> getUrls() {
		return urlServiceInpl.listUrlByUser();
	}
	

}
