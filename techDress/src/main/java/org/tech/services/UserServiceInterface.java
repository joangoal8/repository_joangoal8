package org.tech.services;

import org.tech.model.User;
import org.tech.model.Login;

public interface UserServiceInterface {
	
	public void register(User u);
	
	public User validateUser(Login login);
}
