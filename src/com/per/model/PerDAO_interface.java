package com.per.model;

import java.util.List;
import java.util.Set;


public interface PerDAO_interface {
	  public void insert(PerVO perVO);
      public void update(PerVO perVO);
      public void delete(Integer emp_id, Integer fun_id);
      public PerVO findByPrimaryKey(Integer emp_id, Integer fun_id);
      public List<PerVO> getOnePerList(Integer emp_id);
      public List<PerVO> getAll();
}
