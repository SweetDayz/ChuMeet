package com.act.actMem.model;

import java.sql.Timestamp;

public class ActMemVO {
	private Integer actID;
	private Integer memID;
	private Integer actMemStatus;
	private Timestamp actJoinDate;
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
