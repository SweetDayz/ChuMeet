package com.act.model;

import java.sql.Timestamp;

public class ActTraMemVO {
	private Integer actID;
	private String memID;
	private Timestamp actTrackDate;
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
	public Timestamp getActTrackDate() {
		return actTrackDate;
	}
	public void setActTrackDate(Timestamp actTrackDate) {
		this.actTrackDate = actTrackDate;
	}

}
