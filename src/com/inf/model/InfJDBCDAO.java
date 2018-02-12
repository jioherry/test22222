package com.inf.model;

import java.util.*;
import java.sql.*;

public class InfJDBCDAO implements InfDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba105g3";
	String passwd = "ba105g3";


	private static final String INSERT_STMT = "INSERT INTO INFORMATION(inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic) VALUES(INFOID.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT ="SELECT inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic,inf_date FROM INFORMATION ORDER BY inf_date DESC";
	private static final String GET_ALL_STMT_CAT ="SELECT inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic,inf_date FROM INFORMATION where inf_cat_id=? ORDER BY inf_date DESC";

	
	private static final String GET_ONE_STMT = "SELECT inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic,inf_date FROM INFORMATION WHERE inf_id=?";
	private static final String DELETE ="DELETE FROM INFORMATION WHERE inf_id=?";
	private static final String UPDATE ="UPDATE INFORMATION SET  emp_id=?, inf_cat_id=?, inf_title=?, inf_text=?,inf_pic=? WHERE inf_id=?";
	
	
	@Override
	public void insert(InfVO infVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, infVO.getEmp_id());
            pstmt.setInt(2, infVO.getInf_cat_id());
            pstmt.setString(3, infVO.getInf_title());
            pstmt.setString(4, infVO.getInf_text());
            pstmt.setBytes(5, infVO.getInf_pic());
        	
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
	public void update(InfVO infVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, infVO.getEmp_id());
            pstmt.setInt(2, infVO.getInf_cat_id());
            pstmt.setString(3, infVO.getInf_title());
            pstmt.setString(4, infVO.getInf_text());
            pstmt.setBytes(5, infVO.getInf_pic());
            pstmt.setInt(6, infVO.getInf_id());
            
            
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
	public void delete(Integer inf_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			 pstmt.setInt(1, inf_id);

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
	public InfVO findByPrimaryKey(Integer inf_id) {

		InfVO infVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, inf_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				infVO = new InfVO();
				infVO.setInf_id(rs.getInt("inf_id"));
				infVO.setEmp_id(rs.getInt("emp_id"));
				infVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				infVO.setInf_title(rs.getString("inf_title"));
				infVO.setInf_text(rs.getString("inf_text"));
				infVO.setInf_pic(rs.getBytes("inf_pic"));
				infVO.setInf_date(rs.getTimestamp("inf_date"));
	            
				
				
				
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
		return infVO;
	}

	@Override
	public List<InfVO> getAll() {
		List<InfVO> list = new ArrayList<InfVO>();
		InfVO infVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				infVO = new InfVO();
				
				
				infVO.setInf_id(rs.getInt("inf_id"));
				infVO.setEmp_id(rs.getInt("emp_id"));
				infVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				infVO.setInf_title(rs.getString("inf_title"));
				infVO.setInf_text(rs.getString("inf_text"));
				infVO.setInf_pic(rs.getBytes("inf_pic"));
				infVO.setInf_date(rs.getTimestamp("inf_date"));
				
				
				list.add(infVO); // Store the row in the list
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
	
	public List<InfVO> findInfByCat(Integer inf_cat_id) {
		List<InfVO> list = new ArrayList<InfVO>();
		InfVO infVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_CAT);
			
			pstmt.setInt(1, inf_cat_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				infVO = new InfVO();
				
				
				infVO.setInf_id(rs.getInt("inf_id"));
				infVO.setEmp_id(rs.getInt("emp_id"));
				infVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				infVO.setInf_title(rs.getString("inf_title"));
				infVO.setInf_text(rs.getString("inf_text"));
				infVO.setInf_pic(rs.getBytes("inf_pic"));
				infVO.setInf_date(rs.getTimestamp("inf_date"));
				
				
				list.add(infVO); // Store the row in the list
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

		InfJDBCDAO dao = new InfJDBCDAO();

		// �s�W
//		InfVO infVO1 = new InfVO();
//		infVO1.setEmp_id(8001);
//		infVO1.setInf_cat_id(3);
//		infVO1.setInf_title("111111test");
//		infVO1.setInf_text("222222test");
//		infVO1.setInf_pic(null);
//		dao.insert(infVO1);

		// �ק�
//		InfVO infVO2 = new InfVO();
//		infVO2.setEmp_id(8001);
//		infVO2.setInf_cat_id(3);
//		infVO2.setInf_title("aaaaa");
//		infVO2.setInf_text("aaaaa");
//		infVO2.setInf_pic(null);
//		infVO2.setInf_id(5024);
//		dao.update(infVO2);

		// �R��
//		dao.delete(5024);
	

		// �d��
//		InfVO infVO3 = dao.findByPrimaryKey(5003);
//		System.out.print(infVO3.getInf_id() + ",");
//		System.out.print(infVO3.getEmp_id() + ",");
//		System.out.print(infVO3.getInf_cat_id() + ",");
//		System.out.print(infVO3.getInf_title() + ",");
//		System.out.println(infVO3.getInf_text() + ",");
//		System.out.println(infVO3.getInf_pic() + ",");
//		System.out.println(infVO3.getInf_date() + ",");
//		System.out.println("---------------------");

		// �d��
//		List<InfVO> list = dao.getAll();
//		for (InfVO aEmp : list) {
//			System.out.print(aEmp.getInf_id() + ",");
//			System.out.print(aEmp.getEmp_id() + ",");
//			System.out.print(aEmp.getInf_cat_id() + ",");
//			System.out.print(aEmp.getInf_title() + ",");
//			System.out.print(aEmp.getInf_text() + ",");
//			System.out.print(aEmp.getInf_pic() + ",");
//			System.out.println(aEmp.getInf_date() + ",");
//			System.out.println();
//		}
		List<InfVO> list = dao.findInfByCat(1);
		for (InfVO aEmp : list) {
			System.out.print(aEmp.getInf_id() + ",");
			System.out.print(aEmp.getEmp_id() + ",");
			System.out.print(aEmp.getInf_cat_id() + ",");
			System.out.print(aEmp.getInf_title() + ",");
			System.out.print(aEmp.getInf_text() + ",");
			System.out.print(aEmp.getInf_pic() + ",");
			System.out.println(aEmp.getInf_date() + ",");
			System.out.println();
		}
	}
}