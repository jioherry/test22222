
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.cou_cat.model.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
<%
	
	
// 	課程分類
	session.getAttribute("memVO");
	CouService cSvc = new CouService();
	List<CouVO>	coulist= cSvc.getPubAll();
	pageContext.setAttribute("coulist",coulist);
// 	MemVO memVO = (MemVO) request.getAttribute("memVO");

%>



<!DOCTYPE html>
<html lang="">
	<head>
	<title>Fit Style</title>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />

		
<style type="text/css">
 .size {  
 	width:195px; 
 	height:550px; 
 	opacity:0.5;
	
 }  
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 700px;
	margin-left: 0px;
	background: -webkit-linear-gradient(left top,steelblue,paleturquoise);
	background: -o-linear-gradient(bottom right,steelblue,paleturquoise);
	background: -moz-linear-gradient(bottom right,steelblue,paleturquoise);
	background: linear-gradient(to bottom right,steelblue,paleturquoise);
	}
	.breadcrumb{
	background:none;
	}
	.zindex{
		z-index:9999;
	}	
	
</style>
</head>
<body>
			<!-- 麵包屑 -->

	<div class="col-xs-12 col-sm-10 col-sm-offset-1 zindex">
		<ol class="breadcrumb">
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/MyGYM.jsp">我的課程</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/addCourse.jsp">新增課程</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/plan/myplan.jsp">我的計畫</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/memCourse.jsp">編輯課程動作</a>
			</li>
			<li>
			<a href="<%=request.getContextPath()%>/front_end/chiacourse/listmemcoumovajax.jsp">前往觀看課程動作</a>
<%-- 			<a href="<%=request.getContextPath()%>/front_end/chiacourse/playlist.jsp">課程動作明細</a> --%>
			</li>
		</ol>
	</div>
		
		<div class="container">
			<div class="row">
			
<!-- 			分類標題 -->
				
				<c:forEach var="cou" items="${coulist}">
						<form method="post" action="<%=request.getContextPath()%>/chiacou.do">
						<div class="col-xs-12 col-sm-2">
							<a class="thumbnail size">
								<img src="<%=request.getContextPath()%>/DBGifReader_Cou.do?cou_id=${cou.cou_id}" id="cou_img" class="img-responsive">
								<div class="caption">
									<h2>${cou.cou_name}</h2>
									<p>${cou.cou_intor}</p>
									<input type="submit" value="加入">
								</div>
							</a>
						</div>
						
							<input type="hidden" name="cou_id" value="${cou.cou_id}">
<%-- 							<input type="hidden" name="mem_id" value="${cou.mem_id}"> --%>
							<input type="hidden" name="cou_mem_id" value="${cou.mem_id}">
							<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
							<input type="hidden" name="action" value="addothermemecou">
							
						</form>
				</c:forEach>
				
				
				
			</div>

			
			
		</div>
		
<%@ include file="/front_end/footer.jsp" %>			
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">	
</script>
</body>
</html>