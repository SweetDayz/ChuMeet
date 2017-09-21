package com.act.model;

import java.sql.Timestamp;

public class ActMemVO {
	private Integer actID;
	private String memID;
	private Integer actMemStatus;
	private Timestamp actJoinDate;
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public Integer getActMemStatus() {
		return actMemStatus;
	}
	public void setActMemStatus(Integer actMemStatus) {
		this.actMemStatus = actMemStatus;
	}
	public Timestamp getActJoinDate() {
		return actJoinDate;
	}
	public void setActJoinDate(Timestamp actJoinDate) {
		this.actJoinDate = actJoinDate;
	}

}
