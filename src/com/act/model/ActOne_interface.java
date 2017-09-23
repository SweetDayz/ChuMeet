package com.act.model;

import java.sql.Timestamp;
import java.util.*;
import com.act.model.ActOneVO;

public interface ActOne_interface {
    public void insert(ActOneVO actVO);
    public void update(ActOneVO actVO);
    public ActOneVO getActByActID(Integer actID);
    public List<ActOneVO> getAll();
    public List<ActOneVO> getActByPOIID(Integer POIID);
    public List<ActOneVO> getActByDate(Timestamp actDate);
    public List<ActOneVO> getActByWks(Timestamp actDate);
//    public List<ActVO> getActByDist(Integer Dist);
    public List<ActOneVO> getActByMemIDJoin(Integer memID);
    public List<ActOneVO> getActByMemIDCreate(Integer memID);
    public List<ActOneVO> getActByMemIDFriend(Integer memID);
    public List<ActOneVO> getActByMemIDTrack(Integer memID);
    public List<ActOneVO> getActByClub(Integer clubID);
}
