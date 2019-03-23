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

public class MyServer {
	public static void main(String[] args) {
		try {
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
				// 解析客户端数据[end: 断开连接 、 query：查询数据]
				String result = new String(b, 0, len);
				OutputStream os = client.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				if (result != null && result.toLowerCase().contains("query")) {
					// 查询数据 opration=query&table=bs_user&username=admin
					String[] params = result.split("&");
					Map<String, String> paramsMap = new HashMap<String, String>();
					for (String string : params) {
						if (string.toLowerCase().contains("opration")) {
							String opration = string.split("=")[1].trim();
							paramsMap.put("opration", opration);
						} else if (string.toLowerCase().contains("table")) {
							String table = string.split("=")[1].trim();
							paramsMap.put("table", table);
						} else if (string.toLowerCase().contains("username")) {
							String username = string.split("=")[1].trim();
							paramsMap.put("username", username);
						}
					}
					// 调用DAO完成查询操作
					UserDao userDao = new UserDaoImpl();
					User user = userDao.getUserByUsername(paramsMap);
					String data = null;
					if (user == null) {
						data = "没有查询到员工信息！！";
					} else {
						data = user.toString();
					}
					bw.write(data);
					bw.newLine();
				}else {
					bw.write("未知 操作....\r\n");
					bw.newLine();
				}
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
