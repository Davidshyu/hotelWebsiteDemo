package com.newsList.model;

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

public class NewsListJNDIDAO implements NewsListDAO_interface{
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
			"INSERT INTO CFA104G1.NEWSLIST(TITLE,CONTENT,STATUS,NEWSCOMEFROM) VALUES ( ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE CFA104G1.NEWSLIST SET TITLE=?, CONTENT=?, STATUS=?, NEWSCOMEFROM=? WHERE NEWSID = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM CFA104G1.NEWSLIST WHERE NEWSID = ?";
	private static final String FIND_BY_PK = 
			"SELECT * FROM CFA104G1.NEWSLIST WHERE NEWSID = ?";
	private static final String FIND_BY_TITLE = 
			"SELECT * FROM CFA104G1.NEWSLIST WHERE TITLE = ?";
	private static final String GET_ALL = 
			"SELECT * FROM CFA104G1.NEWSLIST";
	
	@Override
	public NewsListVO insert(NewsListVO newsListVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, newsListVO.getTitle());
			pstmt.setString(2, newsListVO.getContent());
			pstmt.setString(3, newsListVO.getStatus());
			pstmt.setString(4, newsListVO.getNewsComeFrom());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				newsListVO.setNewsId(rs.getInt(1));
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
		return newsListVO;
	}
	
	@Override
	public void update(NewsListVO newsListVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, newsListVO.getTitle());
			pstmt.setString(2, newsListVO.getContent());
			pstmt.setString(3, newsListVO.getStatus());
			pstmt.setString(4, newsListVO.getNewsComeFrom());
			pstmt.setInt(5, newsListVO.getNewsId());
			pstmt.executeUpdate();
			
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
	}
	
	
	@Override
	public void delete(Integer newsId) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, newsId);
			pstmt.executeUpdate();
			
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
	}
	
	@Override
	public NewsListVO findByPK(Integer newsId) {
		NewsListVO newsListVO = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, newsId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsListVO = new NewsListVO();
				newsListVO.setNewsId(rs.getInt("NEWSID"));
				newsListVO.setTitle(rs.getString("TITLE"));
				newsListVO.setContent(rs.getString("CONTENT"));
				newsListVO.setStatus(rs.getString("STATUS"));
				newsListVO.setNewsComeFrom(rs.getString("NEWSCOMEFROM"));
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
		return newsListVO;
	}
	
	@Override
	public NewsListVO findByTITLE(String title) {
		NewsListVO newsListVO = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_TITLE);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsListVO = new NewsListVO();
				newsListVO.setNewsId(rs.getInt("NEWSID"));
				newsListVO.setTitle(rs.getString("TITLE"));
				newsListVO.setContent(rs.getString("CONTENT"));
				newsListVO.setStatus(rs.getString("STATUS"));
				newsListVO.setNewsComeFrom(rs.getString("NEWSCOMEFROM"));
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
		return newsListVO;
	}
	
	
	@Override
	public List<NewsListVO> getAll() {
		NewsListVO newsListVO = null;
		List<NewsListVO> listNewsList = new ArrayList<NewsListVO>();		//===============//
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsListVO = new NewsListVO();
				newsListVO.setNewsId(rs.getInt("NEWSID"));
				newsListVO.setTitle(rs.getString("TITLE"));
				newsListVO.setContent(rs.getString("CONTENT"));
				newsListVO.setStatus(rs.getString("STATUS"));
				newsListVO.setNewsComeFrom(rs.getString("NEWSCOMEFROM"));
				listNewsList.add(newsListVO);			//=================================//
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
		return listNewsList;
	}
	
	public static void main(String[] args) {

		NewsListJNDIDAO dao = new NewsListJNDIDAO();

//		 ??????
//		NewsListVO newsListVO1 = new NewsListVO();
//		newsListVO1.setTitle("??????????????????????????????");
//		newsListVO1.setContent(" ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
//		newsListVO1.setStatus("1");
//		newsListVO1.setNewsComeFrom("????????????");
//		dao.insert(newsListVO1);

		// ??????
//		NewsListVO newsListVO2 = new NewsListVO();
//		newsListVO2.setNewsId(10);
//		newsListVO2.setTitle("??????????????????");
//		newsListVO2.setContent("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
//		newsListVO2.setStatus("1");
//		newsListVO2.setNewsComeFrom("??????");
//		dao.update(newsListVO2);

		// ??????
//		dao.delete(13);

		// ??????-?????????????????????????????????????????????
//		NewsListVO newsListVO3 = dao.findByPK(10);
//		System.out.print(newsListVO3.getNewsId() + ",");
////		System.out.print(newsListVO3.getThumbnail() + ",");
//		System.out.print(newsListVO3.getTitle() + ",");
//		System.out.print(newsListVO3.getContent() + ",");
//		System.out.print(newsListVO3.getStatus() + ",");
////		System.out.print(newsListVO3.getNewsDate()+ ",");
//		System.out.println(newsListVO3.getNewsComeFrom());
//		System.out.println("---------------------");
		
		// ??????-?????????????????????????????????????????????
//		NewsListVO newsListVO3 = dao.findByTITLE("??????4");
//		System.out.print(newsListVO3.getNewsId() + ",");
////		System.out.print(newsListVO3.getThumbnail() + ",");
//		System.out.print(newsListVO3.getTitle() + ",");
//		System.out.print(newsListVO3.getContent() + ",");
//		System.out.print(newsListVO3.getStatus() + ",");
////		System.out.print(newsListVO3.getNewsDate()+ ",");
//		System.out.println(newsListVO3.getNewsComeFrom());
//		System.out.println("---------------------");
		
		
		// ??????-????????????????????????
//		List<NewsListVO> list = dao.getAll();
//		for (NewsListVO aNewsList : list) {
//			System.out.print(aNewsList.getNewsId() + ",");
////			System.out.print(aNewsList.getThumbnail() + ",");
//			System.out.print(aNewsList.getTitle() + ",");
//			System.out.print(aNewsList.getContent() + ",");
//			System.out.print(aNewsList.getStatus() + ",");
////			System.out.print(aNewsList.getNewsDate()+ ",");
//			System.out.print(aNewsList.getNewsComeFrom());
//			System.out.println();
//		}
	}
	
	
}
