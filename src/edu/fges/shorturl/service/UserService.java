package edu.fges.shorturl.service;

import org.springframework.stereotype.Service;

import edu.fges.shorturl.domain.User;

@Service
public interface UserService {
	public void createUser(User user);
	
	public boolean checkUserEmail(String email);

	public boolean checkUserEmailPwd(User user);
	
	public User getUser(int id);
	
	public void removeUser(int id);

}
