package cn.majin.domain;

/**
 * ��ǰҳ����ǰҳ��Ĵ�С
 * @author majin
 *
 */
public class QueryInfo {
	private int currentPage =1;
	private int pageSize=9;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
