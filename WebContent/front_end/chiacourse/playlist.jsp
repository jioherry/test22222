<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.cou_cat.model.*"%>
<%@ page import="com.cou_det.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
	
	
<%
	CouService couSvc = new CouService();
	session.getAttribute("memVO");
	Map<String, String[]> map = new TreeMap<String, String[]>();
    map.put("mem_id", new String[] {memVO.getMem_id().toString()});
    List<CouVO> list = couSvc.oneMemberCous(map);
    pageContext.setAttribute("list",list);

%>
	
<jsp:useBean id="cou_detSvc" scope="page" class="com.cou_det.model.CouDetService" />



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
/* 	.size { */
/* 			height:280px; */
/* 			width:195px; */
		
/* 		} */
/* 	.textsize { */
/* 		background-color:#fff; */
/* 		font-size: 18px; */
/* 		border: 2px solid	#FFF; */
/* 		border-radius: 15px; */
/* 		box-shadow: 2.5px 5.5px 2.5px #000; */
/* 		width:60%; */
/* 		margin-left:auto;  */
/* 		margin-right:auto; */
/* 		margin-top:80px; */
		
/* 	} */
/* 	#up { */
/* 	  padding: 10px; */
/* 	  margin-bottom: 50px; */
/* 	} */
/* 	#o1 { */
/* 	  width: 200px; */
/* 	  height: 200px; */
/* 	} */
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
				新增課程
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
				<div class="col-xs-12 col-sm-6 col-sm-offset-3">
					
				
<!-- 			動作表格 -->
				
<!-- 				<table class="table table-striped"> -->
<!-- 				<tr> -->
<%-- 					<td>${couVO.cou_id}</td> --%>
				
<!-- 				</tr> -->
<%-- 				<c:forEach var="couVO" items="${coulist}"> --%>
<%-- 					<c:forEach var="coudetVO" items="${cou_detSvc.getfindByCouid(couVO.getCou_id())}"> --%>
<%-- 						<tr><td>${couVO.cou_id}</td> --%>
<%-- 						<td>${coudetVO.mov_id}</td> --%>
<%-- 						<td>${coudetVO.mov_order}</td> --%>
<!-- 						<td>播放</td></tr> -->
					
<%-- 					</c:forEach> --%>
<%-- 				</c:forEach> --%>
<!-- 				</table> -->
						
						課程：
						<select id="cou">
							<option value="-1">請選擇</option>
							<c:forEach var="couVO" items="${list}">
								<option value="${couVO.cou_id}">${couVO.cou_name}</option>
								
								
							</c:forEach>
						</select>
						動作影片：
						<select id="mov" onchange="movPlay($(this).val());">
							<option value="-1">請選擇</option>
						</select>
						影片測試
						
<!-- 								<video id="changeVideo" width="400" height="600" controls="" loop=""> -->
<!-- 									<source type="video/mp4"> -->
<!-- 								</video> -->
								
<!-- 								<video width="400" height="600" controls="" loop=""> -->
<!-- 									<source type="video/mp4" src="/BA105G3_Course_01-18/DBvideoReader.do?mov_id=M000000043"> -->
<!-- 								</video> -->
								
								<div class="play">
								
								</div>
								
<!-- 								<video width='400' height='600' controls='' loop=''><source type='video/mp4' src='/BA105G3_Course_01-18/DBvideoReader.do?mov_id=M000000043'></video> -->
				</div>
			</div>
        </div>
        

		
<%@ include file="/front_end/footer.jsp" %>	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			 $('#cou').change(function(){
				 $.ajax({
					 type: "GET",
					 url: "<%=request.getContextPath()%>/chiacou.do",
					 data: creatQueryString($(this).val()),
					 dataType: "json",
					 success: function (data){
						 console.log(data);
						clearSelect();
						$.each(data, function(i, item){
							$('#mov').append("<option value='"+item.mov_id+"'>"+item.mov_id+"</option>");
						});
//	 					$(data).each(function(i, item){
//	 						$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//	 					});
//	 					jQuery.each(data, function(i, item){
//	 						$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//	 					});
				     },
		             error: function(){alert("AJAX-grade發生錯誤囉!")}
		         })
			 })
			 
		})
		
		function clearSelect(){
				$('#mov').empty();
				$('#mov').append("<option value='-1'>請選擇</option>");
        }
			 
		function creatQueryString(cou_id){
			console.log("cou_id:"+cou_id);
			var queryString= {"action":"listcou", "cou_id":cou_id};
			return queryString;
		}
		
		
		
		function movPlay(mov_id){
			var strmov="";
			strmov +="<video width='400' height='600' controls='' loop=''>";
			strmov +="<source type='video/mp4' src='<%=request.getContextPath()%>/DBvideoReader.do?mov_id="+mov_id+"'>";
			strmov +="</video>";
			console.log(strmov);
			$( ".play" ).append(strmov);
		}
		
		
			 
		
		</script>
</body>
</html>