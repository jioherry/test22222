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

public class BackLoginServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
	    res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = res.getWriter();

	    String emp_acct = req.getParameter("emp_acct");
	    String emp_psw = req.getParameter("emp_psw");

	    // �炎�閰脣董��� , 撖Ⅳ�������
	    if (!allowUser(emp_acct, emp_psw, req)) {          //�董��� , 撖Ⅳ������
	      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	      out.println("<BODY>密碼錯誤!<BR>");
	      out.println("<A HREF="+req.getContextPath()+"/back_end/login.jsp>回登入頁面</A>");
	      out.println("</BODY></HTML>");
	      
	      
	      
	    } else {
	    	
	    	HttpSession session = req.getSession();
	    	System.out.println(session.getId()+"*******");
	        session.setAttribute("emp_acct", emp_acct);   //*撌乩��1: ��session���歇蝬�������
	        
	         try {                                                        
	           String location = (String) session.getAttribute("location");
	           if (location != null) {
	             session.removeAttribute("location");   //*撌乩��2: ����靘�雯��� (-->憒���雯���:����靘�雯���)
	             res.sendRedirect(location);            
	             return;
	           }
	         }catch (Exception ignored) { }

	        res.sendRedirect(req.getContextPath()+"/back_end/back_index.jsp");  //*撌乩��3: (-->憒靘�雯���:����敺擐��)
	      
	    	
	    	
	    	
	    }
	
	
	}
//	撽�董���Ⅳ
	protected boolean allowUser(String emp_acct, String emp_psw, HttpServletRequest req) {
	    
		EmpService empSvc = new EmpService();
		List<EmpVO> list = empSvc.getAll();
		
		for(EmpVO c : list) {
            
//			�撌交�������
//			if(c.getMem_status().equals("����")){
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
