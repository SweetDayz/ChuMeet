<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.act.act.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gen.tool.actCodeTrans"%>
<%
    ActMngService actMngSvc = new ActMngService();
    List<ActMngVO> list = actMngSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%
    Act_Service actS = new Act_Service();
    List<Act_VO> actlist = (List<Act_VO>)actS.getAll();
    pageContext.setAttribute("actlist",actlist);
%>
<!DOCTYPE html>
<html>
<!-- Head BEGIN -->
<head>
	<title>所有活動資料</title><!-- 共用Header -->
	<!-- 共用Header -->
    <meta charset="utf-8">
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/apple-icon.png" rel="apple-touch-icon" sizes="76x76">
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/favicon.png" rel="icon" type="image/png">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <title>ChuMeet-Manage</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta content="width=device-width" name="viewport"><!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/bootstrap.min.css" rel="stylesheet"><!--  Material Dashboard CSS    -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/material-dashboard.css" rel="stylesheet"><!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/demo.css" rel="stylesheet"><!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons" rel="stylesheet" type="text/css">

	<style>
	      .table, .table th, .table td, .table select{
	          text-align: center;
	          vertical-align: middle;
	          line-height: 1em;
	       
	      }
	      .table>tbody>tr>td{
	          line-height: 1em;
	          padding:.3em;
	      }
	      .table>thead>tr>th{
	          padding: .5em;
	          font-weight: bold;
	          line-height: 1.5em;
	       }
	      .radio i{
	          color:red;
	      }

	input[type=checkbox]{
	   height: 0;
	   width: 0;
	   visibility: hidden;
	}

	.cclabel {
	   cursor: pointer;
	   text-indent: -9999px;
	   width: 40px;
	   height: 20px;
	   background: grey;
	   display: block;
	   border-radius: 20px;
	   position: relative;
	   margin-left: auto;
	   margin-right: auto;
	}

	.cclabel:after {
	   content: '';
	   position: absolute;
	   top: 0px;
	   left: 2px;
	   width: 20px;
	   height: 20px;
	   background: #fff;
	   border-radius: 20px;
	   transition: 0.3s;
	}

	input:checked + .cclabel {
	   background: #bada55;
	}

	input:checked + .cclabel:after {
	   left: calc(100% - 2px);
	   transform: translateX(-100%);
	}

	.cclabel:active:after {
	   width: 20px;
	}
	      
	</style>
</head>
<body>
	<div class="wrapper">
		<div class="sidebar" data-color="purple" data-image="../assets/img/sidebar-1.jpg">
            <!--
            Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

            Tip 2: you can also add an image using data-image tag
        -->
            <div class="logo text-center">
                <img src="../assets/img/ChuMeet_logo_2.png"> <strong>管理平台</strong>
            </div>
            <div class="sidebar-wrapper">
                <ul class="nav">
                    <li>
                        <a href="index.html"><i class="material-icons">home</i>
                        <p><strong>首頁</strong></p></a>
                    </li>
                    <li>
                        <a href="admin_admin.html"><i class="material-icons">person</i>
                        <p><strong>管理員管理</strong></p></a>
					</li>
                    <li>
                        <a href="admin_info.html"><i class="material-icons">person</i>
                        <p><strong>網站管理</strong></p></a>
                    </li>
                    <li>
                        <a href="admin_appAnn.html"><i class="material-icons">phonelink_ring</i>
                        <p><strong>APP推播管理</strong></p></a>
                    </li>
                    <li>
                        <a href="admin_member.html"><i class="material-icons text-gray">face</i>
                        <p><strong>會員管理</strong></p></a>
                    </li>
                    <li>
                        <a href="admin_reward.html"><i class="material-icons text-gray">face</i>
                        <p><strong>成就與獎賞管理</strong></p></a>
                    </li>
                    <li>
                        <a href="admin_tickets.html"><i class="material-icons">library_books</i>
                        <p><strong>檢舉管理</strong></p></a>
                    </li>

                    <li>
                        <a href="admin_POI.html"><i class="material-icons">location_on</i>
                        <p><strong>活動社團分類管理</strong></p></a>
                    </li>
                    <li class="active">
                        <a href="admin_act.html"><i class="material-icons text-gray">directions_bike</i>
                        <p><strong>活動管理</strong></p></a>
                    </li>
                    <li>
                        <a href="admin_club.html"><i class="material-icons text-gray">wc</i>
                        <p><strong>社團資料管理</strong></p></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="main-panel">
            <nav class="navbar navbar-transparent">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button class="navbar-toggle" data-toggle="collapse" type="button"><span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Material Dashboard</a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#pablo"><i class="material-icons">dashboard</i>
                                <p class="hidden-lg hidden-md">Dashboard</p></a>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="material-icons">notifications</i> <span class="notification">5</span>
                                <p class="hidden-lg hidden-md">Notifications</p></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="#">Mike John responded to your email</a>
                                    </li>
                                    <li>
                                        <a href="#">You have 5 new tasks</a>
                                    </li>
                                    <li>
                                        <a href="#">You're now friend with Andrew</a>
                                    </li>
                                    <li>
                                        <a href="#">Another Notification</a>
                                    </li>
                                    <li>
                                        <a href="#">Another One</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#pablo"><i class="material-icons">person</i>
                                <p class="hidden-lg hidden-md">Profile</p></a>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group is-empty">
                                <input class="form-control" placeholder="Search" type="text"> <span class="material-input"></span>
                            </div><button class="btn btn-white btn-round btn-just-icon" type="submit"><i class="material-icons">search</i>
                            <div class="ripple-container"></div></button>
                        </form>
                    </div>
                </div>
            </nav>


          <!--/////////////CONTENT///////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            
            
            <div class="container">
    			<h2><strong>活動管理</strong></h2>
    			<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
						</font>
					</c:if>
					
					<ul class="nav nav-tabs">
					  <li class="active"><a data-toggle="tab" href="#home">活動列表</a></li>
					  <li><a data-toggle="tab" href="#menu1">Menu 1</a></li>
					  <li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
					</ul>

					<div class="tab-content">
					  <div id="home" class="tab-pane fade in active">
						<h3>活動列表</h3>
						
						<table class="table  table-hover">
							<thead>
								<tr class="bg-danger">
									<th class="col-md-1">代碼</th>
									<th class="col-md-1">舉辦人</th>
									<th class="col-md-1">建立時間</th>
									<th class="col-md-4">活動名稱</th>
									<th class="col-md-1">狀態</th>
									<th class="col-md-1">設定</th>
									<th class="col-md-1">HOT</th>
									<th class="col-md-1">地區</th>
									<th class="col-md-1">詳細</th>
								</tr>
							</thead>
							<tbody>
							<jsp:useBean id="act" scope="page" class="com.gen.tool.actCodeTrans"/>
								<%@ include file="act/listAllAct.file" %> 
								<c:forEach var="actVO" items="${actlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr align='center' valign='middle'>
										<td>${actVO.actID}</td>
										<td>xxxxx</td>
										<td>  <fmt:formatDate  pattern="yyyy-MM-dd" value="${actVO.actCreateDate}"/> </td>
										<td>${actVO.actName}</td>
										<td>${act.actStattoString(actVO.actStatus)}</td>
										<td>
												<div class="radio">
												  <label><input type="radio" name="radio_${actVO.actID}" value="0" checked><i class="fa fa-check-square" aria-hidden="true"></i></label>
												</div>
												<div class="radio">
												  <label><input type="radio" name="radio_${actVO.actID}" value="4"><i class="fa fa-ban" aria-hidden="true"></i></label>
												</div>
												<div class="radio">
												  <label><input type="radio" name="radio_${actVO.actID}" value="5"><i class="fa fa-question-circle" aria-hidden="true"></i></label>
												</div>

												</td>
										<td>
    
											<input type="checkbox"  name="ck_${actVO.actID} " id="switch${actVO.actID} " /><label class="cclabel" for="switch${actVO.actID} ">推</label>
										</td>
										<td>${actVO.actPost}</td>
										<td><a href="#" class="btn btn-info btn-sm">詳情</a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						
						
						<button class="btn btn-success">儲存變更</button>
						<%@ include file="act/page.file" %>
					  </div>
					  <div id="menu1" class="tab-pane fade">
						<h3>Menu 1</h3>
						<p>Some content in menu 1.</p>
					  </div>
					  <div id="menu2" class="tab-pane fade">
						<h3>Menu 2</h3>
						<p>Some content in menu 2.</p>
					  </div>
					</div>
           
            </div>
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
        </div>
    </div>




<!--   Core JS Files   -->
	<script src="<%=request.getContextPath() %>/HTML/BackEnd/assets/js/jquery-3.1.0.min.js" type="text/javascript">
	</script> 
	<script src="<%=request.getContextPath() %>/HTML/BackEnd/assets/js/bootstrap.min.js" type="text/javascript">
	</script> 
	<script src="<%=request.getContextPath() %>/HTML/BackEnd/assets/js/material.min.js" type="text/javascript">
	</script> <!--  Charts Plugin -->
	<script src="<%=request.getContextPath() %>/HTML/BackEnd/assets/js/chartist.min.js">
	</script> <!--  Notifications Plugin    -->
	<script src="<%=request.getContextPath() %>/HTML/BackEnd/assets/js/bootstrap-notify.js">
	</script> <!--  Google Maps Plugin    -->
	<script src="https://maps.googleapis.com/maps/api/js" type="text/javascript">
	</script> <!-- Material Dashboard javascript methods -->
	<script src="<%=request.getContextPath() %>/HTML/BackEnd/assets/js/material-dashboard.js">
	</script> <!-- Material Dashboard DEMO methods, don't include it in your project! -->
	<script src="<%=request.getContextPath() %>/HTML/BackEnd//assets/js/demo.js">
	</script> 
	<script type="text/javascript">
	$(document).ready(function() {

	   // Javascript method's body can be found in assets/js/demos.js
	   demo.initDashboardPageCharts();

	});
	</script>
</body>
</html>