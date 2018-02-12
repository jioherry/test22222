package com.inf_cat.controller;

import java.io.IOException;
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
import com.inf_cat.model.*;




public class nouse extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				
				String inf_cat_name = req.getParameter("inf_cat_name").trim();
				if (inf_cat_name == null || inf_cat_name.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				
				
				
				Inf_catVO inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(inf_cat_id);
				inf_catVO.setInf_cat_name(inf_cat_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("inf_catVO", inf_catVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/addInf_cat.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				inf_catVO = inf_catSvc.addInf_cat(inf_cat_id, inf_cat_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/inf_cat/listAllInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/addInf_cat.jsp");
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
				String str = req.getParameter("inf_cat_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer inf_cat_id = null;
				try {
					inf_cat_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				Inf_catVO inf_catVO = inf_catSvc.getOneInf_cat(inf_cat_id);
				if (inf_catVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("inf_catVO", inf_catVO); // 資料庫取出的empVO物件,存入req
				String url = "/inf_cat/listOneInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/select_page.jsp");
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
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id"));
				
				/***************************2.開始刪除資料***************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				inf_catSvc.deleteInf_cat(inf_cat_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/inf_cat/listAllInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/listAllInf_cat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id"));
				
				/***************************2.開始查詢資料****************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				Inf_catVO inf_catVO = inf_catSvc.getOneInf_cat(inf_cat_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("inf_catVO", inf_catVO);         // 資料庫取出的empVO物件,存入req
				String url = "/inf_cat/update_inf_cat_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/listAllInf_cat.jsp");
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
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());
				
				
				
				String inf_cat_name = req.getParameter("inf_cat_name").trim();
				if (inf_cat_name == null || inf_cat_name.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}	
				
				

				Inf_catVO inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(inf_cat_id);
				inf_catVO.setInf_cat_name(inf_cat_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("inf_catVO", inf_catVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/update_inf_cat_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				inf_catVO = inf_catSvc.updateInf_cat(inf_cat_id, inf_cat_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("inf_catVO", inf_catVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/inf_cat/listOneInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/update_inf_cat_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
