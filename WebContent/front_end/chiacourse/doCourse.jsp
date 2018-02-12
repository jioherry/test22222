<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
<%@ page import="java.util.*"%>
<%@ page import="com.movement.model.*"%>
<%
	CouVO couVO = (CouVO) request.getAttribute("couVO");
	session.getAttribute("memVO");

%>

<%-- <jsp:useBean id="cou_detSvc" scope="page" class="com.cou_det.model.CouDetService" /> --%>
<jsp:useBean id="movSvc" scope="page" class="com.movement.model.MovementService" />

<!-- EL寫法可省去很多雜項 -->
<%-- ${couVO.cou_id}; --%>

<html>
<head>
	<title>Fit Style</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%=request.getContextPath()%>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />
	
	
	<style type="text/css">
		
		.clr {
			background-color:#E0E0E0;
		}
	body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 300px;
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
	 .modal {
  text-align: center;
}

@media screen and (min-width: 768px) { 
  .modal:before {
    display: inline-block;
    vertical-align: middle;
    content: " ";
    height: 100%;
  }
}

.modal-dialog {
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}
 .modal.in .modal-dialog{
 width:400px;
 }
 
 
  .size {  
 	width:250px; 
 	height:400px; 
	
 }  
	</style>
	
	
</head>
<body>
<!-- 麵包屑 -->
<div class="col-xs-12 col-sm-10 col-sm-offset-1 zindex">
	<ol class="breadcrumb">
		<li>
			<a href="<%=request.getContextPath()%>/front_end/chiacourse/MyGYM.jsp">我的課程</a><br>
		</li>
		<li>新增課程</li>
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
		<c:forEach var="movVO" items="${movSvc.all}">
		<%-- 				<a href="<%=request.getContextPath()%>/back_end/cou/cou.do?action=getOne_For_Display&cou_id="> --%>		
		<div class="col-xs-12 col-sm-3">
			<div class="thumbnail clr size">
				<form method="post" action="<%=request.getContextPath()%>/back_end/cou_det/cou_det.do">			
					<img src="<%=request.getContextPath()%>/DBimgReader_Mov.do?mov_id=${ movVO.mov_id }" id="mov_img" class="img-responsive" style="width: -webkit-fill-available;">
				<div class="caption">
					<h2>${movVO.mov_name}</h2>
					<p>${movVO.mov_info}</p>							
				</div>
					播放順序<input type="text" name="mov_order" size="4">
					播放次數<input type="text" name="mov_play_time" size="4">
					<input type="hidden" name="cou_id" value="${couVO.cou_id}">
					<input type="hidden" name="mov_id" value="${movVO.mov_id}">
					<input type="hidden" name="action" value="insert2">
					<input type="submit" class="btn btn-info" value="此動作加入課程">
				</form>
			<!-- 					</a>	 -->
			</div>
		</div>
		</c:forEach>
	</div>
</div>
			
			


<a href="<%=request.getContextPath()%>/front_end/chiacourse/memCourse.jsp">編輯課程動作</a>
		
<%@ include file="/front_end/footer.jsp" %>		

<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel"></h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
              <h3>動作已加入</h3>
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<!--                 <button type="button" class="btn btn-primary">Save changes</button> -->
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>
</body>
</html>