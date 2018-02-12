package com.plan.model;

import java.io.Serializable;
import java.sql.Date;


public class PlanVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String plan_id;
	private Integer mem_id ;
	private String shareplan ;
	private String plan_name ;
	private Date startdate;
	private Integer interval;
	private Integer cycle;
	
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getShareplan() {
		return shareplan;
	}
	public void setShareplan(String shareplan) {
		this.shareplan = shareplan;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public Integer getCycle() {
		return cycle;
	}
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}


}
