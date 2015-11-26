package edu.fges.shorturl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.fges.shorturl.domain.Url;
import edu.fges.shorturl.service.UrlServiceInpl;

@Controller
public class CtrlUrl {

	@Autowired
	private UrlServiceInpl urlServiceInpl;

	@RequestMapping(value = "/*")
	public String redirection(@ModelAttribute Url url, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (requestUri.substring(0, 3).equals("/r/")) {
			String uniKeyUrl = requestUri.subSequence(3, requestUri.length()).toString();
			if (uniKeyUrl.length() == 4) {
				String urlBase = urlServiceInpl.getUrlBase(uniKeyUrl);
				if (urlBase != null) {
					return "redirect:" + urlBase;
				}
			}
		}
		return "404";
	}

	@RequestMapping(value = "/accueil")
	public ModelAndView home(@ModelAttribute Url url, HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") == null) {
			return new ModelAndView("redirect:/pages/index");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("urls", urlServiceInpl.listUrlByUser((int) request
				.getSession().getAttribute("idUser")));
		return new ModelAndView("accueil", model);
	}
	


	@RequestMapping(value = "/ajouterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String addUrl(@ModelAttribute Url url, HttpServletRequest request,
			BindingResult results) {
		if (request.getSession().getAttribute("boolConnexion") == null) {
			return "{\"objetResult\": \"redirect\",\"redirect\":  \"/pages/index\" }";
		}
		if (results.hasErrors()) {
			return "{\"objetResult\": \"message\",\"message\":  \"Aucun champ ne doit être vide.\",\"codeError\": 1}";
		}
		if (!urlServiceInpl.urlIsValid(url.getUrlBase())) {
			return "{\"objetResult\": \"message\",\"message\":  \"L'url n'est pas valide.\",\"codeError\": 1}";
		}

		urlServiceInpl.createUniKey(url);
		url.setUrlShort("http://" + request.getHeader("host") + "/r/"
				+ url.getUniKey());
		url.setIdUser((int) request.getSession().getAttribute("idUser"));
		urlServiceInpl.addUrl(url);

		if (url.getId() > 0) {
			return "{\"objetResult\": \"message\",\"message\":  \"Ajout de l'url.\",\"codeError\": 0,\"url\": {\"id\": \""+url.getId()+"\",\"urlShort\": \""+url.getUrlShort()+"\"}}";
		}

		return "{\"objetResult\": \"message\",\"message\":  \"Une erreure c'est produite, vueillez recommencer ou contater un administrateur.\",\"codeError\": 1}";
	}

	
	@RequestMapping(value = "/supprimerUrl")
	@ResponseBody
	public String deleteUrl(HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") == null) {
			return "redirect:/pages/index";
		}
		if(request.getParameter("listUrlDelete") == null){
			return "{\"objetResult\": \"message\",\"message\":  \"Vueillez cocher au moins 1 checkbox.\",\"codeError\": 1}";
		}
		List<Integer> listUrl = urlServiceInpl.convertStringInListInteger(request.getParameter("listUrlDelete"));
		if(listUrl == null){
			return "{\"objetResult\": \"message\",\"message\":  \"Un problème c'est produit vueillez recommencer.\",\"codeError\": 1}";
		}
		
		urlServiceInpl.deleteUrl(listUrl,(int) request.getSession().getAttribute("idUser"));
		
		return "{\"objetResult\": \"message\",\"message\":  \"Supprimer avec success.\",\"codeError\": 0}";
	}
}
