package org.tech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.tech.dao.UserDAO;
import org.tech.model.Login;
import org.tech.model.User;

public class UserService implements UserServiceInterface{

	@Autowired
	UserDAO userDAO;
	
	public void register(User u) {
		// TODO Auto-generated method stub
		userDAO.save(u);
	}

	public User validateUser(Login login) {
		// TODO Auto-generated method stub	
		return userDAO.mapList().get(login.getEmail());
	}
	
	
}
