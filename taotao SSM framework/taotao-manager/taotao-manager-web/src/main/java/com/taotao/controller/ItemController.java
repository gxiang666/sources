package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品查询 Controller
 * @author Daniel
 * @date: 2017年6月19日上午11:03:38
 */

// 一来先加上注解（有四个基本注解，这里加@Service也是可以的，
// @Component，@Reponsitory, @Controller,@Service）
// 这四个功能都是一样的，随便写哪个都行，但是为了区分，
// 我们在会在service层写@Service，controller层写@Controller,dao层写@Repository
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	/*private TbItem getItemById(@PathVariable Long itemId){
		TbItem item=itemService.getItemById(itemId);
		return item;*/
		private TbItem getItemById(@PathVariable Long itemId) {
			TbItem item = itemService.getItemById(itemId);
			return item;
	}
}