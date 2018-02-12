package com.per.model;

import java.util.List;

public class PerService {

	
	private PerDAO_interface dao;
	
	public PerService(){
		dao = new PerDAO();
	}
	
	public PerVO addPer(Integer emp_id, Integer fun_id){
		
		PerVO perVO = new PerVO();
		
		perVO.setEmp_id(emp_id);
		perVO.setFun_id(fun_id);
		
		dao.insert(perVO);
		
		return perVO;
		
		
	}
	
	//¨S¦³update
	
	
	public void deletePer(Integer emp_id, Integer fun_id){
		dao.delete(emp_id, fun_id);
	}
	
	public PerVO getOnePer(Integer emp_id, Integer fun_id){
		return dao.findByPrimaryKey(emp_id, fun_id);
	}
	
	public List<PerVO> getOnePerList(Integer emp_id){
		return dao.getOnePerList(emp_id);
	}
	
	public List<PerVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
	
	
	
}
