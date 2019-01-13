package com.atguigu.bean;
/**
 * t_mall_class_2(分类2表)
-----------------------------
Id(编号)           PKInteger
flmch2(分类名称2)  String
flbh1(分类编号1)   Integer
 * @author 熊高祥
 *
 */
public class T_MALL_CLASS_2 {

	private int id;
	private String flmch2;
	private int flbh1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlmch2() {
		return flmch2;
	}

	public void setFlmch2(String flmch2) {
		this.flmch2 = flmch2;
	}

	public int getFlbh1() {
		return flbh1;
	}

	public void setFlbh1(int flbh1) {
		this.flbh1 = flbh1;
	}

}
