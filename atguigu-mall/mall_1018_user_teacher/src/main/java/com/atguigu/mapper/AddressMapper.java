package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface AddressMapper {

	List<T_MALL_ADDRESS> select_addresses(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS select_address(int address_id);

}
