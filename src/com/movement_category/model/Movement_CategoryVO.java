package com.movement_category.model;

public class Movement_CategoryVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer mov_cat_id;
	private String mov_cat_name;
	private String mov_cat_info;
	private byte[] mov_cat_img;
	
	public Integer getMov_cat_id() {
		return mov_cat_id;
	}
	public void setMov_cat_id(Integer mov_cat_id) {
		this.mov_cat_id = mov_cat_id;
	}
	public String getMov_cat_name() {
		return mov_cat_name;
	}
	public void setMov_cat_name(String mov_cat_name) {
		this.mov_cat_name = mov_cat_name;
	}
	public String getMov_cat_info() {
		return mov_cat_info;
	}
	public void setMov_cat_info(String mov_cat_info) {
		this.mov_cat_info = mov_cat_info;
	}
	public byte[] getMov_cat_img() {
		return mov_cat_img;
	}
	public void setMov_cat_img(byte[] mov_cat_img) {
		this.mov_cat_img = mov_cat_img;
	}

}
