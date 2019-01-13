package com.atguigu.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.AddressMapper;

public class AddressServerImp implements AddressServerInf {

	@Autowired
	AddressMapper addressMapper;

	@Override
	public List<T_MALL_ADDRESS> get_addresses(T_MALL_USER_ACCOUNT user) {
		List<T_MALL_ADDRESS> list_address = addressMapper.select_addresses(user);
		return list_address;
	}

	@Override
	public T_MALL_ADDRESS get_address(int address_id) {
		T_MALL_ADDRESS address = addressMapper.select_address(address_id);
		return address;
	}

}
