package com.act.act.model;

import java.sql.Timestamp;
import java.util.*;

import com.act.act.model.Act_VO;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;


public interface Act_interface {
    public void insert(Act_VO actVO);
    public void update(Act_VO actVO);

    public List<Act_VO> getAll();
    public Act_VO getOne(Integer actID);
    
    public Set<ActMemVO> whosin(Integer actID);
    public Set<ActPOIVO> showthetags(Integer actID);
        
    public List<Act_VO> getActByPOIID(Integer POIID);
    public List<Act_VO> getActByDate(Timestamp actDate);
    public List<Act_VO> getActByWks(Timestamp actDate);
//    public List<ActVO> getActByDist(Integer Dist);
    public List<Act_VO> getActByMemIDJoin(Integer memID);
    public List<Act_VO> getActByMemIDCreate(Integer memID);
    public List<Act_VO> getActByMemIDFriend(Integer memID);
    public List<Act_VO> getActByMemIDTrack(Integer memID);
    public List<Act_VO> getActByClub(Integer clubID);
}
