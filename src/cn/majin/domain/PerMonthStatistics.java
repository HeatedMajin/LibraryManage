package cn.majin.domain;

//每个月的借阅量
public class PerMonthStatistics {
	private String month;
	private int borrow_count;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getBorrow_count() {
		return borrow_count;
	}

	public void setBorrow_count(int borrow_count) {
		this.borrow_count = borrow_count;
	}


}
