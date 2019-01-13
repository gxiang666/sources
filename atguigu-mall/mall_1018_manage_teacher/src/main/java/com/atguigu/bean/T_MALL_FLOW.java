package com.atguigu.bean;

import java.util.Date;
/**
 * t_mall_flow(物流表)
------------------------------
Id(编号)          PKInteger
psfsh(配送方式)   String(100)
psshj(配送时间)   Date
psmsh(配送描述)   String(100)
yh_id(用户id)     Integer
chjshj(创建时间)  Date
dd_id(订单id)     Integer
mqdd(目前地点)    String(100)
mdd(目的地)       String(100)
ywy(业务员)       String(100)
lxfsh(联系方式)   String(100)
 * @author 熊高祥
 *
 */
public class T_MALL_FLOW {
	private int id;
	private String psfsh;
	private Date psshj;
	private String psmsh;
	private int yh_id;
	private int dd_id;
	private String mqdd;
	private String mdd;
	private String ywy;
	private String lxfsh;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPsfsh() {
		return psfsh;
	}

	public void setPsfsh(String psfsh) {
		this.psfsh = psfsh;
	}

	public Date getPsshj() {
		return psshj;
	}

	public void setPsshj(Date psshj) {
		this.psshj = psshj;
	}

	public String getPsmsh() {
		return psmsh;
	}

	public void setPsmsh(String psmsh) {
		this.psmsh = psmsh;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public int getDd_id() {
		return dd_id;
	}

	public void setDd_id(int dd_id) {
		this.dd_id = dd_id;
	}

	public String getMqdd() {
		return mqdd;
	}

	public void setMqdd(String mqdd) {
		this.mqdd = mqdd;
	}

	public String getMdd() {
		return mdd;
	}

	public void setMdd(String mdd) {
		this.mdd = mdd;
	}

	public String getYwy() {
		return ywy;
	}

	public void setYwy(String ywy) {
		this.ywy = ywy;
	}

	public String getLxfsh() {
		return lxfsh;
	}

	public void setLxfsh(String lxfsh) {
		this.lxfsh = lxfsh;
	}

}
