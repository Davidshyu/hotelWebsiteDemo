package com.meal.model;

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

public class MealDAO implements MealDAO_interface{
	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	
	private static final String INSERT_STMT = 
			"INSERT INTO meal (meal_id,meal_category_id,meal_description,meal_price,meal_name,meal_quantity,meal_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
	
				pstmt.setInt(1, mealVO.getMeal_id());
				pstmt.setInt(2, mealVO.getMeal_category_id());
				pstmt.setString(3, mealVO.getMeal_description());
				pstmt.setInt(4, mealVO.getMeal_price());
				pstmt.setString(5, mealVO.getMeal_name());
				pstmt.setInt(6, mealVO.getMeal_quantity());
				pstmt.setBoolean(7, mealVO.getMeal_status());
//				pstmt.setBytes(8, mealVO.getMeal_photo());
	
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
		public void update(MealVO mealVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
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
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
	
				pstmt.setInt(1, meal_id);
	
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
		public MealVO findByPrimaryKey(Integer meal_id) {
	
			MealVO MealVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	
			try {
	
				con = ds.getConnection();
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
	
				con = ds.getConnection();
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
		public List<MealVO> getMealCategory(Integer mail_id) {
			List<MealVO> list = new ArrayList<MealVO>();
			MealVO MealVO = null;
	
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	
			try {
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_CATEGORY_STMT);
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
