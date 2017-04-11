package cn.majin.Web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.ChangeBean;
import cn.majin.domain.Role;
import cn.majin.domain.User;
import cn.majin.serviceImpl.ManageService;

public class ManageServlet extends HttpServlet {
	private ManageService service = new ManageService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String todo = request.getParameter("todo");
			if ("manageUI".equals(todo)) {
				request.getRequestDispatcher("/WEB-INF/jsp/manage/manager.jsp").forward(request, response);
				return;
			} else if ("checkDBchange".equals(todo)) {
				List<ChangeBean> changes = service.getAllChange();
				request.setAttribute("changes", changes);
				request.getRequestDispatcher("/WEB-INF/jsp/manage/showchanges.jsp").forward(request, response);
				return;
			} else if ("listalluser".equals(todo)) {
				List<User> users = service.getAllUser();
				List<String> shutUsername = service.getAllShutUsername();
				request.setAttribute("shutnames", shutUsername);
				request.setAttribute("users", users);
				request.getRequestDispatcher("/WEB-INF/jsp/manage/listallusers.jsp").forward(request, response);
				return;
			} else if ("shouquan".equals(todo)) {
				// 提供授权表单
				String uname = request.getParameter("uname");

				List<Role> allroles = service.getAllRole();
				User u = service.getUserByName(uname);
				request.setAttribute("changeuser", u);
				request.setAttribute("allroles", allroles);

				request.getRequestDispatcher("/WEB-INF/jsp/manage/changePrivilegeForm.jsp").forward(request, response);
				return;
			} else if ("fenghao".equals(todo)) {
				String uname = request.getParameter("uname");
				service.fenghao(uname);
			} else if ("jiefeng".equals(todo)) {
				String uname = request.getParameter("uname");
				service.jiefeng(uname);
			} else {
				request.setAttribute("notice", "非法操作~严重警告~");
				request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
				return;
			}

			request.setAttribute("notice", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "操作失败");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] rids = request.getParameterValues("newrole");
			String username = request.getParameter("username");

			service.setUserRole(username, rids);
			request.setAttribute("notice", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "添加失败");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);

	}

}
