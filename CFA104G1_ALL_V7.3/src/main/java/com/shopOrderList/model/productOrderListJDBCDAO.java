package com.shopOrderList.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class productOrderListJDBCDAO implements productOrderListDAO_interface{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA104G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "David";
	String PASSWORD = "12345678";
	
	private static final String INSERT_STMT = "INSERT INTO CFA104G1.ORDER_LIST(O_NO, PI_NO, OL_PROAMOT, OL_PRI)VALUES(?, ?, ?, ?)"; 
	private static final String UPDATE_STMT = "UPDATE CFA104G1.ORDER_LIST SET O_NO=?, PI_NO=?, OL_PROAMOT=?, OL_PRI=? WHERE OL_NO=?";
	private static final String DELETE_STMT = "DELETE FROM CFA104G1.ORDER_LIST WHERE OL_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM CFA104G1.ORDER_LIST WHERE OL_NO=?";
	private static final String GET_ALL = "SELECT * FROM CFA104G1.ORDER_LIST";
	
	
	@Override
	public productOrderListVO insert(productOrderListVO productOrderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, productOrderList.getO_no());
			pstmt.setInt(2, productOrderList.getPi_no());
			pstmt.setInt(3, productOrderList.getOl_proamot());
			pstmt.setInt(4, productOrderList.getOl_pri());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productOrderList.setOl_no(rs.getInt(1));
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
		return productOrderList;
	}
	
	
	@Override
	public void update(productOrderListVO productOrderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, productOrderList.getO_no());
			pstmt.setInt(2, productOrderList.getPi_no());
			pstmt.setInt(3, productOrderList.getOl_proamot());
			pstmt.setInt(4, productOrderList.getOl_pri());
			pstmt.setInt(5, productOrderList.getOl_no());

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
	public void delete(Integer ol_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ol_no);
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
	public productOrderListVO findByPk(Integer ol_no) {
		productOrderListVO productOrderList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, ol_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productOrderList = new productOrderListVO();
				productOrderList.setOl_no(rs.getInt("OL_NO"));
				productOrderList.setO_no( rs.getInt("O_NO"));
				productOrderList.setPi_no(rs.getInt("PI_NO"));
				productOrderList.setOl_proamot(rs.getInt("OL_PROAMOT"));
				productOrderList.setOl_pri(rs.getInt("OL_PRI"));
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
		return productOrderList;
	}
	
	
	@Override
	public List<productOrderListVO> getAll() {
		productOrderListVO productOrderList = null;
		List<productOrderListVO> listSecOrderList = new ArrayList<productOrderListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productOrderList = new productOrderListVO();
				productOrderList.setOl_no(rs.getInt("OL_NO"));
				productOrderList.setO_no( rs.getInt("O_NO"));
				productOrderList.setPi_no(rs.getInt("PI_NO"));
				productOrderList.setOl_proamot(rs.getInt("OL_PROAMOT"));
				productOrderList.setOl_pri(rs.getInt("OL_PRI"));
				listSecOrderList.add(productOrderList);
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
		return listSecOrderList;
	}

	

}
