package com.act.model;
import java.sql.*;
import java.util.*;
import java.util.List;
import com.act.model.ActOneVO;
public class ActOneService {
		private ActOne_interface dao;

		public ActOneService() {
			dao = new ActOneDAO();
		}

		public List<ActOneVO> getAll() {
				return dao.getAll();
		}
		
	    public void insert(ActOneVO actVO){
			dao.insert(actVO);
	    };
	    public void update(ActOneVO actVO){};
	    
	    public ActOneVO getActByActID(Integer actID){
	    	return dao.getActByActID(actID);
		};
		
			
	    public List<ActOneVO> getActByCat(Integer POIID){
			return null;};
	    public List<ActOneVO> getActByDate(Timestamp actDate){
			return null;};
	    public List<ActOneVO> getActByWks(Timestamp actDate){
			return null;};
//	    public List<ActVO> getActByDist(Integer Dist);
	    public List<ActOneVO> getActByMemIDJoin(Integer memID){
			return null;};
	    public List<ActOneVO> getActByMemIDCreate(Integer memID){
			return null;};
	    public List<ActOneVO> getActByMemIDFriend(Integer memID){
			return null;};
	    public List<ActOneVO> getActByMemIDTrack(Integer memID){
			return null;};
	    public List<ActOneVO> getActByClub(Integer clubID){
			return null;};
		
}

	

