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
		// 获取用户
		PageContext pageContext = (PageContext) this.getJspContext();
		User u = (User) pageContext.getSession().getAttribute("user");

		// 用户没有登录，不显示
		if (u == null) {
			return;
		}
		// 用户的角色中没有要求的角色，不显示
		List<Role> roles = u.getRoles();
		if (roles == null) {
			return;
		}
		Iterator<Role> it = roles.iterator();
		while (it.hasNext()) {
			Role r = it.next();
			if (r.getName().equals(allowRoleName)) {
				// 用户角色中有要求的角色，显示
				this.getJspBody().invoke(null);
				return;
			}
		}
	}

}
