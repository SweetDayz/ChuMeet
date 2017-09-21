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
  <div class="container">
  <div class="wd80">
  <br><br><br>
<form action="<%=request.getContextPath()%>/front-end/act/act.do" method="get">
	<button type="submit" class="btn mybtns" name="actID" value="1">1</button>
	<button type="submit" class="btn mybtns" name="actID" value="2">2</button>
	<button type="submit" class="btn mybtns" name="actID" value="3">3</button>
	<button type="submit" class="btn mybtns" name="actID" value="4">4</button>
	<button type="submit" class="btn mybtns" name="actID" value="5">5</button>
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