
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.cou_cat.model.*"%>
<%@ page import="com.cou_det.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>



<%
	CouService couSvc = new CouService();
	Map<String, String[]> map = new TreeMap<String, String[]>();
	map.put("mem_id", new String[] { "7002" });
	List<CouVO> coulist = couSvc.oneMemberCous(map);
	pageContext.setAttribute("coulist", coulist);
	session.getAttribute("memVO");
%>

<jsp:useBean id="cou_detSvc" scope="page" class="com.cou_det.model.CouDetService" />



<!DOCTYPE html>
<html lang="">
<head>
	<title>Fit Style</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%=request.getContextPath()%>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.size {
	height: 280px;
	width: 195px;
}
/* 		table { */
/* 			outline:1px red solid; */
/* 		} */
</style>
<style>
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 300px;
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
	
	<!-- 麵包屑 -->
	<div class="col-xs-12 col-sm-10 col-sm-offset-1 zindex">
		<ol class="breadcrumb">
			<li><a href="#">我的課程</a></li>
			<li class="active">我的計畫</li>
		</ol>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-sm-offset-3">


				<!-- 			動作表格 -->

				<table class="table table-striped">
					<tr>
						<td>${couVO.cou_id}</td>

					</tr>
					<c:forEach var="couVO" items="${coulist}">
						<c:forEach var="coudetVO"
							items="${cou_detSvc.getfindByCouid(couVO.getCou_id())}">
							<tr>
								<td>${couVO.cou_id}</td>
								<td>${coudetVO.mov_id}</td>
								<td>${coudetVO.mov_order}</td>
								<td>播放</td>
							</tr>

						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
<%@ include file="/front_end/footer.jsp" %>	
</body>
</html>