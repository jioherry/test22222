package com.plan.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class PlanService {
	
	private PlanDAO_interface dao;
	
	public PlanService() {
		dao = new PlanDAO();
	}
	
	public PlanVO addPlan(Integer mem_id,String shareplan,String plan_name,Date startdate,
			Integer interval,Integer cycle){
		
		PlanVO planVO = new PlanVO();
		
		planVO.setMem_id(mem_id);
		planVO.setShareplan(shareplan);
		planVO.setPlan_name(plan_name);
		planVO.setStartdate(startdate);
		planVO.setInterval(interval);
		planVO.setCycle(cycle);
		dao.insert(planVO);
		
		return planVO;
	}
	
	public PlanVO updatePlan(String plan_id, Integer mem_id, String shareplan, String plan_name,
			java.sql.Date startdate, Integer interval, Integer cycle){
		
		PlanVO planVO = new PlanVO();
		
		planVO.setPlan_id(plan_id);
		planVO.setMem_id(mem_id);
		planVO.setShareplan(shareplan);
		planVO.setPlan_name(plan_name);
		planVO.setStartdate(startdate);
		planVO.setInterval(interval);
		planVO.setCycle(cycle);
		dao.update(planVO);
		
		return planVO;
	}
	
	public void deletePlan(String plan_id){
		dao.delete(plan_id);
	}
	
	public PlanVO getOnePlan(String plan_id) {
		return dao.findByPK(plan_id);
	}
	
	public List<PlanVO> getAll(){
		return dao.getAll();
	}
	public List<PlanVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public List<PlanVO> getfindByPK(Integer mem_id){
		return dao.getfindByPK(mem_id);
	}
}
