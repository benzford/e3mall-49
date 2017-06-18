/**
 * 
 */
package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;

/**
 * @author hasee
 *
 */
public interface ContentCatService {
	List<EasyUITreeNode> getContentCatList(long parentId);
}
