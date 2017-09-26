<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.act.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%Act_VO act_VO = (Act_VO) request.getAttribute("act_VO");%>
<%Integer memNow = (Integer) request.getAttribute("memNow");%>


<%
	ActPOIService actPOISvc = new ActPOIService();
	List<String> actpoilist = actPOISvc.getPOIByActID(act_VO.getActID());
	pageContext.setAttribute("actpoilist",actpoilist);
%>

<%
	ActMemService actMemSvc = new ActMemService();
	HashMap<Integer, String> memInhs = actMemSvc.whosIn(act_VO.getActID());
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
			<li class="active">${act_VO.actName}</li>
        </ul>
        
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN CONTENT -->
          <div class="col-md-12 col-sm-12">
            <h1>${act_VO.actName}</h1>
            <div class="content-page">
              <div class="row">
		<jsp:useBean id="toolman" scope="session" class="com.gen.tool.tools"/> 
                <!-- BEGIN LEFT SIDEBAR -->            
                <div class="col-md-9 col-sm-9 blog-item">
                  <div class="row">
						<div class="col-md-6">   <img src="act_assets/img/eventSamples/POI.jpg" class="img-responsive margin-bottom-30 img-rounded" alt=""></div>
                        <div class="col-md-6">
                          		<table  class="table table-hover">
                          		<tr><th class="text-danger topstat"><i class="fa fa-smile-o"></i></th><th>我的狀態</th><td><span>已參加，等待活動開始</span></td></tr>
								<tr><th class="text-danger topstat"><i class="fa fa-user"></i></th><th>活動發起人</th><td><span>${act_VO.memName}</span></td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-calendar"></i></th><th>活動時間</th><td><span> ${toolman.tsToActStr(act_VO.actStartDate)}起至 ${toolman.tsToActStr(act_VO.actEndDate)}</span> <br><span>每周五晚上8:00-9:00</span></td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-calendar-check-o"></i></th><th>報名時間</th><td><span> ${toolman.tsToActStr(act_VO.actSignStartDate)} 起至 ${toolman.tsToActStr(act_VO.actSignEndDate)}</span></td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-users"></i></th><th>目前人數</th><td><span>${memInCount}</span>/<span>${act_VO.actMemMax}</span></td></tr>

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

                             <p>哈洛·芬奇（麥可·愛默生飾）是一位深居簡出的億萬富豪，他為政府開發了一套稱作「機器」（The Machine），可偵測恐怖攻擊的大規模監控電腦系統。它可預測「有計畫或謀略策劃的犯罪」，諸如911事件之類的大型恐怖攻擊災難，並提供情報讓有關當局防範未然。它可以偵測一般人的暴力犯罪，於是芬奇將情報區分為重要（relevant）和不重要（irrelevant）兩類，只將重要的清單呈報給有關當局，而不重要清單則會在每天晚上刪除。但芬奇意識到，不重要清單的資料可制止許多犯罪活動和挽救人命。但政府沒有興趣處理不重要清單，芬奇決心要找到一種方法來阻止這些犯罪。他利用程式中的一個後門，取得一項資訊：社會安全號碼。出現這號碼的人將參與即將發生的罪行，芬奇僱用一位被推定死亡的前美國特種部隊綠扁帽隊員、前CIA探員約翰·里斯（吉姆·卡維佐飾），干預犯罪的發生和阻止有人再受傷害。里斯利用在軍方和CIA獲得的技能，保護受害者和阻止肇事者出手，他們必須設法制止犯罪的發生，他們除了讓紐約市警察局腐敗警探萊昂諾·傅斯可（凱文·查普曼飾）重回正軌外，並與警探賈絲·卡特（泰拉姬·漢森飾）合作。</p>
                  <blockquote>
                    <p>You are being watched.</p>
                    <small>Someone famous <cite title="Source Title">Source Title</cite></small>
                  </blockquote>                
                  <p>《疑犯追蹤》（Person of Interest），是美國CBS電視台製作的犯罪電視影集，由強納森·諾蘭（Jonathan Nolan）與J·J·亞柏拉罕（J. J. Abrams）共同打造出劇情架構，全五季共103集。哈洛·芬奇為政府開發了一套稱作「機器」（The Machine），可偵測恐怖攻擊的大規模監控電腦系統。它可預測「有計畫或謀略策劃的犯罪」，諸如911事件之類的大型恐怖攻擊災難，並提供情報讓有關當局防範未然。</p>

                  <p>根據CBS的消息指出，《疑犯追蹤》在得到近15年來所有劇情類試播集中最高評分[11]，而一位CBS高層的形容本劇「不常見的極具瘋狂吸引力」後，促使電視台把十年來都在星期四播放的熱門影集《CSI犯罪現場》挪到星期三，把該時段空出來給《疑犯追蹤》。[12]首播集不負所望地贏得播出時段的收視冠軍，吸引了1320萬的觀眾收看。[13]<br>
《舊金山紀事報》的執行編輯兼電視評論員大衛威根（David Wiegand）表示《疑犯追蹤》跳脫了噱頭包裝，不只是因為有非常細微地角色刻劃和劇情敘述，還因為本劇探討了後911時代觀眾的偏執感。[14]<br>
《紐約每日新聞》的評論員大衛辛克萊（David Hinckley）則給了首播集四顆星的高評價（總分五顆星），並評論兩位主角吉姆卡維佐和邁可艾默森的演出，認為卡維佐「完美地詮釋這個角色」。而給艾默森的評價是「演芬奇演的太棒了」[15]。<br>
《洛杉磯時報》的電視評論員瑪莉麥納馬拉（Mary McNamara）認為： "比起解決犯罪，阻止犯罪的概念更吸引人...劇中的監視器畫面非常酷"[1]。</p>

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
					<div>
						<button class="btn btn-block btn-primary">我要參加</button>
						<button class="btn btn-block btn-success">追蹤活動</button>
						<button class="btn btn-block btn-warning">退出活動</button>
						<button class="btn btn-block btn-info">管理活動</button>
					</div>
                  <!-- BEGIN map -->
                  <div class="blog-photo-stream margin-bottom-20">
                    <h2>活動地點</h2>
					  <div id="map"></div>
						<script>
						  function initMap() {
							var uluru = {lat: 22.970269, lng: 120.227395};
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
                            
                  </div>
                  <!-- END BLOG PHOTOS STREAM -->
                  <!-- BEGIN BLOG PHOTOS STREAM -->
                  <div class="blog-photo-stream margin-bottom-20">
                    <h2>已參加的成員</h2>
                    <ul class="list-unstyled">
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/people/img5-small.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/works/img1.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/people/img4-large.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/works/img6.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/pics/img1-large.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/pics/img2-large.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/works/img3.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/people/img2-large.jpg"></a></li>
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
