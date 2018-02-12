<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>


<%
	CouService couSvc = new CouService();
	CouVO couVO = (CouVO) request.getAttribute("couVO");
	String mem_acct = (String) session.getAttribute("mem_acct");
	session.setAttribute("mem_acct", mem_acct);
	
	MemService memSvc = new MemService();
	
	List<CouVO> list_Cou = couSvc.getfindByPK(memSvc.getOneMemByAcct(mem_acct).getMem_id());
	
	pageContext.setAttribute("list_Cou", list_Cou);
	
  
%>
<!-- EL寫法可省去很多雜項 -->
<%-- ${couVO.cou_id}; --%>

<!DOCTYPE html>
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<style>
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 800px;
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
.snb{
	margin-top:40px;
}
</style>
</head>
<body>
	<!-- 麵包屑 -->
	<div class="col-xs-12 col-sm-10 col-sm-offset-1 zindex">
		<ol class="breadcrumb">
			<li>
				我的課程
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/addCourse.jsp">新增課程</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/plan/myplan.jsp">我的計畫</a>
			</li>
<!-- 			<li> -->
<%-- 				<a href="<%=request.getContextPath()%>/front_end/chiacourse/memCourse.jsp">編輯課程動作</a> --%>
<!-- 			</li> -->
			<li>
<%-- 			<a href="<%=request.getContextPath()%>/front_end/chiacourse/listmemcoumovajax.jsp">前往觀看課程動作</a> --%>
			<a href="<%=request.getContextPath()%>/front_end/chiacourse/playlist.jsp">課程動作明細</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/listMemCourse.jsp">官方課程</a>
			</li>
		</ol>
	</div>

<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/cou_det/cou_det.do" name="form1" enctype="multipart/form-data"> --%>
<%-- <input type="hidden" name="cou_cat_id" size="45" value="<%= (couVO==null)? "C0005" : couVO.getCou_cat_id()%>"/> --%>
<!-- 		<input type="hidden" name="mem_id" size="45" value="7002" /> -->
<!-- 		<input type="hidden" name="cou_permi" size="45" value="parivate" /> -->
<!-- 		<input type="hidden" name="dis_state" size="45" value="open" /> -->
<%-- 		<input type="hidden" name="cou_int" size="45" value="<%= (couVO==null)? "20" : couVO.getCou_int()%>"/>	 --%>
<%-- 		<input type="hidden" name="cited_count" size="45" value="<%= (couVO==null)? "Easy" : couVO.getCited_count()%>"/> --%>
<%-- 		<input type="hidden" name="cou_cal_cns" size="45" value="<%= (couVO==null)? "100" : couVO.getCou_cal_cns()%>"/> --%>
<%-- 		<input type="hidden" name="cou_time_length" size="45" value="<%= (couVO==null)? "1000" : couVO.getCou_time_length()%>"/> --%>


<div class="container">
	<div class="row">
		<div class=" col-xs-12 col-sm-12>">
			<c:forEach var="couVO" items="${list_Cou}">
				<a href="<%=request.getContextPath()%>/back_end/cou/cou.do?action=getOne_For_Display&cou_id=${couVO.cou_id}">
					<div class=" col-xs-12 col-sm-2 categorylock">
						<div class="thumbnail">
							<form method="post" action="<%=request.getContextPath()%>/back_end/cou_det/cou_det.do">							
								<img src="<%=request.getContextPath()%>/DBGifReader_Cou.do?cou_id=${couVO.cou_id}" id="cou_img" class="img-responsive">
									<div class="caption" style="height:400px">
										<h2>${couVO.cou_name}</h2>
										<p>${couVO.cou_intor}</p>							
									</div>
								<input type="hidden" name="cou_id" value="${couVO.cou_id}">
								<input type="hidden" name="action" value="docourse2">
								<button type="submit" class="btn btn-success" style="margin-top:30px;" value="前往加入動作">前往加入動作</button>
							</form>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
</div>
		
		<c:if test="${list.isze() == 0 }">
			尚無內容
		</c:if>


<%@ include file="/front_end/footer.jsp" %>
</body>
</html>