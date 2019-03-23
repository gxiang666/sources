package com.atguigu.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {
	public static void main(String[] args) {
		try {
			Socket client = new Socket("localhost", 9999);
			System.out.println("请选择操作：");
			System.out.println("1、查询：query");
			System.out.println("2、退出：end");
			Scanner scanner = new Scanner(System.in);
			String queryString = null;
			String opration = scanner.nextLine();
			if(opration!=null && opration.equalsIgnoreCase("end")) {
				//结束
				System.out.println("连接已断开");
				client.close();
			}else if(opration!=null && opration.equalsIgnoreCase("query")) {
				System.out.println("请输入要查询的表名：");
				String table = scanner.nextLine();
				System.out.println("请输入要查询的用户名：");
				String username = scanner.nextLine();
				queryString = "opration="+opration+"&table="+table+"&username="+username;
				OutputStream os = client.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				bw.write(queryString);
				bw.newLine();
				bw.flush();
				InputStream is = client.getInputStream();
				int len;
				byte[] b = new byte[1024*10];
				len = is.read(b);
				String result = new String(b, 0, len);
				System.out.println("接收到服务器响应数据："+result);
				client.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
