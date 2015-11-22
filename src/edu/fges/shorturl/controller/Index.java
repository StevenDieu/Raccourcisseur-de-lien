package edu.fges.shorturl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.fges.shorturl.domain.User;
import edu.fges.shorturl.service.UserServiceInpl;

@Controller
public class Index {

	@Autowired
	private UserServiceInpl modelService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@ModelAttribute User user,HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") != null) {
			return "redirect:/pages/accueil";
		}
		
		modelService.update(user);
		return "index";
	}

	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String accueil(HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") != null) {
			return "accueil";
		}
		return "redirect:/pages/index";
	}

}
