package com.plan_detail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.plan_detail.model.Plan_DetailService;
import com.plan_detail.model.Plan_DetailVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;





public class Plan_DetailServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
System.out.println("innnnnnn");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action:"+action);
		
		if ("getOne_For_Display".equals(action)) { // ���select_page.jsp嚙踝蕭��蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.嚙踐�蕭����蕭蹇蕭�� - ��岳�嚙踐僱��嚙踝���嚙踐嚙踝蕭**********************/
				String str = req.getParameter("pd_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("����蕭����嚙踝蕭����嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//���蕭���蕭謘�
				}
				
				Integer pd_no = null;
				try {
					pd_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙踐僱��嚙踝�縣�嚙�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//���蕭���蕭謘�
				}
				
				/***************************2.嚙踝蕭��蕭���嚗綽蕭�嚙踝蕭*****************************************/
				Plan_DetailService plan_detailSvc = new Plan_DetailService();
				Plan_DetailVO plan_detailVO = plan_detailSvc.getOnePlan_Detail(pd_no);
				if (plan_detailVO == null) {
					errorMsgs.add("嚙踐�蕭���嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//���蕭���蕭謘�
				}
				
				/***************************3.嚙踐�嚗瘀蕭�嚙踝蕭,��□嚙踐�蕭�瞍�(Send the Success view)*************/
				req.setAttribute("plan_detailVO", plan_detailVO); // ��嚙踐��蕭嚙踐□�嚙踝蕭�lanVO嚙踝��蕭,�謢塚req
				String url = "/front_end/plan_detail/listOnePlan_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭��蕭賹蕭�瞍� listOneplan.jsp
				successView.forward(req, res);

				/***************************嚙踝���□�嚙踝�蕭嚙踝���嚙踐嚙踝蕭*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPKAll_For_Display".equals(action)) { // ���select_page.jsp嚙踝蕭��蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.嚙踐�蕭����蕭蹇蕭�� - ��岳�嚙踐僱��嚙踝���嚙踐嚙踝蕭**********************/
				String str = req.getParameter("plan_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("����蕭������嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//���蕭���蕭謘�
				}
				
				String plan_id = null;
				try {
					plan_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("嚙踐僱��嚙踝�縣�嚙�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//���蕭���蕭謘�
				}
				
				/***************************2.嚙踝蕭��蕭���嚗綽蕭�嚙踝蕭*****************************************/
				Plan_DetailService plan_detailSvc = new Plan_DetailService();
				List<Plan_DetailVO> list = plan_detailSvc.getByPK(plan_id);
				if (list == null) {
					errorMsgs.add("嚙踐�蕭���嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//���蕭���蕭謘�
				}
				
				/***************************3.嚙踐�嚗瘀蕭�嚙踝蕭,��□嚙踐�蕭�瞍�(Send the Success view)*************/
				req.setAttribute("list", list); // ��嚙踐��蕭嚙踐□�嚙踝蕭�lanVO嚙踝��蕭,�謢塚req
				String url = "/front_end/plan_detail/listPKAllPlan_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭��蕭賹蕭�瞍� listOneplan.jsp
				successView.forward(req, res);

				/***************************嚙踝���□�嚙踝�蕭嚙踝���嚙踐嚙踝蕭*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭�嚙踝蕭:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan_detail/select_page.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // ���addEmp.jsp嚙踝蕭��蕭��蕭嚙�  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.嚙踐�蕭����蕭蹇蕭�� - ��岳�嚙踐僱��嚙踝���嚙踐嚙踝蕭*************************/
				
				String plan_id = req.getParameter("plan_id").trim();
				if (plan_id == null || plan_id.trim().length() == 0) {
					errorMsgs.add("������走");
				}
				
				String cou_id = req.getParameter("cou_id").trim();
				if (cou_id == null || cou_id.trim().length() == 0) {
					errorMsgs.add("������走");
				}
				
				java.sql.Date selectdate = null;
				try {
					selectdate = java.sql.Date.valueOf(req.getParameter("selectdate").trim());
				} catch (IllegalArgumentException e) {
					selectdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("����蕭�嚙踐�蕭嚙踝蕭!");
				}
				
				Plan_DetailVO plan_detailVO = new Plan_DetailVO();
				plan_detailVO.setPlan_id(plan_id);
				plan_detailVO.setCou_id(cou_id);
				plan_detailVO.setSelectdate(selectdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("plan_detailVO", plan_detailVO); // 嚙踐�蕭嚙踝��蕭�嚙踐僱�����嚙踝mpVO嚙踝��蕭,���蕭謢塚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/addPlan_Detail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.嚙踝蕭��蕭����筆嚙踝嚙踝蕭***************************************/
				Plan_DetailService plan_detailSvc = new Plan_DetailService();
				plan_detailSvc.addPlan_Detail(plan_id,cou_id,selectdate);
				
				/***************************3.嚙踐��竣嚙踝嚙踝蕭,��□嚙踐�蕭�瞍�(Send the Success view)***********/
				String url = "/front_end/plan_detail/listAllPlan_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踐��等嚙踐�蕭賹蕭�嚙踝瞍彬istAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************嚙踝���□�嚙踝�蕭嚙踝���嚙踐嚙踝蕭**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan_detail/addPlan_Detail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
        if ("insert_calendar".equals(action)) { 
//        	���嚙踐賑est_plan.jsp	
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����������姜瞏 - ���J��都撘�������劑����歇*************************/
				
				String plan_id = req.getParameter("plan_id").trim();
				if (plan_id == null || plan_id.trim().length() == 0) {
					errorMsgs.add("���蝛箔�");
				}
				
				System.out.println(plan_id);
				
				String json = req.getParameter("cou_id").trim();
				if (json == null || json.trim().length() == 0) {
					errorMsgs.add("���蝛箔�");
				}
				System.out.println(json);
				
				
//				json
				JSONArray jsonArray = JSONArray.fromObject(json);  
				System.out.println("jsonArray.size()"+jsonArray.size());
//				System.out.println("jsonArray"+jsonArray);
				
				
				Plan_DetailService pdSvc= new Plan_DetailService();
				List<Plan_DetailVO> list = pdSvc.getByPK(plan_id);
				System.out.println(list.toString());
				
//				嚙踝蕭��蕭����
//				if(list !=null){
//					
//					for(Plan_DetailVO p:list){
//						pdSvc.deletePlan_Detail(p.getPlan_id(), p.getCou_id());
//						
//					}
//				}
				
//				擗虜嚙踝�蕭蹎
				for(int i =0;i<jsonArray.size(); i++){
					
				JSONObject jUser = jsonArray.getJSONObject(i);  
				String cou_id = jUser.getString("cou_id");
			
				java.sql.Date selectdate = null;
				try {
					selectdate = java.sql.Date.valueOf(jUser.getString("date").trim());
				} catch (IllegalArgumentException e) {
					selectdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("����J�����蕭!");
				}
				Plan_DetailVO plan_detailVO = new Plan_DetailVO();
				plan_detailVO.setCou_id(jUser.getString("cou_id"));
				plan_detailVO.setSelectdate(selectdate);

				Plan_DetailService plan_detailSvc = new Plan_DetailService();
				plan_detailSvc.addPlan_Detail(plan_id, cou_id, selectdate);
				
				}
				
				
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("plan_detailVO", plan_detailVO); // ��楠������J��都撘����劑���empVO������,��里��楊���eq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/plan_detail/addPlan_Detail.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/***************************2.��蔆����楊�������蕭***************************************/
//				Plan_DetailService plan_detailSvc = new Plan_DetailService();
//				plan_detailVO = plan_detailSvc.addPlan_Detail(plan_id,cou_id,selectdate);
				
				/***************************3.��楊��������,���������蕭(Send the Success view)***********/
				req.setAttribute("plan_id", plan_id);
				String url = "/front_end/plan_detail/showplan_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ��楊�����������������stAllEmp.jsp
				successView.forward(req, res);				
				System.out.println("新增成功");
				/***************************���L����������劑����歇**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan_detail/addPlan_Detail.jsp");
				failureView.forward(req, res);
				System.out.println("增加課程例外");
			}
		}
        
        
        
        
        if ("getOne_For_Update".equals(action)) { // ���listAllPlan.jsp嚙踝蕭��蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.嚙踐�蕭����蕭蹇蕭��****************************************/
				Integer pd_no = new Integer(req.getParameter("pd_no"));
		
				
				/***************************2.嚙踝蕭��蕭���嚗綽蕭�嚙踝蕭****************************************/
				Plan_DetailService plan_detailSvc = new Plan_DetailService();
				Plan_DetailVO plan_detailVO = plan_detailSvc.getOnePlan_Detail(pd_no);
								
				/***************************3.嚙踐�嚗瘀蕭�嚙踝蕭,��□嚙踐�蕭�瞍�(Send the Success view)************/
				req.setAttribute("plan_detailVO", plan_detailVO);         // ��嚙踐��蕭嚙踐□�嚙踝蕭�mpVO嚙踝��蕭,�謢塚req
				String url = "/front_end/plan_detail/update_plan_detail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭��蕭賹蕭�瞍� update_emp_input.jsp
				successView.forward(req, res);

				/***************************嚙踝���□�嚙踝�蕭嚙踝���嚙踐嚙踝蕭**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝���蕭謘潘蕭謅蕭蹓箄�剁蕭��蕭嚙踝�蕭�嚙踝蕭:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan_detail/listAllPlan_Detail.jsp");
				failureView.forward(req, res);
			}
		}
        
        
if ("update_calendar".equals(action)) { // ���update_emp_input.jsp嚙踝蕭��蕭��蕭嚙�
	System.out.println("123");		
	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	req.setAttribute("errorMsgs", errorMsgs);

	try {
		/***********************1.�����������姜瞏 - ���J��都撘�������劑����歇*************************/
//		System.out.println("123" + pd_no);	
//		Integer pd_no = new Integer(req.getParameter("pd_no").trim());
//		System.out.println("123" + pd_no);	
//		String plan_id = req.getParameter("plan_id").trim();
//		if (plan_id == null || plan_id.trim().length() == 0) {
//			errorMsgs.add("���蝛箔�");
//		}
//		System.out.println(plan_id);
		
		String plan_id = req.getParameter("plan_id");
		String json = req.getParameter("cou_list").trim();
		System.out.println(plan_id);
		System.out.println("123");	
		if (json == null || json.trim().length() == 0) {
			errorMsgs.add("���蝛箔�");
		}
		System.out.println(json);
		
		
//		json
		JSONArray jsonArray = JSONArray.fromObject(json);  
		System.out.println("jsonArray.size()"+jsonArray.size());
		System.out.println("456");	
//		
//		Plan_DetailService pdSvc= new Plan_DetailService();
//		List<Plan_DetailVO> list = pdSvc.getByPK(plan_id);
//		System.out.println(list.toString());
		
//		嚙踝蕭��蕭����
//		if(list !=null){
//			
//			for(Plan_DetailVO p:list){
//				pdSvc.deletePlan_Detail(p.getPlan_id(), p.getCou_id());
//				
//			}
//		}
		
//		擗虜嚙踝�蕭蹎
		for(int i =0;i<jsonArray.size(); i++){
			System.out.println("");	
		JSONObject jUser = jsonArray.getJSONObject(i);  
//		String plan_id = req.getParameter("plan_id");
		String cou_id = jUser.getJSONArray("cou_id").get(0).toString();
		System.out.println(cou_id);
		Integer pd_no = new Integer(jUser.getString("id"));
		System.out.println(jUser + plan_id + cou_id);
		java.sql.Date selectdate = null;
		try {
			selectdate = java.sql.Date.valueOf(jUser.getString("date").trim());
		} catch (IllegalArgumentException e) {
			selectdate=new java.sql.Date(System.currentTimeMillis());
			errorMsgs.add("����J�����蕭!");
		}
		Plan_DetailVO plan_detailVO = new Plan_DetailVO();
		
		plan_detailVO.setCou_id(jUser.getString("cou_id"));
		plan_detailVO.setSelectdate(selectdate);
		System.out.println(jUser.getString("cou_id"));
		
		Plan_DetailService plan_detailSvc = new Plan_DetailService();
		plan_detailVO = plan_detailSvc.updatePlan_Detail(selectdate,pd_no,plan_id,cou_id);
		
		}
		
		
		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("plan_detailVO", plan_detailVO); // ��楠������J��都撘����劑���empVO������,��里��楊���eq
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/front_end/plan_detail/addPlan_Detail.jsp");
//			failureView.forward(req, res);
//			return;
//		}
		
		/***************************2.��蔆����楊�������蕭***************************************/
//		Plan_DetailService plan_detailSvc = new Plan_DetailService();
//		plan_detailVO = plan_detailSvc.addPlan_Detail(plan_id,cou_id,selectdate);
		
		/***************************3.��楊��������,���������蕭(Send the Success view)***********/
		req.setAttribute("plan_id", plan_id);
		String url = "/front_end/plan_detail/showplan_detail.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // ��楊�����������������stAllEmp.jsp
		successView.forward(req, res);				
		
		/***************************���L����������劑����歇**********************************/
	} catch (Exception e) {
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/front_end/plan_detail/addPlan_Detail.jsp");
		failureView.forward(req, res);
	}
}
		
		if ("update".equals(action)) { // ���update_emp_input.jsp嚙踝蕭��蕭��蕭嚙�
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.嚙踐�蕭����蕭蹇蕭�� - ��岳�嚙踐僱��嚙踝���嚙踐嚙踝蕭**********************/
				Integer pd_no = new Integer(req.getParameter("pd_no").trim());
				
				String plan_id = req.getParameter("plan_id").trim();
				
				String cou_id = req.getParameter("cou_id").trim();
				
				java.sql.Date selectdate = null;
				try {
					selectdate = java.sql.Date.valueOf(req.getParameter("selectdate").trim());
				} catch (IllegalArgumentException e) {
					selectdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("����蕭�嚙踐�蕭嚙踝蕭!");
				}
		
				Plan_DetailVO plan_detailVO = new Plan_DetailVO();
				plan_detailVO.setSelectdate(selectdate);
				plan_detailVO.setPd_no(pd_no);
				plan_detailVO.setPlan_id(plan_id);
				plan_detailVO.setCou_id(cou_id);
				
				
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("plan_detailVO", plan_detailVO); // 嚙踐�蕭嚙踝��蕭�嚙踐僱�����嚙踝mpVO嚙踝��蕭,���蕭謢塚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/plan_detail/update_plan_detail_input.jsp");
					failureView.forward(req, res);
					return; //���蕭���蕭謘�
				}
				
				/***************************2.嚙踝蕭��蕭���剁蕭���嚙踝蕭*****************************************/
				Plan_DetailService plan_detailSvc = new Plan_DetailService();
				plan_detailVO = plan_detailSvc.updatePlan_Detail( selectdate,pd_no, plan_id, cou_id);
				
				/***************************3.�����嚙踝蕭,��□嚙踐�蕭�瞍�(Send the Success view)*************/
				req.setAttribute("plan_detailVO", plan_detailVO); // ��嚙踐��pdate嚙踝蕭��蕭賹蕭嚙�,��蕭�蝞蕭��蕭�mpVO嚙踝��蕭,�謢塚req
				String url = "/front_end/plan_detail/listAllPlan_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����蕭嚙踐�蕭賹蕭嚙�,��瞍彬istOneEmp.jsp
				successView.forward(req, res);
		
				/***************************嚙踝���□�嚙踝�蕭嚙踝���嚙踐嚙踝蕭*************************************/
			} catch (Exception e) {
				errorMsgs.add("�����嚙踐��蕭嚙踝蕭:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan_detail/update_plan_detail_input.jsp");
				failureView.forward(req, res);
			}
		}


        
		if ("delete".equals(action)) { // ���listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.嚙踐�蕭����蕭蹇蕭��***************************************/
				Integer pd_no = new Integer(req.getParameter("pd_no").trim());
				
				/***************************2.嚙踝蕭��蕭���蕭謒�嚙踝蕭***************************************/
				Plan_DetailService plan_detailSvc = new Plan_DetailService();
				plan_detailSvc.deletePlan_Detail(pd_no);
				
				/***************************3.嚙踝�蕭謒�嚙踝蕭,��□嚙踐�蕭�瞍�(Send the Success view)***********/								
				String url = "/front_end/plan_detail/listAllPlan_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝�蕭謒蕭嚙踐�蕭賹蕭嚙�,��瞍梧蕭嚙踐筐嚙踐撒�嚙踝�蕭謒蕭嚙踝�蕭��蕭��嚙踝蕭嚙�
				successView.forward(req, res);
				
				/***************************嚙踝���□�嚙踝�蕭嚙踝���嚙踐嚙踝蕭**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝�蕭謒�嚙踐��蕭嚙踝蕭:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/plan_detail/listAllPlan_Detail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
