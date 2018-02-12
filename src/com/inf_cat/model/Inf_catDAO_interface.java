package com.inf_cat.model;

import java.util.List;
import java.util.Set;


public interface Inf_catDAO_interface {
	  public void insert(Inf_catVO inf_catvo);
      public void update(Inf_catVO inf_catvo);
      public void delete(Integer inf_cat_id);
      public Inf_catVO findByPrimaryKey(Integer inf_cat_id);
      public List<Inf_catVO> getAll();
}
