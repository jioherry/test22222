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

public class MemServlet extends HttpServlet {

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
			res.sendRedirect(req.getContextPath()+"/front_end/index.jsp");
			return;
			
		}
		
        if ("myupdate".equals(action)) { // 嚙踝蕭謏�嚙踐��ddEmp.jsp���嚙踝蕭��嚙踝蕭豯株��蕭  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱� - 嚙踝蕭謜眾嚙踝���嚙踝蕭謢遴���蕭謖蕭��狗嚙踐���飭���*************************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("嚙踐�蕭����蕭��嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //嚙踝蕭豯�曌��蕭賹蕭嚙踐�蕭嚙踝蝮������蕭(嚙踐��蕭)嚙踝�蕭��蕭�嚙踝蕭謏��(regular-expression)
					errorMsgs.add("嚙踐�蕭����蕭蹐���蕭��雓ｇ蕭嚙踝蕭豲蕭��頩����嚙踝���蕭謍箄嚙踝蕭��頩�敢嚙踐雓�嚙踝蕭嚙� , 嚙踝蕭豲蕭��敦嚙踐�嚙賢�����雓蕭2���蕭嚙�10嚙踝蕭�����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踝蕭謖蕭��嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				if (mem_psw == null || mem_psw.trim().length() < 6) {
					errorMsgs.add("嚙踝蕭謖蕭�����敦嚙踐���頩２嚙踝�雓嚙踐���蕭6");
				}
				
				String mem_psw_chk = req.getParameter("mem_psw_chk");
				if(!mem_psw.trim().equals(mem_psw_chk.trim())){
					errorMsgs.add("嚙踐���嚙踝蕭謅��嚙踝蕭��嚙踝蕭豲蕭謕蕭蹎刻���");
				}
				
				MemService memSvc = new MemService();
				List<MemVO> list = memSvc.getAll();
				
				
				for(MemVO c : list) {
		            System.out.println(c.getMem_acct());
		            if(mem_acct.trim().equals(c.getMem_acct())){
		            	errorMsgs.add("嚙踐�蕭����蕭蹓潘蕭������蕭嚙踐�謆折��蕭嚙�");
		            }
				}
				
				
				
				
				
				
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("嚙踐�蕭嚙踐�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("嚙踝�蕭謇啣��蕭謍箄嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("���嚙踝蕭豯株鞈對�鞈對�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_title == null || mem_title.trim().length() == 0) {
					errorMsgs.add("���嚙踝蕭��蕭謚渲韏臭����狩���蕭豲�蕭嚙踐�蕭�韏�");
				}
				

				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//��������蕭
				
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
					errorMsgs.add("������嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踐��������都�嚙踝蕭嚙�");
				}
				
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("������嚙踝蕭蹎刻嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				
				
				Integer mem_repno = new Integer(req.getParameter("mem_repno").trim());
                
				
//				嚙踐�蕭赯蛛蕭嚙踐�嚙踐�蕭謜嚙踐��蕭
//				String mem_agree = req.getParameter("mem_agree").trim();
//				if (!mem_agree.trim().equals("yes")) {
//					errorMsgs.add("嚙踐狎嚙踝蕭豲嚙踐ㄞ������蕭����");
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
					req.setAttribute("memVO", memVO); // ���蕭������蕭嚙踐�嚙踝���嚙踝蕭謢遴�蕭嚙踝�狗嚙踐���儒mpVO���嚙踝蕭豯株,嚙踝蕭�嚙踐�雓Ｗ�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/index.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���嚙踝蕭豲嚙踝蕭豯哨蕭�嚙踐蝑�����***************************************/
//				MemService memSvc = new MemService(); //嚙踝蕭豲蕭��蕭��蕭�����蕭�mSvc
				memVO = memSvc.updateMem(mem_id, mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.���蕭�嚙踐蝡����ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)***********/
				String url = "/front_end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���蕭�嚙踐蝑��蕭��鞈察�嚙踐ㄠ�����蓮istAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踐狎嚙踝蕭豲嚙踐ㄞ������蕭����");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/index.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("register".equals(action)) { // 嚙踝蕭謏�嚙踐��ddEmp.jsp���嚙踝蕭��嚙踝蕭豯株��蕭  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱� - 嚙踝蕭謜眾嚙踝���嚙踝蕭謢遴���蕭謖蕭��狗嚙踐���飭���*************************/
				
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("嚙踐�蕭����蕭��嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //嚙踝蕭豯�曌��蕭賹蕭嚙踐�蕭嚙踝蝮������蕭(嚙踐��蕭)嚙踝�蕭��蕭�嚙踝蕭謏��(regular-expression)
					errorMsgs.add("嚙踐�蕭����蕭蹐���蕭��雓ｇ蕭嚙踝蕭豲蕭��頩����嚙踝���蕭謍箄嚙踝蕭��頩�敢嚙踐雓�嚙踝蕭嚙� , 嚙踝蕭豲蕭��敦嚙踐�嚙賢�����雓蕭2���蕭嚙�10嚙踝蕭�����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踝蕭謖蕭��嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				if (mem_psw == null || mem_psw.trim().length() < 6) {
					errorMsgs.add("嚙踝蕭謖蕭�����敦嚙踐���頩２嚙踝�雓嚙踐���蕭6");
				}
				
				String mem_psw_chk = req.getParameter("mem_psw_chk");
				if(!mem_psw.trim().equals(mem_psw_chk.trim())){
					errorMsgs.add("嚙踐���嚙踝蕭謅��嚙踝蕭��嚙踝蕭豲蕭謕蕭蹎刻���");
				}
				
				MemService memSvc = new MemService();
				List<MemVO> list = memSvc.getAll();
				
				
				for(MemVO c : list) {
		            System.out.println(c.getMem_acct());
		            if(mem_acct.trim().equals(c.getMem_acct())){
		            	errorMsgs.add("嚙踐�蕭����蕭蹓潘蕭������蕭嚙踐�謆折��蕭嚙�");
		            }
				}
				
				
				
				
				
				
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("嚙踐�蕭嚙踐�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("嚙踝�蕭謇啣��蕭謍箄嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("���嚙踝蕭豯株鞈對�鞈對�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_title == null || mem_title.trim().length() == 0) {
					errorMsgs.add("���嚙踝蕭��蕭謚渲韏臭����狩���蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				System.out.println(mem_title);

				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//��������蕭
				
				Part part =req.getPart("mem_pic");
				System.out.println(part);
				InputStream in = part.getInputStream();
				byte[] mem_pic = new byte[in.available()];
				in.read(mem_pic);
				in.close();
				
				
				
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("������嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踐��������都�嚙踝蕭嚙�");
				}
				
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("������嚙踝蕭蹎刻嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				System.out.println(mem_status);
				
				
				Integer mem_repno = new Integer(req.getParameter("mem_repno").trim());

				String mem_agree = req.getParameter("mem_agree").trim();
				if (!mem_agree.trim().equals("yes")) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲嚙踐ㄞ������蕭����");
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
					req.setAttribute("memVO", memVO); // ���蕭������蕭嚙踐�嚙踝���嚙踝蕭謢遴�蕭嚙踝�狗嚙踐���儒mpVO���嚙踝蕭豯株,嚙踝蕭�嚙踐�雓Ｗ�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/login/login.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���嚙踝蕭豲嚙踝蕭豯哨蕭�嚙踐蝑�����***************************************/
//				MemService memSvc = new MemService(); //嚙踝蕭豲蕭��蕭��蕭�����蕭�mSvc
				memVO = memSvc.addMem(mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.���蕭�嚙踐蝡����ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)***********/
				String url = "/front_end/login/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���蕭�嚙踐蝑��蕭��鞈察�嚙踐ㄠ�����蓮istAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踐狎嚙踝蕭豲嚙踐ㄞ������蕭����");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Display".equals(action)) { // 嚙踝蕭謏�嚙踐��elect_page.jsp���嚙踝蕭��嚙踝蕭豯株��蕭

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱� - 嚙踝蕭謜眾嚙踝���嚙踝蕭謢遴���蕭謖蕭��狗嚙踐���飭���**********************/
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭蹎蕭��嚙踝���嚙踐�蕭謒蕭豰蕭����");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙踝蕭��蕭豲嚙踐�蕭嚙踝�雓蕭
				}
				
				Integer mem_id = null;
				try {
					mem_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���嚙踐�蕭謒蕭豰蕭����蕭蹇∪嚙踝蕭謢遴���蕭謢寧腦嚙踐��蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙踝蕭��蕭豲嚙踐�蕭嚙踝�雓蕭
				}
				
				/***************************2.���嚙踝蕭豲嚙踝蕭豯哨蕭蹓遴��飭�嚙踝���*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("���蕭蹐嚙踝蕭��蕭�����");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙踝蕭��蕭豲嚙踐�蕭嚙踝�雓蕭
				}
				
				/***************************3.���蕭蹓遴����嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 嚙踝蕭�����蕭�嚙踝����嚙踝���嚙踐儒mpVO���嚙踝蕭豯株,嚙踐雓Ｗ�req
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���嚙踝蕭��鞈對�嚙踐��蕭 listOneEmp.jsp
				successView.forward(req, res);

				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���*************************************/
			} catch (Exception e) {
				errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 嚙踝蕭謏�嚙踐��istAllEmp.jsp���嚙踝蕭��嚙踝蕭豯株��蕭

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱�****************************************/
				Integer mem_id = new Integer(req.getParameter("mem_id"));
				
				/***************************2.���嚙踝蕭豲嚙踝蕭豯哨蕭蹓遴��飭�嚙踝���****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
								
				/***************************3.���蕭蹓遴����嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 嚙踝蕭�����蕭�嚙踝����嚙踝���嚙踐儒mpVO���嚙踝蕭豯株,嚙踐雓Ｗ�req
				String url = "/front/mem/updatemem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���嚙踝蕭��鞈對�嚙踐��蕭 update_emp_input.jsp
				successView.forward(req, res);

				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���**********************************/
			} catch (Exception e) {
				errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�頩�蕭��嚙踝蕭謖���蕭��嚙踝���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 嚙踝蕭謏�嚙踐��pdate_emp_input.jsp���嚙踝蕭��嚙踝蕭豯株��蕭
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱� - 嚙踝蕭謜眾嚙踝���嚙踝蕭謢遴���蕭謖蕭��狗嚙踐���飭���**********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("����蕭嚙踝嚙踐�嚙踐狐���: 嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //嚙踝蕭豯�曌��蕭賹蕭嚙踐�蕭嚙踝蝮������蕭(嚙踐��蕭)嚙踝�蕭��蕭�嚙踝蕭謏��(regular-expression)
					errorMsgs.add("����蕭嚙踝嚙踐�嚙踐狐���: ������蕭��雓ｇ蕭嚙踝蕭豲蕭��頩����嚙踝���蕭謍箄嚙踝蕭��頩�敢嚙踐雓�嚙踝蕭嚙� , 嚙踝蕭豲蕭��敦嚙踐�嚙賢�����雓蕭2���蕭嚙�10嚙踝蕭�����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踝蕭謖蕭��嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("嚙踐�蕭嚙踐�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("���嚙踝蕭豯株鞈對�鞈對�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//��������蕭
				
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
//					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
//				}
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("嚙踝�蕭謇啣��蕭謍箄嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
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
					req.setAttribute("memVO", memVO); // ���蕭������蕭嚙踐�嚙踝���嚙踝蕭謢遴�蕭嚙踝�狗嚙踐���儒mpVO���嚙踝蕭豯株,嚙踝蕭�嚙踐�雓Ｗ�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //嚙踝蕭��蕭豲嚙踐�蕭嚙踝�雓蕭
				}
				
				/***************************2.���嚙踝蕭豲嚙踝蕭謚哨蕭��嚙踝蕭謖�嚙踝����*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_id, mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.嚙踐�蕭赯蛛蕭嚙踐■�嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 嚙踝蕭�����蕭�嚙踝pdate���嚙踝蕭��鞈察���蕭,嚙踝蕭��嚙踐���嚙踝蕭��嚙踐儒mpVO���嚙踝蕭豯株,嚙踐雓Ｗ�req
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踐�蕭赯蛛蕭嚙踐����蕭��鞈察���蕭,嚙踐�蕭���蓮istOneEmp.jsp
				successView.forward(req, res);

				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踐�蕭赯蛛蕭嚙踐�嚙踝����蕭�嚙踝����:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 嚙踝蕭謏�嚙踐��ddEmp.jsp���嚙踝蕭��嚙踝蕭豯株��蕭  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱� - 嚙踝蕭謜眾嚙踝���嚙踝蕭謢遴���蕭謖蕭��狗嚙踐���飭���*************************/
//				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("����蕭嚙踝嚙踐�嚙踐狐���: 嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //嚙踝蕭豯�曌��蕭賹蕭嚙踐�蕭嚙踝蝮������蕭(嚙踐��蕭)嚙踝�蕭��蕭�嚙踝蕭謏��(regular-expression)
					errorMsgs.add("����蕭嚙踝嚙踐�嚙踐狐���: ������蕭��雓ｇ蕭嚙踝蕭豲蕭��頩����嚙踝���蕭謍箄嚙踝蕭��頩�敢嚙踐雓�嚙踝蕭嚙� , 嚙踝蕭豲蕭��敦嚙踐�嚙賢�����雓蕭2���蕭嚙�10嚙踝蕭�����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踝蕭謖蕭��嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("嚙踐�蕭嚙踐�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("���嚙踝蕭豯株鞈對�鞈對�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//��������蕭
				
				Part part =req.getPart("mem_pic");
				InputStream in = part.getInputStream();
				byte[] mem_pic = new byte[in.available()];
				in.read(mem_pic);
				in.close();
				
//				String mem_pic = req.getParameter("mem_pic").trim();
//				if (mem_pic == null || mem_pic.trim().length() == 0) {
//					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
//				}
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("嚙踝�蕭謇啣��蕭謍箄嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
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
					req.setAttribute("memVO", memVO); // ���蕭������蕭嚙踐�嚙踝���嚙踝蕭謢遴�蕭嚙踝�狗嚙踐���儒mpVO���嚙踝蕭豯株,嚙踝蕭�嚙踐�雓Ｗ�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���嚙踝蕭豲嚙踝蕭豯哨蕭�嚙踐蝑�����***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.���蕭�嚙踐蝡����ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)***********/
				String url = "/back_end/back_index_1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���蕭�嚙踐蝑��蕭��鞈察�嚙踐ㄠ�����蓮istAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mem/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 嚙踝蕭謏�嚙踐��蕭�嚙踐ㄝ嚙踐�istAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱�***************************************/
				Integer mem_id = new Integer(req.getParameter("mem_id"));
				
				/***************************2.���嚙踝蕭豲嚙踝蕭豲蕭��雓�蕭�����***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_id);
				
				/***************************3.���蕭��雓�嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)***********/								
				String url = "/back_end/back_index_1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���蕭��雓����蕭��鞈察���蕭,嚙踐�蕭���╡��������蕭����蕭��雓����蕭��嚙踝蕭��嚙踝蕭�������蕭
				successView.forward(req, res);
				
				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���**********************************/
			} catch (Exception e) {
				errorMsgs.add("���蕭��雓�蕭�����蕭�嚙踝����:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/back_index_1.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update_back".equals(action)) { // 嚙踝蕭謏�嚙踐��蕭�嚙踐ㄝ嚙踐���嚙踝蕭�嚙賢�嚙踝蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱�****************************************/
				Integer mem_id = new Integer(req.getParameter("mem_id"));
				
				/***************************2.���嚙踝蕭豲嚙踝蕭豯哨蕭蹓遴��飭�嚙踝���****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
								
				/***************************3.���蕭蹓遴����嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 嚙踝蕭�����蕭�嚙踝����嚙踝���嚙踐儒mpVO���嚙踝蕭豯株,嚙踐雓Ｗ�req
				String url = "/back_end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���嚙踝蕭��鞈對�嚙踐��蕭 update_emp_input.jsp
				successView.forward(req, res);

				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���**********************************/
			} catch (Exception e) {
				errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�頩�蕭��嚙踝蕭謖���蕭��嚙踝���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update_back".equals(action)) { // 嚙踝蕭謏�嚙踐��蕭�嚙踐ㄝ嚙踐���嚙踝蕭�date
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.���蕭��嚙踝蕭�謇哨蕭嚙踐筑�頩��嚙踐�蕭豱� - 嚙踝蕭謜眾嚙踝���嚙踝蕭謢遴���蕭謖蕭��狗嚙踐���飭���**********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				String mem_acct = req.getParameter("mem_acct");
				String mem_acctReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_acct == null || mem_acct.trim().length() == 0) {
					errorMsgs.add("����蕭嚙踝嚙踐�嚙踐狐���: 嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				} else if(!mem_acct.trim().matches(mem_acctReg)) { //嚙踝蕭豯�曌��蕭賹蕭嚙踐�蕭嚙踝蝮������蕭(嚙踐��蕭)嚙踝�蕭��蕭�嚙踝蕭謏��(regular-expression)
					errorMsgs.add("����蕭嚙踝嚙踐�嚙踐狐���: ������蕭��雓ｇ蕭嚙踝蕭豲蕭��頩����嚙踝���蕭謍箄嚙踝蕭��頩�敢嚙踐雓�嚙踝蕭嚙� , 嚙踝蕭豲蕭��敦嚙踐�嚙賢�����雓蕭2���蕭嚙�10嚙踝蕭�����");
	            }

				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踝蕭謖蕭��嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("嚙踐�蕭嚙踐�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("���嚙踝蕭豯株鞈對�鞈對�嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				Integer mem_bonus = new Integer(req.getParameter("mem_bonus").trim());
				
				
				String mem_title = req.getParameter("mem_title").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				
				
				Integer mem_exp = new Integer(req.getParameter("mem_exp").trim());

				
				//��������蕭
				
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
//					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
//				}
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("嚙踝�蕭謇啣��蕭謍箄嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_add = req.getParameter("mem_add").trim();
				if (mem_add == null || mem_add.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
				}
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("嚙踐狎嚙踝蕭豲�蕭嚙踐�蕭�韏�");
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
					req.setAttribute("memVO", memVO); // ���蕭������蕭嚙踐�嚙踝���嚙踝蕭謢遴�蕭嚙踝�狗嚙踐���儒mpVO���嚙踝蕭豯株,嚙踝蕭�嚙踐�雓Ｗ�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //嚙踝蕭��蕭豲嚙踐�蕭嚙踝�雓蕭
				}
				
				/***************************2.���嚙踝蕭豲嚙踝蕭謚哨蕭��嚙踝蕭謖�嚙踝����*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_id, mem_acct, mem_psw, mem_email, 
						mem_phone, mem_bonus, mem_title, mem_exp,
						mem_pic, mem_name, mem_gender, mem_add, mem_status,
						mem_repno);
				
				/***************************3.嚙踐�蕭赯蛛蕭嚙踐■�嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 嚙踝蕭�����蕭�嚙踝pdate���嚙踝蕭��鞈察���蕭,嚙踝蕭��嚙踐���嚙踝蕭��嚙踐儒mpVO���嚙踝蕭豯株,嚙踐雓Ｗ�req
				String url = "/back_end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踐�蕭赯蛛蕭嚙踐����蕭��鞈察���蕭,嚙踐�蕭���蓮istOneEmp.jsp
				successView.forward(req, res);

				/***************************���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭���*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踐�蕭赯蛛蕭嚙踐�嚙踝����蕭�嚙踝����:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
