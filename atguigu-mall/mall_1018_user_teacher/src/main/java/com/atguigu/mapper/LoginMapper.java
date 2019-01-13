package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface LoginMapper {

	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);

	void update_test();

}
