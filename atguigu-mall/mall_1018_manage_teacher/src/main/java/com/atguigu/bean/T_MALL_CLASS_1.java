package com.atguigu.bean;
/**
 * t_mall_class_1(分类1表)
-----------------------------
Id(编号)           PKInteger
flmch1(分类名称1)  String(100)
 * @author 熊高祥
 *
 */
public class T_MALL_CLASS_1 {

	private int id;
	private String flmch1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlmch1() {
		return flmch1;
	}

	public void setFlmch1(String flmch1) {
		this.flmch1 = flmch1;
	}

}
