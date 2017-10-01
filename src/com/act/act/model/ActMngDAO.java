package com.act.act.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ActMngDAO implements  ActMngDAO_interface {
	private static final String GET_ALL_STMT = "SELECT * FROM actmng order by actID";
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "BA103G2";
//	String passwd = "a123";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public List<ActMngVO> getAll() {
		List<ActMngVO> list = new ArrayList<ActMngVO>();
		ActMngVO ActMngVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

//			try {
//				Class.forName(driver);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			
			
			while (rs.next()) {
				ActMngVO = new ActMngVO();
				ActMngVO.setActID(rs.getInt("actID"));
				ActMngVO.setMemID(rs.getInt("memID"));
				ActMngVO.setMemName(rs.getString("MemName"));
				ActMngVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				ActMngVO.setActName(rs.getString("actName"));
				ActMngVO.setActStatID(rs.getInt("actStatID"));
				ActMngVO.setActAdr(rs.getString("actAdr"));
				list.add(ActMngVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	
	
//	public static void main(String[] args) throws IOException {
//
//		ActMngDAO dao = new ActMngDAO();
//		List<ActMngVO> list = dao.getAll();
//
//		
//		for (ActMngVO ActMngVO : list) {
//			System.out.print(ActMngVO.getActID() + ",");
//			System.out.print(ActMngVO.getActName() + ",");
//			System.out.print(ActMngVO.getMemName() + ",");
//			System.out.print(ActMngVO.getActCreateDate() + ",");
//			System.out.print(ActMngVO.getActAdr() + ",");
//			System.out.println();
//		}
//
//}
}
