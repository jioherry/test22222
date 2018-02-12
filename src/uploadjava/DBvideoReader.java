package uploadjava;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
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

@WebServlet("/DBvideoReader.do")
public class DBvideoReader extends HttpServlet {

	private static final long serialVersionUID = -9188096540776780632L;
	Connection con = null;
	private static final String SQL = "SELECT mov_video FROM movement WHERE mov_id = ?";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 字元編碼處理
		res.setContentType("video/x-msvideo"); // 定義資料型態 Servlet P.78
		ServletOutputStream out = res.getOutputStream();
		
		try {
			// 做動態字元處理，使用 String name = getParameter("name");  name 請用小寫
			String video = req.getParameter("mov_id");
			// 因為 tomcat 版本問題，需加下列程式碼處理 Servlet p.251
			String mov_id = new String(video.getBytes("ISO-8859-1"), "UTF-8");
			
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mov_id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
//-----------------------------------------------------------------------------------------------------------------------				
//				String path = rs.getString("mov_video");
//				System.out.println(path);
//				
//				FileInputStream fis = new FileInputStream(path);
//				BufferedInputStream bis = new BufferedInputStream(fis);
//				
//				byte[] data = new byte[4096];
//				int length;
//				while ((length = bis.read(data)) != -1) {
//					out.write(data, 0, length);
//				}
//
//				bis.close();
//				fis.close();
//				out.close();
//----------------------------------------------------------------------------------------------------------------------
				System.out.println(rs.getString("mov_video"));
				
				FileInputStream fis = new FileInputStream(rs.getString("mov_video"));
				System.out.println(rs.getString("mov_video"));
				BufferedInputStream bis = new BufferedInputStream(fis);
				System.out.println(bis);
				
				byte[] buf = new byte[8 * 1024]; // 4K buffer
				int len;
				while ((len = bis.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				bis.close();
				fis.close();
				out.close();
			} 
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e);
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
	
	public static String readString(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}
	
	public static String readString(Clob clob) throws IOException, SQLException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(clob.getCharacterStream());
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

}
