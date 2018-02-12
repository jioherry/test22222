package com.cou_cat.model;

import java.util.*;
import com.cou.model.CouVO;

public interface CouCatDAO_interface {
	public void insert(CouCatVO cou_catVO);
	public void update(CouCatVO cou_catVO);
	public CouCatVO findByPrimaryKey(String cou_cat_id);
	public List<CouCatVO> getAll();
	//查詢某課程類別的課程(一對多)(回傳Set)
	public Set<CouVO> getCousByCouCatid(String cou_cat_id);
	
}
