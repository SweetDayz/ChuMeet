package com.act.model;

import java.util.HashMap;
import java.util.List;

public class ActMemService {
	private ActMem_Interface dao;

	public ActMemService() {
		dao = new ActMemDAO();
	}
	
    public HashMap<Integer, String> whosIn(Integer actID){
    	return dao.whosIn(actID);
    };
    
    public void delete(Integer actID, Integer memID){
    	dao.delete(actID, memID);
    };
	public void insert(ActMemVO actmVO){
		dao.insert(actmVO);
	}
}
