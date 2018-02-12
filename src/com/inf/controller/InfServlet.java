package com.inf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import net.sf.json.JSONObject;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class InfServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 靘select_page.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				String str = req.getParameter("inf_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓��撌亦楊���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				Integer inf_id = null;
				try {
					inf_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�撌亦楊��撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				InfService infSvc = new InfService();
				InfVO infVO = infSvc.getOneInf(inf_id);
				if (infVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/inf/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				req.setAttribute("infVO", infVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/inf/listOneInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/inf/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		
//		敺ack inf addInf靘�
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���*************************/
				
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				

				String inf_title = req.getParameter("inf_title").trim();
				if (inf_title == null || inf_title.trim().length() == 0) {
					errorMsgs.add("璅��蝛箇");
				}
				String inf_text = req.getParameter("inf_text").trim();
				if (inf_text == null || inf_text.trim().length() == 0) {
					errorMsgs.add("�摰寡�蝛箇");
				}
				
//				������
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
					req.setAttribute("infVO", infVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/inf/addInf.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���憓���***************************************/
				InfService infSvc = new InfService();
				infVO = infSvc.addInf(emp_id, inf_cat_id, inf_title, inf_text, inf_pic);
				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
				String url = "/back_end/inf/listAllInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/back_index.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id"));
				
				/***************************2.����鞈��***************************************/
				InfService infSvc = new InfService();
				infSvc.deleteInf(inf_id);
				
				/***************************3.��摰��,皞��漱(Send the Success view)***********/								
				String url = "/back_end/back_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("��鞈�仃���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/inf/listAllInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id"));
				
				/***************************2.���閰Ｚ���****************************************/
				InfService infSvc = new InfService();
				InfVO infVO = infSvc.getOneInf(inf_id);
								
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)************/
				req.setAttribute("infVO", infVO);         // 鞈�澈����mpVO�隞�,摮req
				String url = "/back_end/inf/update_inf_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/inf/listAllInf.jsp");
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
				Integer inf_id = new Integer(req.getParameter("inf_id").trim());
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				Integer inf_cat_id = new Integer(req.getParameter("inf_cat_id").trim());

				

				String inf_title = req.getParameter("inf_title").trim();
				if (inf_title == null || inf_title.trim().length() == 0) {
					errorMsgs.add("�雿�蝛箇");
				}
				String inf_text = req.getParameter("inf_text").trim();
				if (inf_text == null || inf_text.trim().length() == 0) {
					errorMsgs.add("�雿�蝛箇");
				}
				
				
				
//				������
				
				InfService infSvc = new InfService();
				Part part =req.getPart("inf_pic");
				byte[] inf_pic=null;
				
				if(part.getSize() == 0){
					inf_pic=infSvc.getOneInf(inf_id).getInf_pic();
					
					
				} else {
					InputStream in = part.getInputStream();
					inf_pic = new byte[in.available()];
					in.read(inf_pic);
					in.close();
				}
				
				
				InfVO infVO = new InfVO();
				infVO.setInf_id(inf_id);
				infVO.setEmp_id(emp_id);
				infVO.setInf_cat_id(inf_cat_id);
				infVO.setInf_title(inf_title);
				infVO.setInf_text(inf_text);
				infVO.setInf_pic(inf_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infVO", infVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/inf/update_inf_input.jsp");
					failureView.forward(req, res);
					return; //蝔�葉�
				}
				
				/***************************2.���耨�鞈��*****************************************/
//				InfService infSvc = new InfService();
				infVO = infSvc.updateInf(inf_id, emp_id, inf_cat_id, inf_title, inf_text, inf_pic);
				
				/***************************3.靽格摰��,皞��漱(Send the Success view)*************/
				req.setAttribute("infVO", infVO); // 鞈�澈update�����,甇�蝣箇��mpVO�隞�,摮req
				String url = "/back_end/back_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("靽格鞈�仃���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(req, res);
			}
		}
		
//		敺����銝�蝔桃�����
//		���������
		if ("list".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				String str = req.getParameter("inf_cat_id");
								
				Integer inf_cat_id = null;
				try {
					inf_cat_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/index.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				List<InfVO> list =null;
				InfService infSvc = new InfService();
				list = infSvc.findInfByCat(inf_cat_id);
				if (list == null) {
					errorMsgs.add("��鞈��");
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/index.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				req.setAttribute("list", list); // 鞈�澈����mpVO�隞�,摮req
				String url = "/front_end/inf/infdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/index.jsp");
				failureView.forward(req, res);
			}
		}
		
//		敺����銝�蝔桃����jax��
//		���������
		
		if ("ajaxlist".equals(action)){
			
			String str = req.getParameter("inf_cat_id");
			
			Integer inf_cat_id = null;
			try {
				inf_cat_id = new Integer(str);
			} catch (Exception e) {
			}
			
			
			System.out.println(str+"this");
			
			JSONObject obj = new JSONObject();
			
			InfService infSvc = new InfService();
			List<InfVO> infList = infSvc.findInfByCat(inf_cat_id);
			
	
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
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
//		敺nfdetail靘�
		
		if ("content".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				String str = req.getParameter("inf_id");
								
				Integer inf_id = null;
				try {
					inf_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				List<InfVO> list =null;
				InfService infSvc = new InfService();
				InfVO infVO = infSvc.getOneInf(inf_id);
				if (infVO == null) {
					errorMsgs.add("��鞈��");
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/index.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				req.setAttribute("infVO", infVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/front_end/inf/infcontent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/index.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
