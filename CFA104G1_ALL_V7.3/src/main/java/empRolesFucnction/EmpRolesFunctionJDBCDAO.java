package empRolesFucnction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpRolesFunctionJDBCDAO implements EmpRolesFunctionDAO_interface{
	
	public static final  String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	public static final String userid = "root";
	public static final String passwd = "12345678";
	
	private static final String GET_ROLE_FUCTION = 
			"select * from EMPLYEE AS e join ROLES_ACCESS_RIGHT as rar on e.ROLES_ID = rar.ROLES_ID where e.empno = ? order by rar.FUNCTION_ID";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public List<Integer> selectFucnctionByEmpno(Integer empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> functionList = new ArrayList<>();
		
		try {

			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ROLE_FUCTION);		
			pstmt.setInt(1, empno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				functionList.add(rs.getInt("function_id"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);			
		}
		
		return functionList;
	}
	
	
	
	
	public static void closeResource(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {

		EmpRolesFunctionJDBCDAO dao = new EmpRolesFunctionJDBCDAO();
		List<Integer> list = dao.selectFucnctionByEmpno(7006);
		for(int fun : list) {
			System.out.println("function_id: " + fun);
		}
	}

}
