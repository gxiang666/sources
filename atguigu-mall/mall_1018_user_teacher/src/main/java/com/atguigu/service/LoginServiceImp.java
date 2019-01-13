package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.LoginMapper;
import com.atguigu.util.MyRoutingDataSource;

@Service
public class LoginServiceImp implements LoginServiceInf {

	@Autowired
	LoginMapper loginMapper;

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		// 切换为1数据源
		MyRoutingDataSource.setKey("1");

		loginMapper.update_test();

		return loginMapper.select_user(user);
	}

	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		// 切换为2数据源
		MyRoutingDataSource.setKey("2");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loginMapper.select_user(user);
	}

}
