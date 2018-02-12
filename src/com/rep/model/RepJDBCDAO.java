package com.rep.model;

import java.util.*;

import com.emp.model.EmpVO;


import java.sql.*;

public class RepJDBCDAO implements RepDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba105g3";
	String passwd = "ba105g3";


	private static final String INSERT_STMT = "INSERT INTO REPORT(rep_id, mem_id, emp_id, cou_id, rep_cont,rep_status) VALUES(REPID.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT ="SELECT rep_id, mem_id, emp_id, cou_id, rep_cont, rep_date, rep_status FROM REPORT ORDER BY rep_id";
	private static final String GET_ONE_STMT = "SELECT rep_id, mem_id, emp_id, cou_id, rep_cont, rep_date, rep_status FROM REPORT WHERE rep_id=?";
	private static final String GET_ONE_STMT_MEM_ID = "SELECT rep_id, mem_id, emp_id, cou_id, rep_cont, rep_date, rep_status FROM REPORT where MEM_ID=?";
	private static final String GET_ONE_STMT_COU_ID = "SELECT rep_id, mem_id, emp_id, cou_id, rep_cont, rep_date, rep_status FROM REPORT where COU_ID=?";
	
	
	private static final String DELETE ="DELETE FROM REPORT WHERE rep_id=?";
	private static final String UPDATE ="UPDATE REPORT SET mem_id=?, emp_id=?, cou_id=?, rep_cont=?,rep_date=? ,rep_status=? WHERE rep_id=?";
	
	
	@Override
	public void insert(RepVO repVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, repVO.getMem_id());
            pstmt.setInt(2, repVO.getEmp_id());
            pstmt.setString(3, repVO.getCou_id());
            pstmt.setString(4, repVO.getRep_cont());
            pstmt.setString(5, repVO.getRep_status());
        	
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
	public void update(RepVO repVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			 pstmt.setInt(1, repVO.getMem_id());
	            pstmt.setInt(2, repVO.getEmp_id());
	            pstmt.setString(3, repVO.getCou_id());
	            pstmt.setString(4, repVO.getRep_cont());
	            pstmt.setTimestamp(5, repVO.getRep_date());
	            pstmt.setString(6, repVO.getRep_status());
	            pstmt.setInt(7, repVO.getRep_id());
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
	public void delete(Integer rep_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rep_id);
	           
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
	public RepVO findByPrimaryKey(Integer rep_id) {

		RepVO repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rep_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				repVO = new RepVO();
				repVO.setRep_id(rs.getInt("rep_id"));
				repVO.setMem_id(rs.getInt("mem_id"));
				repVO.setEmp_id(rs.getInt("emp_id"));
				repVO.setCou_id(rs.getString("cou_id"));
				repVO.setRep_cont(rs.getString("rep_cont"));
				repVO.setRep_date(rs.getTimestamp("rep_date"));
				repVO.setRep_status(rs.getString("rep_status"));
	            
			
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
		return repVO;
	}

	@Override
	public List<RepVO> getAll() {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO = null;

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
				repVO = new RepVO();
				
				repVO.setRep_id(rs.getInt("rep_id"));
				repVO.setMem_id(rs.getInt("mem_id"));
				repVO.setEmp_id(rs.getInt("emp_id"));
				repVO.setCou_id(rs.getString("cou_id"));
				repVO.setRep_cont(rs.getString("rep_cont"));
				repVO.setRep_date(rs.getTimestamp("rep_date"));
				repVO.setRep_status(rs.getString("rep_status"));
	            
				list.add(repVO); // Store the row in the list
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
	
	public List<RepVO> findByMemId(Integer mem_id) {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_ID);
			
			pstmt.setInt(1, mem_id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
			
				repVO = new RepVO();
				
				repVO.setRep_id(rs.getInt("rep_id"));
				repVO.setMem_id(rs.getInt("mem_id"));
				repVO.setEmp_id(rs.getInt("emp_id"));
				repVO.setCou_id(rs.getString("cou_id"));
				repVO.setRep_cont(rs.getString("rep_cont"));
				repVO.setRep_date(rs.getTimestamp("rep_date"));
				repVO.setRep_status(rs.getString("rep_status"));
	            
				list.add(repVO);
			}		
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}finally {
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

	@Override
	public List<RepVO> findByCouId(String cou_id) {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_COU_ID);
			
			pstmt.setString(1, cou_id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
			
				repVO = new RepVO();
				
				repVO.setRep_id(rs.getInt("rep_id"));
				repVO.setMem_id(rs.getInt("mem_id"));
				repVO.setEmp_id(rs.getInt("emp_id"));
				repVO.setCou_id(rs.getString("cou_id"));
				repVO.setRep_cont(rs.getString("rep_cont"));
				repVO.setRep_date(rs.getTimestamp("rep_date"));
				repVO.setRep_status(rs.getString("rep_status"));
	            
				list.add(repVO);
			}		
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		RepJDBCDAO dao = new RepJDBCDAO();

		// �s�W
//		RepVO repVO1 = new RepVO();
//		repVO1.setMem_id(7006);
//		repVO1.setEmp_id(8005);
//		repVO1.setCou_id("C000003");
//		repVO1.setRep_cont("111test");
//		repVO1.setRep_status("�s�f��");
//		dao.insert(repVO1);
        
		
		Calendar cal = new GregorianCalendar(); 
		Timestamp rep_date = new Timestamp(cal.getTimeInMillis());
		System.out.println(rep_date);
		// �ק�
		RepVO repVO2 = new RepVO();
		repVO2.setMem_id(7002);
		repVO2.setEmp_id(8002);
		repVO2.setCou_id("C000002");
		repVO2.setRep_cont("測試");
		repVO2.setRep_date(rep_date);
		repVO2.setRep_status("未審核");
		repVO2.setRep_id(2001);
		
		dao.update(repVO2);

		// �R��
//		dao.delete(2010);
	

		// �d��
//		RepVO repVO3 = dao.findByPrimaryKey(2005);
//		System.out.print(repVO3.getRep_id() + ",");
//		System.out.print(repVO3.getMem_id() + ",");
//		System.out.print(repVO3.getEmp_id() + ",");
//		System.out.print(repVO3.getCou_id() + ",");
//		System.out.print(repVO3.getRep_cont() + ",");
//		System.out.print(repVO3.getRep_date() + ",");
//		System.out.println(repVO3.getRep_status() + ",");
//		System.out.println("---------------------");

		// �d��
//		List<RepVO> list = dao.getAll();
//		for (RepVO aEmp : list) {
//			System.out.print(aEmp.getRep_id() + ",");
//			System.out.print(aEmp.getMem_id() + ",");
//			System.out.print(aEmp.getEmp_id() + ",");
//			System.out.print(aEmp.getCou_id() + ",");
//			System.out.print(aEmp.getRep_cont() + ",");
//			System.out.print(aEmp.getRep_date() + ",");
//			System.out.println(aEmp.getRep_status() + ",");
//			System.out.println();
//		}
		
//		List<RepVO> list = dao.findByMemId(7002);
//		for (RepVO aEmp : list) {
//			System.out.print(aEmp.getRep_id() + ",");
//			System.out.print(aEmp.getMem_id() + ",");
//			System.out.print(aEmp.getEmp_id() + ",");
//			System.out.print(aEmp.getCou_id() + ",");
//			System.out.print(aEmp.getRep_cont() + ",");
//			System.out.print(aEmp.getRep_date() + ",");
//			System.out.println(aEmp.getRep_status() + ",");
//			System.out.println();
//		}
		
//		List<RepVO> list = dao.findByCouId("C000011");
//		for (RepVO aEmp : list) {
//			System.out.print(aEmp.getRep_id() + ",");
//			System.out.print(aEmp.getMem_id() + ",");
//			System.out.print(aEmp.getEmp_id() + ",");
//			System.out.print(aEmp.getCou_id() + ",");
//			System.out.print(aEmp.getRep_cont() + ",");
//			System.out.print(aEmp.getRep_date() + ",");
//			System.out.println(aEmp.getRep_status() + ",");
//			System.out.println();
//		}
		
		
		
	}

	@Override
	public List<RepVO> getAll(Map<String, String[]> map) {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from report "
		          + RepCompositeQuery.get_WhereCondition(map)
		          + "order by rep_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				repVO = new RepVO();
				repVO.setRep_id(rs.getInt("rep_id"));
				repVO.setMem_id(rs.getInt("mem_id"));
				repVO.setEmp_id(rs.getInt("emp_id"));
				repVO.setCou_id(rs.getString("cou_id"));
				repVO.setRep_cont(rs.getString("rep_cont"));
				repVO.setRep_date(rs.getTimestamp("rep_date"));
				repVO.setRep_status(rs.getString("rep_status"));
				list.add(repVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return list;
	}
}