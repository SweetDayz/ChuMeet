package com.act.act.model;
import java.sql.*;
import java.util.*;
import java.util.List;

import com.act.act.model.Act_VO;
public class Act_Service {
		private Act_interface dao;

		public Act_Service() {
			dao = new Act_DAO();
		}

		public List<Act_VO> getAll() {
				return dao.getAll();
		}
		
	    public Integer insert(Act_VO Act_VO){
	    	return dao.insert(Act_VO);
	    };
	    
	    public void update(Act_VO Act_VO){};
	    
	    public Act_VO getOne(Integer actID){
	    	return dao.getOne(actID);
		};
		
			
	    public List<Act_VO> getActByCat(Integer POIID){
			return null;};
	    public List<Act_VO> getActByDate(Timestamp actDate){
			return null;};
	    public List<Act_VO> getActByWks(Timestamp actDate){
			return null;};
//	    public List<ActVO> getActByDist(Integer Dist);
	    public List<Act_VO> getActByMemIDJoin(Integer memID){
			return null;};
	    public List<Act_VO> getActByMemIDCreate(Integer memID){
			return null;};
	    public List<Act_VO> getActByMemIDFriend(Integer memID){
			return null;};
	    public List<Act_VO> getActByMemIDTrack(Integer memID){
			return null;};
	    public List<Act_VO> getActByClub(Integer clubID){
			return null;};
		
}

	

