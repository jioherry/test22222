<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%
	//         登入成功回來的會員
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	if (memVO != null) {

	}
%>
<!DOCTYPE html>
<html>
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<%= request.getContextPath() %>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!-- navbar -->
	<nav class="navbar navbar-default" role="navigation">

			<div class="navbar-header"_height="none">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
		
			<!-- 手機隱藏選單區 -->
			<div class="container">
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/index.jsp" style="padding-top: 0px;"><img
						src="<%=request.getContextPath()%>/front_end/images/fslogo.png"></a>
						<li><a href="<%=request.getContextPath()%>/front_end/chiacourse/listAllCourse.jsp">課程</a></li>
						<li><a href="<%=request.getContextPath()%>/inf/inf.do?action=list&inf_cat_id=1">相關資訊</a></li>
				</ul>

				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${memVO==null}">
						<li><a
							href="<%=request.getContextPath()%>/front_end/mem/register.jsp">註冊</a></li>
						<li><a
							href="<%=request.getContextPath()%>/front_end/login/login.jsp">登入</a></li>
					</c:if>

					<c:if test="${memVO!=null}">
						<li><a href="#">${memVO.mem_name} 您好</a></li>
						<li><a
							href="<%=request.getContextPath()%>/mem/mem.do?action=logout">登出</a></li>
<!-- 						<li><a -->
<%-- 							href="<%=request.getContextPath()%>/front_end/mem/updatemem.jsp">個人資料設定</a></li> --%>

<!-- 						<li class="dropdown"><a href="#" class="dropdown-toggle" -->
<!-- 							data-toggle="dropdown">我的健身房 <b class="caret"></b></a> -->
<!-- 							<ul class="dropdown-menu"> -->
								<li><a
									href="<%=request.getContextPath()%>/front_end/chiacourse/MyGYM.jsp">我的健身房</a></li>
<!-- 								<li><a href="#">個人已有課程</a></li> -->
<!-- 							</ul></li> -->
					</c:if>
					</ul>
				</div>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>

</body>
</html>