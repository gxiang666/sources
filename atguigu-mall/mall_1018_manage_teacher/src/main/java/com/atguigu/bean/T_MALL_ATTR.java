package com.atguigu.bean;

import java.util.Date;
import java.util.List;
/**
 * t_mall_attr(属性名表)
--------------------------------
Id(编号)            PKInteger
shxm_mch(属性名称)  String(100)
shfqy(是否启用)     String(1)
flbh2(分类编号2)    Integer
chjshj(创建时间)    Date
 * @author 熊高祥
 *
 */
public class T_MALL_ATTR {

	private int id;
	private String shxm_mch;
	private String shfqy;
	private int flbh2;
	private Date chjshj;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShxm_mch() {
		return shxm_mch;
	}

	public void setShxm_mch(String shxm_mch) {
		this.shxm_mch = shxm_mch;
	}

	public String getShfqy() {
		return shfqy;
	}

	public void setShfqy(String shfqy) {
		this.shfqy = shfqy;
	}

	public int getFlbh2() {
		return flbh2;
	}

	public void setFlbh2(int flbh2) {
		this.flbh2 = flbh2;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

}
