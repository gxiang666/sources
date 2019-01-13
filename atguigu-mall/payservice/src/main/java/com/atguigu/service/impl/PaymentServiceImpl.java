package com.atguigu.service.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.dao.PaymentMapper;
import com.atguigu.entity.Payment;
import com.atguigu.entity.PaymentExample;
import com.atguigu.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentMapper paymentMapper;
	

	
	public int addPayment(Payment payment) {
		paymentMapper.insertSelective(payment);
		return 0;
	}
	
 
	

	public int updatePaymentStatus(Payment payment) {
		paymentMapper.updateByTradeNoSelective(payment);
		return 0;
	}

	public int finishPayment(Payment payment) {
		paymentMapper.updateByTradeNoSelective(payment);
		return 0;
	}
	
	
	public Payment getPaymentByTradeNo(String tradeNo) {

		Payment payment=paymentMapper.selectByTradeNo(tradeNo);
		return payment;
	}
	
	public  String notifyBusi(Payment payment){
		
		
		CloseableHttpClient httpClient=HttpClientBuilder.create().build();
		
		
		HttpGet httpGet=new HttpGet(payment.getBusiNotifyUrl()+"?trade_no="+payment.getTradeNo());
		//给商户系统1秒的传输时间  和2秒的连接建立时间，否则视为通知失效
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(2000).build(); 
		
		httpGet.setConfig(requestConfig);
		
		System.err.println(payment.getBusiNotifyUrl());
		
		String result="";
		try {
		    HttpResponse httpResponse=	httpClient.execute(httpGet);
			
		    HttpEntity httpEntity= httpResponse.getEntity();
			
		    result= EntityUtils.toString(httpEntity) ; 
		    
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 
		return result;
		
	}
	
	

}
