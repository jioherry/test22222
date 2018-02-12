package com.inf_cat.model;

public class Inf_catVO implements java.io.Serializable{
    private Integer inf_cat_id;
    private String inf_cat_name;
	public Integer getInf_cat_id() {
		return inf_cat_id;
	}
	public void setInf_cat_id(Integer inf_cat_id) {
		this.inf_cat_id = inf_cat_id;
	}
	public String getInf_cat_name() {
		return inf_cat_name;
	}
	public void setInf_cat_name(String inf_cat_name) {
		this.inf_cat_name = inf_cat_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inf_cat_id == null) ? 0 : inf_cat_id.hashCode());
		result = prime * result + ((inf_cat_name == null) ? 0 : inf_cat_name.hashCode());
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
		Inf_catVO other = (Inf_catVO) obj;
		if (inf_cat_id == null) {
			if (other.inf_cat_id != null)
				return false;
		} else if (!inf_cat_id.equals(other.inf_cat_id))
			return false;
		if (inf_cat_name == null) {
			if (other.inf_cat_name != null)
				return false;
		} else if (!inf_cat_name.equals(other.inf_cat_name))
			return false;
		return true;
	}
    
    
}
