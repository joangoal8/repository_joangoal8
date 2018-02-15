package org.tech.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation 6DBTUFBJYRLMWW
 */
@Entity
@Table(name="User")
public class User {
	
	@Id
	@Column(name="EMAIL")
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String LastName;
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setFirstName(String name){
		this.firstName = name;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
}
