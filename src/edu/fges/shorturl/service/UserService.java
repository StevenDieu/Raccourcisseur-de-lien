package edu.fges.shorturl.service;

import org.springframework.stereotype.Service;

import edu.fges.shorturl.domain.User;

@Service
public interface UserService {
	public User createUser(User user);
	
	public boolean checkUserEmail(String email);

	public boolean checkUserEmailMdp(User user);
	
	public User getUser(int id);
	
	public void removeUser(int id);

	public void update(User model);

}
