package com.movement_category.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.movement.model.MovementVO;

public class Movement_CategoryDAO implements Movement_CatrgoryDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
//----------------------------- SQL 指令 ------------------------------------------------------------------------------------------
	private static final String INSERT_STMT = 
			"INSERT INTO movement_category (mov_cat_id, mov_cat_name, mov_cat_info, mov_cat_img) "
			+ "VALUES ('10' || LPAD((SEQ_MOVEMENT_CATEGORY.NEXTVAL), 8, '0'), ?, ?, ?)";
	
	private static final String GET_ALL_STMT = "SELECT mov_cat_id , mov_cat_name, mov_cat_info, mov_cat_img "
			+ "FROM movement_category";
	
	private static final String GET_ONE_STMT = "SELECT mov_cat_id , mov_cat_name, mov_cat_info, mov_cat_img "
			+ "FROM movement_category WHERE mov_cat_id = ?";
	
	private static final String GET_Movement_Category_Bymov_cat_id_STMT = 
			"SELECT mov_id, mov_cat_id, mov_name, mov_info, mov_img, mov_level, mov_time_length, mov_video "
			+ "FROM movement WHERE mov_cat_id = ? order by mov_id";
	
	private static final String DELETE_Movement = "DELETE FROM movement WHERE mov_cat_id = ?";
	
	private static final String DELETE_Movement_Category = "DELETE FROM movement_category WHERE mov_cat_id = ?";	
	
	private static final String UPDATE = "UPDATE movement_category SET mov_cat_name = ?, mov_cat_info = ?, mov_cat_img = ? "
			+ "WHERE mov_cat_id = ?";
	private static final String UPDATE_NOimg = "UPDATE movement_category SET mov_cat_name = ?, mov_cat_info = ? "
			+ "WHERE mov_cat_id = ?";
//----------------------------- SQL 指令 -------------------------------------------------------------------------------------------
	@Override
	public void insert(Movement_CategoryVO movement_catrgoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, movement_catrgoryVO.getMov_cat_name());
			pstmt.setString(2, movement_catrgoryVO.getMov_cat_info());
			pstmt.setBytes(3, movement_catrgoryVO.getMov_cat_img());
			
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
	public void update(Movement_CategoryVO movement_catrgoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			if (movement_catrgoryVO.getMov_cat_img() == null) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_NOimg);
				
				pstmt.setString(1, movement_catrgoryVO.getMov_cat_name());
				pstmt.setString(2, movement_catrgoryVO.getMov_cat_info());
				pstmt.setInt(3, movement_catrgoryVO.getMov_cat_id());
				
				pstmt.executeUpdate();
			} else {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, movement_catrgoryVO.getMov_cat_name());
				pstmt.setString(2, movement_catrgoryVO.getMov_cat_info());
				pstmt.setBytes(3, movement_catrgoryVO.getMov_cat_img());
				pstmt.setInt(4, movement_catrgoryVO.getMov_cat_id());
				
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
	
	@SuppressWarnings("resource")
	@Override
	public void delete(Integer mov_cat_id) {
		int updateCount_Movement = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			// 1、設定在 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			// 先刪除動作
			pstmt = con.prepareStatement(DELETE_Movement);
			pstmt.setInt(1, mov_cat_id);
			updateCount_Movement = pstmt.executeUpdate();
			// 再刪除類別
			pstmt = con.prepareStatement(DELETE_Movement_Category);
			pstmt.setInt(1, mov_cat_id);
			pstmt.executeUpdate();
			// 2、設定於 pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除類別編號"+ mov_cat_id +"時，共有動作"+ updateCount_Movement +"個同時被刪除");
			
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
	public Movement_CategoryVO findByPrimaryKey(Integer mov_cat_id) {
		Movement_CategoryVO movement_catrgoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, mov_cat_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// movement_catrgoryVO 也稱為 Domain objects
				movement_catrgoryVO = new Movement_CategoryVO();
				movement_catrgoryVO.setMov_cat_id(rs.getInt("mov_cat_id"));
				movement_catrgoryVO.setMov_cat_name(rs.getString("mov_cat_name"));
				movement_catrgoryVO.setMov_cat_info(rs.getString("mov_cat_info"));
				movement_catrgoryVO.setMov_cat_img(rs.getBytes("mov_cat_img"));
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
		return movement_catrgoryVO;
	}
	
	@Override
	public List<Movement_CategoryVO> getAll() {
		List<Movement_CategoryVO> list = new ArrayList<Movement_CategoryVO>();
		Movement_CategoryVO movement_categoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// movementVO 也稱為 Domain objects
				movement_categoryVO = new Movement_CategoryVO();				
				movement_categoryVO.setMov_cat_id(rs.getInt("mov_cat_id"));
				movement_categoryVO.setMov_cat_name(rs.getString("mov_cat_name"));
				movement_categoryVO.setMov_cat_info(rs.getString("mov_cat_info"));
				movement_categoryVO.setMov_cat_img(rs.getBytes("mov_cat_img"));
				list.add(movement_categoryVO);
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
	public Set<MovementVO> getMovementsByMovement_Catrgory(Integer mov_cat_id) {
		Set<MovementVO> set = new LinkedHashSet<MovementVO>();
		MovementVO movementVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Movement_Category_Bymov_cat_id_STMT);
			pstmt.setInt(1, mov_cat_id);
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
				set.add(movementVO); // Store the row in the vector
			}
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
		return set;
	}

}
