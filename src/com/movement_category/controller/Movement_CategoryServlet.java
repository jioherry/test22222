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
		
/********************************* 來自"listMovements_Bymov_cat"的請求 ****************************************************/
		 	// 來自back_end.jsp的請求
		if ("listMovements_Bymov_cat_A".equals(action) || "listMovements_Bymov_cat_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
/*********************************************** 接收請求參數  ***************************************************************/
			try {
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id"));
				
				// 開始查詢
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				Set<MovementVO> movementVO = movcatSvc.getMovementsByMovement_Catrgory(mov_cat_id);
				
/*********************************** 查詢完成，準備轉交 (Send the success view) ***********************************************/
				// 查出單一動作類別內的所有關聯動作
				request.setAttribute("movementVO", movementVO); // 資料庫取出 Set 物件，存入request
				String url = null;
				if ("listMovements_Bymov_cat_A".equals(action)) {
					url = "/back_end/mov_cat/listMovementsByMovementCategory.jsp";
				} else if ("listMovements_Bymov_cat_B".equals(action)) {
					url = "/front_end/mov/front_listMovementsByMovementCategory.jsp";
				}
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
/********************************************* 其他可能的錯誤處理 *************************************************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
/*********************************** 來自"getOne_For_Update_MovCat"的請求 ****************************************************/
		if ("getOne_For_Update_MovCat".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id"));
				// 開始查詢資料
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				Movement_CategoryVO movement_categoryVO = movcatSvc.getOneMovement_Category(mov_cat_id);
				// 查詢完成，準備轉交
				request.setAttribute("movement_categoryVO", movement_categoryVO);
				String url = "/back_end/mov_cat/update_movement_category_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov_cat/listAllMovementCategory.jsp");
				failureView.forward(request, response);
			}
		}
		
/********************************************** 來自"Insert"的請求 ***********************************************************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收參數，進行格式錯誤處理
				// 名稱
				String mov_cat_name = request.getParameter("mov_cat_name");
				String movcatnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
				if (mov_cat_name == null || mov_cat_name.trim().length() == 0) {
					errorMsgs.add("動作名稱，請勿空白");
				} 
				else if (!mov_cat_name.trim().matches(movcatnameReg)) {
					errorMsgs.add("動作名稱，只能是中文、英文字母大小寫、數字、_");
				}
				
				// 簡介
				String mov_cat_info = request.getParameter("mov_cat_info").trim();
				if (mov_cat_info == null || mov_cat_info.trim().length() == 0) {
					errorMsgs.add("動作簡介請勿空白");
				}
				
				// 動作圖片
				Part part = request.getPart("mov_cat_img");
				byte[] mov_cat_img = null;
				if ( part != null ) {
					InputStream in = part.getInputStream();
					mov_cat_img = new byte[in.available()];
					in.read(mov_cat_img);
					in.close();
				} else {
					errorMsgs.add("請重新上傳");
				}
				
				// 包成 MovementVO java 物件
				Movement_CategoryVO movement_categoryVO = new Movement_CategoryVO();
				movement_categoryVO.setMov_cat_name(mov_cat_name);
				movement_categoryVO.setMov_cat_info(mov_cat_info);
				movement_categoryVO.setMov_cat_img(mov_cat_img);
				
				// 開始新增資料
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				movement_categoryVO = movcatSvc.addMovement_Category(mov_cat_name, mov_cat_info, mov_cat_img);
				
				// 新增完畢，準備轉交
				String url = "/back_end/back_index.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			
			// 其他錯誤處理
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
			
/********************************************** 來自"Update"的請求 ***********************************************************/
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數
				// 動作類別
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
				
				// 查出資料欄位，並判別輸入資料型態
				// 動作名稱
				String mov_cat_name = request.getParameter("mov_cat_name");
				String movcatnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
				if (mov_cat_name == null || mov_cat_name.trim().length() == 0) {
					errorMsgs.add("動作名稱，請勿空白");
				} 
				else if (!mov_cat_name.trim().matches(movcatnameReg)) {
					errorMsgs.add("動作名稱，只能是中文、英文字母大小寫、數字、_");
				}
				
				// 動作簡介
				String mov_cat_info = request.getParameter("mov_cat_info").trim();
				if (mov_cat_info == null || mov_cat_info.trim().length() == 0) {
					errorMsgs.add("動作簡介請勿空白");
				}
				
				// 動作圖片
				Part part = request.getPart("mov_cat_img");
				byte[] mov_cat_img = null;
				if ( part.getSize() > 0 ) {
					InputStream in = part.getInputStream();
					mov_cat_img = new byte[in.available()];
					in.read(mov_cat_img);
					in.close();
				} 
				
				// 將判別完成的資料包成物件
				Movement_CategoryVO movement_categoryVO = new Movement_CategoryVO();
				movement_categoryVO.setMov_cat_id(mov_cat_id);
				movement_categoryVO.setMov_cat_name(mov_cat_name);
				movement_categoryVO.setMov_cat_info(mov_cat_info);
				movement_categoryVO.setMov_cat_img(mov_cat_img);
				
				// 開始修改資料
				Movement_CategoryService movcatSvc = new Movement_CategoryService();
				movement_categoryVO = movcatSvc.updateMovement_Category(mov_cat_id, mov_cat_name, mov_cat_info, mov_cat_img);
				
				// 修改完成，準備轉教
				request.setAttribute("movement_categoryVO", movement_categoryVO); // 資料庫更新完成，正確的movementVO物件，存入 request
				String url = "/back_end/mov_cat/listAllMovementCategory.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov_cat/listAllMovementCategory.jsp");
				failureView.forward(request, response);
			}
		}
		
/********************************************** 來自"delete"的請求 ***********************************************************/
//		if ("delete_MovementCategory".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			request.setAttribute("errorMsgs", errorMsgs);
//			try {
//				// 接收請求參數，mov_id
//				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
//				
//				// 開始刪除資料
//				Movement_CategoryService movement_categoryService = new Movement_CategoryService();
//				movement_categoryService.deleteMovement_Category(mov_cat_id);
//				
//				// 資料庫刪除完成
//				String url = "/back_end/mov/listAllMovementCategory.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url);
//				successView.forward(request, response);
//				
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗"+ e.getMessage());
//				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov/listAllMovementCategory.jsp");
//				failureView.forward(request, response);
//			}
//		}
		
	}
}
