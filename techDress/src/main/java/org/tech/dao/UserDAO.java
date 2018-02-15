package org.tech.dao;

import java.util.Map;

import org.tech.model.User;

public interface UserDAO {

	public void save(User u);
	
	public Map<String,User> mapList();
}
