package cn.majin.Web.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

//当字数超过20字以后，隐藏多余的
public class HideMore extends SimpleTagSupport {
	public String content;

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if (content.length() > 26) {
			content = content.substring(0, 25) + "......";
		}
		this.getJspContext().setAttribute("content", content);
		return;
	}
}
