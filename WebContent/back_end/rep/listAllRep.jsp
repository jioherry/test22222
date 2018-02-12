<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.rep.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
    RepService repSvc = new RepService();
    List<RepVO> list = repSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/back_end/back_index.jsp" />
<style type="text/css">

</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="page-header">
			  <h1>檢舉事件</h1>
			</div>
		</div>
	</div>
<%@ include file="page1.file" %>
	<div class="row" id="up">
		<div class="textsize">
		
			<table class="table table-hover">
				<thead>
					<tr>
						<th>檢舉編號</th>
						<th>會員編號</th>
						<th>員工編號</th>
						<th>課程編號</th>
						<th>檢舉內容</th>
						<th>檢舉日期</th>
						<th>檢舉狀態</th>
						<th colspan="2">處理方式</th>
					</tr>
				</thead>
			<c:forEach var="repVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<c:if test="${ repVO.rep_status != '已審核'}">
				<tbody>
					<tr>
						<td>${repVO.rep_id}</td>
						<td>${repVO.mem_id}</td>
						<td>${repVO.emp_id}</td>
						<td>${repVO.cou_id}</td>
						<td>${repVO.rep_cont}</td>
						<td>${repVO.rep_date}</td>
						<td>${repVO.rep_status}</td>
					</tr>
				</tbody>
				</c:if>
			</c:forEach>
			</table>
			
		</div>
	</div>
<%@ include file="page2.file" %>
</div>

</body>
</html>