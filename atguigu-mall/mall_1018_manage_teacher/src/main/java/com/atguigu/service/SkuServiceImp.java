package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SkuMapper;

@Service
public class SkuServiceImp implements SkuServiceInf {

	@Autowired
	SkuMapper skuMapper;

	@Override
	public void save_sku(T_MALL_SKU sku, T_MALL_PRODUCT spu, List<T_MALL_SKU_ATTR_VALUE> list_attr) {
		// 保存sku表，返回sku主键
		sku.setShp_id(spu.getId());
		skuMapper.insert_sku(sku);

		// 根据sku主键批量保存属性关联表
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("shp_id", spu.getId());
		map.put("sku_id", sku.getId());
		map.put("list_av", list_attr);
		skuMapper.insert_sku_av(map);

	}

}
