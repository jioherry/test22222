package com.movement.model;


import java.util.*;
import java.io.*;
import java.sql.*;

public class MovementJDBCDAO implements MovementDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // localhost -> 10.211.55.3
	String user = "Ray";
	String password = "Ray";
	
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
	private static final String UPDATE =
		"UPDATE movement SET "
			+ "mov_cat_id = ?, mov_name = ?, mov_info = ?, mov_img = ?, mov_level = ?, "
			+ "mov_time_length = ?, mov_video = ? WHERE mov_id = ?";

	@Override
	public void insert(MovementVO movementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
	public void update(MovementVO movementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, movementVO.getMov_cat_id());
			pstmt.setString(2, movementVO.getMov_name());
			pstmt.setString(3, movementVO.getMov_info());
			pstmt.setBytes(4, movementVO.getMov_img());
			pstmt.setString(5, movementVO.getMov_level());
			pstmt.setInt(6, movementVO.getMov_time_length());
			pstmt.setString(7, movementVO.getMov_video());
			pstmt.setString(8, movementVO.getMov_id());
			
			pstmt.executeUpdate();
			
		// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
	public void delete(String mov_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, mov_id);
			pstmt.executeUpdate();
			
		// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, mov_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// movementVo
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
		// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				list.add(movementVO);
			}
		// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		MovementJDBCDAO dao = new MovementJDBCDAO();
		// Creat
//		MovementVO movementVO1 = new MovementVO();
//		movementVO1.setMov_cat_id(1000000008);
//		movementVO1.setMov_name("Dumbbell squat");
//		movementVO1.setMov_info("待補充2");
//		try {
//			byte[] mov_img = Img_Video_Upload.ImgVideo_Upload("D:\\Pictures\\1.jpg");
//			movementVO1.setMov_img(mov_img);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		movementVO1.setMov_video(null);
//		movementVO1.setMov_level("5");
//		movementVO1.setMov_time_length(10);
//		dao.insert(movementVO1);
		
		// Update
//		MovementVO movementVO2 = new MovementVO();
//		movementVO2.setMov_cat_id(1000000001);
//		movementVO2.setMov_name("Dumbbell squat2");
//		movementVO2.setMov_info("待補充2");
//		try {
//			byte[] mov_img = Img_Video_Upload.ImgVideo_Upload("D:\\Pictures\\2.jpg");
//			movementVO2.setMov_img(mov_img);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		movementVO2.setMov_video(null);
//		movementVO2.setMov_level("3");
//		movementVO2.setMov_time_length(8);
//		movementVO2.setMov_id("M000000041");
//		dao.update(movementVO2);
		
		// Delete
//		dao.delete("M000000041");
		
		// findByPrimaryKey
//		MovementVO movementVO3 = dao.findByPrimaryKey("M000000001");
//		System.out.print(movementVO3.getMov_id() + ",");
//		System.out.print(movementVO3.getMov_cat_id() + ",");
//		System.out.print(movementVO3.getMov_name() + ",");
//		System.out.print(movementVO3.getMov_info() + ",");
//		System.out.print(movementVO3.getMov_img() + ",");
//		System.out.print(movementVO3.getMov_video() + ",");
//		System.out.println(movementVO3.getMov_level() + ",");
//		System.out.println(movementVO3.getMov_time_length());
//		System.out.println("---------------------");
		
		// GetAll
//		List<MovementVO> list = dao.getAll();
//		for (MovementVO aMov : list) {
//			System.out.print(aMov.getMov_id() + ",");
//			System.out.print(aMov.getMov_cat_id() + ",");
//			System.out.print(aMov.getMov_name() + ",");
//			System.out.print(aMov.getMov_info() + ",");
//			System.out.print(aMov.getMov_img() + ",");
//			System.out.print(aMov.getMov_video() + ",");
//			System.out.print(aMov.getMov_level() + ",");
//			System.out.print(aMov.getMov_time_length());
//			System.out.println();
//		}
		
	}
	
}
