package com.newsListImage.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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


public class NewsListImageJNDIDAO implements NewsListImageDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA104G1.NewsList_IMAGES(newsID, nimImg)VALUES(?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA104G1.NewsList_IMAGES SET newsID=?, nimImg=? WHERE nimNo=?";
	private static final String DELETE_STMT = "DELETE FROM CFA104G1.NewsList_IMAGES WHERE nimNo=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA104G1.NewsList_IMAGES WHERE nimNo=?";
	private static final String GET_ALL = "SELECT * FROM CFA104G1.NewsList_IMAGES";
	
	
	@Override
	public NewsListImageVO insert(NewsListImageVO newsListImage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);		//=================
			pstmt.setInt(1, newsListImage.getNewsID());
			pstmt.setBytes(2, newsListImage.getNimImg());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				newsListImage.setNimNo(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newsListImage;
	}
	
	@Override
	public void update(NewsListImageVO newsListImage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, newsListImage.getNewsID());
			pstmt.setBytes(2, newsListImage.getNimImg());
			pstmt.setInt(3, newsListImage.getNimNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void delete(Integer nimNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, nimNo);
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public NewsListImageVO findByPk(Integer nimNo) {
		NewsListImageVO newsListImage = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, nimNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsListImage = new NewsListImageVO();
				newsListImage.setNimNo(rs.getInt("nimNo"));
				newsListImage.setNewsID(rs.getInt("newsID"));
				newsListImage.setNimImg(rs.getBytes("nimImg"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newsListImage;
	}

	
	
	
	@Override
	public List<NewsListImageVO> getAll() {
		NewsListImageVO newsListImage = null;
		List<NewsListImageVO> lsitSecProductImages = new ArrayList<NewsListImageVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsListImage = new NewsListImageVO();
				newsListImage.setNimNo(rs.getInt("nimNo"));
				newsListImage.setNewsID(rs.getInt("newsID"));
				newsListImage.setNimImg(rs.getBytes("nimImg"));;
				lsitSecProductImages.add(newsListImage);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lsitSecProductImages;
	}       
	public static void main(String[] args) {

		NewsListImageJNDIDAO dao = new NewsListImageJNDIDAO();

//		 新增
//		NewsListImageVO newsListImage = new NewsListImageVO();
//		newsListImage.setNewsID(1);
//		newsListImage.setNimImg(null);
//		dao.insert(newsListImage);

		// 修改
//		NewsListImageVO newsListImage = new NewsListImageVO();
//		newsListImage.setNimNo(1);
//		newsListImage.setNewsID(1);
//		newsListImage.setNimImg(null);
//		dao.update(newsListImage);

		// 刪除
//		dao.delete(1);

		// 查詢-用媒體報導編號，列出該單筆報導
//		NewsListImageVO newsListImage = dao.findByPk(1);
//		System.out.print(newsListImage.getNimNo() + ",");
//		System.out.print(newsListImage.getNewsID() + ",");
//		System.out.print(newsListImage.getNimImg() + ",");
//		System.out.println("---------------------");
		
		
		// 查詢-列出所有媒體報導
		List<NewsListImageVO> list = dao.getAll();
		for (NewsListImageVO aNewsListImage : list) {
			System.out.print(aNewsListImage.getNimNo() + ",");
			System.out.print(aNewsListImage.getNewsID() + ",");
			System.out.print(aNewsListImage.getNimImg() + ",");
			System.out.println();
		}
	}
	
}
