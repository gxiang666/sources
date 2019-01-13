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
public class T_MALL_VALUE {

	private int id;
	private String shxzh;
	private String shfqy;
	private int shxm_id;
	private String shxzh_mch;
	private Date chjshj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShxzh() {
		return shxzh;
	}

	public void setShxzh(String shxzh) {
		this.shxzh = shxzh;
	}

	public String getShfqy() {
		return shfqy;
	}

	public void setShfqy(String shfqy) {
		this.shfqy = shfqy;
	}

	public int getShxm_id() {
		return shxm_id;
	}

	public void setShxm_id(int shxm_id) {
		this.shxm_id = shxm_id;
	}

	public String getShxzh_mch() {
		return shxzh_mch;
	}

	public void setShxzh_mch(String shxzh_mch) {
		this.shxzh_mch = shxzh_mch;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

}
