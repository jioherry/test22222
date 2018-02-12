package com.inf_cat.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Inf_catJNDIDAO implements Inf_catDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, inf_catVO.getInf_cat_id());
            pstmt.setString(2, inf_catVO.getInf_cat_name());
        	
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, inf_catVO.getInf_cat_name());
	        pstmt.setInt(2, inf_catVO.getInf_cat_id());
        	pstmt.executeUpdate();
        	
			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, inf_cat_id);
	           
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);


			pstmt.setInt(1, inf_cat_id);
			rs=pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				inf_catVO.setInf_cat_name(rs.getString("inf_cat_name"));
	            
			
			
			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				inf_catVO = new Inf_catVO();
				inf_catVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				inf_catVO.setInf_cat_name(rs.getString("inf_cat_name"));
				list.add(inf_catVO);
			}

			// Handle any driver errors
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

	
}