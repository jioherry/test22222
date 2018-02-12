package com.mem.model;

import java.util.Arrays;

public class MemVO implements java.io.Serializable{
    private Integer mem_id;
    private String mem_acct;
    private String mem_psw;
    private String mem_email;
    private String mem_phone;
    private Integer mem_bonus;
    private String mem_title;
    private Integer mem_exp;
    private byte[] mem_pic;
    private String mem_name;
    private String mem_gender;
    private String mem_add;
    private String mem_status;
    private Integer mem_repno;
    
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_acct() {
		return mem_acct;
	}
	public void setMem_acct(String mem_acct) {
		this.mem_acct = mem_acct;
	}
	public String getMem_psw() {
		return mem_psw;
	}
	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public Integer getMem_bonus() {
		return mem_bonus;
	}
	public void setMem_bonus(Integer mem_bonus) {
		this.mem_bonus = mem_bonus;
	}
	public String getMem_title() {
		return mem_title;
	}
	public void setMem_title(String mem_title) {
		this.mem_title = mem_title;
	}
	public Integer getMem_exp() {
		return mem_exp;
	}
	public void setMem_exp(Integer mem_exp) {
		this.mem_exp = mem_exp;
	}
	public byte[] getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getMem_add() {
		return mem_add;
	}
	public void setMem_add(String mem_add) {
		this.mem_add = mem_add;
	}
	public String getMem_status() {
		return mem_status;
	}
	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}
	public Integer getMem_repno() {
		return mem_repno;
	}
	public void setMem_repno(Integer mem_repno) {
		this.mem_repno = mem_repno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_acct == null) ? 0 : mem_acct.hashCode());
		result = prime * result + ((mem_add == null) ? 0 : mem_add.hashCode());
		result = prime * result + ((mem_bonus == null) ? 0 : mem_bonus.hashCode());
		result = prime * result + ((mem_email == null) ? 0 : mem_email.hashCode());
		result = prime * result + ((mem_exp == null) ? 0 : mem_exp.hashCode());
		result = prime * result + ((mem_gender == null) ? 0 : mem_gender.hashCode());
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((mem_name == null) ? 0 : mem_name.hashCode());
		result = prime * result + ((mem_phone == null) ? 0 : mem_phone.hashCode());
		result = prime * result + Arrays.hashCode(mem_pic);
		result = prime * result + ((mem_psw == null) ? 0 : mem_psw.hashCode());
		result = prime * result + ((mem_repno == null) ? 0 : mem_repno.hashCode());
		result = prime * result + ((mem_status == null) ? 0 : mem_status.hashCode());
		result = prime * result + ((mem_title == null) ? 0 : mem_title.hashCode());
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
		MemVO other = (MemVO) obj;
		if (mem_acct == null) {
			if (other.mem_acct != null)
				return false;
		} else if (!mem_acct.equals(other.mem_acct))
			return false;
		if (mem_add == null) {
			if (other.mem_add != null)
				return false;
		} else if (!mem_add.equals(other.mem_add))
			return false;
		if (mem_bonus == null) {
			if (other.mem_bonus != null)
				return false;
		} else if (!mem_bonus.equals(other.mem_bonus))
			return false;
		if (mem_email == null) {
			if (other.mem_email != null)
				return false;
		} else if (!mem_email.equals(other.mem_email))
			return false;
		if (mem_exp == null) {
			if (other.mem_exp != null)
				return false;
		} else if (!mem_exp.equals(other.mem_exp))
			return false;
		if (mem_gender == null) {
			if (other.mem_gender != null)
				return false;
		} else if (!mem_gender.equals(other.mem_gender))
			return false;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (mem_name == null) {
			if (other.mem_name != null)
				return false;
		} else if (!mem_name.equals(other.mem_name))
			return false;
		if (mem_phone == null) {
			if (other.mem_phone != null)
				return false;
		} else if (!mem_phone.equals(other.mem_phone))
			return false;
		if (!Arrays.equals(mem_pic, other.mem_pic))
			return false;
		if (mem_psw == null) {
			if (other.mem_psw != null)
				return false;
		} else if (!mem_psw.equals(other.mem_psw))
			return false;
		if (mem_repno == null) {
			if (other.mem_repno != null)
				return false;
		} else if (!mem_repno.equals(other.mem_repno))
			return false;
		if (mem_status == null) {
			if (other.mem_status != null)
				return false;
		} else if (!mem_status.equals(other.mem_status))
			return false;
		if (mem_title == null) {
			if (other.mem_title != null)
				return false;
		} else if (!mem_title.equals(other.mem_title))
			return false;
		return true;
	}
    
    
    
}
