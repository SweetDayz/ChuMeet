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

}
