package com.mem.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class TestUpload extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5"); // 處理中文檔名
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
		System.out.println("ContentType="+req.getContentType());
		
		
		Part part =req.getPart("mem_pic");
		InputStream in = part.getInputStream();
		byte[] mem_pic = new byte[in.available()];
		in.read(mem_pic);
		in.close();
		
		
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_pic(mem_pic);
		
		
		MemService memSvc = new MemService();
		memVO = memSvc.updateMem(7001, "ssss", "ssss", "ssss", 
				"ssss", 3, "ssss", 3,
				mem_pic, "ssss", "ssss", "ssss", "ssss",
				3);
		
		
	}

}
