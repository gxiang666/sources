package com.atguigu.bean;

import java.io.Serializable;

public class T_MALL_TRADE_MARK  implements Serializable{

	private int id;
	private String ppmch;
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPpmch() {
		return ppmch;
	}

	public void setPpmch(String ppmch) {
		this.ppmch = ppmch;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
