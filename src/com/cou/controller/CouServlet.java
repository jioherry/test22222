package com.cou.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cou.model.*;
import com.cou_cat.model.CouCatService;
import com.cou_det.model.CouDetService;
import com.cou_det.model.CouDetVO;
import com.movement.model.MovementService;
import com.movement.model.MovementVO;

import net.sf.json.JSONObject;


@WebServlet("/front_end/cou/cou.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CouServlet extends HttpServlet{


	public void doGet(HttpServletRequest req , HttpServletResponse res)
			throws ServletException , IOException {
		doPost(req , res);
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res)
			throws ServletException , IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs" , errorMsgs);
			
			try {
				String str = req.getParameter("cou_id");
				
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���5");
				}
				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView 
						= req.getRequestDispatcher("/back_end/back_end_forcoursetest.jsp");
					failureView.forward(req, res);
					
					return;
				}
						
				String cou_idReq = "C[0-9]{6}";
				
				if(!str.trim().matches(cou_idReq)){
						errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���6");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView 
						= req.getRequestDispatcher("/back_end/back_end_forcoursetest.jsp");
					failureView.forward(req, res);
					
					return;
				}
				
				CouService couSvc = new CouService();
				CouVO couVO = couSvc.getOneCou(str);
				if( couVO == null) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���7");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView
						= req.getRequestDispatcher("/back_end/back_end_forcoursetest.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("couVO", couVO);
				String url = "/front_end/chiacourse/listOneCou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���8" + e.getMessage());
				RequestDispatcher failureView 
					= req.getRequestDispatcher("/back_end/back_end_forcoursetest.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				String cou_id = new String(req.getParameter("cou_id"));
				
				CouService couSvc = new CouService();
				CouVO couVO = couSvc.getOneCou(cou_id);
				
				req.setAttribute("couVO", couVO);
				String url = "/back_end/course/update_course_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���9" + e.getMessage());
				RequestDispatcher failureView 
					= req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				String cou_id = (req.getParameter("cou_id").trim());
				
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

				String cou_name = req.getParameter("cou_name");
				String cou_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (cou_name == null || cou_name.trim().length() == 0) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���10");
				} else if(!cou_name.trim().matches(cou_nameReg)) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���11, ���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���11");	
				}
				
				
				String cou_cat_id = req.getParameter("cou_cat_id").trim();
				String cou_cat_idReg = "C[0-9]{4}";
				if (!cou_cat_id.trim().matches(cou_cat_idReg)){
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���12");
				}
				

				
				String cou_intor = req.getParameter("cou_intor").trim();
				if(cou_intor == null || cou_intor.trim().length() == 0) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���13");
				} else if(!cou_intor.trim().matches(cou_nameReg)) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���13 , ���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���13");	
				}
				
				
				Integer cou_int= null;
				try {
					cou_int = new Integer(req.getParameter("cou_int").trim());
				} catch (NumberFormatException e) {
					cou_int = 0;
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���14");
				}
				
				String cou_permi = req.getParameter("cou_permi");
				if (cou_permi == null || cou_permi.trim().length() == 0) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���15");
				}
				
				String cited_count = req.getParameter("cited_count");
				if (cited_count == null || cited_count.trim().length() == 0){
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���16");
				}
				
				Integer cou_cal_cns = null;
				try {
					cou_cal_cns = new Integer(req.getParameter("cou_cal_cns").trim());
				} catch (NumberFormatException e) {
					cou_cal_cns = 0 ;
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���17");
				}
				
				Integer cou_time_length = null;
				try {
					cou_time_length = new Integer(req.getParameter("cou_time_length").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���18");
				}			
				
				Part cou_imgpart = req.getPart("cou_img");
				byte[] cou_img = null;
				InputStream in = cou_imgpart.getInputStream();
				cou_img = new byte[in.available()];
				in.read(cou_img);
				in.close();
				
				CouVO couVO = new CouVO();
				couVO.setCou_id(cou_id);
				couVO.setCou_cat_id(cou_cat_id);
				couVO.setMem_id(mem_id);
				couVO.setCou_intor(cou_intor);
				couVO.setCou_name(cou_name);
				couVO.setCou_permi(cou_permi);
				couVO.setCou_int(cou_int);
				couVO.setCited_count(cited_count);
				couVO.setCou_cal_cns(cou_cal_cns);
				couVO.setCou_time_length(cou_time_length);
				couVO.setCou_img(cou_img);				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couVO", couVO);
					RequestDispatcher failureView
						= req.getRequestDispatcher("/back_end/course/update_course_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				CouService couSvc = new CouService();
				couVO = couSvc.updateCou( cou_id ,  cou_cat_id ,  mem_id , 
						 cou_intor ,  cou_name ,  cou_permi ,  cou_int ,
						 cited_count ,  cou_cal_cns ,  cou_time_length , cou_img);
				
				CouCatService cou_catSvc = new CouCatService();
				if(requestURL.equals("back_end/course_category/listCous_ByCouCatID.jsp") || requestURL.equals("back_end/course_category/listAllCouCat.jsp"))
					req.setAttribute("listCous_ByCouCatID",cou_catSvc.getCousByCouCatID(cou_cat_id)); // 嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕蕭豲list嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐�蕭謇quest
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e){
				errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���19" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course/update_course_input.jsp");
				failureView.forward(req, res); 
			}
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String cou_name = req.getParameter("cou_name");
				String cou_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (cou_name == null || cou_name.trim().length() == 0) {
					errorMsgs.add("cou_name");
				} else if(!cou_name.trim().matches(cou_nameReg)) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���20, ���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���20");	
				}
				
				
				String cou_cat_id = req.getParameter("cou_cat_id").trim();
				String cou_cat_idReg = "C[0-9]{4}";
				if (!cou_cat_id.trim().matches(cou_cat_idReg)){
					errorMsgs.add("cou_cat_id");
				}
				
				Integer mem_id = null;
				try {
					mem_id = new Integer(req.getParameter("mem_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("mem_id");
				}
				
				String cou_intor = req.getParameter("cou_intor").trim();
				if(cou_intor == null || cou_intor.trim().length() == 0) {
					errorMsgs.add("cou_intor");
				} else if(!cou_intor.trim().matches(cou_nameReg)) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���21, ���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���21");	
				}
				
				
				Integer cou_int= null;
				try {
					cou_int = new Integer(req.getParameter("cou_int").trim());
				} catch (NumberFormatException e) {
					cou_int = 0;
					errorMsgs.add("cou_int");
				}
				
				String cou_permi = req.getParameter("cou_permi");
				if (cou_permi == null || cou_permi.trim().length() == 0) {
					errorMsgs.add("cou_permi");
				}
				
				String cited_count = req.getParameter("cited_count");
				if (cited_count == null || cited_count.trim().length() == 0){
					errorMsgs.add("cited_count");
				}
				
				Integer cou_cal_cns = null;
				try {
					cou_cal_cns = new Integer(req.getParameter("cou_cal_cns").trim());
				} catch (NumberFormatException e) {
					cou_cal_cns = 0 ;
					errorMsgs.add("cou_cal_cns");
				}
				
				Integer cou_time_length = null;
				try {
					cou_time_length = new Integer(req.getParameter("cou_time_length").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("cou_time_length");
				}
				
				Part cou_imgpart = req.getPart("cou_img");
				byte[] cou_img = null;
				if(cou_imgpart.getSize() != 0){
					InputStream in = cou_imgpart.getInputStream();
					cou_img = new byte[in.available()];
					in.read(cou_img);
					in.close();

				} else {
					errorMsgs.add("cou_imgpart");
				}
				
				String dis_state = req.getParameter("dis_state");
			
				CouVO couVO = new CouVO();
				couVO.setCou_cat_id(cou_cat_id);
				couVO.setMem_id(mem_id);
				couVO.setCou_intor(cou_intor);
				couVO.setCou_name(cou_name);
				couVO.setCou_permi(cou_permi);
				couVO.setCou_int(cou_int);
				couVO.setCited_count(cited_count);
				couVO.setCou_cal_cns(cou_cal_cns);
				couVO.setCou_time_length(cou_time_length);
				couVO.setCou_img(cou_img);
				couVO.setDis_state(dis_state);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couVO", couVO);
					RequestDispatcher failureView
						= req.getRequestDispatcher("/front_end/chiacourse/addCourse.jsp");
					failureView.forward(req, res);
					return;
				}
				
				CouService couSvc = new CouService();
				couVO = couSvc.addCou( cou_cat_id ,  mem_id , 
						 cou_intor ,  cou_name ,  cou_permi ,  cou_int ,
						 cited_count ,  cou_cal_cns ,  cou_time_length , cou_img, dis_state);
				
				String url = "/front_end/chiacourse/MyGYM.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course/addCourse.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				String cou_id = req.getParameter("cou_id");
				
				CouService couSvc = new CouService();
				CouVO couVO = couSvc.getOneCou(cou_id);
				couSvc.delete(cou_id);
				
				CouCatService cou_catSvc = new CouCatService();
				if(requestURL.equals("/back_end/course_category/listCous_ByCouCatID.jsp") || requestURL.equals("/back_end/course_category/listCous_ByCouCatID_ListAllCouCat.jsp"))
					req.setAttribute("listCous_ByCouCatID",cou_catSvc.getCousByCouCatID(couVO.getCou_cat_id()));
				
				String url = requestURL;
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲,嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭�嚙踝蕭�嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���22:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
		
		if ("listCous_ByCompositeQuery".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�select_page.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭�嚙踝�蕭謕蕭��蕭謕蕭蹎∴蕭��蕭嚙�
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = new HashMap<String, String[]>(req.getParameterMap());
				}
				
				CouService couSvc = new CouService();
				List<CouVO> list  = couSvc.getAll(map);
				
				req.setAttribute("listCous_ByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/course/listCous_ByCompositeQuery.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/back_end_forcoursetest.jsp");
				failureView.forward(req, res);
			}
		}
        if ("insert2".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String cou_name = req.getParameter("cou_name");
				String cou_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (cou_name == null || cou_name.trim().length() == 0) {
					errorMsgs.add("cou_name");
				} else if(!cou_name.trim().matches(cou_nameReg)) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���20, ���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���20");	
				}
				
				
				String cou_cat_id = req.getParameter("cou_cat_id").trim();
				String cou_cat_idReg = "C[0-9]{4}";
				if (!cou_cat_id.trim().matches(cou_cat_idReg)){
					errorMsgs.add("cou_cat_id");
				}
				
				Integer mem_id = null;
				try {
					mem_id = new Integer(req.getParameter("mem_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("mem_id");
				}
				
				String cou_intor = req.getParameter("cou_intor").trim();
				if(cou_intor == null || cou_intor.trim().length() == 0) {
					errorMsgs.add("cou_intor");
				} else if(!cou_intor.trim().matches(cou_nameReg)) {
					errorMsgs.add("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���21, ���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���21");	
				}
				
				
				Integer cou_int= null;
				try {
					cou_int = new Integer(req.getParameter("cou_int").trim());
				} catch (NumberFormatException e) {
					cou_int = 0;
					errorMsgs.add("cou_int");
				}
				
				String cou_permi = req.getParameter("cou_permi");
				if (cou_permi == null || cou_permi.trim().length() == 0) {
					errorMsgs.add("cou_permi");
				}
				
				String cited_count = req.getParameter("cited_count");
				if (cited_count == null || cited_count.trim().length() == 0){
					errorMsgs.add("cited_count");
				}
				
				Integer cou_cal_cns = null;
				try {
					cou_cal_cns = new Integer(req.getParameter("cou_cal_cns").trim());
				} catch (NumberFormatException e) {
					cou_cal_cns = 0 ;
					errorMsgs.add("cou_cal_cns");
				}
				
				Integer cou_time_length = null;
				try {
					cou_time_length = new Integer(req.getParameter("cou_time_length").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("cou_time_length");
				}
				
				Part cou_imgpart = req.getPart("cou_img");
				byte[] cou_img = null;
				if(cou_imgpart.getSize() != 0){
					InputStream in = cou_imgpart.getInputStream();
					cou_img = new byte[in.available()];
					in.read(cou_img);
					in.close();

				} else {
					errorMsgs.add("cou_imgpart");
				}
				
				String dis_state = req.getParameter("dis_state");
			
				CouVO couVO = new CouVO();
				couVO.setCou_cat_id(cou_cat_id);
				couVO.setMem_id(mem_id);
				couVO.setCou_intor(cou_intor);
				couVO.setCou_name(cou_name);
				couVO.setCou_permi(cou_permi);
				couVO.setCou_int(cou_int);
				couVO.setCited_count(cited_count);
				couVO.setCou_cal_cns(cou_cal_cns);
				couVO.setCou_time_length(cou_time_length);
				couVO.setCou_img(cou_img);
				couVO.setDis_state(dis_state);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couVO", couVO);
					RequestDispatcher failureView
						= req.getRequestDispatcher("/front_end/chiacourse/addCourse.jsp");
					failureView.forward(req, res);
					return;
				}
				
				CouService couSvc = new CouService();
				couVO = couSvc.addCou( cou_cat_id ,  mem_id , 
						 cou_intor ,  cou_name ,  cou_permi ,  cou_int ,
						 cited_count ,  cou_cal_cns ,  cou_time_length , cou_img, dis_state);
				
				String url = "/front_end/chiacourse/MyGYM.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/course/addCourse.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if ("memcou".equals(action)){
			JSONObject obj = new JSONObject();
			MovementService mSvc = new MovementService();
			List<MovementVO> infList = mSvc.getAll();
			try {
				obj.put("infList", infList);
			} catch (Exception e) {
				
			}
			
			System.out.println(obj.toString());
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
			
		}
	}
	
}
