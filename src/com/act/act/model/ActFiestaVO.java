package com.act.act.model;

import java.util.Set;

import com.act.actMem.model.ActMemVO;
import com.gen.tool.actCodeTrans;

public class ActFiestaVO implements java.io.Serializable  {

	private String memName;
	private String actCnt;
	private Act_VO actVO;
	private Set<Integer> memPackJ;
	private Set<Integer> memPackT;
	private Set<Integer> memPackS;
	private Integer memCNow=0;
	
	public ActFiestaVO() {
	}
	

	public ActFiestaVO(Act_VO actVO) {
		this.actVO = actVO;
		this.memName = actCodeTrans.whoRU(actVO.getMemID());
		this.actCnt = actCodeTrans.delHTMLTag(actVO.getActContent());
		for (ActMemVO am : actVO.getActMems()) {
			if(am.getActMemStatus()==2) {
				this.memPackJ.add(am.getMemberHVO().getMemID());
			} else if (am.getActMemStatus()==5) {
				this.memPackT.add(am.getMemberHVO().getMemID());
			} else if (am.getActStar()>=0) {
				this.memPackS.add(am.getMemberHVO().getMemID());
			} 
		}
		if(actVO.getActType()==1) {
			this.memCNow=memPackJ.size()+1;
		}else if(memPackJ==null || memPackJ.size()==0) {
			this.memCNow=0;
		}else {
			this.memCNow=memPackJ.size();
		}
	}
	
	
	public String getMemName() {
		return memName;
	}

	public String getActCnt() {
		return actCnt;
	}

	public Act_VO getActVO() {
		return actVO;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public void setActCnt(String actCnt) {
		this.actCnt = actCnt;
	}

	public void setActVO(Act_VO actVO) {
		this.actVO = actVO;
	}


	public Set<Integer> getMemPackJ() {
		return memPackJ;
	}


	public Set<Integer> getMemPackT() {
		return memPackT;
	}


	public Set<Integer> getMemPackS() {
		return memPackS;
	}


	public void setMemPackJ(Set<Integer> memPackJ) {
		this.memPackJ = memPackJ;
	}


	public void setMemPackT(Set<Integer> memPackT) {
		this.memPackT = memPackT;
	}


	public void setMemPackS(Set<Integer> memPackS) {
		this.memPackS = memPackS;
	}


	public Integer getMemCNow() {
		return memCNow;
	}


	public void setMemCNow(Integer memCNow) {
		this.memCNow = memCNow;
	}
	
}
