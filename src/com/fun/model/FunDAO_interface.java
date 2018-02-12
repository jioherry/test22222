package com.fun.model;

import java.util.List;
import java.util.Set;


public interface FunDAO_interface {
	  public void insert(FunVO funvo);
      public void update(FunVO funvo);
      public void delete(Integer fun_id);
      public FunVO findByPrimaryKey(Integer fun_id);
      public List<FunVO> getAll();
}
