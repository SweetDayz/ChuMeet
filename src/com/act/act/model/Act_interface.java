package com.act.act.model;

import java.sql.Timestamp;
import java.util.*;

import com.act.act.model.Act_VO;

public interface Act_interface {
    public void insert(Act_VO actVO);
    public void update(Act_VO actVO);

    public List<ActFiestaVO> getAll();
    public ActFiestaVO getOne(Integer actID);
    
    public List<ActFiestaVO> getActByPOIID(Integer POIID);
    public List<ActFiestaVO> getActByDate(Timestamp actDate);
    public List<ActFiestaVO> getActByWks(Timestamp actDate);
//    public List<ActVO> getActByDist(Integer Dist);
    public List<ActFiestaVO> getActByMemIDJoin(Integer memID);
    public List<ActFiestaVO> getActByMemIDCreate(Integer memID);
    public List<ActFiestaVO> getActByMemIDFriend(Integer memID);
    public List<ActFiestaVO> getActByMemIDTrack(Integer memID);
    public List<ActFiestaVO> getActByClub(Integer clubID);
}
