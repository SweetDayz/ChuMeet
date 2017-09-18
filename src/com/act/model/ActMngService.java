package com.act.model;
import java.util.List;
import com.act.model.ActMngVO;

public class ActMngService {
		private ActMngDAO_interface dao;

		public ActMngService() {
			dao = new ActMngDAO();
		}

		public List<ActMngVO> getAll() {
				return dao.getAll();
		}
}