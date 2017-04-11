package cn.majin.domain;

import java.util.Date;
import java.util.List;

public class User {

	private String username;// --- �û�������
	private String gender;// --- �û����Ա�
	private String password;// --- �û�������
	private String email;// --- �û�������
	private String phone;// ---�û��ĵ绰��
	private Date birthday;// --- �û�������
	private String preference;// --- �û��İ���

	private List<Role> roles;// ---�û�ӵ�еĽ�ɫ

	
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
