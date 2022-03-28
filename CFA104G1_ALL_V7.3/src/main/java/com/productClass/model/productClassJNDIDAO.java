package com.productClass.model;

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


public class productClassJNDIDAO implements productClassDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO CFA104G1.PRODUCT_CLASS(PC_NAME) VALUES(?)";
	private static final String UPDATE_STMT = "UPDATE CFA104G1.PRODUCT_CLASS SET PC_NAME = ? WHERE PC_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM CFA104G1.PRODUCT_CLASS WHERE PC_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA104G1.PRODUCT_CLASS WHERE PC_NO = ?";
	private static final String GET_ALL = "SELECT * FROM CFA104G1.PRODUCT_CLASS";
	
	
	@Override
	public ProductClassVO insert(ProductClassVO productClassVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, productClassVO.getPcName());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productClassVO.setPcNo(rs.getInt(1));
			}
			
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
		return productClassVO;
	}
	
	@Override
	public void update(ProductClassVO productClassVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, productClassVO.getPcName());
			pstmt.setInt(2, productClassVO.getPcNo());

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
	public void delete(Integer pc_no) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, pc_no);
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
	public ProductClassVO findByPK(Integer pc_no) {
		ProductClassVO productClass = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, pc_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productClass = new ProductClassVO();
				productClass.setPcNo(rs.getInt("PC_NO"));
				productClass.setPcName(rs.getString("PC_NAME"));
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
		return productClass;
	}
	
	
	@Override
	public List<ProductClassVO> getAll() {
		ProductClassVO productClass = null;
		List<ProductClassVO> listProductClass = new ArrayList<ProductClassVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productClass = new ProductClassVO();
				productClass.setPcNo(rs.getInt("PC_NO"));
				productClass.setPcName(rs.getString("PC_NAME"));
				listProductClass.add(productClass);
			}
			
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
		return listProductClass;
	}
}
