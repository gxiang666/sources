package com.atguigu.controller;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.PaymentStatus;
import com.alipay.util.UtilDate;
import com.atguigu.entity.Payment;
import com.atguigu.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PaymentController.class);

	// 支付模块处理用户发起的支付请求
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String doPayment(@RequestParam Map<String, String> paraMap, Model model,
			@RequestHeader("referer") String origin_url, HttpServletRequest request) {

		// 1、检查必填项

		List<String> errlist = AlipaySubmit.validOutParams(paraMap);
		if (!errlist.isEmpty()) {
			model.addAttribute("msg", Arrays.toString(errlist.toArray()));
			logger.info(origin_url);
			return "error";
		}

		// 2、检查该笔业务是否已经完成，幂等性原则
		Payment originPayment = paymentService.getPaymentByTradeNo(paraMap.get("trade_no"));
		if (originPayment != null && (originPayment.getStatus() != PaymentStatus.SUBMITED)) {
			model.addAttribute("msg", "该笔订单已存在业务");
			logger.info(origin_url);
			return "error";
		}

		// 3、整合默认参数
		StringBuilder url = new StringBuilder(AlipayConfig.ALIPAY_GATEWAY_NEW);
		Map<String, String> submitMap = new HashMap<String, String>();
		submitMap.put("out_trade_no", paraMap.get("trade_no"));
		submitMap.put("total_fee", paraMap.get("total_fee"));
		submitMap.put("subject", paraMap.get("subject"));
		submitMap.put("body", paraMap.get("body"));
		submitMap.put("service", AlipayConfig.service);
		submitMap.put("partner", AlipayConfig.partner);
		submitMap.put("seller_id", AlipayConfig.seller_id);
		submitMap.put("_input_charset", AlipayConfig.input_charset);
		submitMap.put("payment_type", AlipayConfig.payment_type);
		submitMap.put("notify_url", AlipayConfig.notify_url);
		submitMap.put("return_url", AlipayConfig.return_url);

		// 4、制作签名
		submitMap = AlipaySubmit.buildRequestPara(submitMap);

		// 5、保存业务参数
		if (originPayment == null) {
			Payment payment = new Payment();
			payment.setTradeNo(paraMap.get("trade_no"));
			payment.setTotalFee(new BigDecimal(paraMap.get("total_fee")));
			payment.setBody(paraMap.get("body"));
			payment.setSubject(paraMap.get("subject"));
			payment.setBusiNotifyUrl(paraMap.get("busi_notify_url"));
			payment.setBusiReturnUrl(paraMap.get("busi_return_url"));
			payment.setSubmitUrl(url.toString() + StringUtils.replaceChars(submitMap.toString(), ", {}", "&"));
			payment.setStatus(PaymentStatus.SUBMITED);
			payment.setSubmitTime(UtilDate.getDateFormatter());

			paymentService.addPayment(payment);
		}

		// 6、重定向客户端到支付宝
		model.addAttribute("URL", url);
		model.addAttribute("METHOD", "get");
		model.addAttribute("MAP", submitMap);

		return "submit";

	}

	// 支付服务(payment)和支付平台(支付宝)生成重定向地址给浏览器客户端
	// 支付后同步回调
	@RequestMapping(value = "/pay_return", method = RequestMethod.GET)
	public String payReturn(@RequestParam Map<String, String> paraMap, Model model, HttpServletRequest request) {
		logger.info("----------------------------");
		logger.info("-------前台收到支付宝通知---------");
		logger.info("----------------------------");
		Payment payment = paymentService.getPaymentByTradeNo(paraMap.get("out_trade_no"));

		if(1==1){
			model.addAttribute("busi_return_url",
					payment.getBusiReturnUrl() + "?" + "trade_no=" + payment.getTradeNo());
			return "paysuccess";
		}
		// 1、检查参数
		List<String> errlist = AlipayNotify.validInParams(paraMap);
		if (!errlist.isEmpty()) {
			// 错误流水保存到日志
			logger.info("err:" + errlist.toString());
			model.addAttribute("msg", Arrays.toString(errlist.toArray()));
			return "error";
		}
		logger.info("----------------------------");
		logger.info("-------检查参数完成-------------");
		logger.info("----------------------------");

		// 2、检查签名
		logger.info("-------开始验证签名-------------");
		boolean verify_result = AlipayNotify.verify(paraMap);
		// 测试用过签名验证
		if ("1".equals(paraMap.get("istest"))) {
			verify_result = true;
		}
		if (!verify_result) {
			// 错误流水保存到日志
			logger.info("err:" + errlist.toString());

			model.addAttribute("msg", "验证失败！");
			return "error";
		}
		logger.info("--------2、检查签名完成----------");

		logger.info("--------3、 查询单据是否存在----------");
		// 查询单据是否存在
		if (payment == null) {
			model.addAttribute("msg", "该单据不存在");
			return "error";
		}

		logger.info("--------4、 查询单据是否支付成功----------");
		String trade_status = paraMap.get("trade_status");
		if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
			logger.info("--------5、 根据状态处理单据----------");
			// 根据单据状态进行处理 ： 判断该笔订单是否在商户网站中已经做过处理,直接告知第三方支付交易已成功
			if (payment.getStatus() == PaymentStatus.SUBMITED) {
				savePaidStatus(paraMap); // 1、确认收款成功
				String result = noticeBusi_front(payment, model); // 2、给电商发送通知
				return result;

			} else if (payment.getStatus() == PaymentStatus.PAID) {
				String result = noticeBusi_front(payment, model); // 2、给电商发送通知
				return result;

			} else if (payment.getStatus() == PaymentStatus.NOTIFIED || payment.getStatus() == PaymentStatus.FINISH) {
				logger.info(" ---检测到单据已完成，直接跳转成功页面");
				model.addAttribute("busi_return_url",
						payment.getBusiReturnUrl() + "?" + "trade_no=" + payment.getTradeNo());
				return "paysuccess"; // 3、 已经处理过了， 返回订单页
			} else {
				model.addAttribute("msg", "订单状态未识别！");
				String.valueOf(new BigDecimal("123"));
				return "error";

			}
		} else {
			// 支付未成功，返回错误页面

			model.addAttribute("msg", "支付未成功！");
			model.addAttribute("busi_return_url",
					payment.getBusiReturnUrl() + "?" + "trade_no=" + payment.getTradeNo());
			return "error";
		}

	}

	// 支付宝支付成功后，支付宝支付服务回调的通知接口
	// 支付成功后 第三方支付异步调用
	@RequestMapping(value = "/pay_notify", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String payNotify(@RequestParam Map<String, String> paraMap, Model model, HttpServletRequest request) {
		logger.info("----------------------------");
		logger.info("-------后台收到支付宝通知---------");
		logger.info("----------------------------");

		// 1、检查参数
		List<String> errlist = AlipayNotify.validInParams(paraMap);
		if (!errlist.isEmpty()) {
			// 错误流水保存到日志
			logger.info("err:" + errlist.toString());
			return "error:" + errlist.toString();
		}

		logger.info("-------检查参数完成---------");

		// 2、检查签名
		boolean verify_result = AlipayNotify.verify(paraMap);
		// 测试用过签名验证
		if ("1".equals(paraMap.get("istest"))) {
			verify_result = true;
		}
		if (!verify_result) {
			// 错误流水保存到日志
			logger.info("err:: verify fail！");
			return "error: 验证失败！";
		}
		logger.info("-------检查签名完成---------");

		// 3、检查单据是否存在
		Payment payment = paymentService.getPaymentByTradeNo(paraMap.get("out_trade_no"));
		if (payment == null) {
			return "error:单据不存在";
		}

		// 4、检查业务成功标识
		String trade_status = paraMap.get("trade_status");
		if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
			// 根据单据状态进行处理 ： 判断该笔订单是否在商户网站中已经做过处理,直接告知第三方支付交易已成功
			if (payment.getStatus() == PaymentStatus.SUBMITED) {
				savePaidStatus(paraMap); // 1、确认收款成功
				String result = noticeBusi_background(payment); // 2、给电商发送通知
				return result; // 3、通知支付宝
			} else if (payment.getStatus() == PaymentStatus.PAID) {
				String result = noticeBusi_background(payment); // 2、给电商发送通知
				return result; // 3、通知支付宝
			} else if (payment.getStatus() == PaymentStatus.NOTIFIED) {
				saveFinishStatus(payment); // 3、通知支付宝
				return "success";
			} else if (payment.getStatus() == PaymentStatus.FINISH) {
				return "success"; // 已经处理过了， 通知支付宝
			} else {
				return "err";
			}
		} else {
			// 2.1、支付失败

			logger.info(payment.getTradeNo() + ":支付失败！！！！！！！！！！");
			return "error";
		}

	}

	// 通过浏览器重定向到交易系统的方法
	// 通知电商_前台操作
	private String noticeBusi_front(Payment payment, Model model) {
		String result = paymentService.notifyBusi(payment); // 利用webservice
															// 给电商发通知
		if ("success".equals(result)) { // 如果电商返回success 说明该笔业务通知完成
			saveNotifyStatus(payment);
			model.addAttribute("busi_return_url",
					payment.getBusiReturnUrl() + "?" + "trade_no=" + payment.getTradeNo());
			return "paysuccess";
		} else {
			model.addAttribute("msg", "电商订单系统更新异常，请联系客服人员！");
			model.addAttribute("busi_return_url",
					payment.getBusiReturnUrl() + "?" + "trade_no=" + payment.getTradeNo());
			return "error";
		}

	}

	// 异步通知电商系统交易完成
	// 通知电商_后台操作
	private String noticeBusi_background(Payment payment) {
		String result = paymentService.notifyBusi(payment); // 通知电商
		if ("success".equals(result)) {
			saveFinishStatus(payment);
			return "success";
		} else {
			logger.info("---tradeno---" + payment.getTradeNo() + "-----通知电商平台未成功--");
		}
		return "err";
	}

	// 调用支付日志服务，保存支付流水信息
	// 保存单据为已支付状态
	private void savePaidStatus(Map<String, String> paraMap) {
		Payment paymentReturn = new Payment();
		paymentReturn.setTradeNo((String) paraMap.get("out_trade_no"));
		paymentReturn.setStatus(PaymentStatus.PAID);
		paymentReturn.setReturnTime(UtilDate.getDateFormatter());
		paymentReturn.setNotifyParam(paraMap.toString());
		paymentService.updatePaymentStatus(paymentReturn);

	}

	// 保存支付系统的日志信息
	// 更新交易状态为完成
	private void saveFinishStatus(Payment payment) {
		Payment paymentNotify = new Payment();
		paymentNotify.setTradeNo(payment.getTradeNo());
		paymentNotify.setStatus(PaymentStatus.FINISH);
		paymentNotify.setNotifyTime(UtilDate.getDateFormatter());
		paymentNotify.setFinishTime(UtilDate.getDateFormatter());
		paymentService.updatePaymentStatus(paymentNotify);
		logger.info("---tradeno---" + payment.getTradeNo() + "-----通知电商平台成功--交易完成------------");
	}

	// 在交易完成后，保存支付系统的日志信息，将交易状态更新为已完成，防止出现重复交易(幂等性原则)
	// 更新交易状态为“已通知电商”
	private void saveNotifyStatus(Payment payment) {
		Payment paymentNotify = new Payment();
		paymentNotify.setTradeNo(payment.getTradeNo());
		paymentNotify.setStatus(PaymentStatus.NOTIFIED);
		paymentNotify.setNotifyTime(UtilDate.getDateFormatter());
		paymentService.updatePaymentStatus(paymentNotify);
		logger.info("---tradeno---" + payment.getTradeNo() + "-----通知电商平台成功--交易完成------------");
	}

}
