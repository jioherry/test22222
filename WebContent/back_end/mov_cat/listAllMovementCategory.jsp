<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movement_category.model.*"%>
<jsp:useBean id="movcatSvc" scope="page" class="com.movement_category.model.Movement_CategoryService" />
<%
	List<Movement_CategoryVO> list = movcatSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FitStyle</title>
	<style type="text/css">
		table#table-1 {
			background-color: #CCCCFF;
			border: 2px solid black;
			text-align: center;
		}
		table#table-1 h4 {
			color: red;
			display: block;
			margin-bottom: 1px;
		}
		h4 {
			color: blue;
			display: inline;
		}
		table {
			width: 600px;
			background-color: white;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		table, th, td {
			border: 1px solid #CCCCFF;
		}
		th, td {
			padding: 5px;
			text-align: center;
		}
		#mov_cat_img {
			width: 100px;
		}
	</style>
</head>
<body>
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有動作類別</h3>
		 <h4>
		 	<a href="<%=request.getContextPath()%>/back_end/back_index.jsp">
		 		<img src="<%=request.getContextPath()%>/back_end/images/back1.gif" width="100" height="30" border="0">回首頁
		 	</a>
		 </h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>動作類別編號</th>
		<th>動作類別名稱</th>
		<th>動作類別簡介</th>
		<th>動作類別圖片</th>
		<th>修改</th>
		<th>查詢動作</th>
	</tr>
<%@ include file="pages/page1.file"%>
	<c:forEach var="movement_categoryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${ movement_categoryVO.mov_cat_id }</td>
			<td>${ movement_categoryVO.mov_cat_name }</td>
			<td>${ movement_categoryVO.mov_cat_info }</td>
			<td>
				<img src="<%=request.getContextPath()%>/DBGifReader_MovCat.do?mov_cat_id=${ movement_categoryVO.mov_cat_id }" id="mov_cat_img">
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/back_end/mov_cat/mov_cat.do" style="margin-bottom: 0px;">
					<input type="submit" value="修改"> 
					<input type="hidden" name="mov_cat_id" value="${ movement_categoryVO.mov_cat_id }">
					<input type="hidden" name="action" value="getOne_For_Update_MovCat">
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/back_end/mov_cat/mov_cat.do" style="margin-bottom: 0px;">
				    <input type="submit" value="送出查詢"> 
				    <input type="hidden" name="mov_cat_id" value="${movement_categoryVO.mov_cat_id}">
				    <input type="hidden" name="action" value="listMovements_Bymov_cat_B">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file"%>
<%if (request.getAttribute("movementVO")!=null){%>
       <jsp:include page="listMovementsByMovementCategory.jsp" />
<%} %>
</body>
</html>