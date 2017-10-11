package com.act.act.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the “current session”? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */

import org.hibernate.*;

import hibernate.util.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import hibernate.util.HibernateUtil;

import javax.servlet.ServletException;

import com.act.act.model.Act_VO;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;
import com.gen.tool.actCodeTrans;
import com.gen.tool.tools;
import com.member.model.MemberHVO;
import com.poi.model.POIVO;

public class Act_DAO implements Act_interface {

	@Override
	public Integer insert(Act_VO actVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Integer actID = 0;
		try {
			session.beginTransaction();
			actID = Integer.parseInt(session.save(actVO).toString());
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		System.out.println(actID);

		return actID;
	}

	@Override
	public void update(Act_VO actVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(actVO);
			session.getTransaction().commit();
			System.out.println("OK!!!!!!!!!!");
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	
	
	
	@Override
	public Act_VO getOne(Integer actID) {
		Act_VO actVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			actVO = (Act_VO) session.get(Act_VO.class, actID);
			// Query queryF = session.createSQLQuery("select * from actmem where
			// actid=? and actmemstatus <3");
			// Set<String> actMemberSet=null;
			// for (ActPOIVO actp: actVO.getActPOIs()){
			// actp.getPOIVO().getPOINameC();
			// }

			// Query.set Query queryF = session.createSQLQuery("select * from
			// actmem where actid=? and actmemstatus <3");
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return actVO;
	}

	@Override
	public List<Act_VO> getAll() {
		List<Act_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Act_VO order by actID");
			list = (List<Act_VO>) query.list();
			List<ActFiestaVO> listf = null;
			for (Act_VO actVO : list) {
				ActFiestaVO actf = new ActFiestaVO();
				actf.setMemName(actCodeTrans.whoRU(actVO.getMemID()));
				actf.setActCnt(tools.delHTMLTag(actVO.getActContent()).trim());
				actf.setActType(actVO.getActType());
				actf.setActID(actVO.getActID());
				actf.setMemID(actVO.getMemID());
				actf.setActCreateDate(actVO.getActCreateDate());
				actf.setActName(actVO.getActName());
				actf.setActStatus(actVO.getActStatus());
				actf.setActPriID(actVO.getActPriID());
				actf.setActStartDate(actVO.getActStartDate());
				actf.setActEndDate(actVO.getActEndDate());
				actf.setActSignStartDate(actVO.getActSignStartDate());
				actf.setActSignEndDate(actVO.getActSignEndDate());
				actf.setActTimeTypeID(actVO.getActTimeTypeID());
				actf.setActTimeTypeCnt(actVO.getActTimeTypeCnt());
				actf.setActMemMax(actVO.getActMemMax());
				actf.setActMemMin(actVO.getActMemMin());
				actf.setActIMG(actVO.getActIMG());
				actf.setActContent(actVO.getActContent());
				actf.setActIsHot(actVO.getActIsHot());
				actf.setActLong(actVO.getActLong());
				actf.setActLat(actVO.getActLat());
				actf.setActPost(actVO.getActPost());
				actf.setActLocName(actVO.getActLocName());
				actf.setActAdr(actVO.getActAdr());
				actf.setActUID(actVO.getActUID());
				actf.setActShowUnit(actVO.getActShowUnit());
				actf.setActMasterUnit(actVO.getActMasterUnit());
				actf.setActWebSales(actVO.getActWebSales());
				actf.setActSourceWebName(actVO.getActSourceWebName());
				actf.setActOnSale(actVO.getActOnSale());
				actf.setActPrice(actVO.getActPrice());

				listf.add(actf);
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<Act_VO> getActByPOIID(Integer POIID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByDate(Timestamp selectDate) {
		List<Act_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			// 【參數綁定-使用?】
			Query query = session.createQuery("from Act_VO where to_char(actDate,'yyyy/mm/dd')=? order by actID");
			query.setParameter(0, selectDate);

			// 【可分頁】
			// Query query = session.createQuery("from EmpVO");
			// query.setFirstResult(0); //0,3,6,9,12...
			// query.setMaxResults(3); //假設每頁有3筆

			// 【測試查詢結果】
			// 【query.list();直接回傳List】
			list = query.list();

			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			System.out.println(ex.getMessage());
		}

		return list;
	}

	@Override
	public List<Act_VO> getActByWks(Timestamp actDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByMemIDJoin(Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByMemIDCreate(Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByMemIDFriend(Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByMemIDTrack(Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Act_VO> getActByClub(Integer clubID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ActMemVO> whosin(Integer actID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ActPOIVO> showthetags(Integer actID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		Timestamp ts = tools.nowTimestamp();
		Integer actType = 1;
		Integer memID = 1;
		Timestamp actCreateDate = ts;
		String actName = "123";
		Integer actStatus = 1;
		Integer actPriID = 1;
		Timestamp actStartDate = ts;
		Integer actTimeTypeID = 1;
		Double actLong = 12.3;
		Double actLat = 45.6;
		Integer actPost = 999;
		String actAdr = "qqq";
		
		Set<Integer> poiincome = new HashSet<>();
		poiincome.add(20);
		poiincome.add(2);
		poiincome.add(5);
		
		Act_DAO dao = new Act_DAO();
		Act_VO actVO1=new Act_VO();
		actVO1.setActType(actType);

		actVO1.setMemID(memID);
		actVO1.setActCreateDate(actCreateDate);
		actVO1.setActName(actName);
		actVO1.setActStatus(actStatus);
		actVO1.setActPriID(actPriID);
		actVO1.setActStartDate(actStartDate);

		actVO1.setActTimeTypeID(actTimeTypeID);

		actVO1.setActLong(actLong);
		actVO1.setActLat(actLat);
		actVO1.setActPost(actPost);

		actVO1.setActAdr(actAdr);
		Integer actID=dao.insert(actVO1);




		// 為了回傳用的

		Act_VO actVO2 = new Act_VO();

		Set<ActMemVO> amset = new HashSet<ActMemVO>();
		Set<ActPOIVO> apset = new HashSet<ActPOIVO>();

		ActMemVO amVO = new ActMemVO();
		MemberHVO mvo = new MemberHVO();
			mvo.setMemID(1);
		amVO.setMemberHVO(mvo);
		amVO.setActJoinDate(tools.nowTimestamp());
		amVO.setActMemStatus(1);

		for (Integer poiID : poiincome) {
			ActPOIVO apVO = new ActPOIVO();
			POIVO pv = new POIVO();
				pv.setPOIID(poiID);
			apVO.setPOIVO(pv);
			
			Act_VO actVO3 = new Act_VO();
				actVO3.setActID(actID);
			apVO.setActVO(actVO2);
			
			apset.add(apVO);
		}

		actVO2.setActPOIs(apset);
		actVO2.setActMems(amset);

		dao.update(actVO2);

		// ● 查詢-findByPrimaryKey (優秀!) (一方dept2.hbm.xml必須設為lazy="false")
		// DeptVO deptVO3 = dao.findByPrimaryKey(30);
		// System.out.print(deptVO3.getDeptno() + ",");
		// System.out.print(deptVO3.getDname() + ",");
		// System.out.print(deptVO3.getLoc());
		// System.out.println("\n-----------------");
		// Set<EmpVO> set3 = deptVO3.getEmps();
		// for (EmpVO aEmp : set3) {
		// System.out.print(aEmp.getEmpno() + ",");
		// System.out.print(aEmp.getEname() + ",");
		// System.out.print(aEmp.getJob() + ",");
		// System.out.print(aEmp.getHiredate() + ",");
		// System.out.print(aEmp.getSal() + ",");
		// System.out.print(aEmp.getComm() + ",");
		// System.out.print(aEmp.getDeptVO().getDeptno() + ",");
		// System.out.print(aEmp.getDeptVO().getDname() + ",");
		// System.out.print(aEmp.getDeptVO().getLoc());
		// System.out.println();
		// }

		// ● 查詢-getAll-1 (一方dept2.hbm.xml不用設為lazy="false",因為沒用到多方的物件)
		// List<DeptVO> list1 = dao.getAll();
		// for (DeptVO aDept : list1) {
		// System.out.print(aDept.getDeptno() + ",");
		// System.out.print(aDept.getDname() + ",");
		// System.out.print(aDept.getLoc());
		// System.out.println();
		// }

		// ● 查詢-getAll-2 (優秀!!!) (一方dept2.hbm.xml必須設為lazy="false")
		// List<Act_VO> list2 = dao.getAll();
		// for (Act_VO actvo : list2) {
		// System.out.print(actvo.getActID() + ",");
		// System.out.print(actvo.getActName() + ",");
		// System.out.print(actvo.getMemID());
		// System.out.println("\n-----------------");
		//
		// Set<ActMemVO> set2 = actvo.getActMems();
		// for (ActMemVO memv : set2) {
		// System.out.print(memv.getActMemStatus() + ",");
		// System.out.print(memv.getActJoinDate() + ",");
		// System.out.println();
		// }
		// System.out.println();
		// }

	}

}
