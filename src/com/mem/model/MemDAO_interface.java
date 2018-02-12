package com.mem.model;

import java.util.List;
import java.util.Set;


public interface MemDAO_interface {
	  public void insert(MemVO memVO);
      public void update(MemVO memVO);
      public void delete(Integer mem_id);
      public MemVO findByPrimaryKey(Integer mem_id);
      public MemVO findByMemAcct(String mem_acct);
      public List<MemVO> getAll();
}
