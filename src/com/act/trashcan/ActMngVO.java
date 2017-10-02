package com.act.trashcan;

import java.sql.Timestamp;

public class ActMngVO {
	private Integer actID; 
	private Integer memID;
	private String memName;
	private Timestamp actCreateDate;
	private String actName;
	private Integer actStatID;
	private String actAdr;
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
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
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
	public Integer getActStatID() {
		return actStatID;
	}
	public void setActStatID(Integer actStatID) {
		this.actStatID = actStatID;
	}
	public String getActAdr() {
		return actAdr;
	}
	public void setActAdr(String actAdr) {
		this.actAdr = actAdr;
	}
	
}
