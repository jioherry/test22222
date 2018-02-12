<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>


<%	
	session.getAttribute("memVO");
	CouService couSvc = new CouService();
	Map<String, String[]> map = new TreeMap<String, String[]>();
	map.put("mem_id", new String[] { "7001" });
	List<CouVO> list = couSvc.oneMemberCous(map);
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->	
<style>
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

</style>	
</head>
<body>	
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-3 col-sm-offset-6">
						<select id="couid">
							<option value="-1">請選擇</option>
							<c:forEach var="couVO" items="${list}">
								<option value="${couVO.cou_id}">${couVO.cou_id}</option>
							</c:forEach>
						</select>
					<div class="row" id="showPanel"></div>
				</div>
			</div>
		</div>
		
		<%@ include file="/front_end/footer.jsp" %>	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			 $('#couid').change(function(){
				 $.ajax({
					 type: "GET",
					 url: "<%=request.getContextPath()%>/chiacou.do",
					 data: {"action":"getmov"},
					 dataType: "json",
					 success: function (data){
						console.log(data);
						$.each(data, function(i, item){
							$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
						});
				     },
		             error: function(){alert("AJAX-grade發生錯誤囉!")}
		         })
			 })
		})
	
	</script>
</body>
</html>