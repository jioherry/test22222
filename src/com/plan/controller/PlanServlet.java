package com.plan.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.plan.model.*;

public class PlanServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		System.out.println(action);
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("plan_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�p�e�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String plan_id = null;
				try {
					plan_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getOnePlan(plan_id);
				if (planVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("planVO", planVO); // ��Ʈw���X��planVO����,�s�Jreq
				String url = "/front_end/plan/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneplan.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("addcou_toplan".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String plan_id = req.getParameter("plan_id");
				Integer mem_id = Integer.parseInt(req.getParameter("mem_id"));
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
//				PlanService planSvc = new PlanService();
//				PlanVO planVO = planSvc.getOnePlan(plan_id);
//				if (planVO == null) {
//					errorMsgs.add("�d�L���");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("plan_id", plan_id); // ��Ʈw���X��planVO����,�s�Jreq
				req.setAttribute("mem_id", mem_id); // ��Ʈw���X��planVO����,�s�Jreq
				String url = "/front_end/plan/addcou_toplan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneplan.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/select_page.jsp");
				failureView.forward(req, res);

			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllPlan.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String plan_id = new String(req.getParameter("plan_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getOnePlan(plan_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("planVO", planVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front_end/plan/update_plan_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/listAllPlan.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				String plan_id = req.getParameter("plan_id").trim();
				
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				String shareplan = req.getParameter("shareplan").trim();	

				String plan_name = req.getParameter("plan_name");
				String plan_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (plan_name == null || plan_name.trim().length()== 0) {
					errorMsgs.add("�p�e�W��: �ФŪť�");
				} else if(!plan_name.trim().matches(plan_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�p�e�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				java.sql.Date startdate = null;
				try {
					startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim());
				} catch (IllegalArgumentException e) {
					startdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				Integer interval = null;
				try {
					interval = new Integer(req.getParameter("interval").trim());
				} catch (NumberFormatException e) {
					interval = 0;
					errorMsgs.add("���j���ƽж�Ʀr.");
				}

				Integer cycle = null;
				try {
					cycle = new Integer(req.getParameter("cycle").trim());
				} catch (NumberFormatException e) {
					cycle = 0;
					errorMsgs.add("�`�����ƽж�Ʀr.");
				}

				PlanVO planVO = new PlanVO();
				planVO.setPlan_id(plan_id);
				planVO.setMem_id(mem_id);
				planVO.setShareplan(shareplan);
				planVO.setPlan_name(plan_name);
				planVO.setStartdate(startdate);
				planVO.setInterval(interval);
				planVO.setCycle(cycle);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("planVO", planVO); // �t����J�榡���~��planVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/update_plan_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				PlanService planSvc = new PlanService();
				planVO = planSvc.updatePlan(plan_id, mem_id, shareplan, plan_name, startdate,interval, cycle);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("planVO", planVO); // ��Ʈwupdate���\��,���T����planVO����,�s�Jreq
				String url = "/front_end/plan/listAllPlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOnePlan.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/update_plan_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addPlan.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer mem_id = null;
				try {
					mem_id = new Integer(req.getParameter("mem_id").trim());
				} catch (NumberFormatException e) {
					mem_id = 0;
					errorMsgs.add("�ж�Ʀr.");
				}
				System.out.println(mem_id);
				
				String shareplan = req.getParameter("shareplan").trim();
				if (shareplan == null || shareplan.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				String plan_name = req.getParameter("plan_name").trim();
				if (plan_name == null || plan_name.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				java.sql.Date startdate = new java.sql.Date(System.currentTimeMillis());
//				try {
//					startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim());
//				} catch (IllegalArgumentException e) {
//					startdate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
		
				
				Integer interval = null;
				try {
					interval = new Integer(req.getParameter("interval").trim());
				} catch (NumberFormatException e) {
					interval = 0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				Integer cycle = null;
				try {
					cycle = new Integer(req.getParameter("cycle").trim());
				} catch (NumberFormatException e) {
					cycle = 0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				PlanVO planVO = new PlanVO();
				planVO.setMem_id(mem_id);
				planVO.setShareplan(shareplan);
				planVO.setPlan_name(plan_name);
				planVO.setStartdate(startdate);
				planVO.setInterval(interval);
				planVO.setCycle(cycle);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("planVO", planVO); // �t����J�榡���~��planVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/addPlan.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				PlanService planSvc = new PlanService();
				planVO = planSvc.addPlan(mem_id,shareplan, plan_name, startdate, interval, cycle);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/plan/myplan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllPlan.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/addPlan.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllPlan.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String plan_id = new String(req.getParameter("plan_id"));
				
				/***************************2.�}�l�R�����***************************************/
				PlanService planSvc = new PlanService();
				planSvc.deletePlan(plan_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front_end/plan/listAllPlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/listAllPlan.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listPlan_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = new HashMap<String, String[]>(req.getParameterMap());
				} 
				
				/***************************2.�}�l�ƦX�d��***************************************/
				PlanService planSvc = new PlanService();
				List<PlanVO> list  = planSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listPlan_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/plan/listPlan_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

