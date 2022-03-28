	package com.mealCategory.model;

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

	public class MealCategoryJNDIDAO implements MealCategoryDAO_interface{
		// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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
			"INSERT INTO  MEAL_CATEGORY(MEAL_CATEGORY_ID,MEAL_CATEGORY_NAME) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT MEAL_CATEGORY_ID,MEAL_CATEGORY_NAME FROM meal_category order by MEAL_CATEGORY_ID";
		private static final String GET_ONE_STMT = 
			"SELECT MEAL_CATEGORY_ID,MEAL_CATEGORY_NAME FROM meal_category where MEAL_CATEGORY_ID = ?";
		private static final String DELETE = 
			"DELETE FROM MEAL_CATEGORY where MEAL_CATEGORY_ID = ?";
		private static final String UPDATE = 
			"UPDATE meal_category set meal_category_name = ? where meal_category_id = ?";

		@Override
		public void insert(MealCategoryVO mealCategoryVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, mealCategoryVO.getMeal_category_id());
				pstmt.setString(2, mealCategoryVO.getMeal_category_name());
				

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
		public void update(MealCategoryVO mealCategoryVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, mealCategoryVO.getMeal_category_name());
				pstmt.setInt(2, mealCategoryVO.getMeal_category_id());
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
		public void delete(Integer empno) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

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
		public MealCategoryVO findByPrimaryKey(Integer setMeal_category_id) {

			MealCategoryVO MealCategoryVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, setMeal_category_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// MealCategoryVO �]�٬� Domain objects
					MealCategoryVO = new MealCategoryVO();
					MealCategoryVO.setMeal_category_id(rs.getInt("meal_category_id"));
					MealCategoryVO.setMeal_category_name(rs.getString("meal_category_name"));
					
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
			return MealCategoryVO;
		}

		@Override
		public List<MealCategoryVO> getAll() {
			List<MealCategoryVO> list = new ArrayList<MealCategoryVO>();
			MealCategoryVO MealCategoryVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// MealCategoryVO �]�٬� Domain objects
					MealCategoryVO = new MealCategoryVO();
					MealCategoryVO.setMeal_category_id(rs.getInt("meal_category_id"));
					MealCategoryVO.setMeal_category_name(rs.getString("meal_category_name"));
					list.add(MealCategoryVO); // Store the row in the list
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
