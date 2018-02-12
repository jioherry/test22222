package com.plan_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Plan_DetailDAO implements Plan_Detail_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO PLAN_DETAIL(PD_NO, PLAN_ID, COU_ID, SELECTDATE)"
			+ "VALUES(PD_NO_SEQ.NEXTVAL, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE PLAN_DETAIL SET SELECTDATE = ?  WHERE PD_NO = ? AND PLAN_ID = ? AND COU_ID = ? ";
	private static final String DELETE_STMT = "DELETE FROM PLAN_DETAIL WHERE PD_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PLAN_DETAIL WHERE PD_NO = ?";
	private static final String GET_BY_PK= "SELECT * FROM PLAN_DETAIL WHERE PLAN_ID = ?";
	private static final String GET_ALL = "SELECT * FROM PLAN_DETAIL";
	
	
	
	@Override
	public void insert(Plan_DetailVO plan_detail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			System.out.println(plan_detail.getPlan_id());
			System.out.println(plan_detail.getCou_id());
			System.out.println(plan_detail.getSelectdate());
			
			
			pstmt.setString(1, plan_detail.getPlan_id());
			pstmt.setString(2, plan_detail.getCou_id());
			pstmt.setDate(3, plan_detail.getSelectdate());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Plan_DetailVO plan_detail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

//			pstmt.setString(1, plan_detail.getPlan_id());
//			pstmt.setString(2, plan_detail.getCou_id());
			pstmt.setDate(1, plan_detail.getSelectdate());
			pstmt.setInt(2, plan_detail.getPd_no());
			pstmt.setString(3, plan_detail.getPlan_id());
			pstmt.setString(4, plan_detail.getCou_id());
			System.out.println("111111111110"+plan_detail.getSelectdate());
			System.out.println("22222222222"+plan_detail.getPd_no());
			System.out.println("3333333330"+plan_detail.getPlan_id());
			System.out.println("144444444444410"+ plan_detail.getCou_id());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer pd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, pd_no);
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Plan_DetailVO findByPK(Integer pd_no) {
		Plan_DetailVO plan_detail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, pd_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				plan_detail = new Plan_DetailVO();
				plan_detail.setPd_no(rs.getInt("PD_NO"));
				plan_detail.setPlan_id(rs.getString("PLAN_ID"));
				plan_detail.setCou_id(rs.getString("COU_ID"));
				plan_detail.setSelectdate(rs.getDate("SELECTDATE"));
				plan_detail.setCom_date(rs.getDate("COM_DATE"));
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

		return plan_detail;
	}

	@Override
	public List<Plan_DetailVO> getByPK(String plan_id) {
		List<Plan_DetailVO> plan_detail_List = new ArrayList<>();
		Plan_DetailVO plan_detail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PK);
			pstmt.setString(1, plan_id);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				plan_detail = new Plan_DetailVO();
				plan_detail.setPd_no(rs.getInt("PD_NO"));
				plan_detail.setPlan_id(rs.getString("PLAN_ID"));
				plan_detail.setCou_id(rs.getString("COU_ID"));
				plan_detail.setSelectdate(rs.getDate("SELECTDATE"));
				plan_detail_List.add(plan_detail);
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
		return plan_detail_List;
	}
	
	@Override
	public List<Plan_DetailVO> getAll() {
		List<Plan_DetailVO> plan_detail_List = new ArrayList<>();
		Plan_DetailVO plan_detail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				plan_detail = new Plan_DetailVO();
				plan_detail.setPd_no(rs.getInt("PD_NO"));
				plan_detail.setPlan_id(rs.getString("PLAN_ID"));
				plan_detail.setCou_id(rs.getString("COU_ID"));
				plan_detail.setSelectdate(rs.getDate("SELECTDATE"));
				plan_detail_List.add(plan_detail);
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
		return plan_detail_List;
	}


}


