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
  <!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->

  
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