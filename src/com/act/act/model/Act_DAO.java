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


public class Act_DAO implements Act_interface {

	private void saveOrUpdate(Act_VO actVO){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(actVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void insert(Act_VO actVO) {
			saveOrUpdate(actVO);
	}

	@Override
	public void update(Act_VO actVO) {
			saveOrUpdate(actVO);
	}


	@Override
	public Act_VO getOne(Integer actID) {
		Act_VO actVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			actVO = (Act_VO) session.get(Act_VO.class, actID);
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
			SQLQuery queryAdd= session.createSQLQuery("select 1 from act");
			list = (List<Act_VO>) query.list(); 
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
			
//	       【參數綁定-使用?】
			Query query = session.createQuery("from Act_VO where to_char(actDate,'yyyy/mm/dd')=? order by actID");
			query.setParameter(0, selectDate);

//        【可分頁】
//			Query query = session.createQuery("from EmpVO");
//			query.setFirstResult(0); //0,3,6,9,12...
//			query.setMaxResults(3);  //假設每頁有3筆

//		   【測試查詢結果】
//        【query.list();直接回傳List】	
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
}
