package com.act.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActMemDAO implements ActMem_Interface{
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
	
	private static final String WHOSIN = "select MEMBER.MEMNAME AS memName, ACTMEM.MEMID as memID from ACTMEM Join MEMBER ON ACTMEM.MEMID=MEMBER.MEMID where ACTMEM.ACTID=? AND ACTMEM.ACTMEMSTATUS in (1,2)";
	private static final String AMIIN = "select memID, ACTMEMSTATUS from ACTMEN where actID=? AND ACTMEMSTATUS in (1,2) AND memID=?";
	private static final String AMITRAKING = "select memID from ACTMEN where actID=? AND ACTMEMSTATUS in 5 AND memID=?";
	private static final String AMIRATED = "select memID from ACTMEN where actID=? AND ACTSTAR>0";
	private static final String DELETE = 	"delete from ACTMEM where actid=? and MEMID=?";
	private static final String INSERT = 
			"INSERT INTO ACTMEM (actID, memID, actMemStatus, actJoinDate) VALUES (?,?,?,SYSTIMESTAMP)";
	
	
	@Override
	public HashMap<Integer, String> whosIn(Integer actID) {
		HashMap<Integer, String> hm = new HashMap<Integer, String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(WHOSIN);
			pstmt.setInt(1, actID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer k=rs.getInt("memID");
				String v= rs.getString("memName");
				System.out.println(k+", "+v);
				hm.put(k,v);
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
		return hm;
	}

	public void delete(Integer actID, Integer memID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actID);
			pstmt.setInt(2, memID);

			pstmt.executeUpdate();
			System.out.println("deleted: "+actID+", "+memID);
			// Handle any driver errors
		} catch (SQLException  se) {
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
	public void insert(ActMemVO actmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, actmVO.getActID());
			pstmt.setInt(2, actmVO.getMemID());
			pstmt.setInt(3, actmVO.getActMemStatus());

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
	public List<Integer> amIIn(Integer actID, Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> amITracking(Integer actID, Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> amIRated(Integer actID, Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}
//	public static void main(String[] args) throws IOException {
//		ActMemDAO dao = new ActMemDAO();

		// 查詢	ok
//		HashMap<Integer, String> hs = dao.whosIn(1);
//		
//		for (Integer key : hs.keySet()) {
//			   System.out.println("------------------------------------------------");
//			   System.out.println("key: " + key + " value: " + hs.get(key));
//			}

//		List<Act_VO> list = dao.getAll();
//		for (Act_VO aDept : list) {
//			System.out.print(aDept.getMemName() + ",");
//			System.out.print(aDept.getActName() + ",");
//			System.out.print(aDept.getActAdr());
//			System.out.println();
//		}
		
//		dao.delete(6, 2);

	}


