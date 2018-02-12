<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link href="<%=request.getContextPath()%>/front_end/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/front_end/css/index.css" rel="stylesheet" type="text/css" />
<style>
h2{
font-family: "Microsoft YaHei";
text-align:center
}
</style>
</head>
<body>

	<div class="our section" id="cover">
		<div class="bg-wrap">
			<!-- navbar -->
			<nav class="navbar navbar-default" role="navigation">

				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
				</div>

				<!-- 手機隱藏選單區 -->
				<div class="container">
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- 左選單 -->
						<ul class="nav navbar-nav">
							<li><a class="navbar-brand" >Fit Style</a></li>
							<li><a class="navbar-brand"
								href="<%=request.getContextPath()%>/front_end/chiacourse/listAllCourse.jsp">課程</a></li>
							<%-- 					<li><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/chiacourse/addCourse.jsp">我的健身房</a></li> --%>
							<li><a class="navbar-brand"
								href="<%=request.getContextPath()%>/inf/inf.do?action=list&inf_cat_id=1">相關資訊</a></li>
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
								<li><a>${memVO.mem_name} 您好</a></li>
								<li><a
									href="<%=request.getContextPath()%>/mem/mem.do?action=logout">登出</a></li>
<!-- 								<li><a -->
<%-- 									href="<%=request.getContextPath()%>/front_end/mem/updatemem.jsp">個人資料設定</a></li> --%>

<!-- 								<li class="dropdown"><a href="#" class="dropdown-toggle" -->
<!-- 									data-toggle="dropdown">我的健身房 <b class="caret"></b></a> -->
<!-- 									<ul class="dropdown-menu"> -->
								<li><a
									href="<%=request.getContextPath()%>/front_end/chiacourse/MyGYM.jsp">我的健身房</a></li>
<!-- 								<li><a href="#">個人已有課程</a></li> -->
<!-- 									</ul></li> -->
							</c:if>
						</ul>
					</div>
				</div>
			</nav>
			<!-- 手機隱藏選單區結束 -->
			<video controls autoplay loop id="bgvid">
				<source src="<%=request.getContextPath()%>/front_end/images/5s.mp4" type="video/mp4">
			</video>
		</div>
		<div class="row text-center content">
			<i><h1 style="margin-top: 200px; color: white; font-size: 70px;">
					<b>相 信 自 己 </b><img src="<%=request.getContextPath()%>/front_end/images/fslogo.png" width="300px"><b>健
						康 在 我</b></h1></i>
		</div>
	</div>
	
	<!-- 熱門課程區塊 -->
	<div class="popular clear">
		<div class="container clear" style="margin-top: -150px;">
			<div class="row">
				<h1 class="text-center">
					<b>熱門課程</b>
				</h1>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c1.jpg" alt="">
						<div class="caption">
							<h2>HIIT喚醒</h2>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c2.jpg" alt="">
						<div class="caption">
							<h2>零碎時間腹肌</h2>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c3.jpg" alt="">
						<div class="caption">
							<h2>HIIT全身燃動</h2>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c4.jpg" alt="">
						<div class="caption">
							<h2>上肢力量突破</h2>
						</div>
					</a>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c5.jpg" alt="">
						<div class="caption">
							<h2>核心養成</h2>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c6.jpg" alt="">
						<div class="caption">
							<h2>人魚線雕刻</h2>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c7.jpg" alt="">
						<div class="caption">
							<h2>拳擊燃脂</h2>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="thumbnail"> <img
						src="<%=request.getContextPath()%>/front_end/images/c8.jpg" alt="">
						<div class="caption">
							<h2>零碎時間減脂</h2>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>

<!-- footer -->
		<div class="container-fluid footer">
			<div class="row">
				<div>
					<div class="col-xs-12 col-sm-8">
						<div class="col-xs-12 col-sm-6">
							<a href="#">關於我們</a> <a href="#">聯絡方式</a>
							<a href="#">客服中心</a> <a href="#">訓練課程</a>
						</div>
						<div align=center class="col-xs-12 col-sm-6">
							<h4>© Fit Style. 桃園市中壢區中大路300號</h4>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4" >
						<div class="col-xs-12 col-sm-2"	align=right><a href="#"><img src="<%= request.getContextPath() %>/front_end/images/instagram.png" width="30px"></a></div>
						<div class="col-xs-12 col-sm-2"	align=right><a href="#"><img src="<%= request.getContextPath() %>/front_end/images/facebook.png" width="30px"></a></div>
						<div class="col-xs-12 col-sm-2"	align=right><a href="#"><img src="<%= request.getContextPath() %>/front_end/images/line.png" width="30px"></a></div>
						<div class="col-xs-12 col-sm-2"	align=right><a href="#"><img src="<%= request.getContextPath() %>/front_end/images/taiwan.png" width="30px"></a></div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
