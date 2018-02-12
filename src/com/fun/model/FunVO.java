package com.fun.model;

public class FunVO implements java.io.Serializable {
    private Integer fun_id;
    private String fun_name;
	public Integer getFun_id() {
		return fun_id;
	}
	public void setFun_id(Integer fun_id) {
		this.fun_id = fun_id;
	}
	public String getFun_name() {
		return fun_name;
	}
	public void setFun_name(String fun_name) {
		this.fun_name = fun_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fun_id == null) ? 0 : fun_id.hashCode());
		result = prime * result + ((fun_name == null) ? 0 : fun_name.hashCode());
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
		FunVO other = (FunVO) obj;
		if (fun_id == null) {
			if (other.fun_id != null)
				return false;
		} else if (!fun_id.equals(other.fun_id))
			return false;
		if (fun_name == null) {
			if (other.fun_name != null)
				return false;
		} else if (!fun_name.equals(other.fun_name))
			return false;
		return true;
	}
	
    
    
    
}
