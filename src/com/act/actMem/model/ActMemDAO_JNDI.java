package com.act.actMem.model;

	import java.sql.*;
	import java.sql.Timestamp;
	import java.util.*;
	//import com.act.model.ActVO;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;

import com.act.act.model.ActFiestaVO;
import com.act.act.model.ActVO;
	import com.act.act.model.Act_interface;
import com.act.actPOI.model.ActPOIVO;

	public class ActMemDAO_JNDI {
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String MY_ACT ="select * from act join actmem on act.actID=actmem.actID join member on actmem.memid=member.memID "
				+ "where actmem.memid=? and actmem.actmemStatus=? order by act.actStartDate";



		public List<ActFiestaVO> getMyAct1(Integer memID) {
			List<ActFiestaVO> list = new ArrayList<ActFiestaVO>();
			ActVO ActVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement("MY_ACT");
				pstmt.setInt(1, memID);
				pstmt.setInt(2, 1);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ActVO actvo= new ActVO();
					actvo.setActType(rs.getInt("actType"));
					actvo.setActID(rs.getInt("actID"));
					actvo.setMemID(rs.getInt("memID"));
					actvo.setActCreateDate(rs.getTimestamp("actCreateDate"));
					actvo.setActName(rs.getString("actName"));
					actvo.setActStartDate(rs.getTimestamp("actStartDate"));
					actvo.setActEndDate(rs.getTimestamp("actEndDate"));
					actvo.setActIMG(rs.getBytes("actIMG"));
					actvo.setActContent((Objects.isNull(rs.getString("actContent")))? "^_^" :( rs.getString("actContent"))) ;
					actvo.setActLong(rs.getDouble("actLong"));
					actvo.setActLat(rs.getDouble("actLat"));
					actvo.setActPost(rs.getInt("actPost")) ;
					actvo.setActLocName((Objects.isNull(rs.getString("actLocName")))? "" : (rs.getString("actLocName")));
					actvo.setActAdr(rs.getString("actAdr"));
					actvo.setActUID((Objects.isNull(rs.getString("actUID")))? "" : (rs.getString("actUID")));
					actvo.setActShowUnit((Objects.isNull(rs.getString("actShowUnit")))? "" : (rs.getString("actShowUnit")));
					actvo.setActMasterUnit((Objects.isNull(rs.getString("actMasterUnit")))? "" : (rs.getString("actMasterUnit")));
					actvo.setActWebSales((Objects.isNull(rs.getString("actWebSales")))? "" : (rs.getString("actWebSales")));
					actvo.setActSourceWebName((Objects.isNull(rs.getString("actSourceWebName")))? "" : (rs.getString("actSourceWebName")));
					actvo.setActOnSale((Objects.isNull(rs.getString("actOnSale")))? "" : (rs.getString("actOnSale")));
					actvo.setActPrice((Objects.isNull(rs.getString("actPrice")))? "" : (rs.getString("actPrice")));
					
					ActFiestaVO actfvo=new ActFiestaVO(actvo);
					list.add(actfvo);

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
		
		
		public List<ActFiestaVO> getMyAct2(Integer memID) {
			List<ActFiestaVO> list = new ArrayList<ActFiestaVO>();
			ActVO ActVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement("MY_ACT");
				pstmt.setInt(1, memID);
				pstmt.setInt(2, 2);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ActVO actvo= new ActVO();
					actvo.setActType(rs.getInt("actType"));
					actvo.setActID(rs.getInt("actID"));
					actvo.setMemID(rs.getInt("memID"));
					actvo.setActCreateDate(rs.getTimestamp("actCreateDate"));
					actvo.setActName(rs.getString("actName"));
					actvo.setActStartDate(rs.getTimestamp("actStartDate"));
					actvo.setActIMG(rs.getBytes("actIMG"));
					actvo.setActContent((Objects.isNull(rs.getString("actContent")))? "^_^" :( rs.getString("actContent"))) ;
					actvo.setActLong(rs.getDouble("actLong"));
					actvo.setActLat(rs.getDouble("actLat"));
					actvo.setActPost(rs.getInt("actPost")) ;
					actvo.setActLocName((Objects.isNull(rs.getString("actLocName")))? "" : (rs.getString("actLocName")));
					actvo.setActAdr(rs.getString("actAdr"));
					actvo.setActUID((Objects.isNull(rs.getString("actUID")))? "" : (rs.getString("actUID")));
					actvo.setActShowUnit((Objects.isNull(rs.getString("actShowUnit")))? "" : (rs.getString("actShowUnit")));
					actvo.setActMasterUnit((Objects.isNull(rs.getString("actMasterUnit")))? "" : (rs.getString("actMasterUnit")));
					actvo.setActWebSales((Objects.isNull(rs.getString("actWebSales")))? "" : (rs.getString("actWebSales")));
					actvo.setActSourceWebName((Objects.isNull(rs.getString("actSourceWebName")))? "" : (rs.getString("actSourceWebName")));
					actvo.setActOnSale((Objects.isNull(rs.getString("actOnSale")))? "" : (rs.getString("actOnSale")));
					actvo.setActPrice((Objects.isNull(rs.getString("actPrice")))? "" : (rs.getString("actPrice")));
					
					ActFiestaVO actfvo=new ActFiestaVO(actvo);
					list.add(actfvo);

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
		
		
		
		public List<ActFiestaVO> getMyAct5(Integer memID) {
			List<ActFiestaVO> list = new ArrayList<ActFiestaVO>();
			ActVO ActVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement("MY_ACT");
				pstmt.setInt(1, memID);
				pstmt.setInt(2, 5);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ActVO actvo= new ActVO();
					actvo.setActType(rs.getInt("actType"));
					actvo.setActID(rs.getInt("actID"));
					actvo.setMemID(rs.getInt("memID"));
					actvo.setActCreateDate(rs.getTimestamp("actCreateDate"));
					actvo.setActName(rs.getString("actName"));
					actvo.setActStartDate(rs.getTimestamp("actStartDate"));
					actvo.setActIMG(rs.getBytes("actIMG"));
					actvo.setActContent((Objects.isNull(rs.getString("actContent")))? "^_^" :( rs.getString("actContent"))) ;
					actvo.setActLong(rs.getDouble("actLong"));
					actvo.setActLat(rs.getDouble("actLat"));
					actvo.setActPost(rs.getInt("actPost")) ;
					actvo.setActLocName((Objects.isNull(rs.getString("actLocName")))? "" : (rs.getString("actLocName")));
					actvo.setActAdr(rs.getString("actAdr"));
					actvo.setActUID((Objects.isNull(rs.getString("actUID")))? "" : (rs.getString("actUID")));
					actvo.setActShowUnit((Objects.isNull(rs.getString("actShowUnit")))? "" : (rs.getString("actShowUnit")));
					actvo.setActMasterUnit((Objects.isNull(rs.getString("actMasterUnit")))? "" : (rs.getString("actMasterUnit")));
					actvo.setActWebSales((Objects.isNull(rs.getString("actWebSales")))? "" : (rs.getString("actWebSales")));
					actvo.setActSourceWebName((Objects.isNull(rs.getString("actSourceWebName")))? "" : (rs.getString("actSourceWebName")));
					actvo.setActOnSale((Objects.isNull(rs.getString("actOnSale")))? "" : (rs.getString("actOnSale")));
					actvo.setActPrice((Objects.isNull(rs.getString("actPrice")))? "" : (rs.getString("actPrice")));
					
					ActFiestaVO actfvo=new ActFiestaVO(actvo);
					list.add(actfvo);

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


	}
