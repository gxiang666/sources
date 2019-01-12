package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemServcie;
	
	@ResponseBody
	@RequestMapping("/item/{itemId}")
	public TbItem getItemById(@PathVariable("itemId") Long itemId) {
		TbItem item = itemServcie.getItemById(itemId);
		return item;
	}
}
