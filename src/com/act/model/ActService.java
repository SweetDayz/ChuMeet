package com.act.model;
import java.sql.*;
import java.util.*;
import java.util.List;
import com.act.model.ActVO;
public class ActService {
		private ActDAO_interface dao;

		public ActService() {
			dao = new ActDAO();
		}

		public List<ActVO> getAll() {
				return dao.getAll();
		}
		
	    public void insert(ActVO actVO){
			dao.insert(actVO);
	    };
	    public void update(ActVO actVO){};
	    public ActVO getActByActID(Integer actID){
			return null;};
	    public List<ActVO> getActByCat(Integer POIID){
			return null;};
	    public List<ActVO> getActByDate(Timestamp actDate){
			return null;};
	    public List<ActVO> getActByWks(Timestamp actDate){
			return null;};
//	    public List<ActVO> getActByDist(Integer Dist);
	    public List<ActVO> getActByMemIDJoin(Integer memID){
			return null;};
	    public List<ActVO> getActByMemIDCreate(Integer memID){
			return null;};
	    public List<ActVO> getActByMemIDFriend(Integer memID){
			return null;};
	    public List<ActVO> getActByMemIDTrack(Integer memID){
			return null;};
	    public List<ActVO> getActByClub(Integer clubID){
			return null;};
		
}

	

