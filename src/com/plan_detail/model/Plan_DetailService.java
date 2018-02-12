package com.plan_detail.model;

import java.sql.Date;
import java.util.List;

import com.plan.model.PlanVO;

public class Plan_DetailService {

	private Plan_Detail_interface dao;
	
	public Plan_DetailService(){
		dao = new Plan_DetailDAO();
	}
	
	public void addPlan_Detail(String plan_id,String cou_id,Date selectdate){
		
		Plan_DetailVO plan_detailVO = new Plan_DetailVO();
		
		plan_detailVO.setPlan_id(plan_id);
		plan_detailVO.setCou_id(cou_id);
		plan_detailVO.setSelectdate(selectdate);
		dao.insert(plan_detailVO);
	}
	
	public Plan_DetailVO updatePlan_Detail(Date selectdate ,Integer pd_no ,String plan_id , String cou_id){
		
		Plan_DetailVO plan_detailVO = new Plan_DetailVO();
		
		plan_detailVO.setSelectdate(selectdate);
		plan_detailVO.setPd_no(pd_no);
		plan_detailVO.setPlan_id(plan_id);
		plan_detailVO.setCou_id(cou_id);
		dao.update(plan_detailVO);
		
		return plan_detailVO;
	}
	
	
	public void deletePlan_Detail(Integer pd_no){
		dao.delete(pd_no);
	}
	
	public Plan_DetailVO getOnePlan_Detail(Integer pd_no){
		return dao.findByPK(pd_no);
	}
	
	public List<Plan_DetailVO> getByPK(String plan_id){
		return dao.getByPK(plan_id);
	}
	
	public List<Plan_DetailVO> getAll(){
		return dao.getAll();
	}
}
