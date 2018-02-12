package com.inf.model;

import java.util.List;
import java.util.Set;


public interface InfDAO_interface {
	  public void insert(InfVO infVO);
      public void update(InfVO infVO);
      public void delete(Integer inf_id);
      public InfVO findByPrimaryKey(Integer inf_id);
      public List<InfVO> findInfByCat(Integer inf_cat_id);
      public List<InfVO> getAll();
}
