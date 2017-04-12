package cn.majin.Web.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.User;
import cn.majin.service.UserService;
import cn.majin.serviceImpl.UserServiceImpl;

//�޸��û�������
public class ChangPasswordServlet extends HttpServlet {
	private String changePasswordPage = "/WEB-INF/jsp/user/changePassword.jsp";

	// �ṩ�����޸Ľ���
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(changePasswordPage).forward(request, response);
	}

	// ���ܲ�У�������޸ı�
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// �û���¼���ɵ�¼��Ϣ������session��
			User u = (User) request.getSession().getAttribute("user");
			// �û���������ľ�����
			String password = request.getParameter("oldpassword");
			Map<String, String> p_errors = new HashMap<String, String>();
			if (u == null || !u.getPassword().equals(password)) {
				// �������������
				p_errors.put("oldpassword", "�������������������~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			p_errors.put("oldpassword", "");

			// ��������ȷ�����޸����룬����Ҫ������������������ǲ���һֱ
			String newpassowrd = request.getParameter("newpassword");
			String confirmpassowrd = request.getParameter("confirmpassword");
			if (newpassowrd == null||newpassowrd.equals("")) {
				// ����Ϊ��
				p_errors.put("newpassowrd", "���벻��Ϊ�գ�����������~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			p_errors.put("newpassowrd", "");
			if (confirmpassowrd == null||newpassowrd.equals("")) {
				// ȷ������Ϊ��
				p_errors.put("confirmpassword", "ȷ�����벻��Ϊ�գ�����������~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			if (!newpassowrd.equals(confirmpassowrd)) {
				// ������������벻һ��
				p_errors.put("confirmpassword", "�������벻һ�£�����������~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			p_errors.put("confirmpassword", "");

			// �����޸�����
			UserService service = new UserServiceImpl();
			service.changPassword(u.getUsername(), confirmpassowrd);

			request.setAttribute("notice", "�޸�����ɹ�~");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "�޸�����ʧ��~");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
		}
	}

}
