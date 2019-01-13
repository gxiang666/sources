package com.atguigu.bean;

import java.util.Date;
/**
 * t_mall_shoppingCar(购物车表)
------------------------------
Id(编号)          PKInteger
sku_mch(sku名称)  String(100)
sku_jg(sku价格)   decimal
tjshl(添加数量)   Integer
hj(合计)          decimal
yh_id(用户id)     Integer
shp_id(商品id)    Integer
chjshj(创建时间)  Date
sku_id(skuid)     Integer
shp_tp(商品图片)  String(100)
shfxz(是否选中)   String(1)
kcdz(库存地址) String(500)
 * @author 熊高祥
 *
 */
public class T_MALL_SHOPPINGCAR {
	private int id;
	private String sku_mch;
	private double sku_jg;
	private int tjshl;
	private double hj;
	private int yh_id;
	private int shp_id;
	private Date chjshj;
	private int sku_id;
	private String shp_tp;
	private String shfxz;

	private String kcdz;

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

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public double getSku_jg() {
		return sku_jg;
	}

	public void setSku_jg(double sku_jg) {
		this.sku_jg = sku_jg;
	}

	public int getTjshl() {
		return tjshl;
	}

	public void setTjshl(int tjshl) {
		this.tjshl = tjshl;
	}

	public double getHj() {
		return hj;
	}

	public void setHj(double hj) {
		this.hj = hj;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
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

	public String getShp_tp() {
		return shp_tp;
	}

	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}

	public String getShfxz() {
		return shfxz;
	}

	public void setShfxz(String shfxz) {
		this.shfxz = shfxz;
	}

}
