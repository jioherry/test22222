package com.emp.model;

import java.util.List;
import java.util.Set;


public interface EmpDAO_interface {
	  public void insert(EmpVO empVO);
      public void update(EmpVO empVO);
      public void delete(Integer emp_id);
      public EmpVO findByPrimaryKey(Integer emp_id);
      public List<EmpVO> getAll();
}
