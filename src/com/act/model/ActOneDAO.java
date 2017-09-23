package com.act.model;
import java.sql.*;
import java.sql.Timestamp;
import java.util.*;
//import com.act.model.ActVO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActOneDAO implements ActOne_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into act (actID,memID,actCreateDate,actName,actStatID,actTimeID,actPriID,actLocID,actStartDate,actEndDate,actSignStartDate,actSignEndDate,actITVType,actITVCount,actMemMax,actMemMin,actImg,actBN,actContent,actWeather,actWD,actWR,actIsHot,actLong,actLat,actLocName,actAdr) "+
																					"values (actID_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,?,?,?)";
	private static final String UPDATE = "update act set actName=?, actStatID=?, actTimeID=?, actPriID=?, "
																					+ "actStartDate=?, actEndDate=?, actSignStartDate=?, actSignEndDate=?,"
																					+ " actITVType=?, actITVCount=?, actMemMax=?, actMemMin=?, "
																					+ "actImg=?, actContent=?, actAdr=? where actID=?";
	private static final String GET_ALL_STMT = "SELECT * FROM act";
//	private static final String GET_ONE_STMT = "select * from act where actID=?";
	private static final String GET_ACT_BY_ACTID="select * from actview where actID=?";
	private static final String GET_ACT_BY_DATE="select * from act where actDate=timestamp (\'?\')";
	private static final String GET_ACT_BY_POIID="select * from act join actPOI on act.actID=actPOI.actID where POIID=?";
	private static final String GET_ACT_BY_WKS="SELECT * FROM act WHERE MOD(TO_CHAR(?, 'J'), 7) + 1 IN (6, 7);";
//	private static final String GET_ACT_BY_DIST="select * from act where ?";
	private static final String GET_ACT_BY_MEMID_CREATE="select * from act where actmem.memID=?";
	private static final String GET_ACT_BY_MEMID_JOIN="select * from act join actmem on act.actID=actmem.actID join member on actmem.memID=member.memID"
																								+"where actmem.memID=?";
	private static final String GET_ACT_BY_MEMID_TRACK="select * from act join actmem on act.actID=actTraMem.actID join member on actTraMem.memID=member.memID"
																								+"where actTraMem.memID=?";
	private static final String GET_ACT_BY_MEMID_FRIEND="select * from act join actmem on act.actID=actmem.actID join Friends on actmem.memID=Friends.frimem1 where Friends.FriendType='好友'";

	private static final String GET_ACT_BY_CLUBID="select * from act join actclub on act.actID=actClub.actID join club on actClub.ClubID=Club.ClubID"
			+"where actClub (actClub.ClubID!=0) AND actClub.ClubID=?";


	@Override

	public void insert(ActOneVO ActVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, ActVO.getMemID());
			pstmt.setTimestamp(2, ActVO.getActCreateDate());
			pstmt.setString(3, ActVO.getActName());
			pstmt.setInt(4, ActVO.getActStatID());
			pstmt.setInt(5, ActVO.getActTimeID());
			pstmt.setInt(6, ActVO.getActPriID());
			pstmt.setInt(7, ActVO.getActLocID());
			pstmt.setTimestamp(8, ActVO.getActStartDate());
			pstmt.setTimestamp(9, ActVO.getActEndDate());
			pstmt.setTimestamp(10, ActVO.getActSignStartDate());
			pstmt.setTimestamp(11, ActVO.getActSignEndDate());
			pstmt.setInt(12, ActVO.getActITVType());
			pstmt.setString(13, ActVO.getActITVCount());
			pstmt.setInt(14, ActVO.getActMemMax());
			pstmt.setInt(15, ActVO.getActMemMin());
			pstmt.setBytes(16, ActVO.getActImg());
			pstmt.setBytes(17, ActVO.getActBN());
			pstmt.setString(18, ActVO.getActContent());
			pstmt.setString(19, ActVO.getActWeather());
			pstmt.setString(20, ActVO.getActWD());
			pstmt.setString(21, ActVO.getActWR());
			pstmt.setDouble(22, ActVO.getActLong());
			pstmt.setDouble(23, ActVO.getActLat());
			pstmt.setString(24, ActVO.getActLocName());
			pstmt.setString(25, ActVO.getActAdr());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(ActOneVO ActVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ActVO.getActName());
			pstmt.setInt(2, ActVO.getActStatID());
			pstmt.setInt(3, ActVO.getActTimeID());
			pstmt.setInt(4, ActVO.getActPriID());
			pstmt.setTimestamp(5, ActVO.getActStartDate());
			pstmt.setTimestamp(6, ActVO.getActEndDate());
			pstmt.setTimestamp(7, ActVO.getActSignStartDate());
			pstmt.setTimestamp(8, ActVO.getActSignEndDate());
			pstmt.setInt(9, ActVO.getActITVType());
			pstmt.setString(10, ActVO.getActITVCount());
			pstmt.setInt(11, ActVO.getActMemMax());
			pstmt.setInt(12, ActVO.getActMemMin());
			pstmt.setBytes(13, ActVO.getActImg());
			pstmt.setString(14, ActVO.getActContent());
			pstmt.setString(15, ActVO.getActAdr());
			pstmt.setInt(16, ActVO.getActID());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ActOneVO getActByActID(Integer actID) {
		ActOneVO actViewVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					actViewVO.setMemName(rs.getString("memName"));
					actViewVO.setActID(rs.getInt("actID"));
					actViewVO.setMemID(rs.getInt("memID"));
					actViewVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
					actViewVO.setActName(rs.getString("actName"));
					actViewVO.setActStatID(rs.getInt("actStatID"));
					actViewVO.setActTimeID(rs.getInt("actTimeID"));
					actViewVO.setActPriID(rs.getInt("actPriID"));
					actViewVO.setActLocID(rs.getInt("actLocID"));
					actViewVO.setActStartDate(rs.getTimestamp("actStartDate"));
					actViewVO.setActEndDate(rs.getTimestamp("actEndDate"));
					actViewVO.setActSignStartDate(rs.getTimestamp("actSignStartDate"));
					actViewVO.setActSignEndDate(rs.getTimestamp("actSignEndDate"));
					actViewVO.setActITVType(rs.getInt("actITVType"));
					actViewVO.setActITVCount(rs.getString("actITVCount"));
					actViewVO.setActMemMax(rs.getInt("actMemMax"));
					actViewVO.setActMemMin(rs.getInt("actMemMin"));
					actViewVO.setActImg(rs.getBytes("actImg"));
					actViewVO.setActBN(rs.getBytes("actBN"));
					actViewVO.setActContent(rs.getString("actContent"));
					actViewVO.setActWeather(rs.getString("actWeather"));
					actViewVO.setActWD(rs.getString("actWD"));
					actViewVO.setActWR(rs.getString("actWR"));
					actViewVO.setActIsHot(rs.getInt("actIsHot"));
					actViewVO.setActLong(rs.getDouble("actLong"));
					actViewVO.setActLat(rs.getDouble("actLat"));
					actViewVO.setActLocName(rs.getString("actLocName"));
					actViewVO.setActAdr(rs.getString("actAdr"));
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
			return actViewVO;
		}


	@Override
	public List<ActOneVO> getAll() {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActStatID(rs.getInt("actStatID"));
				ActVO.setActTimeID(rs.getInt("actTimeID"));
				ActVO.setActPriID(rs.getInt("actPriID"));
				ActVO.setActLocID(rs.getInt("actLocID"));
				ActVO.setActStartDate(rs.getTimestamp("actStartDate"));
				ActVO.setActEndDate(rs.getTimestamp("actEndDate"));
				ActVO.setActSignStartDate(rs.getTimestamp("actSignStartDate"));
				ActVO.setActSignEndDate(rs.getTimestamp("actSignEndDate"));
				ActVO.setActITVType(rs.getInt("actITVType"));
				ActVO.setActITVCount(rs.getString("actITVCount"));
				ActVO.setActMemMax(rs.getInt("actMemMax"));
				ActVO.setActMemMin(rs.getInt("actMemMin"));
				ActVO.setActImg(rs.getBytes("actImg"));
				ActVO.setActBN(rs.getBytes("actBN"));
				ActVO.setActContent(rs.getString("actContent"));
				ActVO.setActWeather(rs.getString("actWeather"));
				ActVO.setActWD(rs.getString("actWD"));
				ActVO.setActWR(rs.getString("actWR"));
				ActVO.setActIsHot(rs.getInt("actIsHot"));
				ActVO.setActLong(rs.getDouble("actLong"));
				ActVO.setActLat(rs.getDouble("actLat"));
				ActVO.setActLocName(rs.getString("actLocName"));
				ActVO.setActAdr(rs.getString("actAdr"));
				list.add(ActVO); // Store the row in the list
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

	@Override
	public List<ActOneVO> getActByPOIID(Integer POIID) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_POIID);
			pstmt.setInt(1, POIID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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

@Override
	public List<ActOneVO> getActByDate(Timestamp actDate) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_DATE);
			pstmt.setTimestamp(1, actDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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

	@Override
	public List<ActOneVO> getActByWks(Timestamp actDate) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_WKS);
			pstmt.setTimestamp(1, actDate);
			pstmt.setTimestamp(2, actDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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


//	@Override
//	public List<ActVO> getActByDist(Integer Dist) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<ActOneVO> getActByMemIDJoin(Integer memID) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_JOIN);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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


	@Override
	public List<ActOneVO> getActByMemIDCreate(Integer memID) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_CREATE);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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


	@Override
	public List<ActOneVO> getActByMemIDFriend(Integer memID) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_FRIEND);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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


	@Override
	public List<ActOneVO> getActByMemIDTrack(Integer memID) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_TRACK);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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


	@Override
	public List<ActOneVO> getActByClub(Integer clubID) {
		List<ActOneVO> list = new ArrayList<ActOneVO>();
		ActOneVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_CLUBID);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO = new ActOneVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setMemID(rs.getInt("memID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(ActVO); // Store the row in the list
			}


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
