package com.cou.model;

import java.sql.Date;
import java.util.Collection;

import javax.servlet.http.Part;

public class CouVO {
	private String cou_id;
	private String cou_cat_id;
	private Integer mem_id;
	private byte[]cou_img;
	private String cou_intor;
	private String cou_name;
	private String cou_permi;
	private Integer cou_int;
	private Date cre_date;
	private String cited_count;
	private Integer cou_cal_cns;
	private Integer cou_time_length;
	private String dis_state;
	
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public String getCou_cat_id() {
		return cou_cat_id;
	}
	public void setCou_cat_id(String cou_cat_id) {
		this.cou_cat_id = cou_cat_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public byte[]getCou_img() {
		return cou_img;
	}
	public void setCou_img(byte[]cou_img) {
		this.cou_img = cou_img;
	}
	public String getCou_intor() {
		return cou_intor;
	}
	public void setCou_intor(String cou_intor) {
		this.cou_intor = cou_intor;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public String getCou_permi() {
		return cou_permi;
	}
	public void setCou_permi(String cou_permi) {
		this.cou_permi = cou_permi;
	}
	public Integer getCou_int() {
		return cou_int;
	}
	public void setCou_int(Integer cou_int) {
		this.cou_int = cou_int;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public void setCre_date(Date cre_date) {
		this.cre_date = cre_date;
	}
	public String getCited_count() {
		return cited_count;
	}
	public void setCited_count(String cited_count) {
		this.cited_count = cited_count;
	}
	public Integer getCou_cal_cns() {
		return cou_cal_cns;
	}
	public void setCou_cal_cns(Integer cou_cal_cns) {
		this.cou_cal_cns = cou_cal_cns;
	}
	public Integer getCou_time_length() {
		return cou_time_length;
	}
	public void setCou_time_length(Integer cou_time_length) {
		this.cou_time_length = cou_time_length;
	}
	public String getDis_state() {
		return dis_state;
	}
	public void setDis_state(String dis_state) {
		this.dis_state = dis_state;
	}
	
}
