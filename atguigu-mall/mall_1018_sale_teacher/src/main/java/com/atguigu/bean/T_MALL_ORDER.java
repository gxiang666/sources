package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_ORDER {

	private Integer id;
	private String shhr;
	private BigDecimal zje;
	private Integer jdh;
	private Integer yh_id;
	private Date chjshj;
	private Date yjsdshj;
	private Integer dzh_id;
	private String dzh_mch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShhr() {
		return shhr;
	}

	public void setShhr(String shhr) {
		this.shhr = shhr;
	}

	public BigDecimal getZje() {
		return zje;
	}

	public void setZje(BigDecimal zje) {
		this.zje = zje;
	}

	public Integer getJdh() {
		return jdh;
	}

	public void setJdh(Integer jdh) {
		this.jdh = jdh;
	}

	public Integer getYh_id() {
		return yh_id;
	}

	public void setYh_id(Integer yh_id) {
		this.yh_id = yh_id;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public Date getYjsdshj() {
		return yjsdshj;
	}

	public void setYjsdshj(Date yjsdshj) {
		this.yjsdshj = yjsdshj;
	}

	public Integer getDzh_id() {
		return dzh_id;
	}

	public void setDzh_id(Integer dzh_id) {
		this.dzh_id = dzh_id;
	}

	public String getDzh_mch() {
		return dzh_mch;
	}

	public void setDzh_mch(String dzh_mch) {
		this.dzh_mch = dzh_mch;
	}

}
