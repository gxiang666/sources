package com.atguigu.bean;

import java.util.Date;
/**
 * t_mall_value(属性值表)
---------------------------------
Id(编号)             PKInteger
shxzh(属性值)        String(100)
shfqy(是否启用)      String(1)
shxm_id(属性id)      Integer
shxzh_mch(属性值名)  String(100)
chjshj(创建时间)     Date
 * @author 熊高祥
 *
 */
public class T_MALL_SKU_ATTR_VALUE {
	private int id;
	private int shxm_id;
	private int shxzh_id;
	private int shp_id;
	private String is_sku;
	private Date chjshj;
	private int sku_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShxm_id() {
		return shxm_id;
	}

	public void setShxm_id(int shxm_id) {
		this.shxm_id = shxm_id;
	}

	public int getShxzh_id() {
		return shxzh_id;
	}

	public void setShxzh_id(int shxzh_id) {
		this.shxzh_id = shxzh_id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public String getIs_sku() {
		return is_sku;
	}

	public void setIs_sku(String is_sku) {
		this.is_sku = is_sku;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public int getSku_id() {
		return sku_id;
	}

	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}

}
