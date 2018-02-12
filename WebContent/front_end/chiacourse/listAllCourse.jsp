<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.movement_category.model.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
<jsp:useBean id="movcatSvc" scope="page" class="com.movement_category.model.Movement_CategoryService" />

<%
    CouService couSvc = new CouService();
    List<CouVO> list = couSvc.getPubAll();
    pageContext.setAttribute("list",list);
    session.getAttribute("memVO");
%>

<!DOCTYPE html>
<html>
<head>
	<title>FitStyle</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%= request.getContextPath() %>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css"/>
<style>
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 400px;
	margin-left: 0px;
	background: -webkit-linear-gradient(left top,steelblue,paleturquoise);
	background: -o-linear-gradient(bottom right,steelblue,paleturquoise);
	background: -moz-linear-gradient(bottom right,steelblue,paleturquoise);
	background: linear-gradient(to bottom right,steelblue,paleturquoise);
	}
.breadcrumb{
	background:none;
	}
.zindex{
	z-index:9999;
}
</style>	

</head>
<body>
	
	<div class="container">
	
	<div class="page-header">
	  <h1><i style="font-family:Microsoft YaHei; color:#fff;">推薦課程</i></h1>
	</div>
	<!-- 課程清單 -->
		<div class="row">
			<c:forEach var="couVO" items="${list}">
				<a href="<%=request.getContextPath()%>/back_end/cou/cou.do?action=getOne_For_Display&cou_id=${couVO.cou_id}">
					<div class=" col-xs-12 col-sm-3 categoryblock" style="background-image: url(<%=request.getContextPath()%>/DBGifReader_Cou.do?cou_id=${couVO.cou_id}); height:300px; background-repeat:no-repeat; border:1px #ccc solid; background-size:300px 300px;">
						<h2 style="color:white;"><b>${couVO.cou_name}</b></h2>
					</div>
				</a>
			</c:forEach>
		</div>

	<!-- 動作庫 -->
		<div class="page-header">
	  <h1><i style="font-family:Microsoft YaHei; color:#fff;">動作庫</i></h1>
	</div>
		<div class="row">
			<c:forEach var="movement_categoryVO" items="${movcatSvc.all}" >
				<a href="<%=request.getContextPath()%>/back_end/mov_cat.do?action=listMovements_Bymov_cat_B&mov_cat_id=${movement_categoryVO.mov_cat_id}">
					<div class="col-xs-12 col-sm-3 categoryblock" style="background-image: url(<%=request.getContextPath()%>/DBGifReader_MovCat.do?mov_cat_id=${ movement_categoryVO.mov_cat_id }); height:300px; background-repeat:no-repeat; border:1px #ccc solid; background-size:300px 300px;">
						<div>
							<h2 style="color:white;"><b>${movement_categoryVO.mov_cat_name}</b></h2>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
<%@ include file="/front_end/footer.jsp" %>
</body>
</html>