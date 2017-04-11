package cn.majin.domain;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;
import cn.majin.serviceImpl.ManageService;

public class Book {

	private String id = null;
	private String name = null;
	private String author = null;
	private Date publishDate = null;
	private String detail = null;

	// 保存借书信息
	private Borrow borrow = null;

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}

	public Book(String id, String name, String author, Date publishDate, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.publishDate = publishDate;
		this.detail = detail;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void checkAndRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BookService bookService = new BookServiceImpl();
		ManageService manageService = new ManageService();

		/* 根据操作类型，决定转发向register.jsp还是UserInfo.jsp */
		String target = "/WEB-INF/jsp/addBook.jsp";

		request.setAttribute("bookBean", this);
		Map<String, String> errors = new HashMap<String, String>();
		/* 检查书的id */
		if (id == null || id.trim().equals("")) {
			errors.put("id", "书本的id不能为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("id", "");
		/* 检查书名 */
		if (name == null || name.trim().equals("")) {
			errors.put("name", "书名不能为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("name", "");
		/* 检查作者 */
		if (author == null || author.equals("")) {
			errors.put("author", "作者不为空");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("author", "");
		/* 检查出版日期 */
		if (publishDate == null) {
			errors.put("publishDate", "日期无效");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("publishDate", "");

		/* 经过上面的检查，书本信息已较完备 */
		try {
			// 向数据库中添加这本书
			bookService.addBook(this);
			//并做记录
			User u = (User) request.getSession().getAttribute("user");
			manageService.updateOp_user(u.getUsername(),name+"("+id+")");
			request.setAttribute("notice", "添加成功");
		} catch (SQLException e) {
			if (e.getMessage().contains("ORA-00001: 违反唯一约束条件")) {
				request.setAttribute("notice", "添加失败，书本的ID不能重复");
			} else {
				e.printStackTrace();
				request.setAttribute("notice", "添加失败~请重试~");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "添加失败~请重试~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
	}

}
