package com.per.controller;

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
import com.per.model.*;


public class nouse extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req, res);

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(action+"--/");
		
		
		
//		�X�{�\��e��
		if ("9004".equals(action)){
		
		req.setAttribute("9004", "9004"); // ��Ʈw���X��empVO����,�s�Jreq
		String url = "/back/backindex.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
		successView.forward(req, res);
		}
	
	
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				
				String str2 = req.getParameter("fun_id");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("�п�J���s��");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer emp_id = null;
				try {
					emp_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				
				Integer fun_id = null;
				try {
					fun_id = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("���s���榡�����T");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PerService perSvc = new PerService();
				PerVO perVO = perSvc.getOnePer(emp_id, fun_id);
				if (perVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("perVO", perVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/per/listOnePer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/per/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOnePerList".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer emp_id = null;
				try {
					emp_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PerService perSvc = new PerService();
				
				List<PerVO> list  = perSvc.getOnePerList(emp_id);
				System.out.println(list);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/per/listOnePer2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/per/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
         if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer fun_id = new Integer(req.getParameter("fun_id").trim());

				PerVO perVO = new PerVO();
				
				perVO.setEmp_id(emp_id);
				perVO.setFun_id(fun_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("perVO", perVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/per/addPer.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				PerService perSvc = new PerService();
				perVO = perSvc.addPer(emp_id, fun_id);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/per/listAllPer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/per/addPer.jsp");
				failureView.forward(req, res);
			}
		}
         
         if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

 			List<String> errorMsgs = new LinkedList<String>();
 			// Store this set in the request scope, in case we need to
 			// send the ErrorPage view.
 			req.setAttribute("errorMsgs", errorMsgs);
 	
 			try {
 				/***************************1.�����ШD�Ѽ�***************************************/
 				Integer emp_id = new Integer(req.getParameter("emp_id"));
 				Integer fun_id = new Integer(req.getParameter("fun_id"));
 				
 				/***************************2.�}�l�R�����***************************************/
 				PerService perSvc = new PerService();
 				perSvc.deletePer(emp_id, fun_id);
 				
 				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
 				String url = "/per/listAllPer.jsp";
 				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
 				successView.forward(req, res);
 				
 				/***************************��L�i�઺���~�B�z**********************************/
 			} catch (Exception e) {
 				errorMsgs.add("�R����ƥ���:"+e.getMessage());
 				RequestDispatcher failureView = req
 						.getRequestDispatcher("/per/listAllPer.jsp");
 				failureView.forward(req, res);
 			}
 		}
	}

}
