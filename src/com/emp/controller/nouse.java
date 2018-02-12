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

	    // 【檢查該帳號 , 密碼是否有效】
	    if (!allowUser(emp_acct, emp_psw, req)) {          //【帳號 , 密碼無效時】
	      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
	      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/back/backlogin/backlogin.jsp>重新登入</A>");
	      out.println("</BODY></HTML>");
	      
	      
	      
	    } else {
	    	
	    	HttpSession session = req.getSession();
	    	System.out.println(session.getId()+"*******");
	        session.setAttribute("emp_acct", emp_acct);   //*工作1: 才在session內做已經登入過的標識
	        
	         try {                                                        
	           String location = (String) session.getAttribute("location");
	           if (location != null) {
	             session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
	             res.sendRedirect(location);            
	             return;
	           }
	         }catch (Exception ignored) { }

	        res.sendRedirect(req.getContextPath()+"/back/backindex.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
	      
	    	
	    	
	    	
	    }
	
	
	}
//	驗證帳號密碼
	protected boolean allowUser(String emp_acct, String emp_psw, HttpServletRequest req) {
	    
		EmpService empSvc = new EmpService();
		List<EmpVO> list = empSvc.getAll();
		
		for(EmpVO c : list) {
            
//			員工沒有
//			if(c.getMem_status().equals("停權")){
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
