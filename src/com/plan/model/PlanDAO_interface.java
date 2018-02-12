package com.plan.model;

import java.util.List;
import java.util.Map;

public  interface PlanDAO_interface {
	void insert(PlanVO planVO);
	void update(PlanVO planVO);
	void delete(String plan_id);
	PlanVO findByPK(String plan_id);
	List<PlanVO> getfindByPK(Integer mem_id);
	List<PlanVO> getAll();
	List<PlanVO> getAll(Map<String, String[]> map); 
}
