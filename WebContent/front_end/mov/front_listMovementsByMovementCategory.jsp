<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
<%
  session.getAttribute("memVO");
%>
<jsp:useBean id="movementVO" scope="request" type="java.util.Set<MovementVO>" />
<jsp:useBean id="movementSvc" scope="page" class="com.movement_category.model.Movement_CategoryService" />
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
</style>
</head>
<body>

<div class="container">
	<div class="row">
		<c:forEach var="movementVO" items="${movementVO}" >
			<a href="mov.do?action=getOne_For_Display_B&mov_id=${movementVO.mov_id}">
				<div class=" col-xs-12 col-sm-3 categoryblock" style="background-image: url(<%=request.getContextPath()%>/DBimgReader_Mov.do?mov_id=${ movementVO.mov_id }); height:200px;">
					<h2>${ movementVO.mov_name }</h2>
				</div>
			</a>
		</c:forEach>
	</div>
</div>
<%@ include file="/front_end/footer.jsp" %>	
</body>
</html>