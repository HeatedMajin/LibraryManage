package cn.majin.domain;

import java.util.Date;

public class ChangeBean {

	private String op_table;
	private String op_type;
	private String op_user;
	private Date op_date;

	public String getOp_table() {
		return op_table;
	}

	public void setOp_table(String op_table) {
		this.op_table = op_table;
	}

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

	public String getOp_user() {
		return op_user;
	}

	public void setOp_user(String op_user) {
		this.op_user = op_user;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

}
