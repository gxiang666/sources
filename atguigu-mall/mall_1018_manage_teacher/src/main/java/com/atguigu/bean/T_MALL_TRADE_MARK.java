package com.atguigu.bean;
/**
 * t_mall_trade_mark(商标表)
-----------------------------
Id(编号)         PKInteger
ppmch(品牌名称)  String(100)
url(品牌图片)    String(100)
 * @author 熊高祥
 *
 */
public class T_MALL_TRADE_MARK {

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
