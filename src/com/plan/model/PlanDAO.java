package com.plan.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Plan;


public class PlanDAO implements PlanDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO PLAN(PLAN_ID, MEM_ID, SHAREPLAN, PLAN_NAME, STARTDATE, INTERVAL, CYCLE)"
			+ "VALUES('P'||LPAD(TO_CHAR(PLAN_ID_SEQ.NEXTVAL),5,0), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE PLAN SET MEM_ID = ?, SHAREPLAN = ?, PLAN_NAME = ?,"
			+ "STARTDATE = ?, INTERVAL = ?, CYCLE = ? WHERE PLAN_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM PLAN WHERE PLAN_ID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PLAN WHERE PLAN_ID = ?";
	private static final String GET_ALL = "SELECT * FROM PLAN";
	private static final String FIND_BY_MEM = "SELECT * FROM PLAN WHERE MEM_ID = ?";
	
	
	
	@Override
	public void insert(PlanVO planVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, planVO.getMem_id());
			pstmt.setString(2, planVO.getShareplan());
			pstmt.setString(3, planVO.getPlan_name());
			pstmt.setDate(4, planVO.getStartdate());
			pstmt.setDouble(5, planVO.getInterval());
			pstmt.setInt(6, planVO.getCycle());

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
	public void update(PlanVO planVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setInt(1, planVO.getMem_id());
			pstmt.setString(2, planVO.getShareplan());
			pstmt.setString(3, planVO.getPlan_name());
			pstmt.setDate(4, planVO.getStartdate());
			pstmt.setDouble(5, planVO.getInterval());
			pstmt.setInt(6, planVO.getCycle());
			pstmt.setString(7, planVO.getPlan_id());
			
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
	public void delete(String plan_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, plan_id);
			
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
	public PlanVO findByPK(String plan_id) {
		PlanVO plan = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, plan_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				plan = new PlanVO();
				plan.setPlan_id(rs.getString("PLAN_ID"));
				plan.setMem_id(rs.getInt("MEM_ID"));
				plan.setShareplan(rs.getString("SHAREPLAN"));
				plan.setPlan_name(rs.getString("PLAN_NAME"));
				plan.setStartdate(rs.getDate("STARTDATE"));
				plan.setInterval(rs.getInt("INTERVAL"));
				plan.setCycle(rs.getInt("CYCLE"));
				
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

		return plan;
		
	}

	@Override
	public List<PlanVO> getAll() {
		List<PlanVO> planList = new ArrayList<>();
		PlanVO plan = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				plan = new PlanVO();
				plan.setPlan_id(rs.getString("PLAN_ID"));
				plan.setMem_id(rs.getInt("MEM_ID"));
				plan.setShareplan(rs.getString("SHAREPLAN"));
				plan.setPlan_name(rs.getString("PLAN_NAME"));
				plan.setStartdate(rs.getDate("STARTDATE"));
				plan.setInterval(rs.getInt("INTERVAL"));
				plan.setCycle(rs.getInt("CYCLE"));
				planList.add(plan);
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
		return planList;
	}

	@Override
	public List<PlanVO> getAll(Map<String, String[]> map) {
		List<PlanVO> list = new ArrayList<PlanVO>();
		PlanVO planVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from plan "
		          + jdbcUtil_CompositeQuery_Plan.get_WhereCondition(map)
		          + "order by plan_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("����finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				planVO = new PlanVO();
				planVO.setPlan_id(rs.getString("PLAN_ID"));
				planVO.setMem_id(rs.getInt("MEM_ID"));
				planVO.setShareplan(rs.getString("SHAREPLAN"));
				planVO.setPlan_name(rs.getString("PLAN_NAME"));
				planVO.setStartdate(rs.getDate("STARTDATE"));
				planVO.setInterval(rs.getInt("INTERVAL"));
				planVO.setCycle(rs.getInt("CYCLE"));
				list.add(planVO);
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

	@Override
	public List<PlanVO> getfindByPK(Integer mem_id) {
		List<PlanVO> planList = new ArrayList<>();
		PlanVO plan = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				plan = new PlanVO();
				plan.setPlan_id(rs.getString("PLAN_ID"));
				plan.setMem_id(rs.getInt("MEM_ID"));
				plan.setShareplan(rs.getString("SHAREPLAN"));
				plan.setPlan_name(rs.getString("PLAN_NAME"));
				plan.setStartdate(rs.getDate("STARTDATE"));
				plan.setInterval(rs.getInt("INTERVAL"));
				plan.setCycle(rs.getInt("CYCLE"));
				planList.add(plan);
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
		return planList;
	}
	}
		
	
