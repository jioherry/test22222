package com.rep.model;

import java.sql.Timestamp;

public class RepVO implements java.io.Serializable {
    private Integer rep_id;
    private Integer mem_id;
    private Integer emp_id;
    private String cou_id;
    private String rep_cont;
    private Timestamp rep_date;
    private String rep_status;
	public Integer getRep_id() {
		return rep_id;
	}
	public void setRep_id(Integer rep_id) {
		this.rep_id = rep_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public String getRep_cont() {
		return rep_cont;
	}
	public void setRep_cont(String rep_cont) {
		this.rep_cont = rep_cont;
	}
	public Timestamp getRep_date() {
		return rep_date;
	}
	public void setRep_date(Timestamp rep_date) {
		this.rep_date = rep_date;
	}
	public String getRep_status() {
		return rep_status;
	}
	public void setRep_status(String rep_status) {
		this.rep_status = rep_status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cou_id == null) ? 0 : cou_id.hashCode());
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((rep_cont == null) ? 0 : rep_cont.hashCode());
		result = prime * result + ((rep_date == null) ? 0 : rep_date.hashCode());
		result = prime * result + ((rep_id == null) ? 0 : rep_id.hashCode());
		result = prime * result + ((rep_status == null) ? 0 : rep_status.hashCode());
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
		RepVO other = (RepVO) obj;
		if (cou_id == null) {
			if (other.cou_id != null)
				return false;
		} else if (!cou_id.equals(other.cou_id))
			return false;
		if (emp_id == null) {
			if (other.emp_id != null)
				return false;
		} else if (!emp_id.equals(other.emp_id))
			return false;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (rep_cont == null) {
			if (other.rep_cont != null)
				return false;
		} else if (!rep_cont.equals(other.rep_cont))
			return false;
		if (rep_date == null) {
			if (other.rep_date != null)
				return false;
		} else if (!rep_date.equals(other.rep_date))
			return false;
		if (rep_id == null) {
			if (other.rep_id != null)
				return false;
		} else if (!rep_id.equals(other.rep_id))
			return false;
		if (rep_status == null) {
			if (other.rep_status != null)
				return false;
		} else if (!rep_status.equals(other.rep_status))
			return false;
		return true;
	}
	
    
    
	
    
    
    
}
