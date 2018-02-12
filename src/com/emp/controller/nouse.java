package com.emp.controller;

import java.io.*;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.*;
import com.mem.model.*;

public class nouse extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
	    res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = res.getWriter();

	    String emp_acct = req.getParameter("emp_acct");
	    String emp_psw = req.getParameter("emp_psw");

	    // �i�ˬd�ӱb�� , �K�X�O�_���ġj
	    if (!allowUser(emp_acct, emp_psw, req)) {          //�i�b�� , �K�X�L�Įɡj
	      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	      out.println("<BODY>�A���b�� , �K�X�L��!<BR>");
	      out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/back/backlogin/backlogin.jsp>���s�n�J</A>");
	      out.println("</BODY></HTML>");
	      
	      
	      
	    } else {
	    	
	    	HttpSession session = req.getSession();
	    	System.out.println(session.getId()+"*******");
	        session.setAttribute("emp_acct", emp_acct);   //*�u�@1: �~�bsession�����w�g�n�J�L������
	        
	         try {                                                        
	           String location = (String) session.getAttribute("location");
	           if (location != null) {
	             session.removeAttribute("location");   //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
	             res.sendRedirect(location);            
	             return;
	           }
	         }catch (Exception ignored) { }

	        res.sendRedirect(req.getContextPath()+"/back/backindex.jsp");  //*�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
	      
	    	
	    	
	    	
	    }
	
	
	}
//	���ұb���K�X
	protected boolean allowUser(String emp_acct, String emp_psw, HttpServletRequest req) {
	    
		EmpService empSvc = new EmpService();
		List<EmpVO> list = empSvc.getAll();
		
		for(EmpVO c : list) {
            
//			���u�S��
//			if(c.getMem_status().equals("���v")){
//				return false;
//			}
			
			
            
            if(emp_acct.trim().equals(c.getEmp_acct()) && emp_psw.trim().equals(c.getEmp_psw())){
            	
            	HttpSession session = req.getSession();
    	        session.setAttribute("empVO", empSvc.getOneEmp(c.getEmp_id()));
            	
            	return true;
            }
            
            
		}
		return false;
		
	  }

}
