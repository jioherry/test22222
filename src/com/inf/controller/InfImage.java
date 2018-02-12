package com.inf.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/InfImage.do")
public class InfImage extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		
		

		try {
			Statement stmt = con.createStatement();
			String empno = req.getParameter("inf_id");
			String empno2 = new String(empno.getBytes("ISO-8859-1"), "UTF-8");
			ResultSet rs = stmt.executeQuery(
				"SELECT INF_PIC FROM INFORMATION WHERE INF_ID = '"+empno2+"'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("INF_PIC"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = req.getServletContext().getResourceAsStream("/front_end/images/nopic.jpg");
				byte[] buffer = new byte[in.available()];
				in.read(buffer);
				out.write(buffer);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = req.getServletContext().getResourceAsStream("/front_end/images/nopic.jpg");
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
