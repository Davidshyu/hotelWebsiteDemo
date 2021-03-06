package com.mem.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemJDBCDAO implements MemDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12345678";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MEM (MEM_EMAIL,MEM_PASSWORD,MEM_NAME,MEM_PHONE,MEM_ADDRESS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEM_ID, MEM_EMAIL, MEM_PASSWORD, MEM_NAME, MEM_PHONE, MEM_ADDRESS, MEM_STATUS FROM MEM ORDER BY MEM_ID";
	private static final String GET_ONE_STMT = 
			"SELECT MEM_ID, MEM_EMAIL, MEM_PASSWORD, MEM_NAME, MEM_PHONE, MEM_ADDRESS, MEM_STATUS FROM MEM WHERE MEM_ID = ?";
	private static final String GET_ONE_STMT_EMAIL = 
			"SELECT MEM_ID, MEM_EMAIL, MEM_PASSWORD, MEM_NAME, MEM_PHONE, MEM_ADDRESS, MEM_STATUS FROM MEM WHERE MEM_EMAIL = ?";
	private static final String GET_ONE_STMT_EMAIL_PASSWORD = 
			"SELECT MEM_ID, MEM_EMAIL, MEM_PASSWORD, MEM_NAME, MEM_PHONE, MEM_ADDRESS, MEM_STATUS FROM MEM WHERE MEM_EMAIL = binary(?) AND MEM_PASSWORD = binary(?)";
	private static final String GET_ONE_STMT_ID_PASSWORD = 
			"SELECT MEM_ID, MEM_EMAIL, MEM_PASSWORD, MEM_NAME, MEM_PHONE, MEM_ADDRESS FROM MEM WHERE MEM_ID = ? AND MEM_PASSWORD = ?";
	private static final String UPDATE = 
			"UPDATE MEM SET MEM_NAME=?, MEM_PHONE=?, MEM_ADDRESS=? WHERE MEM_ID = ?";
	private static final String UPDATEALL = 
			"UPDATE MEM SET MEM_NAME=?, MEM_PHONE=?, MEM_ADDRESS=?, MEM_STATUS=? WHERE MEM_ID = ?";
	private static final String UPDATE_PIC = 
			"UPDATE MEM SET MEM_PIC=? WHERE MEM_EMAIL = ?";
	private static final String UPDATE_STUTAS = 
			"UPDATE MEM SET MEM_STATUS=? WHERE MEM_EMAIL = ?";
	private static final String UPDATE_PWD = 
			"UPDATE MEM SET MEM_PASSWORD=? WHERE MEM_ID = ?";
	private static final String RESET_PWD = 
			"UPDATE MEM SET MEM_PASSWORD=? WHERE MEM_EMAIL = ?";
	
	@Override
	public void insert(MemVO memVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);			
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
//			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_email());
			pstmt.setString(2, memVO.getMem_password());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_phone());
			pstmt.setString(5, memVO.getMem_address());

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
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void update(MemVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE);

//			pstmt.setString(1, memVO.getMem_password());
			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_phone());
			pstmt.setString(3, memVO.getMem_address());
			pstmt.setInt(4, memVO.getMem_id());

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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void updateAll(MemVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATEALL);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_phone());
			pstmt.setString(3, memVO.getMem_address());
			pstmt.setByte(4, memVO.getMem_status());
			pstmt.setInt(5, memVO.getMem_id());

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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updatePic(MemVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_PIC);
			pstmt.setBytes(1, memVO.getMem_pic());
			pstmt.setString(2, memVO.getMem_email());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}
	
	@Override
	public void updatePWD(MemVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_PWD);
			pstmt.setString(1, memVO.getMem_password());
			pstmt.setInt(2, memVO.getMem_id());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}

	@Override
	public void resetPWD(MemVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(RESET_PWD);
			pstmt.setString(1, memVO.getMem_password());
			pstmt.setString(2, memVO.getMem_email());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		
	}

	@Override
	public void updateStatus(MemVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STUTAS);
			pstmt.setByte(1, memVO.getMem_status());
//			System.out.println(memVO.getMem_status());
			pstmt.setString(2, memVO.getMem_email());
//			System.out.println(memVO.getMem_id());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}
	
	@Override
	public MemVO findByPrimaryKey(Integer mem_id) {
		MemVO memVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ????????? Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_status(rs.getByte("mem_status"));
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}
	
	@Override
	public MemVO selectByEmail(String mem_email) {
		MemVO memVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT_EMAIL);

			pstmt.setString(1, mem_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ????????? Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_status(rs.getByte("mem_status"));
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	@Override
	public MemVO selectForLogin(String mem_email, String mem_password) {
		MemVO memVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT_EMAIL_PASSWORD);

			pstmt.setString(1, mem_email);
			pstmt.setString(2, mem_password);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ????????? Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_status(rs.getByte("mem_status"));
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	@Override
	public MemVO selectForPwd(Integer mem_id, String mem_password) {
		MemVO memVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT_ID_PASSWORD);

			pstmt.setInt(1, mem_id);
			pstmt.setString(2, mem_password);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ????????? Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_address(rs.getString("mem_address"));
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ????????? Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_status(rs.getByte("mem_status"));
				list.add(memVO); // Store the row in the list
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {

		MemJDBCDAO dao = new MemJDBCDAO();

		// ??????
//		MemVO memVO1 = new MemVO();
//		memVO1.setMem_email("KayJBowden@armyspy.com");
//		memVO1.setMem_password("Kee9ooc1qu");
//		memVO1.setMem_name("Kay J. Bowden");
//		memVO1.setMem_phone("0947658235");
//		memVO1.setMem_address("??????????????????????????????10???");
//		dao.insert(memVO1);
		
		// ????????????
//		MemVO memVO2 = new MemVO();
//		memVO2.setMem_id(3);
//		memVO2.setMem_password("qwerasdfzxcv");
//		memVO2.setMem_name("Linglan");
//		memVO2.setMem_phone("0987657829");
//		memVO2.setMem_address("??????????????????????????????31???");
//		dao.update(memVO2);
		
		// ????????????
//		MemVO memVO3 = new MemVO();
//		byte[] pic;
//		try {
//			pic = getPictureByteArray("C:/Users/Tibame_T14/Pictures/MEM/gura.jpg");
//			memVO1.setMem_id(1);
//			memVO1.setMem_pic(pic);
//			dao.updatePic(memVO1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// ????????????
//		MemVO memVO4 = new MemVO();
//		memVO4.setMem_id(3);
//		memVO4.setMem_password("rrrrrrr");
//		dao.updatePWD(memVO4);
		
//		// ????????????
//		MemVO memVO5 = new MemVO();
//		memVO5.setMem_id(5);
//		memVO5.setMem_status((byte) 2);
//		dao.updateStatus(memVO5);
		
		// ????????????
//		MemVO memVO6 = dao.findByPrimaryKey(1);
//		System.out.print(memVO6.getMem_id() + ",");
//		System.out.print(memVO6.getMem_email() + ",");
//		System.out.print(memVO6.getMem_password()+ ",");
//		System.out.print(memVO6.getMem_name() + ",");
//		System.out.print(memVO6.getMem_phone() + ",");
//		System.out.print(memVO6.getMem_address() + ",");
//		System.out.println(memVO6.getMem_status());
//		System.out.println("---------------------");
		
//        // email??????
		MemVO memVO7 = dao.selectByEmail("admin@tibame.com.tw");
		System.out.print(memVO7.getMem_id() + ",");
		System.out.print(memVO7.getMem_email() + ",");
		System.out.print(memVO7.getMem_password()+ ",");
		System.out.print(memVO7.getMem_name() + ",");
		System.out.print(memVO7.getMem_phone() + ",");
		System.out.print(memVO7.getMem_address() + ",");
		System.out.println(memVO7.getMem_status());
		System.out.println("---------------------");
		
		// ??????????????????
//		MemVO memVO8 = dao.selectForLogin("test5@tibame.com.tw", "1234qwer");
//		System.out.print(memVO8.getMem_id() + ",");
//		System.out.print(memVO8.getMem_email() + ",");
//		System.out.print(memVO8.getMem_password()+ ",");
//		System.out.print(memVO8.getMem_name() + ",");
//		System.out.print(memVO8.getMem_phone() + ",");
//		System.out.println(memVO8.getMem_address());
//		System.out.println("---------------------");
		
		// ID????????????
//		MemVO memVO9 = dao.selectForPwd(4, "12345678");
//		System.out.print(memVO9.getMem_id() + ",");
//		System.out.print(memVO9.getMem_email() + ",");
//		System.out.print(memVO9.getMem_password()+ ",");
//		System.out.print(memVO9.getMem_name() + ",");
//		System.out.print(memVO9.getMem_phone() + ",");
//		System.out.println(memVO9.getMem_address());
//		System.out.println("---------------------");
		
		// ????????????ALL
//		MemVO memVO10 = new MemVO();
//		memVO10.setMem_id(3);
//		memVO10.setMem_password("qwerasdfzxcv");
//		memVO10.setMem_name("Linglan");
//		memVO10.setMem_phone("0987657829");
//		memVO10.setMem_address("??????????????????????????????31???");
//		memVO10.setMem_status((byte)2);
//		dao.updateAll(memVO10);
		
		// ????????????
//		MemVO memVO11 = new MemVO();
//		memVO11.setMem_email("fafasfafas@armyspy.com");
//		memVO11.setMem_password("aaaaaaaa");
//		dao.resetPWD(memVO11);
		
//		List<MemVO> list = dao.getAll();
//		for (MemVO aMem : list) {
//			System.out.print(aMem.getMem_id() + ",");
//			System.out.print(aMem.getMem_email() + ",");
//			System.out.print(aMem.getMem_password()+ ",");
//			System.out.print(aMem.getMem_name() + ",");
//			System.out.print(aMem.getMem_phone() + ",");
//			System.out.print(aMem.getMem_address() + ",");
//		    System.out.println(aMem.getMem_status());
//			System.out.println();
//		}

	}
}
