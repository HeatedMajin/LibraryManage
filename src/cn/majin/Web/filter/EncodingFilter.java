package cn.majin.Web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.User;

public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		request.setCharacterEncoding("UTF-8");

		chain.doFilter(new MyRequest(request), response);

		// ����д������ʱ�ı���
		response.setCharacterEncoding("UTF-8");
		// �����������ʱ�ģ����뷽ʽ
		response.setHeader("content-type", "text/html;charset=UTF-8");
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	// ��ǿrequest����
	class MyRequest extends HttpServletRequestWrapper {

		private HttpServletRequest request;

		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public String getParameter(String name) {
			try {

				String value = this.request.getParameter(name);
				if(value==null){
					return null;
				}
				// ʹ�õ���get��ʽ�ύ������
				if ("GET".equals(request.getMethod())) {
					value = new String(value.getBytes("iso8859-1"), "UTF-8");
				}
				return value;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}
}
