package com.shopOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.shopOrderList.model.productOrderListVO;

public class productOrderJDBCDAO implements productOrderDAO_interface{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = 
			"jdbc:mysql://localhost:3306/CFA104G1?"
			+ "rewriteBatchedStatements=true&"
			+ "serverTimezone=Asia/Taipei";
	String USER = "root";
	String PASSWORD = "12345678";
	
	private static final String INSERT_STMT = "INSERT INTO PRODUCT_ORDER("
			+ "O_PURTIME,MEM_NO,O_STA,O_PAY_STA,O_SHIP_STA,O_TOTALPRI,O_PRODEL,O_DELADRS,O_PAYMTHD,O_SHIPDATE,O_DELCOST) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE PRODUCT_ORDER SET "
			+ "O_PURTIME=?,MEM_NO=?,O_STA=?,O_PAY_STA=?,O_SHIP_STA=?,O_TOTALPRI=?,O_PRODEL=?,O_DELADRS=?,O_PAYMTHD=?,O_SHIPDATE=?,O_DELCOST=? "
			+ "WHERE O_NO = ?";

	private static final String DELETE_STMT = "DELETE FROM PRODUCT_ORDER WHERE O_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PRODUCT_ORDER WHERE O_NO = ?";
	private static final String FIND_BY_MEM_NO = "SELECT * FROM PRODUCT_ORDER WHERE MEM_NO = ?";
	private static final String GET_ALL = "SELECT * FROM PRODUCT_ORDER";
	
	
	@Override
	public productOrderVO insert(productOrderVO productOrderVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, productOrderVO.getO_purtime());
			pstmt.setInt(2, productOrderVO.getMem_no());
			pstmt.setString(3, productOrderVO.getO_sta());
			pstmt.setString(4, productOrderVO.getO_pay_sta());
			pstmt.setString(5, productOrderVO.getO_ship_sta());		
			pstmt.setInt(6, productOrderVO.getO_totalpri());
			pstmt.setString(7, productOrderVO.getO_prodel());
			pstmt.setString(8, productOrderVO.getO_deladrs());
			pstmt.setString(9, productOrderVO.getO_paymthd());
			pstmt.setTimestamp(10, productOrderVO.getO_shipdate());
			pstmt.setInt(11, productOrderVO.getO_delcost());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productOrderVO.setO_no(rs.getInt(1));
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
		return productOrderVO;
	}
	
	
	@Override
	public void update(productOrderVO productOrderVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setTimestamp(1, productOrderVO.getO_purtime());
			pstmt.setInt(2, productOrderVO.getMem_no());
			pstmt.setString(3, productOrderVO.getO_sta());
			pstmt.setString(4, productOrderVO.getO_pay_sta());
			pstmt.setString(5, productOrderVO.getO_ship_sta());			
			pstmt.setInt(6, productOrderVO.getO_totalpri());
			pstmt.setString(7, productOrderVO.getO_prodel());
			pstmt.setString(8, productOrderVO.getO_deladrs());
			pstmt.setString(9, productOrderVO.getO_paymthd());
			pstmt.setTimestamp(10, productOrderVO.getO_shipdate());
			pstmt.setInt(11, productOrderVO.getO_delcost());
			pstmt.setInt(12, productOrderVO.getO_no());
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
	public void delete(Integer o_no) {

		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, o_no);
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
	public productOrderVO findByPK(Integer o_no) {
		productOrderVO productOrderVO = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, o_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productOrderVO = new productOrderVO();
				productOrderVO.setO_no(rs.getInt("O_NO"));
				productOrderVO.setO_purtime(rs.getTimestamp("O_PURTIME"));
				productOrderVO.setMem_no(rs.getInt("MEM_NO"));
				productOrderVO.setO_sta(rs.getString("O_STA"));
				productOrderVO.setO_pay_sta(rs.getString("O_PAY_STA"));
				productOrderVO.setO_ship_sta(rs.getString("O_SHIP_STA"));
				productOrderVO.setO_totalpri(rs.getInt("O_TOTALPRI"));
				productOrderVO.setO_prodel(rs.getString("O_PRODEL"));
				productOrderVO.setO_deladrs(rs.getString("O_DELADRS"));
				productOrderVO.setO_paymthd(rs.getString("O_PAYMTHD"));
				productOrderVO.setO_shipdate(rs.getTimestamp("O_SHIPDATE"));
				productOrderVO.setO_delcost(rs.getInt("O_DELCOST"));			
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
		return productOrderVO;
	}
	
	
	@Override
	public List<productOrderVO> findByMem_NO(Integer mem_no) {
		productOrderVO productOrderVO = null;
		List<productOrderVO> listSecOrder = new ArrayList<productOrderVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productOrderVO = new productOrderVO();
				productOrderVO.setO_no(rs.getInt("O_NO"));
				productOrderVO.setO_purtime(rs.getTimestamp("O_PURTIME"));
				productOrderVO.setMem_no(rs.getInt("MEM_NO"));
				productOrderVO.setO_sta(rs.getString("O_STA"));
				productOrderVO.setO_pay_sta(rs.getString("O_PAY_STA"));
				productOrderVO.setO_ship_sta(rs.getString("O_SHIP_STA"));
				productOrderVO.setO_totalpri(rs.getInt("O_TOTALPRI"));
				productOrderVO.setO_prodel(rs.getString("O_PRODEL"));
				productOrderVO.setO_deladrs(rs.getString("O_DELADRS"));
				productOrderVO.setO_paymthd(rs.getString("O_PAYMTHD"));
				productOrderVO.setO_shipdate(rs.getTimestamp("O_SHIPDATE"));
				productOrderVO.setO_delcost(rs.getInt("O_DELCOST"));
				listSecOrder.add(productOrderVO);
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
		return listSecOrder;
	}
	

	@Override
	public List<productOrderVO> getAll() {
		productOrderVO productOrderVO = null;
		List<productOrderVO> listSecOrder = new ArrayList<productOrderVO>();
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productOrderVO = new productOrderVO();
				productOrderVO.setO_no(rs.getInt("O_NO"));
				productOrderVO.setO_purtime(rs.getTimestamp("O_PURTIME"));
				productOrderVO.setMem_no(rs.getInt("MEM_NO"));
				productOrderVO.setO_sta(rs.getString("O_STA"));
				productOrderVO.setO_pay_sta(rs.getString("O_PAY_STA"));
				productOrderVO.setO_ship_sta(rs.getString("O_SHIP_STA"));
				productOrderVO.setO_totalpri(rs.getInt("O_TOTALPRI"));
				productOrderVO.setO_prodel(rs.getString("O_PRODEL"));
				productOrderVO.setO_deladrs(rs.getString("O_DELADRS"));
				productOrderVO.setO_paymthd(rs.getString("O_PAYMTHD"));
				productOrderVO.setO_shipdate(rs.getTimestamp("O_SHIPDATE"));
				productOrderVO.setO_delcost(rs.getInt("O_DELCOST"));
				listSecOrder.add(productOrderVO);
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
		return listSecOrder;
	}
	
	@Override				///////////////////////////////////////////////////
	public productOrderVO insertWithList(productOrderVO productOrder, List<productOrderListVO> productOrderListVO) {
		// TODO Auto-generated method stub
		return productOrder;
	}
}
