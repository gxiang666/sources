package com.atguigu.test;

public class Test {

	public static void main(String[] args) {
		TestServiceImpService service = new TestServiceImpService();

		TestServiceImp testServiceImpPort = service.getTestServiceImpPort();
	
		String ping = testServiceImpPort.ping("你好");
		
		System.out.println(ping);
	}

}
