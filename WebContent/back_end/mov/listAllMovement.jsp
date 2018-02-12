<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movement.model.*"%>
<!DOCTYPE html>
<%
	MovementService movSvc = new MovementService();
	List<MovementVO> list = movSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/back_end/back_index.jsp" />
<style type="text/css">
	#movement_no {
		width: 200px;
		height: 200px;
	}
</style>
</head>
<body>
<div class="container col-xs-12 col-sm-10 col-sm-offset-2">
	<c:forEach var="movementVO" items="${ list }">
		<a href="mov.do?action=getOne_For_Display_A&mov_id=${movementVO.mov_id}">
			<div class="col-xs-12 col-sm-3 categoryblock" id="movement_no">
				<img src="<%=request.getContextPath()%>/DBimgReader_Mov.do?mov_id=${ movementVO.mov_id }" id="mov_img" class="img-responsive">
				<h5>${ movementVO.mov_name }</h5>
			</div>
		</a>
	</c:forEach>
</div>
</body>
</html>