package cn.majin.domain;

public class LoginFormBean {
	
	private String username;
	private String password;
	private String remember;
	
	public LoginFormBean() {
		super();
	}
	public LoginFormBean(String name, String password, String remember) {
		super();
		this.username = name;
		this.password = password;
		this.remember = remember;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemember() {
		return remember;
	}
	public void setRemember(String remember) {
		this.remember = remember;
	}
	
}
