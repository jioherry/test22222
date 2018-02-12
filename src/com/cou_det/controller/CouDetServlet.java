package com.cou_det.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cou.model.CouService;
import com.cou.model.CouVO;
import com.cou_det.model.CouDetService;
import com.cou_det.model.CouDetVO;
//import com.inf.model.InfService;
//import com.inf.model.InfVO;
import com.movement.model.MovementVO;

import net.sf.json.JSONObject;

public class CouDetServlet extends HttpServlet{

	public void doGet(HttpServletRequest req , HttpServletResponse res)
			throws ServletException , IOException {
		doPost(req , res);
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res)
			throws ServletException , IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String cou_id = req.getParameter("cou_id").trim();
				String cou_idReg = "C[0-9]{6}";
				if (!cou_id.trim().matches(cou_idReg)){
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭35");
				}
				System.out.println(cou_id);
				
				String mov_id = req.getParameter("mov_id").trim();
				String mov_idReg = "M[0-9]{9}";
				if (!mov_id.trim().matches(mov_idReg)){
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭36");
				}
				
				System.out.println(mov_id);
				Integer mov_order = null;
				try {
					mov_order = new Integer(req.getParameter("mov_order").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭37r");
				}
				
				System.out.println(req.getParameter("mov_order"));
				Integer mov_play_time = null;
				try {
					mov_play_time = new Integer(req.getParameter("mov_play_time").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭38");
				}
				System.out.println(req.getParameter("mov_play_time"));
				
	
				CouDetVO cou_detVO = new CouDetVO();
				cou_detVO.setCou_id(cou_id);
				cou_detVO.setMov_id(mov_id);
				cou_detVO.setMov_order(mov_order);
				cou_detVO.setMov_play_time(mov_play_time);


				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cou_detVO", cou_detVO);
					RequestDispatcher failureView
						= req.getRequestDispatcher("/back_end/course_detail/addCourse_Detail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				CouDetService cou_detSvc = new CouDetService();
				cou_detVO = cou_detSvc.addCouDet(cou_id , mov_id , mov_order , mov_play_time);
				
				String url = "/back_end/course_detail/listAllCouDet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course_detail/addCourse_Detail.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		if ("insert2".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String cou_id = req.getParameter("cou_id").trim();
				String cou_idReg = "C[0-9]{6}";
				if (!cou_id.trim().matches(cou_idReg)){
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭35");
				}
				System.out.println(cou_id);
				
				String mov_id = req.getParameter("mov_id").trim();
				String mov_idReg = "M[0-9]{9}";
				if (!mov_id.trim().matches(mov_idReg)){
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭36");
				}
				
				System.out.println(mov_id);
				Integer mov_order = null;
				try {
					mov_order = new Integer(req.getParameter("mov_order").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭37r");
				}
				
				System.out.println(req.getParameter("mov_order"));
				Integer mov_play_time = null;
				try {
					mov_play_time = new Integer(req.getParameter("mov_play_time").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭38");
				}
				System.out.println(req.getParameter("mov_play_time"));
				
	
				CouDetVO cou_detVO = new CouDetVO();
				cou_detVO.setCou_id(cou_id);
				cou_detVO.setMov_id(mov_id);
				cou_detVO.setMov_order(mov_order);
				cou_detVO.setMov_play_time(mov_play_time);


				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cou_detVO", cou_detVO);
					RequestDispatcher failureView
						= req.getRequestDispatcher("/front_end/chiacourse/doCourse.jsp");
					failureView.forward(req, res);
					return;
				}
				
				CouDetService cou_detSvc = new CouDetService();
				cou_detVO = cou_detSvc.addCouDet(cou_id , mov_id , mov_order , mov_play_time);
				
				CouVO couVO = new CouService().getOneCou(cou_id);	
				req.setAttribute("couVO", couVO);
				boolean openModal = true;
				req.setAttribute("openModal", openModal);
				
				
				String url = "/front_end/chiacourse/doCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/chiacourse/doCourse.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("getOne_For_Display".equals(action) || "listMovs_ByCouID_listCous_ByCouCatID".equals(action)) { // ����select_page3.jsp��������
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs" , errorMsgs);
			
			try {
				String cou_id = req.getParameter("cou_id");
				if (cou_id == null || (cou_id.trim()).length() == 0) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭39");
				}
				
				String cou_idReq = "C[0-9]{6}";
				
				if(!cou_id.trim().matches(cou_idReq)){
						errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭40");
				}
				if (!errorMsgs.isEmpty()) {
					
					String url = null;
					if ("getOne_For_Display".equals(action))
						url = "/back_end/course_category/back_end_forcoursetest.jsp"; 
					else if ("listMovs_ByCouID_listCous_ByCouCatID".equals(action))
						url = "/back_end/course_category/listCous_ByCouCatID.jsp";

					RequestDispatcher failureView 
						= req.getRequestDispatcher(url);
					failureView.forward(req, res);
					
					return;
				}

				System.out.println(cou_id+"--");
				CouDetService cou_detSvc = new CouDetService();
				Set<CouDetVO> cou_detVO_det = cou_detSvc.getfindByCouid(cou_id);
												
				if( cou_detVO_det.size() == 0) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭41");
				}
				
				System.out.println(errorMsgs);
				System.out.println(action);
				if (!errorMsgs.isEmpty()) {
					
					String url = null;
					if ("getOne_For_Display".equals(action))
						url = "/back_end/course_category/back_end_forcoursetest.jsp";
					else if ("listMovs_ByCouID_listCous_ByCouCatID".equals(action))
						url = "/back_end/course_category/listCous_ByCouCatID.jsp"; 

					RequestDispatcher failureView
						= req.getRequestDispatcher(url);
					failureView.forward(req, res);
					System.out.println(url+".."+req+".."+res+"..");
					return;
				}
				
				req.setAttribute("cou_detVO_det", cou_detVO_det);
				String url = "/back_end/course_detail/listCourse_MovsAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				
				String url = null;
				if ("getOne_For_Display".equals(action))
					url = "/back_end/course_category/back_end_forcoursetest.jsp";
				else if ("listMovs_ByCouID_listCous_ByCouCatID".equals(action))
					url = "/back_end/course_category/listCous_ByCouCatID.jsp";

				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭43" + e.getMessage());
				RequestDispatcher failureView 
					= req.getRequestDispatcher(url);
				failureView.forward(req, res);
				
			}
		}
		
		if ("delete_mov".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				String cou_id = (req.getParameter("cou_id"));
				
				String mov_id = (req.getParameter("mov_id"));
				
				CouDetService cou_detSvc = new CouDetService();

				cou_detSvc.deleteMov(cou_id, mov_id);
				Set<CouDetVO> cou_detVO = cou_detSvc.getfindByCouid(cou_id);

				req.setAttribute("cou_detVO_det", cou_detVO);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/course_detail/listCourse_MovsAll.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭44:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course_detail/listCourse_MovsAll.jsp");
				System.out.println("11515");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_one_cou_movs".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				String cou_id = (req.getParameter("cou_id"));

				CouDetService cou_detSvc = new CouDetService();

				cou_detSvc.deleteCou(cou_id);
				
				String url = "/back_end/course_detail/listAllCouDet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭45:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course_detail/listAllCouDet.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("docourse".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs" , errorMsgs);
			
			try {
				String cou_id = req.getParameter("cou_id");
				System.out.println(cou_id+"---");
				
				CouDetService cou_detSvc = new CouDetService();
				Set<CouDetVO> cou_detVO_det = cou_detSvc.getfindByCouid(cou_id);
				
				Set<MovementVO> set_move = new LinkedHashSet();
				
				for(CouDetVO cou_detVO : cou_detVO_det) {
					
					set_move.add(cou_detSvc.findByMovID(cou_detVO.getMov_id()));
				}
				
				req.setAttribute("set_move", set_move);
				
				String url = "/back_end/course_detail/doCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				
				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭46" + e.getMessage());
				RequestDispatcher failureView 
					= req.getRequestDispatcher("/back_end/course_detail/listCourse_MovsAll.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		
		if("docourse2".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs" , errorMsgs);
			
			try {
				String cou_id = req.getParameter("cou_id");
				System.out.println(cou_id+"---");
				
				CouDetService cou_detSvc = new CouDetService();
				Set<CouDetVO> cou_detVO_det = cou_detSvc.getfindByCouid(cou_id);
				
				Set<MovementVO> set_move = new LinkedHashSet();
				
				for(CouDetVO cou_detVO : cou_detVO_det) {
					
					set_move.add(cou_detSvc.findByMovID(cou_detVO.getMov_id()));
				}
				
//				課程VO
				
				CouVO couVO = new CouService().getOneCou(cou_id);
				
				req.setAttribute("set_move", set_move);
				req.setAttribute("couVO", couVO);
				
				String url = "/front_end/chiacourse/doCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				
				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭46" + e.getMessage());
				RequestDispatcher failureView 
					= req.getRequestDispatcher("/back_end/course_detail/listCourse_MovsAll.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		if ("insert_chia".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("this------------------");
			try {
				String cou_id = req.getParameter("cou_id").trim();
				String cou_idReg = "C[0-9]{6}";
				if (!cou_id.trim().matches(cou_idReg)){
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭47");
				}
				System.out.println(cou_id);
				
				String mov_id = req.getParameter("mov_id").trim();
				String mov_idReg = "M[0-9]{9}";
				if (!mov_id.trim().matches(mov_idReg)){
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭48");
				}
				
				System.out.println(mov_id);
				Integer mov_order = null;
				try {
					mov_order = new Integer(req.getParameter("mov_order").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭49");
				}
				
				System.out.println(req.getParameter("mov_order"));
				Integer mov_play_time = null;
				try {
					mov_play_time = new Integer(req.getParameter("mov_play_time").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭50");
				}
				System.out.println(req.getParameter("mov_play_time"));
				
				CouDetService cou_detSvc = new CouDetService();
				cou_detSvc.addCouDet(cou_id , mov_id , mov_order , mov_play_time);
				System.out.println("-----------------------------------");
				JSONObject obj = new JSONObject();
				obj.put("infList", "infList");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course_detail/addCourse_Detail.jsp");
				failureView.forward(req, res);
			}	
		}
		
	}
}
