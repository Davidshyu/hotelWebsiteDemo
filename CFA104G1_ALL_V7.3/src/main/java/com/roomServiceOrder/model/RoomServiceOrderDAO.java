package com.roomServiceOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.roomOrderList.model.RoomOrderListVO;
import com.roomServiceOrderList.model.RoomServiceOrderListDAO;
import com.roomServiceOrderList.model.RoomServiceOrderListVO;

public class RoomServiceOrderDAO implements RoomServiceOrder_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1";
	String userid = "root";
	String passwd = "password";
	
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
	@Override
	public void insert(RoomServiceOrderVO roomServiceOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, roomServiceOrderVO.getRoom_id());
			pstmt.setBoolean(2, roomServiceOrderVO.getService_order_status());
			pstmt.setDate(3, roomServiceOrderVO.getService_order_date());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ServiceOrderVO.getRoom_id());
			pstmt.setBoolean(2, ServiceOrderVO.getService_order_status());
			pstmt.setDate(3, ServiceOrderVO.getService_order_date());
			pstmt.setInt(4, ServiceOrderVO.getService_order_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, service_order_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, service_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// RoomServiceOrderVO 嚙稽嚙誶穿蕭 Domain objects
				RoomServiceOrderVO = new RoomServiceOrderVO();
				RoomServiceOrderVO.setService_order_id(rs.getInt("service_order_id"));
				RoomServiceOrderVO.setRoom_id(rs.getInt("room_id"));
				RoomServiceOrderVO.setService_order_status(rs.getBoolean("service_order_status"));
				RoomServiceOrderVO.setService_order_date(rs.getDate("service_order_date"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// RoomServiceOrderVO 嚙稽嚙誶穿蕭 Domain objects
				RoomServiceOrderVO = new RoomServiceOrderVO();
				RoomServiceOrderVO.setService_order_id(rs.getInt("service_order_id"));
				RoomServiceOrderVO.setRoom_id(rs.getInt("room_id"));
				RoomServiceOrderVO.setService_order_status(rs.getBoolean("service_order_status"));
				RoomServiceOrderVO.setService_order_date(rs.getDate("service_order_date"));
				list.add(RoomServiceOrderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public void insertWithOrderList(RoomServiceOrderVO rsoVO , List<RoomServiceOrderListVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1嚙踝蕭嚙稽嚙緩嚙踝蕭 pstm.executeUpdate()嚙踝蕭嚙箴
    		con.setAutoCommit(false);
			
    		// 嚙踝蕭嚙編嚙磕嚙踝蕭嚙踝蕭
			String cols[] = {"SERVIE_ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, rsoVO.getRoom_id());
			pstmt.setBoolean(2, rsoVO.getService_order_status());
			pstmt.setDate(3, rsoVO.getService_order_date());
Statement stmt=	con.createStatement();
stmt.executeUpdate("set auto_increment_offset=1;");    //嚙諛增嚙瘩嚙踝蕭-嚙踝蕭l嚙踝蕭
stmt.executeUpdate("set auto_increment_increment=1;"); //嚙諛增嚙瘩嚙踝蕭-嚙踝蕭嚙磕
			pstmt.executeUpdate();
			//嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙諛增嚙瘩嚙踝蕭嚙�
			String next_service_order_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_service_order_id = rs.getString(1);
			}
			rs.close();
			// 嚙璀嚙瞑嚙褕新嚙磕嚙踝蕭嚙線
			RoomServiceOrderListDAO dao = new RoomServiceOrderListDAO();
			System.out.println("list.size()-A="+list.size());
			for (RoomServiceOrderListVO aRSOL : list) {
				aRSOL.setService_order_id(new Integer(next_service_order_id));
				System.out.println("嚙誹佗蕭嚙稽嚙踝蕭o" + aRSOL.getService_order_id());
				dao.insert2(aRSOL,con);
			}

			// 2嚙踝蕭嚙稽嚙緩嚙踝蕭 pstm.executeUpdate()嚙踝蕭嚙踝蕭
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("嚙編嚙磕嚙踝蕭嚙踝蕭嚙編嚙踝蕭" + next_service_order_id + "嚙踝蕭,嚙瑾嚙踝蕭嚙踝蕭嚙線" + list.size()
					+ "嚙瘡嚙瞑嚙褕被嚙編嚙磕");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3嚙踝蕭嚙稽嚙緩嚙踝蕭嚙箴xception嚙緻嚙談時歹蕭catch嚙誕塚蕭嚙踝蕭
					System.err.print("Transaction is being ");
					System.err.println("rolled back-嚙踝蕭-dept");
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

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(CHECKOUT);

		pstmt.setBoolean(1, roomServiceOrderVO.getService_order_status());
		pstmt.setInt(2, roomServiceOrderVO.getService_order_id());

		pstmt.executeUpdate();

		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
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
	return roomServiceOrderVO;

}

	public static void main(String[] args) {

		RoomServiceOrderDAO dao = new RoomServiceOrderDAO();

		//嚙編嚙磕
		RoomServiceOrderVO RoomServiceOrderVO1 = new RoomServiceOrderVO();
		RoomServiceOrderVO1.setService_order_id(2);
		RoomServiceOrderVO1.setRoom_id(2);
//		RoomServiceOrderVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
		RoomServiceOrderVO1.setService_order_status(false);
		RoomServiceOrderVO1.setService_order_date(java.sql.Date.valueOf("2023-01-19"));
		dao.insert(RoomServiceOrderVO1);

//		// 嚙論改蕭
		RoomServiceOrderVO RoomServiceOrderVO2 = new RoomServiceOrderVO();
		RoomServiceOrderVO2.setService_order_id(1);
		RoomServiceOrderVO2.setRoom_id(1);
//		RoomServiceOrderVO2.setHiredate(java.sql.Date.valueOf("2005-01-01"));
		RoomServiceOrderVO2.setService_order_status(false);
		RoomServiceOrderVO2.setService_order_date(java.sql.Date.valueOf("2022-01-23"));
		dao.update(RoomServiceOrderVO2);

		// 嚙磋嚙踝蕭
		dao.delete(1);

		// 嚙範嚙踝蕭
		RoomServiceOrderVO RoomServiceOrderVO3 = dao.findByPrimaryKey(1);
		System.out.print(RoomServiceOrderVO3.getService_order_id() + ",");
		System.out.print(RoomServiceOrderVO3.getRoom_id() + ",");
		System.out.print(RoomServiceOrderVO3.getService_order_status() + ",");
		System.out.print(RoomServiceOrderVO3.getService_order_date() + ",");
		System.out.println("");
		System.out.println("---------------------");

		// 嚙範嚙踝蕭
		List<RoomServiceOrderVO> list = dao.getAll();
		for (RoomServiceOrderVO RSOV : list) {
			System.out.print(RSOV.getService_order_id() + ",");
			System.out.print(RSOV.getRoom_id() + ",");
			System.out.print(RSOV.getService_order_status() + ",");
			System.out.print(RSOV.getService_order_date() + ",");
			System.out.println();
		}
	}

	@Override
	public List<RoomServiceOrderVO> getAllOrderByPK(Integer room_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithOrderList(RoomServiceOrderVO rsoVO, List<RoomServiceOrderListVO> list,
			RoomOrderListVO RoomOrderListVO) {
		// TODO Auto-generated method stub
		
	}
}
