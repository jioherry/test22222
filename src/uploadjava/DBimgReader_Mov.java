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
		req.setCharacterEncoding("UTF-8"); // �r���s�X�B�z
		res.setContentType("image/gif"); // �w�q��ƫ��A Servlet P.78
		ServletOutputStream out = res.getOutputStream();
		
		try {
			// ���ʺA�r���B�z�A�ϥ� String name = getParameter("name");  name �ХΤp�g
			String img = req.getParameter("mov_id");
			// �]�� tomcat �������D�A�ݥ[�U�C�{���X�B�z Servlet p.251
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
