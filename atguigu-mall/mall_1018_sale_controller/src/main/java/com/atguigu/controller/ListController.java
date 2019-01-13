package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.AttrServiceInf;
import com.atguigu.service.ListServiceInf;
import com.atguigu.util.MyCacheUtil;
import com.atguigu.util.MyHttpGetUtil;
import com.atguigu.util.MyJsonUtil;
import com.atguigu.util.MyPropertiesUtil;

@Controller
public class ListController {

	@Autowired
	ListServiceInf listServiceInf;

	@Autowired
	AttrServiceInf attrServiceInf;

	@RequestMapping("keywords")
	public String keywords(String keywords, ModelMap map) {

		// 调用keywords的关键词查询接口
		String doGet = "";
		String keywords_url = MyPropertiesUtil.getMyProperty("ws.properties", "keywords_url") + "?keywords=" + keywords;
		try {
			doGet = MyHttpGetUtil.doGet(keywords_url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<KEYWORDS_T_MALL_SKU> list_sku = MyJsonUtil.json_to_list(doGet, KEYWORDS_T_MALL_SKU.class);
		map.put("list_sku", list_sku);
		map.put("keywords", keywords);
		return "search";
	}

	@RequestMapping("goto_list")
	public String goto_list(int flbh2, ModelMap map) {

		// flbh2属性的集合
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceInf.get_attr_list(flbh2);

		// flbh2商品列表
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();

		// 缓存检索
		String key = "class_2_" + flbh2;
		list_sku = MyCacheUtil.getList(key, OBJECT_T_MALL_SKU.class);

		if (list_sku == null || list_sku.size() < 1) {
			// mysql检索
			list_sku = listServiceInf.get_list_by_flbh2(flbh2);

			// 将检索结果同步到redis
			MyCacheUtil.setKey(key, list_sku);
		}

		map.put("list_attr", list_attr);
		map.put("list_sku", list_sku);
		map.put("flbh2", flbh2);
		return "list";
	}

	@RequestMapping("get_list_by_attr")
	public String get_list_by_attr(MODEL_T_MALL_SKU_ATTR_VALUE list_attr, int flbh2, ModelMap map) {

		// 根据属性查询列表的业务
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		// 缓存检索
		List<T_MALL_SKU_ATTR_VALUE> list_attr2 = list_attr.getList_attr();
		String[] keys = new String[list_attr2.size()];
		for (int i = 0; i < list_attr2.size(); i++) {
			keys[i] = "attr_" + flbh2 + "_" + list_attr2.get(i).getShxm_id() + "_" + list_attr2.get(i).getShxzh_id();
		}

		// 交叉检索，返回生成的key
		String interKeys = MyCacheUtil.interKeys(keys);
		list_sku = MyCacheUtil.getList(interKeys, OBJECT_T_MALL_SKU.class);

		if (list_sku == null || list_sku.size() < 1) {
			// 当前交叉检索结果
			list_sku = listServiceInf.get_list_by_attr(list_attr.getList_attr(), flbh2);

			// 同步redis
			for (int i = 0; i < list_attr2.size(); i++) {
				String key = keys[i];// attr_28_1_2

				// 判断redis中是否存在
				boolean if_key = MyCacheUtil.if_key(key);

				if (!if_key) {
					// 根据属性id，查询出属性值集合
					// 循环属性值，拼接出attr的key
					// key对应的sku集合
					List<T_MALL_SKU_ATTR_VALUE> list_attr_for_redis = new ArrayList<T_MALL_SKU_ATTR_VALUE>();
					List<OBJECT_T_MALL_SKU> list_sku_for_redis = new ArrayList<OBJECT_T_MALL_SKU>();
					T_MALL_SKU_ATTR_VALUE attr_value = new T_MALL_SKU_ATTR_VALUE();
					attr_value.setShxm_id(list_attr2.get(i).getShxm_id());
					attr_value.setShxzh_id(list_attr2.get(i).getShxzh_id());
					list_attr_for_redis.add(attr_value);
					list_sku_for_redis = listServiceInf.get_list_by_attr(list_attr_for_redis, flbh2);

					// 再根据属性和属性值可以查询 出对应的sku集合
					// attr的可以和sku的集合循环 插入到redis
					MyCacheUtil.setKey(key, list_sku_for_redis);
				}

			}
		}

		map.put("list_sku", list_sku);
		return "skuList";
	}

}
