package com.atguigu.test;

import javax.xml.ws.Endpoint;

public class TestPublish {

	public static void main(String[] args) {

		TestServiceInf ws = new TestServiceImp();
		
		
		Endpoint.publish("http://192.168.23.100:1234/ws", ws);
	}

}
