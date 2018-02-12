package com.cou_det.model;

import java.util.List;
import java.util.Set;

import com.movement.model.MovementVO;

public class CouDetService {
	
	private CouDetDAO_interface dao;
	
	public CouDetService(){
		
		dao = new CouDetDAO();
	}
	
	public CouDetVO addCouDet(String cou_id , String mov_id , Integer mov_order , Integer mov_play_time){
		
		CouDetVO cou_detVO = new CouDetVO();
		
		cou_detVO.setCou_id(cou_id);
		cou_detVO.setMov_id(mov_id);
		cou_detVO.setMov_order(mov_order);
		cou_detVO.setMov_play_time(mov_play_time);
		dao.insert(cou_detVO);
		
		return cou_detVO;
	}
	
	
	public CouDetVO updateCouDet(String cou_id , String mov_id , Integer mov_order , Integer mov_play_time){
		
		CouDetVO cou_detVO = new CouDetVO();
		
		cou_detVO.setCou_id(cou_id);
		cou_detVO.setMov_id(mov_id);
		cou_detVO.setMov_order(mov_order);
		cou_detVO.setMov_play_time(mov_play_time);
		dao.update(cou_detVO);
		
		return cou_detVO;
	}
	
	public void deleteCou(String cou_id){
		dao.delete(cou_id);
	}
	
	public void deleteMov(String cou_id , String mov_id){
		dao.delete(cou_id, mov_id);
		
	}
	
	public Set<CouDetVO> getfindByCouid(String cou_id){
		return dao.getfindByCouid(cou_id);
	}
	
	public List<CouDetVO> getAll(){
		return dao.getAll();
	}
	
	public MovementVO findByMovID(String mov_id) {
		return dao.findByMovID(mov_id);
	}

}
