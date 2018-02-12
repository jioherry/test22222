package com.cou_det.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.cou_cat.model.CouCatVO;
import com.movement.model.MovementVO;

public class CouDetJDBCDAO implements CouDetDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "ba105g3";
	String password = "ba105g3";
	
	private static final String INSERT_STMT = 
			"INSERT INTO COURSE_DETAIL VALUES (? , ? , ? , ? )";
	private static final String UPDATE =
			"UPDATE COURSE_DETAIL SET MOV_ORDER = ? , MOV_PLAY_TIME = ? WHERE COU_ID = ? AND MOV_ID = ?";
	private static final String DELETE_COURSE =
			"DELETE FROM COURSE_DETAIL WHERE COU_ID = ?";
	private static final String DELETE_MOVE =
			"DELETE FROM COURSE_DETAIL WHERE COU_ID = ? AND MOV_ID = ?";
	private static final String GET_ALL = 
			"SELECT * FROM COURSE_DETAIL ORDER BY COU_ID";
	
	private static final String GET_COURSE_STMT =
			"SELECT * FROM COURSE_DETAIL WHERE COU_ID = ?";
	private static final String GET_MOVS_BYMOVID_STMT =
			"SELECT MOV_ID , MOV_NAME , MOV_INFO , MOV_IMG , MOV_VIDEO , MOV_LEVEL , MOV_TIME_LENGTH FROM MOVEMENT WHERE MOV_ID = ?";
	@Override
	public void insert(CouDetVO cou_detVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1 , cou_detVO.getCou_id());
			pstmt.setString(2 , cou_detVO.getMov_id());
			pstmt.setInt(3, cou_detVO.getMov_order());
			pstmt.setInt(4, cou_detVO.getMov_play_time());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("�瘜�����27" + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("�瘜�����28" + se.getMessage());
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
	public void update(CouDetVO cou_detVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, cou_detVO.getMov_order());
			pstmt.setInt(2, cou_detVO.getMov_play_time());
			pstmt.setString(3, cou_detVO.getCou_id());
			pstmt.setString(4, cou_detVO.getMov_id());
			
			pstmt.executeUpdate();
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
	public void delete(String cou_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE_COURSE);
			
			pstmt.setString(1, cou_id);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("�瘜�����29" + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("�瘜�����30" + se.getMessage());
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
	public void delete(String cou_id, String mov_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE_MOVE);
			
			pstmt.setString(1, cou_id);
			pstmt.setString(2, mov_id);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("�瘜�����31" + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("�瘜�����32" + se.getMessage());
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
	public Set<CouDetVO> getfindByCouid(String cou_id) {
		// TODO Auto-generated method stub
		Set<CouDetVO> set = new LinkedHashSet<CouDetVO>();
		CouDetVO cou_detVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_COURSE_STMT);

			pstmt.setString(1, cou_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cou_detVO = new CouDetVO();
				cou_detVO.setCou_id(rs.getString("cou_id"));
				cou_detVO.setMov_id(rs.getString("mov_id"));
				cou_detVO.setMov_order(rs.getInt("mov_order"));
				cou_detVO.setMov_play_time(rs.getInt("mov_play_time"));
				set.add(cou_detVO);		
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	
	@Override
	public List<CouDetVO> getAll() {
		// TODO Auto-generated method stub
		List<CouDetVO> list = new ArrayList<CouDetVO>();
		CouDetVO cou_detVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				cou_detVO = new CouDetVO();
				cou_detVO.setCou_id(rs.getString("cou_id"));
				cou_detVO.setMov_id(rs.getString("Mov_id"));
				cou_detVO.setMov_order(rs.getInt("mov_order"));
				cou_detVO.setMov_play_time(rs.getInt("mov_play_time"));
				list.add(cou_detVO);
				
			}
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("�瘜�����33" + se.getMessage());	
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
					pstmt.clearBatch();
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
	
	public static void main(String[] args){
		
		CouDetJDBCDAO dao = new CouDetJDBCDAO();
		
		List<CouDetVO> list = dao.getAll();
		for(CouDetVO aCouDet : list) {
			System.out.print(aCouDet.getCou_id() + ",");
			System.out.print(aCouDet.getMov_id() + ",");
			System.out.print(aCouDet.getMov_order() + ",");
			System.out.print(aCouDet.getMov_play_time() + ",");
			System.out.println();
		}
	}

	
	
	@Override
	public MovementVO findByMovID(String mov_id) {
		MovementVO movVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);	
			pstmt = con.prepareStatement(GET_MOVS_BYMOVID_STMT);
			pstmt.setString(1, mov_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				movVO = new MovementVO();
				movVO.setMov_id(rs.getString("mov_id"));
				movVO.setMov_name(rs.getString("mov_name"));
				movVO.setMov_info(rs.getString("mov_info"));;
				movVO.setMov_img(rs.getBytes("mov_img"));
				movVO.setMov_video(rs.getString("mov_video"));
				movVO.setMov_level(rs.getString("mov_level"));
				movVO.setMov_time_length(rs.getInt("mov_time_length"));
			}
			
		}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		return movVO;
	}

	@Override
	public void insert2(CouDetVO cou_detVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cou_detVO.getCou_id());
			pstmt.setString(2, cou_detVO.getMov_id());
			pstmt.setInt(3, cou_detVO.getMov_order());
			pstmt.setInt(4, cou_detVO.getMov_play_time());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}
}
