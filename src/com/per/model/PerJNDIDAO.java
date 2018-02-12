package com.per.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class PerJNDIDAO implements PerDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO PERMISSION(emp_id, fun_id) VALUES(?,?)";
	private static final String GET_ALL_STMT ="SELECT emp_id, fun_id FROM PERMISSION ORDER BY emp_id";
	private static final String GET_ONE_STMT = "SELECT emp_id, fun_id FROM PERMISSION WHERE emp_id=?";
	private static final String DELETE ="DELETE FROM PERMISSION WHERE emp_id=? AND fun_id=?";
	private static final String UPDATE ="UPDATE PERMISSION SET emp_id=?, fun_id=? WHERE emp_id=? AND fun_id=?";
	private static final String GET_ONE_STMT_BY2 = "SELECT emp_id, fun_id FROM PERMISSION WHERE emp_id=? AND fun_id=?";
	
	
	@Override
	public void insert(PerVO perVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, perVO.getEmp_id());
            pstmt.setInt(2, perVO.getFun_id());
        	
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
	public void update(PerVO perVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, perVO.getEmp_id());
            pstmt.setInt(2, perVO.getFun_id());
            pstmt.setInt(3, perVO.getEmp_id());	            
            pstmt.setInt(4, perVO.getFun_id());
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
	public void delete(Integer emp_id, Integer fun_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_id);
            pstmt.setInt(2, fun_id);
           
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
	public PerVO findByPrimaryKey(Integer emp_id, Integer fun_id) {

		PerVO perVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY2);

			pstmt.setInt(1, emp_id);
			pstmt.setInt(2, fun_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				perVO = new PerVO();
				perVO.setEmp_id(rs.getInt("emp_id"));
				perVO.setFun_id(rs.getInt("fun_id"));
	            
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
		return perVO;
	}

	@Override
	public List<PerVO> getAll() {
		List<PerVO> list = new ArrayList<PerVO>();
		PerVO perVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				perVO = new PerVO();
				perVO.setEmp_id(rs.getInt("emp_id"));
				perVO.setFun_id(rs.getInt("fun_id"));
				list.add(perVO);
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
	
	public List<PerVO> getOnePerList(Integer emp_id) {
		List<PerVO> list = new ArrayList<PerVO>();
		PerVO perVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, emp_id);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
			
				perVO = new PerVO();
				perVO.setEmp_id(rs.getInt("emp_id"));
				perVO.setFun_id(rs.getInt("fun_id"));
				list.add(perVO);
			}		
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
		    if(rs!=null){
		    	try{
		    		rs.close();
		    	}catch(SQLException se){
		    		se.printStackTrace(System.err);
		    	}
		    }
		    if(pstmt!=null){
		    	try{
		    		pstmt.close();
		    	}catch(SQLException se){
		    		se.printStackTrace(System.err);
		    	}
		    }
		    if(con!=null){
		    	try{
		    		con.close();
		    	}catch(Exception e){
		    		e.printStackTrace(System.err);
		    	}
		    }		
		}
		return list;
	}
	
}