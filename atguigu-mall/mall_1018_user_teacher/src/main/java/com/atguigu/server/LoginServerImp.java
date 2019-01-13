package com.atguigu.server;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.LoginServiceInf;
import com.atguigu.util.MyRoutingDataSource;
import com.google.gson.Gson;

public class LoginServerImp implements LoginServerInf {

	@Autowired
	LoginServiceInf loginServiceInf;

	@Override
	@Path("login")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String login(@BeanParam T_MALL_USER_ACCOUNT user) {
		MyRoutingDataSource.setKey("2");
		T_MALL_USER_ACCOUNT select_user = loginServiceInf.login(user);

		Gson gson = new Gson();
		return gson.toJson(select_user);
	}

	@Override
	@Path("login2")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String login2(@BeanParam T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT select_user = loginServiceInf.login2(user);

		Gson gson = new Gson();
		return gson.toJson(select_user);
	}

}
