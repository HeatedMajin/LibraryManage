package cn.majin.Web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import cn.majin.domain.User;

public class GZIPFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		
		User u = (User) request.getSession().getAttribute("user");
		if (u == null && !request.getRequestURI().contains("login.jsp")) {
			// 用户没有登录就进来，让他去登陆
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		MyResponse myresponse = new MyResponse(response);
		chain.doFilter(request, myresponse);

		byte[] bytes = myresponse.getbytes();

		byte[] gzipBytes = GZIP(bytes);

		// 通知浏览器数据的压缩类型
		response.setHeader("content-encoding", "gzip");
		response.setHeader("content-length", gzipBytes.length + "");

		response.getOutputStream().write(gzipBytes);

	}

	// 将byte数组压缩
	private byte[] GZIP(byte[] bytes) throws IOException {
		ByteArrayOutputStream bout = null;

		bout = new ByteArrayOutputStream();
		GZIPOutputStream gzipOutput = new GZIPOutputStream(bout);

		// 会写到底层流中
		gzipOutput.write(bytes);
		gzipOutput.close();

		return bout.toByteArray();
	}

	// 增强response对象，让服务器把数据写到缓冲区中，然后再将缓冲区中的数据压缩后写给客户机
	class MyResponse extends HttpServletResponseWrapper {
		// 记录要增强的变量
		private HttpServletResponse response;
		// 服务器把数据都写到bout中
		private ByteArrayOutputStream bout = new ByteArrayOutputStream();
		private PrintWriter pw;

		public MyResponse(HttpServletResponse response) {
			super(response);
			this.response = response;
		}

		// 获取缓冲区中的存缓
		public byte[] getbytes() {
			try {
				if (pw != null) {
					pw.close();
				}
				return bout.toByteArray();
			} finally {
				try {
					if (bout != null)
						bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			pw = new PrintWriter(new OutputStreamWriter(bout, "UTF-8"));
			return pw;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return new MyOutputStream();
		}

		class MyOutputStream extends ServletOutputStream {
			@Override
			public void write(int b) throws IOException {
				bout.write(b);
			}

		}
	}

}
