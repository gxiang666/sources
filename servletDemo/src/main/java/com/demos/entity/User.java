package com.demos.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID= -4011263747806388045L;
	
	/**
	 * 主键，ID
	 */
	public Integer id;
	/**
	 * 姓名
	 */
	private String name;
    /**
     * 年龄
     */
    private Integer age;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
    
}
