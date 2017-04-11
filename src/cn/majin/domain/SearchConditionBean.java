package cn.majin.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.soap.Detail;

public class SearchConditionBean {

	private String id;
	private String name;
	private String author;
	private String description;
	private Date publishDate1;
	private Date publishDate2;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate1() {
		return publishDate1;
	}

	public void setPublishDate1(Date publishDate1) {
		this.publishDate1 = publishDate1;
	}

	public Date getPublishDate2() {
		return publishDate2;
	}

	public void setPublishDate2(Date publishDate2) {
		this.publishDate2 = publishDate2;
	}

	/**
	 * 
	 * @param info QueryInfo
	 * @return δ����ҳ���޶���sql��ѯ
	 */
	public String generateSQL() {

		StringBuffer sb = new StringBuffer();
		sb.append("select id,name,author,detail,publishDate from book where");
		if (id != null && !id.equals(""))
			sb.append(" id='" + id + "' and ");
		if (name != null && !name.equals(""))
			sb.append(" name='" + name + "' and ");
		if (author != null && !author.equals(""))
			sb.append(" author='" + author + "' and ");
		
		//���ͻ��˴���Date��ʽ�������ݿ��и�ʽ��ͬ
		SimpleDateFormat format = new SimpleDateFormat("dd-M�� -yy");
		if (publishDate1 != null) {// ��ĳ��ʱ���Ժ��
			sb.append(" publishDate>'"+format.format(publishDate1)+"' and ");
		}
		if (publishDate2 != null) {// ��ĳ��ʱ��֮ǰ��
			sb.append(" publishDate<'"+format.format(publishDate2)+"' and ");
		}

		if (description != null && !description.equals("")) {// �ж� ��� ���Ƿ��� ���������

		}
		
		String str = sb.toString();
		int index = str.lastIndexOf("and");
		if( -1 == index){//������and����û�������κβ�ѯ����
			str += " 1=1";//��ѯȫ��
		}else{
			//����and,�����һ��andȥ��
			str=str.substring(0,index);
		}
		
		//Ϊ����ʾ�ĺÿ�������ID������������
		str += " order by id";
		
		return str;
	}
}
