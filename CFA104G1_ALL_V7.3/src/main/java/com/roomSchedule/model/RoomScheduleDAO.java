package com.roomSchedule.model;

import java.sql.Connection;

import java.sql.Date;
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
import com.roomType.*;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;




public class RoomScheduleDAO implements RoomScheduleDAO_interface {
	
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
	"INSERT INTO ROOM_SCHEDULE (room_type_id,room_schedule_date,room_amount,room_rsv_booked) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT room_schedule_id,room_type_id,room_schedule_date,room_amount,room_rsv_booked FROM ROOM_SCHEDULE order by room_schedule_id";
	private static final String GET_ONE_STMT = 
	"SELECT room_schedule_id,room_type_id,room_schedule_date,room_amount,room_rsv_booked FROM ROOM_SCHEDULE where room_schedule_id = ?";
	private static final String DELETE = 
	"DELETE FROM ROOM_SCHEDULE where room_schedule_id = ?";
	private static final String UPDATE = 
	"UPDATE ROOM_SCHEDULE set room_type_id=?, room_schedule_date=?, room_amount=?, room_rsv_booked=? where room_schedule_id=?";
	private static final String GET_ID_BY_DATEandTYPE =
	"select * from ROOM_SCHEDULE where room_type_id= ? and room_schedule_date ="+ "?" ;
	private static final String GET_RSID_Count_FOR_SEARCH =
	"WITH RECURSIVE dates (v_date) AS (SELECT" + 
	"?" + 
	"UNION ALL SELECT v_date + INTERVAL 1 DAY FROM dates WHERE v_date + INTERVAL 1 DAY < ADDDATE("+"?"+", INTERVAL ? DAY)) "+
	"SELECT z.ROOM_TYPE_ID, count(*) "+
	"FROM dates d JOIN ROOM_TYPE z LEFT JOIN ROOM_SCHEDULE r on (d.v_date = r.ROOM_SCHEDULE_DATE AND z.ROOM_TYPE_ID = r.ROOM_TYPE_ID) "+
	"where ((ROOM_TYPE_AMOUNT - ROOM_RSV_BOOKED ) >= ? or (ROOM_RSV_BOOKED is null and ROOM_TYPE_AMOUNT > ? )) and ROOM_TOTAL_PERSON >= ? " +
	"group by z.ROOM_TYPE_ID having count(*) = ?";
			

	@Override
	public void insert(RoomScheduleVO room_scheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, room_scheduleVO.getRoom_type_id());
			pstmt.setDate(2, room_scheduleVO.getRoom_schedule_date());
			pstmt.setInt(3, room_scheduleVO.getRoom_amount());
			pstmt.setInt(4, room_scheduleVO.getRoom_rsv_booked());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void update(RoomScheduleVO room_scheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, room_scheduleVO.getRoom_type_id());
			
			pstmt.setDate(2, room_scheduleVO.getRoom_schedule_date());
		
			pstmt.setInt(3, room_scheduleVO.getRoom_amount());
		
			pstmt.setInt(4, room_scheduleVO.getRoom_rsv_booked());
	
			pstmt.setInt(5, room_scheduleVO.getRoom_schedule_id());
			pstmt.executeUpdate();
		

			// Handle any driver errors
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
	public void delete(Integer room_schedule_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_schedule_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public RoomScheduleVO findByPrimaryKey(Integer room_schedule_id) {
		RoomScheduleVO room_scheduleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_schedule_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_scheduleVO = new RoomScheduleVO();
				room_scheduleVO.setRoom_schedule_id(rs.getInt("room_schedule_id"));
				room_scheduleVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_scheduleVO.setRoom_schedule_date(rs.getDate("room_schedule_date"));
				room_scheduleVO.setRoom_amount(rs.getInt("room_amount"));
				room_scheduleVO.setRoom_rsv_booked(rs.getInt("room_rsv_booked"));
	
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
		return room_scheduleVO;
	}

	@Override
	public List<RoomScheduleVO> getAll() {
		List<RoomScheduleVO> list = new ArrayList<RoomScheduleVO>();
		RoomScheduleVO room_scheduleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_scheduleVO = new RoomScheduleVO();
				room_scheduleVO.setRoom_schedule_id(rs.getInt("room_schedule_id"));
				room_scheduleVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_scheduleVO.setRoom_schedule_date(rs.getDate("room_schedule_date"));
				room_scheduleVO.setRoom_amount(rs.getInt("room_amount"));
				room_scheduleVO.setRoom_rsv_booked(rs.getInt("room_rsv_booked"));
				list.add(room_scheduleVO); // Store the row in the list
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
	public RoomScheduleVO findByRoomTypeAndDATE(Integer room_type_id, Date room_schedule_date) {
		RoomScheduleVO room_scheduleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ID_BY_DATEandTYPE);

			pstmt.setInt(1, room_type_id);
			pstmt.setDate(2, java.sql.Date.valueOf(room_schedule_date.toString()));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_scheduleVO = new RoomScheduleVO();
				room_scheduleVO.setRoom_schedule_id(rs.getInt("room_schedule_id"));
				room_scheduleVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_scheduleVO.setRoom_schedule_date(rs.getDate("room_schedule_date"));
				room_scheduleVO.setRoom_amount(rs.getInt("room_amount"));
				room_scheduleVO.setRoom_rsv_booked(rs.getInt("room_rsv_booked"));
	
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
		return room_scheduleVO;
	}
	
	public List<RoomTypeVO> findEmptyRoomByDate(int totalrooms, int totaldays, Date arrival_date,int num_of_people ) {
		List<RoomScheduleVO> list = new ArrayList<RoomScheduleVO>();
		List<RoomTypeVO> listType  = new ArrayList<RoomTypeVO>();
		RoomScheduleVO room_scheduleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RSID_Count_FOR_SEARCH);
			pstmt.setDate(1, java.sql.Date.valueOf(arrival_date.toString()));
			pstmt.setDate(2, java.sql.Date.valueOf(arrival_date.toString()));
			pstmt.setInt(3, totaldays);
			pstmt.setInt(4, totalrooms);
			pstmt.setInt(5, totalrooms);
			pstmt.setInt(6, num_of_people);
			pstmt.setInt(7, totaldays);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_scheduleVO = new RoomScheduleVO();
				room_scheduleVO.setRoom_type_id(rs.getInt("room_type_id"));
				list.add(room_scheduleVO); // Store the row in the list
				RoomTypeService roomTypeSvc =new RoomTypeService();
				RoomTypeVO roomTypeVO = new RoomTypeVO();
				roomTypeVO = roomTypeSvc.getOneRoomType(rs.getInt("room_type_id"));
				listType.add(roomTypeVO);
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
		return listType;
	}
	
	
	

}
