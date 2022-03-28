package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class productJDBCDAO implements productDAO_interface{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA104G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "root";
	String PASSWORD = "12345678";
	
	private static final String INSERT_STMT = "INSERT INTO PRODUCT_INFORMATION(PI_NAME, PC_NO, PI_CONTENT, PI_PRI, PI_STOCK, PI_STA) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE PRODUCT_INFORMATION SET PI_NAME=?, PC_NO=?, PI_CONTENT=?, PI_PRI=?, PI_STOCK=?, PI_STA=? WHERE PI_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM PRODUCT_INFORMATION WHERE PI_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PRODUCT_INFORMATION WHERE PI_NO = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM PRODUCT_INFORMATION WHERE PI_NAME = ?";
	private static final String GET_ALL = "SELECT * FROM PRODUCT_INFORMATION";
	
	@Override
	public ProductVO insert(ProductVO productVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, productVO.getPiName());
			pstmt.setInt(2, productVO.getPcNo());
			pstmt.setString(3, productVO.getPiContent());
			pstmt.setInt(4, productVO.getPiPri());
			pstmt.setInt(5, productVO.getPiStock());
			pstmt.setString(6, productVO.getPiSta());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productVO.setPiNo(rs.getInt(1));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return productVO;
	}
	
	@Override
	public void update(ProductVO productVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, productVO.getPiName());
			pstmt.setInt(2, productVO.getPcNo());
			pstmt.setString(3, productVO.getPiContent());
			pstmt.setInt(4, productVO.getPiPri());
			pstmt.setInt(5, productVO.getPiStock());
			pstmt.setString(6, productVO.getPiSta());
			pstmt.setInt(7, productVO.getPiNo());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void delete(Integer pi_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, pi_no);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public ProductVO findByPK(Integer pi_no) {
		ProductVO productVO = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, pi_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setPiNo(rs.getInt("PI_NO"));
				productVO.setPiName(rs.getString("PI_NAME"));
				productVO.setPcNo(rs.getInt("PC_NO"));
				productVO.setPiContent(rs.getString("PI_CONTENT"));
				productVO.setPiPri(rs.getInt("PI_PRI"));
				productVO.setPiStock(rs.getInt("PI_STOCK"));
				productVO.setPiSta(rs.getString("PI_STA"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return productVO;
	}
	
	@Override
	public ProductVO findByPI_Name(String pi_name) {
		ProductVO productVO = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_NAME);
			pstmt.setString(1, pi_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setPiNo(rs.getInt("PI_NO"));
				productVO.setPiName(rs.getString("PI_NAME"));
				productVO.setPcNo(rs.getInt("PC_NO"));
				productVO.setPiContent(rs.getString("PI_CONTENT"));
				productVO.setPiPri(rs.getInt("PI_PRI"));
				productVO.setPiStock(rs.getInt("PI_STOCK"));
				productVO.setPiSta(rs.getString("PI_STA"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return productVO;
	}
	
	
	@Override
	public List<ProductVO> getAll() {
		ProductVO productVO = null;
		List<ProductVO> listProductInform = new ArrayList<ProductVO>();		//===============//
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setPiNo(rs.getInt("PI_NO"));
				productVO.setPiName(rs.getString("PI_NAME"));
				productVO.setPcNo(rs.getInt("PC_NO"));
				productVO.setPiContent(rs.getString("PI_CONTENT"));
				productVO.setPiPri(rs.getInt("PI_PRI"));
				productVO.setPiStock(rs.getInt("PI_STOCK"));
				productVO.setPiSta(rs.getString("PI_STA"));
				listProductInform.add(productVO);			//=================================//
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return listProductInform;
	}
	
	
//	public static void main (String[] args) {
//		productJDBCDAO dao = new productJDBCDAO();
//		productVO productVO = new productVO();
//		productVO.setPi_name("���ֹq��");
//		productVO.setPc_no(1);
//		productVO.setPi_content("�W�j���ֹq����!!!!!�W�j���ֹq����!!!!!�W�j���ֹq����!!!!!�W�j���ֹq����!!!!!");
//		productVO.setPi_pri(20000);
//		productVO.setPi_stock(1);
//		productVO.setPi_sta("�W�[");
//		productVO = dao.insert(productVO);
//		System.out.println(productVO.getPi_no());
//		
//	}	
	
}
