package com.cou_det.model;

import java.util.List;
import java.util.Set;

import com.emp.model.EmpVO;
import com.movement.model.MovementVO;

public interface CouDetDAO_interface {
	public void insert(CouDetVO cou_detVO);
	public void delete(String cou_id , String mov_id);
	public List<CouDetVO> getAll();
	public Set<CouDetVO> getfindByCouid(String cou_id);
	public void delete(String cou_id);
	public MovementVO findByMovID(String mov_id);
	
	public void update(CouDetVO cou_detVO);
//	public Set<CouDetVO> getfindByOneMovid(String mov_id);  
	
	 public void insert2 (CouDetVO cou_detVO , java.sql.Connection con);
	
}
