package com.movement_category.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.movement.model.*;
import com.movement_category.model.Movement_CategoryService;
import com.movement_category.model.Movement_CategoryVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class Movement_CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Movement_CategoryServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
/********************************* �Ӧ�"listMovements_Bymov_cat"���ШD ****************************************************/
		 	// �Ӧ�back_end.jsp���ШD
		if ("listMovements_Bymov_cat_A".equals(action) || "listMovements_Bymov_cat_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
/*********************************************** �����ШD�Ѽ�  ***************************************************************/
			try {
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id"));
				
				// �}�l�d��
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				Set<MovementVO> movementVO = movcatSvc.getMovementsByMovement_Catrgory(mov_cat_id);
				
/*********************************** �d�ߧ����A�ǳ���� (Send the success view) ***********************************************/
				// �d�X��@�ʧ@���O�����Ҧ����p�ʧ@
				request.setAttribute("movementVO", movementVO); // ��Ʈw���X Set ����A�s�Jrequest
				String url = null;
				if ("listMovements_Bymov_cat_A".equals(action)) {
					url = "/back_end/mov_cat/listMovementsByMovementCategory.jsp";
				} else if ("listMovements_Bymov_cat_B".equals(action)) {
					url = "/front_end/mov/front_listMovementsByMovementCategory.jsp";
				}
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
/********************************************* ��L�i�઺���~�B�z *************************************************************/
			} catch(Exception e) {
				errorMsgs.add("�L�k���o���");
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
/*********************************** �Ӧ�"getOne_For_Update_MovCat"���ШD ****************************************************/
		if ("getOne_For_Update_MovCat".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// �����ШD�Ѽ�
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id"));
				// �}�l�d�߸��
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				Movement_CategoryVO movement_categoryVO = movcatSvc.getOneMovement_Category(mov_cat_id);
				// �d�ߧ����A�ǳ����
				request.setAttribute("movement_categoryVO", movement_categoryVO);
				String url = "/back_end/mov_cat/update_movement_category_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			// ��L���~�B�z
			} catch (Exception e) {
				errorMsgs.add("�L�k���o��ơG" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov_cat/listAllMovementCategory.jsp");
				failureView.forward(request, response);
			}
		}
		
/********************************************** �Ӧ�"Insert"���ШD ***********************************************************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// �����ѼơA�i��榡���~�B�z
				// �W��
				String mov_cat_name = request.getParameter("mov_cat_name");
				String movcatnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
				if (mov_cat_name == null || mov_cat_name.trim().length() == 0) {
					errorMsgs.add("�ʧ@�W�١A�ФŪť�");
				} 
				else if (!mov_cat_name.trim().matches(movcatnameReg)) {
					errorMsgs.add("�ʧ@�W�١A�u��O����B�^��r���j�p�g�B�Ʀr�B_");
				}
				
				// ²��
				String mov_cat_info = request.getParameter("mov_cat_info").trim();
				if (mov_cat_info == null || mov_cat_info.trim().length() == 0) {
					errorMsgs.add("�ʧ@²���ФŪť�");
				}
				
				// �ʧ@�Ϥ�
				Part part = request.getPart("mov_cat_img");
				byte[] mov_cat_img = null;
				if ( part != null ) {
					InputStream in = part.getInputStream();
					mov_cat_img = new byte[in.available()];
					in.read(mov_cat_img);
					in.close();
				} else {
					errorMsgs.add("�Э��s�W��");
				}
				
				// �]�� MovementVO java ����
				Movement_CategoryVO movement_categoryVO = new Movement_CategoryVO();
				movement_categoryVO.setMov_cat_name(mov_cat_name);
				movement_categoryVO.setMov_cat_info(mov_cat_info);
				movement_categoryVO.setMov_cat_img(mov_cat_img);
				
				// �}�l�s�W���
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				movement_categoryVO = movcatSvc.addMovement_Category(mov_cat_name, mov_cat_info, mov_cat_img);
				
				// �s�W�����A�ǳ����
				String url = "/back_end/back_index.jsp";
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
				// �����ШD�Ѽ�
				// �ʧ@���O
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
				
				// �d�X������A�çP�O��J��ƫ��A
				// �ʧ@�W��
				String mov_cat_name = request.getParameter("mov_cat_name");
				String movcatnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
				if (mov_cat_name == null || mov_cat_name.trim().length() == 0) {
					errorMsgs.add("�ʧ@�W�١A�ФŪť�");
				} 
				else if (!mov_cat_name.trim().matches(movcatnameReg)) {
					errorMsgs.add("�ʧ@�W�١A�u��O����B�^��r���j�p�g�B�Ʀr�B_");
				}
				
				// �ʧ@²��
				String mov_cat_info = request.getParameter("mov_cat_info").trim();
				if (mov_cat_info == null || mov_cat_info.trim().length() == 0) {
					errorMsgs.add("�ʧ@²���ФŪť�");
				}
				
				// �ʧ@�Ϥ�
				Part part = request.getPart("mov_cat_img");
				byte[] mov_cat_img = null;
				if ( part.getSize() > 0 ) {
					InputStream in = part.getInputStream();
					mov_cat_img = new byte[in.available()];
					in.read(mov_cat_img);
					in.close();
				} 
				
				// �N�P�O��������ƥ]������
				Movement_CategoryVO movement_categoryVO = new Movement_CategoryVO();
				movement_categoryVO.setMov_cat_id(mov_cat_id);
				movement_categoryVO.setMov_cat_name(mov_cat_name);
				movement_categoryVO.setMov_cat_info(mov_cat_info);
				movement_categoryVO.setMov_cat_img(mov_cat_img);
				
				// �}�l�ק���
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				movement_categoryVO = movcatSvc.updateMovement_Category(mov_cat_id, mov_cat_name, mov_cat_info, mov_cat_img);
				
				// �ק粒���A�ǳ����
				request.setAttribute("movement_categoryVO", movement_categoryVO); // ��Ʈw��s�����A���T��movementVO����A�s�J request
				String url = "/back_end/mov_cat/listAllMovementCategory.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			// ��L���~�B�z
			} catch (Exception e) {
				errorMsgs.add("�L�k���o��ơG" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov_cat/listAllMovementCategory.jsp");
				failureView.forward(request, response);
			}
		}
		
/********************************************** �Ӧ�"delete"���ШD ***********************************************************/
//		if ("delete_MovementCategory".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			request.setAttribute("errorMsgs", errorMsgs);
//			try {
//				// �����ШD�ѼơAmov_id
//				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
//				
//				// �}�l�R�����
//				Movement_CategoryService movement_categoryService = new Movement_CategoryService();
//				movement_categoryService.deleteMovement_Category(mov_cat_id);
//				
//				// ��Ʈw�R������
//				String url = "/back_end/mov/listAllMovementCategory.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url);
//				successView.forward(request, response);
//				
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���"+ e.getMessage());
//				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov/listAllMovementCategory.jsp");
//				failureView.forward(request, response);
//			}
//		}
		
	}
}
