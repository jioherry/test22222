package com.cou_cat.model;

import java.util.*;
import com.cou.model.CouVO;

public interface CouCatDAO_interface {
	public void insert(CouCatVO cou_catVO);
	public void update(CouCatVO cou_catVO);
	public CouCatVO findByPrimaryKey(String cou_cat_id);
	public List<CouCatVO> getAll();
	//�d�߬Y�ҵ{���O���ҵ{(�@��h)(�^��Set)
	public Set<CouVO> getCousByCouCatid(String cou_cat_id);
	
}
