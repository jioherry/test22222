package com.cou_cat.model;

import java.sql.*;
import java.util.*;

import com.cou.model.CouVO;

public class CouCatJDBCDAO implements CouCatDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "ba105g3";
	String password = "ba105g3";
	
	private static final String INSERT_STMT =
		"INSERT INTO COURSE_CATEGORY VALUES('C'||LPAD(TO_CHAR(COU_CAT_ID_SEQ.NEXTVAL) , 4 , '0') , ? , ? , ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM COURSE_CATEGORY ORDER BY COU_CAT_ID";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM COURSE_CATEGORY WHERE COU_CAT_ID = ?";
	private static final String UPDATE =
		"UPDATE COURSE_CATEGORY SET COU_CAT_NAME = ? , COU_CAT_INFO = ? , COU_CAT_IMG = ? WHERE COU_CAT_ID = ?";
	
	@Override
	public void insert(CouCatVO cou_catVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1 , cou_catVO.getCou_cat_name());
			pstmt.setString(2 , cou_catVO.getCou_cat_info());
			pstmt.setBytes(3, cou_catVO.getCou_cat_img());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法取得資料51" + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("無法取得資料52" + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(CouCatVO cou_catVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1 , cou_catVO.getCou_cat_name());
			pstmt.setString(2 , cou_catVO.getCou_cat_info());
			pstmt.setBytes(3, cou_catVO.getCou_cat_img());
			pstmt.setString(4, cou_catVO.getCou_cat_id());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法取得資料53" + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("無法取得資料54" + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public CouCatVO findByPrimaryKey(String cou_cat_id) {
		// TODO Auto-generated method stub
		
		CouCatVO cou_catVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cou_cat_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				
				cou_catVO = new CouCatVO();
				cou_catVO.setCou_cat_id(rs.getString("cou_cat_id"));
				cou_catVO.setCou_cat_name(rs.getString("cou_cat_name"));
				cou_catVO.setCou_cat_info(rs.getString("cou_cat_info"));
				cou_catVO.setCou_cat_img(rs.getBytes("cou_cat_img"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法取得資料55" + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("無法取得資料56" + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return cou_catVO;
	}
	@Override
	public List<CouCatVO> getAll() {
		// TODO Auto-generated method stub
		List<CouCatVO> list = new ArrayList<CouCatVO>();
		CouCatVO cou_catVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				cou_catVO = new CouCatVO();
				cou_catVO.setCou_cat_id(rs.getString("cou_cat_id"));
				cou_catVO.setCou_cat_name(rs.getString("cou_cat_name"));
				cou_catVO.setCou_cat_info(rs.getString("cou_cat_info"));
				cou_catVO.setCou_cat_img(rs.getBytes("cou_cat_img"));
				
				list.add(cou_catVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException( "無法取得資料57" + e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("無法取得資料58" + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				} 
			}
			if (pstmt != null) {
				try {
					pstmt.clearBatch();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return list;
	}
	
	public static void main(String[] args) {
		
		CouCatJDBCDAO dao = new CouCatJDBCDAO();
		
		CouCatVO coucatVO1 = new CouCatVO();
		coucatVO1.setCou_cat_name("無法取得資料59");
		coucatVO1.setCou_cat_info("無法取得資料60");
		
		dao.insert(coucatVO1);
		
		CouCatVO cou_catVO2 = new CouCatVO();
		cou_catVO2.setCou_cat_id("C0002");
		cou_catVO2.setCou_cat_name("無法取得資料61");
		cou_catVO2.setCou_cat_info("無法取得資料62");
		
		dao.update(cou_catVO2);
		
		CouCatVO cou_catVO3 = dao.findByPrimaryKey("C0001");
		System.out.println(cou_catVO3.getCou_cat_id());
		System.out.println(cou_catVO3.getCou_cat_name());
		System.out.println(cou_catVO3.getCou_cat_info());
		System.out.println(cou_catVO3.getCou_cat_img());
		
		System.out.println("------------------------------------------------------------------------");
		
		List<CouCatVO> list = dao.getAll();
		for(CouCatVO acou_catVO4 : list) {
			System.out.println(acou_catVO4.getCou_cat_id() + ",");
			System.out.println(acou_catVO4.getCou_cat_name() + ",");
			System.out.println(acou_catVO4.getCou_cat_info() + ",");
			System.out.println(acou_catVO4.getCou_cat_img() + ",");
			
		}
	}



@Override

	public Set<CouVO> getCousByCouCatid(String cou_cat_id) {
		// TODO Auto-generated method stub
		
		Set<CouVO> set = new LinkedHashSet<CouVO>();
		CouVO couVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			pstmt.setString(1, cou_cat_id);
			rs = pstmt.executeQuery();
			System.out.println("resultset = " + rs);
			while (rs.next()) {
				couVO = new CouVO();
				couVO.setCou_id(rs.getString("cou_id"));
				couVO.setCou_cat_id(rs.getString("cou_cat_id"));
				couVO.setMem_id(rs.getInt("mem_id"));
				couVO.setCou_img(rs.getBytes("cou_img"));
				couVO.setCou_intor(rs.getString("cou_intor"));
				couVO.setCou_name(rs.getString("cou_name"));
				couVO.setCou_permi(rs.getString("cou_permi"));
				couVO.setCou_int(rs.getInt("cou_int"));
				couVO.setCre_date(rs.getDate("cre_date"));
				couVO.setCited_count(rs.getString("cited_count"));
				couVO.setCou_cal_cns(rs.getInt("cou_cal_cns"));
				couVO.setCou_time_length(rs.getInt("cou_time_length"));
				set.add(couVO);
			}
		}catch (ClassNotFoundException e) {
			throw new RuntimeException( "無法取得資料63" + e.getMessage());
		
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return set;
	}
}
