package com.atguigu.server;

public class TestServerImp implements TestServerInf {

	@Override
	public String ping(String hello) {
		System.out.println("cxf接口调用:" + hello);
		return "pong";
	}

}
