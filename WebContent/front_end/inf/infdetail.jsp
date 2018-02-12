<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.inf.model.*" %>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>

<%
	session.getAttribute("memVO");        
	List<InfVO> list = (List<InfVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="">
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<%= request.getContextPath() %>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css"/>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 		選項做在上方 -->
		<div class="container">
            <div class="row">
            	<div class="col-xs-12 col-sm-6 col-sm-offset-3">
					<ul class="nav nav-tabs">
					  <li><a href="<%=request.getContextPath()%>/inf/inf.do?action=list&inf_cat_id=1">健身知識</a></li>
					  <li><a href="<%=request.getContextPath()%>/inf/inf.do?action=list&inf_cat_id=2">運動保健</a></li>
					  <li><a href="<%=request.getContextPath()%>/inf/inf.do?action=list&inf_cat_id=3">瘦身飲食</a></li>
					  <li><a href="<%=request.getContextPath()%>/inf/inf.do?action=list&inf_cat_id=4">醫療新知</a></li>
					</ul>
		  	</div>
		  </div>
        </div>

		
        <div class="container">
            <div class="row">
                <c:forEach var="infVO" items="${list}">
					<div class="col-xs-12 col-sm-4">
                       <a href="<%=request.getContextPath()%>/inf/inf.do?action=content&inf_id=${infVO.inf_id}" class="thumbnail size">
                           <img src="<%=request.getContextPath()%>/inf/InfImage?inf_id=${infVO.inf_id}" class="img-fluid img-thumbnail" width="200" height="100">
                               <div class="caption">
                                   <h5>${infVO.inf_title}</h5>
                                      <p><fmt:formatDate value="${infVO.inf_date}" pattern="yyyy-MM-dd"/></p>
                               </div>
                       </a>
                    </div>
   				</c:forEach>
            </div>
        </div>


<%@ include file="/front_end/footer.jsp" %>	
</body>
</html>