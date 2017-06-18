/**
 * 
 */
package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Data;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import cn.e3mall.service.JedisClient;

/**
 * @author hasee
 *
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination destination;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Override
	public TbItemDesc findDescById(long id) {
		try {
			String json = jedisClient.get(ITEM_INFO+":"+id+":DESC");
			if(StringUtils.isNotBlank(json)){
				return JsonUtils.jsonToPojo(json, TbItemDesc.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc desc = tbItemDescMapper.selectByPrimaryKey(id);
		try {
			jedisClient.set(ITEM_INFO+":"+id+":DESC", JsonUtils.objectToJson(desc));
			jedisClient.expire(ITEM_INFO+":"+id+":DESC", 3600);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return desc;
	}
	@Override
	public E3Result deleteItems(String ids) {
		String[] idsArr = ids.split(",");
		for (String idString : idsArr) {
			Long id = new Long(idString);
			tbItemMapper.deleteByPrimaryKey(id);
			tbItemDescMapper.deleteByPrimaryKey(id);
		}
		return E3Result.ok();
	}
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public EasyUIDataGridResult qageQuery(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> pageList=new PageInfo<>(list);
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setTotal(pageList.getTotal());
		easyUIDataGridResult.setRows(list);
		return easyUIDataGridResult;
	}
	@Override
	public E3Result addItem(TbItem item, String desc) {
		try {
			final long id = IDUtils.getItemId();
			item.setId(id);
			//'状态。可选值:1(正常),2(删除)',
			item.setStatus((byte) 1);
			Date date = new Date();
			item.setCreated(date);
			item.setUpdated(date);
			tbItemMapper.insert(item);
			TbItemDesc tbItemDesc = new TbItemDesc();
			tbItemDesc.setItemId(id);
			tbItemDesc.setItemDesc(desc);
			tbItemDesc.setCreated(date);
			tbItemDesc.setUpdated(date);
			tbItemDescMapper.insert(tbItemDesc);
			jmsTemplate.send(destination, new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage message = session.createTextMessage(id+"");
					return message;
				}
			});
			return E3Result.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "添加商品失败");
		}
		
	}
	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public TbItem findById(long id) {
		try {
			String json = jedisClient.get(ITEM_INFO+":"+id+":BASE");
			if(StringUtils.isNotBlank(json)){
				return JsonUtils.jsonToPojo(json, TbItem.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItem item = tbItemMapper.selectByPrimaryKey(id);
		try {
			jedisClient.set(ITEM_INFO+":"+id+":BASE", JsonUtils.objectToJson(item));
			jedisClient.expire(ITEM_INFO+":"+id+":BASE", 3600);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	@Override
	public E3Result updateItem(TbItem item, String desc) {
		Long id = item.getId();
		item.setUpdated(new Date());
		tbItemMapper.updateByPrimaryKeySelective(item);
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
		return E3Result.ok();
	}
	@Override
	public E3Result instockItems(String ids) {
		String[] idsArr = ids.split(",");
		for (String idString : idsArr) {
			Long id = new Long(idString);
			TbItem tbItem = new TbItem();
			tbItem.setId(id);
			//'状态。可选值:1(正常),2(删除)',
			tbItem.setStatus((byte) 2);;
			tbItemMapper.updateByPrimaryKeySelective(tbItem);
		}
		return E3Result.ok();
	}
	@Override
	public E3Result reshelfItems(String ids) {
		String[] idsArr = ids.split(",");
		for (String idString : idsArr) {
			Long id = new Long(idString);
			TbItem tbItem = new TbItem();
			tbItem.setId(id);
			//'状态。可选值:1(正常),2(删除)',
			tbItem.setStatus((byte) 1);;
			tbItemMapper.updateByPrimaryKeySelective(tbItem);
		}
		return E3Result.ok();
	}

}
