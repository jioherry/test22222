package com.emp.model;

import java.util.*;
import java.sql.*;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba105g3";
	String passwd = "ba105g3";


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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_acct());
            pstmt.setString(2, empVO.getEmp_psw());
            pstmt.setString(3, empVO.getEmp_name());
            pstmt.setString(4, empVO.getEmp_email());
            pstmt.setString(5, empVO.getEmp_role());

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
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_acct());
            pstmt.setString(2, empVO.getEmp_psw());
            pstmt.setString(3, empVO.getEmp_name());
            pstmt.setString(4, empVO.getEmp_email());
            pstmt.setString(5, empVO.getEmp_role());
            pstmt.setInt(6, empVO.getEmp_id());
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
	public void delete(Integer emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			 pstmt.setInt(1, emp_id);

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
	public EmpVO findByPrimaryKey(Integer emp_id) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_acct(rs.getString("emp_acct"));
				empVO.setEmp_psw(rs.getString("emp_psw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_role(rs.getString("emp_role"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
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

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmp_acct("test");
//		empVO1.setEmp_psw("test");
//		empVO1.setEmp_name("test");
//		empVO1.setEmp_email("test");
//		empVO1.setEmp_role("111test");
//		dao.insert(empVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmp_acct("aaa");
//		empVO2.setEmp_psw("aa");
//		empVO2.setEmp_name("aa");
//		empVO2.setEmp_email("xx");
//		empVO2.setEmp_role("xxx");
//		empVO2.setEmp_id(8006);
//		
//		dao.update(empVO2);

		// 刪除
//		dao.delete(8006);
	

		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(8002);
//		System.out.print(empVO3.getEmp_id() + ",");
//		System.out.print(empVO3.getEmp_acct() + ",");
//		System.out.print(empVO3.getEmp_psw() + ",");
//		System.out.print(empVO3.getEmp_name() + ",");
//		System.out.print(empVO3.getEmp_email() + ",");
//		System.out.println(empVO3.getEmp_role() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmp_id() + ",");
			System.out.print(aEmp.getEmp_acct() + ",");
			System.out.print(aEmp.getEmp_psw() + ",");
			System.out.print(aEmp.getEmp_name() + ",");
			System.out.print(aEmp.getEmp_email() + ",");
			System.out.println(aEmp.getEmp_role() + ",");
			System.out.println();
		}
	}
}