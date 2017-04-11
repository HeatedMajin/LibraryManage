package cn.majin.domain;

import java.util.List;

/**
 * 提供分页显示的基本数据 总的数据数、总的页数、每页的数据量、每页的数据内容 上一页、当前页、下一页、临近的8页
 * 
 * @author majin
 *
 */
public class PageBean {
	int totalSize;
	List<Book> context;
	int totalPage;
	int pageSize;
	int currentPage;
	int previousPage;
	int nextPage;
	int[] p;

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

	public int getTotalPage() {
		// 100 / 5 = 20
		// 99 / 5 = 19 还要 +1
		// 101 / 5 = 20 还要+1
		if (0 == totalSize % pageSize) {
			totalPage = totalSize / pageSize;
		} else {
			totalPage = totalSize / pageSize + 1;
		}
		return totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPreviousPage() {
		if (currentPage <= 1) {
			previousPage = 1;
		} else {
			previousPage = currentPage - 1;
		}

		return previousPage;
	}

	public int getNextPage() {
		if (currentPage >= this.getTotalPage()) {
			nextPage = totalPage;
		} else {
			nextPage = currentPage + 1;
		}
		return nextPage;
	}

	public int[] getP() {
		if (getTotalPage() < 9) {
			p = new int[totalPage];
			for (int i = 0; i < totalPage; i++) {
				p[i] = i + 1;
			}
		} else {
			p = new int[9];
			int start;
			int end;
			if (currentPage - 4 < 1) {
				start = 1;
			} else {
				if ((totalPage - currentPage) < 4) {
					start = totalPage - 8;
				} else {
					start = currentPage - 4;
				}
			}

			if (currentPage + 4 > getTotalPage()) {
				end = totalPage;
			} else {
				if (currentPage < 5) {
					end = 9;
				} else {
					end = currentPage + 4;
				}
			}
			int index = 0;
			for (int i = start; i <= end; i++) {
				p[index++] = i;
			}
		}
		return p;
	}

}
