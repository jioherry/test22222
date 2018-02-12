<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="com.movement_category.model.*"%>
<%@ page import="com.movement.model.*"%>

<jsp:useBean id="movcatSvc" scope="page" class="com.movement_category.model.Movement_CategoryService" />

<%
// 	Movement_CategoryService movcatSvc = new Movement_CategoryService();
//	Set<Movement_CategoryVO> movementVO = movcatSvc.getMovementsByMovement_Catrgory();
//	pageContext.setAttribute("movementVO", movementVO);

    EmpVO empVO = (EmpVO) session.getAttribute("empVO");
    PerService perSvc = new PerService();
    List<PerVO> listper = perSvc.getOnePerList(empVO.getEmp_id());
    System.out.println(empVO.getEmp_id());
    FunService funSvc = new FunService();
%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<link href="<%=request.getContextPath()%>/back_end/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back_end/css/back_index.css" rel="stylesheet" type="text/css" />
<link type="text/javascript" rel="stylesheet" href="<%=request.getContextPath()%>/back_end/js/back_index_JQuery.js"/>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
</head>
<body>  
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

	<% if (listper != null && (listper.size() > 0)) { %>
		<%for (PerVO per : listper) {%>

			<!-- 員工管理 -->
			<%if(funSvc.getOneFun(per.getFun_id()).getFun_name().equals("員工管理")) { %>
				<div class="panel panel-danger">
					<div class="panel-heading" role="tab" id="panel1">
						<h4 class="panel-title">
							<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
							員工管理
							</a>
						</h4>
					</div>
					<div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
						<div class="panel-body">
							<a href="<%=request.getContextPath()%>/back_end/emp/addEmp.jsp">新增員工</a>
						</div>
						<div class="panel-body">
							<a href="<%=request.getContextPath()%>/back_end/emp/listAllEmp.jsp">查詢員工</a>
						</div>
						<div class="panel-body">
							<a href="<%=request.getContextPath()%>/back_end/per/addEmpPer.jsp">權限設置</a>
						</div>
					</div>
				</div>
			<%}%>
			
			<!-- 會員管理 -->
			<%if(funSvc.getOneFun(per.getFun_id()).getFun_name().equals("會員管理")) { %>
				<div class="panel panel-danger">
					<div class="panel-heading" role="tab" id="panel2">
						<h4 class="panel-title">
							<a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
								會員管理
							</a>
						</h4>
					</div>
					<div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
						<div class="panel-body">
							<a href="<%=request.getContextPath()%>/back_end/mem/listAllMem.jsp">查詢會員</a>
						</div>
					</div>
				</div>
			<%}%>	
			
			<!-- 動作管理 -->
			<%if(funSvc.getOneFun(per.getFun_id()).getFun_name().equals("健身動作管理")){ %>
				<div class="panel panel-danger">
					<div class="panel-heading" role="tab" id="panel3">
						<h4 class="panel-title">
							<a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
								動作管理
							</a>
						</h4>
					</div>
					<div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
						<div class="panel-body">
							<div class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">動作庫 <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<!-- 查動作類別，下拉選單 -->
									<li>
										<ul>
											<c:forEach var="movement_categoryVO" items="${movcatSvc.all}" >
												<li style="list-style-type: none;">
													<a href="<%=request.getContextPath()%>/back_end/mov_cat.do?action=listMovements_Bymov_cat_A&mov_cat_id=${movement_categoryVO.mov_cat_id}">
														${movement_categoryVO.mov_cat_name}
													</a>
												</li>
											</c:forEach>
										</ul>
									</li>
								</ul>
							</div>
						</div>
						<div class="panel-body">
							<a href="<%= request.getContextPath() %>/back_end/mov_cat/addMovementCategory.jsp">新增動作庫</a>
						</div>
						<div class="panel-body">
							<a href="<%= request.getContextPath() %>/back_end/mov/addMovement.jsp">新增動作</a>
						</div>
						<div class="panel-body">
							<a href="<%= request.getContextPath() %>/back_end/mov/listAllMovement.jsp">全部動作</a>
						</div>
					</div>
				</div>
			<%}%>
			
			
			<!-- 相關資訊管理 -->
			<%if(funSvc.getOneFun(per.getFun_id()).getFun_name().equals("相關資訊管理")){ %>
				<div class="panel panel-danger">
					<div class="panel-heading" role="tab" id="panel5">
						<h4 class="panel-title">
							<a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd">
								相關資訊管理
							</a>
						</h4>
					</div>
					<div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5">
						<div class="panel-body">
							<a href="<%=request.getContextPath()%>/back_end/inf/addInf.jsp">新增相關資訊</a>
						</div>
						<div class="panel-body">
							<a href="<%=request.getContextPath()%>/back_end/inf/listAllInf.jsp">查詢相關資訊</a>
						</div>
					</div>
				</div>
			<%}%>
			
		<%}%>
	<%}%>

</div>
</body>
</html>