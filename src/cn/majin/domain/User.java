package cn.majin.domain;

import java.util.Date;
import java.util.List;

public class User {

	private String username;// --- 用户的名称
	private String gender;// --- 用户的性别
	private String password;// --- 用户的密码
	private String email;// --- 用户的邮箱
	private String phone;// ---用户的电话号
	private Date birthday;// --- 用户的生日
	private String preference;// --- 用户的爱好

	private List<Role> roles;// ---用户拥有的角色

	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User() {
		super();
	}

	public User(String username, String gender, String password, String email, String phone, Date birthday,
			String preference) {
		super();
		this.username = username;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.preference = preference;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

}
