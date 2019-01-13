package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.ListMapper;

@Service
public class ListServiceImp implements ListServiceInf {

	@Autowired
	ListMapper listMapper;

	@Override
	public List<OBJECT_T_MALL_SKU> get_list_by_flbh2(int flbh2) {
		List<OBJECT_T_MALL_SKU> list_sku = listMapper.select_list_by_flbh2(flbh2);
		return list_sku;
	}

	@Override
	public List<OBJECT_T_MALL_SKU> get_list_by_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2) {

		StringBuffer subSql = new StringBuffer("");

		// 根据属性集合动态拼接条件过滤的sql语句
		subSql.append(" and sku.id in ( select sku0.sku_id from ");

		if (list_attr != null && list_attr.size() > 0) {
			for (int i = 0; i < list_attr.size(); i++) {
				subSql.append(
						" (select sku_id from t_mall_sku_attr_value where shxm_id = " + list_attr.get(i).getShxm_id()
								+ " and shxzh_id = " + list_attr.get(i).getShxzh_id() + ") sku" + i + " ");
				if ((i + 1) < list_attr.size() && list_attr.size() > 1) {
					subSql.append(" , ");
				}
			}

			if (list_attr.size() > 1) {
				subSql.append(" where ");

				for (int i = 0; i < list_attr.size(); i++) {
					if ((i + 1) < list_attr.size()) {
						subSql.append(" sku" + i + ".sku_id=" + "sku" + (i + 1) + ".sku_id");
						
						if(list_attr.size()>2&&i  < (list_attr.size()- 2)){
							subSql.append(" and ");
						}
					}
				}
			}

		}

		subSql.append(" ) ");

		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("flbh2", flbh2);
		hashMap.put("subSql", subSql.toString());

		List<OBJECT_T_MALL_SKU> list_sku = listMapper.select_list_by_attr(hashMap);
		return list_sku;
	}

}
