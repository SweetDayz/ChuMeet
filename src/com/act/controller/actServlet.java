package com.act.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.gen.tool.tools;


public class actServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
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
				ActVO actVO = new ActVO();
				actVO.setActID(actID);
				actVO.setMemID(memID);
				actVO.setActCreateDate(actCreateDate);
				actVO.setActName(actName);
				actVO.setActStatID(actStatID);
				actVO.setActTimeID(actTimeID);
				actVO.setActPriID(actPriID);
				actVO.setActLocID(actLocID);
				actVO.setActStartDate(actStartDate);
				actVO.setActEndDate(actEndDate);
				actVO.setActSignStartDate(actSignStartDate);
				actVO.setActSignEndDate(actSignEndDate);
				actVO.setActITVType(actITVType);
				actVO.setActMemMax(actMemMax);
				actVO.setActMemMin(actMemMin);
				actVO.setActImg(actImg);
				actVO.setActContent(actContent);
				actVO.setActLong(actLong);
				actVO.setActLat(actLat);
				actVO.setActLocName(actLocName);
				actVO.setActAdr(actAdr);

				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/actStart.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActService actSrv = new ActService();
				actSrv.insert(actVO);
				
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
	}
}