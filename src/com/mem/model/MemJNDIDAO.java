package com.mem.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class MemJNDIDAO implements MemDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEM_ID, MEM_ACCT, MEM_PSW, MEM_EMAIL,MEM_PHONE, MEM_BONUS, MEM_TITLE, MEM_EXP, MEM_PIC,MEM_NAME, MEM_GENDER,"+ 
"MEM_ADD, MEM_STATUS, MEM_REPNO) VALUES (MEMID.NEXTVAL, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT ="SELECT MEM_ID, MEM_ACCT, MEM_PSW, MEM_EMAIL,MEM_PHONE, MEM_BONUS, MEM_TITLE, MEM_EXP, MEM_PIC,MEM_NAME, MEM_GENDER, MEM_ADD, MEM_STATUS, MEM_REPNO FROM MEMBER ORDER BY mem_id";
	private static final String GET_ONE_STMT_MEMACCT = "SELECT MEM_ID, MEM_ACCT, MEM_PSW, MEM_EMAIL,MEM_PHONE, MEM_BONUS, MEM_TITLE, MEM_EXP, MEM_PIC,MEM_NAME, MEM_GENDER, MEM_ADD, MEM_STATUS, MEM_REPNO FROM MEMBER WHERE mem_acct=?";

	private static final String GET_ONE_STMT = "SELECT MEM_ID, MEM_ACCT, MEM_PSW, MEM_EMAIL,MEM_PHONE, MEM_BONUS, MEM_TITLE, MEM_EXP, MEM_PIC,MEM_NAME, MEM_GENDER, MEM_ADD, MEM_STATUS, MEM_REPNO FROM MEMBER WHERE mem_id=?";
	private static final String DELETE ="DELETE FROM MEMBER WHERE mem_id=?";
	private static final String UPDATE ="UPDATE MEMBER SET MEM_ACCT=?, MEM_PSW=?, MEM_EMAIL=?,MEM_PHONE=?, MEM_BONUS=?, MEM_TITLE=?, MEM_EXP=?, MEM_PIC=?,MEM_NAME=?, MEM_GENDER=?,"+ 
"MEM_ADD=?, MEM_STATUS=?, MEM_REPNO=? WHERE mem_id=?";
	
	
	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_acct());
            pstmt.setString(2, memVO.getMem_psw());
            pstmt.setString(3, memVO.getMem_email());
            pstmt.setString(4, memVO.getMem_phone());
            pstmt.setInt(5, memVO.getMem_bonus());
            pstmt.setString(6, memVO.getMem_title());
            pstmt.setInt(7, memVO.getMem_exp());
            pstmt.setBytes(8, memVO.getMem_pic());
            pstmt.setString(9, memVO.getMem_name());
            pstmt.setString(10, memVO.getMem_gender());
            pstmt.setString(11, memVO.getMem_add());
            pstmt.setString(12, memVO.getMem_status());
            pstmt.setInt(13, memVO.getMem_repno());

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
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_acct());
            pstmt.setString(2, memVO.getMem_psw());
            pstmt.setString(3, memVO.getMem_email());
            pstmt.setString(4, memVO.getMem_phone());
            pstmt.setInt(5, memVO.getMem_bonus());
            pstmt.setString(6, memVO.getMem_title());
            pstmt.setInt(7, memVO.getMem_exp());
            pstmt.setBytes(8, memVO.getMem_pic());
            pstmt.setString(9, memVO.getMem_name());
            pstmt.setString(10, memVO.getMem_gender());
            pstmt.setString(11, memVO.getMem_add());
            pstmt.setString(12, memVO.getMem_status());
            pstmt.setInt(13, memVO.getMem_repno());
            pstmt.setInt(14, memVO.getMem_id());
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
	public void delete(Integer mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			 pstmt.setInt(1, mem_id);

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
	
	public MemVO findByMemAcct(String mem_acct){
		MemVO memVO = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT_MEMACCT);
			
			pstmt.setString(1, mem_acct);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_acct(rs.getString("mem_acct"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_bonus(rs.getInt("mem_bonus"));
				memVO.setMem_title(rs.getString("mem_title"));
				memVO.setMem_exp(rs.getInt("mem_exp"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_gender(rs.getString("mem_gender"));
				memVO.setMem_add(rs.getString("mem_add"));
				memVO.setMem_status(rs.getString("mem_status"));
				memVO.setMem_repno(rs.getInt("mem_repno"));
	            
			}
		} catch (SQLException se) {
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
		    	}catch (SQLException se){
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
		
		return memVO;
	}

	@Override
	public MemVO findByPrimaryKey(Integer mem_id) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_acct(rs.getString("mem_acct"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_bonus(rs.getInt("mem_bonus"));
				memVO.setMem_title(rs.getString("mem_title"));
				memVO.setMem_exp(rs.getInt("mem_exp"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_gender(rs.getString("mem_gender"));
				memVO.setMem_add(rs.getString("mem_add"));
				memVO.setMem_status(rs.getString("mem_status"));
				memVO.setMem_repno(rs.getInt("mem_repno"));
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_acct(rs.getString("mem_acct"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_bonus(rs.getInt("mem_bonus"));
				memVO.setMem_title(rs.getString("mem_title"));
				memVO.setMem_exp(rs.getInt("mem_exp"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_gender(rs.getString("mem_gender"));
				memVO.setMem_add(rs.getString("mem_add"));
				memVO.setMem_status(rs.getString("mem_status"));
				memVO.setMem_repno(rs.getInt("mem_repno"));
				list.add(memVO); // Store the row in the list
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