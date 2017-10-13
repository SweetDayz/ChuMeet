package com.act.act.model;

import java.sql.*;
import java.util.*;

import com.act.act.model.Act_VO;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;

public class Act_Service implements Act_interface{
	private Act_interface dao;

	public Act_Service() {
		dao = new Act_DAO();
	}

	public List<Act_VO> getAll() {
		return dao.getAll();
	}

	public List<ActFiestaVO> getAllFromNow() {
		return dao.getAllFromNow();
	}

	public Integer insert(Act_VO actVO) {
		return dao.insert(actVO);
	};

	public Integer insert(Integer actType, Integer memID, Timestamp actCreateDate, String actName,
			Integer actStatus, Integer actPriID, Timestamp actStartDate, Timestamp actEndDate,
			Timestamp actSignStartDate, Timestamp actSignEndDate, Integer actTimeTypeID, String actTimeTypeCnt,
			Integer actMemMax, Integer actMemMin, byte[] actIMG, String actContent, Integer actIsHot, Double actLong,
			Double actLat, Integer actPost, String actLocName, String actAdr, String actUID, String actShowUnit,
			String actMasterUnit, String actWebSales, String actSourceWebName, String actOnSale, String actPrice) {
		Act_VO actVO=new Act_VO();
		actVO.setActType(actType);
		actVO.setMemID(memID);
		actVO.setActCreateDate(actCreateDate);
		actVO.setActName(actName);
		actVO.setActStatus(actStatus);
		actVO.setActPriID(actPriID);
		actVO.setActStartDate(actStartDate);
		actVO.setActEndDate(actEndDate);
		actVO.setActSignStartDate(actSignStartDate);
		actVO.setActSignEndDate(actSignEndDate);
		actVO.setActTimeTypeID(actTimeTypeID);
		actVO.setActTimeTypeCnt(actTimeTypeCnt);
		actVO.setActMemMax(actMemMax);
		actVO.setActMemMin(actMemMin);
		actVO.setActIMG(actIMG);
		actVO.setActContent(actContent);
		actVO.setActIsHot(actIsHot);
		actVO.setActLong(actLong);
		actVO.setActLat(actLat);
		actVO.setActPost(actPost);
		actVO.setActLocName(actLocName);
		actVO.setActAdr(actAdr);
		actVO.setActUID(actUID);
		actVO.setActShowUnit(actShowUnit);
		actVO.setActMasterUnit(actMasterUnit);
		actVO.setActWebSales(actWebSales);
		actVO.setActSourceWebName(actSourceWebName);
		actVO.setActOnSale(actOnSale);
		actVO.setActPrice(actPrice);
		
		return dao.insert(actVO);
	}
	
	
	
	public void update(Act_VO Act_VO) {
	};

	public ActFiestaVO getOne(Integer actID) {
		return dao.getOne(actID);
	};

	public List<ActFiestaVO> getActByDate(Timestamp actDate) {
		return dao.getActByDate(actDate);
	};

	public List<ActFiestaVO> getActByWks() {
		return dao.getActByWks();
	};


	public List<ActFiestaVO> getActByClub(Integer clubID) {
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

	@Override
	public List<ActFiestaVO> getActByPOIID(Integer POIID) {
		// TODO Auto-generated method stub
		return dao.getActByPOIID(POIID);
	};

}
