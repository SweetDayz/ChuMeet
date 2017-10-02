package com.act.act.model;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;

public class Act_VO {
	
	private Integer actID;
	private Integer memID;
	private Timestamp actCreateDate;
	private String actName;
	private Integer actStatus;
	private Integer actPriID;
	private Timestamp actStartDate;
	private Timestamp actEndDate;
	private Timestamp actSignStartDate;
	private Timestamp actSignEndDate;
	private Integer actTimeTypeID;
	private String actTimeTypeCnt;
	private Integer actMemMax;
	private Integer actMemMin;
	private byte[] actIMG;
	private String actContent;
	private Integer actIsHot;
	private Double actLong;
	private Double actLat;
	private Integer actPost;
	private String actLocName;
	private String actAdr;

	private Set<ActMemVO> actMemVO = new HashSet<ActMemVO>();
	private Set<ActPOIVO> actPOIVO = new HashSet<ActPOIVO>();
	
	public Set<ActPOIVO> getActPOIVO() {
		return actPOIVO;
	}
	public void setActPOIVO(Set<ActPOIVO> actPOIVO) {
		this.actPOIVO = actPOIVO;
	}
	public Set<ActMemVO> getActMemVO() {
		return actMemVO;
	}
	public void setActMemVO(Set<ActMemVO> actMemVO) {
		this.actMemVO = actMemVO;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Timestamp getActCreateDate() {
		return actCreateDate;
	}
	public void setActCreateDate(Timestamp actCreateDate) {
		this.actCreateDate = actCreateDate;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public Integer getActStatus() {
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	public Integer getActPriID() {
		return actPriID;
	}
	public void setActPriID(Integer actPriID) {
		this.actPriID = actPriID;
	}
	public Timestamp getActStartDate() {
		return actStartDate;
	}
	public void setActStartDate(Timestamp actStartDate) {
		this.actStartDate = actStartDate;
	}
	public Timestamp getActEndDate() {
		return actEndDate;
	}
	public void setActEndDate(Timestamp actEndDate) {
		this.actEndDate = actEndDate;
	}
	public Timestamp getActSignStartDate() {
		return actSignStartDate;
	}
	public void setActSignStartDate(Timestamp actSignStartDate) {
		this.actSignStartDate = actSignStartDate;
	}
	public Timestamp getActSignEndDate() {
		return actSignEndDate;
	}
	public void setActSignEndDate(Timestamp actSignEndDate) {
		this.actSignEndDate = actSignEndDate;
	}
	public Integer getActTimeTypeID() {
		return actTimeTypeID;
	}
	public void setActTimeTypeID(Integer actTimeTypeID) {
		this.actTimeTypeID = actTimeTypeID;
	}
	public String getActTimeTypeCnt() {
		return actTimeTypeCnt;
	}
	public void setActTimeTypeCnt(String actTimeTypeCnt) {
		this.actTimeTypeCnt = actTimeTypeCnt;
	}
	public Integer getActMemMax() {
		return actMemMax;
	}
	public void setActMemMax(Integer actMemMax) {
		this.actMemMax = actMemMax;
	}
	public Integer getActMemMin() {
		return actMemMin;
	}
	public void setActMemMin(Integer actMemMin) {
		this.actMemMin = actMemMin;
	}
	public byte[] getActIMG() {
		return actIMG;
	}
	public void setActIMG(byte[] actIMG) {
		this.actIMG = actIMG;
	}
	public String getActContent() {
		return actContent;
	}
	public void setActContent(String actContent) {
		this.actContent = actContent;
	}
	public Integer getActIsHot() {
		return actIsHot;
	}
	public void setActIsHot(Integer actIsHot) {
		this.actIsHot = actIsHot;
	}
	public Double getActLong() {
		return actLong;
	}
	public void setActLong(Double actLong) {
		this.actLong = actLong;
	}
	public Double getActLat() {
		return actLat;
	}
	public void setActLat(Double actLat) {
		this.actLat = actLat;
	}
	public Integer getActPost() {
		return actPost;
	}
	public void setActPost(Integer actPost) {
		this.actPost = actPost;
	}
	public String getActLocName() {
		return actLocName;
	}
	public void setActLocName(String actLocName) {
		this.actLocName = actLocName;
	}
	public String getActAdr() {
		return actAdr;
	}
	public void setActAdr(String actAdr) {
		this.actAdr = actAdr;
	}


	
}