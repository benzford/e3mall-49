/**
 * 
 */
package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemCatService;
import cn.e3mall.service.ItemService;

/**
 * @author hasee
 *
 */
@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<EasyUITreeNode> findList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<EasyUITreeNode> treeList=new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			treeList.add(node);
		}
		
		return treeList;
	}

}
