package com.atguigu.busi.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BusiController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BusiController.class);

	@RequestMapping("/notifyBusi")
	@ResponseBody
	public String notifyBusi(@RequestParam Map map) {

		String tradeNo = (String) map.get("trade_no");

		logger.info("---------收到订单" + tradeNo + "的通知!!!------------");
		logger.info("---------返回成功标志success给支付模块 ------------");
		return "success";
	}

}
