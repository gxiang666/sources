package com.atguigu.bean;

/**
 * t_mall_product_image(商品图片表)
---------------------------------
Id(编号)        PKInteger
zy(作用)        String(100)
shp_id(商品id)  Integer
url(图片地址)   String(100)
 *
 */
public class T_MALL_PRODUCT_IMAGE {
	private int id;
	private String zy;
	private int shp_id;
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
