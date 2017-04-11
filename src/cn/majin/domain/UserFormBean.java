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

	String username;// --- 用户的名称
	String gender;// --- 用户的性别
	String password;// --- 用户的密码
	String password2;// ---确认密码
	String email;// --- 用户的邮箱
	String phone;// ---用户的电话号
	Date birthday;// --- 用户的生日
	String preference;// --- 用户的爱好

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
		/* 获取当前操作的类型，是注册还是修改 */
		String type = request.getParameter("type");
		/* 根据操作类型，决定转发向register.jsp还是UserInfo.jsp */
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
			errors.put("username", "用户名不能为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("username", "");
		if (gender == null || gender.equals("")) {
			errors.put("gender", "性别不为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("gender", "");
		if (phone == null || !phone.matches("^[1][0-9]{10}$")) {
			errors.put("phone", "手机的格式不正确");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("phone", "");
		if (birthday == null) {
			errors.put("birthday", "日期错误");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("birthday", "");
		if (email == null
				|| !email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")) {
			errors.put("email", "邮箱的格式不正确");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("email", "");

		UserService impl = new UserServiceImpl();

		// 进行修改中户信息操作,不修改password，我们要为password进行赋值
		try {
			User u = (User) request.getSession().getAttribute("user");
			this.password = u.getPassword();
			impl.editUser(this.getUser());
			request.setAttribute("notice", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "修改失败~请重试~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);

	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response, String target)
			throws ServletException, IOException {

		Map errors = new HashMap();
		if (username == null || username.trim().equals("")) {
			errors.put("username", "用户名不能为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("username", "");
		if (password == null || password.trim().equals("")) {
			errors.put("password", "密码不能为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("password", "");
		if (password2 == null || password2.trim().equals("") || !password2.equals(password)) {
			errors.put("password2", "两次输入的密码不相同");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("password2", "");
		if (gender == null || gender.equals("")) {
			errors.put("gender", "性别不为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("gender", "");
		if (phone == null || !phone.matches("^[1][0-9]{10}$")) {
			errors.put("phone", "手机的格式不正确");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("phone", "");
		if (birthday == null) {
			errors.put("birthday", "日期错误");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("birthday", "");
		if (email == null
				|| !email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")) {
			errors.put("email", "邮箱的格式不正确");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("email", "");
		
		UserService impl = new UserServiceImpl();
		// 进行注册操作
		if (!impl.register(getUser())) {
			// 返回false，注册不成功
			errors.put("username", "用户名已经被注册");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("username", "");

		

		/* 将登陆后的用户信息，提取成User对象，便于后面获取用户信息 */
		HttpSession session = request.getSession();
		session.setAttribute("user", this.getUser());

		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

}
