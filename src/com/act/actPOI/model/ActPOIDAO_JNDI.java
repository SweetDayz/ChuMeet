package com.act.actPOI.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.act.act.model.Act_VO;

public class ActPOIDAO_JNDI {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

private static final String GET_TWO_byPOI = "SELECT "
		+ "act.actID as actID"
		+ "act.actname as actname"
		+ "act.actContent as actContent"
		+ "act.actStartDate as actStartDate"
		+ "act.actPost as actPost"
		+ " from ACTPOI join act on actpoi.actid=act.actid where POIID=?";
	

public Integer getOnePOI(Integer actID){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select POIID from actPOI where actid="+actID);
			rs = pstmt.executeQuery();

				rs.next();
				Act_VO avo= new Act_VO();
				return rs.getInt("POIID");
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
		}



	public List<Act_VO> poix2(Integer POIID) {
		List<Act_VO> list = new ArrayList<Act_VO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TWO_byPOI);
			pstmt.setInt(1, POIID);
			rs = pstmt.executeQuery();

				rs.next();
				Act_VO avo= new Act_VO();
				avo.setActID(rs.getInt("actID"));
				avo.setActName(rs.getString("actname"));
				avo.setActContent(rs.getString("actContent"));
				avo.setMemID(rs.getInt("memID"));
				avo.setActPost(rs.getInt("actPost"));
				avo.setActStartDate(rs.getTimestamp("actStartDate"));
				
				list.add(avo); // Store the row in the list
				rs.next();
				Act_VO avo2= new Act_VO();
				avo2.setActID(rs.getInt("actID"));
				avo2.setActName(rs.getString("actname"));
				avo2.setActContent(rs.getString("actContent"));
				avo2.setMemID(rs.getInt("memID"));
				avo2.setActPost(rs.getInt("actPost"));
				avo2.setActStartDate(rs.getTimestamp("actStartDate"));
				list.add(avo2); // Store the row in the list			


			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	
}
