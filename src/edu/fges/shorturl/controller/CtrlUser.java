package edu.fges.shorturl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fges.shorturl.domain.User;
import edu.fges.shorturl.service.UserServiceInpl;

@Controller
public class CtrlUser {

	@Autowired
	private UserServiceInpl userServiceInpl;
	
	@RequestMapping(value = "/r/*")
	public String redirection(@ModelAttribute User user,HttpServletRequest request) {
		return "accueil";
	}
	
	@RequestMapping(value = "/index")
	public String index(@ModelAttribute User user,HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") != null) {
			return "redirect:/pages/accueil";
		}
		return "index";
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	@ResponseBody
	public String signUp(@Valid @ModelAttribute User user, BindingResult results, HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") != null) {
			return "redirect:/pages/accueil";
		}

		if (results.hasErrors()) {
			return "{\"objetResult\": \"message\",\"message\":  \"Aucun champ ne doit être vide.\",\"codeError\": 0}";
		}
		if (!user.getCpwd().equals(user.getPwd())) {
			return "{\"objetResult\": \"message\",\"message\":  \"Les mots de passe ne sont pas identiques.\",\"codeError\": 1}";
		}
		if (!userServiceInpl.isEmailAdress(user.getEmail())) {
			return "{\"objetResult\": \"message\",\"message\":  \"Cette adresse email n'est pas valide.\",\"codeError\": 2}";
		}
		if (userServiceInpl.checkUserEmail(user.getEmail())) {
			return "{\"objetResult\": \"message\",\"message\":  \"Cette adresse email existe déja.\",\"codeError\": 2}";
		}
		if (user.getPwd().length() <= 6 && user.getPwd().length() >= 254) {
			return "{\"objetResult\": \"message\",\"message\":  \"Le mot de passe doit être compris entre 6 et 254 caratères.\",\"codeError\": 2}";
		}

		user.setIp(userServiceInpl.getIpAdresse(request));
		userServiceInpl.createUser(user);

		if (user.getId() > 0) {
			setSessionUser(user, request);

			return "{\"objetResult\": \"redirect\",\"redirect\":  \"/pages/accueil\" }";
		}

		return "{\"objetResult\": \"message\",\"message\":  \"Une erreure c'est produite, vueillez recommencer ou contater un administrateur.\",\"codeError\": 3}";
	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	@ResponseBody
	public String signIn(@Valid @ModelAttribute User user, BindingResult results, HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") != null) {
			return "redirect:/pages/accueil";
		}

		if (results.hasErrors()) {
			return "{\"objetResult\": \"message\",\"message\":  \"Aucun champ ne doit être vide.\",\"codeError\": 0}";
		}
		if (!userServiceInpl.checkUserEmail(user.getEmail())) {
			return "{\"objetResult\": \"message\",\"message\":  \"Cette adresse email n'existe pas.\",\"codeError\": 2}";
		}

		if (userServiceInpl.checkUserEmailPwd(user)) {
			setSessionUser(user, request);

			return "{\"objetResult\": \"redirect\",\"redirect\":  \"/pages/accueil\" }";
		}
		return "{\"objetResult\": \"message\",\"message\":  \"Le mot de passe est incorrect.\",\"codeError\": 2}";

	}

	@RequestMapping(value = "/deconnexion")
	public String signOut(HttpServletRequest request) {
		if (request.getSession().getAttribute("boolConnexion") != null) {
			request.getSession().setAttribute("boolConnexion", null);
			request.getSession().setAttribute("idUser", null);
			request.getSession().setAttribute("emailUser", null);
			request.getSession().setAttribute("user", null);

		}
		return "redirect:/pages/index";
	}

	private void setSessionUser(User user, HttpServletRequest request) {
		request.getSession().setAttribute("boolConnexion", true);
		request.getSession().setAttribute("idUser", user.getId());
		request.getSession().setAttribute("emailUser", user.getEmail());
		request.getSession().setAttribute("user", user);
	}

}
