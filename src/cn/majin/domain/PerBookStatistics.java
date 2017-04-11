package cn.majin.domain;

public class PerBookStatistics {
	private int borrow_count;// 书本被借阅的次数
	private String id; // 书本的id
	private String name;// 书本的名称

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

	public int getBorrow_count() {
		return borrow_count;
	}

	public void setBorrow_count(int borrow_count) {
		this.borrow_count = borrow_count;
	}

}
