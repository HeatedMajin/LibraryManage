package cn.majin.domain;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.majin.service.UserService;
import cn.majin.serviceImpl.UserServiceImpl;

public class UserFormBean {

	String username;// --- �û�������
	String gender;// --- �û����Ա�
	String password;// --- �û�������
	String password2;// ---ȷ������
	String email;// --- �û�������
	String phone;// ---�û��ĵ绰��
	Date birthday;// --- �û�������
	String preference;// --- �û��İ���

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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
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

	public User getUser() {
		User u = new User();
		u.setUsername(username);
		u.setGender(gender);
		u.setPassword(password);
		u.setBirthday(birthday);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPreference(preference);
		return u;
	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* ��ȡ��ǰ���������ͣ���ע�ỹ���޸� */
		String type = request.getParameter("type");
		/* ���ݲ������ͣ�����ת����register.jsp����UserInfo.jsp */
		String target = type.equals("edit") ? "/WEB-INF/jsp/EditUserInfo.jsp" : "/WEB-INF/jsp/register.jsp";

		request.setAttribute("form", this);
		if (type.equals("edit")) {
			doEdit(request, response, target);
			return;
		} else {
			doRegister(request, response, target);
			return;
		}
	}

	private void doEdit(HttpServletRequest request, HttpServletResponse response, String target)
			throws ServletException, IOException {

		Map errors = new HashMap();
		if (username == null || username.trim().equals("")) {
			errors.put("username", "�û�������Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("username", "");
		if (gender == null || gender.equals("")) {
			errors.put("gender", "�Ա�Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("gender", "");
		if (phone == null || !phone.matches("^[1][0-9]{10}$")) {
			errors.put("phone", "�ֻ��ĸ�ʽ����ȷ");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("phone", "");
		if (birthday == null) {
			errors.put("birthday", "���ڴ���");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("birthday", "");
		if (email == null
				|| !email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")) {
			errors.put("email", "����ĸ�ʽ����ȷ");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("email", "");

		UserService impl = new UserServiceImpl();

		// �����޸��л���Ϣ����,���޸�password������ҪΪpassword���и�ֵ
		try {
			User u = (User) request.getSession().getAttribute("user");
			this.password = u.getPassword();
			impl.editUser(this.getUser());
			request.setAttribute("notice", "�޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "�޸�ʧ��~������~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);

	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response, String target)
			throws ServletException, IOException {

		Map errors = new HashMap();
		if (username == null || username.trim().equals("")) {
			errors.put("username", "�û�������Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("username", "");
		if (password == null || password.trim().equals("")) {
			errors.put("password", "���벻��Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("password", "");
		if (password2 == null || password2.trim().equals("") || !password2.equals(password)) {
			errors.put("password2", "������������벻��ͬ");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("password2", "");
		if (gender == null || gender.equals("")) {
			errors.put("gender", "�Ա�Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("gender", "");
		if (phone == null || !phone.matches("^[1][0-9]{10}$")) {
			errors.put("phone", "�ֻ��ĸ�ʽ����ȷ");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("phone", "");
		if (birthday == null) {
			errors.put("birthday", "���ڴ���");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("birthday", "");
		if (email == null
				|| !email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")) {
			errors.put("email", "����ĸ�ʽ����ȷ");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("email", "");
		
		UserService impl = new UserServiceImpl();
		// ����ע�����
		if (!impl.register(getUser())) {
			// ����false��ע�᲻�ɹ�
			errors.put("username", "�û����Ѿ���ע��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("username", "");

		

		/* ����½����û���Ϣ����ȡ��User���󣬱��ں����ȡ�û���Ϣ */
		HttpSession session = request.getSession();
		session.setAttribute("user", this.getUser());

		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

}
