package com.rolesAccessRight.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emplyee.model.EmplyeeVO;


public class RolesAccessRightJDBCDAO implements RolesAccessRightDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12345678";
	
	private static final String INSERT_STMT = "INSERT INTO ROLES_ACCESS_RIGHT (ROLES_ID,FUNCTION_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT ROLES_ID,FUNCTION_ID FROM ROLES_ACCESS_RIGHT ORDER BY ROLES_ID";
	private static final String GET_ONE_STMT = "SELECT ROLES_ID,FUNCTION_ID FROM ROLES_ACCESS_RIGHT WHERE ROLES_ID = ? AND FUNCTION_ID = ?";
	private static final String DELETE = "DELETE FROM ROLES_ACCESS_RIGHT WHERE ROLES_ID = ? AND FUNCTION_ID = ?";
	private static final String UPDATE = "UPDATE ROLES_ACCESS_RIGHT SET FUNCTION_ID=? WHERE ROLES_ID = ? AND FUNCTION_ID = ?";
	
	@Override
	public void insert(RolesAccessRightVO rolesAccessRightVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rolesAccessRightVO.getRoles_id());
			pstmt.setInt(2, rolesAccessRightVO.getFunction_id());
			
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
	public void update(RolesAccessRightVO rolesAccessRightVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setInt(1, rolesAccessRightVO.getFunction_id());
			pstmt.setInt(2, rolesAccessRightVO.getRoles_id());
			pstmt.setInt(3, rolesAccessRightVO.getFunction_id());

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
	public void delete(Integer roles_id, Integer fuction_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, roles_id);
			pstmt.setInt(2, fuction_id);

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
	public RolesAccessRightVO findByPrimaryKey(Integer roles_id, Integer fuction_id) {
		RolesAccessRightVO rolesAccessRightVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, roles_id);
			pstmt.setInt(2, fuction_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rolesAccessRightVO = new RolesAccessRightVO();
				rolesAccessRightVO.setRoles_id(rs.getInt("roles_id"));
				rolesAccessRightVO.setFunction_id(rs.getInt("function_id"));
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
		return rolesAccessRightVO;
	}
	
	@Override
	public List<RolesAccessRightVO> getAll() {
		List<RolesAccessRightVO> list = new ArrayList<RolesAccessRightVO>();
		RolesAccessRightVO rolesAccessRightVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rolesAccessRightVO = new RolesAccessRightVO();
				rolesAccessRightVO.setRoles_id(rs.getInt("roles_id"));
				rolesAccessRightVO.setFunction_id(rs.getInt("function_id"));
				list.add(rolesAccessRightVO); // Store the row in the list
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
	
	public static void main(String[] args) {

		RolesAccessRightJDBCDAO dao = new RolesAccessRightJDBCDAO();
	
		// 新增權限-管理員
		RolesAccessRightVO RolesAccessRightVO11 = new RolesAccessRightVO();
		for (int i = 1; i<18; i++) {
			RolesAccessRightVO11.setRoles_id(1);
			RolesAccessRightVO11.setFunction_id(i);
			dao.insert(RolesAccessRightVO11);
		}	
		
		System.out.println("管理員新增結束");
		
		// 新增權限-會員管理員
		RolesAccessRightVO RolesAccessRightVO12 = new RolesAccessRightVO();
		for (int i = 1; i<2; i++) {
			RolesAccessRightVO12.setRoles_id(2);
			RolesAccessRightVO12.setFunction_id(i);
			dao.insert(RolesAccessRightVO12);
		}	
		
		System.out.println("會員管理員新增結束");
		
		// 新增權限-網頁管理員
		RolesAccessRightVO RolesAccessRightVO13 = new RolesAccessRightVO();
		for (int i = 2; i<4; i++) {
			RolesAccessRightVO13.setRoles_id(3);
			RolesAccessRightVO13.setFunction_id(i);
			dao.insert(RolesAccessRightVO13);
		}	
		
		System.out.println("網頁管理員新增結束");
		
		// 新增權限-活動管理員
		RolesAccessRightVO RolesAccessRightVO14 = new RolesAccessRightVO();
		for (int i = 4; i<7; i++) {
			RolesAccessRightVO14.setRoles_id(4);
			RolesAccessRightVO14.setFunction_id(i);
			dao.insert(RolesAccessRightVO14);
		}	
		
		System.out.println("活動管理員新增結束");
		
		// 新增權限-商城管理員
		RolesAccessRightVO RolesAccessRightVO15 = new RolesAccessRightVO();
		for (int i = 7; i<9; i++) {
			RolesAccessRightVO15.setRoles_id(5);
			RolesAccessRightVO15.setFunction_id(i);
			dao.insert(RolesAccessRightVO15);
		}	
		
		System.out.println("商城管理員新增結束");
		
		// 新增權限-房務管理員
		RolesAccessRightVO RolesAccessRightVO16 = new RolesAccessRightVO();
		for (int i = 9; i<17; i++) {
			RolesAccessRightVO16.setRoles_id(6);
			RolesAccessRightVO16.setFunction_id(i);
			dao.insert(RolesAccessRightVO16);
		}	
		
		System.out.println("房務管理員新增結束");
		
		// 新增權限-員工管理員
		RolesAccessRightVO RolesAccessRightVO17 = new RolesAccessRightVO();
		for (int i = 17; i<18; i++) {
			RolesAccessRightVO17.setRoles_id(7);
			RolesAccessRightVO17.setFunction_id(i);
			dao.insert(RolesAccessRightVO17);
		}	
		
		System.out.println("員工管理員新增結束");
		


		// 修改*
//		RolesAccessRightVO RolesAccessRightVO2 = new RolesAccessRightVO();
//		RolesAccessRightVO2.setRoles_id(1);
//		RolesAccessRightVO2.setFunction_id(3);
//		dao.update(RolesAccessRightVO2);

		// 刪除
//		RolesAccessRightVO RolesAccessRightVO3 = new RolesAccessRightVO();
//		RolesAccessRightVO3.setRoles_id(2);
//		RolesAccessRightVO3.setFunction_id(3);
//	    dao.delete(2, 3);
		
		// 查詢單一
//		RolesAccessRightVO rolesAccessRightVO4 = dao.findByPrimaryKey(1, 3);
//		System.out.print(rolesAccessRightVO4.getRoles_id() + ",");
//		System.out.println(rolesAccessRightVO4.getFunction_id());
//		System.out.println("---------------------");
		
		// 查詢all
//		List<RolesAccessRightVO> list = dao.getAll();
//		for (RolesAccessRightVO aRolesAccessRightVO : list) {
//			System.out.print(aRolesAccessRightVO.getRoles_id() + ",");
//			System.out.println(aRolesAccessRightVO.getFunction_id());
//			System.out.println();
//		}
	}
}
