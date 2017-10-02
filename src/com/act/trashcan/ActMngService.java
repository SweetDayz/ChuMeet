package com.act.trashcan;
import java.util.List;

import com.act.trashcan.ActMngVO;

public class ActMngService {
		private ActMngDAO_interface dao;

		public ActMngService() {
			dao = new ActMngDAO();
		}

		public List<ActMngVO> getAll() {
				return dao.getAll();
		}
}