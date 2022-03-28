package com.meal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.meal.model.MealVO;
//import com.meal.model.MealDAO_interface;

public class MealJDBCDAO implements MealDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cfa104g1";
	String userid = "root";
	String passwd = "12345678";

	private static final String INSERT_STMT = 
		"INSERT INTO meal (meal_id,meal_category_id,meal_description,meal_price,meal_name,meal_quantity,meal_status,meal_photo) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT meal_id,meal_category_id,meal_description,meal_price,meal_name,meal_quantity,meal_status,meal_photo FROM meal order by meal_id";
	private static final String GET_ONE_STMT = 
		"SELECT meal_id,meal_category_id,meal_description,meal_price,meal_name,meal_quantity,meal_status,meal_photo FROM meal where meal_id = ?";
	private static final String DELETE = 
		"DELETE FROM meal where meal_id = ?";
	private static final String UPDATE = 
		"UPDATE meal set meal_category_id=?, meal_description=?, meal_price=?, meal_name=?, meal_quantity=?, meal_status=?, meal_photo=? where meal_id = ?";
	private static final String GET_CATEGORY_STMT = 
		"SELECT * FROM meal where MEAL_CATEGORY_id = ?";

	@Override
	public void insert(MealVO mealVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, mealVO.getMeal_id());
			pstmt.setInt(2, mealVO.getMeal_category_id());
			pstmt.setString(3, mealVO.getMeal_description());
			pstmt.setInt(4, mealVO.getMeal_price());
			pstmt.setString(5, mealVO.getMeal_name());
			pstmt.setInt(6, mealVO.getMeal_quantity());
			pstmt.setBoolean(7, mealVO.getMeal_status());
			pstmt.setBytes(8, mealVO.getMeal_photo());

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
	public void update(MealVO mealVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, mealVO.getMeal_category_id());
			pstmt.setString(2, mealVO.getMeal_description());
			pstmt.setInt(3, mealVO.getMeal_price());
			pstmt.setString(4, mealVO.getMeal_name());
			pstmt.setInt(5, mealVO.getMeal_quantity());
			pstmt.setBoolean(6, mealVO.getMeal_status());
			pstmt.setBytes(7, mealVO.getMeal_photo());
			pstmt.setInt(8, mealVO.getMeal_id());
			

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
	public void delete(Integer meal_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, meal_id);

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
	public MealVO findByPrimaryKey(Integer meal_id) {

		MealVO MealVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, meal_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MealVO �]�٬� Domain objects
				MealVO = new MealVO();
				MealVO.setMeal_id(rs.getInt("meal_id"));
				MealVO.setMeal_category_id(rs.getInt("meal_category_id"));
				MealVO.setMeal_description(rs.getString("meal_description"));
				MealVO.setMeal_price(rs.getInt("meal_price"));
				MealVO.setMeal_name(rs.getString("meal_name"));
				MealVO.setMeal_quantity(rs.getInt("meal_quantity"));
				MealVO.setMeal_status(rs.getBoolean("meal_status"));
				MealVO.setMeal_photo(rs.getBytes("meal_photo"));
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
		return MealVO;
	}

	@Override
	public List<MealVO> getAll() {
		List<MealVO> list = new ArrayList<MealVO>();
		MealVO MealVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MealVO �]�٬� Domain objects
				MealVO = new MealVO();
				MealVO.setMeal_id(rs.getInt("meal_id"));
				MealVO.setMeal_category_id(rs.getInt("meal_category_id"));
				MealVO.setMeal_description(rs.getString("meal_description"));
				MealVO.setMeal_price(rs.getInt("meal_price"));
				MealVO.setMeal_name(rs.getString("meal_name"));
				MealVO.setMeal_quantity(rs.getInt("meal_quantity"));
				MealVO.setMeal_status(rs.getBoolean("meal_status"));
				MealVO.setMeal_photo(rs.getBytes("meal_photo"));
				list.add(MealVO); // Store the row in the list
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
	
	@Override
	public List<MealVO> getMealCategory(Integer meal_id) {
		List<MealVO> list = new ArrayList<MealVO>();
		MealVO MealVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CATEGORY_STMT);

			pstmt.setInt(1 , meal_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MealVO �]�٬� Domain objects
				MealVO = new MealVO();
				MealVO.setMeal_id(rs.getInt("meal_id"));
				MealVO.setMeal_category_id(rs.getInt("meal_category_id"));
				MealVO.setMeal_description(rs.getString("meal_description"));
				MealVO.setMeal_price(rs.getInt("meal_price"));
				MealVO.setMeal_name(rs.getString("meal_name"));
				MealVO.setMeal_quantity(rs.getInt("meal_quantity"));
				MealVO.setMeal_status(rs.getBoolean("meal_status"));
				MealVO.setMeal_photo(rs.getBytes("meal_photo"));
				list.add(MealVO); // Store the row in the list
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
	
	

	public static void main(String[] args) {

		MealDAO_interface dao = new MealJDBCDAO();

		// �s�W
		MealVO MealVO1 = new MealVO();
		MealVO1.setMeal_id(3);
		MealVO1.setMeal_category_id(3);
		MealVO1.setMeal_description("ddd");
		MealVO1.setMeal_price(123);
		MealVO1.setMeal_name("b");		
		MealVO1.setMeal_quantity(2);
		MealVO1.setMeal_status(true);
		MealVO1.setMeal_photo(new byte[1024]);
		dao.insert(MealVO1);

		// �ק�
		MealVO MealVO2 = new MealVO();
		MealVO2.setMeal_id(1);
		MealVO2.setMeal_category_id(4);
		MealVO2.setMeal_description("���Y����Y");
		MealVO2.setMeal_price(999);
		MealVO2.setMeal_name("�~������");
		MealVO2.setMeal_quantity(1);
		MealVO2.setMeal_status(true);
		MealVO2.setMeal_photo(new byte[1024]);
		dao.update(MealVO2);

		// �R��
		dao.delete(1);

		// �d��
		MealVO MealVO3 = dao.findByPrimaryKey(1);
		System.out.print(MealVO3.getMeal_id() + ",");
		System.out.print(MealVO3.getMeal_description() + ",");
		System.out.print(MealVO3.getMeal_description() + ",");
		System.out.print(MealVO3.getMeal_price() + ",");
		System.out.print(MealVO3.getMeal_name() + ",");
		System.out.print(MealVO3.getMeal_quantity() + ",");
		System.out.println(MealVO3.getMeal_status());
		System.out.println("---------------------");

		// �d��
		List<MealVO> list = dao.getAll();
		for (MealVO aEmp : list) {
			System.out.print(aEmp.getMeal_id() + ",");
			System.out.print(aEmp.getMeal_description() + ",");
			System.out.print(aEmp.getMeal_description() + ",");
			System.out.print(aEmp.getMeal_price() + ",");
			System.out.print(aEmp.getMeal_name() + ",");
			System.out.print(aEmp.getMeal_quantity() + ",");
			System.out.print(aEmp.getMeal_status());
			System.out.println();
		}
	}
}
