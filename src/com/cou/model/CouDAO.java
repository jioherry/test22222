package com.cou.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cou_det.model.CouDetDAO;
import com.cou_det.model.CouDetService;
import com.cou_det.model.CouDetVO;
import com.emp.model.EmpJDBCDAO;
import com.emp.model.EmpVO;
import com.movement.model.MovementVO;

import aboutselect.CompositeQuery;
import uploadjava.CompositeQuery_Cou;

public class CouDAO implements CouDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO COURSE VALUES ('C'| | LPAD(TO_CHAR(COU_ID_SEQ.NEXTVAL) , 6 , '0') , ? , ? , ? , ? , ? , ? , ? , (SYSDATE) , ? , ? , ?, ?)";
	private static final String GET_PUB_ALL_STMT = "SELECT COU_ID , COU_CAT_ID , MEM_ID , COU_IMG , COU_INTOR , COU_NAME , COU_PERMI , COU_INT , TO_CHAR(CRE_DATE,'YYYY-MM-DD') CRE_DATE , CITED_COUNT , COU_CAL_CNS , COU_TIME_LENGTH, DIS_STATE FROM COURSE WHERE MEM_ID = '7001' ORDER BY COU_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM COURSE WHERE COU_ID = ?";
	private static final String DELETE = "DELETE FROM COURSE WHERE COU_ID = ?";
	private static final String UPDATE = "UPDATE COURSE SET COU_CAT_ID = ? , MEM_ID = ?, COU_IMG = ?, COU_INTOR = ?, COU_NAME = ?, COU_PERMI = ?, COU_INT = ?, CRE_DATE = (SYSDATE), CITED_COUNT = ?, COU_CAL_CNS = ?, COU_TIME_LENGTH = ? WHERE COU_ID = ?";
	private static final String DELETE_MOVs = "DELETE FROM course_detail where cou_id = ?";

	private static final String GET_COU_DETAIL = "SELECT * FROM COURSE_DETAIL WHERE COU_ID = ?";

	private static final String GET_MOVEMENT = "SELECT * FROM MOVEMENT WHERE MOV_ID = ?";

	private static final String FIND_BY_MEM = "SELECT * FROM COURSE WHERE MEM_ID = ?";
	
	@Override
	public void insert(CouVO couVO) {
		{

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, couVO.getCou_cat_id());
				pstmt.setInt(2, couVO.getMem_id());
				pstmt.setBytes(3, couVO.getCou_img());
				pstmt.setString(4, couVO.getCou_intor());
				pstmt.setString(5, couVO.getCou_name());
				pstmt.setString(6, couVO.getCou_permi());
				pstmt.setInt(7, couVO.getCou_int());
				pstmt.setString(8, couVO.getCited_count());
				pstmt.setInt(9, couVO.getCou_cal_cns());
				pstmt.setInt(10, couVO.getCou_time_length());
				pstmt.setString(11, couVO.getDis_state());

				pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException("嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕��" + se.getMessage());
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
	}

	@Override
	public void update(CouVO couVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couVO.getCou_cat_id());
			pstmt.setInt(2, couVO.getMem_id());
			pstmt.setBytes(3, couVO.getCou_img());
			pstmt.setString(4, couVO.getCou_intor());
			pstmt.setString(5, couVO.getCou_name());
			pstmt.setString(6, couVO.getCou_permi());
			pstmt.setInt(7, couVO.getCou_int());
			pstmt.setString(8, couVO.getCited_count());
			pstmt.setInt(9, couVO.getCou_cal_cns());
			pstmt.setInt(10, couVO.getCou_time_length());
			pstmt.setString(11, couVO.getDis_state());
			pstmt.setString(12, couVO.getCou_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���1" + se.getMessage());
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

	// ---------------------------------------------------------------------------------------------
	public void delete(String cou_id) {
		int updateCount_MOVs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_MOVs);
			pstmt.setString(1, cou_id);
			updateCount_MOVs = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, cou_id);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			throw new RuntimeException("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���2" + se.getMessage());
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
	// -------------------------------------------------------------------------------------------

	@Override
	public CouVO findByPrimaryKey(String cou_id) {

		CouVO couVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cou_id);

			rs = pstmt.executeQuery();

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
				couVO.setDis_state(rs.getString("dis_state"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���3" + se.getMessage());
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
		return couVO;
	}

	@Override
	public List<CouVO> getPubAll() {

		List<CouVO> list = new ArrayList<CouVO>();
		CouVO couVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PUB_ALL_STMT);
			rs = pstmt.executeQuery();

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
				couVO.setDis_state(rs.getString("dis_state"));
				list.add(couVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("���蕭��蕭謚喉蕭賹雓�雓偌�嚙踝���4" + se.getMessage());
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
	public List<CouVO> getAll(Map<String, String[]> map) {
		List<CouVO> list = new ArrayList<CouVO>();
		CouVO couVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from course " + CompositeQuery.get_WhereCondition(map) + "order by cou_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

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
				couVO.setDis_state(rs.getString("dis_state"));
				list.add(couVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<CouVO> pubAll() {

		return null;
	}

	@Override
	public List<CouVO> oneMemberCous(Map<String, String[]> map) {
		List<CouVO> list = new ArrayList<CouVO>();
		CouVO couVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from course " + CompositeQuery_Cou.get_WhereCondition(map) + "order by cou_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

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
				list.add(couVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<CouDetVO> getCouDetsByCouID(String cou_id) {
		Set<CouDetVO> couDetSet = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_COU_DETAIL);

			pstmt.setString(1, cou_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				CouDetVO couDetVO = new CouDetVO();
				couDetVO.setCou_id(cou_id);
				couDetVO.setMov_id(rs.getString("MOV_ID"));
				couDetVO.setMov_order(rs.getInt("MOV_ORDER"));
				couDetVO.setMov_play_time(rs.getInt("MOV_PLAY_TIME"));
				couDetSet.add(couDetVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return couDetSet;
	}

	@Override
	public MovementVO getMovementByCouId(CouDetVO couDetVO) {
		MovementVO movementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_MOVEMENT);

			pstmt.setString(1, couDetVO.getMov_id());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				movementVO = new MovementVO();
				movementVO.setMov_id(rs.getString("MOV_ID"));
				movementVO.setMov_cat_id(rs.getInt("MOV_CAT_ID"));
				movementVO.setMov_name(rs.getString("MOV_NAME"));
				movementVO.setMov_info(rs.getString("MOV_INFO"));
				movementVO.setMov_img(rs.getBytes("MOV_IMG"));
				movementVO.setMov_level(rs.getString("MOV_LEVEL"));
				movementVO.setMov_time_length(rs.getInt("MOV_TIME_LENGTH"));
				movementVO.setMov_video(rs.getString("MOV_VIDEO"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return movementVO;
	}

	@Override
	public List<CouVO> getfindByPK(Integer mem_id) {
		List<CouVO> couList = new ArrayList<>();
		CouVO couVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();
			

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
				couList.add(couVO);
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
		return couList;
	}

	@Override
	public void insertWithCou(CouVO couVO, Set<CouDetVO> set) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增課程
			String cols[] = {"cou_id"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			
			pstmt.setString(1, couVO.getCou_cat_id());
			pstmt.setInt(2, couVO.getMem_id());
			pstmt.setBytes(3, couVO.getCou_img());
			pstmt.setString(4, couVO.getCou_intor());
			pstmt.setString(5, couVO.getCou_name());
			pstmt.setString(6, couVO.getCou_permi());
			pstmt.setInt(7, couVO.getCou_int());
			pstmt.setString(8, couVO.getCited_count());
			pstmt.setInt(9, couVO.getCou_cal_cns());
			pstmt.setInt(10, couVO.getCou_time_length());
			pstmt.setString(11, couVO.getDis_state());
			
			
			
			
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_deptno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_deptno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_deptno +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增課程明細
//			CouDetService cdSvc = new CouDetService();
			CouDetDAO cdDao = new CouDetDAO();
			System.out.println("list.size()-A="+set.size());
			for (CouDetVO aEmp : set) {
				aEmp.setCou_id(next_deptno);
				aEmp.setMov_id(aEmp.getMov_id());
				aEmp.setMov_order(aEmp.getMov_order());
				aEmp.setMov_play_time(aEmp.getMov_play_time());
				cdDao.insert2(aEmp, con);
			}
			
			
			

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("list.size()-B="+list.size());
//			System.out.println("新增部門編號" + next_deptno + "時,共有員工" + list.size()
//					+ "人同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}
	
}
