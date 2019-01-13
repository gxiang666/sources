package com.atguigu.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

public class MyCallback implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		// 配置username/password的代码
		WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];

		String user = wsc.getIdentifier();
		String password = "";// 查询数据库或者配置文件

		// 配置私人的加密协议
		if (user.equals("cxf")) {
			password = "123";
		}

		wsc.setPassword(password);

	}

}
