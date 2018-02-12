package com.cou_cat.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

import com.cou.model.CouVO;

public class CouCatDAO implements CouCatDAO_interface{

	//DataSource
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT =
			"INSERT INTO COURSE_CATEGORY VALUES('C'||LPAD(TO_CHAR(COU_CAT_ID_SEQ.NEXTVAL) , 4 , '0') , ? , ? , EMPTY_BLOB())";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM COURSE_CATEGORY ORDER BY COU_CAT_ID";
		private static final String GET_Cous_ByCouCatID_STMT =
			"SELECT * FROM COURSE WHERE COU_CAT_ID = ? ORDER BY COU_ID";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM COURSE_CATEGORY WHERE COU_CAT_ID = ?";
		private static final String UPDATE =
			"UPDATE COURSE_CATEGORY SET COU_CAT_NAME = ? , COU_CAT_INFO = ? , COU_CAT_IMG = EMPTY_BLOB() WHERE COU_CAT_ID = ?";
		
		@Override
		public void insert(CouCatVO cou_catVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1 , cou_catVO.getCou_cat_name());
				pstmt.setString(2 , cou_catVO.getCou_cat_info());
//				pstmt.setBytes(3, coucatVO.getCou_cat_img());
				
				pstmt.executeUpdate();
				
			} catch (SQLException se) {
				throw new RuntimeException("無法取得資料65" + se.getMessage());
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
		public void update(CouCatVO cou_catVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1 , cou_catVO.getCou_cat_name());
				pstmt.setString(2 , cou_catVO.getCou_cat_info());
//				pstmt.setBytes(3, coucatVO.getCou_cat_img());
				pstmt.setString(3, cou_catVO.getCou_cat_id());
				
				pstmt.executeUpdate();
				
			} catch (SQLException se) {
				throw new RuntimeException("無法取得資料66" + se.getMessage());
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
		public CouCatVO findByPrimaryKey(String cou_cat_id) {
			// TODO Auto-generated method stub
			
			CouCatVO coucatVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, cou_cat_id);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()){
					
					coucatVO = new CouCatVO();
					coucatVO.setCou_cat_id(rs.getString("cou_cat_id"));
					coucatVO.setCou_cat_name(rs.getString("cou_cat_name"));
					coucatVO.setCou_cat_info(rs.getString("cou_cat_info"));
					coucatVO.setCou_cat_img(rs.getBytes("cou_cat_img"));
				}
			} catch (SQLException se) {
				throw new RuntimeException("無法取得資料67" + se.getMessage());
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
			return coucatVO;
		}
		@Override
		public List<CouCatVO> getAll() {
			// TODO Auto-generated method stub
			List<CouCatVO> list = new ArrayList<CouCatVO>();
			CouCatVO coucatVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					coucatVO = new CouCatVO();
					coucatVO.setCou_cat_id(rs.getString("cou_cat_id"));
					coucatVO.setCou_cat_name(rs.getString("cou_cat_name"));
					coucatVO.setCou_cat_info(rs.getString("cou_cat_info"));
					coucatVO.setCou_cat_img(rs.getBytes("cou_cat_img"));
					
					list.add(coucatVO);
				}
				
			} catch (SQLException se) {
				throw new RuntimeException("無法取得資料68" + se.getMessage());
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
		@Override
		public Set<CouVO> getCousByCouCatid(String cou_cat_id) {
			// TODO Auto-generated method stub
			
			Set<CouVO> set = new LinkedHashSet<CouVO>();
			CouVO couVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_Cous_ByCouCatID_STMT);
				pstmt.setString(1, cou_cat_id);
				rs = pstmt.executeQuery();
				System.out.println("resultset = " + rs);
				while (rs.next()) {
					couVO = new CouVO();
					couVO.setCou_id(rs.getString("cou_id"));
					couVO.setCou_cat_id(rs.getString("cou_cat_id"));
					couVO.setMem_id(rs.getInt("mem_id"));
					couVO.setCou_img(rs.getBytes("cou_img"));
					couVO.setCou_intor(rs.getString("cou_intor"));
					couVO.setCou_name(rs.getString("cou_name"));
					couVO.setCou_permi(rs.getString("cou_permi"));
					couVO.setCou_int(rs.getInt("cou_int"));
					couVO.setCre_date(rs.getDate("cre_date"));
					couVO.setCited_count(rs.getString("cited_count"));
					couVO.setCou_cal_cns(rs.getInt("cou_cal_cns"));
					couVO.setCou_time_length(rs.getInt("cou_time_length"));
					set.add(couVO);
				}
			}catch (SQLException se) {
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
