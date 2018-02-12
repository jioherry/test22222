package com.com_cou.model;

import java.util.*;

public interface ComCouDAO_interface {
	public void insert(ComCouVO com_couVO);
	public void update(ComCouVO com_couVO);
	public Set<ComCouVO> getComCousByCouid(String cou_id);
	public Set<ComCouVO> getComCousByMemid(Integer mem_id);
	
}
