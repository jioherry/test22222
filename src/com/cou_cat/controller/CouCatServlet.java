package com.cou_cat.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.cou.model.*;
import com.cou_cat.model.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CouCatServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("listCous_ByCouCatID_back_end_forcoursetest".equals(action) || "listCous_ByCouCatID_ListAllCouCat".equals(action)) {
			
			HttpSession session = req.getSession();
			
			List<String> errorMsgs = new LinkedList<String>();
			session.setAttribute("errorMsgs", errorMsgs);

			try {
				String cou_cat_id = req.getParameter("cou_cat_id");
				System.out.println("id" + cou_cat_id);
				CouCatService cou_catSvc = new CouCatService();
				Set<CouVO> set = cou_catSvc.getCousByCouCatID(cou_cat_id);
				System.out.println("Servlet set = " + set);

				session.setAttribute("listCous_ByCouCatID", set);

				String url = null;
				if ("listCous_ByCouCatID_back_end_forcoursetest".equals(action))
					url = "/back_end/course_category/listCous_ByCouCatID.jsp";
				else if ("listCous_ByCouCatID_ListAllCouCat".equals(action))
					url = "/back_end/course_category/listCous_ByCouCatID.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String cou_cat_name = req.getParameter("cou_cat_name");
				String cou_cat_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (cou_cat_name == null || cou_cat_name.trim().length() == 0) {
					errorMsgs.add("無法取得資料70");
				} else if(!cou_cat_name.trim().matches(cou_cat_nameReg)) {
					errorMsgs.add("無法取得資料71 , 無法取得資料72");	
				}
				
				
				String cou_cat_info = req.getParameter("cou_cat_info").trim();
				if(cou_cat_info == null || cou_cat_info.trim().length() == 0) {
					errorMsgs.add("無法取得資料73");
				} else if(!cou_cat_info.trim().matches(cou_cat_nameReg)) {
					errorMsgs.add("無法取得資料74 , 無法取得資料75");	
				}
				
				
				Part cou_catimgpart = req.getPart("cou_cat_img");
				byte[] cou_cat_img = null;
				if(cou_catimgpart.getSize() != 0){
					InputStream in = cou_catimgpart.getInputStream();
					cou_cat_img = new byte[in.available()];
					in.read(cou_cat_img);
					in.close();

				} else {
					errorMsgs.add("無法取得資料76");
				}
				
				CouCatVO cou_catVO = new CouCatVO();
				cou_catVO.setCou_cat_info(cou_cat_info);
				cou_catVO.setCou_cat_name(cou_cat_name);
				cou_catVO.setCou_cat_img(cou_cat_img);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cou_catVO", cou_catVO);
					RequestDispatcher failureView
						= req.getRequestDispatcher("/back_end/course_category/addCourse_Category.jsp");
					failureView.forward(req, res);
					return;
				}
				
				CouCatService cou_catSvc = new CouCatService();
				cou_catVO = cou_catSvc.addCouCat(cou_cat_name , cou_cat_info , cou_cat_img);
				
				String url = "/back_end/course_category/listAllCourseCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course_category/addCourse_Category.jsp");
				failureView.forward(req, res);
			}	
		}
		
	}
}
