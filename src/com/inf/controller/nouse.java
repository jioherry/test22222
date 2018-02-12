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
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("inf_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer inf_id = null;
				try {
					inf_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				InfService infSvc = new InfService();
				InfVO infVO = infSvc.getOneInf(inf_id);
				if (infVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("infVO", infVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/inf/listOneInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/select_page.jsp");
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
				
//				Integer inf_id = new Integer(req.getParameter("inf_id").trim());
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				

				String inf_title = req.getParameter("inf_title").trim();
				if (inf_title == null || inf_title.trim().length() == 0) {
					errorMsgs.add("¾��ФŪť�");
				}
				String inf_text = req.getParameter("inf_text").trim();
				if (inf_text == null || inf_text.trim().length() == 0) {
					errorMsgs.add("¾��ФŪť�");
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
					req.setAttribute("infVO", infVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/addInf.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				InfService infSvc = new InfService();
				infVO = infSvc.addInf(emp_id, inf_cat_id, inf_title, inf_text, inf_pic);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/inf/listAllInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/addInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id"));
				
				/***************************2.�}�l�R�����***************************************/
				InfService infSvc = new InfService();
				infSvc.deleteInf(inf_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/inf/listAllInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/listAllInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				InfService infSvc = new InfService();
				InfVO infVO = infSvc.getOneInf(inf_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("infVO", infVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/inf/update_inf_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/listAllInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id").trim());
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				

				String inf_title = req.getParameter("inf_title").trim();
				if (inf_title == null || inf_title.trim().length() == 0) {
					errorMsgs.add("¾��ФŪť�");
				}
				String inf_text = req.getParameter("inf_text").trim();
				if (inf_text == null || inf_text.trim().length() == 0) {
					errorMsgs.add("¾��ФŪť�");
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
					req.setAttribute("infVO", infVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/update_inf_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				InfService infSvc = new InfService();
				infVO = infSvc.updateInf(inf_id, emp_id, inf_cat_id, inf_title, inf_text, inf_pic);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("infVO", infVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/inf/listOneInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/update_inf_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
