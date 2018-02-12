package com.movement.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.movement.model.*;

import net.sf.json.JSON;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class MovementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MovementServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String action = request.getParameter("action");
		
/************************************ �Ӧ�"getOne_For_Display"���ШD *********************************************************/
		if("getOne_For_Display_A".equals(action) || "getOne_For_Display_B".equals(action)) { // �Ӧ�back_end.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
/*************************************** �����ШD�Ѽ� - ��J�榡���~�B�z ********************************************************/
			try {
				String mov_id = request.getParameter("mov_id");
				if (mov_id == null || (mov_id.trim()).length() == 0) {
					errorMsgs.add("�п�J�ʧ@�s��");
				}
			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
					failureView.forward(request, response);
					return;
				}
				
/****************************************** �}�l�d�߸�� *********************************************************************/
				MovementService movementSvc = new MovementService();
				MovementVO movementOneVO = movementSvc.getOneMovement(mov_id);
				if (movementOneVO == null) {
					errorMsgs.add("�d�L���");
				}
				System.out.println(movementOneVO.getMov_id());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
					failureView.forward(request, response);
					return;
				}
				
/*********************************** �d�ߧ����A�ǳ���� (Send the success view) ***********************************************/
				request.setAttribute("movementOneVO", movementOneVO); // ��Ʈw���XmovementVO����A�s�Jrequest
				String url = null;
				if ("getOne_For_Display_A".equals(action)) {
					url = "/back_end/mov/listOneMovement.jsp";
				} else if ("getOne_For_Display_B".equals(action)) {
					url = "/front_end/mov/oneMovement.jsp";
				}
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
/******************************************* ��L�i�઺���~�B�z ***************************************************************/
			} catch(Exception e) {
				errorMsgs.add("�L�k���o���");
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
		
/*************************************** �Ӧ�"getOne_For_Update"���ШD *******************************************************/	
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// �����ШD�Ѽ�
				String mov_id = request.getParameter("mov_id");
				// �}�l�d�߸��
				MovementService movementSvc = new MovementService();
				MovementVO movementUpdateVO = movementSvc.getOneMovement(mov_id);
				// �d�ߧ����A�ǳ����
				request.setAttribute("movementUpdateVO", movementUpdateVO);
				String url = "/back_end/mov/update_movement_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			// ��L���~�B�z
			} catch (Exception e) {
				errorMsgs.add("�L�k���o��ơG" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
			
/********************************************** �Ӧ�"Insert"���ШD ***********************************************************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// �����ѼơA�i��榡���~�B�z
				// ���O
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
				
				// �W��
				String mov_name = request.getParameter("mov_name");
				String movnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
				if (mov_name == null || mov_name.trim().length() == 0) {
					errorMsgs.add("�ʧ@�W�١A�ФŪť�");
				} else if (!mov_name.trim().matches(movnameReg)) {
					errorMsgs.add("�ʧ@�W�١A�u��O����B�^��r���j�p�g�B�Ʀr�B_");
				}
				
				// ²��
				String mov_info = request.getParameter("mov_info").trim();
				if (mov_info == null || mov_info.trim().length() == 0) {
					errorMsgs.add("�ʧ@²���ФŪť�");
				}
				
				// �ʧ@�Ϥ�
				Part imgpart = request.getPart("mov_img");
				byte[] mov_img = null;
				if ( imgpart != null ) {
					InputStream in = imgpart.getInputStream();
					mov_img = new byte[in.available()];
					in.read(mov_img);
					in.close();
				} else {
					errorMsgs.add("�Э��s�W��");
				}
				
				// �ʧ@����
				String mov_level = request.getParameter("mov_level").trim();
				String movlevelReg = "^[(a-zA-Z)]{2,6}$";
				if (mov_level == null || mov_level.trim().length() == 0) {
					errorMsgs.add("�ʧ@���ŽФŪť�");
				} else if (!mov_level.trim().matches(movlevelReg)) {
					errorMsgs.add("�ʧ@���ťu��O�Ʀr");
				}
				
				// �ʧ@�ɶ�����
				String mov_time_length2 = request.getParameter("mov_time_length").trim();
				Integer mov_time_length = new Integer(mov_time_length2);
				String movtimeReg = "^(0|[1-9][0-9]*)$";
				if (mov_time_length == null || mov_time_length.equals(movtimeReg)) {
					errorMsgs.add("�ʧ@�ɶ����׽ФŪťթ� 0");
				} 
				
				// �ʧ@�v��
				String mov_video = null;
				Part part1 = request.getPart("mov_video");
				request.setCharacterEncoding("UTF-8"); // �B�z�����ɦW
				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
				System.out.println("ContentType="+request.getContentType()); // ���ե�
				
//------------- ���� eclipse �ۤw���������|--------------------------------------------------------------
//				String saveDirectory = "/Users/ray/Desktop/BA105G3/videos";
//				// ���ե� eclipse �ۤw���������|
//				String realPath = getServletContext().getRealPath(saveDirectory); 
//				// �N�u����| getServletContext()�AgetRealPath()�O�� eclipse �ۤw���������|
//----------------------------------------------------------------------------------------------------
				
				String realPath = "C:/BA105G3/video";
				System.out.println("realPath="+realPath); // ���ե�
				
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�
				}
				String filename = getFileNameFromPart(part1);
				File videofile = new File(fsaveDirectory, filename);
				part1.write(videofile.toString());
				mov_video = videofile.getPath();
				
				// �]�� MovementVO java ����
				MovementVO movementOneVO = new MovementVO();
				movementOneVO.setMov_cat_id(mov_cat_id);
				movementOneVO.setMov_name(mov_name);
				movementOneVO.setMov_info(mov_info);
				movementOneVO.setMov_img(mov_img);
				movementOneVO.setMov_level(mov_level);
				movementOneVO.setMov_time_length(mov_time_length);
				movementOneVO.setMov_video(mov_video);
				
				// �}�l�s�W���
				MovementService movementSvc = new MovementService();
				movementOneVO = movementSvc.addMovement(mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video);
				
				// �s�W�����A�ǳ����
				request.setAttribute("movementOneVO", movementOneVO);
				String url = "/back_end/mov/listAllMovement.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			
			// ��L���~�B�z
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
			
/********************************************** �Ӧ�"Update"���ШD ***********************************************************/
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// �����ШD�ѼơAmov_id
				String mov_id = request.getParameter("mov_id").trim();
				
				// �d�X������A�çP�O��J��ƫ��A
				// �ʧ@���O
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
				
				// �ʧ@�W��
				String mov_name = request.getParameter("mov_name");
				String movnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
									// ����B�^��j�p�g a ~ z�A�Ʀr�M_
				if (mov_name == null || mov_name.trim().length() == 0) {
					errorMsgs.add("�ʧ@�W�١G�ФŪť�");
				} else if (!mov_name.trim().matches(movnameReg)) {
					errorMsgs.add("�ʧ@�W�١A�u��O����B�^��r���j�p�g�B�Ʀr�B_");
				}
				
				// �ʧ@²��
				String mov_info = request.getParameter("mov_info").trim();
				if (mov_info == null || mov_info.trim().length() == 0) {
					errorMsgs.add("�ʧ@²���ФŪť�");
				}
				
				// �ʧ@�Ϥ�
				Part part = request.getPart("mov_img");
				byte[] mov_img = null;
				if ( part.getSize() > 0 ) {
					InputStream in = part.getInputStream();
					mov_img = new byte[in.available()];
					in.read(mov_img);
					in.close();
				} 
				
				// �ʧ@����
				String mov_level = request.getParameter("mov_level").trim();
				String movlevelReg = "^[(a-zA-Z)]{2,6}$";
				if (mov_level == null || mov_level.trim().length() == 0) {
					errorMsgs.add("�ʧ@���ŽФŪť�");
				} else if (!mov_level.trim().matches(movlevelReg)) {
					errorMsgs.add("�ʧ@���ťu��O�Ʀr");
				}
				
				// �ʧ@�ɶ�����
				String mov_time_length2 = request.getParameter("mov_time_length").trim();
				Integer mov_time_length = new Integer(mov_time_length2);
				String movtimeReg = "^(0|[1-9][0-9]*)$";
				if (mov_time_length == null || mov_time_length.equals(movtimeReg)) {
					errorMsgs.add("�ʧ@�ɶ����׽ФŪťթ� 0");
				} 
				
				// �ʧ@�v��
				String mov_video = null;
				Part part1 = request.getPart("mov_video");
				request.setCharacterEncoding("UTF-8"); // �B�z�����ɦW
				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
				System.out.println("ContentType="+request.getContentType()); // ���ե�
				
//------------- ���� eclipse �ۤw���������|--------------------------------------------------------------
//				String saveDirectory = "/Users/ray/Desktop/BA105G3/videos";
//				// ���ե� eclipse �ۤw���������|
//				String realPath = getServletContext().getRealPath(saveDirectory); 
//				// �N�u����| getServletContext()�AgetRealPath()�O�� eclipse �ۤw���������|
//----------------------------------------------------------------------------------------------------
				
				String realPath = "C:/BA105G3/video";
				System.out.println("realPath="+realPath); // ���ե�
				
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�
				}
				String filename = getFileNameFromPart(part1);
				File videofile = new File(fsaveDirectory, filename);
				part1.write(videofile.toString());
				mov_video = videofile.getPath();

				// �N�P�O��������ƥ]������
				MovementVO movementOneVO = new MovementVO();
				
				movementOneVO.setMov_cat_id(mov_cat_id);
				movementOneVO.setMov_name(mov_name);
				movementOneVO.setMov_info(mov_info);
				movementOneVO.setMov_img(mov_img);
				movementOneVO.setMov_level(mov_level);
				movementOneVO.setMov_time_length(mov_time_length);
				movementOneVO.setMov_video(mov_video);
				
				// �}�l�ק���
				// ���o��Ʈw�s�u
				MovementService movementSvc = new MovementService();
				
				// �����
				movementOneVO = movementSvc.updateMovement(mov_id, mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video);
				
				// �ק粒���A�ǳ����
				request.setAttribute("movementOneVO", movementOneVO); // ��Ʈw��s�����A���T��movementVO����A�s�J request
				String url = "/back_end/mov/listOneMovement.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			// ��L���~�B�z
			} catch (Exception e) {
				errorMsgs.add("�L�k���o��ơG" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
			
/********************************************** �Ӧ�"delete"���ШD ***********************************************************/
//		if ("delete".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			request.setAttribute("errorMsgs", errorMsgs);
//			try {
//				// �����ШD�ѼơAmov_id
//				String mov_id = request.getParameter("mov_id");
//				
//				// �}�l�R�����
//				MovementService movementSvc = new MovementService();
//				movementSvc.deleteMovement(mov_id);
//				
//				// ��Ʈw�R������
//				String url = "/back_end/mov/listAllMovement.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url);
//				successView.forward(request, response);
//				
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���"+ e.getMessage());
//				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov/listAllMovement.jsp");
//				failureView.forward(request, response);
//			}
//		}
/************************************ �Ӧ�"listMovements_By_Composite_Query"���ШD *********************************************/
		if ("listMovements_By_Composite_Query".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 7.082 tomcat ����
				// �ĥ� Map<String, String[]> getParameterMap()����k
				// �`�N�Gan immutable java.util.Map
				// Map<String, String[]> map = request.getParameterMap()
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (request.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(request.getParameterMap());
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					map = new HashMap<String, String[]>(request.getParameterMap());
				}
				
				MovementService movementSvc = new MovementService();
				// getAll() ���O�o�n���L map
				List<MovementVO> list = movementSvc.getAll(map);
				// �e�� JSP: useBean id = "list"�A�o�˦b request �����Ȥ~�i�H send ��e�ݡC
				
				request.setAttribute("list", list);
				RequestDispatcher successView = request.getRequestDispatcher("/back_end/mov/listMovements_By_Composite_Query.jsp");
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
		
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
