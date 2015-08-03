package by.academy.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String login;
	private String pass;
	private List<Role> roles = new ArrayList<Role>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role){
		roles.add(role);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", pass=" + pass + ", roles=" + roles + "]";
	}
	
	
}
