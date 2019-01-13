package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_SKU;

public interface ListMapper {

	List<OBJECT_T_MALL_SKU> select_list_by_flbh2(int flbh2);

	List<OBJECT_T_MALL_SKU> select_list_by_attr(HashMap<Object, Object> hashMap);

}
