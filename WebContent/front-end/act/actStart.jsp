<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<!-- Head BEGIN -->
<head>
	<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
	<!-- 共用Header -->
  <!--  my styles  -->
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actStart.css" media="screen" rel="stylesheet" type="text/css">
      
    <style type="text/css" media="screen">
/*      .map_canvas { float: left; }*/
      form { width: 100%;}
      fieldset { width: 320px; margin-top: 20px}
      fieldset label { display: block; margin: 0.5em 0 0em; }
      fieldset input { width: 95%; }
    </style>
    <script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/nicEdit.js" type="text/javascript"></script>
    <script type="text/javascript">
			bkLib.onDomLoaded(function() {
			new nicEditor({fullPanel : true,iconsPath : 'act_assets/js/nicEditorIcons.gif',buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image']}).panelInstance('actCntTA');
		});
	</script>

  
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBktVb6UZUJi0f3ySJlXHz3wqWL4nMI6Us&libraries=places" async defer>
	</script>
  
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
    
    
      <div class="wd80">

      
<form action="https://www.google.com.tw/" method="get">
   
<!--     		start of row-->
<!--     		======================actName HERE-->
      		<div class="row" id="step1">
      			<div class="col-md-1"><img src="act_assets/img/start/stick-man.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 1/6</div>
      				<div class="startTitle"><h3>先幫活動取個好聽的名字吧！</h3></div>
      				<div class="warningText" id="w1">　</div>
      				<input type="text"  class="form-control" id="actName" name="actName" value="啾啾揪揪團"  onkeydown="if(event.keyCode==13)return false;">
      				<a href="#next1" class="btn btn-primary actNext toscroll" id="next1" type="button">下一步</a>
      			</div>
      			
      		</div>
      		<hr class="stratLine" id="line1"/>
<!--      		end of row-->
   		
    		<div id="step2" class="hns">
     		<!--     		start of row-->
<!--     		======================actLocID actLong actLat actLocName actAdr HERE-->
      		<div class="row">
      			<div class="col-md-1"><img src="act_assets/img/start/location.png"></div>
      			<div class="col-md-6">
      				<div class="startHead">step 2/6</div>
      				<div class="startTitle"><h3>舉辦地點在哪兒呢？</h3></div>
      				
				<div class="warningText" id="w2">　</div>
  			  
  			      <div class="input-group">
    				<input id="geocomplete" class="form-control" type="text" placeholder="請輸入地址" value="台灣桃園市中壢區TibaMe" />
					  <span class="input-group-btn">
						<button class="btn btn-info" type="button" id="find">定位</button>
					  </span>
					</div><!-- /input-group -->


          <div>
          	     <a href="#tg2" class="btn btn-primary actNext toscroll" id="next2" type="button">下一步</a>
          	
          </div>
           </div>
            <div class="col-md-5">
          <div class="map_canvas">
          	
          </div>

			<div id="tg2"></div>
      <fieldset>

        <input class="adruse" name="name" id="prename" type="hidden" value="">
        <input class="adruse" name="lat" id="prelat" type="hidden" value="">
        <input class="adruse" name="lng" id="prelong" type="hidden" value="">
        <input class="adruse" name="formatted_address" id="preadr" type="hidden" value="">
        <input class="adruse" name="postal_code" id="prepost" type="hidden" value="">
        
        <input name="actLocName" id="actLocName" type="hidden" value="">
        <input name="actLong" id="actLong" type="hidden" value="">
        <input name="actLat" id="actLat" type="hidden" value="">
        <input name="actAdr" id="actAdr" type="hidden" value="">
        <input name="actLocID" id="actLocID" type="hidden" value="">
        <input name="location" type="hidden" value="">
        <input name="location_type" type="hidden" value="">
        <input name="bounds" type="hidden" value="">
        <input name="viewport" type="hidden" value="">
        <input name="route" type="hidden" value="">
        <input name="street_number" type="hidden" value="">
        <input name="locality" type="hidden" value="">
        <input name="sublocality" type="hidden" value="">
        <input name="country" type="hidden" value="">
        <input name="country_short" type="hidden" value="">
        <input name="administrative_area_level_1" type="hidden" value="">
        <input name="place_id" type="hidden" value="">
        <input name="id" type="hidden" value="">
        <input name="reference" type="hidden" value="">
        <input name="url" type="hidden" value=""> 
        <input name="point_of_interest" type="hidden" value="">
        <input name="website" type="hidden" value="">
      </fieldset>

     				

      			</div>
      		</div>
      		<hr class="stratLine"/>
<!--      		end of row-->
     		</div>
     		<div id="step3" class="hns">
     		<!--     		start of row-->
<!--     		======================POI HERE-->
      		<div class="row">
      			<div class="col-md-1"><img src="act_assets/img/start/gift-tag.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 3/6</div>
      				<div class="startTitle"><h3>活動分類是？</h3></div>
      				<div class="warningText" id="w3">　</div>
     					   	<div class="event-tags">
								<li id="t1"><a href="#"><i class="fa fa-tag"></i>運動</a></li>
								<li id="t3"><a href="#"><i class="fa fa-tag"></i>學習</a></li>
								<li id="t4"><a href="#"><i class="fa fa-tag"></i>餐聚</a></li>
								<li id="t5"><a href="#"><i class="fa fa-tag"></i>藝文</a></li>
								<li id="t6"><a href="#"><i class="fa fa-tag"></i>電影</a></li>
								<li id="t7"><a href="#"><i class="fa fa-tag"></i>電競</a></li>
								<li id="t9"><a href="#"><i class="fa fa-tag"></i>戶外</a></li>
								<li id="t10"><a href="#"><i class="fa fa-tag"></i>寵物</a></li>
								<li id="t14"><a href="#"><i class="fa fa-tag"></i>其他</a></li>
							</div>
      					      <div class="margin-top-10">
       					      <span class="catadiv">
								<input id="sports" class="checkbox-custom" name="cata" type="checkbox">
								<label for="sports" value="1">運動</label>
							  </span>
       					      <span class="catadiv">
								<input id="learning" class="checkbox-custom" name="cata" type="checkbox">
								<label for="learning" value="3">學習</label>
							  </span>
       					      <span class="catadiv">
								<input id="eat" class="checkbox-custom" name="cata" type="checkbox">
								<label for="eat" value="4">餐聚</label>
							  </span>
       					      <span class="catadiv">
								<input id="arts" class="checkbox-custom" name="cata" type="checkbox">
								<label for="arts" value="5">藝文</label>
							  </span>
       					      <span class="catadiv">
								<input id="movie" class="checkbox-custom" name="cata" type="checkbox">
								<label for="movie" value="6">電影</label>
							  </span>
       					      <span class="catadiv">
								<input id="game" class="checkbox-custom" name="cata" type="checkbox">
								<label for="game" value="7">電競</label>
							  </span>
       					      <span class="catadiv">
								<input id="outdoors" class="checkbox-custom" name="cata" type="checkbox">
								<label for="outdoors" value="9">戶外</label>
							  </span>
       					      <span class="catadiv">
								<input id="pets" class="checkbox-custom" name="cata" type="checkbox">
								<label for="pets" value="10">寵物</label>
							  </span>
       					      <span class="catadiv">
								<input id="others" class="checkbox-custom" name="cata" type="checkbox">
								<label for="others" value="14">其他</label>
							  </span>
      					
       					
       					
     				</div>
      				<a href="#tg3" class="btn btn-primary actNext toscroll" id="next3" type="button">下一步</a>
      				<div id="tg3"></div>
      			</div>
      		</div>
      		<hr class="stratLine"/>
<!--      		end of row-->
    		</div>

    		<div id="step4" class="hns">
<!--     		======================actStartDate actEndDate actSignStartDate actSignEndDate actTimeID actITVType HERE-->
     		<!--     		start of row-->
      		<div class="row">
      			<div class="col-md-1"><img src="act_assets/img/start/cal.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 4/6</div>
      				<div class="startTitle"><h3>活動時間設定</h3></div>
      				<div class="warningText" id="w4">　</div>
      				<div>
						<table class="table">
							  <tr>
								<th class="col-md-3">活動舉辦時間</th>
								<td class="col-md-3">
									<input type="datetime-local" name="actStartDate" onkeydown="if(event.keyCode==13)return false;" value="2017-10-20T15:00:00">
								</td>
								<th class="col-md-3">活動結束時間</th>
								<td class="col-md-3">
									<input type="datetime-local" name="actEndDate" onkeydown="if(event.keyCode==13)return false;" value="2017-10-21T15:00:00">
								</td>
							  </tr>
							  <tr>
								<th>開始報名時間</th>
								<td>
									<input type="datetime-local" name="actSignStartDate" onkeydown="if(event.keyCode==13)return false;" value="2017-9-16T23:00:00">
								</td>
								<th>截止報名時間</th>
								<td>
									<input type="datetime-local" name="actSignEndDate" onkeydown="if(event.keyCode==13)return false;" value="2017-10-18T23:59:59">
								</td>
							  </tr>
							  <tr>
								<th>重複設定</th>
								 <td>
									<label class="radio-inline">
										<input type="radio" name="actITVType" class="option-input radio" value="0" id="oneTime" checked>一次
									</label>
									<label class="radio-inline">
										<input type="radio" name="actITVType" class="option-input radio" value="1" id="repTime">重複
									</label>
							
									
								</td>
								<td colspan="2">
								</td>
								<td></td>
							  </tr>
							  <session id="papprep hns">
							  <tr>
							  	<th>單位</th>
							  	<td colspan="3">

									<div style="display: inline-block; background-color: white;">
										<ul class="nav nav-tabs">
										  <li class="active"><a data-toggle="tab" href="#home">日</a></li>
										  <li><a data-toggle="tab" href="#menu1">週</a></li>
										  <li><a data-toggle="tab" href="#menu2">月</a></li>
										  <li><a data-toggle="tab" href="#menu3">年</a></li>
										</ul>

										<div class="tab-content">
										  <div id="home" class="tab-pane fade in active">
											每<select name="cal" id="cal">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
											</select>日重複
										  </div>
										  <div id="menu1" class="tab-pane fade">
				<label class="checkbox-inline"><input type="checkbox" value="">週日</label>
				<label class="checkbox-inline"><input type="checkbox" value="">週一</label>
				<label class="checkbox-inline"><input type="checkbox" value="">週二</label>
				<label class="checkbox-inline"><input type="checkbox" value="">週三</label>
				<label class="checkbox-inline"><input type="checkbox" value="">週四</label>
				<label class="checkbox-inline"><input type="checkbox" value="">週五</label>
				<label class="checkbox-inline"><input type="checkbox" value="">週六</label>

										  </div>
										  <div id="menu2" class="tab-pane fade">
											每<select name="cal3" id="cal2">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
											</select>月重複
										  </div>
										  <div id="menu3" class="tab-pane fade">
											<p>每年重覆</p>
										  </div>
										</div>
									</div>
							  		
							  		
							  		
							  	</td>
							  </tr>
							  </session>
						  </table>

      				</div>

      	 			<a href="#tg4" class="btn btn-primary actNext toscroll" id="next4" type="button">下一步</a>
      	 			<div id="tg4"></div>
      			</div>
      		</div>
      		<hr class="stratLine"/>
      		</div>
<!--      		end of row-->
   		
    		<div id="step5" class="hns">
     		<!--     		start of row-->
<!--     		======================actMemMax actMemMin actContent actImg actPriID HERE-->
      		<div class="row">
      			<div class="col-md-1"><img src="act_assets/img/start/electronic.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 5/6</div>
      				<div class="startTitle"><h3>活動詳細說明</h3></div>
      				<div class="warningText" id="w5">　</div>
      				<div>
						<table class="table table-striped">
							<tr>
								<th style="width:8em;">人數設定</th>
								<td>
							  <div class="form-inline">
							  		<span class="input-group input-group-sm pp">
									  <span class="input-group-addon">下限</span>
									  <input type="number" class="form-control" name="actMemMin" value="2">
									</span>
								  	<span class="input-group input-group-sm pp">
									  <span class="input-group-addon">上限</span>
									  <input type="number" class="form-control" name="actMemMax" value="15">
									</span>

								</div>
								</td>
							</tr>
							  <tr>
							  	<th>隱私設定</th>
							  	<td>
<!--"隱私狀態
1 公開
2 僅限社團
3.僅限限定社團(社團聯誼)
4 僅限邀請 XXXXX
5.僅限邀請(被邀請人可以邀請其他人)
6-16限定等級以上參加"
-->
							  	
							  	<div>
							  		<label class="radio-inline">
										<input type="radio" class="actPriSS option-input radio" name="actPriS" value="PUBLIC">公開</label>
									<label class="radio-inline">
										<input type="radio" class="actPriSS option-input radio" name="actPriS" value="CLUB">僅限社團</label>
<!--					  			TODO:選社團-->
									<label class="radio-inline">
										<input type="radio" class="actPriSS option-input radio" name="actPriS" value="CLUBS">社團聯誼</label>
<!--						  		TODO:選人-->
					  			
						  			<label class="radio-inline">
										<input type="radio" class="actPriSS option-input radio" name="actPriS" value="INV">邀請制</label>
<!--						  		TODO:選人-->

						  			<label class="radio-inline">
									<input type="radio" class="actPriSS option-input radio" name="actPriS" id="LVLVLV" value="LV">
									限制等級
						  			<span id="priLVshow">						  		
							  		　LV
							  			<select id="priLVsel">
							  				<option value="0"></option>
							  				<option value="6">1</option>
							  				<option value="7">2</option>
							  				<option value="8">3</option>
							  				<option value="9">4</option>
							  				<option value="10">5</option>
							  				<option value="11">6</option>
							  				<option value="12">7</option>
							  				<option value="13">8</option>
							  				<option value="14">9</option>
							  				<option value="15">10</option>
							  			</select>以上
							  		</span>
							  		</label>
							  		
									<input type="hidden" id="actPriID" name="actPriID">
									</div>
							  	</td>
							  </tr>
							  <tr>
							  <th colspan="4">詳細內容
							  <div class="margin-top-10">
							  	<textarea class="form-control" name="actContent" rows="15" cols="50" id="actCntTA"></textarea>
							  	
							  </div>
							  </th>
							  </tr>
								<tr>
							  	 <th>上傳活動照片
						  	 		</th>
						  	 		<td>
						  	 		<div class="image-editor">
						  	 		<span class="filebeauty">
									<input type="file" id="imguploader" accept="image/png,image/gif,image/jpeg,image/jpg" class="cropit-image-input">
									<i class="fa fa-cloud-upload" aria-hidden="true"></i> 選擇圖片
									</span>
										<div id="imgprev" style="width: 400px;">
										<div class="cropit-preview"></div>
										<div class="image-size-label">
										  <p class="small">調整圖片大小</p>
										</div>
										<input type="range" class="cropit-image-zoom-input">
										<input type="hidden" name="image-data" id="actImg" class="hidden-image-data" />
	<!--									<button id="testimg" type="submit">Submit</button>-->
										</div>

<!--
										<div id="result">
										  <code>$form.serialize() =</code>
										  <code id="result-data"></code>
										</div>
-->
						  	 	</div>
						  	 			
						  	 		</td>

							  </tr>
						  </table>

      				</div>

      	 			<a href="#tg5" class="btn btn-primary actNext toscroll" id="next5" type="button">下一步</a>
      	 			<div id="tg5"></div>
      			</div>
      		</div>
      		<hr class="stratLine"/>
      		</div>
<!--      		end of row-->
		
		
			<div id="step6" class="hns">　
<!--     		start of row-->
      		<div class="row">
      			<div class="col-md-1"><img src="act_assets/img/start/people.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 6/6</div>
      				<div class="startTitle"><h3>揪咪是一個友善熱情的網站，請同意我們的使用條款</h3></div>
      				<input id="ckfinal" type="checkbox"> 我同意<a href="#"> 《使用條款》 </a><p />
      				<div style="height: 8em;">
      				<button id="goSubmit" class="btn btn-danger actNext" type="submit">開啟活動囉！</button>
      				</div>
      			</div>
      		</div>
<!--      		end of row-->
     		</div>
        </form>
      </div>
        <!-- END SIDEBAR & CONTENT -->

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
  	
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.cropit.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.geocomplete.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
            Layout.init();    
            Layout.initOWL();
            Layout.initTwitter();
            Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
            Layout.initNavScrolling();
			$("#goSubmit").hide();
			$("#imgprev").hide();
			$("#priLVshow").hide();
			$("#next2").hide();
//			   $("#imguploader").on('change', function() {
//				$("#imgprev").show();
//		 	});


	//scroll
		$("a.toscroll").on('click',function(e) {
				var url = e.target.href;
				var hash = url.substring(url.indexOf("#")+1);
				$('html, body').animate({
					scrollTop: $('#'+hash).offset().top
				}, 500);
				return false;
			});
//step1>2
		$("#next1").click(function(){
				$("#step2").css("visibility","visible");
				 $("#geocomplete").geocomplete({
				  map: ".map_canvas",
				  details: "form",
				  types: ["geocode", "establishment"],
				});

				$("#find").click(function(){
				  $("#geocomplete").trigger("geocode");
				  $("#next2").show();
				});
			});
	
//step2>3
	
		$("#next2").click(function(){
			$("#step3").css("visibility","visible");
			$("#actLocName").val($("#prename").val());
			$("#actLong").val($("#prelong").val());
			$("#actLat").val($("#prelat").val());
			$("#actAdr").val($("#preadr").val());
			$("#actLocID").val($("#prepost").val());
		});
		

//step3>4
		$("#next3").click(function(){
			$("#step4").css("visibility","visible");
		});

//step4===
	$("#repTime").change(function(){
		$("#papprep").css("visibility","visible");
		
	});
	
//step4>5
		$("#next4").click(function(){
			$("#step5").css("visibility","visible");
			$(".image-editor").cropit();
			$("#imguploader").on('change', function() {
				$("#imgprev").show();
		 	});
		});
	
	
//step5==

			$("input:radio[name=actPriS]").change(function(){
			if(this.value!='LV'){
				$("#priLVshow").hide();
			};
			
			if (this.value == 'LV') {
				$("#priLVshow").show();
				$("#actPriID").val('LV');
				var LVLV=$("#priLVsel").val();
				$("#actPriID").val(LVLV);
			}else if (this.value == 'PUBLIC') {
//				alert(this.value);
				$("#actPriID").val(1);
			}else if (this.value == 'CLUB') {
//				alert(this.value);
				$("#actPriID").val(2);
			}else if (this.value == 'CLUBS') {
//				alert(this.value);
				$("#actPriID").val(3)
			}else if (this.value == 'INV') {
//				alert(this.value);
				$("#actPriID").val(4);
			}else{
				alert("WAT");
			}
		});
			$("#priLVsel").change(function(){
				var LVLV=$("#priLVsel").val();
				$("#actPriID").val(LVLV);
				$("#LVLVLV").prop("checked", true);
			});
	
	        $("#testimg").click(function() {
          // Move cropped image data to hidden input
          var imageData = $('.image-editor').cropit('export');
          $('.hidden-image-data').val(imageData);
			
          // Print HTTP request params
          var formValue = $("#hns").serialize();
          $('#result-data').text(formValue);
          // Prevent the form from actually submitting
          return false;
			
			
			
			
        });
	//step5>6
	
		$("#next5").click(function(){
			$("#step6").css("visibility","visible");
		});
	
	//finalcheck
	        $("#ckfinal").click(function(){
			if($("#ckfinal").prop("checked")){
				$("#goSubmit").fadeIn();
		}});
	    });
</script>



<!--	<link href="../src/act/js/actStartEnd.js" rel="stylesheet" type="text/css">-->

    
<!--TODO:dateLIMIT
    
    <script type="text/javascript">
    $(function () {
        $('#datetimepicker6').datetimepicker();
        $('#datetimepicker7').datetimepicker({
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
    });
	</script>
-->
    
    
    
    
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>