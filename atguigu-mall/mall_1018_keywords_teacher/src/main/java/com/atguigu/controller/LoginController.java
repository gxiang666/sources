package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

@Controller
public class LoginController {

	@RequestMapping(value="login",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String login(MODEL_T_MALL_SKU_ATTR_VALUE list_attr,T_MALL_USER_ACCOUNT user) {
		return "成功";
	}

}
