package com.rep.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.emp.model.EmpVO;


public interface RepDAO_interface {
	  public void insert(RepVO repVO);
      public void update(RepVO repVO);
      public void delete(Integer rep_id);
      public RepVO findByPrimaryKey(Integer rep_id);
      public List<RepVO> findByMemId(Integer mem_id);
      public List<RepVO> findByCouId(String cou_id);
      public List<RepVO> getAll();
      public List<RepVO> getAll(Map<String, String[]> map); 
}
