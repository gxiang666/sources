package com.alipay.util;

public interface PaymentStatus {
	
	public static final int SUBMITED=1;   //用户发起支付时的状态
	
	public static final int PAID=2;   //用户对该订单已支付，并且通过第三方支付返回 
	
	public static final int NOTIFIED=3;//接到支付宝返回信息后，通知商家的订单系统，并返回确认信息

	public static final int FINISH=4;//第三方支付的异步通知，支付模块需返回成功标志，则第三方支付进行确认入账}
} 
