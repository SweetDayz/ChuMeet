package com.act.actMem.model;

import java.util.HashMap;
import java.util.List;

import com.act.trashcan.ActMemDAO_JNDI;

public class ActMemService {
	private ActMem_Interface dao;

	public ActMemService() {
		dao = new ActMemDAO_JNDI();
	}

    public void delete(Integer actID, Integer memID){
    	dao.delete(actID, memID);
    };
	public void insert(ActMemVO actmVO){
		dao.insert(actmVO);
	}
}
