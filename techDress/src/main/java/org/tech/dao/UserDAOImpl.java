package org.tech.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.tech.model.User;

public class UserDAOImpl implements UserDAO{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public void save(User u) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		session.persist(u);
		tx.commit();
		session.close();
		
	}

	public Map<String,User> mapList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		
		List<User> userList = session.createQuery("from User").list();
		
		session.close();
		Map<String,User> mapUser = new HashMap<String, User>();
		
		for (User user: userList){
			mapUser.put(user.getEmail(), user);
		}
		
		return mapUser;
	}
	
}
