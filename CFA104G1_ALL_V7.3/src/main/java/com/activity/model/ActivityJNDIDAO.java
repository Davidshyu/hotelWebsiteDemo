package com.activity.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityJNDIDAO implements ActivityDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY (ACTIVITY_CATEGORY_ID,ACTIVITY_NAME,ACTIVITY_INFO) VALUES (?, ?, ?)";// ACTIVITY_ID
	private static final String GET_ALL_STMT = "SELECT ACTIVITY_ID,ACTIVITY_CATEGORY_ID,ACTIVITY_NAME,ACTIVITY_INFO FROM ACTIVITY order by ACTIVITY_ID";
	private static final String GET_CATE1_STMT = "SELECT ACTIVITY_ID,ACTIVITY_CATEGORY_ID,ACTIVITY_NAME,ACTIVITY_INFO FROM ACTIVITY where ACTIVITY_CATEGORY_ID =1 order by ACTIVITY_ID";
	private static final String GET_CATE2_STMT = "SELECT ACTIVITY_ID,ACTIVITY_CATEGORY_ID,ACTIVITY_NAME,ACTIVITY_INFO FROM ACTIVITY where ACTIVITY_CATEGORY_ID =2 order by ACTIVITY_ID";
	private static final String GET_CATE3_STMT = "SELECT ACTIVITY_ID,ACTIVITY_CATEGORY_ID,ACTIVITY_NAME,ACTIVITY_INFO FROM ACTIVITY where ACTIVITY_CATEGORY_ID =3 order by ACTIVITY_ID";
	private static final String GET_ONE_STMT = "SELECT ACTIVITY_ID,ACTIVITY_CATEGORY_ID,ACTIVITY_NAME,ACTIVITY_INFO FROM ACTIVITY where ACTIVITY_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITY where ACTIVITY_ID  = ?";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY set ACTIVITY_CATEGORY_ID=?, ACTIVITY_NAME=?, ACTIVITY_INFO=? where ACTIVITY_ID = ?";

	@Override
	public int insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int phtoid = -1;

		try {
			// Load (and therefore register) the JDBC Driver
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, activityVO.getActivity_category_id());
			pstmt.setString(2, activityVO.getActivity_name());
			pstmt.setString(3, activityVO.getActivity_info());

			pstmt.executeUpdate();
			
			int pk = pstmt.executeUpdate();
			
			if(pk>0) {
				//找出PK
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				phtoid = rs.getInt(1);
				}

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
		return phtoid;
	}

	@Override
	public void update(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, activityVO.getActivity_category_id());
			pstmt.setString(2, activityVO.getActivity_name());
			pstmt.setString(3, activityVO.getActivity_info());
			pstmt.setInt(4, activityVO.getActivity_id());
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
	public void delete(Integer activity_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, activity_id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	public ActivityVO findByPrimaryKey(Integer activity_id) {
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, activity_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activityVO.setActivity_category_id(rs.getInt("ACTIVITY_CATEGORY_ID"));
				activityVO.setActivity_name(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivity_info(rs.getString("ACTIVITY_INFO"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return activityVO;
	}

	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> activity_list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activityVO.setActivity_category_id(rs.getInt("ACTIVITY_CATEGORY_ID"));
				activityVO.setActivity_name(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivity_info(rs.getString("ACTIVITY_INFO"));
				activity_list.add(activityVO);

			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return activity_list;
	}
	@Override
	public List<ActivityVO> getCate1Act() {
		List<ActivityVO> activity_cate1_list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CATE1_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activityVO.setActivity_category_id(rs.getInt("ACTIVITY_CATEGORY_ID"));
				activityVO.setActivity_name(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivity_info(rs.getString("ACTIVITY_INFO"));
				activity_cate1_list.add(activityVO);

			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return activity_cate1_list;
	}
	@Override
	public List<ActivityVO> getCate2Act() {
		List<ActivityVO> activity_cate2_list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CATE2_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activityVO.setActivity_category_id(rs.getInt("ACTIVITY_CATEGORY_ID"));
				activityVO.setActivity_name(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivity_info(rs.getString("ACTIVITY_INFO"));
				activity_cate2_list.add(activityVO);

			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return activity_cate2_list;
	}
	@Override
	public List<ActivityVO> getCate3Act() {
		List<ActivityVO> activity_cate3_list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CATE3_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activityVO.setActivity_category_id(rs.getInt("ACTIVITY_CATEGORY_ID"));
				activityVO.setActivity_name(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivity_info(rs.getString("ACTIVITY_INFO"));
				activity_cate3_list.add(activityVO);

			}
			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
		return activity_cate3_list;
	}
}
