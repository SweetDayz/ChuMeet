package com.act.model;
	import java.sql.*;
	import java.util.*;
	//import com.act.model.ActVO;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;

	public class ActPOIDAO implements ActPOIDAO_interface{
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String INSERT_STMT = "insert into actPOI values (?,?)";
		private static final String DELETE_STMT = "DELETE FROM actPOI where actID = ?";
		private static final String SELECT_POI_BY_ACTID_STMT = "select POINAMEC from actPOI a join POI p on a.POIID=p.POIID where a.actID=?";
		private static final String SELECT_ACT_BY_POIID_STMT = "select act.actID, act.memID, act.actImg, act.actITVLType, act.actStatID, act.ACTSTARTDATE, act.actMemMax, act.ACTCONTENT, act.ACTLOCID, act.actLat, act.actLong, act.ACTCREATEDATE from actPOI join act on actPOI.ACTID=act.ACTID where actPOI.POIID=?";

		
		@Override
		public List<String> getPOIByActID(Integer actID) {
			List<String> POIlist = new ArrayList<String>();
			Act_VO act_VO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_POI_BY_ACTID_STMT);
				pstmt.setInt(1, actID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					POIlist.add(rs.getString("POINAMEC"));
				}

				// Handle any SQL errors
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
			return POIlist;
		}
		
		@Override
		public List<Act_VO> getActByPOIID(Integer POIID) {
			List<Act_VO> list = new ArrayList<Act_VO>();
			Act_VO act_VO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_ACT_BY_POIID_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					act_VO = new Act_VO();
					act_VO.setMemName(rs.getString("memName"));					
					act_VO.setActID(rs.getInt("actID"));
					act_VO.setMemID(rs.getInt("memID"));
					act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
					act_VO.setActName(rs.getString("actName"));
					act_VO.setActStatus(rs.getInt("actStatus"));
					act_VO.setActPriID(rs.getInt("actPriID"));
					act_VO.setActStartDate(rs.getTimestamp("actStartDate"));
					act_VO.setActEndDate(rs.getTimestamp("actEndDate"));
					act_VO.setActSignStartDate(rs.getTimestamp("actSignStartDate"));
					act_VO.setActSignEndDate(rs.getTimestamp("actSignEndDate"));
					act_VO.setActTimeTypeID(rs.getInt("actTimeTypeID"));
					act_VO.setActTimeTypeCnt(rs.getString("actTimeTypeCnt"));
					act_VO.setActMemMax(rs.getInt("actMemMax"));
					act_VO.setActMemMin(rs.getInt("actMemMin"));
					act_VO.setActIMG(rs.getBytes("actIMG"));
					act_VO.setActContent(rs.getString("actContent"));
					act_VO.setActIsHot(rs.getInt("actIsHot"));
					act_VO.setActLong(rs.getDouble("actLong"));
					act_VO.setActLat(rs.getDouble("actLat"));
					act_VO.setActPost(rs.getInt("actPost"));
					act_VO.setActLocName(rs.getString("actLocName"));
					act_VO.setActAdr(rs.getString("actAdr"));
				};

				// Handle any SQL errors
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
		
		
		@Override
		public void insert(ActPOIVO actPOIVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, actPOIVO.getActID());
				pstmt.setInt(2, actPOIVO.getPOIID());

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " 	+ se.getMessage());
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
		public void delete(Integer actID) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, actID);

				pstmt.executeUpdate();
				System.out.println("actPOI delete compelete");
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " 	+ se.getMessage());
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
		public List<ActPOIVO> mutate(Integer actID, List<Integer> poilist) {
			// TODO Auto-generated method stub
			List<ActPOIVO> list = new ArrayList<ActPOIVO>();
			ActPOIVO actPOIVO = null;

			Connection con = null;
			try {
				con.setAutoCommit(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, actID);
				pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(INSERT_STMT);
				//TODO size()
				
				System.out.println("actPOI delete compelete");
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " 	+ se.getMessage());
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
			return list;
		}	

	
}
