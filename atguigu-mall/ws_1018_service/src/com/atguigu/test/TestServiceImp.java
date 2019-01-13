package com.atguigu.test;

import javax.jws.WebService;

/**
 * TestServiceImp(1).java
 * @author 熊高祥
 *
 */
@WebService
public class TestServiceImp implements TestServiceInf {

	@Override
	public String ping(String hello) {
		System.out.println("接口调用：" + hello);
		return "pong";
	}

}
