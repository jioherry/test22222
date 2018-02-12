package com.chiacou;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cou.model.CouService;
import com.cou.model.CouVO;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import java.util.*;
import com.cou.model.*;
import com.movement.model.*;

//import ajax.model.UserVO;

import com.cou_cat.model.*;
import com.cou_det.model.*;
import uploadjava.*;


public class chiacou extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public chiacou() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		String cou_id = req.getParameter("cou_id");
		System.out.println(action);	
		System.out.println(cou_id);	
		
		if ("listcou".equals(action)) {
			
			JSONArray array = new JSONArray();
			
			CouService couSvc = new CouService();
			Map<String, String[]> map = new TreeMap<String, String[]>();
			map.put("mem_id", new String[] { cou_id });
			
			
			
			CouDetService cdSvc = new CouDetService();
			
			Set<CouDetVO> movlist = cdSvc.getfindByCouid(cou_id);
			
			
			for (CouDetVO CouDetVO : movlist) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("mov_id", CouDetVO.getMov_id());
					obj.put("mov_play_time", CouDetVO.getMov_play_time());
				} catch (Exception e) {
				}
				array.add(obj);
			}
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		
		
		}
		
		
		
		
		
		
		if ("addothermemecou".equals(action)) {
			
			
			String cou_mem_id = req.getParameter("cou_mem_id");
			String mem_id = req.getParameter("mem_id");
			String cou = req.getParameter("cou_id");
			System.out.println(cou_mem_id);
			System.out.println(mem_id);
			
			CouService cSvc = new CouService();
			
			
			CouVO cuoVO = cSvc.getOneCou(cou);
			cuoVO.setMem_id(new Integer(mem_id));
			Set<CouDetVO> couDet = cSvc.getCouDetsByCouID(cou);
			System.out.println(couDet.toString());
			for (CouDetVO aEmp : couDet) {
				System.out.println(aEmp.getMov_id());
				
				
//				cdSvc.addCouDet(next_deptno,aEmp.getMov_id(),aEmp.getMov_order(),aEmp.getMov_play_time());
			}
			cSvc.insertWithCou(cuoVO, couDet);
			
			
			String url = "/front_end/chiacourse/MyGYM.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		
		
		}
		
		
		
		
		
		
		
		
		
		
//		if ("getSelect".equals(action)) {
//			JSONArray array = new JSONArray();
//			// �����僑�����阮������������������蕭
//			if (!"".equals(gradeId) && !"".equals(classId)) {
//				/* ���������������service������w���僑�����阮���������溶 */
//				List<UserVO> list = null;
//				if("AA101".equals(classId)){
//					list = new ArrayList<UserVO>();
//					UserVO user1 = new UserVO();
//					user1.setName("��矽餈���");
//					user1.setNameId("1001");
//					user1.setClassId("AA101");
//					list.add(user1);
//	
//					UserVO user2 = new UserVO();
//					user2.setName("���������");
//					user2.setNameId("1002");
//					user2.setClassId("AA101");
//					list.add(user2);
//					
//					UserVO user3 = new UserVO();
//					user3.setName("���������");
//					user3.setNameId("1003");
//					user3.setClassId("AA101");
//					list.add(user3);
//				}
//				if("AA102".equals(classId)){
//					list = new ArrayList<UserVO>();
//					UserVO user1 = new UserVO();
//					user1.setName("��矽���部�");
//					user1.setNameId("1004");
//					user1.setClassId("AA102");
//					list.add(user1);
//	
//					UserVO user2 = new UserVO();
//					user2.setName("�����Ⅵ��部�");
//					user2.setNameId("1005");
//					user2.setClassId("AA102");
//					list.add(user2);
//				}
//				if("AB101".equals(classId)){
//					list = new ArrayList<UserVO>();
//					UserVO user1 = new UserVO();
//					user1.setName("���������");
//					user1.setNameId("1006");
//					user1.setClassId("AB101");
//					list.add(user1);
//	
//					UserVO user2 = new UserVO();
//					user2.setName("���l����");
//					user2.setNameId("1007");
//					user2.setClassId("AB101");
//					list.add(user2);
//				}
//				if("AB102".equals(classId)){
//					list = new ArrayList<UserVO>();
//					UserVO user1 = new UserVO();
//					user1.setName("����");
//					user1.setNameId("1008");
//					user1.setClassId("AB102");
//					list.add(user1);
//	
//					UserVO user2 = new UserVO();
//					user2.setName("�����蕭");
//					user2.setNameId("1009");
//					user2.setClassId("AB102");
//					list.add(user2);
//				}
//				if("AB103".equals(classId)){
//					list = new ArrayList<UserVO>();
//					UserVO user1 = new UserVO();
//					user1.setName("������");
//					user1.setNameId("1010");
//					user1.setClassId("AB103");
//					list.add(user1);
//				}
//				if("AC101".equals(classId)){
//					list = new ArrayList<UserVO>();
//					UserVO user1 = new UserVO();
//					user1.setName("��狐�����1���");
//					user1.setNameId("1011");
//					user1.setClassId("AC101");
//					list.add(user1);
//					
//					UserVO user2 = new UserVO();
//					user2.setName("��狐�����2���");
//					user2.setNameId("1012");
//					user2.setClassId("AC101");
//					list.add(user2);
//				}
//				/*---------------------------------------------*/
//				for (UserVO usb : list) {
//					JSONObject obj = new JSONObject();
//					try {
//						obj.put("name", usb.getName());
//						obj.put("nameId", usb.getNameId());
//						obj.put("classId", usb.getClassId());
//					} catch (Exception e) {
//					}
//					array.add(obj);
//				}
//			} else {
//				/* ���������������service������w���僑�����阮���������溶 */
//				List<ClassVO> classList = null;
//				if("grade1".equals(gradeId)){
//					classList = new ArrayList<ClassVO>();
//					ClassVO cls1 = new ClassVO();
//					cls1.setClassId("AA101");
//					cls1.setClassName("��������阮");
//					cls1.setGradeId("grade1");
//					classList.add(cls1);
//	
//					ClassVO cls2 = new ClassVO();
//					cls2.setClassId("AA102");
//					cls2.setClassName("��Ⅵ��部���阮");
//					cls2.setGradeId("grade1");
//					classList.add(cls2);
//				}
//				if("grade2".equals(gradeId)){
//					classList = new ArrayList<ClassVO>();
//					ClassVO cls1 = new ClassVO();
//					cls1.setClassId("AB101");
//					cls1.setClassName("������阮");
//					cls1.setGradeId("grade2");
//					classList.add(cls1);
//	
//					ClassVO cls2 = new ClassVO();
//					cls2.setClassId("AB102");
//					cls2.setClassName("������阮");
//					cls2.setGradeId("grade2");
//					classList.add(cls2);
//					
//					ClassVO cls3 = new ClassVO();
//					cls3.setClassId("AB103");
//					cls3.setClassName("������阮");
//					cls3.setGradeId("grade2");
//					classList.add(cls3);
//				}
//				if("grade3".equals(gradeId)){
//					classList = new ArrayList<ClassVO>();
//					ClassVO cls1 = new ClassVO();
//					cls1.setClassId("AC101");
//					cls1.setClassName("��狐�����阮");
//					cls1.setGradeId("grade3");
//					classList.add(cls1);
//				}
//				/*---------------------------------------------*/
//				for (ClassVO csb : classList) {
//					JSONObject obj = new JSONObject();
//					try {
//						obj.put("classId", csb.getClassId());
//						obj.put("gradeId", csb.getGradeId());
//						obj.put("className", csb.getClassName());
//					} catch (Exception e) {
//					}
//					array.add(obj);
//				}
//			}
//			res.setContentType("text/plain");
//			res.setCharacterEncoding("UTF-8");
//			PrintWriter out = res.getWriter();
//			out.write(array.toString());
//			out.flush();
//			out.close();
//		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

}
