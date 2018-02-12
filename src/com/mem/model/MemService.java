package com.mem.model;

import java.util.List;

public class MemService {
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem(String mem_acct, String mem_psw, String mem_email, 
			String mem_phone,Integer mem_bonus, String mem_title, Integer mem_exp,
			byte[] mem_pic, String mem_name, String mem_gender, String mem_add, String mem_status,
			Integer mem_repno){
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_acct(mem_acct);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_email(mem_email);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_bonus(mem_bonus);
		memVO.setMem_title(mem_title);
		memVO.setMem_exp(mem_exp);
		memVO.setMem_pic(mem_pic);
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_add(mem_add);
		memVO.setMem_status(mem_status);
		memVO.setMem_repno(mem_repno);
				
		
		
	    dao.insert(memVO);
		return memVO;
	}
	
	public MemVO updateMem(Integer mem_id, String mem_acct, String mem_psw, String mem_email, 
			String mem_phone,Integer mem_bonus, String mem_title, Integer mem_exp,
			byte[] mem_pic, String mem_name, String mem_gender, String mem_add, String mem_status,
			Integer mem_repno){
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_id(mem_id);
		memVO.setMem_acct(mem_acct);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_email(mem_email);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_bonus(mem_bonus);
		memVO.setMem_title(mem_title);
		memVO.setMem_exp(mem_exp);
		memVO.setMem_pic(mem_pic);
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_add(mem_add);
		memVO.setMem_status(mem_status);
		memVO.setMem_repno(mem_repno);
				
		
		
	    dao.update(memVO);
		return memVO;
	}
	
	public void deleteMem(Integer mem_id){
		 dao.delete(mem_id);
	}
	
	public MemVO getOneMem(Integer mem_id){
		return dao.findByPrimaryKey(mem_id);
	}
	
	public MemVO getOneMemByAcct(String mem_acct){
		return dao.findByMemAcct(mem_acct);
	}
	
	public List<MemVO> getAll(){
		return dao.getAll();
	}
	
	
	
}
