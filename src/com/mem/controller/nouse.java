package com.mem.controller;

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

import com.emp.model.*;
import com.emp.model.EmpVO;
import com.mem.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class nouse extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("logout".equals(action)) {
			
			System.out.println(req.getSession().getId()+"-----------------");
			req.getSession().invalidate();		
			System.out.println(req.getSession().getId()+"/////////////////");
			res.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
			
		}
		
        if ("myupdate".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("�b���ФŪť�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�b���u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}
				
				if (mem_psw == null || mem_psw.trim().length() < 6) {
					errorMsgs.add("�K�X���׭n�j�󵥩�6");
				}
				
				String mem_psw_chk = req.getParameter("mem_psw_chk");
				if(!mem_psw.trim().equals(mem_psw_chk.trim())){
					errorMsgs.add("�T�{�K�X���ۦP");
				}
				
				MemService memSvc = new MemService();
				List<MemVO> list = memSvc.getAll();
				
				
				for(MemVO c : list) {
		            System.out.println(c.getMem_acct());
		            if(mem_acct.trim().equals(c.getMem_acct())){
		            	errorMsgs.add("�b���w���H�ϥ�");
		            }
				}
				
				
				
				
				
				
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("�H�c�ФŪť�");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("�m�W�ФŪť�");
				}
				
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("������X�ФŪť�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_title == null || mem_title.trim().length() == 0) {
					errorMsgs.add("���N�Y�νФŪť�");
				}
				

				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//�Ӥ�
				
				Part part =req.getPart("mem_pic");
				byte[] mem_pic=null;
				
				if(part.getSize() == 0){
					mem_pic=memSvc.getOneMem(mem_id).getMem_pic();
					
					
				} else {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					in.close();
				}
				
				
				
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�п�ܩʧO");
				}
				
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("���A�ФŪť�");
				}
				
				
				
				Integer mem_repno = new Integer(req.getParameter("mem_repno").trim());
                
				
//				�ק藍�ݭn
//				String mem_agree = req.getParameter("mem_agree").trim();
//				if (!mem_agree.trim().equals("yes")) {
//					errorMsgs.add("�ЦP�N����");
//				}
				

				
				
				

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_acct(mem_acct);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_bonus(mem_bonus);
				memVO.setMem_title(mem_title);
				memVO.setMem_exp(mem_exp);
				memVO.setMem_pic(mem_pic);
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_add(mem_add);
				memVO.setMem_status(mem_status);
				memVO.setMem_repno(mem_repno);
				
				System.out.println("-----------------");
				System.out.println(mem_id);
				System.out.println(mem_acct);
				System.out.println(mem_psw);
				System.out.println(mem_email);
				System.out.println(mem_phone);
				System.out.println(mem_bonus);
				System.out.println(mem_title);
				System.out.println(mem_exp);
				System.out.println(mem_pic);
				System.out.println(mem_name);
				System.out.println(mem_gender);
				System.out.println(mem_add);
				System.out.println(mem_status);
				System.out.println(mem_repno);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
//				MemService memSvc = new MemService(); //�W���w��memSvc
				memVO = memSvc.updateMem(mem_id, mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�ЦP�N����");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/mem/updatemem.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("register".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("�b���ФŪť�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�b���u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}
				
				if (mem_psw == null || mem_psw.trim().length() < 6) {
					errorMsgs.add("�K�X���׭n�j�󵥩�6");
				}
				
				String mem_psw_chk = req.getParameter("mem_psw_chk");
				if(!mem_psw.trim().equals(mem_psw_chk.trim())){
					errorMsgs.add("�T�{�K�X���ۦP");
				}
				
				MemService memSvc = new MemService();
				List<MemVO> list = memSvc.getAll();
				
				
				for(MemVO c : list) {
		            System.out.println(c.getMem_acct());
		            if(mem_acct.trim().equals(c.getMem_acct())){
		            	errorMsgs.add("�b���w���H�ϥ�");
		            }
				}
				
				
				
				
				
				
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("�H�c�ФŪť�");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("�m�W�ФŪť�");
				}
				
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("������X�ФŪť�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_title == null || mem_title.trim().length() == 0) {
					errorMsgs.add("���N�Y�νФŪť�");
				}
				
				System.out.println(mem_title);

				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//�Ӥ�
				
				Part part =req.getPart("mem_pic");
				System.out.println(part);
				InputStream in = part.getInputStream();
				byte[] mem_pic = new byte[in.available()];
				in.read(mem_pic);
				in.close();
				
				
				
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�п�ܩʧO");
				}
				
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("���A�ФŪť�");
				}
				
				System.out.println(mem_status);
				
				
				Integer mem_repno = new Integer(req.getParameter("mem_repno").trim());

				String mem_agree = req.getParameter("mem_agree").trim();
				if (!mem_agree.trim().equals("yes")) {
					errorMsgs.add("�ЦP�N����");
				}
				
				System.out.println(mem_agree);

				
				
				

				MemVO memVO = new MemVO();
				memVO.setMem_acct(mem_acct);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_bonus(mem_bonus);
				memVO.setMem_title(mem_title);
				memVO.setMem_exp(mem_exp);
				memVO.setMem_pic(mem_pic);
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_add(mem_add);
				memVO.setMem_status(mem_status);
				memVO.setMem_repno(mem_repno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
//				MemService memSvc = new MemService(); //�W���w��memSvc
				memVO = memSvc.addMem(mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�ЦP�N����");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/addMem.jsp");
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
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer mem_id = null;
				try {
					mem_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/select_page.jsp");
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
				Integer mem_id = new Integer(req.getParameter("mem_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/mem/updatemem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/listAllMem.jsp");
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
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("�H�c�ФŪť�");
				}
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("������X�ФŪť�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//�Ӥ�
				
				Part part =req.getPart("mem_pic");
				byte[] mem_pic=null;
				
				System.out.println(part.getSize());
				if(part.getSize() == 0){
					MemService memSvc = new MemService();
					mem_pic=memSvc.getOneMem(mem_id).getMem_pic();
					
					
				} else {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					in.close();
				}
				
				
				
//				String mem_pic = req.getParameter("mem_pic").trim();
//				if (mem_pic == null || mem_pic.trim().length() == 0) {
//					errorMsgs.add("�ФŪť�");
//				}
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("�m�W�ФŪť�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				Integer mem_repno = new Integer(req.getParameter("mem_repno").trim());

				
				
				
				
				
//				byte a =12;
//				byte[] mem_pic = new byte[a];
				

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_acct(mem_acct);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_bonus(mem_bonus);
				memVO.setMem_title(mem_title);
				memVO.setMem_exp(mem_exp);
				memVO.setMem_pic(mem_pic);
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_add(mem_add);
				memVO.setMem_status(mem_status);
				memVO.setMem_repno(mem_repno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_id, mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/update_mem_input.jsp");
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
//				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("�H�c�ФŪť�");
				}
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("������X�ФŪť�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//�Ӥ�
				
				Part part =req.getPart("mem_pic");
				InputStream in = part.getInputStream();
				byte[] mem_pic = new byte[in.available()];
				in.read(mem_pic);
				in.close();
				
//				String mem_pic = req.getParameter("mem_pic").trim();
//				if (mem_pic == null || mem_pic.trim().length() == 0) {
//					errorMsgs.add("�ФŪť�");
//				}
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("�m�W�ФŪť�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				Integer mem_repno = new Integer(req.getParameter("mem_repno").trim());

				
				
				
				
				
//				byte a =12;
//				byte[] mem_pic = new byte[a];
				

				MemVO memVO = new MemVO();
//				memVO.setMem_id(mem_id);
				memVO.setMem_acct(mem_acct);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_bonus(mem_bonus);
				memVO.setMem_title(mem_title);
				memVO.setMem_exp(mem_exp);
				memVO.setMem_pic(mem_pic);
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_add(mem_add);
				memVO.setMem_status(mem_status);
				memVO.setMem_repno(mem_repno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/addMem.jsp");
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
				Integer mem_id = new Integer(req.getParameter("mem_id"));
				
				/***************************2.�}�l�R�����***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
