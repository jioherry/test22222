<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movement.model.*"%>
<%
	MovementVO movementVO = (MovementVO) request.getAttribute("movementOneVO");
	pageContext.setAttribute("movementVO", movementVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<style type="text/css">
	#mov_video_css {
		margin-top: 10px; 
	}
/* 	.modal{ */
/* 		text-align: center; */
/* 		padding: 0!important; */
/* 	} */
/* 	.modal:before{ */
/* 		content: ''; */
/* 		display: inline-block; */
/* 		height: 100%; */
/* 		vertical-align: middle; */
/* 		margin-right: -4px; */
/* 	} */
/* 	.modal .modal-dialog{ */
/* 		display: inline-block; */
/* 		text-align: left; */
/* 		vertical-align: middle; */
/* 	} */
</style>
</head>
<body>
<jsp:include page="/back_end/back_index.jsp" flush="true" />
<div class="container" >
<!-- 動作編號 -->
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="page-header">
				<h1>動作編號：${ movementVO.mov_id }</h1>
			</div>
		</div>
	</div>
<!-- 影片 -->
	<div class="row" id="up">
		<div class="textsize">
			<form method="post" action="<%=request.getContextPath()%>/back_end/mov.do" class="form-horizontal">
			<input type="hidden" name="mov_id" value="${ movementVO.mov_id }">
			<input type="hidden" name="action" value="getOne_For_Update">
				<table class="table table-hover">
					<thead>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label"></label>
								<div class="col-sm-9 col-sm-offset-4" id="mov_video_css">
									<video width="400" height="600" controls loop>
										<source type="video/mp4" src="<%=request.getContextPath()%>/DBvideoReader.do?mov_id=${ movementVO.mov_id }">
									</video>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label">動作名稱：</label>
								<div class="col-sm-9">
									${ movementVO.mov_name }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label">動作簡介：</label>
								<div class="col-sm-9">
									${ movementVO.mov_info }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label">動作等級：</label>
								<div class="col-sm-9">
									${ movementVO.mov_level }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label">時間長度：</label>
								<div class="col-sm-9">
									${ movementVO.mov_time_length }
								</div>
							</td>
						</tr>
						<tr>
							<td style="padding-top: 20px;">
								<div class="col-sm-3 control-label">
									<input class="btn btn-success" type="submit" value="修改">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>

<!-- 跳窗開關 開始 ajax未完成-->
	<%-- 	<form method="post" action="<%=request.getContextPath()%>/back_end/mov/mov.do" style="margin-bottom: 0px;"> --%>
	<!-- 		<a href='#modal-id' data-toggle="modal" class="btn btn-primary" type="submit">修改</a> -->
	<%-- 		<input type="hidden" name="mov_id" value="${ movementVO.mov_id }"> --%>
	<!-- 		<input type="hidden" name="action" value="getOne_For_Update"> -->
	<!-- 	</form> -->
<!-- 跳窗開關 結束 ajax未完成-->

<!-- 跳窗 ajax -->
<!-- <form method="post" enctype="multipart/form-data" class="data"> -->
<%-- 	<input type="hidden" name="mov_id" value="${ movementVO.mov_id }"> --%>
<!-- 	<input type="hidden" name="action" value="update"> -->
<!-- 	<div class="modal fade" id="modal-id"> -->
<!-- 		<div class="modal-dialog"> -->
<!-- 			<div class="modal-content"> -->
<!-- 				<div class="modal-header"> -->
<!-- 					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
<%-- 					<h4 class="modal-title">動作編號：<font color="red"><b>*</b></font>${ movementVO.mov_id }</h4> --%>
<!-- 				</div> -->
<!-- 				<div class="modal-body"> -->
<!-- 					<h5>動作名稱：</h5> -->
<%-- 					<input type="text" name="mov_name" size="50" value="<%=movementVO.getMov_name()%>"/> --%>
<!-- 				</div> -->
<!-- 				<div class="modal-body"> -->
<!-- 					<h5>動作簡介：</h5> -->
<%-- 					<textarea name="mov_info" rows="4" cols="50" placeholder="<%=movementVO.getMov_info()%>"></textarea> --%>
<!-- 				</div> -->
<!-- 				<div class="modal-body"  id="img"> -->
<!-- 					<h5>動作圖片：</h5> -->
<!-- 					<div> -->
<%-- 						<input type="file" class="touch" name="mov_img" size="50" value="<%=movementVO.getMov_img()%>"/> --%>
<!-- 						<img class="preview" src="" id="o1"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="modal-body"> -->
<!-- 					<h5>動作等級：</h5> -->
<%-- 					<input type="text" name="mov_level" size="50" value="<%=movementVO.getMov_level()%>"/> --%>
<!-- 				</div> -->
<!-- 				<div class="modal-body"> -->
<!-- 					<h5>時間長度：</h5> -->
<%-- 					<input type="text" name="mov_time_length" size="50" value="<%=movementVO.getMov_time_length()%>"/> --%>
<!-- 				</div> -->
<!-- 				<div class="modal-body" id="video"> -->
<!-- 					<h5>動作影音：</h5> -->
<%-- 					<input type="file" name="mov_video" size="50" value="<%=movementVO.getMov_video()%>"/> --%>
<!-- 				</div> -->
<!-- 				<div class="modal-footer"> -->
<!-- 					<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button> -->
<!-- 					<button type="button" class="btn btn-primary" type="button" onclick='savaChang(this)'>Save changes</button> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </form> -->

<script type="text/javascript">
// function savaChang(me) {
// 	alert(me);
// 	var formdata = $(me).parents('form').serialize();
// 	console.log(formdata);
//---------------------- 抓圖與影音未完成 ---------------------------------
// 	var img = $(me).parents('#img').find('.touch').files;
// 	console.log(img);
//----------------------------------------------------------------------
// 	$.ajax({ // 想像成 form 表單
// 		type:"POST", // form 的 mtehod
<%-- 		url:"<%=request.getContextPath()%>/back_end/mov/mov.do", // form 的 action 要傳到哪個 controller --%>
// 		data:formdata + img + video,
// 		dataType:"json", // 用 JSON 傳
// 		success: function(data) {
// 			alert("更新成功");
// 			location.reload();
// 		}, error: function() {
// 			alert("AJAX-data發生錯誤!")
// 		}
// 	});
// }

</script>
</body>
</html>