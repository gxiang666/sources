package com.atguigu.entity;

import java.math.BigDecimal;

public class Payment {
    private Long id;

    private String tradeNo;
    
    private BigDecimal totalFee;

    private String subject;

    private String body;

    private String submitUrl;

    private String returnParam;

    private String notifyParam;

    private String busiReturnUrl;

    private String busiNotifyUrl;

    private Integer status;

    private String submitTime;

    private String returnTime;

    private String notifyTime;

    private String finishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl == null ? null : submitUrl.trim();
    }



    public String getBusiReturnUrl() {
        return busiReturnUrl;
    }

    public void setBusiReturnUrl(String busiReturnUrl) {
        this.busiReturnUrl = busiReturnUrl == null ? null : busiReturnUrl.trim();
    }

    public String getBusiNotifyUrl() {
        return busiNotifyUrl;
    }

    public void setBusiNotifyUrl(String busiNotifyUrl) {
        this.busiNotifyUrl = busiNotifyUrl == null ? null : busiNotifyUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime == null ? null : submitTime.trim();
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime == null ? null : returnTime.trim();
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime == null ? null : notifyTime.trim();
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime == null ? null : finishTime.trim();
    }

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getReturnParam() {
		return returnParam;
	}

	public void setReturnParam(String returnParam) {
		this.returnParam = returnParam;
	}

	public String getNotifyParam() {
		return notifyParam;
	}

	public void setNotifyParam(String notifyParam) {
		this.notifyParam = notifyParam;
	}
	
	 
}