package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;


/**
 * 商品查询Service
 *@author Daniel
 *@date: 2017年6月19日上午10:17:09
 */

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired  //注入mapper
	private TbItemMapper itemMapper;
	@Override	
	public TbItem getItemById(Long itemId) {
		//方法一,根据主键查询（这个相对简单，而方法二创建条件查询其实更加的复杂）
		TbItem item=itemMapper.selectByPrimaryKey(itemId);
		
		//方法二：创建查询条件2017-6-19 10:51:25
		/*TbItemExample example=new TbItemExample();
		Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem>list=itemMapper.selectByExample(example);
		//判断list中是否为空
		TbItem item=null;
		if(list !=null&& list.size()>0){
			item=list.get(0);
		}		*/
		return item;
	}

}
