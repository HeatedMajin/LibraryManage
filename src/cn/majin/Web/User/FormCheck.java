package cn.majin.Web.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.Utils.WebUtils;
import cn.majin.domain.UserFormBean;

/**
 * 用户注册时，表单的校验
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
		 * 将request中的paramter封装到formBean中， 并调用formBean的注册方法，注册这个表单
		 * 
		 */
		UserFormBean form = WebUtils.request2bean(request, UserFormBean.class);

		form.register(request, response);
	}
}
