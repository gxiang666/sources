package com.atguigu.util;

public class Test {

	public static void main(String[] args) {

		String a = "hello";
		String b = "word";
		String c = (a + b).intern();
		
		System.out.println(c);

	}

}
