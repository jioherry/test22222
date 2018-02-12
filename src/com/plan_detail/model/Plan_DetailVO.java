package com.plan_detail.model;

import java.io.Serializable;
import java.sql.Date;

public class Plan_DetailVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer pd_no;
	private String plan_id;
	private String cou_id;
	private Date selectdate;
	private Date com_date;
	public Integer getPd_no() {
		return pd_no;
	}
	public void setPd_no(Integer pd_no) {
		this.pd_no = pd_no;
	}
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public Date getSelectdate() {
		return selectdate;
	}
	public void setSelectdate(Date selectdate) {
		this.selectdate = selectdate;
	}
	public Date getCom_date() {
		return com_date;
	}
	public void setCom_date(Date com_date) {
		this.com_date = com_date;
	}
	
}
