package com.com_cou.model;

import java.util.*;

import java.sql.*;

public class ComCouJDBCDAO implements ComCouDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "ba105g3";
	String password = "ba105g3";
	
	private static final String INSERT_STMT = 
			"INSERT INTO COMPLETE_COURSE VALUES((TO_CHAR(SYSDATE,'yyyymmdd') | | 'C' | | LPAD(TO_CHAR(COU_COM_ID_SEQ.NEXTVAL), 6, '0') ) , ?, ? , (SYSDATE) , ?)";
	private static final String UPDATE =
			"UPDATE COMPLETE_COURSE SET COU_COM_DATE = SYSDATE , DAY_COM_COUNT = ? WHERE COU_ID = ? AND MEM_ID = ?";
	private static final String GET_MEM_ALL_STMT = 
			"SELECT COU_ID FROM COMPLETE_COURSE WHERE MEM_ID = ?";
	private static final String GET_COUID_STMT =
			"SELECT COU_ID , COU_COM_DATE , DAY_COM_COUNT FROM COMPLETE_COURSE WHERE COU_ID = ?";
	
	@Override
	public void insert(ComCouVO com_couVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1 , com_couVO.getMem_id());
			pstmt.setString(2, com_couVO.getCou_id());
			pstmt.setInt(3, com_couVO.getDay_com_count());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e){
			throw new RuntimeException("無法取得資料77"+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("無法取得資料78"+se.getMessage());
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
	public void update(ComCouVO com_couVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, com_couVO.getDay_com_count());
			pstmt.setString(2, com_couVO.getCou_id());
			pstmt.setInt(3, com_couVO.getMem_id());
			
			pstmt.executeUpdate();
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
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
	public Set<ComCouVO> getComCousByCouid(String cou_id) {
		// TODO Auto-generated method stub
		Set<ComCouVO> set = new LinkedHashSet<ComCouVO>();
		ComCouVO com_couVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_COUID_STMT);

			pstmt.setString(1, cou_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				com_couVO = new ComCouVO();
				com_couVO.setCou_id(rs.getString("cou_id"));
				com_couVO.setCou_com_date(rs.getDate("cou_com_date"));
				com_couVO.setDay_com_count(rs.getInt("day_com_count"));
				set.add(com_couVO);

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public Set<ComCouVO> getComCousByMemid(Integer mem_id) {
		// TODO Auto-generated method stub
		Set<ComCouVO> set = new LinkedHashSet<ComCouVO>();
		ComCouVO com_couVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_MEM_ALL_STMT);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				com_couVO = new ComCouVO();
				com_couVO.setCou_id(rs.getString("cou_id"));
				set.add(com_couVO);			
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
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
	
	public static void main(String[] args){
		
		ComCouJDBCDAO dao = new ComCouJDBCDAO();
		
		Set<ComCouVO> com_couVO3 = dao.getComCousByMemid(7002);
		for (ComCouVO acom_couVO3 : com_couVO3) {
		System.out.println(acom_couVO3.getCou_id());
		}
		
	}
}
