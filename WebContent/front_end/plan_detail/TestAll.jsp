<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan_detail.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%
	Plan_DetailService plan_detailSvc = new Plan_DetailService();
    List<Plan_DetailVO> list = plan_detailSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%= request.getContextPath() %>/html_plan/css/plan.css" rel="stylesheet" type="text/css"/>
<style>
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
</style>

<style>
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
</style>


</head>
<body>

<nav class="navbar navbar-default" role="navigation">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				</a>
			</div>
		
			<!-- 手機隱藏選單區 -->
			<div class="container">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 左選單 -->
					<ul class="nav navbar-nav">
						<a class="navbar-brand" href="#"><img src="img/logo.png">
						<li><a href="#">課程</a></li>
						<li><a href="#">我的健身房</a></li>
						<li><a href="#">商城</a></li>
						<li><a href="#">相關資訊</a></li>
					</ul>
						
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">註冊</a></li>
						<li><a href="#">登入</a></li>
					</ul>
				</div>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
	<!-- 麵包屑 -->
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<ol class="breadcrumb">
			<li>
				<a href="#">我的課程</a>
			</li>
			<li>
				<a href="#">建立課程</a>
			</li>
			<li class="active">我的計畫</li>
			<li>
				<a href="#">建立計畫</a>
			</li>				
		</ol>
	</div>


		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
				</div>
				<div class="col-xs-12 col-sm-6">
				<table>
				<tr>
					<th>計畫編號</th>
					<th>課程編號</th>
					<th>選擇日期</th>
				</tr>
				
				<c:forEach var="plan_detailVO" items="${list}" >
					
				<tr>
					<td>${plan_detailVO.plan_id}</td>
					<td>${plan_detailVO.cou_id}</td>
					<td>${plan_detailVO.selectdate}</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan_detail/plan_detail.do" style="margin-bottom: 0px;">
							<input type="submit" value="刪除">
						    <input type="hidden" name="plan_id"  value="${plan_detailVO.plan_id}">
							<input type="hidden" name="cou_id"  value="${plan_detailVO.cou_id}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
				</c:forEach>
				
				</table>
				</div>
			</div>
		</div>


		<div class="container-fluid footer">
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<a href="#">關於我們</a>｜
					<a href="#">客服中心</a>
				</div>
				<div class="col-xs-12 col-sm-4">
					
				</div>
				<div class="col-xs-12 col-sm-4">
					
				</div>
			</div>
		</div>
</body>
</html>