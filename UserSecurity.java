package org.dressTech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dressTech.utils.Security;

@Entity
@Table(name="Users")
public class User {

	@Id
	@Column(name="email")
	private String email;
	
	@Column(name = "pwd", nullable = false)
	private String pwd;
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPwd(String pwd) {
		try {
			this.pwd = Security.HashSHA1(pwd);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
}
