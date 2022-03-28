package com.activityCategory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityCategoryJDBCDAO implements ActivityCategoryDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12345678";

	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_CATEGORY (ACTIVITY_CATEGORY_NAME,ACTIVITY_CATEGORY_INFO) VALUES (?, ?)";// ACTIVITY_CATEGORY_ID
	private static final String GET_ALL_STMT = "SELECT ACTIVITY_CATEGORY_ID,ACTIVITY_CATEGORY_NAME,ACTIVITY_CATEGORY_INFO FROM ACTIVITY_CATEGORY order by ACTIVITY_CATEGORY_ID";
	private static final String GET_ONE_STMT = "SELECT ACTIVITY_CATEGORY_ID,ACTIVITY_CATEGORY_NAME,ACTIVITY_CATEGORY_INFO FROM ACTIVITY_CATEGORY where ACTIVITY_CATEGORY_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITY_CATEGORY where ACTIVITY_CATEGORY_ID  = ?";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY_CATEGORY set ACTIVITY_CATEGORY_NAME=?, ACTIVITY_CATEGORY_INFO=? where ACTIVITY_CATEGORY_ID = ?";

	@Override
	public void insert(ActivityCategoryVO activityCategoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// Load (and therefore register) the JDBC Driver
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, activityCategoryVO.getActivity_category_name());
			pstmt.setString(2, activityCategoryVO.getActivity_category_info());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(ActivityCategoryVO activityCategoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, activityCategoryVO.getActivity_category_name());
			pstmt.setString(2, activityCategoryVO.getActivity_category_info());
			pstmt.setInt(3, activityCategoryVO.getActivity_category_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer activity_category_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, activity_category_id);

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
	public ActivityCategoryVO findByPrimaryKey(Integer activity_category_id) {
		ActivityCategoryVO activity_categoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activity_category_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity_categoryVO = new ActivityCategoryVO();
				activity_categoryVO.setActivity_category_id(rs.getInt("ACTIVITY_CATEGORY_ID"));
				activity_categoryVO.setActivity_category_name(rs.getString("ACTIVITY_CATEGORY_NAME"));
				activity_categoryVO.setActivity_category_info(rs.getString("ACTIVITY_CATEGORY_INFO"));
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
		return activity_categoryVO;
	}

	@Override
	public List<ActivityCategoryVO> getAll() {
		List<ActivityCategoryVO> activity_category_list = new ArrayList<ActivityCategoryVO>();
		ActivityCategoryVO activityCategoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				activityCategoryVO = new ActivityCategoryVO();
				activityCategoryVO.setActivity_category_id(rs.getInt("ACTIVITY_CATEGORY_ID"));
				activityCategoryVO.setActivity_category_name(rs.getString("ACTIVITY_CATEGORY_NAME"));
				activityCategoryVO.setActivity_category_info(rs.getString("ACTIVITY_CATEGORY_INFO"));
				activity_category_list.add(activityCategoryVO);
			}
			// Handle any driver errors
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Couldn't load database driver. "
								+ e.getMessage());
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
		return activity_category_list;
	}

}
