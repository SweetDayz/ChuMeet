package com.act.act.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.act.model.*;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.*;
import com.gen.tool.*;
import com.member.model.MemberHVO;
import com.poi.model.POIVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// @@@@@@@@@@@@@@@
		// INSERT @@@@@@@@@
		// @@@@@@@@@@@@@@@
		System.out.println(action);
		if ("insert".equals(action)) { // 來自actStart.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {

				Timestamp ts = tools.nowTimestamp();
				Integer actType = 1;
				Integer memID = 1;
				Timestamp actCreateDate = ts;
				String actName = "空字串";
				Integer actStatus = 1;
				Integer actPriID = 1;
				Timestamp actStartDate = ts;
				Integer actTimeTypeID = 1;
				Double actLong = 0.0;
				Double actLat = 0.0;
				Integer actPost = 999;
				String actAdr = "空字串";
				Timestamp actEndDate = ts;
				String actTimeTypeCnt = "空字串";
				byte[] actIMG = null;
				String actContent = "空字串";
				String actLocName = "空字串";
				Timestamp actSignStartDate = ts;
				Timestamp actSignEndDate = ts;
				Integer actMemMax = 1;
				Integer actMemMin = 1;

				String actShowUnit = "空字串";
				String actMasterUnit = "空字串";
				String actWebSales = "空字串";
				String actSourceWebName = "空字串";
				String actOnSale = "空字串";
				String actPrice = "空字串";
				Integer actIsHot = 0;
				String actUID = "空字串";

				 actName=req.getParameter("actName");
				 tools.strToTimestamp(req.getParameter("actStartDate"));
				 tools.strToTimestamp(req.getParameter("actEndDate"));
				 actTimeTypeCnt = req.getParameter("actTimeTypeCnt");
				
				   InputStream in = req.getPart("actIMG").getInputStream();
					actIMG = new byte[in.available()];
					in.read(actIMG);
					in.close();
				 
				 actContent = req.getParameter("actContent");
				 actLong = Double.parseDouble(req.getParameter("lng"));
				 actLat = Double.parseDouble(req.getParameter("lat"));
				 actPost= Integer.parseInt(req.getParameter("postal_code"));
				 actLocName = req.getParameter("name");
				 actAdr = req.getParameter("formatted_address");

				Set<Integer> poiincome = new HashSet<>();
				poiincome.add(16);
				Act_Service actS=new Act_Service();
				Act_DAO dao = new Act_DAO();
				Act_VO actVO1 = new Act_VO();
				// Integer actID=dao.insert(actVO1);
				Integer actID2 = actS.insert(actType,  memID, actCreateDate, actName, actStatus, actPriID,
						actStartDate, actEndDate, actSignStartDate, actSignEndDate, actTimeTypeID, actTimeTypeCnt,
						actMemMax, actMemMin, actIMG, actContent, actIsHot, actLong, actLat, actPost, actLocName,
						actAdr, actUID, actShowUnit, actMasterUnit, actWebSales, actSourceWebName, actOnSale, actPrice);

				// String[] values=req.getParameter("pois").split(", ");
				// Set<String> hs = new HashSet<String>(Arrays.asList(values));
				// Set<Integer> poiincome3= new HashSet<>(hs.size());
				// hs.forEach(i -> poiincome3.add(Integer.parseInt(i)));

				// 為了回傳用的

				/*************************** 2.開始新增資料 ***************************************/



				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/

				String url = "/front-end/act/act.do?action=showOne&actID=" + actID2;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/xxxx.jsp");
//				failureView.forward(req, res);
//			}
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		// @@@@@@@@@@@@@@@@ ShowOne @@@@@@@@@@@@@@@@@@@@@
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		if ("showOne".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			Integer actID = Integer.parseInt(req.getParameter("actID"));
			System.out.println(actID);
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service actSvc = new Act_Service();
			ActFiestaVO actfVO = actSvc.getOne(actID);
			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (actfVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/error");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actfVO", actfVO); // 資料庫取出的act_VO物件,存入req
			req.setAttribute("memNow", memNow); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@

			String url = "/front-end/act/actItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		// @@@@@@@@@@@@@@@
		// Q WK WK WK WK @@@@@@@@@
		// @@@@@@@@@@@@@@@

		if ("QueryWks".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFiestaVO> actfVOs = act_Svc.getActByWks();

			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (actfVOs.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/error");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "QueryWks");
			req.setAttribute("actfVOs", actfVOs); // 資料庫取出的act_VO物件,存入req
			String url = "/front-end/act/actList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		if ("QueryPOI".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			Integer poiID = Integer.parseInt(req.getParameter("poiID"));
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFiestaVO> actfVOs = act_Svc.getActByPOIID(poiID);

			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (actfVOs.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/error");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "QueryPOI");
			req.setAttribute("poiID", poiID);
			req.setAttribute("actfVOs", actfVOs); // 資料庫取出的act_VO物件,存入req
			String url = "/front-end/act/actList.jsp?actie=QueryPOI";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	}
}