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
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("plan_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入計畫編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String plan_id = null;
				try {
					plan_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getOnePlan(plan_id);
				if (planVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("planVO", planVO); // 資料庫取出的planVO物件,存入req
				String url = "/front_end/plan/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneplan.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("addcou_toplan".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String plan_id = req.getParameter("plan_id");
				Integer mem_id = Integer.parseInt(req.getParameter("mem_id"));
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
//				PlanService planSvc = new PlanService();
//				PlanVO planVO = planSvc.getOnePlan(plan_id);
//				if (planVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("plan_id", plan_id); // 資料庫取出的planVO物件,存入req
				req.setAttribute("mem_id", mem_id); // 資料庫取出的planVO物件,存入req
				String url = "/front_end/plan/addcou_toplan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneplan.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/select_page.jsp");
				failureView.forward(req, res);

			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPlan.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String plan_id = new String(req.getParameter("plan_id"));
				
				/***************************2.開始查詢資料****************************************/
				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getOnePlan(plan_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("planVO", planVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front_end/plan/update_plan_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/listAllPlan.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String plan_id = req.getParameter("plan_id").trim();
				
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				String shareplan = req.getParameter("shareplan").trim();	

				String plan_name = req.getParameter("plan_name");
				String plan_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (plan_name == null || plan_name.trim().length()== 0) {
					errorMsgs.add("計畫名稱: 請勿空白");
				} else if(!plan_name.trim().matches(plan_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("計畫名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				java.sql.Date startdate = null;
				try {
					startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim());
				} catch (IllegalArgumentException e) {
					startdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer interval = null;
				try {
					interval = new Integer(req.getParameter("interval").trim());
				} catch (NumberFormatException e) {
					interval = 0;
					errorMsgs.add("間隔次數請填數字.");
				}

				Integer cycle = null;
				try {
					cycle = new Integer(req.getParameter("cycle").trim());
				} catch (NumberFormatException e) {
					cycle = 0;
					errorMsgs.add("循環次數請填數字.");
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
					req.setAttribute("planVO", planVO); // 含有輸入格式錯誤的planVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/update_plan_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PlanService planSvc = new PlanService();
				planVO = planSvc.updatePlan(plan_id, mem_id, shareplan, plan_name, startdate,interval, cycle);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("planVO", planVO); // 資料庫update成功後,正確的的planVO物件,存入req
				String url = "/front_end/plan/listAllPlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePlan.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/update_plan_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPlan.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_id = null;
				try {
					mem_id = new Integer(req.getParameter("mem_id").trim());
				} catch (NumberFormatException e) {
					mem_id = 0;
					errorMsgs.add("請填數字.");
				}
				System.out.println(mem_id);
				
				String shareplan = req.getParameter("shareplan").trim();
				if (shareplan == null || shareplan.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				String plan_name = req.getParameter("plan_name").trim();
				if (plan_name == null || plan_name.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				java.sql.Date startdate = new java.sql.Date(System.currentTimeMillis());
//				try {
//					startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim());
//				} catch (IllegalArgumentException e) {
//					startdate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
		
				
				Integer interval = null;
				try {
					interval = new Integer(req.getParameter("interval").trim());
				} catch (NumberFormatException e) {
					interval = 0;
					errorMsgs.add("請填數字.");
				}
				
				Integer cycle = null;
				try {
					cycle = new Integer(req.getParameter("cycle").trim());
				} catch (NumberFormatException e) {
					cycle = 0;
					errorMsgs.add("請填數字.");
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
					req.setAttribute("planVO", planVO); // 含有輸入格式錯誤的planVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan/addPlan.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PlanService planSvc = new PlanService();
				planVO = planSvc.addPlan(mem_id,shareplan, plan_name, startdate, interval, cycle);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/plan/myplan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPlan.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/addPlan.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllPlan.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String plan_id = new String(req.getParameter("plan_id"));
				
				/***************************2.開始刪除資料***************************************/
				PlanService planSvc = new PlanService();
				planSvc.deletePlan(plan_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/plan/listAllPlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/listAllPlan.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listPlan_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
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
				
				/***************************2.開始複合查詢***************************************/
				PlanService planSvc = new PlanService();
				List<PlanVO> list  = planSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listPlan_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/plan/listPlan_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

