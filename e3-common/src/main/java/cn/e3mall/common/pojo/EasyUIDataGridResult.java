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
public class EasyUIDataGridResult implements Serializable{
	public EasyUIDataGridResult() {
		super();
	}
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public EasyUIDataGridResult(long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	public EasyUIDataGridResult(Long total, List<?> rows) {
		this.total = total.intValue();
		this.rows = rows;
	}
	
}
