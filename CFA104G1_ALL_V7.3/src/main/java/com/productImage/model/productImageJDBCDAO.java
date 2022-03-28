package com.productImage.model;

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

public class productImageJDBCDAO implements productImageDAO_interface{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA104G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "root";
	String PASSWORD = "12345678";
	
	private static final String INSERT_STMT = "INSERT INTO CFA104G1.PRODUCT_IMAGES(PI_NO, PIM_IMG)VALUES(?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA104G1.PRODUCT_IMAGES SET PI_NO=?, PIM_IMG=? WHERE PIM_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA104G1.PRODUCT_IMAGES WHERE PIM_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA104G1.PRODUCT_IMAGES WHERE PIM_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA104G1.PRODUCT_IMAGES";
	
	
	@Override
	public productImageVO insert(productImageVO productImage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);		//=================
			pstmt.setInt(1, productImage.getPi_no());
			pstmt.setBytes(2, productImage.getPim_img());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productImage.setPim_no(rs.getInt(1));
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
		return productImage;
	}
	
	@Override
	public void update(productImageVO productImage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, productImage.getPi_no());
			pstmt.setBytes(2, productImage.getPim_img());
			pstmt.setInt(3, productImage.getPim_no());

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
	public void delete(Integer pim_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, pim_no);
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
	public productImageVO findByPk(Integer pim_no) {
		productImageVO productImage = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, pim_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productImage = new productImageVO();
				productImage.setPim_no(rs.getInt("PIM_NO"));
				productImage.setPi_no(rs.getInt("PI_NO"));
				productImage.setPim_img(rs.getBytes("PIM_IMG"));
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
		return productImage;
	}

	
	
	
	@Override
	public List<productImageVO> getAll() {
		productImageVO productImage = null;
		List<productImageVO> lsitSecProductImages = new ArrayList<productImageVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productImage = new productImageVO();
				productImage.setPim_no(rs.getInt("PIM_NO"));
				productImage.setPi_no(rs.getInt("PI_NO"));
				productImage.setPim_img(rs.getBytes("PIM_IMG"));
				lsitSecProductImages.add(productImage);
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
		return lsitSecProductImages;
	}              
}
