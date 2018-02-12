<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
<jsp:useBean id="perSvc" scope="page" class="com.per.model.PerService" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<style type="text/css">
	
</style>
</head>
<body>
<jsp:include page="/back_end/back_index.jsp" />
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="page-header">
				<h1>權限設置</h1>
			</div>
		</div>
	</div>
		
	<div class="row" id="up">
		<div class="textsize">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>員工帳號</th>
						<th>會員管理</th>
						<th>相關資訊管理</th>
						<th>檢舉管理</th>
						<th>員工管理</th>
						<th>健身動作管理</th>
						<th>健身課程管理</th>
						<th>訂單管理</th>
						<th>商品管理</th>
						<th>客服管理</th>
					</tr>
				</thead>
				<tbody>
			<c:forEach var="empVO" items="${empSvc.all}">
				<form method="post" action="<%=request.getContextPath()%>/back/per/per.do">
					<tr>
						<td>
							${empVO.emp_acct}
						</td>
					<c:forEach var="funVO" items="${funSvc.all}">
						<td>
							<input type="checkbox" id="coding" name="fun_id" value="${funVO.fun_id }" 
								<c:forEach var="perVO" items="${perSvc.getOnePerList(empVO.emp_id)}">
									${(perVO.fun_id==funVO.fun_id)? 'checked':'' }
								</c:forEach>
							>
						</td>
					</c:forEach>
						<td>
							<input type="submit" class="btn btn-primary" value="送出">
							<input type="hidden" name="emp_id" value="${empVO.emp_id}">
							<input type="hidden" name="action" value="addPer">
						</td>
					</tr>
				</form>					    
			</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
</div>

</body>
</html>