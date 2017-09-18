package com.act.model;

import java.sql.Timestamp;
import java.util.*;
import com.act.model.ActVO;

public interface ActDAO_interface {
    public void insert(ActVO actVO);
    public void update(ActVO actVO);
    public ActVO getActByActID(Integer actID);
    public List<ActVO> getAll();
    public List<ActVO> getActByCat(Integer POIID);
    public List<ActVO> getActByDate(Timestamp actDate);
    public List<ActVO> getActByWks(Timestamp actDate);
//    public List<ActVO> getActByDist(Integer Dist);
    public List<ActVO> getActByMemIDJoin(Integer memID);
    public List<ActVO> getActByMemIDCreate(Integer memID);
    public List<ActVO> getActByMemIDFriend(Integer memID);
    public List<ActVO> getActByMemIDTrack(Integer memID);
    public List<ActVO> getActByClub(Integer clubID);
}
