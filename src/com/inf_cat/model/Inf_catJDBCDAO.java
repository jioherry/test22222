package com.inf_cat.model;

import java.util.*;
import java.sql.*;

public class Inf_catJDBCDAO implements Inf_catDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba105g3";
	String passwd = "ba105g3";


	private static final String INSERT_STMT = "INSERT INTO INFORMATION_CATEGORY(inf_cat_id, inf_cat_name) VALUES(?,?)";
	private static final String GET_ALL_STMT ="SELECT inf_cat_id, inf_cat_name FROM INFORMATION_CATEGORY ORDER BY inf_cat_id";
	private static final String GET_ONE_STMT = "SELECT inf_cat_id, inf_cat_name FROM INFORMATION_CATEGORY WHERE inf_cat_id=?";
	private static final String DELETE ="DELETE FROM INFORMATION_CATEGORY WHERE inf_cat_id=?";
	private static final String UPDATE ="UPDATE INFORMATION_CATEGORY SET inf_cat_name=? WHERE inf_cat_id=?";
	
	@Override
	public void insert(Inf_catVO inf_catVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, inf_catVO.getInf_cat_id());
            pstmt.setString(2, inf_catVO.getInf_cat_name());
        	
			pstmt.executeUpdate();

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
	public void update(Inf_catVO inf_catVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, inf_catVO.getInf_cat_name());
	        pstmt.setInt(2, inf_catVO.getInf_cat_id());
        	pstmt.executeUpdate();
        	
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
	public void delete(Integer inf_cat_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, inf_cat_id);
	           
			pstmt.executeUpdate();

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
	public Inf_catVO findByPrimaryKey(Integer inf_cat_id) {

		Inf_catVO inf_catVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);


			pstmt.setInt(1, inf_cat_id);
			rs=pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				inf_catVO.setInf_cat_name(rs.getString("inf_cat_name"));
	            
			
			
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
		return inf_catVO;
	}

	@Override
	public List<Inf_catVO> getAll() {
		List<Inf_catVO> list = new ArrayList<Inf_catVO>();
		Inf_catVO inf_catVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				inf_catVO.setInf_cat_name(rs.getString("inf_cat_name"));
				list.add(inf_catVO);
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
		return list;
	}

	public static void main(String[] args) {

		Inf_catJDBCDAO dao = new Inf_catJDBCDAO();

		// 新增
//		Inf_catVO inf_catVO1 = new Inf_catVO();
//		inf_catVO1.setInf_cat_id(22222);
//		inf_catVO1.setInf_cat_name("t222est");
//		dao.insert(inf_catVO1);

		// 修改
//		Inf_catVO inf_catVO2 = new Inf_catVO();
//		inf_catVO2.setInf_cat_name("aaa");
//		inf_catVO2.setInf_cat_id(22222);
//			
//		dao.update(inf_catVO2);

		// 刪除
		dao.delete(22222);
	

		// 查詢
//		Inf_catVO inf_catVO3 = dao.findByPrimaryKey(1);
//		System.out.print(inf_catVO3.getInf_cat_id() + ",");
//		System.out.println(inf_catVO3.getInf_cat_name() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<Inf_catVO> list = dao.getAll();
//		for (Inf_catVO aEmp : list) {
//			System.out.print(aEmp.getInf_cat_id() + ",");
//			System.out.println(aEmp.getInf_cat_name() + ",");
//			System.out.println();
//		}
	}
}