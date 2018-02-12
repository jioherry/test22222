package com.emp.model;

public class EmpVO implements java.io.Serializable {
    private Integer emp_id;
    private String emp_acct;
    private String emp_psw;
    private String emp_name;
    private String emp_email;
    private String emp_role;
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_acct() {
		return emp_acct;
	}
	public void setEmp_acct(String emp_acct) {
		this.emp_acct = emp_acct;
	}
	public String getEmp_psw() {
		return emp_psw;
	}
	public void setEmp_psw(String emp_psw) {
		this.emp_psw = emp_psw;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_role() {
		return emp_role;
	}
	public void setEmp_role(String emp_role) {
		this.emp_role = emp_role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp_acct == null) ? 0 : emp_acct.hashCode());
		result = prime * result + ((emp_email == null) ? 0 : emp_email.hashCode());
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((emp_name == null) ? 0 : emp_name.hashCode());
		result = prime * result + ((emp_psw == null) ? 0 : emp_psw.hashCode());
		result = prime * result + ((emp_role == null) ? 0 : emp_role.hashCode());
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
		EmpVO other = (EmpVO) obj;
		if (emp_acct == null) {
			if (other.emp_acct != null)
				return false;
		} else if (!emp_acct.equals(other.emp_acct))
			return false;
		if (emp_email == null) {
			if (other.emp_email != null)
				return false;
		} else if (!emp_email.equals(other.emp_email))
			return false;
		if (emp_id == null) {
			if (other.emp_id != null)
				return false;
		} else if (!emp_id.equals(other.emp_id))
			return false;
		if (emp_name == null) {
			if (other.emp_name != null)
				return false;
		} else if (!emp_name.equals(other.emp_name))
			return false;
		if (emp_psw == null) {
			if (other.emp_psw != null)
				return false;
		} else if (!emp_psw.equals(other.emp_psw))
			return false;
		if (emp_role == null) {
			if (other.emp_role != null)
				return false;
		} else if (!emp_role.equals(other.emp_role))
			return false;
		return true;
	}
	
    
    
}
