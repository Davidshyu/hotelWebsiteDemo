package com.emplyee.model;

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

import com.mem.model.MemVO;


public class EmplyeeDAO implements EmplyeeDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO EMPLYEE (EMPNO,ROLES_ID,EMP_NAME,EMP_PASSWORD) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT EMPNO, ROLES_ID, EMP_NAME, EMP_PASSWORD, EMP_STATUS FROM EMPLYEE ORDER BY EMPNO";
	private static final String GET_ONE_STMT = "SELECT EMPNO, ROLES_ID, EMP_NAME, EMP_PASSWORD, EMP_STATUS FROM EMPLYEE WHERE EMPNO = ?";
	private static final String GET_ONE_STMT_EMPNO_PASSWORD = "SELECT EMPNO, ROLES_ID, EMP_NAME, EMP_PASSWORD, EMP_STATUS FROM EMPLYEE WHERE EMPNO = ? AND EMP_PASSWORD = ?";
	private static final String DELETE = "DELETE FROM EMPLYEE WHERE EMPNO = ?";
	private static final String UPDATE = "UPDATE EMPLYEE SET ROLES_ID=?, EMP_NAME=?, EMP_STATUS=? WHERE EMPNO = ?";
	private static final String UPDATE_STATUS = "UPDATE EMPLYEE SET EMP_STATUS=? WHERE EMPNO = ?";

	@Override
	public void insert(EmplyeeVO emplyeeVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, emplyeeVO.getEmpno());
			pstmt.setInt(2, emplyeeVO.getRoles_id());
			pstmt.setString(3, emplyeeVO.getEmp_name());
			pstmt.setString(4, emplyeeVO.getEmp_password());

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
	public void update(EmplyeeVO emplyeeVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setInt(1, emplyeeVO.getRoles_id());
			pstmt.setString(2, emplyeeVO.getEmp_name());
			pstmt.setBoolean(3, emplyeeVO.getEmp_status());
			pstmt.setInt(4, emplyeeVO.getEmpno());

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
	public void updateStatus(EmplyeeVO emplyeeVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_STATUS);

			pstmt.setBoolean(1, emplyeeVO.getEmp_status());
			pstmt.setInt(2, emplyeeVO.getEmpno());

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
	public void delete(Integer empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

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
	public EmplyeeVO findByPrimaryKey(Integer empno) {
		EmplyeeVO emplyeeVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				emplyeeVO = new EmplyeeVO();
				emplyeeVO.setEmpno(rs.getInt("empno"));
				emplyeeVO.setRoles_id(rs.getInt("roles_id"));
				emplyeeVO.setEmp_name(rs.getString("emp_name"));
				emplyeeVO.setEmp_password(rs.getString("emp_password"));
				emplyeeVO.setEmp_status(rs.getBoolean("emp_status"));
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return emplyeeVO;
	}

	@Override
	public EmplyeeVO selectForLogin(Integer empno, String emp_password) {
		EmplyeeVO emplyeeVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT_EMPNO_PASSWORD);

			pstmt.setInt(1, empno);
			pstmt.setString(2, emp_password);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				emplyeeVO = new EmplyeeVO();
				emplyeeVO.setEmpno(rs.getInt("empno"));
				emplyeeVO.setRoles_id(rs.getInt("roles_id"));
				emplyeeVO.setEmp_name(rs.getString("emp_name"));
				emplyeeVO.setEmp_password(rs.getString("emp_password"));
				emplyeeVO.setEmp_status(rs.getBoolean("emp_status"));
			}

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
		return emplyeeVO;
	}

	@Override
	public List<EmplyeeVO> getAll() {
		List<EmplyeeVO> list = new ArrayList<EmplyeeVO>();
		EmplyeeVO emplyeeVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emplyeeVO = new EmplyeeVO();
				emplyeeVO.setEmpno(rs.getInt("empno"));
				emplyeeVO.setRoles_id(rs.getInt("roles_id"));
				emplyeeVO.setEmp_name(rs.getString("emp_name"));
				emplyeeVO.setEmp_password(rs.getString("emp_password"));
				emplyeeVO.setEmp_status(rs.getBoolean("emp_status"));
				list.add(emplyeeVO); // Store the row in the list
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

		EmplyeeDAO dao = new EmplyeeDAO();

		// 新增
//		EmplyeeVO emplyeeVO1 = new EmplyeeVO();
//		emplyeeVO1.setEmpno(700);
//		emplyeeVO1.setRoles_id(1);
//		emplyeeVO1.setEmp_name("Jack");
//		emplyeeVO1.setEmp_password("AFDASFDASFAD");
//		emplyeeVO1.setEmp_status(true);
//		dao.insert(emplyeeVO1);

		// 修改
		EmplyeeVO emplyeeVO2 = new EmplyeeVO();
		emplyeeVO2.setEmpno(7010);
		emplyeeVO2.setRoles_id(1);
		emplyeeVO2.setEmp_name("Fred");
		emplyeeVO2.setEmp_password("1qaz2wsx");
		emplyeeVO2.setEmp_status(true);
		dao.update(emplyeeVO2);

		// 修改狀態
//		EmplyeeVO emplyeeVO3 = new EmplyeeVO();
//		emplyeeVO3.setEmpno(7001);
//		emplyeeVO3.setEmp_status(true);
//		dao.updateStatus(emplyeeVO3);
		
		// 刪除
//		EmplyeeVO emplyeeVO4 = new EmplyeeVO();
//		emplyeeVO4.setEmpno(7015);
//		dao.delete(7015);
		
		// 查詢單一
//		EmplyeeVO emplyeeVO5 = dao.findByPrimaryKey(7001);
//		System.out.print(emplyeeVO5.getEmpno() + ",");
//		System.out.print(emplyeeVO5.getRoles_id() + ",");
//		System.out.print(emplyeeVO5.getEmp_name()+ ",");
//		System.out.print(emplyeeVO5.getEmp_password() + ",");
//		System.out.println(emplyeeVO5.getEmp_status());
//		System.out.println("---------------------");
		
//		List<EmplyeeVO> list = dao.getAll();
//		for (EmplyeeVO aEmplyeeVO : list) {
//			System.out.print(aEmplyeeVO.getEmpno() + ",");
//			System.out.print(aEmplyeeVO.getRoles_id() + ",");
//			System.out.print(aEmplyeeVO.getEmp_name()+ ",");
//			System.out.print(aEmplyeeVO.getEmp_password() + ",");
//			System.out.println(aEmplyeeVO.getEmp_status());
//			System.out.println();
//		}
	}

}
