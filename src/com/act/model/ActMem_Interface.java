package com.act.model;

import java.util.HashMap;
import java.util.List;

public interface ActMem_Interface {
    public HashMap<Integer, String> whosIn(Integer actID);
    public List<Integer> amIIn(Integer actID, Integer memID);
    public List<Integer> amITracking(Integer actID, Integer memID);
    public List<Integer> amIRated(Integer actID, Integer memID);
}
