package com.act.controller;

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

import com.act.model.ActOneService;
import com.act.model.ActOneVO;
import com.act.model.ActOneVO;
import com.gen.tool.tools;


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
				Integer actStatID = Integer.parseInt(req.getParameter("actStatID"));
				Integer actTimeID = Integer.parseInt(req.getParameter("actTimeID"));
				Integer actPriID = Integer.parseInt(req.getParameter("actPriID"));
				Integer actLocID = Integer.parseInt(req.getParameter("actLocID"));
				Timestamp actStartDate = tools.strToTimestamp(req.getParameter("actStartDate"));
				Timestamp actEndDate = tools.strToTimestamp(req.getParameter("actEndDate"));
				Timestamp actSignStartDate = tools.strToTimestamp(req.getParameter("actSignStartDate"));
				Timestamp actSignEndDate = tools.strToTimestamp(req.getParameter("actSignEndDate"));
				Integer actITVType = Integer.parseInt(req.getParameter("actITVType"));
				Integer actMemMax = Integer.parseInt(req.getParameter("actMemMax"));
				Integer actMemMin = Integer.parseInt(req.getParameter("actMemMin"));
				byte[] actImg = req.getParameter("actImg").getBytes();
				String actContent = req.getParameter("actContent");
				Integer actIsHot = Integer.parseInt(req.getParameter("actIsHot"));
				Double actLong = Double.parseDouble(req.getParameter("actLong"));
				Double actLat = Double.parseDouble(req.getParameter("actLat"));
				String actLocName = req.getParameter("actLocName");
				String actAdr = req.getParameter("actAdr");

//為了回傳用的
				ActOneVO actOneVO = new ActOneVO();
				actOneVO.setActID(actID);
				actOneVO.setMemID(memID);
				actOneVO.setActCreateDate(actCreateDate);
				actOneVO.setActName(actName);
				actOneVO.setActStatID(actStatID);
				actOneVO.setActTimeID(actTimeID);
				actOneVO.setActPriID(actPriID);
				actOneVO.setActLocID(actLocID);
				actOneVO.setActStartDate(actStartDate);
				actOneVO.setActEndDate(actEndDate);
				actOneVO.setActSignStartDate(actSignStartDate);
				actOneVO.setActSignEndDate(actSignEndDate);
				actOneVO.setActITVType(actITVType);
				actOneVO.setActMemMax(actMemMax);
				actOneVO.setActMemMin(actMemMin);
				actOneVO.setActImg(actImg);
				actOneVO.setActContent(actContent);
				actOneVO.setActLong(actLong);
				actOneVO.setActLat(actLat);
				actOneVO.setActLocName(actLocName);
				actOneVO.setActAdr(actAdr);

				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/actStart.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActOneService actSrv = new ActOneService();
				actSrv.insert(actOneVO);
				
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
		
		if ("showOne".equals(action)) { // 來自actListjsp的請求
			System.out.println("yaaaaa");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actID=Integer.parseInt(req.getParameter("actID"));
				System.out.println(Integer.parseInt(req.getParameter("actID")));
				/***************************2.開始查詢資料*****************************************/
				ActOneService actOneSvc = new ActOneService();
				ActOneVO actOneVO = actOneSvc.getActByActID(actID);
				if (actOneVO == null) {
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
				req.setAttribute("actOneVO", actOneVO); // 資料庫取出的actOneVO物件,存入req
				System.out.println("yaaaaa2");
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