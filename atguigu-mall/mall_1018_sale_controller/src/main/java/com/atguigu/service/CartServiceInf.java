package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface CartServiceInf {

	void add_cart(T_MALL_SHOPPINGCAR cart);

	void update_cart(T_MALL_SHOPPINGCAR cart);

	boolean if_cart_exists(T_MALL_SHOPPINGCAR cart);

	List<T_MALL_SHOPPINGCAR> get_list_cart_by_user(T_MALL_USER_ACCOUNT user);

}
