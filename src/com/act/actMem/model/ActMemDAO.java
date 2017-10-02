package com.act.actMem.model;
import org.hibernate.*;

import hibernate.util.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class ActMemDAO implements ActMem_Interface {

	private static final String GET_ALL_STMT = "from DeptVO order by deptno";

	@Override
	public void insert(ActMemVO actMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(actMemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer actID, Integer memID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActMemVO> myActList(Integer actID, Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}
}
