<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.movement_category.model.*"%>
<%-- <%@ page import="java.util.List"%> --%>

<!-- 這裡 useBean id 接後端 controller 傳來的 list。listMovements_By_Composite_Query區塊  -->
<jsp:useBean id="list" scope="request" type="java.util.List<MovementVO>" />
<%
// 	List<MovementVO> list = (List<MovementVO>)request.getAttribute("list");
// 	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="movementSvc" scope="page" class="com.movement_category.model.Movement_CategoryService" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
		<c:forEach var="movementVO" items="${list}">
			<a href="mov.do?action=getOne_For_Display&mov_id=${movementVO.mov_id}">
				<div class="col-sm-3 categoryblock" id="movement_no">
					<img src="<%=request.getContextPath()%>/DBimgReader_Mov.do?mov_id=${ movementVO.mov_id }" id="mov_img" class="img-responsive">
					<h5>${ movementVO.mov_name }</h5>
				</div>
			</a>
		</c:forEach>
	</div>
</div>
</body>
</html>