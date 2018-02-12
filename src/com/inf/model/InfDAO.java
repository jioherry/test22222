package com.inf.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class InfDAO implements InfDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB4");
			
		} catch(NamingException e){
			e.printStackTrace();
		}
			
	}
	
	private static final String INSERT_STMT = "INSERT INTO INFORMATION(inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic) VALUES(INFOID.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT ="SELECT inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic,inf_date FROM INFORMATION ORDER BY inf_date DESC";
	private static final String GET_ALL_STMT_CAT ="SELECT inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic,inf_date FROM INFORMATION where inf_cat_id=? ORDER BY inf_date DESC";
	
	private static final String GET_ONE_STMT = "SELECT inf_id, emp_id, inf_cat_id, inf_title, inf_text,inf_pic,inf_date FROM INFORMATION WHERE inf_id=?";
	private static final String DELETE ="DELETE FROM INFORMATION WHERE inf_id=?";
	private static final String UPDATE ="UPDATE INFORMATION SET emp_id=?, inf_cat_id=?, inf_title=?, inf_text=?,inf_pic=? WHERE inf_id=?";
	
	
	@Override
	public void insert(InfVO infVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            
            pstmt.setInt(1, infVO.getEmp_id());
            pstmt.setInt(2, infVO.getInf_cat_id());
            pstmt.setString(3, infVO.getInf_title());
            pstmt.setString(4, infVO.getInf_text());
            pstmt.setBytes(5, infVO.getInf_pic());
        	
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
	public void update(InfVO infVO) {
		 Connection con = null;
	     PreparedStatement pstmt = null;
	        
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(UPDATE);
	            
	     
	            pstmt.setInt(1, infVO.getEmp_id());
	            pstmt.setInt(2, infVO.getInf_cat_id());
	            pstmt.setString(3, infVO.getInf_title());
	            pstmt.setString(4, infVO.getInf_text());
	            pstmt.setBytes(5, infVO.getInf_pic());
	            pstmt.setInt(6, infVO.getInf_id());
	            
	            
	            
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
	public void delete(Integer inf_id) {
		 Connection con = null;
	     PreparedStatement pstmt = null;
	        
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(DELETE);
	            
	            pstmt.setInt(1, inf_id);
	           
	        	
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
	public InfVO findByPrimaryKey(Integer inf_id) {
		InfVO infVO = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, inf_id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				infVO = new InfVO();
				infVO.setInf_id(rs.getInt("inf_id"));
				infVO.setEmp_id(rs.getInt("emp_id"));
				infVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				infVO.setInf_title(rs.getString("inf_title"));
				infVO.setInf_text(rs.getString("inf_text"));
				infVO.setInf_pic(rs.getBytes("inf_pic"));
				infVO.setInf_date(rs.getTimestamp("inf_date"));
	            
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
		
		return infVO;
	}

	@Override
	public List<InfVO> getAll() {

		List<InfVO> list = new ArrayList<InfVO>();
		InfVO infVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			
				infVO = new InfVO();
				
				
				infVO.setInf_id(rs.getInt("inf_id"));
				infVO.setEmp_id(rs.getInt("emp_id"));
				infVO.setInf_cat_id(rs.getInt("inf_cat_id"));
				infVO.setInf_title(rs.getString("inf_title"));
				infVO.setInf_text(rs.getString("inf_text"));
				infVO.setInf_pic(rs.getBytes("inf_pic"));
				infVO.setInf_date(rs.getTimestamp("inf_date"));
				
				
				list.add(infVO);
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
	
	 public List<InfVO> findInfByCat(Integer inf_cat_id){
		 List<InfVO> list = new ArrayList<InfVO>();
			InfVO infVO =null;
			
			Connection con = null;
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			
			try{
				con=ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_CAT);
				pstmt.setInt(1, inf_cat_id);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
				
					infVO = new InfVO();
					
					
					infVO.setInf_id(rs.getInt("inf_id"));
					infVO.setEmp_id(rs.getInt("emp_id"));
					infVO.setInf_cat_id(rs.getInt("inf_cat_id"));
					infVO.setInf_title(rs.getString("inf_title"));
					infVO.setInf_text(rs.getString("inf_text"));
					infVO.setInf_pic(rs.getBytes("inf_pic"));
					infVO.setInf_date(rs.getTimestamp("inf_date"));
					
					
					list.add(infVO);
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
