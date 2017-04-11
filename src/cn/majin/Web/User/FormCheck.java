package cn.majin.Web.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.Utils.WebUtils;
import cn.majin.domain.UserFormBean;

/**
 * �û�ע��ʱ������У��
 * @author majin
 *
 */
public class FormCheck extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *
		 * ��request�е�paramter��װ��formBean�У� ������formBean��ע�᷽����ע�������
		 * 
		 */
		UserFormBean form = WebUtils.request2bean(request, UserFormBean.class);

		form.register(request, response);
	}
}
