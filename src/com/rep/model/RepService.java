package com.rep.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.emp.model.EmpVO;

public class RepService {
    
	private RepDAO_interface dao;
	
	public RepService(){
		dao = new RepDAO();
	}
	
	public RepVO addRep(Integer mem_id, 
			Integer emp_id, String cou_id, String rep_cont, String rep_status){
		
		
		RepVO repVO = new RepVO();
		
		repVO.setMem_id(mem_id);
		repVO.setEmp_id(emp_id);
		repVO.setCou_id(cou_id);
		repVO.setRep_cont(rep_cont);
		repVO.setRep_status(rep_status);
		dao.insert(repVO);
		
		return repVO;
		
	}
	
	public RepVO updateRep(Integer rep_id, Integer mem_id, 
			Integer emp_id, String cou_id, String rep_cont, Timestamp rep_date, String rep_status){
		
		
		RepVO repVO = new RepVO();
		
		repVO.setRep_id(rep_id);
		repVO.setMem_id(mem_id);
		repVO.setEmp_id(emp_id);
		repVO.setCou_id(cou_id);
		repVO.setRep_cont(rep_cont);
		repVO.setRep_date(rep_date);
		repVO.setRep_status(rep_status);
		
		dao.update(repVO);
		
		return repVO;
		
	}
	
	
	public void deleteRep(Integer rep_id){
		
		dao.delete(rep_id);
	}
	
	public RepVO getOneRep(Integer rep_id){
		return dao.findByPrimaryKey(rep_id);
	}
	
	public List<RepVO> getAll(){
		return dao.getAll();
	}
	
	public List<RepVO> getOneRepByMemId(Integer mem_id){
		return dao.findByMemId(mem_id);
	}
	public List<RepVO> getOneRepByCouId(String cou_id){
		return dao.findByCouId(cou_id);
	}
	
	public List<RepVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	
	
}
