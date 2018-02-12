package uploadjava;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/DBGifReader_MovCat.do")
public class DBGifReader_MovCat extends HttpServlet {

	private static final long serialVersionUID = 4517427125261422156L;
	Connection con = null;
	private static final String SQL = "SELECT mov_cat_img FROM movement_category WHERE mov_cat_id = ?";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); // 字元編碼處理
		res.setContentType("image/gif"); // 定義資料型態 Servlet P.78
		ServletOutputStream out = res.getOutputStream();
		
		try {
			// 做動態字元處理，使用 String name = getParameter("name");  name 請用小寫
			String img = req.getParameter("mov_cat_id");
			// 因為 tomcat 版本問題，需加下列程式碼處理 Servlet p.251
			String mov_cat_id = new String(img.getBytes("ISO-8859-1"), "UTF-8");
			
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mov_cat_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("mov_cat_img"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = req.getServletContext().getResourceAsStream("/back_end/images/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = req.getServletContext().getResourceAsStream("/back_end/images/nopic.jpg");
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
