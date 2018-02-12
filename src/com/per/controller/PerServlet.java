package com.per.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.per.model.*;


public class PerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req, res);

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(action+"--/");
		
		

		
		
//		增加功能畫面
		if ("addPer".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			
				try{
			
				PerService perSvc = new PerService();
				
				String[] fun_id = req.getParameterValues("fun_id");
				Integer emp_id = new Integer(req.getParameter("emp_id"));
				List<PerVO> list = perSvc.getOnePerList(emp_id);
				
				EmpService empSvc = new EmpService();		
				
				
				
//				刪除原本功能
				for(PerVO perVO:list){
					
					perSvc.deletePer(emp_id, perVO.getFun_id());
					
				}
				
				
//				新增
				for(int i=0;i<fun_id.length;i++){
					System.out.println(fun_id[i]);
					perSvc.addPer(emp_id, new Integer(fun_id[i]));
				}
				
				
//				更新員工資料
				EmpVO empVO = empSvc.getOneEmp(emp_id);
				empVO.setEmp_role("有權限");
				
				empSvc.updateEmp(empVO.getEmp_id(), empVO.getEmp_acct(), 
						empVO.getEmp_psw(), empVO.getEmp_name(), empVO.getEmp_email(), empVO.getEmp_role());
				
				

			
			
			String url = "/back_end/per/addEmpPer.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			
				}catch(Exception e) {
					errorMsgs.add("請勾選權限功能" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/per/addEmpPer.jsp");
					failureView.forward(req, res);
				}
			
		}
		
		
	
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				
				String str2 = req.getParameter("fun_id");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入功能編號");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer emp_id = null;
				try {
					emp_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				
				Integer fun_id = null;
				try {
					fun_id = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("功能編號格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PerService perSvc = new PerService();
				PerVO perVO = perSvc.getOnePer(emp_id, fun_id);
				if (perVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("perVO", perVO); // 資料庫取出的empVO物件,存入req
				String url = "/per/listOnePer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/per/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOnePerList".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer emp_id = null;
				try {
					emp_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PerService perSvc = new PerService();
				
				List<PerVO> list  = perSvc.getOnePerList(emp_id);
				System.out.println(list);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/per/listOnePer2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/per/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
         if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer fun_id = new Integer(req.getParameter("fun_id").trim());

				PerVO perVO = new PerVO();
				
				perVO.setEmp_id(emp_id);
				perVO.setFun_id(fun_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("perVO", perVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/addPer.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PerService perSvc = new PerService();
				perVO = perSvc.addPer(emp_id, fun_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/per/listAllPer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/per/addPer.jsp");
				failureView.forward(req, res);
			}
		}
         
         if ("delete".equals(action)) { // 來自listAllEmp.jsp

 			List<String> errorMsgs = new LinkedList<String>();
 			// Store this set in the request scope, in case we need to
 			// send the ErrorPage view.
 			req.setAttribute("errorMsgs", errorMsgs);
 	
 			try {
 				/***************************1.接收請求參數***************************************/
 				Integer emp_id = new Integer(req.getParameter("emp_id"));
 				Integer fun_id = new Integer(req.getParameter("fun_id"));
 				
 				/***************************2.開始刪除資料***************************************/
 				PerService perSvc = new PerService();
 				perSvc.deletePer(emp_id, fun_id);
 				
 				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
 				String url = "/back/per/deleteEmpPer.jsp";
 				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
 				successView.forward(req, res);
 				
 				/***************************其他可能的錯誤處理**********************************/
 			} catch (Exception e) {
 				errorMsgs.add("刪除資料失敗:"+e.getMessage());
 				RequestDispatcher failureView = req
 						.getRequestDispatcher("/back/per/deleteEmpPer.jsp");
 				failureView.forward(req, res);
 			}
 		}
	}

}
