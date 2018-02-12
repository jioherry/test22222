package com.cou.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cou_det.model.CouDetVO;
import com.movement.model.MovementVO;

public class CouService {
	
	private CouDAO_interface dao;
	
	public CouService(){
		
		dao = new CouDAO();
	}
	
	public CouVO addCou(String cou_cat_id , Integer mem_id , 
			String cou_intor , String cou_name , String cou_permi , Integer cou_int ,
			String cited_count , Integer cou_cal_cns , Integer cou_time_length , byte[]cou_img, String dis_state) {
		
		CouVO couVO = new CouVO();
		
		couVO.setCou_cat_id(cou_cat_id);
		couVO.setMem_id(mem_id);
		couVO.setCou_intor(cou_intor);
		couVO.setCou_name(cou_name);
		couVO.setCou_permi(cou_permi);
		couVO.setCou_int(cou_int);
		couVO.setCited_count(cited_count);
		couVO.setCou_cal_cns(cou_cal_cns);
		couVO.setCou_time_length(cou_time_length);
		couVO.setDis_state(dis_state);
		couVO.setCou_img(cou_img);
		dao.insert(couVO);
		
		return couVO;
	}
	
	public CouVO updateCou(String cou_id , String cou_cat_id , Integer mem_id , 
			String cou_intor , String cou_name , String cou_permi , Integer cou_int ,
			String cited_count , Integer cou_cal_cns , Integer cou_time_length , byte[]cou_img) {
		
		CouVO couVO = new CouVO();
		
		couVO.setCou_id(cou_id);
		couVO.setCou_cat_id(cou_cat_id);
		couVO.setMem_id(mem_id);
		couVO.setCou_intor(cou_intor);
		couVO.setCou_name(cou_name);
		couVO.setCou_permi(cou_permi);
		couVO.setCou_int(cou_int);
		couVO.setCited_count(cited_count);
		couVO.setCou_cal_cns(cou_cal_cns);
		couVO.setCou_time_length(cou_time_length);
		couVO.setCou_img(cou_img);
		dao.update(couVO);
		
		return couVO;
	}
	
	public Set<CouDetVO> getCouDetsByCouID(String cou_id) {
		return dao.getCouDetsByCouID(cou_id);
	}
	
	public MovementVO getMovementByCouId(CouDetVO couDetVO) {
		return dao.getMovementByCouId(couDetVO);
	}
	
	public void delete(String cou_id) {
		dao.delete(cou_id);
	}
	
	public CouVO getOneCou(String cou_id) {
		return dao.findByPrimaryKey(cou_id);
	}
	
	public List<CouVO> getPubAll() {
		return dao.getPubAll();
	}
	
	public List<CouVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<CouVO> oneMemberCous(Map<String, String[]> map){
		return dao.oneMemberCous(map);
	}
	public List<CouVO> getfindByPK(Integer mem_id){
		return dao.getfindByPK(mem_id);
	}
	public void insertWithCou(CouVO couVO, Set<CouDetVO> set){
		 dao.insertWithCou(couVO, set);
	}
	
	
	
	
	
	
	
	
}
