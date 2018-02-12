<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.emp.model.*"%> --%>
<%-- <%@ page import="com.per.model.*"%> --%>
<%-- <%@ page import="com.fun.model.*"%> --%>
<%-- <%@ page import="com.movement_category.model.*"%> --%>
<%-- <%@ page import="com.movement.model.*"%> --%>
<%-- <jsp:useBean id="movcatSvc" scope="page" class="com.movement_category.model.Movement_CategoryService" /> --%>
<%
// 	List<Movement_CategoryVO> list = movcatSvc.getAll();
// 	pageContext.setAttribute("list", list);

// 	EmpVO empVO = (EmpVO) session.getAttribute("empVO");
// 	PerService perSvc = new PerService();
// 	List<PerVO> listper = perSvc.getOnePerList(empVO.getEmp_id());
// 	System.out.println(empVO.getEmp_id());
// 	FunService funSvc = new FunService();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/back_end/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="/back_end/css/back_index.css" rel="stylesheet" type="text/css" />
<link type="text/javascript" rel="stylesheet" href="<%=request.getContextPath()%>/back_end/js/back_index_JQuery.js"/>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<style type="text/css">
#fixed-left {
	position: fixed;
	left: 10px;
}
/* icon 搜尋放大鏡圖、登出 */
li#nav-link a:hover {
	color: 	#FFAF60;
}
li#nav-link a {
	color: 	#ffffff;
}

</style>
<title>FitStyle Backstage</title>
</head>
<body>
<!-- navbar 請不要動到它-->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">選單切換</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
	</div>

	<!-- 手機隱藏選單區 -->
	<div class="container-fluid">
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<!-- 左選單 -->
			<ul class="nav navbar-nav" id="navbar-nav">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/back_end/back_index.jsp">
					<img src="<%=request.getContextPath()%>/front_end/images/fslogo.png" >
				</a>
			</ul>

			<!-- 右選單 -->
			<ul class="nav navbar-nav navbar-right">
				<li>
					<%-- 萬用複合查詢 --%>
					<form class="form-inline" method="post" action="<%=request.getContextPath()%>/back_end/mov/mov.do">
						<input type="hidden" name="action" value="listMovements_By_Composite_Query">
						<div class="input-group" style="height:35px;">
							<!-- 搜尋框 -->
							<input class="form-control" type="text" name="mov_name" size="30" placeholder="Search for..." value="">
							<span class="input-group-btn">
							<button class="btn btn-primary" type="submit" value="">
								<!-- 搜尋放大鏡圖 -->
								<i class="fa fa-search search" aria-hidden="true"></i>
							</button>
							</span>
						</div>
					</form>
				</li>
				<!-- 登出 -->
				<li id="nav-link">
					<a class="nav-link" href="<%=request.getContextPath()%>/back/emp/emp.do?action=logout" >
<%-- 					<div ><%=empVO.getEmp_name()%>，您好 請使用你的權限</div> --%>
						<i class="glyphicon glyphicon-log-out" >登出</i>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 手機隱藏選單區結束 -->
</nav>

<!-- container -->
<div class="container-fluid" style="margin-top:120px;" >

		<!-- 左邊管理清單列表 開始 -->
		<div class="col-xs-12 col-sm-2" id="fixed-left">
			<jsp:include page="/back_end/back_left.jsp"/>
		</div>
		<!-- 左邊管理清單列表 結束 -->

		<!-- 右邊內容 開始 -->
		<div class="col-xs-12 col-sm-10 col-sm-offset-2">

		</div>
		<!-- 右邊內容 結束 -->
</div>



<!-- footer -->

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>