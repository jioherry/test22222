<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movement.model.*"%>
<jsp:useBean id="movementVO" scope="request" type="java.util.Set<MovementVO>" />
<jsp:useBean id="movementSvc" scope="page" class="com.movement_category.model.Movement_CategoryService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="<%=request.getContextPath()%>/back_end/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back_end/css/back_index.css" rel="stylesheet" type="text/css" />
<title>FitStyle Backstage</title>
<style type="text/css">
	#movement_no {
		width: 200px;
		height: 200px;
	}
</style>
</head>
<body>
<jsp:include page="/back_end/back_index.jsp" />
<div class="container">
	<div class="row" style="margin-left:100px;">
		<c:forEach var="movementVO" items="${movementVO}" >
			<a href="mov.do?action=getOne_For_Display_A&mov_id=${movementVO.mov_id}">
				<div class="col-xs-12 col-sm-3 categoryblock" id="movement_no">
					<img src="<%=request.getContextPath()%>/DBimgReader_Mov.do?mov_id=${ movementVO.mov_id }" id="mov_img" class="img-responsive">
					<h5>${ movementVO.mov_name }</h5>
				</div>
			</a>
		</c:forEach>
	</div>
</div>
</body>
</html>