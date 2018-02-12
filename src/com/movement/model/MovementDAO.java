package com.movement.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import uploadjava.Composite_Query;

public class MovementDAO implements MovementDAO_interface {
// 一個應用程式中，針對一個資料庫，共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
//----------------------------- SQL 指令 ----------------------------------------
	private static final String INSERT_STMT =
		"INSERT INTO movement (mov_id, mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video) "
			+ "VALUES ('M' || LPAD(TO_CHAR(SEQ_MOVEMENT.NEXTVAL), 9, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mov_id, mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video "
			+ "FROM movement ORDER BY mov_id"; // DESC Big -> Small
	private static final String GET_ONE_STMT =
		"SELECT mov_id, mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video "
			+ "FROM movement WHERE mov_id = ?";
	private static final String DELETE =
		"DELETE FROM movement WHERE mov_id = ?";
	private static final String UPDATE_NOimgFILE =
		"UPDATE movement SET "
			+ "mov_cat_id = ?, mov_name = ?, mov_info = ?, mov_level = ?, "
			+ "mov_time_length = ?, mov_video = ? WHERE mov_id = ?";
	private static final String UPDATE_NOviduoFILE =
		"UPDATE movement SET "
				+ "mov_cat_id = ?, mov_name = ?, mov_info = ?, mov_img = ?, "
				+ "mov_level = ?, mov_time_length = ? WHERE mov_id = ?";
	private static final String UPDATE_NOimgANDvideoFILE =
		"UPDATE movement SET "
				+ "mov_cat_id = ?, mov_name = ?, mov_info = ?, mov_level = ?, "
				+ "mov_time_length = ? WHERE mov_id = ?";
	private static final String UPDATE_FILE =
		"UPDATE movement SET "
			+ "mov_cat_id = ?, mov_name = ?, mov_info = ?, mov_img = ?, mov_level = ?, "
			+ "mov_time_length = ?, mov_video = ? WHERE mov_id = ?";
//----------------------------- SQL 指令 ----------------------------------------
	@Override
	public void insert(MovementVO movementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, movementVO.getMov_cat_id());
			pstmt.setString(2, movementVO.getMov_name());
			pstmt.setString(3, movementVO.getMov_info());
			pstmt.setBytes(4, movementVO.getMov_img());
			pstmt.setString(5, movementVO.getMov_level());
			pstmt.setInt(6, movementVO.getMov_time_length());
			pstmt.setString(7, movementVO.getMov_video());
			
			pstmt.executeUpdate();
			
		// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
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
	public void update(MovementVO movementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			if (movementVO.getMov_img() == null) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_NOimgFILE);
				
				pstmt.setInt(1, movementVO.getMov_cat_id());
				pstmt.setString(2, movementVO.getMov_name());
				pstmt.setString(3, movementVO.getMov_info());
				pstmt.setString(4, movementVO.getMov_level());
				pstmt.setInt(5, movementVO.getMov_time_length());
				pstmt.setString(6, movementVO.getMov_video());
				pstmt.setString(7, movementVO.getMov_id());
				
				pstmt.executeUpdate();
				
			} else if (movementVO.getMov_video() == null) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_NOviduoFILE);
				
				pstmt.setInt(1, movementVO.getMov_cat_id());
				pstmt.setString(2, movementVO.getMov_name());
				pstmt.setString(3, movementVO.getMov_info());
				pstmt.setBytes(4, movementVO.getMov_img());
				pstmt.setString(5, movementVO.getMov_level());
				pstmt.setInt(6, movementVO.getMov_time_length());
				pstmt.setString(7, movementVO.getMov_id());
				
				pstmt.executeUpdate();
				
			} else if (movementVO.getMov_img() == null && movementVO.getMov_video().equals("null")) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_NOimgANDvideoFILE);
				
				pstmt.setInt(1, movementVO.getMov_cat_id());
				pstmt.setString(2, movementVO.getMov_name());
				pstmt.setString(3, movementVO.getMov_info());
				pstmt.setString(4, movementVO.getMov_level());
				pstmt.setInt(5, movementVO.getMov_time_length());
				pstmt.setString(6, movementVO.getMov_id());
				
				pstmt.executeUpdate();
				
			} else {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_FILE);
				
				pstmt.setInt(1, movementVO.getMov_cat_id());
				pstmt.setString(2, movementVO.getMov_name());
				pstmt.setString(3, movementVO.getMov_info());
				pstmt.setBytes(4, movementVO.getMov_img());
				pstmt.setString(5, movementVO.getMov_level());
				pstmt.setInt(6, movementVO.getMov_time_length());
				pstmt.setString(7, movementVO.getMov_video());
				pstmt.setString(8, movementVO.getMov_id());
				
				pstmt.executeUpdate();
			}
		// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
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
	public void delete(String mov_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, mov_id);
			pstmt.executeUpdate();
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
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
	public MovementVO findByPrimaryKey(String mov_id) {
		MovementVO movementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, mov_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// movementVO 也稱為 Domain objects
				movementVO = new MovementVO();
				movementVO.setMov_id(rs.getString("mov_id"));
				movementVO.setMov_cat_id(rs.getInt("mov_cat_id"));
				movementVO.setMov_name(rs.getString("mov_name"));
				movementVO.setMov_info(rs.getString("mov_info"));
				movementVO.setMov_img(rs.getBytes("mov_img"));
				movementVO.setMov_level(rs.getString("mov_level"));
				movementVO.setMov_time_length(rs.getInt("mov_time_length"));
				movementVO.setMov_video(rs.getString("mov_video"));
			}
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
//			se.printStackTrace();
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
				} catch(SQLException se) {
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
		return movementVO;
	}
	
	@Override
	public List<MovementVO> getAll() {
		List<MovementVO> list = new ArrayList<MovementVO>();
		MovementVO movementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// movementVO 也稱為 Domain objects
				movementVO = new MovementVO();				
				movementVO.setMov_id(rs.getString("mov_id"));
				movementVO.setMov_cat_id(rs.getInt("mov_cat_id"));
				movementVO.setMov_name(rs.getString("mov_name"));
				movementVO.setMov_info(rs.getString("mov_info"));
				movementVO.setMov_img(rs.getBytes("mov_img"));
				movementVO.setMov_level(rs.getString("mov_level"));
				movementVO.setMov_time_length(rs.getInt("mov_time_length"));
				movementVO.setMov_video(rs.getString("mov_video"));
				list.add(movementVO);
			}
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch(SQLException se) {
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
	public List<MovementVO> getAll(Map<String, String[]> map) {
		List<MovementVO> list = new ArrayList<MovementVO>();
		MovementVO movementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			String finalSQL = "select * from movement "
			          + Composite_Query.get_WhereCondition(map)
			          + "order by mov_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL = " + finalSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				movementVO = new MovementVO();				
				movementVO.setMov_id(rs.getString("mov_id"));
				movementVO.setMov_cat_id(rs.getInt("mov_cat_id"));
				movementVO.setMov_name(rs.getString("mov_name"));
				movementVO.setMov_info(rs.getString("mov_info"));
				movementVO.setMov_img(rs.getBytes("mov_img"));
				movementVO.setMov_level(rs.getString("mov_level"));
				movementVO.setMov_time_length(rs.getInt("mov_time_length"));
				movementVO.setMov_video(rs.getString("mov_video"));
				list.add(movementVO); // Store the row in the List
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch(SQLException se) {
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
