package com.rep.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.*;
import com.emp.model.EmpVO;
import com.mem.model.*;
import com.mem.model.MemVO;
import com.rep.model.*;

public class RepServlet extends HttpServlet {

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
				
				
				String mem_id2 = req.getParameter("mem_id").trim();
				if (mem_id2 == null || mem_id2.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				Integer mem_id = new Integer(mem_id2);
				
				String emp_id2 = req.getParameter("emp_id").trim();
				if (emp_id2 == null || emp_id2.trim().length() == 0) {
					errorMsgs.add("員工編號請勿空白");
				}
				
				Integer emp_id = new Integer(emp_id2);
				
				String cou_id = req.getParameter("cou_id").trim();
				if (cou_id == null || cou_id.trim().length() == 0) {
					errorMsgs.add("課程編號請勿空白");
				}
				
				
				
				String rep_cont = req.getParameter("rep_cont").trim();
				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				
				String rep_status = req.getParameter("rep_status").trim();
				if (rep_status == null || rep_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
				
				
				
				

				RepVO repVO = new RepVO();
				repVO.setMem_id(mem_id);
				repVO.setEmp_id(emp_id);
				repVO.setCou_id(cou_id);
				repVO.setRep_cont(rep_cont);
				repVO.setRep_status(rep_status);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repVO", repVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rep/addRep.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RepService RepSvc = new RepService();
				repVO = RepSvc.addRep(mem_id, emp_id, cou_id, rep_cont, rep_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/rep/listAllRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep/addRep.jsp");
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
				Integer rep_id = new Integer(req.getParameter("rep_id"));
				
				/***************************2.開始刪除資料***************************************/
				RepService repSvc = new RepService();
				repSvc.deleteRep(rep_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/rep/listAllRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep/listAllRep.jsp");
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
				Integer rep_id = new Integer(req.getParameter("rep_id"));
				
				/***************************2.開始查詢資料****************************************/
				RepService repSvc = new RepService();
				RepVO repVO = repSvc.getOneRep(rep_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("repVO", repVO);         // 資料庫取出的empVO物件,存入req
				String url = "/rep/update_rep_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep/listAllRep.jsp");
				failureView.forward(req, res);
			}
		}
        
      if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				
				Integer rep_id = new Integer(req.getParameter("rep_id").trim());
				
				String mem_id2 = req.getParameter("mem_id").trim();
				if (mem_id2 == null || mem_id2.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				Integer mem_id = new Integer(mem_id2);
				
				String emp_id2 = req.getParameter("emp_id").trim();
				if (emp_id2 == null || emp_id2.trim().length() == 0) {
					errorMsgs.add("員工編號請勿空白");
				}
				
				Integer emp_id = new Integer(emp_id2);
				
				String cou_id = req.getParameter("cou_id").trim();
				if (cou_id == null || cou_id.trim().length() == 0) {
					errorMsgs.add("課程編號請勿空白");
				}
				
				
				
				String rep_cont = req.getParameter("rep_cont").trim();
				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				
				String rep_status = req.getParameter("rep_status").trim();
				if (rep_status == null || rep_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
				
//				timestamp時間
				Calendar cal = new GregorianCalendar(); 
				Timestamp rep_date = new Timestamp(cal.getTimeInMillis());
				System.out.println(rep_date);

				RepVO repVO = new RepVO();
				repVO.setMem_id(rep_id);
				repVO.setMem_id(mem_id);
				repVO.setEmp_id(emp_id);
				repVO.setCou_id(cou_id);
				repVO.setRep_cont(rep_cont);
				repVO.setRep_date(rep_date);
				repVO.setRep_status(rep_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repVO", repVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rep/update_rep_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RepService repSvc = new RepService();
				repVO = repSvc.updateRep(rep_id, mem_id, emp_id, cou_id, rep_cont,rep_date, rep_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("repVO", repVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/rep/listOneRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep/update_rep_input.jsp");
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
				String str = req.getParameter("rep_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer rep_id = null;
				try {
					rep_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RepService repSvc = new RepService();
				RepVO repVO = repSvc.getOneRep(rep_id);
				if (repVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("repVO", repVO); // 資料庫取出的empVO物件,存入req
				String url = "/rep/listOneRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep/select_page.jsp");
				failureView.forward(req, res);
			}
		}
      
      
      if ("listReps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				RepService repSvc = new RepService();
				List<RepVO> list  = repSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listReps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/rep/listReps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep/select_page.jsp");
				failureView.forward(req, res);
			}
		}
      
      if ("yes".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				找出員工違規次數加1
				RepService repSvc = new RepService();
				String str = req.getParameter("rep_id");
				RepVO repVO= repSvc.getOneRep(new Integer(str));
				
//				檢舉狀態改成已審核
				Calendar cal = new GregorianCalendar(); 
				Timestamp rep_date = new Timestamp(cal.getTimeInMillis());
				repVO.setRep_date(rep_date);
				repVO.setRep_status("已審核");
				System.out.println(repVO.getRep_status());
				repSvc.updateRep(repVO.getRep_id(), repVO.getMem_id(), repVO.getEmp_id(), repVO.getCou_id(), repVO.getRep_cont(), repVO.getRep_date(), repVO.getRep_status());
				
				
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(repVO.getMem_id());
				memVO.setMem_repno(memVO.getMem_repno()+1);
				System.out.println(memVO.getMem_repno());
				
				memSvc.updateMem(memVO.getMem_id(), memVO.getMem_acct(), memVO.getMem_psw(), memVO.getMem_email(), memVO.getMem_phone(), memVO.getMem_bonus(), memVO.getMem_title(), memVO.getMem_exp(), 
						memVO.getMem_pic(), memVO.getMem_name(), memVO.getMem_gender(), memVO.getMem_add(), memVO.getMem_status(), memVO.getMem_repno());
				
				
//				次數超過3次停權
				if(memVO.getMem_repno()>=3){
					memVO.setMem_status("停權");
					memSvc.updateMem(memVO.getMem_id(), memVO.getMem_acct(), memVO.getMem_psw(), memVO.getMem_email(), memVO.getMem_phone(), memVO.getMem_bonus(), memVO.getMem_title(), memVO.getMem_exp(), 
							memVO.getMem_pic(), memVO.getMem_name(), memVO.getMem_gender(), memVO.getMem_add(), memVO.getMem_status(), memVO.getMem_repno());
					
					
				}
				
				
				
				
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/rep/listAllRep.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rep/listAllRep.jsp");
				failureView.forward(req, res);
			}
		}
      
      if ("no".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
//				檢舉狀態改成已審核
				RepService repSvc = new RepService();
				String str = req.getParameter("rep_id");
				RepVO repVO= repSvc.getOneRep(new Integer(str));
				Calendar cal = new GregorianCalendar(); 
				Timestamp rep_date = new Timestamp(cal.getTimeInMillis());
				repVO.setRep_date(rep_date);
				repVO.setRep_status("已審核");
				System.out.println(repVO.getRep_status());
				repSvc.updateRep(repVO.getRep_id(), repVO.getMem_id(), repVO.getEmp_id(), repVO.getCou_id(), repVO.getRep_cont(), repVO.getRep_date(), repVO.getRep_status());
				
				
				
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/rep/listAllRep.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/rep/listAllRep.jsp");
				failureView.forward(req, res);
			}
		}
      
      
        
	}

}
