package com.atguigu.bean;
/**
 * t_mall_user_account
	//用户账户表
	---------------------
	Id       PKInteger   //编号
	yh_mch   String(100) //用户名称
	yh_nch   String(100) //用户昵称
	yh_mm    String(100) //用户密码
	yh_xm    String(100) //用户姓名
	yh_shjh  String(100) //手机号
	yh_yx    String(100) //邮箱
	yh_tx    String(100) //头像
	yh_jb    String(100) //用户级别
 * @author 熊高祥
 *
 */
public class T_MALL_USER_ACCOUNT {

	private int id;
	private String yh_mch;
	private String yh_nch;
	private String yh_mm;
	private String yh_xm;
	private String yh_shjh;
	private String yh_yx;
	private String yh_tx;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYh_mch() {
		return yh_mch;
	}

	public void setYh_mch(String yh_mch) {
		this.yh_mch = yh_mch;
	}

	public String getYh_nch() {
		return yh_nch;
	}

	public void setYh_nch(String yh_nch) {
		this.yh_nch = yh_nch;
	}

	public String getYh_mm() {
		return yh_mm;
	}

	public void setYh_mm(String yh_mm) {
		this.yh_mm = yh_mm;
	}

	public String getYh_xm() {
		return yh_xm;
	}

	public void setYh_xm(String yh_xm) {
		this.yh_xm = yh_xm;
	}

	public String getYh_shjh() {
		return yh_shjh;
	}

	public void setYh_shjh(String yh_shjh) {
		this.yh_shjh = yh_shjh;
	}

	public String getYh_yx() {
		return yh_yx;
	}

	public void setYh_yx(String yh_yx) {
		this.yh_yx = yh_yx;
	}

	public String getYh_tx() {
		return yh_tx;
	}

	public void setYh_tx(String yh_tx) {
		this.yh_tx = yh_tx;
	}

}
