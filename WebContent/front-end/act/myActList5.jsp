<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="java.util.*"%>
<%
List<ActFiestaVO> list=null;
List<ActFiestaVO> list1=null;
List<ActFiestaVO> list2=null;
List<ActFiestaVO> list5=null;
		
ActMemService amS=new ActMemService();
		List<ActMemVO> am1s=amS.myActList1(1);
		List<ActMemVO> am2s=amS.myActList2(1);
		List<ActMemVO> am5s=amS.myActList5(1);
		for (ActMemVO amv:am1s){
			ActFiestaVO afVO = new ActFiestaVO(amv.getActVO());
			list.add(afVO);
		}
		for (ActMemVO amv:am2s){
			ActFiestaVO afVO = new ActFiestaVO(amv.getActVO());
			list2.add(afVO);
		}
		for (ActMemVO amv:am5s){
			ActFiestaVO afVO = new ActFiestaVO(amv.getActVO());
			list5.add(afVO);
		}
		
		pageContext.setAttribute("list", list);

	
%>
<!-- @@@@@@@@@@@@@@@@@@@@@@@@@ -->
<c:set var="memNow" value="1"  scope="session"/>				
<!-- @@@@@@@@@@@@@@@@@@@@@@@@@ -->

<c:set var="myActList1" value="${ActMemService.actMS.getMyActs1(memNow)}" scope="session" />
<c:set var="myActList2" value="${ActMemService.actMS.getMyActs2(memNow)}" scope="session" />
<c:set var="myActList5" value="${ActMemService.actMS.getMyActs5(memNow)}" scope="session" />

<html>
<!-- Head BEGIN -->
<head>
	<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
	<!-- 共用Header -->
  <!--  my styles  -->
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css" rel="stylesheet">
  
</head>
<!-- Head END -->

<!-- Body BEGIN -->
<body class="chumeet">
<!-- userHeader Start -->
  <c:import url="/front-end/userHeader.jsp">
</c:import>
<!-- userHeader Start -->
  <!-- Header Start -->
  <c:import url="/front-end/header.jsp">
</c:import>
  <!-- Header END -->
	<!--主頁面要修改的都在這下面-->
<div class="main">
		<jsp:useBean id="toolman" scope="session" class="com.gen.tool.tools"/> 
		<jsp:useBean id="transman" scope="session" class="com.gen.tool.actCodeTrans"/>

<!--     end of top contain-->
      <div class="container">
<!--      start of top top row (with select)-->
        <div class="row">
        <div class="col-md-3">
			<div id="bread">
				<ul class="breadcrumb">
					<li><a href="<%=request.getContextPath()%>/index.html">ChuMeet!</a></li>
					<li><a href="<%=request.getContextPath()%>/front-end/act/actList.jsp">活動列表</a></li>
					<li class="active">揪咪推薦</li>
				</ul>
			</div>
              </div>
<!--        <div class="col-md-9">-->
        
        <!--    start of search group 	-->
<!-- BEGIN TOP SEARCH -->
   <!--					  <div class="input-group">
							<span class="input-group-btn">
								 <button class="btn btn-primary btnSearch" onClick="searchStart();">
									<i class="fa fa-search" aria-hidden="true"></i>
								</button>
							</span>							<input type="text" placeholder="Search" id="searchInput" class="form-control">

					  </div>-->
<!-- END TOP SEARCH -->
<!--	</div>-->

<!--end of search group-->
       

        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
      
<!-- BEGIN CONTENT -->
          <div class="col-md-12 col-sm-12">
           <div class="row">
           	<div class="col-md-8">

           	  <h1>我的活動</h1>
           	  
        </div>
       <div class="col-md-4 padding-top-10">
        		<div class="actFilter pull-right">
					<select>
						<option>依距離排列</option>
						<option selected>依時間排列</option>
<!-- 						<option>依人數排列</option> -->
<!-- 						<option>依熱門度排列</option> -->
					</select>
				</div>
       </div> 
           </div>
          
            <div class="content-page">
              <div class="row">
              
              
              
                <div class="col-md-3 col-sm-3">
                  <ul class="tabbable actl-tabbable">
                   	<li><a href="actStart.jsp" data-toggle="tab">開個揪揪團</a></li>
                    <li data-toggle="collapse" data-target="#myAct">
                           <a href="#tab_1" data-toggle="tab">我的活動 <span class="arrow"></span></a></li>
                            <ul class="sub-menu" id="myAct">
								<li><a href="<%=request.getContextPath()%>/front-end/act/myActList2.jsp">參加中</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/act/myActList1.jsp">我舉辦的活動</a></li>
								<li class="active"><a href="<%=request.getContextPath()%>/front-end/act/myActList5.jsp">追蹤中</a></li>
								<li><a href="#">社團活動</a></li>
								<li><a href="#">好友活動</a></li>
							</ul>

                    <li><a href="<%=request.getContextPath()%>/front-end/act/actList.jsp" >揪咪推薦</a></li>

                    <li><a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryWks">周末特調</a></li>
                    <li data-toggle="collapse" data-target="#actPOI" class="collapsed">
                         <a href="#tab_2" data-toggle="tab">活動分類 <span class="arrow"></span></a></li>
                          <ul class="sub-menu collapse"> id="actPOI">
								  <li>
							<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=9">運動</a></li>
								  <li>
                    		<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=1">音樂</a></li>
								  <li>
                    		<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=2">戲劇</a></li>
								  <li>
                    		<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=3">舞蹈</a></li>
							  <li>
                    		<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=6">展覽</a></li>
								  <li>
                    		<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=7">講座</a></li>
							  <li>
                    		<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=8">電影</a></li>
								  <li>
                    		<a href="<%=request.getContextPath()%>/front-end/act/actPOIs.jsp">其他</a></li>
							</ul>
					<li><a href="#tab_2" data-toggle="tab">揪揪地圖</a></li>
				</ul>
                              
                                    <!-- BEGIN RECENT NEWS -->                            
                  <p />
                  <h3>注目活動</h3>
                  <div class="recent-news margin-bottom-10">
                   
                   
<!--                  card start -->
                    <div class="wow fadeInUp">
                        <div class="card">
							<img class="card-img-top cardImg" src="act_assets/img/eventSamples/Cap-Commandos.jpg" alt="Card image cap">
							<div class="card-block">
							  <h4 class="card-title">咆嘯突擊隊</h4>
							  <small><i class="fa fa-map-marker"></i>中壢, <i class="fa fa-calendar"></i>2017/07/31 12:00</small>
							<div class="event-tags">
								<li><a href="#"><i class="fa fa-tags"></i>影視</a></li>
								<li><a href="#"><i class="fa fa-tag"></i>帥哥</a></li>
							</div>
							  <span class="card-text">The Howling Commandos is the name of several fictional groups appearing in American comic books published by Marvel Comics.</span>
							  <div class="card-text cardmore">
								  <button class="btn btn-sm btn-danger"> 詳細資訊 </button>
								</div>
							</div>
                           </div>
                      </div>
<!--                      card over-->
                   

<!--                  card start -->
                    <div class="wow fadeInUp" data-wow-duration=".3" data-wow-delay=".2s">
                        <div class="card">
							<img class="card-img-top cardImg" src="act_assets/img/eventSamples/fess.jpg" alt="Card image cap">
							<div class="card-block">
							  <h4 class="card-title">普羅米修斯</h4>
							  <small><i class="fa fa-map-marker"></i>中壢, <i class="fa fa-calendar"></i>2017/07/31 12:00</small>
							<div class="event-tags">
								<li><a href="#"><i class="fa fa-tags"></i>法鯊</a></li>
								<li><a href="#"><i class="fa fa-tag"></i>帥哥</a></li>
							</div>
							  <span class="card-text">法鯊就是帥BJ4</span>
							  <div class="card-text cardmore">
								  <button class="btn btn-sm btn-danger"> 詳細資訊 </button>
								</div>
							</div>
                           </div>
                      </div>
<!--                      card over-->
					</div>
                  <!-- END RECENT NEWS -->   
                </div>              
<!-- BEGIN LEFT SIDEBAR -->            
<div class="col-md-9 col-sm-9 event-posts">

        
  

<%  int rowsPerPage = 5;  //每頁的筆數    
    int rowNumber=0;      //總筆數
    int pageNumber=0;     //總頁數      
    int whichPage=1;      //第幾頁
    int pageIndexArray[]=null;
    int pageIndex=0; 
%>

<%  
    rowNumber=list.size();
    if (rowNumber%rowsPerPage !=0)
     pageNumber=rowNumber/rowsPerPage +1;
    else pageNumber=rowNumber/rowsPerPage;    

    pageIndexArray=new int[pageNumber]; 
    for (int i=1 ; i<=pageIndexArray.length ; i++)
    pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
%>

<%  try {
      whichPage = Integer.parseInt(request.getParameter("whichPage"));
      pageIndex=pageIndexArray[whichPage-1];
    } catch (NumberFormatException e) { //第一次執行的時候
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
         if (pageNumber>0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
         }
    } 
%>
<c:forEach var="afVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<!--      1st card          -->
	<div class="ec wow fadeInLeft">
		<div class="row">
			<div class="col-md-4 col-sm-4">

				<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=${afVO.actVO.actID}">
					<img alt="Person Of Interest" class="img-responsive img-rounded" src="<%=request.getContextPath()%>/img/showIMG?colName=actIMG&table=ACT&pk=actID&imgFrom=${afVO.actVO.actID}"">
				</a>
					<c:if test="${myActList2.contains(afVO.actVO.actID)}">
								<span class="label label-info"><i class="fa fa-star" aria-hidden="true"></i> 已參加</span>
					</c:if>
					
					<c:if test="${myActList5.contains(afVO.actVO.actID)}">
								<span class="label label-info"><i class="fa fa-star" aria-hidden="true"></i> 已追蹤</span>
					</c:if>
					
			</div>
			
			<div class="col-md-8 col-sm-8">
				<h2><a href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=${afVO.actVO.actID}">${afVO.actVO.actName}</a></h2>
				<ul class="event-info">
					<li><i class="fa fa-calendar"></i>${toolman.tsToActStr(afVO.actVO.actStartDate)}起至 ${toolman.tsToActStrOT(afVO.actVO.actEndDate)}</li>
					<li><i class="fa fa-map-marker"></i><a href="#">${transman.actPosttoString(afVO.actVO.actPost)}</a></li>
					<li><i class="fa fa-user"></i><a href="#">${afVO.memName}</a></li>
				</ul>
				<div class="ecContent"><p>${afVO.actCnt}<span>...</span></p>	</div>

				<div class="row"></div>
				<div class="event-tags col-md-8" style="margin-top: 0; verticle-align:top;">
				<c:forEach var="actpoi" items="${afVO.actVO.actPOIs}">
						<li style="font-size:14px"><a href="#"><i class="fa fa-tags"></i>${actpoi.POIVO.POINameC}</a></li>
				</c:forEach>
				</div>
				<div class="col-md-4 pull-right" style="text-align: right">
						<form action="<%=request.getContextPath()%>/front-end/act/act.do" method="get">
							<input type="hidden" class="btn mybtns" name="action" value="showOne">
							<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=${afVO.actVO.actID}"><span type="submit" class="btn btn-default" name="actID" value="1">詳細資訊 <i class="icon-angle-right"></i></span></a>
							<input type="hidden" name="actID" value=${actVO.actID}>
						  </form>
				</div>
				
			</div>
		</div>
	</div>
	<!--  end of 1st card         -->
	<hr class="event-post-sep">
</c:forEach>
                                                           
                                                            


	<hr class="event-post-sep">
	
<span>rowNumber=<%=rowNumber%></span>
<span>pageNumber=<%=pageNumber%></span>
<%if ( pageNumber > 1) {%>
   <FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">   
                  <ul class="pagination">
                    <%if (rowsPerPage<rowNumber) {%>
                    			<li
                     	            <%if(pageIndex<rowsPerPage){%>
                    	              class="disabled"
         			    	       	<%}%>		
         			    	       	><a href="/ChuMeetWebsite/front-end/act/act.do?<%if(pageContext.getAttribute("pageName").equals("ref")){}%><%else {%>action=<%=pageContext.getAttribute("pageName")%>&<%}%>whichPage=<%=whichPage-1%>"><i class="fa fa-angle-left" aria-hidden="true"></i></a></li>
                        		  <%for (int i=1; i<=pageNumber; i++){%>
						             <li <%if(i==whichPage) {%>  
						             				class="active"  
						                  <%}%>
						             >
						             <a href="/ChuMeetWebsite/front-end/act/act.do?<%if(pageContext.getAttribute("pageName").equals("ref")){}%><%else {%>action=<%=pageContext.getAttribute("pageName")%>&<%}%>whichPage=<%=i%>"><%=i%></a></li>
						        <%}%> 
                        
                 			   <li
                     	            <%if(pageIndex>=pageIndexArray[pageNumber-1]){%>
                    	              class="disabled"
         			    	       	<%}%>		
                 			   ><a href="/ChuMeetWebsite/front-end/act/act.do?<%if(pageContext.getAttribute("pageName").equals("ref")){}%><%else {%>action=<%=pageContext.getAttribute("pageName")%>&<%}%>whichPage=<%=whichPage+1%>"><i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                     	
                     <%}%>
                  </ul>           
   </FORM>
<%}%>


                </div>

                <!-- END LEFT SIDEBAR -->
        
              </div>
            </div>
          </div>
          <!-- END CONTENT -->
        </div>
        <!-- END SIDEBAR & CONTENT -->
      </div>
      </div>
<!--      end of top top row (with select)-->
</div>

<!--主頁面要修改的都在這上面-->
	
	
	
	        <input type="hidden" name="action" value="showOne">
</form>
<br><br><br>
  </div>
  </div>
  
  <!--主頁面要修改的都在這上面-->
  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->
	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->
</body>
<!-- END BODY -->
</html>