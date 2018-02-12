package com.emp.controller;

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
import com.tool.MailService;

public class EmpServlet extends HttpServlet {
       

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
			res.sendRedirect(req.getContextPath()+"/back_end/login.jsp");
			return;
			
		}
		
		if ("getOne_For_Display".equals(action)) { // 靘select_page.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓��撌亦楊���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				Integer emp_id = null;
				try {
					emp_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�撌亦楊��撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
				if (empVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		if ("insert".equals(action)) { // 靘addEmp.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���*************************/
				
				String emp_acct = req.getParameter("emp_acct").trim();
				if (emp_acct == null || emp_acct.trim().length() == 0) {
					errorMsgs.add("撣唾��蝛箇");
				}
				
				
//				銝�閬�Ⅳ
//				String emp_psw = req.getParameter("emp_psw").trim();
//				if (emp_psw == null || emp_psw.trim().length() == 0) {
//					errorMsgs.add("撖Ⅳ隢蝛箇");
//				}
				
				
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("�撌亙���: 隢蝛箇");
				} else if(!emp_name.trim().matches(emp_nameReg)) { //隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("�撌亙���: ���銝准������摮� , 銝摨血���2�10銋��");
	            }
				
				String emp_email = req.getParameter("emp_email").trim();
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("靽∠拳隢蝛箇");
				}
				
				String emp_role = req.getParameter("emp_role").trim();
				if (emp_role == null || emp_role.trim().length() == 0) {
					errorMsgs.add("頨怠��蝛箇");
				}
				
				
				
				String emp_psw = getAuthCode().trim();
				System.out.println(emp_psw);
				
//				撖縑
				
				String to = emp_email;
			      
			      String subject = "密碼通知";
			      
			      String ch_name = emp_name;
			      String passRandom = emp_psw;
			      String messageText = "Hello! " + ch_name + " 請記住: 帳號"+emp_acct+"密碼" + passRandom + "\n" ; 
			       
			      MailService mailService = new MailService();
			      mailService.sendMail(to, subject, messageText);
				
				
				

				EmpVO empVO = new EmpVO();
				
				empVO.setEmp_acct(emp_acct);
				empVO.setEmp_psw(emp_psw);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_role(emp_role);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���憓���***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(emp_acct, emp_psw, emp_name, emp_email,
						emp_role);
				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
				String url = "/back_end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/back_index_1.jsp");
				failureView.forward(req, res);
			}
			
			
			
			
		}
	
		if ("delete".equals(action)) { // 靘listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.��隢��***************************************/
				Integer emp_id = new Integer(req.getParameter("emp_id"));
				
				/***************************2.����鞈��***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(emp_id);
				
				/***************************3.��摰��,皞��漱(Send the Success view)***********/								
				String url = "/back_end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("��鞈�仃���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 靘listAllEmp.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.��隢��****************************************/
				Integer emp_id = new Integer(req.getParameter("emp_id"));
				
				/***************************2.���閰Ｚ���****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
								
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 鞈�澈����mpVO�隞�,摮req
				String url = "/back_end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 靘update_emp_input.jsp�����
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				
				
				String emp_acct = req.getParameter("emp_acct").trim();
				if (emp_acct == null || emp_acct.trim().length() == 0) {
					errorMsgs.add("撣唾��蝛箇");
				}
				

				String emp_psw = req.getParameter("emp_psw").trim();
				if (emp_psw == null || emp_psw.trim().length() == 0) {
					errorMsgs.add("撖Ⅳ隢蝛箇");
				}
				
				
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("�撌亙���: 隢蝛箇");
				} else if(!emp_name.trim().matches(enameReg)) { //隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("�撌亙���: ���銝准������摮� , 銝摨血���2�10銋��");
	            }
				
				
				String emp_email = req.getParameter("emp_email").trim();
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("靽∠拳隢蝛箇");
				}
				
				String emp_role = req.getParameter("emp_role").trim();
				if (emp_role == null || emp_role.trim().length() == 0) {
					errorMsgs.add("�雿�蝛箇");
				}	
				
				
				
				

				EmpVO empVO = new EmpVO();
				empVO.setEmp_id(emp_id);
				empVO.setEmp_acct(emp_acct);
				empVO.setEmp_psw(emp_psw);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_role(emp_role);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //蝔�葉�
				}
				
				/***************************2.���耨�鞈��*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, emp_acct, emp_psw, emp_name, emp_email, emp_role);
				
				/***************************3.靽格摰��,皞��漱(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 鞈�澈update�����,甇�蝣箇��mpVO�隞�,摮req
				String url = "/back_end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("靽格鞈�仃���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}



	}
	
			public String getAuthCode(){
				StringBuilder sb = new StringBuilder();
				int i=0;
				while(i<8){
				int x = (int)(Math.random()*75)+48 ;		
					if((x>=48 && x<=57) || (x>=65 && x<=90) || (x>=97 && x<=122)){
					    sb.append(((char)x));
					    i++;
					}
				}
				return sb.toString();
			}

}
