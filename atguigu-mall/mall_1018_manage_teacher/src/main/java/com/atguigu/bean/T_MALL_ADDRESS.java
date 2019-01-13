package com.atguigu.bean;
/**
 * t_mall_address(用户地址表)
-----------------------------
Id(编号)         PKInteger
dz_mch(用户地址)  String(100)
dzzt(地址状态)   Integer
yh_id(用户id)    Integer(100)
shjr(收件人)     String(100)
lxfsh(联系方式)  String(100)
 * @author 熊高祥
 *
 */
public class T_MALL_ADDRESS {

	private int id;
	private String yh_dz;
	private String dzzt;
	private int yh_id;
	private String shjr;
	private String lxfsh;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYh_dz() {
		return yh_dz;
	}

	public void setYh_dz(String yh_dz) {
		this.yh_dz = yh_dz;
	}

	public String getDzzt() {
		return dzzt;
	}

	public void setDzzt(String dzzt) {
		this.dzzt = dzzt;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public String getShjr() {
		return shjr;
	}

	public void setShjr(String shjr) {
		this.shjr = shjr;
	}

	public String getLxfsh() {
		return lxfsh;
	}

	public void setLxfsh(String lxfsh) {
		this.lxfsh = lxfsh;
	}

}
