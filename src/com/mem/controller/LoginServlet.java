package com.mem.controller;

import java.io.*;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.*;
import com.tool.MailService;

public class LoginServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
	    res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = res.getWriter();

	    String mem_acct = req.getParameter("mem_acct");
	    String mem_psw = req.getParameter("mem_psw");
	    String action = req.getParameter("action");
	    String mem_email = req.getParameter("mem_email");
	    String acct = req.getParameter("acct");
	    System.out.println(mem_email);
	    System.out.println(acct);
	    
	    if(action.equals("login")){
	    // 嚙踐��蕭貔��嚙踝蕭嚙� , ���嚙踐�嚙踐�蕭嚙踝嚙踝�蕭嚙�
	    if (!allowUser(mem_acct, mem_psw, req)) {          //嚙踐�嚙踝蕭嚙� , ���嚙踝�蕭嚙踝�蕭蹇蕭嚙�
	      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	      out.println("<BODY>密碼錯誤!!<BR>");
	      out.println("<A HREF="+req.getContextPath()+"/front_end/login/login.jsp>回登入頁面</A>");
	      out.println("</BODY></HTML>");
	      
	      
	      
	    } else {
	    	
	    	HttpSession session = req.getSession();
	    	System.out.println(session.getId()+"*******");
	        session.setAttribute("mem_acct", mem_acct);   //*��鼎嚙踝蕭1: 嚙踝蕭��祗session嚙踝嚙踝蕭謍唳��謒抬蕭�嚙踝蕭��蕭��蕭謕蕭嚙�
	        
	         try {                                                        
	           String location = (String) session.getAttribute("location");
	           if (location != null) {
	             session.removeAttribute("location");   //*��鼎嚙踝蕭2: 嚙踝蕭��蕭��蕭�����蕭��嚙踝蕭嚙� (-->���蕭�嚙踝�蕭��嚙踝蕭嚙�:嚙踝蕭�嚙踝�蕭�����蕭��嚙踝蕭嚙�)
	             res.sendRedirect(location);            
	             return;
	           }
	         }catch (Exception ignored) { }

	        res.sendRedirect(req.getContextPath()+"/front_end/index.jsp");  //*��鼎嚙踝蕭3: (-->������蕭��嚙踝蕭嚙�:嚙踝蕭�嚙踝�蕭��login_success.jsp)
	      
	    	
	    	
	    }
	    }
	    
	    if(action.equals("forpsw")){
	    	
	    	
	    	try {
	    
	      MemService memSvc = new MemService();
	      MemVO memVO = memSvc.getOneMemByAcct(acct);
	      
	      if(!(memVO.getMem_email().equals(mem_email.trim()))){
	    	  System.out.println(memVO.getMem_email());
	    	  
	      }
	      
	      System.out.println(memVO.getMem_email());
	      System.out.println(memVO.getMem_name());
	      
	    	
	      String to = memVO.getMem_email();
	      
	      String subject = "���嚙踐垓貔�";
	      
	      String ch_name = memVO.getMem_name();
	      String passRandom = memVO.getMem_psw();
	      String messageText = "Hello! " + ch_name + " ����謢瑁縐���: " + passRandom + "\n" +" (��嚙踐嚙踐���)-----------"; 
	       
	      MailService mailService = new MailService();
	      mailService.sendMail(to, subject, messageText);
	    	System.out.println(passRandom);
			String url = "/front_end/login/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���蕭賹韏航����蕭��嚙踝蕭謕蕭��嚙踝蕭豯抆���蕭��嚙踝蕭謕,���蕭蹎刻嚙踝蕭��蕭��istOneEmp.jsp
			successView.forward(req, res);

			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/login/login.jsp");
				failureView.forward(req, res);
			}
	    }
	    
	    
	
	
	}
//	�謅蕭��嚙踝蕭賹蕭��
	protected boolean allowUser(String mem_acct, String mem_psw, HttpServletRequest req) {
	    
		MemService memSvc = new MemService();
		List<MemVO> list = memSvc.getAll();
		
		for(MemVO c : list) {
            
			
			if(c.getMem_status().equals("嚙踝蕭謚恬蕭嚙�")){
				return false;
			}
			
			
            
            if(mem_acct.trim().equals(c.getMem_acct()) && mem_psw.trim().equals(c.getMem_psw())){
            	
            	HttpSession session = req.getSession();
    	        session.setAttribute("memVO", memSvc.getOneMem(c.getMem_id()));
            	
            	return true;
            }
            
            
		}
		return false;
		
	  }

}
