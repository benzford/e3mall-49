/**
 * 
 */
package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author hasee
 *
 */
public class SearchResult implements Serializable{
	private List<SearchItem> searchItems;
	private int totalPages;
	private long recourdCount;
	public List<SearchItem> getSearchItems() {
		return searchItems;
	}
	public void setSearchItems(List<SearchItem> searchItems) {
		this.searchItems = searchItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getRecourdCount() {
		return recourdCount;
	}
	public void setRecourdCount(long recourdCount) {
		this.recourdCount = recourdCount;
	}
}