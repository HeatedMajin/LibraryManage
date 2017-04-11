package cn.majin.Web.mytag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.majin.domain.Role;
import cn.majin.domain.User;

public class ShowOrNot extends SimpleTagSupport {
	private String allowRoleName;

	public void setAllowRoleName(String allowRole) {
		this.allowRoleName = allowRole;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// ��ȡ�û�
		PageContext pageContext = (PageContext) this.getJspContext();
		User u = (User) pageContext.getSession().getAttribute("user");

		// �û�û�е�¼������ʾ
		if (u == null) {
			return;
		}
		// �û��Ľ�ɫ��û��Ҫ��Ľ�ɫ������ʾ
		List<Role> roles = u.getRoles();
		if (roles == null) {
			return;
		}
		Iterator<Role> it = roles.iterator();
		while (it.hasNext()) {
			Role r = it.next();
			if (r.getName().equals(allowRoleName)) {
				// �û���ɫ����Ҫ��Ľ�ɫ����ʾ
				this.getJspBody().invoke(null);
				return;
			}
		}
	}

}
