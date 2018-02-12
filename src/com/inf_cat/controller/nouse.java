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
		
		
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				
				String inf_cat_name = req.getParameter("inf_cat_name").trim();
				if (inf_cat_name == null || inf_cat_name.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				
				
				
				Inf_catVO inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(inf_cat_id);
				inf_catVO.setInf_cat_name(inf_cat_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("inf_catVO", inf_catVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/addInf_cat.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				inf_catVO = inf_catSvc.addInf_cat(inf_cat_id, inf_cat_name);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/inf_cat/listAllInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/addInf_cat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("inf_cat_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer inf_cat_id = null;
				try {
					inf_cat_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				Inf_catVO inf_catVO = inf_catSvc.getOneInf_cat(inf_cat_id);
				if (inf_catVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("inf_catVO", inf_catVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/inf_cat/listOneInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/select_page.jsp");
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
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id"));
				
				/***************************2.�}�l�R�����***************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				inf_catSvc.deleteInf_cat(inf_cat_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/inf_cat/listAllInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/listAllInf_cat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				Inf_catVO inf_catVO = inf_catSvc.getOneInf_cat(inf_cat_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("inf_catVO", inf_catVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/inf_cat/update_inf_cat_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/listAllInf_cat.jsp");
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
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());
				
				
				
				String inf_cat_name = req.getParameter("inf_cat_name").trim();
				if (inf_cat_name == null || inf_cat_name.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}	
				
				

				Inf_catVO inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(inf_cat_id);
				inf_catVO.setInf_cat_name(inf_cat_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("inf_catVO", inf_catVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf_cat/update_inf_cat_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Inf_catService inf_catSvc = new Inf_catService();
				inf_catVO = inf_catSvc.updateInf_cat(inf_cat_id, inf_cat_name);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("inf_catVO", inf_catVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/inf_cat/listOneInf_cat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf_cat/update_inf_cat_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
