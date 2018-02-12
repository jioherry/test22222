package com.fun.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class FunDAO implements FunDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB4");
			
		} catch(NamingException e){
			e.printStackTrace();
		}
			
	}
	
	private static final String INSERT_STMT = "INSERT INTO FUNCTION(fun_id, fun_name) VALUES(FUNID.NEXTVAL,?)";
	private static final String GET_ALL_STMT ="SELECT fun_id, fun_name FROM FUNCTION ORDER BY fun_id";
	private static final String GET_ONE_STMT = "SELECT fun_id, fun_name FROM FUNCTION WHERE fun_id=?";
	private static final String DELETE ="DELETE FROM FUNCTION WHERE fun_id=?";
	private static final String UPDATE ="UPDATE FUNCTION SET fun_name=? WHERE fun_id=?";
	
	
	@Override
	public void insert(FunVO funVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            
            pstmt.setString(1, funVO.getFun_name());
        	
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
	public void update(FunVO funVO) {
		 Connection con = null;
	        PreparedStatement pstmt = null;
	        
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(UPDATE);
	            
	            pstmt.setString(1, funVO.getFun_name());
	            pstmt.setInt(2, funVO.getFun_id());
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
	public void delete(Integer fun_id) {
		 Connection con = null;
	     PreparedStatement pstmt = null;
	        
	        try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(DELETE);
	            
	            pstmt.setInt(1, fun_id);
	           
	        	
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
	public FunVO findByPrimaryKey(Integer fun_id) {
		FunVO funVO = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, fun_id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				funVO = new FunVO();
				funVO.setFun_id(rs.getInt("fun_id"));
				funVO.setFun_name(rs.getString("fun_name"));
	            
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
		
		return funVO;
	}

	@Override
	public List<FunVO> getAll() {
		List<FunVO> list = new ArrayList<FunVO>();
		FunVO funVO =null;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try{
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			
				funVO = new FunVO();
				funVO.setFun_id(rs.getInt("fun_id"));
				funVO.setFun_name(rs.getString("fun_name"));
				list.add(funVO);
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
