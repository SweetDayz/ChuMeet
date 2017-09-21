package com.act.model;

import java.util.List;

public interface ActPOIDAO_interface {
    public List<String> getPOIByActID(Integer actID);
    public List<ActVO> getActByPOIID(Integer POIID);
    public void insert(ActPOIVO actPOIVO);
    public void delete(Integer actID);

}
