<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movement_category.model.*"%>
<%
	Movement_CategoryVO movement_categoryVO = (Movement_CategoryVO) request.getAttribute("movement_categoryVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge, chrome=1" http-equiv="X-UA-Compatible">
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
			width: 700px;
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
	</style>
</head>
<body>
	<table id="table-1">
	<tr>
		<td>
		 	<h3>動作類別更新 update_movement_category_input.jsp</h3>
		 </td>
		 <td>
		 	<h4>
		 		<a href="<%= request.getContextPath() %>/back_end/back_index.jsp">
		 			<img src="<%=request.getContextPath()%>/back_end/images/tomcat.png" width="100" height="100" border="0">回首頁
		 		</a>
		 	</h4>
		</td>
	</tr>
	</table>
	<h3>資料新增：</h3>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form method="post" action="mov_cat.do" enctype="multipart/form-data">
	<input type="hidden" name="action" value="update">
		<table>
			<tr>
				<td>動作類別編號：<font color="red"><b>*</b></font></td>
				<td>${ movement_categoryVO.mov_cat_id }</td>
			</tr>
			<tr>
				<td>動作類別名稱：</td>
				<td><input type="text" name="mov_cat_name" size="50" value="<%=movement_categoryVO.getMov_cat_name()%>"/></td>
			</tr>
			<tr>
				<td>動作類別簡介：</td>
				<td><input type="text" name="mov_cat_info" size="50" value="<%=movement_categoryVO.getMov_cat_info()%>"/></td>
			</tr>
			<tr>
				<td>動作類別圖片：</td>
				<td><input type="file" name="mov_cat_img" size="50" value="<%=movement_categoryVO.getMov_cat_img()%>"/></td>
			</tr>
		</table>
		<input type="hidden" name="mov_cat_id" value="<%=movement_categoryVO.getMov_cat_id()%>">
		<input type="submit" value="送出修改">
	</form>
</body>
</html>