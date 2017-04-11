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
	 * @return 未进行页数限定的sql查询
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
		
		//将客户端传来Date格式成与数据库中格式相同
		SimpleDateFormat format = new SimpleDateFormat("dd-M月 -yy");
		if (publishDate1 != null) {// 以某个时间以后的
			sb.append(" publishDate>'"+format.format(publishDate1)+"' and ");
		}
		if (publishDate2 != null) {// 以某个时间之前的
			sb.append(" publishDate<'"+format.format(publishDate2)+"' and ");
		}

		if (description != null && !description.equals("")) {// 判断 简介 中是否含有 输入的内容

		}
		
		String str = sb.toString();
		int index = str.lastIndexOf("and");
		if( -1 == index){//不存在and，即没有设置任何查询条件
			str += " 1=1";//查询全部
		}else{
			//存在and,把最后一个and去掉
			str=str.substring(0,index);
		}
		
		//为了显示的好看，按照ID进行升序排列
		str += " order by id";
		
		return str;
	}
}
