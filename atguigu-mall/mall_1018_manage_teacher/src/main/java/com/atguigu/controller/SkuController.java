package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.service.AttrServiceInf;
import com.atguigu.service.SkuServiceInf;

@Controller
public class SkuController {

	@Autowired
	AttrServiceInf attrServiceInf;

	@Autowired
	SkuServiceInf skuServiceInf;

	@RequestMapping("save_sku")
	public ModelAndView save_sku(T_MALL_SKU sku, MODEL_T_MALL_SKU_ATTR_VALUE list_attr, T_MALL_PRODUCT spu,
			ModelMap map) {

		skuServiceInf.save_sku(sku, spu, list_attr.getList_attr());

		ModelAndView mv = new ModelAndView("redirect:/goto_sku_add.do");
		mv.addObject("flbh1", spu.getFlbh1());
		mv.addObject("flbh2", spu.getFlbh2());

		return mv;
	}

	@RequestMapping("goto_sku_add")
	public String goto_sku_add(int flbh1, int flbh2, ModelMap map) {

		// 加载属性和属性值列表
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceInf.get_attr_list(flbh2);

		// 传递分类id
		map.put("flbh1", flbh1);
		map.put("flbh2", flbh2);
		map.put("list_attr", list_attr);
		return "skuAdd";
	}

}
