package com.act.act.model;
import java.sql.*;
import java.sql.Timestamp;
import java.util.*;
//import com.act.model.ActVO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Act_DAO implements Act_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into act(actID, memID, actCreateDate, actName, actStatus, actPriID, actStartDate ,actEndDate ,actSignStartDate ,actSignEndDate ,actTimeTypeID ,actTimeTypeCnt ,actMemMax ,actMemMin ,actIMG ,actContent ,actIsHot ,actLong,actLat ,actPost ,actLocName, actAdr) "+
			"values (act_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,?,?,?,?)";


private static final String UPDATE = "update act set "
+ "actName=?, "
+ "actPriID=?, "
+ "actStartDate=?, "
+ "actEndDate=?, "
+ "actSignStartDate=?, "
+ "actSignEndDate=?, "
+ "actTimeTypeID=?, "
+ "actTimeTypeCnt=?, "
+ "actMemMax=?, "
+ "actMemMin=?, "
+ "actIMG=?, "
+ "actContent=?, "
+ "actAdr=?"
+ "where actID=?";

//private static final String GET_ALL_STMT = "SELECT actID,memID,actCreateDate,actName,actStatus,actPriID,actStartDate,actEndDate,actSignStartDate,actSignEndDate,actTimeTypeID,actTimeTypeCnt,actMemMax,actMemMin,actIMG,actContent,actIsHot,actLong,actLat,actPost,actLocName,actAdr FROM act";
//private static final String GET_ONE_STMT = "select * from act where actID=?";
private static final String GET_ONE_STMT = "select "
+"member.memName as memName, "
+"act.actID as actID, "
+"act.memID as memID, "
+"act.actCreateDate as actCreateDate, "
+"act.actName as actName, "
+"act.actStatus as actStatus, "
+"act.actPriID as actPriID, "
+"act.actStartDate as actStartDate, "
+"act.actEndDate as actEndDate, "
+"act.actSignStartDate as actSignStartDate, "
+"act.actSignEndDate as actSignEndDate, "
+"act.actTimeTypeID as actTimeTypeID, "
+"act.actTimeTypeCnt as actTimeTypeCnt, "
+"act.actMemMax as actMemMax, "
+"act.actMemMin as actMemMin, "
+"act.actIMG as actIMG, "
+"act.actContent as actContent, "
+"act.actIsHot as actIsHot, "
+"act.actLong as actLong, "
+"act.actLat as actLat, "
+"act.actPost as actPost, "
+"act.actLocName as actLocName, "
+"act.actAdr as actAdr "
+"FROM ACT join MEMBER on act.memID=MEMBER.memID where act.actID=?";

private static final String GET_ALL_STMT = "select "
+"member.memName as memName, "
+"act.actID as actID, "
+"act.memID as memID, "
+"act.actCreateDate as actCreateDate, "
+"act.actName as actName, "
+"act.actStatus as actStatus, "
+"act.actPriID as actPriID, "
+"act.actStartDate as actStartDate, "
+"act.actEndDate as actEndDate, "
+"act.actSignStartDate as actSignStartDate, "
+"act.actSignEndDate as actSignEndDate, "
+"act.actTimeTypeID as actTimeTypeID, "
+"act.actTimeTypeCnt as actTimeTypeCnt, "
+"act.actMemMax as actMemMax, "
+"act.actMemMin as actMemMin, "
+"act.actIMG as actIMG, "
+"act.actContent as actContent, "
+"act.actIsHot as actIsHot, "
+"act.actLong as actLong, "
+"act.actLat as actLat, "
+"act.actPost as actPost, "
+"act.actLocName as actLocName, "
+"act.actAdr as actAdr "
+"FROM ACT join MEMBER on act.memID=MEMBER.memID";

private static final String GET_ACT_BY_ACTID="select * from act join member where actID=?";
private static final String GET_ACT_BY_Wat="select * from act join member where";

private static final String GET_ACT_BY_DATE="select * from act where actDate=timestamp (\'?\')";
private static final String GET_ACT_BY_POIID="select * from act join actPOI on act.actID=actPOI.actID where POIID=?";
private static final String GET_ACT_BY_WKS="SELECT * FROM act WHERE MOD(TO_CHAR(?, 'J'), 7) + 1 IN (6, 7);";
//private static final String GET_ACT_BY_DIST="select * from act where ?";
private static final String GET_ACT_BY_MEMID_CREATE="select * from act where actmem.memID=?";
private static final String GET_ACT_BY_MEMID_JOIN="select * from act join actmem on act.actID=actmem.actID join member on actmem.memID=member.memID"
						+"where actmem.memID=?";
private static final String GET_ACT_BY_MEMID_TRACK="select * from act join actmem on act.actID=actTraMem.actID join member on actTraMem.memID=member.memID"
						+"where actTraMem.memID=?";
private static final String GET_ACT_BY_MEMID_FRIEND="select * from act join actmem on act.actID=actmem.actID join Friends on actmem.memID=Friends.frimem1 where Friends.FriendType='好友'";

private static final String GET_ACT_BY_CLUBID="select * from act join actclub on act.actID=actClub.actID join club on actClub.ClubID=Club.ClubID"
+"where actClub (actClub.ClubID!=0) AND actClub.ClubID=?";



	@Override

	public void insert(Act_VO Act_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, Act_VO.getMemID());
			pstmt.setTimestamp(2, Act_VO.getActCreateDate());
			pstmt.setString(3, Act_VO.getActName());
			pstmt.setInt(4, Act_VO.getActStatus());
			pstmt.setInt(5, Act_VO.getActPriID());
			pstmt.setTimestamp(6, Act_VO.getActStartDate());
			pstmt.setTimestamp(7, Act_VO.getActEndDate());
			pstmt.setTimestamp(8, Act_VO.getActSignStartDate());
			pstmt.setTimestamp(9, Act_VO.getActSignEndDate());
			pstmt.setInt(10, Act_VO.getActTimeTypeID());
			pstmt.setString(11, Act_VO.getActTimeTypeCnt());
			pstmt.setInt(12, Act_VO.getActMemMax());
			pstmt.setInt(13, Act_VO.getActMemMin());
			pstmt.setBytes(14, Act_VO.getActIMG());
			pstmt.setString(15, Act_VO.getActContent());
			pstmt.setDouble(16, Act_VO.getActLong());
			pstmt.setDouble(17, Act_VO.getActLat());
			pstmt.setInt(18, Act_VO.getActPost());
			pstmt.setString(19, Act_VO.getActLocName());
			pstmt.setString(20, Act_VO.getActAdr());

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
	public void update(Act_VO Act_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt.setString(1, Act_VO.getActName());
			pstmt.setInt(2, Act_VO.getActPriID());
			pstmt.setTimestamp(3, Act_VO.getActStartDate());
			pstmt.setTimestamp(4, Act_VO.getActEndDate());
			pstmt.setTimestamp(5, Act_VO.getActSignStartDate());
			pstmt.setTimestamp(6, Act_VO.getActSignEndDate());
			pstmt.setInt(7, Act_VO.getActTimeTypeID());
			pstmt.setString(8, Act_VO.getActTimeTypeCnt());
			pstmt.setInt(9, Act_VO.getActMemMax());
			pstmt.setInt(10, Act_VO.getActMemMin());
			pstmt.setBytes(11, Act_VO.getActIMG());
			pstmt.setString(12, Act_VO.getActContent());
			pstmt.setString(13, Act_VO.getActAdr());
			pstmt.setInt(14, Act_VO.getActID());

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
	public Act_VO getOne(Integer actID) {
		Act_VO Act_VO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setInt(1, actID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Act_VO = new Act_VO();
					Act_VO.setMemName(rs.getString("memName"));					
					Act_VO.setActID(rs.getInt("actID"));
					Act_VO.setMemID(rs.getInt("memID"));
					Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
					Act_VO.setActName(rs.getString("actName"));
					Act_VO.setActStatus(rs.getInt("actStatus"));
					Act_VO.setActPriID(rs.getInt("actPriID"));
					Act_VO.setActStartDate(rs.getTimestamp("actStartDate"));
					Act_VO.setActEndDate(rs.getTimestamp("actEndDate"));
					Act_VO.setActSignStartDate(rs.getTimestamp("actSignStartDate"));
					Act_VO.setActSignEndDate(rs.getTimestamp("actSignEndDate"));
					Act_VO.setActTimeTypeID(rs.getInt("actTimeTypeID"));
					Act_VO.setActTimeTypeCnt(rs.getString("actTimeTypeCnt"));
					Act_VO.setActMemMax(rs.getInt("actMemMax"));
					Act_VO.setActMemMin(rs.getInt("actMemMin"));
					Act_VO.setActIMG(rs.getBytes("actIMG"));
					Act_VO.setActContent(rs.getString("actContent"));
					Act_VO.setActIsHot(rs.getInt("actIsHot"));
					Act_VO.setActLong(rs.getDouble("actLong"));
					Act_VO.setActLat(rs.getDouble("actLat"));
					Act_VO.setActPost(rs.getInt("actPost"));
					Act_VO.setActLocName(rs.getString("actLocName"));
					Act_VO.setActAdr(rs.getString("actAdr"));
				};

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
			return Act_VO;
		}


	@Override
	public List<Act_VO> getAll() {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setMemName(rs.getString("memName"));					
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActStatus(rs.getInt("actStatus"));
				Act_VO.setActPriID(rs.getInt("actPriID"));
				Act_VO.setActStartDate(rs.getTimestamp("actStartDate"));
				Act_VO.setActEndDate(rs.getTimestamp("actEndDate"));
				Act_VO.setActSignStartDate(rs.getTimestamp("actSignStartDate"));
				Act_VO.setActSignEndDate(rs.getTimestamp("actSignEndDate"));
				Act_VO.setActTimeTypeID(rs.getInt("actTimeTypeID"));
				Act_VO.setActTimeTypeCnt(rs.getString("actTimeTypeCnt"));
				Act_VO.setActMemMax(rs.getInt("actMemMax"));
				Act_VO.setActMemMin(rs.getInt("actMemMin"));
				Act_VO.setActIMG(rs.getBytes("actIMG"));
				Act_VO.setActContent(rs.getString("actContent"));
				Act_VO.setActIsHot(rs.getInt("actIsHot"));
				Act_VO.setActLong(rs.getDouble("actLong"));
				Act_VO.setActLat(rs.getDouble("actLat"));
				Act_VO.setActPost(rs.getInt("actPost"));
				Act_VO.setActLocName(rs.getString("actLocName"));
				Act_VO.setActAdr(rs.getString("actAdr"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByPOIID(Integer POIID) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_POIID);
			pstmt.setInt(1, POIID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByDate(Timestamp actDate) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_DATE);
			pstmt.setTimestamp(1, actDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByWks(Timestamp actDate) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
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
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByMemIDJoin(Integer memID) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_JOIN);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByMemIDCreate(Integer memID) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_CREATE);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByMemIDFriend(Integer memID) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_FRIEND);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByMemIDTrack(Integer memID) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_TRACK);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
	public List<Act_VO> getActByClub(Integer clubID) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		Act_VO Act_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACT_BY_CLUBID);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
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
