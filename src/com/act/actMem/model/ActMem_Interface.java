package com.act.actMem.model;


import java.util.List;

public interface ActMem_Interface {

    public List<ActMemVO> myActList(Integer memID);

	public void delete(Integer actID, Integer memID);
	void insert(ActMemVO actmVO);
}
