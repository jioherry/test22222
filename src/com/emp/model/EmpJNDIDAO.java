package com.emp.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class EmpJNDIDAO implements EmpDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE(emp_id, emp_acct, emp_psw, emp_name, emp_email, emp_role) VALUES(EMPID.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT ="SELECT emp_id, emp_acct, emp_psw, emp_name, emp_email, emp_role FROM EMPLOYEE ORDER BY emp_id";
	private static final String GET_ONE_STMT = "SELECT emp_id, emp_acct, emp_psw, emp_name, emp_email, emp_role FROM EMPLOYEE WHERE emp_id=?";
	private static final String DELETE ="DELETE FROM EMPLOYEE WHERE emp_id=?";
	private static final String UPDATE ="UPDATE EMPLOYEE SET emp_acct=?, emp_psw=?, emp_name=?, emp_email=?, emp_role=? WHERE emp_id=?";
	
	
	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_acct());
            pstmt.setString(2, empVO.getEmp_psw());
            pstmt.setString(3, empVO.getEmp_name());
            pstmt.setString(4, empVO.getEmp_email());
            pstmt.setString(5, empVO.getEmp_role());

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
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_acct());
            pstmt.setString(2, empVO.getEmp_psw());
            pstmt.setString(3, empVO.getEmp_name());
            pstmt.setString(4, empVO.getEmp_email());
            pstmt.setString(5, empVO.getEmp_role());
            pstmt.setInt(6, empVO.getEmp_id());
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
	public void delete(Integer emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			 pstmt.setInt(1, emp_id);

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
	public EmpVO findByPrimaryKey(Integer emp_id) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_acct(rs.getString("emp_acct"));
				empVO.setEmp_psw(rs.getString("emp_psw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_role(rs.getString("emp_role"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_acct(rs.getString("emp_acct"));
				empVO.setEmp_psw(rs.getString("emp_psw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_role(rs.getString("emp_role"));
				list.add(empVO); // Store the row in the list
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