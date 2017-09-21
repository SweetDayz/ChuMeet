package com.act.model;

import java.util.List;

public class ActPOIService {

		private ActPOIDAO_interface dao;

		public ActPOIService() {
			dao = new ActPOIDAO();
		}


	    //++POI
	    public void insert(ActPOIVO actPOIVO){
	    	
	    };
	    //--POI

		
		public List<String> getPOIByActID(Integer actID) {
			return dao.getPOIByActID(actID);
		}

		public  List<ActVO> getActByPOIID(Integer POIID) {
			return dao.getActByPOIID(POIID);
		}

		public void delete(Integer actID) {
			dao.delete(actID);
		}
	}