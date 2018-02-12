package com.emp.model;

import java.util.List;

public class EmpService {
	
	private EmpDAO_interface dao;
	
	public EmpService(){
		dao = new EmpDAO();
		
	}
	
	
	public EmpVO addEmp(String emp_acct, String emp_psw, String emp_name,
			String emp_email, String emp_role){
		EmpVO empVO = new EmpVO();
		
		empVO.setEmp_acct(emp_acct);
		empVO.setEmp_psw(emp_psw);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_role(emp_role);
		dao.insert(empVO);
		
		return empVO;
		
		
	}
	
	public EmpVO updateEmp(Integer emp_id, String emp_acct, String emp_psw, String emp_name,
			String emp_email, String emp_role){
		EmpVO empVO = new EmpVO();
		
		empVO.setEmp_id(emp_id);
		empVO.setEmp_acct(emp_acct);
		empVO.setEmp_psw(emp_psw);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_role(emp_role);
		dao.update(empVO);
		
		return empVO;
		
		
		
	}
	
	
	public void deleteEmp(Integer emp_id){
		dao.delete(emp_id);
	}
	
	public EmpVO getOneEmp(Integer emp_id){
		return dao.findByPrimaryKey(emp_id);
		
	}
	
	public List<EmpVO> getAll(){
		return dao.getAll();
	}	
	
	
	
	
}
