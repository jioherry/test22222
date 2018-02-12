package com.rep.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inf.model.*;
import com.rep.model.RepService;
import com.rep.model.RepVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AjaxRepServlet extends HttpServlet {
       
    public AjaxRepServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println(action);
		req.setCharacterEncoding("UTF-8");
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
//				測試
//				String mem_id2 = req.getParameter("mem_id").trim();
//				if (mem_id2 == null || mem_id2.trim().length() == 0) {
//					errorMsgs.add("會員編號請勿空白");
//				}
//				
//				Integer mem_id = new Integer(mem_id2);
//				
//				String emp_id2 = req.getParameter("emp_id").trim();
//				if (emp_id2 == null || emp_id2.trim().length() == 0) {
//					errorMsgs.add("員工編號請勿空白");
//				}
//				
//				Integer emp_id = new Integer(emp_id2);
//				
//				String cou_id = req.getParameter("cou_id").trim();
//				if (cou_id == null || cou_id.trim().length() == 0) {
//					errorMsgs.add("課程編號請勿空白");
//				}
//				
//				
//				
				String rep_cont2 = req.getParameter("rep_cont").trim();
				if (rep_cont2 == null || rep_cont2.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				String rep_cont=new String(rep_cont2.getBytes("ISO-8859-1"), "UTF-8");
				System.out.println(rep_cont);
				
//				
//				
//				String rep_status = req.getParameter("rep_status").trim();
//				if (rep_status == null || rep_status.trim().length() == 0) {
//					errorMsgs.add("狀態請勿空白");
//				}
				
//				假資料
				
				Integer mem_id = 7003;
				Integer emp_id = 8001;
				String cou_id ="C000002";
				String rep_status="未審核";

				RepVO repVO = new RepVO();
				repVO.setMem_id(mem_id);
				repVO.setEmp_id(emp_id);
				repVO.setCou_id(cou_id);
				repVO.setRep_cont(rep_cont);
				repVO.setRep_status(rep_status);
				
				
				

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("repVO", repVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/rep/addRep.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
				RepService RepSvc = new RepService();
				repVO = RepSvc.addRep(mem_id, emp_id, cou_id, rep_cont, rep_status);
				

				
				
				JSONObject obj = new JSONObject();
				
				InfService infSvc = new InfService();
				List<InfVO> infList = infSvc.getAll();
				
		
				try {
					obj.put("infList", infList);
				} catch (Exception e) {
				}
				
				
				
				
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
				
				
				
				
				
				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/rep/listAllRep.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rep/addRep.jsp");
//				failureView.forward(req, res);
//			}
		}
	}

}
