package com.plan.model;

import java.util.List;

public class TestPlanDAO {

	public static void main(String[] args) {
		PlanDAO dao = new PlanDAO();
		
		// �憓�
//		PlanVO plan = new PlanVO();
//		plan.setMem_id(7004);
//		plan.setShareplan("on");
//		plan.setPlan_name("linplan");
//		plan.setStartdate(java.sql.Date.valueOf("2017-12-20"));
//		plan.setInterval(0);
//		plan.setCycle(0);
//		dao.insert(plan);
//		
		//靽格
//		Plan plan2 = new Plan();
//		plan2.setPlan_id("P00010");
//		plan2.setMem_id(7005);
//		plan2.setShareplan("on");
//		plan2.setPlan_name("plan");
//		plan2.setStartdate(java.sql.Date.valueOf("2017-12-17"));
//		plan2.setInterval(0);
//		plan2.setCycle(0);
//		dao.update(plan2);
		
		//��
//		dao.delete("P00010");
		
		//�閰�
//		Plan plan3 = dao.findByPK("P00007");
//		System.out.print(plan3.getPlan_id() + ",");
//		System.out.print(plan3.getMem_id() + ",");
//		System.out.print(plan3.getShareplan() + ",");
//		System.out.print(plan3.getPlan_name() + ",");
//		System.out.print(plan3.getStartdate() + ",");
//		System.out.print(plan3.getInterval() + ",");
//		System.out.println(plan3.getCycle());
//		System.out.println("---------------------");
		
		//���閰�
		List<PlanVO> list = dao.getfindByPK(7002);
		for (PlanVO plan4 : list) {
			System.out.print(plan4.getPlan_id() + ",");
			System.out.print(plan4.getMem_id() + ",");
			System.out.print(plan4.getShareplan() + ",");
			System.out.print(plan4.getPlan_name() + ",");
			System.out.print(plan4.getStartdate() + ",");
			System.out.print(plan4.getInterval() + ",");
			System.out.println(plan4.getCycle());
			System.out.println("---------------------");
		}
	}

}
