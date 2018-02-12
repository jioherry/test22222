package com.inf.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class InfVO implements java.io.Serializable{
	private Integer inf_id;
	private Integer emp_id;
	private Integer inf_cat_id;
	private String inf_title;
	private String inf_text;
	private byte[] inf_pic;
	private Timestamp inf_date;
	public Integer getInf_id() {
		return inf_id;
	}
	public void setInf_id(Integer inf_id) {
		this.inf_id = inf_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getInf_cat_id() {
		return inf_cat_id;
	}
	public void setInf_cat_id(Integer inf_cat_id) {
		this.inf_cat_id = inf_cat_id;
	}
	public String getInf_title() {
		return inf_title;
	}
	public void setInf_title(String inf_title) {
		this.inf_title = inf_title;
	}
	public String getInf_text() {
		return inf_text;
	}
	public void setInf_text(String inf_text) {
		this.inf_text = inf_text;
	}
	public byte[] getInf_pic() {
		return inf_pic;
	}
	public void setInf_pic(byte[] inf_pic) {
		this.inf_pic = inf_pic;
	}
	public Timestamp getInf_date() {
		return inf_date;
	}
	public void setInf_date(Timestamp inf_date) {
		this.inf_date = inf_date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((inf_cat_id == null) ? 0 : inf_cat_id.hashCode());
		result = prime * result + ((inf_date == null) ? 0 : inf_date.hashCode());
		result = prime * result + ((inf_id == null) ? 0 : inf_id.hashCode());
		result = prime * result + Arrays.hashCode(inf_pic);
		result = prime * result + ((inf_text == null) ? 0 : inf_text.hashCode());
		result = prime * result + ((inf_title == null) ? 0 : inf_title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfVO other = (InfVO) obj;
		if (emp_id == null) {
			if (other.emp_id != null)
				return false;
		} else if (!emp_id.equals(other.emp_id))
			return false;
		if (inf_cat_id == null) {
			if (other.inf_cat_id != null)
				return false;
		} else if (!inf_cat_id.equals(other.inf_cat_id))
			return false;
		if (inf_date == null) {
			if (other.inf_date != null)
				return false;
		} else if (!inf_date.equals(other.inf_date))
			return false;
		if (inf_id == null) {
			if (other.inf_id != null)
				return false;
		} else if (!inf_id.equals(other.inf_id))
			return false;
		if (!Arrays.equals(inf_pic, other.inf_pic))
			return false;
		if (inf_text == null) {
			if (other.inf_text != null)
				return false;
		} else if (!inf_text.equals(other.inf_text))
			return false;
		if (inf_title == null) {
			if (other.inf_title != null)
				return false;
		} else if (!inf_title.equals(other.inf_title))
			return false;
		return true;
	}
	
	
}
