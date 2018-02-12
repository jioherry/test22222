<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
    MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/back_end/back_index.jsp" />
<style type="text/css">

</style>
</head>
<body>
<div class="container col-xs-9 col-sm-9 col-sm-offset-2">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="page-header">
			  <h1>會員</h1>
			</div>
		</div>
	</div>
<%@ include file="page1.file" %>
	<div class="row" id="up">
		<div class="textsize">
		
			<table class="table table-hover">
				<thead>
					<tr>
						<th>會員編號</th>
						<th>帳號</th>
						<th>密碼</th>
						<th>信箱</th>
						<th>電話</th>
						<th>紅利</th>
						<th>頭銜</th>
						<th>積分</th>
						<th>照片</th>
						<th>姓名</th>
						<th>性別</th>
						<th>地址</th>
						<th>狀態</th>
						<th>檢舉</th>
					</tr>
				</thead>
			<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tbody>
					<tr>
						<td>${memVO.mem_id}</td>
						<td>${memVO.mem_acct}</td>
						<td>${memVO.mem_psw}</td>
						<td>${memVO.mem_email}</td>
						<td>${memVO.mem_phone}</td>
						<td>${memVO.mem_bonus}</td>
						<td>${memVO.mem_title}</td>
						<td>${memVO.mem_exp}</td>
						<td width="100px">
							<img src="<%=request.getContextPath()%>/mem/DBGifReader4?mem_id=${memVO.mem_id}" width="200px">
						</td>
						<td>${memVO.mem_name}</td>
						<td>${memVO.mem_gender}</td>
						<td>${memVO.mem_add}</td>
						<td>${memVO.mem_status}</td>
						<td>${memVO.mem_repno}</td>
					</tr>
				</tbody>
			</c:forEach>
			</table>
			
		</div>
	</div>
<%@ include file="page2.file" %>
</div>

</body>
</html>