package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.Date;
/**
 * t_mall_sku(sku库存表)
------------------------------
Id(编号)          PKInteger
shp_id(商品id)      Integer
kc(库存)          Integer
jg(价格)          decimal
chjshj(创建时间)  Date
sku_mch(sku名称)  String(100)
sku_xl(sku销量)   Integer
kcdz(库存地址)    String(500)
 * @author 熊高祥
 *
 */
public class T_MALL_SKU {

	private int id;
	private int shp_id;
	private int kc;
	private BigDecimal jg;
	private Date chjshj;
	private String sku_mch;
	private String kcdz;
	private long sku_xl;

	public long getSku_xl() {
		return sku_xl;
	}

	public void setSku_xl(long sku_xl) {
		this.sku_xl = sku_xl;
	}

	public String getKcdz() {
		return kcdz;
	}

	public void setKcdz(String kcdz) {
		this.kcdz = kcdz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public int getKc() {
		return kc;
	}

	public void setKc(int kc) {
		this.kc = kc;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public BigDecimal getJg() {
		return jg;
	}

	public void setJg(BigDecimal jg) {
		this.jg = jg;
	}

}
