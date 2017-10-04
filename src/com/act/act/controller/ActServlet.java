package com.act.act.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.act.model.*;
import com.act.actPOI.model.*;
import com.gen.tool.*;



public class ActServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

        if ("insert".equals(action)) { // 來自actStart.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer actID = Integer.parseInt(req.getParameter("actID"));
				Integer memID = Integer.parseInt(req.getParameter("memID"));
				Timestamp actCreateDate = tools.strToTimestamp(req.getParameter("actCreateDate"));
				String actName = req.getParameter("actName");
				Integer actStatus = Integer.parseInt(req.getParameter("actStatus"));
				Integer actPriID = Integer.parseInt(req.getParameter("actPriID"));
				Timestamp actStartDate = tools.strToTimestamp(req.getParameter("actStartDate"));
				Timestamp actEndDate = tools.strToTimestamp(req.getParameter("actEndDate"));
				Timestamp actSignStartDate = tools.strToTimestamp(req.getParameter("actSignStartDate"));
				Timestamp actSignEndDate = tools.strToTimestamp(req.getParameter("actSignEndDate"));
				Integer actTimeTypeID = Integer.parseInt(req.getParameter("actTimeTypeID"));
				String actTimeTypeCnt = req.getParameter("actTimeTypeCnt");
				Integer actMemMax = Integer.parseInt(req.getParameter("actMemMax"));
				Integer actMemMin = Integer.parseInt(req.getParameter("actMemMin"));
				byte[] actIMG = req.getParameter("actImg").getBytes();
				String actContent = req.getParameter("actContent");
				Integer actIsHot = Integer.parseInt(req.getParameter("actIsHot"));
				Double actLong = Double.parseDouble(req.getParameter("actLong"));
				Double actLat = Double.parseDouble(req.getParameter("actLat"));
				Integer actPost = Integer.parseInt(req.getParameter("actPost"));
				String actLocName = req.getParameter("actLocName");
				String actAdr = req.getParameter("actAdr");



//為了回傳用的
				Act_VO Act_VO = new Act_VO();
				Act_VO.setActID(actID);
				Act_VO.setMemID(memID);
				Act_VO.setActCreateDate(actCreateDate);
				Act_VO.setActName(actName);
				Act_VO.setActStatus(actStatus);
				Act_VO.setActPriID(actPriID);
				Act_VO.setActStartDate(actStartDate);
				Act_VO.setActEndDate(actEndDate);
				Act_VO.setActSignStartDate(actSignStartDate);
				Act_VO.setActSignEndDate(actSignEndDate);
				Act_VO.setActTimeTypeID(actTimeTypeID);
				Act_VO.setActTimeTypeCnt(actTimeTypeCnt);
				Act_VO.setActMemMax(actMemMax);
				Act_VO.setActMemMin(actMemMin);
				Act_VO.setActIMG(actIMG);
				Act_VO.setActContent(actContent);
				Act_VO.setActIsHot(actIsHot);
				Act_VO.setActLong(actLong);
				Act_VO.setActLat(actLat);
				Act_VO.setActPost(actPost);
				Act_VO.setActLocName(actLocName);
				Act_VO.setActAdr(actAdr);


				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/actStart.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Act_Service actSrv = new Act_Service();
				actSrv.insert(Act_VO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
        
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@					ShowOne				@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
		if ("showOne".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actID=Integer.parseInt(req.getParameter("actID"));
				/***************************2.開始查詢資料*****************************************/
				Act_Service act_Svc = new Act_Service();
				ActPOIService actpoiSvc = new ActPOIService();
				Act_VO act_VO = act_Svc.getOne(actID);
				
				List<String> actpoilist = actpoiSvc.getPOIByActID(actID);
				
				
				Integer memNow=1;						 // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				if (act_VO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/error");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("act_VO", act_VO); // 資料庫取出的act_VO物件,存入req
				req.setAttribute("memNow", memNow); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				req.setAttribute("actpoilist", actpoilist); // poi

				String url = "/front-end/act/actItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/error2");
//				failureView.forward(req, res);
//			}
		}
		
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@					ShowFive@LIST		@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
		if ("showList5".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actID=Integer.parseInt(req.getParameter("actID"));
				/***************************2.開始查詢資料*****************************************/
				Act_Service act_Svc = new Act_Service();
				ActPOIService actpoiSvc = new ActPOIService();
				Act_VO act_VO = act_Svc.getOne(actID);
				
				List<String> actpoilist = actpoiSvc.getPOIByActID(actID);
				Integer memNow=1;						 // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				if (act_VO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/error");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("act_VO", act_VO); // 資料庫取出的act_VO物件,存入req
				System.out.println(act_VO);
				System.out.println(123);
				req.setAttribute("memNow", memNow); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				req.setAttribute("actpoilist", actpoilist); // poi

				String url = "/front-end/act/actItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/error2");
//				failureView.forward(req, res);
//			}
		}
		
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	}
}