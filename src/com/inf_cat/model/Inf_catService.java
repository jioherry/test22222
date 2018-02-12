package com.inf_cat.model;

import java.util.List;

import com.emp.model.EmpVO;

public class Inf_catService {
    
	private Inf_catDAO_interface dao;
	
	public Inf_catService(){
		dao = new Inf_catDAO();
	}
	
	public Inf_catVO addInf_cat(Integer inf_cat_id, String inf_cat_name){
		
		Inf_catVO inf_catVO = new Inf_catVO();
		
		inf_catVO.setInf_cat_id(inf_cat_id);
		inf_catVO.setInf_cat_name(inf_cat_name);
		
		dao.insert(inf_catVO);
		
		return inf_catVO;	
		
		
	}
	
	public Inf_catVO updateInf_cat(Integer inf_cat_id, String inf_cat_name) {

		Inf_catVO inf_catVO = new Inf_catVO();

		inf_catVO.setInf_cat_id(inf_cat_id);
		inf_catVO.setInf_cat_name(inf_cat_name);
		
		dao.update(inf_catVO);

		return inf_catVO;
	}
	
	public void deleteInf_cat(Integer inf_cat_id) {
		dao.delete(inf_cat_id);
	}

	public Inf_catVO getOneInf_cat(Integer inf_cat_id) {
		return dao.findByPrimaryKey(inf_cat_id);
	}

	public List<Inf_catVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
	
	
	
	
}
