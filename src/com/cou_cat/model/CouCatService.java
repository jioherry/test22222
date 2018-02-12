package com.cou_cat.model;

import java.util.List;
import java.util.Set;

import com.cou.model.CouVO;

public class CouCatService {
	
	private CouCatDAO_interface dao;
	
	public CouCatService() {
		
		dao = new CouCatDAO();
	}
	
	public CouCatVO addCouCat(String cou_cat_name , String cou_cat_info , byte[] cou_cat_img){
		
		CouCatVO cou_catVO = new CouCatVO();
		
		cou_catVO.setCou_cat_name(cou_cat_name);
		cou_catVO.setCou_cat_info(cou_cat_info);
		cou_catVO.setCou_cat_img(cou_cat_img);
		
		dao.insert(cou_catVO);
		
		return cou_catVO;
	}
	
	public CouCatVO updateCouCat(String cou_cat_id , String cou_cat_name , String cou_cat_info , byte[] cou_cat_img){
		
		CouCatVO cou_catVO = new CouCatVO();
		
		cou_catVO.setCou_cat_id(cou_cat_id);
		cou_catVO.setCou_cat_name(cou_cat_name);
		cou_catVO.setCou_cat_info(cou_cat_info);
		cou_catVO.setCou_cat_img(cou_cat_img);
		
		dao.update(cou_catVO);
		
		return cou_catVO;	
	}
	
//	public void deleteCouCat(String cou_cat_id) {
//		dao.delete(cou_cat_id);
//	}
	
	public CouCatVO getOneCouCat(String cou_cat_id) {
		return dao.findByPrimaryKey(cou_cat_id);
	}
	
	public List<CouCatVO> getAll() {
		return dao.getAll();
	}
	
	public Set<CouVO> getCousByCouCatID(String cou_cat_id){
		return dao.getCousByCouCatid(cou_cat_id);
	}
}
