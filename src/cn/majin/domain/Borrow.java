package cn.majin.domain;

import java.util.Date;

public class Borrow {
	// ��˭�����ˣ����ߵ�ʱ��
	private String borrowedBy = null;
	private Date borrowDate = null;

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getBorrowedBy() {
		if (borrowedBy == null) {
			return null;
		} else {
			return borrowedBy;
		}
	}

	public void setBorrowedBy(String borrowedBy) {
		this.borrowedBy = borrowedBy;
	}
}
