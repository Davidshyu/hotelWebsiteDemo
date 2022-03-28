package com.roomServiceOrderList.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.roomServiceOrder.model.RoomServiceOrderVO;


public class RoomServiceOrderListJNDIDAO implements RoomServiceOrderList_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
			
	private static final String INSERT_STMT = 
		"INSERT INTO ROOMSERVICE_ORDER_LIST (SERVICE_ORDER_ID , MEAL_ID,MEAL_PRICE,MEAL_QUANTITY,MEAL_TOTAL) VALUES (?,?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT SERVICE_ORDER_ID,MEAL_ID,MEAL_PRICE,MEAL_QUANTITY,MEAL_TOTAL FROM ROOMSERVICE_ORDER_LIST order by SERVICE_ORDER_ID";
	private static final String GET_ONE_STMT = 
		"SELECT SERVICE_ORDER_ID,MEAL_ID,MEAL_PRICE,MEAL_QUANTITY,MEAL_TOTAL FROM ROOMSERVICE_ORDER_LIST where SERVICE_ORDER_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ROOMSERVICE_ORDER_LIST where SERVICE_ORDER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ROOMSERVICE_ORDER_LIST set MEAL_ID=?, MEAL_PRICE=? , MEAL_QUANTITY=?, MEAL_TOTAL=? where SERVICE_ORDER_ID = ?";

	@Override
	public void insert(RoomServiceOrderListVO roomServiceOrderListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, roomServiceOrderListVO.getMeal_id());
			pstmt.setInt(2, roomServiceOrderListVO.getMeal_price());
			pstmt.setInt(3, roomServiceOrderListVO.getMeal_quantity());
			pstmt.setInt(4, roomServiceOrderListVO.getMeal_total());

			pstmt.executeUpdate();

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
	public void update(RoomServiceOrderListVO roomServiceOrderListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roomServiceOrderListVO.getMeal_id());
			pstmt.setInt(2, roomServiceOrderListVO.getMeal_price());
			pstmt.setInt(3, roomServiceOrderListVO.getMeal_quantity());
			pstmt.setInt(4, roomServiceOrderListVO.getMeal_total());
			pstmt.setInt(5, roomServiceOrderListVO.getService_order_id());

			pstmt.executeUpdate();

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
	public void delete(Integer service_order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, service_order_id);

			pstmt.executeUpdate();

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
	public RoomServiceOrderListVO findByPrimaryKey(Integer service_order_id) {

		RoomServiceOrderListVO RoomServiceOrderListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, service_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// RoomServiceOrderListVO 也稱為 Domain objects
				RoomServiceOrderListVO = new RoomServiceOrderListVO();
				RoomServiceOrderListVO.setService_order_id(rs.getInt("service_order_id"));
				RoomServiceOrderListVO.setMeal_id(rs.getInt("meal_id"));
				RoomServiceOrderListVO.setMeal_price(rs.getInt("meal_price"));
				RoomServiceOrderListVO.setMeal_quantity(rs.getInt("meal_quantity"));
				RoomServiceOrderListVO.setMeal_total(rs.getInt("meal_total"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
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
		return RoomServiceOrderListVO;
	}

	@Override
	public List<RoomServiceOrderListVO> getAll() {
		List<RoomServiceOrderListVO> list = new ArrayList<RoomServiceOrderListVO>();
		RoomServiceOrderListVO RoomServiceOrderListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// RoomServiceOrderListVO 也稱為 Domain objects
				RoomServiceOrderListVO = new RoomServiceOrderListVO();
				RoomServiceOrderListVO.setService_order_id(rs.getInt("service_order_id"));
				RoomServiceOrderListVO.setMeal_id(rs.getInt("meal_id"));
				RoomServiceOrderListVO.setMeal_price(rs.getInt("meal_price"));
				RoomServiceOrderListVO.setMeal_quantity(rs.getInt("meal_quantity"));
				RoomServiceOrderListVO.setMeal_total(rs.getInt("meal_total"));
				list.add(RoomServiceOrderListVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
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
	
	public void insert2 (RoomServiceOrderListVO rsolVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);
     		pstmt.setInt(1, rsolVO.getService_order_id());
			pstmt.setInt(2, rsolVO.getMeal_id());
			pstmt.setInt(3, rsolVO.getMeal_price());
			pstmt.setInt(4, rsolVO.getMeal_quantity());
			pstmt.setInt(5, rsolVO.getMeal_total());

//Statement stmt=	con.createStatement();
//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
//stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
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
	
	 @Override
		public List<RoomServiceOrderListVO> getAllListByPK(Integer service_order_id){
			List<RoomServiceOrderListVO> list = new ArrayList<RoomServiceOrderListVO>();
			RoomServiceOrderListVO RoomServiceOrderListVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setInt(1, service_order_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// RoomServiceOrderVO 也稱為 Domain objects
					RoomServiceOrderListVO = new RoomServiceOrderListVO();
					RoomServiceOrderListVO.setService_order_id(rs.getInt("service_order_id"));
					RoomServiceOrderListVO.setMeal_id(rs.getInt("meal_id"));
					RoomServiceOrderListVO.setMeal_price(rs.getInt("meal_price"));
					RoomServiceOrderListVO.setMeal_quantity(rs.getInt("meal_quantity"));
					RoomServiceOrderListVO.setMeal_total(rs.getInt("meal_total"));
					list.add(RoomServiceOrderListVO); // Store the row in the list
				}

				// Handle any SQL errors
			} catch (SQLException se) {
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
