package com.plan_detail.model;

import java.util.List;

public class TestPlan_Detail {

	public static void main(String[] args) {
		Plan_DetailDAO dao = new Plan_DetailDAO();
		
		// �憓�
		Plan_DetailVO plan_detail = new Plan_DetailVO();
		plan_detail.setPlan_id("P00003");
		plan_detail.setCou_id("12");
		dao.insert(plan_detail);
		
		
		//��
//		dao.delete("P00001", 1);
		
		//�閰㎜K�蝑�
//		Plan_Detail plan_detail3 = dao.findByPK("P00001", "2");
//		System.out.print(plan_detail3.getPlan_id() + ",");
//		System.out.print(plan_detail3.getCou_id() + ",");
//		System.out.println("---------------------");

		//�閰㎜K��
//		List<Plan_Detail> list = dao.getByPK("P00001");
//		for (Plan_Detail plan_detail4 : list) {
//			System.out.print(plan_detail4.getPlan_id() + ",");
//		    System.out.print(plan_detail4.getCou_id() + ",");
//			System.out.println("---------------------");
//		}
		
		
		//���閰�
//		List<Plan_Detail> list2 = dao.getAll();
//		for (Plan_Detail plan_detail5 : list2) {
//			System.out.print(plan_detail5.getPlan_id() + ",");
//		    System.out.print(plan_detail5.getCou_id() + ",");
//			System.out.println("---------------------");
//		}		
	}
}
