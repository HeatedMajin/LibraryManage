package cn.majin.Web.mytag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

//<my:MyTag>
//������棬���г����û�������ʾ�ǲ��Ǻ�ɫ�ģ����ǲ��Ǳ���ĺţ�
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
			// ��ĺ���û����������������ʽ
			this.getJspContext().setAttribute("content", target);
		} else {
			// ����ű�����
			this.getJspContext().setAttribute("content", "<font color='red'>" + target + "</font>");
		}
		
		this.getJspBody().invoke(null);
	}

}
