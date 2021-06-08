package it.rdev.rubrica.dto;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 84584015261767167L;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
}
