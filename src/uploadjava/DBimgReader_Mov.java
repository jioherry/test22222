package uploadjava;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/DBimgReader_Mov.do")
public class DBimgReader_Mov extends HttpServlet {

	private static final long serialVersionUID = -3712383412085261305L;
	Connection con = null;
	private static final String SQL = "SELECT mov_img FROM movement WHERE mov_id = ?";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 字元編碼處理
		res.setContentType("image/gif"); // 定義資料型態 Servlet P.78
		ServletOutputStream out = res.getOutputStream();
		
		try {
			// 做動態字元處理，使用 String name = getParameter("name");  name 請用小寫
			String img = req.getParameter("mov_id");
			// 因為 tomcat 版本問題，需加下列程式碼處理 Servlet p.251
			String mov_id = new String(img.getBytes("ISO-8859-1"), "UTF-8");
			
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mov_id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("mov_img"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} 
			else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = req.getServletContext().getResourceAsStream("/front_end/images/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = req.getServletContext().getResourceAsStream("/front_end/images/nopic.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
			if (ds != null) {
				con = ds.getConnection();
			}
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
