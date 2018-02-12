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
		
/************************************ 來自"getOne_For_Display"的請求 *********************************************************/
		if("getOne_For_Display_A".equals(action) || "getOne_For_Display_B".equals(action)) { // 來自back_end.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
/*************************************** 接收請求參數 - 輸入格式錯誤處理 ********************************************************/
			try {
				String mov_id = request.getParameter("mov_id");
				if (mov_id == null || (mov_id.trim()).length() == 0) {
					errorMsgs.add("請輸入動作編號");
				}
			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
					failureView.forward(request, response);
					return;
				}
				
/****************************************** 開始查詢資料 *********************************************************************/
				MovementService movementSvc = new MovementService();
				MovementVO movementOneVO = movementSvc.getOneMovement(mov_id);
				if (movementOneVO == null) {
					errorMsgs.add("查無資料");
				}
				System.out.println(movementOneVO.getMov_id());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
					failureView.forward(request, response);
					return;
				}
				
/*********************************** 查詢完成，準備轉交 (Send the success view) ***********************************************/
				request.setAttribute("movementOneVO", movementOneVO); // 資料庫取出movementVO物件，存入request
				String url = null;
				if ("getOne_For_Display_A".equals(action)) {
					url = "/back_end/mov/listOneMovement.jsp";
				} else if ("getOne_For_Display_B".equals(action)) {
					url = "/front_end/mov/oneMovement.jsp";
				}
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
/******************************************* 其他可能的錯誤處理 ***************************************************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
		
/*************************************** 來自"getOne_For_Update"的請求 *******************************************************/	
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數
				String mov_id = request.getParameter("mov_id");
				// 開始查詢資料
				MovementService movementSvc = new MovementService();
				MovementVO movementUpdateVO = movementSvc.getOneMovement(mov_id);
				// 查詢完成，準備轉交
				request.setAttribute("movementUpdateVO", movementUpdateVO);
				String url = "/back_end/mov/update_movement_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
			
/********************************************** 來自"Insert"的請求 ***********************************************************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收參數，進行格式錯誤處理
				// 類別
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
				
				// 名稱
				String mov_name = request.getParameter("mov_name");
				String movnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
				if (mov_name == null || mov_name.trim().length() == 0) {
					errorMsgs.add("動作名稱，請勿空白");
				} else if (!mov_name.trim().matches(movnameReg)) {
					errorMsgs.add("動作名稱，只能是中文、英文字母大小寫、數字、_");
				}
				
				// 簡介
				String mov_info = request.getParameter("mov_info").trim();
				if (mov_info == null || mov_info.trim().length() == 0) {
					errorMsgs.add("動作簡介請勿空白");
				}
				
				// 動作圖片
				Part imgpart = request.getPart("mov_img");
				byte[] mov_img = null;
				if ( imgpart != null ) {
					InputStream in = imgpart.getInputStream();
					mov_img = new byte[in.available()];
					in.read(mov_img);
					in.close();
				} else {
					errorMsgs.add("請重新上傳");
				}
				
				// 動作等級
				String mov_level = request.getParameter("mov_level").trim();
				String movlevelReg = "^[(a-zA-Z)]{2,6}$";
				if (mov_level == null || mov_level.trim().length() == 0) {
					errorMsgs.add("動作等級請勿空白");
				} else if (!mov_level.trim().matches(movlevelReg)) {
					errorMsgs.add("動作等級只能是數字");
				}
				
				// 動作時間長度
				String mov_time_length2 = request.getParameter("mov_time_length").trim();
				Integer mov_time_length = new Integer(mov_time_length2);
				String movtimeReg = "^(0|[1-9][0-9]*)$";
				if (mov_time_length == null || mov_time_length.equals(movtimeReg)) {
					errorMsgs.add("動作時間長度請勿空白或 0");
				} 
				
				// 動作影音
				String mov_video = null;
				Part part1 = request.getPart("mov_video");
				request.setCharacterEncoding("UTF-8"); // 處理中文檔名
				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
				System.out.println("ContentType="+request.getContentType()); // 測試用
				
//------------- 測試 eclipse 自已的虛擬路徑--------------------------------------------------------------
//				String saveDirectory = "/Users/ray/Desktop/BA105G3/videos";
//				// 測試用 eclipse 自已的虛擬路徑
//				String realPath = getServletContext().getRealPath(saveDirectory); 
//				// 將真實路徑 getServletContext()，getRealPath()是找 eclipse 自已的虛擬路徑
//----------------------------------------------------------------------------------------------------
				
				String realPath = "C:/BA105G3/video";
				System.out.println("realPath="+realPath); // 測試用
				
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄
				}
				String filename = getFileNameFromPart(part1);
				File videofile = new File(fsaveDirectory, filename);
				part1.write(videofile.toString());
				mov_video = videofile.getPath();
				
				// 包成 MovementVO java 物件
				MovementVO movementOneVO = new MovementVO();
				movementOneVO.setMov_cat_id(mov_cat_id);
				movementOneVO.setMov_name(mov_name);
				movementOneVO.setMov_info(mov_info);
				movementOneVO.setMov_img(mov_img);
				movementOneVO.setMov_level(mov_level);
				movementOneVO.setMov_time_length(mov_time_length);
				movementOneVO.setMov_video(mov_video);
				
				// 開始新增資料
				MovementService movementSvc = new MovementService();
				movementOneVO = movementSvc.addMovement(mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video);
				
				// 新增完畢，準備轉交
				request.setAttribute("movementOneVO", movementOneVO);
				String url = "/back_end/mov/listAllMovement.jsp";
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
				// 接收請求參數，mov_id
				String mov_id = request.getParameter("mov_id").trim();
				
				// 查出資料欄位，並判別輸入資料型態
				// 動作類別
				Integer mov_cat_id = new Integer(request.getParameter("mov_cat_id").trim());
				
				// 動作名稱
				String mov_name = request.getParameter("mov_name");
				String movnameReg = "[(\\u4e00-\\u9fa5_)(a-zA-Z)0-9_]{2,33}";
									// 中文、英文大小寫 a ~ z，數字和_
				if (mov_name == null || mov_name.trim().length() == 0) {
					errorMsgs.add("動作名稱：請勿空白");
				} else if (!mov_name.trim().matches(movnameReg)) {
					errorMsgs.add("動作名稱，只能是中文、英文字母大小寫、數字、_");
				}
				
				// 動作簡介
				String mov_info = request.getParameter("mov_info").trim();
				if (mov_info == null || mov_info.trim().length() == 0) {
					errorMsgs.add("動作簡介請勿空白");
				}
				
				// 動作圖片
				Part part = request.getPart("mov_img");
				byte[] mov_img = null;
				if ( part.getSize() > 0 ) {
					InputStream in = part.getInputStream();
					mov_img = new byte[in.available()];
					in.read(mov_img);
					in.close();
				} 
				
				// 動作等級
				String mov_level = request.getParameter("mov_level").trim();
				String movlevelReg = "^[(a-zA-Z)]{2,6}$";
				if (mov_level == null || mov_level.trim().length() == 0) {
					errorMsgs.add("動作等級請勿空白");
				} else if (!mov_level.trim().matches(movlevelReg)) {
					errorMsgs.add("動作等級只能是數字");
				}
				
				// 動作時間長度
				String mov_time_length2 = request.getParameter("mov_time_length").trim();
				Integer mov_time_length = new Integer(mov_time_length2);
				String movtimeReg = "^(0|[1-9][0-9]*)$";
				if (mov_time_length == null || mov_time_length.equals(movtimeReg)) {
					errorMsgs.add("動作時間長度請勿空白或 0");
				} 
				
				// 動作影音
				String mov_video = null;
				Part part1 = request.getPart("mov_video");
				request.setCharacterEncoding("UTF-8"); // 處理中文檔名
				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
				System.out.println("ContentType="+request.getContentType()); // 測試用
				
//------------- 測試 eclipse 自已的虛擬路徑--------------------------------------------------------------
//				String saveDirectory = "/Users/ray/Desktop/BA105G3/videos";
//				// 測試用 eclipse 自已的虛擬路徑
//				String realPath = getServletContext().getRealPath(saveDirectory); 
//				// 將真實路徑 getServletContext()，getRealPath()是找 eclipse 自已的虛擬路徑
//----------------------------------------------------------------------------------------------------
				
				String realPath = "C:/BA105G3/video";
				System.out.println("realPath="+realPath); // 測試用
				
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄
				}
				String filename = getFileNameFromPart(part1);
				File videofile = new File(fsaveDirectory, filename);
				part1.write(videofile.toString());
				mov_video = videofile.getPath();

				// 將判別完成的資料包成物件
				MovementVO movementOneVO = new MovementVO();
				
				movementOneVO.setMov_cat_id(mov_cat_id);
				movementOneVO.setMov_name(mov_name);
				movementOneVO.setMov_info(mov_info);
				movementOneVO.setMov_img(mov_img);
				movementOneVO.setMov_level(mov_level);
				movementOneVO.setMov_time_length(mov_time_length);
				movementOneVO.setMov_video(mov_video);
				
				// 開始修改資料
				// 取得資料庫連線
				MovementService movementSvc = new MovementService();
				
				// 更改資料
				movementOneVO = movementSvc.updateMovement(mov_id, mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video);
				
				// 修改完成，準備轉教
				request.setAttribute("movementOneVO", movementOneVO); // 資料庫更新完成，正確的movementVO物件，存入 request
				String url = "/back_end/mov/listOneMovement.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/back_index.jsp");
				failureView.forward(request, response);
			}
		}
			
/********************************************** 來自"delete"的請求 ***********************************************************/
//		if ("delete".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			request.setAttribute("errorMsgs", errorMsgs);
//			try {
//				// 接收請求參數，mov_id
//				String mov_id = request.getParameter("mov_id");
//				
//				// 開始刪除資料
//				MovementService movementSvc = new MovementService();
//				movementSvc.deleteMovement(mov_id);
//				
//				// 資料庫刪除完成
//				String url = "/back_end/mov/listAllMovement.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url);
//				successView.forward(request, response);
//				
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗"+ e.getMessage());
//				RequestDispatcher failureView = request.getRequestDispatcher("/back_end/mov/listAllMovement.jsp");
//				failureView.forward(request, response);
//			}
//		}
/************************************ 來自"listMovements_By_Composite_Query"的請求 *********************************************/
		if ("listMovements_By_Composite_Query".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 7.082 tomcat 版本
				// 採用 Map<String, String[]> getParameterMap()的方法
				// 注意：an immutable java.util.Map
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
				// getAll() 內記得要給他 map
				List<MovementVO> list = movementSvc.getAll(map);
				// 前端 JSP: useBean id = "list"，這樣在 request 內的值才可以 send 到前端。
				
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
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
