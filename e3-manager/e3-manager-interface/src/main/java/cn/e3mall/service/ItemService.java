/**
 * 
 */
package cn.e3mall.service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

/**
 * @author hasee
 *
 */
public interface ItemService {
	TbItem findById(long id);
	EasyUIDataGridResult qageQuery(int page,int rows);
	E3Result addItem(TbItem item,String desc);
	TbItemDesc findDescById(long id);
	E3Result updateItem(TbItem item,String desc);
	E3Result deleteItems(String ids);
	E3Result instockItems(String ids);
	/**
	* <b>Description:</b><br> 
	* @param
	* @return E3Result
	* @param ids
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月2日 下午12:26:46
	* <br><b>Version:</b> 1.0
	*/
	E3Result reshelfItems(String ids);
}
