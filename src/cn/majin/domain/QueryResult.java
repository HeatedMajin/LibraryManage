package cn.majin.domain;

import java.util.List;

/**
 * 查询数据量 和 查询结果
 * @author majin
 *
 */
public class QueryResult {
	
	int totalSize;
	List<Book> context;
	
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public List<Book> getContext() {
		return context;
	}
	public void setContext(List<Book> context) {
		this.context = context;
	}
	
}
