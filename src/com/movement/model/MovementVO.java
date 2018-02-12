package com.movement.model;
import java.sql.*;

public class MovementVO implements java.io.Serializable {

	private String  mov_id;
	private Integer mov_cat_id;
	private String  mov_name;
	private String  mov_info;
	private byte[]  mov_img;
	private String  mov_level;
	private Integer mov_time_length;
	private String  mov_video;
	
	public String getMov_id() {
		return mov_id;
	}
	public void setMov_id(String mov_id) {
		this.mov_id = mov_id;
	}
	public Integer getMov_cat_id() {
		return mov_cat_id;
	}
	public void setMov_cat_id(Integer mov_cat_id) {
		this.mov_cat_id = mov_cat_id;
	}
	public String getMov_name() {
		return mov_name;
	}
	public void setMov_name(String mov_name) {
		this.mov_name = mov_name;
	}
	public String getMov_info() {
		return mov_info;
	}
	public void setMov_info(String mov_info) {
		this.mov_info = mov_info;
	}
	public byte[] getMov_img() {
		return mov_img;
	}
	public void setMov_img(byte[] mov_img) {
		this.mov_img = mov_img;
	}
	public String getMov_level() {
		return mov_level;
	}
	public void setMov_level(String mov_level) {
		this.mov_level = mov_level;
	}
	public Integer getMov_time_length() {
		return mov_time_length;
	}
	public void setMov_time_length(Integer mov_time_length) {
		this.mov_time_length = mov_time_length;
	}
	public String getMov_video() {
		return mov_video;
	}
	public void setMov_video(String mov_video) {
		this.mov_video = mov_video;
	}
		
}
