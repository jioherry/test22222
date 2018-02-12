package com.inf.model;

import java.util.List;

public class InfService {
    
	
	private InfDAO_interface dao;
	
	public InfService(){
		dao = new InfDAO();
	}
	
	public InfVO addInf(Integer emp_id, Integer inf_cat_id, 
			String inf_title, String inf_text, byte[] inf_pic ){
		
		InfVO infVO = new InfVO();
		
		
		infVO.setEmp_id(emp_id);
		infVO.setInf_cat_id(inf_cat_id);
		infVO.setInf_title(inf_title);
		infVO.setInf_text(inf_text);
		infVO.setInf_pic(inf_pic);
		
		dao.insert(infVO);
		return infVO;
		
		
		
	}
	
	
	public InfVO updateInf(Integer inf_id, Integer emp_id, Integer inf_cat_id, 
			String inf_title, String inf_text, byte[] inf_pic){
		
		InfVO infVO = new InfVO();
		
		infVO.setInf_id(inf_id);
		infVO.setEmp_id(emp_id);
		infVO.setInf_cat_id(inf_cat_id);
		infVO.setInf_title(inf_title);
		infVO.setInf_text(inf_text);
		infVO.setInf_pic(inf_pic);
		
		dao.update(infVO);
		return infVO;
	}
	
	
	public void deleteInf(Integer inf_id){
		dao.delete(inf_id);
	}
	
	public InfVO getOneInf(Integer inf_id){
		return dao.findByPrimaryKey(inf_id);
	}
	
	public List<InfVO> getAll(){
		return dao.getAll();
	}
	
	public List<InfVO> findInfByCat(Integer inf_cat_id){
		return dao.findInfByCat(inf_cat_id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
