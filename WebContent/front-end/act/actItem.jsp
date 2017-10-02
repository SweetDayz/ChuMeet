<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%Act_VO actVO = (Act_VO) request.getAttribute("actVO");%>
<c:set var="holder" value="${actVO.memID}" />
<c:set var="memNow" value="1"  scope="session"/>
				

<%
	ActPOIService actPOISvc = new ActPOIService();
	List<String> actpoilist = actPOISvc.getPOIByActID(1);
	pageContext.setAttribute("actpoilist",actpoilist);

	ActMemService actMemSvc = new ActMemService();
	HashMap<Integer, String> memInhs = actMemSvc.whosIn(1);
	pageContext.setAttribute("memInhs",memInhs);
	pageContext.setAttribute("memInCount",memInhs.size());
%>				

<html>
<!-- Head BEGIN -->
<head>
	<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
	<!-- 共用Header -->
  <!--  my styles  -->
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css" rel="stylesheet">

<style>
 #map {
   width: 100%;
   height: 250px;
   background-color: grey;
 }
</style>
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
<!-- BEGIN CONTENT -->
<!-- BEGIN LEFT SIDEBAR -->            
      <div class="container">
        <ul class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">ChuMeet!</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/act/actList.jsp">活動列表</a></li>
			<li class="active">${actVO.actName}</li>
        </ul>
        
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN CONTENT -->
          <div class="col-md-12 col-sm-12">

            <div class="content-page">
              <div class="row">
		<jsp:useBean id="toolman" scope="session" class="com.gen.tool.tools"/> 
                <!-- BEGIN LEFT SIDEBAR -->     
                       
                <div class="col-md-9 col-sm-9 blog-item">
                            <h1>${actVO.actName}</h1>
                  <div class="row">
						<div class="col-md-5">   <img src="<%=request.getContextPath()%>/img/showIMG?colName=actIMG&table=ACT&pk=actID&imgFrom=${actVO.actID}" class="img-responsive margin-bottom-30 img-rounded" alt=""></div>
						
                        <div class="col-md-7">
                          		<table  class="table table-hover">
                          		<tr><th class="text-danger topstat"><i class="fa fa-smile-o"></i></th><th>我的狀態</th><td><span>
            <c:choose>

							<c:when test="${memNow==holder}">活動發起人
									<c:choose>
												<c:when test = "${toolman.nowTimestamp() < actVO.actStartDate}">，等待活動開始 </c:when>
												<c:when test = "${toolman.nowTimestamp() >= actVO.actStartDate && toolman.nowTimestamp() <= actVO.actEndDate}">，活動進行中 </c:when>
												<c:when test = "${toolman.nowTimestamp() > actVO.actEndDate}">，活動已結束</c:when>
										         <c:otherwise>WAT</c:otherwise>
									</c:choose>
							</c:when>

							<c:when test="${memInhs.containsKey(memNow)}">					
								已參加
									<c:choose>
												<c:when test = "${toolman.nowTimestamp() < actVO.actStartDate}">，等待活動開始 </c:when>
												<c:when test = "${toolman.nowTimestamp() >= actVO.actStartDate && toolman.nowTimestamp() <= actVO.actEndDate}">，活動進行中 </c:when>
												<c:when test = "${toolman.nowTimestamp() > actVO.actEndDate}">，活動已結束</c:when>
										         <c:otherwise>WAT</c:otherwise>
									</c:choose>
							</c:when>
							
						<c:otherwise>
							尚未參加
									<c:choose>
												<c:when test = "${toolman.nowTimestamp() < actVO.actStartDate}">，等待活動開始 </c:when>
												<c:when test = "${toolman.nowTimestamp() >= actVO.actStartDate && toolman.nowTimestamp() <= actVO.actEndDate}">，活動進行中 </c:when>
												<c:when test = "${toolman.nowTimestamp() > actVO.actEndDate}">，活動已結束</c:when>
										         <c:otherwise>WAT</c:otherwise>
									</c:choose>
						</c:otherwise>


						</c:choose>
                          		
                          		
                          		</span></td></tr>
								<tr><th class="text-danger topstat"><i class="fa fa-user"></i></th><th>活動發起人</th><td><span>breadcan</span></td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-calendar"></i></th><th>活動時間</th><td><span> ${toolman.tsToActStr(actVO.actStartDate)}起至 ${toolman.tsToActStr(actVO.actEndDate)}</span></td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-calendar-check-o"></i></th><th>報名時間</th><td><span> ${toolman.tsToActStr(actVO.actSignStartDate)} 起至 ${toolman.tsToActStr(actVO.actSignEndDate)}</span></td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-users"></i></th><th>目前人數</th><td><span>${memInCount}</span>/<span>${actVO.actMemMax}</span></td></tr>

                          		</table>
                			<div class="event-tags">
                          		<c:forEach var="actpoilist" items="${actpoilist}">
                          		<li>
                          		<a href="#"><i class="fa fa-tags"></i>
                          			${actpoilist}
                          		</a></li>
                          		</c:forEach>
							</div>	

                        	
                        </div>
                  </div>
                     <hr class="colorgraph">
                  <h2>活動詳情</h2>
<div style="margin-left: 1em;  margin-right: 1em;">
                   <p>
                   ${actVO.actContent}
                  
                  </p>
</div>
                     <hr class="colorgraph">
                  <h2>Comments</h2>
                  <div class="comments">
                    <div class="media">                    
                      <a href="javascript:;" class="pull-left">
                      <img src="../assets/pages/img/people/img1-small.jpg" alt="" class="media-object">
                      </a>
                      <div class="media-body">
                        <h4 class="media-heading">Media heading <span>5 hours ago / <a href="javascript:;">Reply</a></span></h4>
                        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                        <!-- Nested media object -->
                        <div class="media">
                          <a href="javascript:;" class="pull-left">
                          <img src="../assets/pages/img/people/img2-small.jpg" alt="" class="media-object">
                          </a>
                          <div class="media-body">
                            <h4 class="media-heading">Media heading <span>17 hours ago / <a href="javascript:;">Reply</a></span></h4>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                          </div>
                        </div>
                        <!--end media-->                      
                        <div class="media">
                          <a href="javascript:;" class="pull-left">
                          <img src="../assets/pages/img/people/img3-small.jpg" alt="" class="media-object">
                          </a>
                          <div class="media-body">
                            <h4 class="media-heading">Media heading <span>2 days ago / <a href="javascript:;">Reply</a></span></h4>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                          </div>
                        </div>
                        <!--end media-->
                      </div>
                    </div>
                    <!--end media-->
                    <div class="media">
                      <a href="javascript:;" class="pull-left">
                      <img src="../assets/pages/img/people/img4-small.jpg" alt="" class="media-object">
                      </a>
                      <div class="media-body">
                        <h4 class="media-heading">Media heading <span>July 25,2013 / <a href="javascript:;">Reply</a></span></h4>
                        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                      </div>
                    </div>
                    <!--end media-->
                  </div>

                  <div class="post-comment padding-top-40">
                    <h3>活動留言</h3>
                    <form role="form">
                      <div class="form-group">
                        <textarea class="form-control" rows="8"></textarea>
                      </div>
                      <p><button class="btn btn-primary" type="submit">提交</button></p>
                    </form>
                  </div>                      
                </div>
                <!-- END LEFT SIDEBAR -->

                <!-- BEGIN RIGHT SIDEBAR -->            
                <div class="col-md-3 col-sm-3 blog-sidebar">
                <form action="actm.do" method="post">
					<div>
						<c:choose>
							<c:when test="${memNow==holder}">
								<button id="actMng" <c:if test="${toolman.nowTimestamp() > actVO.actEndDate}"> disabled </c:if>class="btn btn-block btn-info">管理活動</button>

							</c:when>
							
							<c:when test="${memInhs.containsKey(memNow)}">					
								<button type="submit" name="action" value="delete" <c:if test="${toolman.nowTimestamp() > actVO.actEndDate}"> disabled </c:if> class="btn btn-block btn-warning">退出活動</button>
							</c:when>
							
						<c:otherwise>
							<button type="submit" name="action" value="insert2"  <c:if test="${toolman.nowTimestamp() > actVO.actEndDate}"> disabled </c:if> class="btn btn-block btn-primary">我要參加</button>
							<button  type="submit" name="action" value="insert5" <c:if test="${toolman.nowTimestamp() > actVO.actEndDate}"> disabled </c:if> class="btn btn-block btn-success">追蹤活動</button>
						</c:otherwise>


						</c:choose>
					</div>
						<input type="hidden" name="actID" value="${actVO.actID}">
						<input type="hidden" name="requestURL"	 value="<%=request.getServletPath()%>">
					</form>
<p />
                  <!-- BEGIN map -->
                  <div class="blog-photo-stream margin-bottom-20">
                    <h2>活動地點</h2>
                        <p>${actVO.actAdr}</p>
                        <c:choose>
                        <c:when test="${actVO.actPost==999}">
                        	 <img src="<%=request.getContextPath()%>/front-end/act/act_assets/img/online.jpg" alt="" class="img-rounded">
                        </c:when>
                        <c:otherwise>
					  <div id="map"></div>
						<script>
						  function initMap() {
							var uluru = {lat: ${actVO.actLong}, lng: ${actVO.actLat}};
							var map = new google.maps.Map(document.getElementById('map'), {
							  zoom: 17,
							  center: uluru
							});
							var marker = new google.maps.Marker({
							  position: uluru,
							  map: map
							});
						  }
						</script>
						<script async defer
						src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBiHKk-5rL3ARP2SmZvtcQ5poVS97N_7A&callback=initMap">
						</script>
						</c:otherwise>
                      </c:choose>      
                  </div>
<p />
                  <!-- END BLOG PHOTOS STREAM -->
                  <!-- BEGIN BLOG PHOTOS STREAM -->
                  <div class="blog-photo-stream margin-bottom-20">
                    <h2>已參加的成員</h2>
                    <ul class="list-unstyled">
                          		<c:forEach var="memInhs" items="${memInhs}">
                          		<li>
                          		<a href="#"><img src="<%=request.getContextPath()%>/img/showIMG?colName=MEMAVATAR&table=MEMBER&pk=MEMID&imgFrom=${memInhs.getKey()}"  title="${memInhs.getValue()}">
                          		</a></li>
                          		</c:forEach>
                    </ul>                    
                  </div>
                  <!-- END BLOG PHOTOS STREAM -->

                  
                 <!-- BEGIN RECENT NEWS -->                            
                  <h2>類似的活動</h2>
                  <div class="recent-news margin-bottom-10">
                   
                   
<!--                  card start -->
                    <div class="wow fadeInUp" data-wow-duration=".3" data-wow-delay=".2s">
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
                <!-- END RIGHT SIDEBAR -->            
              </div>
            </div>
          </div>
          <!-- END CONTENT -->
        </div> </div> </div>
        <!-- END SIDEBAR & CONTENT -->

<!--主頁面要修改的都在這上面-->



  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->
	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->
  	
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.cropit.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.geocomplete.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
            Layout.init();    
            Layout.initOWL();
            Layout.initTwitter();
            Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
            Layout.initNavScrolling();

		 	});
</script>


<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
