package com.inf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.inf.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class nouse extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("inf_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer inf_id = null;
				try {
					inf_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				InfService infSvc = new InfService();
				InfVO infVO = infSvc.getOneInf(inf_id);
				if (infVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("infVO", infVO); // 資料庫取出的empVO物件,存入req
				String url = "/inf/listOneInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/select_page.jsp");
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
				
//				Integer inf_id = new Integer(req.getParameter("inf_id").trim());
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				

				String inf_title = req.getParameter("inf_title").trim();
				if (inf_title == null || inf_title.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				String inf_text = req.getParameter("inf_text").trim();
				if (inf_text == null || inf_text.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				Part part =req.getPart("inf_pic");
				InputStream in = part.getInputStream();
				byte[] inf_pic = new byte[in.available()];
				in.read(inf_pic);
				in.close();
				
				
				InfVO infVO = new InfVO();
				infVO.setEmp_id(emp_id);
				infVO.setInf_cat_id(inf_cat_id);
				infVO.setInf_title(inf_title);
				infVO.setInf_text(inf_text);
				infVO.setInf_pic(inf_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infVO", infVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/addInf.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				InfService infSvc = new InfService();
				infVO = infSvc.addInf(emp_id, inf_cat_id, inf_title, inf_text, inf_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/inf/listAllInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/addInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id"));
				
				/***************************2.開始刪除資料***************************************/
				InfService infSvc = new InfService();
				infSvc.deleteInf(inf_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/inf/listAllInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/listAllInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id"));
				
				/***************************2.開始查詢資料****************************************/
				InfService infSvc = new InfService();
				InfVO infVO = infSvc.getOneInf(inf_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("infVO", infVO);         // 資料庫取出的empVO物件,存入req
				String url = "/inf/update_inf_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/listAllInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id").trim());
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				

				String inf_title = req.getParameter("inf_title").trim();
				if (inf_title == null || inf_title.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				String inf_text = req.getParameter("inf_text").trim();
				if (inf_text == null || inf_text.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				Part part =req.getPart("inf_pic");
				InputStream in = part.getInputStream();
				byte[] inf_pic = new byte[in.available()];
				in.read(inf_pic);
				in.close();
				
				
				InfVO infVO = new InfVO();
				infVO.setInf_id(inf_id);
				infVO.setEmp_id(emp_id);
				infVO.setInf_cat_id(inf_cat_id);
				infVO.setInf_title(inf_title);
				infVO.setInf_text(inf_text);
				infVO.setInf_pic(inf_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infVO", infVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/update_inf_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				InfService infSvc = new InfService();
				infVO = infSvc.updateInf(inf_id, emp_id, inf_cat_id, inf_title, inf_text, inf_pic);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("infVO", infVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/inf/listOneInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/update_inf_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
