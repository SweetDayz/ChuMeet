package com.act.act.model;
import java.sql.*;
import java.util.*;
import java.util.List;

import com.act.act.model.Act_VO;
public class ActFiestaService {
		private Act_interface dao;

		public ActFiestaService() {
			dao = new Act_DAO();
		}

		public List<ActFiestaVO> getAll() {
				return dao.getAll();
		}
		
	    public void insert(Act_VO Act_VO){
			dao.insert(Act_VO);
	    };
	    public void update(Act_VO Act_VO){};
	    
	    public ActFiestaVO getOne(Integer actID){
	    	return dao.getOne(actID);
		};
		
			
	    public List<ActFiestaVO> getActByCat(Integer POIID){
			return null;};
	    public List<ActFiestaVO> getActByDate(Timestamp actDate){
			return null;};
	    public List<ActFiestaVO> getActByWks(Timestamp actDate){
			return null;};
//	    public List<ActVO> getActByDist(Integer Dist);
	    public List<ActFiestaVO> getActByMemIDJoin(Integer memID){
			return null;};
	    public List<ActFiestaVO> getActByMemIDCreate(Integer memID){
			return null;};
	    public List<ActFiestaVO> getActByMemIDFriend(Integer memID){
			return null;};
	    public List<ActFiestaVO> getActByMemIDTrack(Integer memID){
			return null;};
	    public List<ActFiestaVO> getActByClub(Integer clubID){
			return null;};
		
}

	

