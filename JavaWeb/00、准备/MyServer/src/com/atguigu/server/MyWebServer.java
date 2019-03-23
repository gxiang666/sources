package com.atguigu.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

public class MyWebServer {
	public static void main(String[] args) {
		/*
		 * 1、能够处理浏览器的请求，给浏览器响应
		 * 2、能够将浏览器的请求数据封装为请求数据的对象[面向对象]
		 * 3、服务器还能够根据请求参数给浏览器具体的响应
		 * 4、服务器能够方便的调用其他的java类[服务器提供一个约束-接口 规范，如果实现了此规范的java类，服务器就可以管理它]
		 * 5、服务器得考虑效率问题
		 * */
		try {
			//http://localhost:9999
			// 创建服务器Socket 监听9999端口号
			ServerSocket server = new ServerSocket(9999);
			while (true) {
				// 接收客户端请求
				Socket client = server.accept();
				InputStream is = client.getInputStream();
				int len;
				byte[] b = new byte[1024*10];
				len = is.read(b);
				if(len<0) {
					continue;
				}
				//1、获取浏览器提交数据
				String result = new String(b, 0, len);
				System.out.println("浏览器提交数据：\r\n"+result);
				
				OutputStream os = client.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				
				//2、服务器响应给浏览器的结果
				//浏览器显示内容   一般是html页面
				String response = "<h1>天王盖地虎,degluchenggong</h1>";
				
				bw.write("HTTP/1.1 200 OK \r\n");
				bw.write("Server: ATGUIGU version1.0 OK \r\n");
				bw.write("Content-Type: text/html;charset=utf-8 \r\n");
				bw.write("Content-Length: "+response.getBytes().length+" \r\n");
				bw.write("\r\n");
				bw.write(response);
				bw.flush();
				client.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("服务器异常！！！");
			System.exit(0);
		}
	}
}
