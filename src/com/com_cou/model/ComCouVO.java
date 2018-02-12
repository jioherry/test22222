package com.com_cou.model;

import java.sql.Date;

public class ComCouVO {
	
	private String cou_com_id;
	private Integer mem_id;
	private String cou_id;
	private Date cou_com_date;
	private Integer day_com_count;
	
	
	public String getCou_com_id() {
		return cou_com_id;
	}
	public void setCou_com_id(String cou_com_id) {
		this.cou_com_id = cou_com_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public Date getCou_com_date() {
		return cou_com_date;
	}
	public void setCou_com_date(Date date) {
		this.cou_com_date = date;
	}
	public Integer getDay_com_count() {
		return day_com_count;
	}
	public void setDay_com_count(Integer day_com_count) {
		this.day_com_count = day_com_count;
	}
	
}
