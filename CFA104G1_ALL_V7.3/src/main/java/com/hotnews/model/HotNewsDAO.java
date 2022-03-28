package com.hotnews.model;

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

public class HotNewsDAO implements HotNewsDAO_interface{
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
			"INSERT INTO HOT_NEWS_LIST ( HOT_NEWS_TITLE, HOT_NEWS_DESCRIPTION, HOT_NEWS_DATE, HOT_NEWS_STATUS, HOT_NEWS_PHOTO) VALUES (?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT HOT_NEWS_ID, HOT_NEWS_TITLE, HOT_NEWS_DESCRIPTION, HOT_NEWS_DATE, HOT_NEWS_STATUS, HOT_NEWS_PHOTO FROM HOT_NEWS_LIST order by HOT_NEWS_ID";
		private static final String GET_ALL_ON_STMT = 
				"SELECT HOT_NEWS_ID, HOT_NEWS_TITLE, HOT_NEWS_DESCRIPTION, HOT_NEWS_DATE, HOT_NEWS_STATUS, HOT_NEWS_PHOTO FROM HOT_NEWS_LIST where HOT_NEWS_STATUS = 1 order by HOT_NEWS_ID";
		private static final String GET_ONE_STMT = 
			"SELECT HOT_NEWS_ID, HOT_NEWS_TITLE, HOT_NEWS_DESCRIPTION, HOT_NEWS_DATE, HOT_NEWS_STATUS, HOT_NEWS_PHOTO FROM HOT_NEWS_LIST where HOT_NEWS_ID = ?";
		private static final String DELETE = 
			"DELETE FROM HOT_NEWS_LIST where hot_news_id = ?";
		private static final String UPDATE = 
			"UPDATE HOT_NEWS_LIST set HOT_NEWS_TITLE=?, HOT_NEWS_DESCRIPTION=?, HOT_NEWS_DATE=?, HOT_NEWS_STATUS=? where HOT_NEWS_ID = ?";
		private static final String UPDATE_PHOTO_STMT = 
			"UPDATE HOT_NEWS_LIST set HOT_NEWS_PHOTO=? where HOT_NEWS_ID = ?";
		
		@Override
		public void insert(HotNewsVO HotNewsVO) {
	
			Connection con = null;
			PreparedStatement pstmt = null;
	
			try {
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
	
				pstmt.setString(1, HotNewsVO.getHot_news_title());
				pstmt.setString(2, HotNewsVO.getHot_news_description());
				pstmt.setDate(3, HotNewsVO.getHot_news_date());
				pstmt.setBoolean(4, HotNewsVO.getHot_news_status());
				pstmt.setBytes(5, HotNewsVO.getHot_news_photo());
	
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
		public void update(HotNewsVO HotNewsVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				System.out.println(pstmt);
				pstmt.setString(1, HotNewsVO.getHot_news_title());
				pstmt.setString(2, HotNewsVO.getHot_news_description());
				pstmt.setDate(3, HotNewsVO.getHot_news_date());
				pstmt.setBoolean(4, HotNewsVO.getHot_news_status());
				pstmt.setInt(5, HotNewsVO.getHot_news_id());
				System.out.println(pstmt);

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
		public void updatePhoto(HotNewsVO HotNewsVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_PHOTO_STMT);
				System.out.println(pstmt);
				pstmt.setBytes(1, HotNewsVO.getHot_news_photo());
				pstmt.setInt(2, HotNewsVO.getHot_news_id());
				System.out.println(pstmt);

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
		public void delete(Integer hot_news_id) {
	
			Connection con = null;
			PreparedStatement pstmt = null;
	
			try {
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
	
				pstmt.setInt(1, hot_news_id);
	
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
		public HotNewsVO findByPrimaryKey(Integer hot_news_id) {
	
			HotNewsVO HotNewsVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
	
				pstmt.setInt(1, hot_news_id);
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					HotNewsVO = new HotNewsVO();
					HotNewsVO.setHot_news_id(rs.getInt("hot_news_id"));
					HotNewsVO.setHot_news_title(rs.getString("hot_news_title"));
					HotNewsVO.setHot_news_description(rs.getString("hot_news_description"));
					HotNewsVO.setHot_news_status(rs.getBoolean("hot_news_status"));
					HotNewsVO.setHot_news_date(rs.getDate("hot_news_date"));
					HotNewsVO.setHot_news_photo(rs.getBytes("hot_news_photo"));
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
			return HotNewsVO;
		}
	
		@Override
		public List<HotNewsVO> getAll() {
			List<HotNewsVO> list = new ArrayList<HotNewsVO>();
			HotNewsVO HotNewsVO = null;
	
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	
			try {
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					// HotNewsVO �]�٬� Domain objects
					HotNewsVO = new HotNewsVO();
					
					HotNewsVO.setHot_news_id(rs.getInt("hot_news_id"));
					HotNewsVO.setHot_news_title(rs.getString("hot_news_title"));
					HotNewsVO.setHot_news_description(rs.getString("hot_news_description"));
					HotNewsVO.setHot_news_status(rs.getBoolean("hot_news_status"));
					HotNewsVO.setHot_news_date(rs.getDate("hot_news_date"));
					HotNewsVO.setHot_news_photo(rs.getBytes("hot_news_photo"));
					
					list.add(HotNewsVO); // Store the row in the list
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
		public List<HotNewsVO> getAllOn() {
			List<HotNewsVO> list = new ArrayList<HotNewsVO>();
			HotNewsVO HotNewsVO = null;
	
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	
			try {
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_ON_STMT);
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					// HotNewsVO �]�٬� Domain objects
					HotNewsVO = new HotNewsVO();
					
					HotNewsVO.setHot_news_id(rs.getInt("hot_news_id"));
					HotNewsVO.setHot_news_title(rs.getString("hot_news_title"));
					HotNewsVO.setHot_news_description(rs.getString("hot_news_description"));
					HotNewsVO.setHot_news_status(rs.getBoolean("hot_news_status"));
					HotNewsVO.setHot_news_date(rs.getDate("hot_news_date"));
					HotNewsVO.setHot_news_photo(rs.getBytes("hot_news_photo"));
					
					list.add(HotNewsVO); // Store the row in the list
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
