<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.cou_det.model.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.cou_cat.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  session.getAttribute("memVO");
  CouVO couVO = (CouVO) request.getAttribute("couVO"); //CouServlet.java(Concroller), 存入req的couVO物件
  CouService couSvc = new CouService();
  Set<CouDetVO> set = couSvc.getCouDetsByCouID(couVO.getCou_id());
  List<MovementVO> moveList = new ArrayList();
  for (CouDetVO couDetVO : set) {
	  MovementVO movementVO = couSvc.getMovementByCouId(couDetVO);
	  moveList.add(movementVO);
  }
  request.setAttribute("list", moveList);
  CouCatService cou_catSvc = new CouCatService();
  CouCatVO cou_catVO = cou_catSvc.getOneCouCat(couVO.getCou_cat_id());
%>


<html>
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%= request.getContextPath() %>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css"/>
	<link href='<%= request.getContextPath() %>/front_end/css/fullcalendar.min.css' rel='stylesheet' />
	<link href='<%= request.getContextPath() %>/front_end/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='<%= request.getContextPath() %>/front_end/js/moment.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/jquery.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/jquery-ui.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/fullcalendar.min.js'></script>
<style>
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 600px;
	margin-left: 0px;
	background: -webkit-linear-gradient(left top,steelblue,paleturquoise);
	background: -o-linear-gradient(bottom right,steelblue,paleturquoise);
	background: -moz-linear-gradient(bottom right,steelblue,paleturquoise);
	background: linear-gradient(to bottom right,steelblue,paleturquoise);
	}
.breadcrumb{
	background:none;
	}
#fontSize{
	font-size:19px;
	font-color:#fff;
	font-family: "Microsoft YaHei";
}

#titleSize{
	font-size:21px;
	font-color:#fff;
	font-family: "Microsoft YaHei";
}
</style>
</head>
<body>

<div class="container" style="font-">
	<div class="col-xm-12 col-sm-6 col-sm-offset-3">
		<div class="row">
			<form action="" class="form-horizontal">
				<table class="table">
					<thead>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label" id="titleSize">課程名稱：</label>
								<div class="col-sm-9" id="fontSize">
									<%=couVO.getCou_name()%>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label" id="titleSize">課程介紹：</label>
								<div class="col-sm-9" id="fontSize">
									<%=couVO.getCou_intor()%>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<label for="aa" class="col-sm-3 control-label" id="titleSize">動作影片：</label>
								<div class="col-sm-9" id="fontSize">
									<c:forEach var="movementVO" items="${list}" >
										<video class="preview_video" width="300" style="background:#000; margin-bottom:20px; width:100%;" id="mov_video" controls>
											<source type="video/mp4" src="<%=request.getContextPath()%>/DBvideoReader.do?mov_id=${ movementVO.mov_id }" id="o2">
										</video>
									</c:forEach>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>

<%@ include file="/front_end/footer.jsp" %>	

<script type="text/javascript">
window.onload = function() { // 網頁開啟即載入
	//影音依序撥放----開始
	var i=0;
	var myVideo = document.getElementsByTagName("video")[0];
	myVideo.onended = reload;
	
	function reload() {
	myVideo = document.getElementsByTagName("video")[i];
	i++;
	
	myVideo = document.getElementsByTagName("video")[i];
	myVideo.play();
	myVideo.onended = reload;
	}
	//影音依序撥放----結束
}
</script>
</body>
</html>