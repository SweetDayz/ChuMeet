package com.act.trashcan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.act.act.model.Act_VO;
import com.act.act.model.Act_interface;
import com.gen.tool.tools;

import oracle.sql.BLOB;


public class act_DAO_JDBC implements Act_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "a123";

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

	private static final String UPDATEBLOB = "update act set actIMG=? where actID=?";
	private static final String UPDATEMEMBLOB = "update MEMBER set MEMAVATAR=? where memID=?";
	private static final String UPDATECLOB = "update act set actContent=? where actID=?";
	
//	private static final String GET_ALL_STMT = "SELECT actID,memID,actCreateDate,actName,actStatus,actPriID,actStartDate,actEndDate,actSignStartDate,actSignEndDate,actTimeTypeID,actTimeTypeCnt,actMemMax,actMemMin,actIMG,actContent,actIsHot,actLong,actLat,actPost,actLocName,actAdr FROM act";
//	private static final String GET_ONE_STMT = "select * from act where actID=?";
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

	public void insert(Act_VO Act_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			System.out.println("new ok");
			// Handle any driver errors
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
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
	
	public void updateIMG(String path, Integer actID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATEBLOB);
				
			FileInputStream fin=new FileInputStream(path);
			Blob blobbb=con.createBlob();
			byte[] bytesss=tools.sendPicture(path);
			pstmt.setInt(2, actID);
			blobbb.setBytes(1, bytesss);
			pstmt.setBlob(1, blobbb);

			pstmt.executeUpdate();
			System.out.println("updated img");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void updateMemIMG(String path, Integer memID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATEMEMBLOB);
				
			FileInputStream fin=new FileInputStream(path);
			Blob blobbb=con.createBlob();
			byte[] bytesss=tools.sendPicture(path);
			pstmt.setInt(2, memID);
			blobbb.setBytes(1, bytesss);
			pstmt.setBlob(1, blobbb);

			pstmt.executeUpdate();
			System.out.println("updated img");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	
	public void updateCnt(String path, Integer actID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATECLOB);
				
			FileInputStream fin=new FileInputStream(path);
			Clob clobbb=con.createClob();
			String str=tools.sendInfo(path);
			pstmt.setInt(2, actID);
			clobbb.setString(1, str);
			pstmt.setClob(1, clobbb);

			pstmt.executeUpdate();
			System.out.println("updated Cnt");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
			
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
			System.out.println("updated");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setInt(1, actID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Act_VO = new Act_VO();
//					Act_VO.setMemName(rs.getString("memName"));					
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
				throw new RuntimeException("A database error occured. "	+ se.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
//				Act_VO.setMemName(rs.getString("memName"));					
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_JOIN);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActName(rs.getString("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
			}


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_CREATE);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActName(rs.getString("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
			}


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_FRIEND);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActName(rs.getString("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
			}


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACT_BY_MEMID_TRACK);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActName(rs.getString("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
			}


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACT_BY_CLUBID);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Act_VO = new Act_VO();
				Act_VO.setActID(rs.getInt("actID"));
				Act_VO.setMemID(rs.getInt("memID"));
				Act_VO.setActName(rs.getString("actName"));
				Act_VO.setActName(rs.getString("actCreateDate"));
				list.add(Act_VO); // Store the row in the list
			}


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	
	public static void main(String[] args) throws IOException {

		act_DAO_JDBC dao = new act_DAO_JDBC();

		// 新增	ok
//		Act_VO ActVOIns = new Act_VO();
//		byte[] actImg=com.gen.tool.tools.getPictureByteArray("E:\\Dropbox\\act12.jpg");
//		Timestamp ts=com.gen.tool.tools.strToTimestamp("2017-09-23 10:10:10");
//		Timestamp tsStart=com.gen.tool.tools.strToTimestamp("2017-09-24 18:00:00");
//		Timestamp tsEnd=com.gen.tool.tools.strToTimestamp("2017-09-25 18:00:00");
//		Timestamp tsSStart=com.gen.tool.tools.strToTimestamp("2017-09-22 18:00:00");
//		Timestamp tsSEnd=com.gen.tool.tools.strToTimestamp("2017-09-24 16:00:00");
//        ActVOIns.setMemID(1);
//		ActVOIns.setActCreateDate(ts);
//		ActVOIns.setActName("一起看揪咪<3");
//		ActVOIns.setActStatus(1);
//		ActVOIns.setActTimeTypeID(0);
//		ActVOIns.setActPriID(1);
//		ActVOIns.setActPost(111);
//		ActVOIns.setActStartDate(tsStart);
//		ActVOIns.setActEndDate(tsEnd);
//		ActVOIns.setActSignStartDate(tsSStart);
//		ActVOIns.setActSignEndDate(tsSEnd);
//		ActVOIns.setActTimeTypeCnt("");
//		ActVOIns.setActMemMax(5);
//		ActVOIns.setActMemMin(1);
//		ActVOIns.setActIMG(actImg);
//		ActVOIns.setActContent("123");
//		ActVOIns.setActLong(25.102364);
//		ActVOIns.setActLat(121.548502);
//		ActVOIns.setActLocName("國立故宮博物院");
//		ActVOIns.setActAdr("台北市士林區至善路二段221號");
//
//		dao.insert(ActVOIns);

		// 修改	ok
//		Act_VO ActVOUpd = new Act_VO();
//		ActVOUpd.setActName("更改測試");
//		ActVOUpd.setActPriID(1);
//		ActVOUpd.setActStartDate(tsStart);
//		ActVOUpd.setActEndDate(tsEnd);
//		ActVOUpd.setActSignStartDate(tsSStart);
//		ActVOUpd.setActSignEndDate(tsSEnd);
//		ActVOUpd.setActTimeTypeID(0);
//		ActVOUpd.setActTimeTypeCnt("");
//		ActVOUpd.setActMemMax(999);
//		ActVOUpd.setActMemMin(5);
//		ActVOUpd.setActIMG(actImg);
//		ActVOUpd.setActContent("改了辣");
//		ActVOUpd.setActAdr("我家");
//		ActVOUpd.setActID(14);
//		System.out.print(ActVOUpd.getActName());
//		dao.update(ActVOUpd);


		// UPLOAD IMG	ok
//		for (int i=1; i<14; i++){
//		String path = "E:\\\\actBLOB\\act"+i+".jpg";
//		System.out.println(path);
//		int actID=i;
//		dao.updateIMG(path, actID);
//		}
		
		// UPLOAD CLOB ok
		for (int i=1; i<6; i++){
		String path = "E:\\\\actCLOB\\act"+i+".txt";
		System.out.println(path);
		int actID=i;
		dao.updateCnt(path, actID);
		}		
		
		
		// UPLOAD mem	ok
//		for (int i=1; i<13; i++){
//		String path = "E:\\\\memBLOB\\"+i+".jpg";
//		System.out.println(path);
//		int memID=i;
//		dao.updateMemIMG(path, memID);
//		}
		// 刪除	不能刪辣
//		dao.delete(30);

		// 查詢	ok
//		Act_VO actVO = dao.getOne(5);
//		System.out.print(actVO.getActName() + ",");
//		System.out.print(actVO.getActStartDate() + ",");
//		System.out.println(actVO.getActAdr());
//		System.out.println("---------------------");

//		List<Act_VO> list = dao.getAll();
//		for (Act_VO aDept : list) {
//			System.out.print(aDept.getMemName() + ",");
//			System.out.print(aDept.getActName() + ",");
//			System.out.print(aDept.getActAdr());
//			System.out.println();
//		}

	}

	@Override
	public List<Act_VO> getActByPOIID(Integer POIID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByDate(Timestamp actDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByWks(Timestamp actDate) {
		// TODO Auto-generated method stub
		return null;
	}


	}


	

