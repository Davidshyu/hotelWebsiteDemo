package com.activityOrder.model;

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


public class ActivityOrderJNDIDAO implements ActivityOrderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_ORDER (MEM_ID,ACTIVITY_SESSION_ID,ORDER_TIME,ENTERED_NUMBER,ACTIVITY_STARTED,ACTIVITY_END,ORDER_AMOUNT,ORDER_STATE,REFUND_STATE,ORDER_MEMO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";// ACTIVITY_ORDER_ID
	private static final String GET_ALL_STMT = "SELECT ACTIVITY_ORDER_ID,MEM_ID,ACTIVITY_SESSION_ID,ORDER_TIME,ENTERED_NUMBER,ACTIVITY_STARTED,ACTIVITY_END,ORDER_AMOUNT,ORDER_STATE,REFUND_STATE,ORDER_MEMO FROM ACTIVITY_ORDER order by ACTIVITY_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT ACTIVITY_ORDER_ID,MEM_ID,ACTIVITY_SESSION_ID,ORDER_TIME,ENTERED_NUMBER,ACTIVITY_STARTED,ACTIVITY_END,ORDER_AMOUNT,ORDER_STATE,REFUND_STATE,ORDER_MEMO FROM ACTIVITY_ORDER where ACTIVITY_ORDER_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITY_ORDER where ACTIVITY_ORDER_ID  = ?";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY_ORDER set MEM_ID=?, ACTIVITY_SESSION_ID=?, ORDER_TIME=?, ENTERED_NUMBER=?, ACTIVITY_STARTED=?, ACTIVITY_END=?, ORDER_AMOUNT=?, ORDER_STATE=?, REFUND_STATE=?, ORDER_MEMO=? where ACTIVITY_ORDER_ID = ?";
	private static final String GET_ALL_STMT_By_MemID =	"select * from ACTIVITY_ORDER where mem_id = ?";
	@Override
	public void insert(ActivityOrderVO activityOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// Load (and therefore register) the JDBC Driver
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, activityOrderVO.getMem_id());
			pstmt.setInt(2, activityOrderVO.getActivity_session_id());
			pstmt.setDate(3, activityOrderVO.getOrder_time());
			pstmt.setInt(4, activityOrderVO.getEntered_number());
			pstmt.setDate(5, activityOrderVO.getActivity_started());
			pstmt.setDate(6, activityOrderVO.getActivity_end());
			pstmt.setInt(7, activityOrderVO.getOrder_amount());
			pstmt.setInt(8, activityOrderVO.getOrder_state());
			pstmt.setInt(9, activityOrderVO.getRefund_state());
			pstmt.setString(10, activityOrderVO.getOrder_memo());

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
	public void update(ActivityOrderVO activityOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, activityOrderVO.getMem_id());
			pstmt.setInt(2, activityOrderVO.getActivity_session_id());
			pstmt.setDate(3, activityOrderVO.getOrder_time());
			pstmt.setInt(4, activityOrderVO.getEntered_number());
			pstmt.setDate(5, activityOrderVO.getActivity_started());
			pstmt.setDate(6, activityOrderVO.getActivity_end());
			pstmt.setInt(7, activityOrderVO.getOrder_amount());
			pstmt.setInt(8, activityOrderVO.getOrder_state());
			pstmt.setInt(9, activityOrderVO.getRefund_state());
			pstmt.setString(10, activityOrderVO.getOrder_memo());
			pstmt.setInt(11, activityOrderVO.getActivity_order_id());	
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
	public void delete(Integer activity_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, activity_order_id);

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
	public ActivityOrderVO findByPrimaryKey(Integer activity_order_id) {
		ActivityOrderVO activityOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activity_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityOrderVO = new ActivityOrderVO();
				activityOrderVO.setActivity_order_id(rs.getInt("ACTIVITY_ORDER_ID"));
				activityOrderVO.setMem_id(rs.getInt("MEM_ID"));
				activityOrderVO.setActivity_session_id(rs.getInt("ACTIVITY_SESSION_ID"));
				activityOrderVO.setOrder_time(rs.getDate("ORDER_TIME"));
				activityOrderVO.setEntered_number(rs.getInt("ENTERED_NUMBER"));
				activityOrderVO.setActivity_started(rs.getDate("ACTIVITY_STARTED"));
				activityOrderVO.setActivity_end(rs.getDate("ACTIVITY_END"));
				activityOrderVO.setOrder_amount(rs.getInt("ORDER_AMOUNT"));
				activityOrderVO.setOrder_state(rs.getInt("ORDER_STATE"));
				activityOrderVO.setRefund_state(rs.getInt("REFUND_STATE"));
				activityOrderVO.setOrder_memo(rs.getString("ORDER_MEMO"));
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
		return activityOrderVO;
	}

	@Override
	public List<ActivityOrderVO> getAll() {
		List<ActivityOrderVO> activity_order_list = new ArrayList<ActivityOrderVO>();
		ActivityOrderVO activityOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityOrderVO = new ActivityOrderVO();
				activityOrderVO.setActivity_order_id(rs.getInt("ACTIVITY_ORDER_ID"));
				activityOrderVO.setMem_id(rs.getInt("MEM_ID"));
				activityOrderVO.setActivity_session_id(rs.getInt("ACTIVITY_SESSION_ID"));
				activityOrderVO.setOrder_time(rs.getDate("ORDER_TIME"));
				activityOrderVO.setEntered_number(rs.getInt("ENTERED_NUMBER"));
				activityOrderVO.setActivity_started(rs.getDate("ACTIVITY_STARTED"));
				activityOrderVO.setActivity_end(rs.getDate("ACTIVITY_END"));
				activityOrderVO.setOrder_amount(rs.getInt("ORDER_AMOUNT"));
				activityOrderVO.setOrder_state(rs.getInt("ORDER_STATE"));
				activityOrderVO.setRefund_state(rs.getInt("REFUND_STATE"));
				activityOrderVO.setOrder_memo(rs.getString("ORDER_MEMO"));
				activity_order_list.add(activityOrderVO);
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
		return activity_order_list;
	}
	public List<ActivityOrderVO> getAllByMemId(Integer mem_id) {
		List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
		ActivityOrderVO activityOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_By_MemID);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityOrderVO = new ActivityOrderVO();
				activityOrderVO.setActivity_order_id(rs.getInt("activity_order_id"));
				activityOrderVO.setMem_id(rs.getInt("mem_id"));
				activityOrderVO.setActivity_session_id(rs.getInt("activity_session_id"));
				activityOrderVO.setOrder_time(rs.getDate("order_time"));
				activityOrderVO.setEntered_number(rs.getInt("entered_number"));
				activityOrderVO.setActivity_started(rs.getDate("activity_started"));
				activityOrderVO.setActivity_end(rs.getDate("activity_end"));
				activityOrderVO.setOrder_amount(rs.getInt("order_amount"));
				activityOrderVO.setOrder_state(rs.getInt("order_state"));
				activityOrderVO.setRefund_state(rs.getInt("refund_state"));
				activityOrderVO.setOrder_memo(rs.getString("order_memo"));
				list.add(activityOrderVO); // Store the row in the list
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
