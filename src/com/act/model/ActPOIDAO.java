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
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
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
			List<String> list = new ArrayList<String>();
			ActVO actVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_POI_BY_ACTID_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					list.add(rs.getString("POINAMEC"));
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
			return list;
		}
		
		@Override
		public List<ActVO> getActByPOIID(Integer POIID) {
			List<ActVO> list = new ArrayList<ActVO>();
			ActVO actVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_ACT_BY_POIID_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					actVO.setActID(rs.getInt("actID"));
					actVO.setMemID(rs.getInt("memID"));
					actVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
					actVO.setActName(rs.getString("actName"));
					actVO.setActStatID(rs.getInt("actStatID"));
					actVO.setActLocID(rs.getInt("actLocID"));
					actVO.setActStartDate(rs.getTimestamp("actStartDate"));
					actVO.setActITVType(rs.getInt("actITVType"));
					actVO.setActMemMax(rs.getInt("actMemMax"));
					actVO.setActMemMin(rs.getInt("actMemMin"));
					actVO.setActImg(rs.getBytes("actImg"));
					actVO.setActContent(rs.getString("actContent"));
					actVO.setActLong(rs.getDouble("actLong"));
					actVO.setActLat(rs.getDouble("actLat"));
					actVO.setActAdr(rs.getString("actAdr"));
					list.add(actVO); // Store the row in the list
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
