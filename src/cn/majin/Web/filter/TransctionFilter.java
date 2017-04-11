package cn.majin.Web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.Utils.JdbcUtils;
import edu.fudan.nlp.cn.ner.stringPreHandlingModule;

public class TransctionFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 在dao中获取连接时，开启事务
		chain.doFilter(request, response);
		// if (checkUri(request)) {
		// 提交事务
		JdbcUtils.commitTransction();
		// }
	}

	// 检查uri是不是允许的
	private boolean checkUri(HttpServletRequest request) {
		String allow = "js.css.jpg.png.gif.php";
		String arr[] = allow.split(".");
		String uri = request.getRequestURI();
		for (String a : arr) {
			if (uri.endsWith(a)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {

	}

}
