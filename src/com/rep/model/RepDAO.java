package com.rep.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.*;
import javax.sql.DataSource;

import com.emp.model.EmpVO;


public class RepDAO implements RepDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB4");
			
		} catch(NamingException e){
			e.printStackTrace();
		}
			
	}
	
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
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            
            pstmt.setInt(1, repVO.getMem_id());
            pstmt.setInt(2, repVO.getEmp_id());
            pstmt.setString(3, repVO.getCou_id());
            pstmt.setString(4, repVO.getRep_cont());
            pstmt.setString(5, repVO.getRep_status());
        	
        	pstmt.executeUpdate();
        	
        	
        } catch (SQLException se) {
        	throw new RuntimeException("A database error occured. "
					+ se.getMessage());
        } finally {
        	if(pstmt!=null){
        		try{
        			pstmt.close();
        		}catch(SQLException se){
        			se.printStackTrace(System.err);
        		}
        	}
        	if(con != null){
        		try{
        			con.close();
            	}  catch (Exception e) {
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
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(UPDATE);
	            
	            pstmt.setInt(1, repVO.getMem_id());
	            pstmt.setInt(2, repVO.getEmp_id());
	            pstmt.setString(3, repVO.getCou_id());
	            pstmt.setString(4, repVO.getRep_cont());
	            pstmt.setTimestamp(5, repVO.getRep_date());
	            pstmt.setString(6, repVO.getRep_status());
	            pstmt.setInt(7, repVO.getRep_id());
	        	pstmt.executeUpdate();
	        	
	        	
	        } catch (SQLException se) {
	        	throw new RuntimeException("A database error occured. "
						+ se.getMessage());
	        } finally {
	        	if(pstmt!=null){
	        		try{
	        			pstmt.close();
	        		}catch(SQLException se){
	        			se.printStackTrace(System.err);
	        		}
	        	}
	        	if(con != null){
	        		try{
	        			con.close();
	            	}  catch (Exception e) {
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
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(DELETE);
	            
	            pstmt.setInt(1, rep_id);
	           
	        	
	        	pstmt.executeUpdate();
	        	
	        	
	        } catch (SQLException se) {
	        	throw new RuntimeException("A database error occured. "
						+ se.getMessage());
	        } finally {
	        	if(pstmt!=null){
	        		try{
	        			pstmt.close();
	        		}catch(SQLException se){
	        			se.printStackTrace(System.err);
	        		}
	        	}
	        	if(con != null){
	        		try{
	        			con.close();
	            	}  catch (Exception e) {
	        		e.printStackTrace(System.err);
	        	}
	          }    
	        }
	              
	}

	@Override
	public RepVO findByPrimaryKey(Integer rep_id) {
		RepVO repVO = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, rep_id);
			
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
		
		return repVO;
	}

	@Override
	public List<RepVO> getAll() {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			
			
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

	@Override
	public List<RepVO> findByMemId(Integer mem_id) {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			con=ds.getConnection();
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

	@Override
	public List<RepVO> findByCouId(String cou_id) {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			con=ds.getConnection();
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
    
	
	public List<RepVO> getAll(Map<String, String[]> map) {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
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
		return list;
	}
	
	
	
}
