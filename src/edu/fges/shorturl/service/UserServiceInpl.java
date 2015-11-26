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

	@Override
	public void createUser(User user) {
		URSI.saveUser(user);
	}

	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public void removeUser(int id) {

	}

	@Override
	public boolean checkUserEmail(String email) {
		return URSI.checkUserEmail(email);
	}

	@Override
	public boolean checkUserEmailPwd(User user) {
		return URSI.checkUserEmailPwd(user);
	}

	public String getIpAdresse(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}

	public boolean isEmailAdress(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}

}
