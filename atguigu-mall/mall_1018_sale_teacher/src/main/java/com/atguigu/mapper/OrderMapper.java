package com.atguigu.mapper;

import java.util.Map;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void insert_order(Map<Object, Object> map);

	void insert_flow(Map<Object, Object> map1);

	void insert_infos(Map<Object, Object> map);

	void update_order(OBJECT_T_MALL_ORDER order);

	void update_flow(OBJECT_T_MALL_FLOW object_T_MALL_FLOW);

	void delete_carts(Map<Object, Object> map_cart);

	long select_count_kc(int sku_id);

	long select_kc(Map<Object, Object> map);

	void update_kc(T_MALL_ORDER_INFO info);

}
