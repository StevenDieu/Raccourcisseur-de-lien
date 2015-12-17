package edu.fges.shorturl.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fges.shorturl.domain.User;
import edu.fges.shorturl.repository.UserRepositoryInpl;

@Service
public class UserServiceInpl implements UserService {

	@Autowired
	private UserRepositoryInpl URSI;

	/**
	 * Create user
	 * 
	 * @param user
	 */
	public void createUser(User user) {
		URSI.saveUser(user);
	}

	/**
	 * Get user
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(int id) {
		return null;
	}

	/**
	 * Check emil is exist
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkUserEmail(String email) {
		return URSI.checkUserEmail(email);
	}

	/**
	 * Check emil with pdw exist
	 * 
	 * @param user
	 * @return
	 */
	public boolean checkUserEmailPwd(User user) {
		return URSI.checkUserEmailPwd(user);
	}

	/**
	 * Get ip address of user
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAdresse(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}

	/**
	 * Check if the mail is compatible
	 * 
	 * @param email
	 * @return
	 */
	public boolean isEmailAdress(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}

}
