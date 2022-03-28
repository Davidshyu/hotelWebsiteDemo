package com.activitySession.model;

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

public class ActivitySessionJNDIDAO implements ActivitySessionDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_SESSION (ACTIVITY_ID,ACTIVITY_SESSION_NAME,ACTIVITY_STARTED,ACTIVITY_END,ACTIVITY_PRICE,ACTIVITY_STATE,STATUS_NOTE,ACTIVITY_MAX_PART,ACTIVITY_MIN_PART,ENTERED_TOTAL,ENTERED_STARTED,ENTERED_END,PRODUCT_STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";// ACTIVITY_SESSION_ID
	private static final String GET_ALL_STMT = "SELECT ACTIVITY_SESSION_ID,ACTIVITY_ID,ACTIVITY_SESSION_NAME,ACTIVITY_STARTED,ACTIVITY_END,ACTIVITY_PRICE,ACTIVITY_STATE,STATUS_NOTE,ACTIVITY_MAX_PART,ACTIVITY_MIN_PART,ENTERED_TOTAL,ENTERED_STARTED,ENTERED_END,PRODUCT_STATUS FROM ACTIVITY_SESSION order by ACTIVITY_SESSION_ID";
	private static final String GET_ONE_STMT = "SELECT ACTIVITY_SESSION_ID,ACTIVITY_ID,ACTIVITY_SESSION_NAME,ACTIVITY_STARTED,ACTIVITY_END,ACTIVITY_PRICE,ACTIVITY_STATE,STATUS_NOTE,ACTIVITY_MAX_PART,ACTIVITY_MIN_PART,ENTERED_TOTAL,ENTERED_STARTED,ENTERED_END,PRODUCT_STATUS FROM ACTIVITY_SESSION where ACTIVITY_SESSION_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITY_SESSION where ACTIVITY_SESSION_ID  = ?";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY_SESSION set ACTIVITY_ID=?,ACTIVITY_SESSION_NAME=?, ACTIVITY_STARTED=?, ACTIVITY_END=?, ACTIVITY_PRICE=?, ACTIVITY_STATE=?, STATUS_NOTE=?, ACTIVITY_MAX_PART=?, ACTIVITY_MIN_PART=?, ENTERED_TOTAL=?, ENTERED_STARTED=?, ENTERED_END=?, PRODUCT_STATUS=? where ACTIVITY_SESSION_ID = ?";

	@Override
	public void insert(ActivitySessionVO activitySessionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// Load (and therefore register) the JDBC Driver
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, activitySessionVO.getActivity_id());
			pstmt.setString(2, activitySessionVO.getActivity_session_name());
			pstmt.setDate(3, activitySessionVO.getActivity_started());
			pstmt.setDate(4, activitySessionVO.getActivity_end());
			pstmt.setInt(5, activitySessionVO.getActivity_price());
			pstmt.setInt(6, activitySessionVO.getActivity_state());
			pstmt.setString(7, activitySessionVO.getStatus_note());
			pstmt.setInt(8, activitySessionVO.getActivity_max_part());
			pstmt.setInt(9, activitySessionVO.getActivity_min_part());
			pstmt.setInt(10, activitySessionVO.getEntered_total());
			pstmt.setDate(11, activitySessionVO.getEntered_started());
			pstmt.setDate(12, activitySessionVO.getEntered_end());
			pstmt.setInt(13, activitySessionVO.getProduct_status());

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
	public void update(ActivitySessionVO activitySessionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, activitySessionVO.getActivity_id());
			pstmt.setString(2, activitySessionVO.getActivity_session_name());
			pstmt.setDate(3, activitySessionVO.getActivity_started());
			pstmt.setDate(4, activitySessionVO.getActivity_end());
			pstmt.setInt(5, activitySessionVO.getActivity_price());
			pstmt.setInt(6, activitySessionVO.getActivity_state());
			pstmt.setString(7, activitySessionVO.getStatus_note());
			pstmt.setInt(8, activitySessionVO.getActivity_max_part());
			pstmt.setInt(9, activitySessionVO.getActivity_min_part());
			pstmt.setInt(10, activitySessionVO.getEntered_total());
			pstmt.setDate(11, activitySessionVO.getEntered_started());
			pstmt.setDate(12, activitySessionVO.getEntered_end());
			pstmt.setInt(13, activitySessionVO.getProduct_status());
			pstmt.setInt(14, activitySessionVO.getActivity_session_id());

			pstmt.executeUpdate();

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
	public void delete(Integer activity_session_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, activity_session_id);

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
	public ActivitySessionVO findByPrimaryKey(Integer activity_session_id) {
		ActivitySessionVO activitySessionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activity_session_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activitySessionVO = new ActivitySessionVO();
				activitySessionVO.setActivity_session_id(rs.getInt("ACTIVITY_SESSION_ID"));
				activitySessionVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activitySessionVO.setActivity_session_name(rs.getString("ACTIVITY_SESSION_NAME"));
				activitySessionVO.setActivity_started(rs.getDate("ACTIVITY_STARTED"));
				activitySessionVO.setActivity_end(rs.getDate("ACTIVITY_END"));
				activitySessionVO.setActivity_price(rs.getInt("ACTIVITY_PRICE"));
				activitySessionVO.setActivity_state(rs.getInt("ACTIVITY_STATE"));
				activitySessionVO.setStatus_note(rs.getString("STATUS_NOTE"));
				activitySessionVO.setActivity_max_part(rs.getInt("ACTIVITY_MAX_PART"));
				activitySessionVO.setActivity_min_part(rs.getInt("ACTIVITY_MIN_PART"));
				activitySessionVO.setEntered_total(rs.getInt("ENTERED_TOTAL"));
				activitySessionVO.setEntered_started(rs.getDate("ENTERED_STARTED"));
				activitySessionVO.setEntered_end(rs.getDate("ENTERED_END"));
				activitySessionVO.setProduct_status(rs.getInt("PRODUCT_STATUS"));

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
		return activitySessionVO;
	}

	@Override
	public List<ActivitySessionVO> getAll() {
		List<ActivitySessionVO> activity_session_list = new ArrayList<ActivitySessionVO>();
		ActivitySessionVO activitySessionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activitySessionVO = new ActivitySessionVO();
				activitySessionVO.setActivity_session_id(rs.getInt("ACTIVITY_SESSION_ID"));
				activitySessionVO.setActivity_id(rs.getInt("ACTIVITY_ID"));
				activitySessionVO.setActivity_session_name(rs.getString("ACTIVITY_SESSION_NAME"));
				activitySessionVO.setActivity_started(rs.getDate("ACTIVITY_STARTED"));
				activitySessionVO.setActivity_end(rs.getDate("ACTIVITY_END"));
				activitySessionVO.setActivity_price(rs.getInt("ACTIVITY_PRICE"));
				activitySessionVO.setActivity_state(rs.getInt("ACTIVITY_STATE"));
				activitySessionVO.setStatus_note(rs.getString("STATUS_NOTE"));
				activitySessionVO.setActivity_max_part(rs.getInt("ACTIVITY_MAX_PART"));
				activitySessionVO.setActivity_min_part(rs.getInt("ACTIVITY_MIN_PART"));
				activitySessionVO.setEntered_total(rs.getInt("ENTERED_TOTAL"));
				activitySessionVO.setEntered_started(rs.getDate("ENTERED_STARTED"));
				activitySessionVO.setEntered_end(rs.getDate("ENTERED_END"));
				activitySessionVO.setProduct_status(rs.getInt("PRODUCT_STATUS"));
				activity_session_list.add(activitySessionVO);

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
		return activity_session_list;
	}

}
