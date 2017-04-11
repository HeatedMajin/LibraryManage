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

	// ���������Ϣ
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

		/* ���ݲ������ͣ�����ת����register.jsp����UserInfo.jsp */
		String target = "/WEB-INF/jsp/addBook.jsp";

		request.setAttribute("bookBean", this);
		Map<String, String> errors = new HashMap<String, String>();
		/* ������id */
		if (id == null || id.trim().equals("")) {
			errors.put("id", "�鱾��id����Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("id", "");
		/* ������� */
		if (name == null || name.trim().equals("")) {
			errors.put("name", "��������Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("name", "");
		/* ������� */
		if (author == null || author.equals("")) {
			errors.put("author", "���߲�Ϊ��");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("author", "");
		/* ���������� */
		if (publishDate == null) {
			errors.put("publishDate", "������Ч");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		errors.put("publishDate", "");

		/* ��������ļ�飬�鱾��Ϣ�ѽ��걸 */
		try {
			// �����ݿ�������Ȿ��
			bookService.addBook(this);
			//������¼
			User u = (User) request.getSession().getAttribute("user");
			manageService.updateOp_user(u.getUsername(),name+"("+id+")");
			request.setAttribute("notice", "��ӳɹ�");
		} catch (SQLException e) {
			if (e.getMessage().contains("ORA-00001: Υ��ΨһԼ������")) {
				request.setAttribute("notice", "���ʧ�ܣ��鱾��ID�����ظ�");
			} else {
				e.printStackTrace();
				request.setAttribute("notice", "���ʧ��~������~");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "���ʧ��~������~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
	}

}
