package com.per.model;

import java.util.*;
import java.sql.*;

public class PerJDBCDAO implements PerDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba105g3";
	String passwd = "ba105g3";


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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, perVO.getEmp_id());
            pstmt.setInt(2, perVO.getFun_id());
        	
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
	public void update(PerVO perVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, perVO.getEmp_id());
            pstmt.setInt(2, perVO.getFun_id());
            pstmt.setInt(3, perVO.getEmp_id());	            
            pstmt.setInt(4, perVO.getFun_id());
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
	public void delete(Integer emp_id, Integer fun_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_id);
            pstmt.setInt(2, fun_id);
           
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
	public PerVO findByPrimaryKey(Integer emp_id, Integer fun_id) {

		PerVO perVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	@Override
	public List<PerVO> getOnePerList(Integer emp_id) {
		List<PerVO> list = new ArrayList<PerVO>();
		PerVO perVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, emp_id);
			rs = pstmt.executeQuery();
			
			

			
			while(rs.next()){
			
				perVO = new PerVO();
				perVO.setEmp_id(rs.getInt("emp_id"));
				perVO.setFun_id(rs.getInt("fun_id"));
				list.add(perVO);
			}		
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} 
		finally {
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
	
	
	public static void main(String[] args) {

		PerJDBCDAO dao = new PerJDBCDAO();

		// �s�W
//		PerVO perVO1 = new PerVO();
//		perVO1.setEmp_id(8001);
//		perVO1.setFun_id(9002);
//		dao.insert(perVO1);

		// �L�k�ק�
//		PerVO perVO2 = new PerVO();
//		perVO2.setEmp_id(8007);
//		perVO2.setFun_id(8012);
//		perVO2.setEmp_id(8007);
//		perVO2.setFun_id(9010);
//		
//		dao.update(perVO2);

		// �R��
//		dao.delete(8001, 9001);
	

		// �d��
//		PerVO perVO = dao.findByPrimaryKey(8004, 9009);
//		System.out.print(perVO.getEmp_id() + ",");
//		System.out.println(perVO.getFun_id() + ",");
//		System.out.println("---------------------");
		
		// �d�߭ӤH�Ҧ����v��
		List<PerVO> list = dao.getOnePerList(8002);
		for (PerVO aEmp : list) {
			System.out.print(aEmp.getEmp_id() + ",");
			System.out.println(aEmp.getFun_id() + ",");
			System.out.println();
		}
		
		// �d��
//		List<PerVO> list = dao.getAll();
//		for (PerVO aEmp : list) {
//			System.out.print(aEmp.getEmp_id() + ",");
//			System.out.println(aEmp.getFun_id() + ",");
//			System.out.println();
//		}
	}

	
}