package com.act.act.model;

import java.util.HashSet;
import java.util.Set;

import com.act.actMem.model.ActMemVO;
import com.gen.tool.actCodeTrans;

public class ActFiestaVO implements java.io.Serializable  {

	private String memName;
	private String actCnt;
	private Act_VO actVO2;
	private ActVO actVO;
	
	public ActFiestaVO() {
	}
	

	public ActFiestaVO(Act_VO actVO2) {
		this.actVO2 = actVO2;
		this.memName = actCodeTrans.whoRU(actVO2.getMemID());
		this.actCnt = actCodeTrans.delHTMLTag(actVO2.getActContent());

	}
	
	public ActFiestaVO(ActVO actVO) {
		this.actVO = actVO;
		this.memName = actCodeTrans.whoRU(actVO.getMemID());
		this.actCnt = actCodeTrans.delHTMLTag(actVO.getActContent());

	}
	
	public String getMemName() {
		return memName;
	}

	public String getActCnt() {
		return actCnt;
	}



	public Act_VO getActVO2() {
		return actVO2;
	}


	public void setActVO2(Act_VO actVO2) {
		this.actVO2 = actVO2;
	}


	public ActVO getActVO() {
		return actVO;
	}


	public void setActVO(ActVO actVO) {
		this.actVO = actVO;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}

	public void setActCnt(String actCnt) {
		this.actCnt = actCnt;
	}



}
