package com.act.actPOI.model;

import java.util.List;

import com.act.act.model.Act_VO;

public class ActPOIService {

		private ActPOI_interface dao;
		private ActPOIDAO_JNDI daoJ;
		
		
		
		
		
		public ActPOIService() {
			dao = new ActPOIDAO();
		}


		
		public List<Act_VO> poix2(Integer POIID){
			return daoJ.poix2(POIID);
		}
		
	    //++POI
	    public void insert(ActPOIVO actPOIVO){
	    	
	    };
	    //--POI

		
		public List<String> getPOIByActID(Integer actID) {
			return dao.getPOIByActID(actID);
		}

		
		
		
		public  List<Act_VO> getActByPOIID(Integer POIID) {
			return dao.getActByPOIID(POIID);
		}

		public void delete(Integer actID) {
			dao.delete(actID);
		}
	}
