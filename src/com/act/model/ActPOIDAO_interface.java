package com.act.model;

import java.util.List;

public interface ActPOIDAO_interface {
	//POI做標籤
    public List<String> getPOIByActID(Integer actID);
    //select by POI
    public List<ActVO> getActByPOIID(Integer POIID);
    //++POI
    public void insert(ActPOIVO actPOIVO);
    //--POI
    public void delete(Integer actID);
    
    public List<ActPOIVO> mutate(Integer actID, List<Integer> poilist);

}
