package com.roomServiceOrder.model;

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

import com.roomOrderList.model.RoomOrderListDAO;
import com.roomOrderList.model.RoomOrderListVO;
import com.roomServiceOrderList.model.RoomServiceOrderListDAO;
import com.roomServiceOrderList.model.RoomServiceOrderListVO;

public class RoomServiceOrderJNDIDAO implements RoomServiceOrder_interface{
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
		"INSERT INTO ROOMSERVICE_ORDER (ROOM_ID,SERVICE_ORDER_STATUS,SERVICE_ORDER_DATE) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT SERVICE_ORDER_ID,ROOM_ID,SERVICE_ORDER_STATUS,SERVICE_ORDER_DATE FROM ROOMSERVICE_ORDER order by SERVICE_ORDER_ID";
	private static final String GET_ONE_STMT = 
		"SELECT SERVICE_ORDER_ID,ROOM_ID,SERVICE_ORDER_STATUS,SERVICE_ORDER_DATE FROM ROOMSERVICE_ORDER where SERVICE_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM ROOMSERVICE_ORDER where SERVICE_ORDER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ROOMSERVICE_ORDER set ROOM_ID=?, SERVICE_ORDER_STATUS= ?, SERVICE_ORDER_DATE=? where SERVICE_ORDER_ID = ?";
	private static final String CHECKOUT = "UPDATE ROOMSERVICE_ORDER set SERVICE_ORDER_STATUS= ? where SERVICE_ORDER_ID = ?";
	private static final String GET_ROOM_STMT = 
			"SELECT SERVICE_ORDER_ID,ROOM_ID,SERVICE_ORDER_STATUS,SERVICE_ORDER_DATE FROM ROOMSERVICE_ORDER where ROOM_ID = ?";

	@Override
	public void insert(RoomServiceOrderVO roomServiceOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, roomServiceOrderVO.getRoom_id());
			pstmt.setBoolean(2, roomServiceOrderVO.getService_order_status());
			pstmt.setDate(3, roomServiceOrderVO.getService_order_date());

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
	public void update(RoomServiceOrderVO ServiceOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ServiceOrderVO.getRoom_id());
			pstmt.setBoolean(2, ServiceOrderVO.getService_order_status());
			pstmt.setDate(3, ServiceOrderVO.getService_order_date());
			pstmt.setInt(4, ServiceOrderVO.getService_order_id());

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
	public RoomServiceOrderVO findByPrimaryKey(Integer service_order_id) {

		RoomServiceOrderVO RoomServiceOrderVO = null;
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
				RoomServiceOrderVO = new RoomServiceOrderVO();
				RoomServiceOrderVO.setService_order_id(rs.getInt("service_order_id"));
				RoomServiceOrderVO.setRoom_id(rs.getInt("room_id"));
				RoomServiceOrderVO.setService_order_status(rs.getBoolean("service_order_status"));
				RoomServiceOrderVO.setService_order_date(rs.getDate("service_order_date"));
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
		return RoomServiceOrderVO;
	}

	@Override
	public List<RoomServiceOrderVO> getAll() {
		List<RoomServiceOrderVO> list = new ArrayList<RoomServiceOrderVO>();
		RoomServiceOrderVO RoomServiceOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// RoomServiceOrderVO 也稱為 Domain objects
				RoomServiceOrderVO = new RoomServiceOrderVO();
				RoomServiceOrderVO.setService_order_id(rs.getInt("service_order_id"));
				RoomServiceOrderVO.setRoom_id(rs.getInt("room_id"));
				RoomServiceOrderVO.setService_order_status(rs.getBoolean("service_order_status"));
				RoomServiceOrderVO.setService_order_date(rs.getDate("service_order_date"));
				list.add(RoomServiceOrderVO); // Store the row in the list
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
	
	public void insertWithOrderList(RoomServiceOrderVO rsoVO , List<RoomServiceOrderListVO> list ,RoomOrderListVO RoomOrderListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"SERVIE_ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, rsoVO.getRoom_id());
			pstmt.setBoolean(2, rsoVO.getService_order_status());
			pstmt.setDate(3, rsoVO.getService_order_date());
Statement stmt=	con.createStatement();
stmt.executeUpdate("set auto_increment_offset=1;");    //自增主鍵-初始值
stmt.executeUpdate("set auto_increment_increment=1;"); //自增主鍵-遞增
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_service_order_id = null;
			Integer room_id = rsoVO.getRoom_id();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_service_order_id = rs.getString(1);
				System.out.println("111");
				System.out.println("自增主鍵值= " + next_service_order_id +"(剛新增成功的服務訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			RoomServiceOrderListDAO dao = new RoomServiceOrderListDAO();
			System.out.println("list.size()-A="+list.size());
			for (RoomServiceOrderListVO aRSOL : list) {
				aRSOL.setService_order_id(new Integer(next_service_order_id));
				System.out.println("我有跑到這" + aRSOL.getService_order_id());
				dao.insert2(aRSOL,con);
			}
			RoomOrderListDAO RoomOrderListDAO = new RoomOrderListDAO();
			RoomOrderListVO.setService_order_id(new Integer(next_service_order_id));
			RoomOrderListVO.setRoom_id(room_id);
			RoomOrderListDAO.updateservice(RoomOrderListVO);

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_service_order_id + "時,共有員工" + list.size()
					+ "人同時被新增");
			
			// Handle any SQL errors
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
	
	 @Override
	        public RoomServiceOrderVO checkout(RoomServiceOrderVO roomServiceOrderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(CHECKOUT);

				pstmt.setBoolean(1, roomServiceOrderVO.getService_order_status());
				pstmt.setInt(2, roomServiceOrderVO.getService_order_id());

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
			return roomServiceOrderVO;

		}
	 @Override
		public List<RoomServiceOrderVO> getAllOrderByPK(Integer room_id){
			List<RoomServiceOrderVO> list = new ArrayList<RoomServiceOrderVO>();
			RoomServiceOrderVO RoomServiceOrderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ROOM_STMT);
				
				pstmt.setInt(1, room_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// RoomServiceOrderVO 也稱為 Domain objects
					RoomServiceOrderVO = new RoomServiceOrderVO();
					RoomServiceOrderVO.setService_order_id(rs.getInt("service_order_id"));
					RoomServiceOrderVO.setRoom_id(rs.getInt("room_id"));
					RoomServiceOrderVO.setService_order_status(rs.getBoolean("service_order_status"));
					RoomServiceOrderVO.setService_order_date(rs.getDate("service_order_date"));
					list.add(RoomServiceOrderVO); // Store the row in the list
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
