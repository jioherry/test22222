package com.plan_detail.model;

import java.util.List;

import com.plan.model.PlanVO;

public interface Plan_Detail_interface {
	void insert(Plan_DetailVO plan_detail);
	void update(Plan_DetailVO plan_detail);
	void delete(Integer pd_no);
	Plan_DetailVO findByPK(Integer pd_no);
	List<Plan_DetailVO> getByPK(String plan_id);
	List<Plan_DetailVO> getAll();
}
