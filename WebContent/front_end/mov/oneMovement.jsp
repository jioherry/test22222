<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>

<%
	session.getAttribute("memVO");	
	MovementVO movementVO = (MovementVO) request.getAttribute("movementOneVO");
	pageContext.setAttribute("movementVO", movementVO);
%>
<!DOCTYPE html>
<html>
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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
#fontSize{
	font-size:18px;
}
#titleSize{
	font-size:22px;
}
</style>
</head>
<body>

<div class="container">
	<div class="col-xm-12 col-sm-8 col-sm-offset-2">
	<!-- 動作編號 -->
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="page-header">
					<h1><i>${ movementVO.mov_name }</i></h1>
				</div>
			</div>
		</div>
	<!-- 影片 -->
		<div class="row" id="">
			<div class="">
	
				<table class="table">
					<thead>
						<tr>
							<td>
<!-- 								<label for="aa" class="col-sm-2 control-label"></label> -->
								<div class="col-sm-10 col-sm-offset-1" id="mov_video_css">
									<video class="preview_video" width="300" style="background:#000; margin-bottom:20px; width:100%;" id="mov_video" controls loop>
										<source type="video/mp4" src="<%=request.getContextPath()%>/DBvideoReader.do?mov_id=${ movementVO.mov_id }">
									</video>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label" id="titleSize">動作簡介：</label>
								<div class="col-sm-9" id="fontSize">
									${ movementVO.mov_info }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label" id="titleSize">動作等級：</label>
								<div class="col-sm-9" id="fontSize">
									${ movementVO.mov_level }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label" id="titleSize">時間長度：</label>
								<div class="col-sm-9" id="fontSize">
									${ movementVO.mov_time_length }
								</div>
							</td>
						</tr>
					</tbody>
				</table>
					
			</div>
		</div>
	</div>	
	
</div>
<%@ include file="/front_end/footer.jsp" %>	
</body>
</html>