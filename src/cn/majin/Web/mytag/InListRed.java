package cn.majin.Web.mytag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

//<my:MyTag>
//管理界面，对列出的用户名中显示是不是红色的，（是不是被封的号）
public class InListRed extends SimpleTagSupport {
	private List<String> list;
	private String target;

	public void setList(List<String> list) {
		this.list = list;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void doTag() throws JspException, IOException {
		if (list == null || !list.contains(target)) {
			// 封的号中没有这个，输出正常格式
			this.getJspContext().setAttribute("content", target);
		} else {
			// 这个号被封了
			this.getJspContext().setAttribute("content", "<font color='red'>" + target + "</font>");
		}
		
		this.getJspBody().invoke(null);
	}

}
