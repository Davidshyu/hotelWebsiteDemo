package com.activityPhoto.model;

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

public class ActivityPhotoJDBCDAO implements ActivityPhotoDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12345678";

	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_PHOTO (ACTIVITY_ID,ACTIVITY_PHOTO_FILE) VALUES (?, ?)";// ACTIVITY_PHOTO_ID
																															// pk�|�۰��ܬy�����ҥH���Ω�i�h
	private static final String GET_ALL_STMT = "SELECT ACTIVITY_PHOTO_ID,ACTIVITY_ID,ACTIVITY_PHOTO_FILE FROM ACTIVITY_PHOTO order by ACTIVITY_PHOTO_ID";
	private static final String GET_ONE_STMT = "SELECT ACTIVITY_PHOTO_ID,ACTIVITY_ID,ACTIVITY_PHOTO_FILE FROM ACTIVITY_PHOTO where ACTIVITY_PHOTO_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITY_PHOTO where ACTIVITY_PHOTO_ID  = ?";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY_PHOTO set ACTIVITY_ID=?, ACTIVITY_PHOTO_FILE=? where ACTIVITY_PHOTO_ID = ?";
	private static final String GET_FK_PIC = 
			"SELECT * FROM ACTIVITY_PHOTO where ACTIVITY_ID = ?";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(ActivityPhotoVO activityPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// Load (and therefore register) the JDBC Driver
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, activityPhotoVO.getActivity_id());
			pstmt.setBytes(2, activityPhotoVO.getActivity_photo_file());

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
	public void update(ActivityPhotoVO activityPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, activityPhotoVO.getActivity_id());
			pstmt.setBytes(2, activityPhotoVO.getActivity_photo_file());
			pstmt.setInt(3, activityPhotoVO.getActivity_photo_id());

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
	public void delete(Integer activity_photo_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, activity_photo_id);

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
	public ActivityPhotoVO findByPrimaryKey(Integer activity_photo_id) {
		ActivityPhotoVO activityPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activity_photo_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityPhotoVO = new ActivityPhotoVO();
				activityPhotoVO.setActivity_photo_id(rs.getInt("ACTIVITY_PHOTO_ID"));
				activityPhotoVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activityPhotoVO.setActivity_photo_file(rs.getBytes("ACTIVITY_PHOTO_FILE"));
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
		return activityPhotoVO;
	}

	@Override
	public List<ActivityPhotoVO> getAll() {
		List<ActivityPhotoVO> activity_photo_list = new ArrayList<ActivityPhotoVO>();
		ActivityPhotoVO activityPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityPhotoVO = new ActivityPhotoVO();
				activityPhotoVO.setActivity_photo_id(rs.getInt("ACTIVITY_PHOTO_ID"));
				activityPhotoVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activityPhotoVO.setActivity_photo_file(rs.getBytes("ACTIVITY_PHOTO_FILE"));
				activity_photo_list.add(activityPhotoVO);
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
		return activity_photo_list;
	}
	@Override
	public List<ActivityPhotoVO> findByFK(Integer activity_id) {
		List<ActivityPhotoVO> list = new ArrayList<ActivityPhotoVO>();
		ActivityPhotoVO activityPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FK_PIC);
			
			pstmt.setInt(1, activity_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				activityPhotoVO = new ActivityPhotoVO();
				activityPhotoVO.setActivity_photo_id(rs.getInt("activity_photo_id"));
				activityPhotoVO.setActivity_id(rs.getInt("activity_id"));
				activityPhotoVO.setActivity_photo_file(rs.getBytes("activity_photo_file"));
				list.add(activityPhotoVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

}
