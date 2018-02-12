package com.cou.model;

import java.util.*;

import com.cou_det.model.CouDetVO;
//import com.dept.model.DeptVO;
import com.emp.model.EmpVO;
import com.movement.model.MovementVO;

public interface CouDAO_interface {
	//��x
	public void insert(CouVO couVO);
	public void update(CouVO couVO);
	public void delete(String cou_id);
	public CouVO findByPrimaryKey(String cou_id);
	public List<CouVO> getPubAll();
	
	public List<CouVO> getAll(Map<String , String[]> map);
//	public CouVO findByString (String cou_name);
	public Set<CouDetVO> getCouDetsByCouID(String cou_id);
	
	public List<CouVO> pubAll();
	
	public List<CouVO> oneMemberCous(Map<String, String[]> map);
	
	public MovementVO getMovementByCouId(CouDetVO couDetVO);
	
	List<CouVO> getfindByPK(Integer mem_id);
	
	
	public void insertWithCou(CouVO couVO , Set<CouDetVO> set);
}
