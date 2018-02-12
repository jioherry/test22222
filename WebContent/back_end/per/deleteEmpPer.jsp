<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
    PerService perSvc = new PerService();
    List<PerVO> list = perSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->





</head>
<body>



	<!-- navbar用include的     -->
<%--     <jsp:include page="/back_end/back_navbar_2.jsp" flush="true" /> --%>
	
	
	


	<!-- 這是後台頁面-->
	<div class="container">
		<div class="row">
		  <div class="col-xs-12 col-sm-3">
<!--             左邊選項 -->
<%--             <jsp:include page="/back_end/back_left.jsp" flush="true" /> --%>


	      </div>
	
<!-- 	**************************************************************************** -->
		<div class="col-xs-12 col-sm-9">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>


	

<table>
    <tr>
        <th>員工編號</th>
        <th>員工權限</th>
        
        <th>修改</th>
        <th>刪除</th>
    </tr>
    <c:forEach var="perVO" items="${list}">
        
        <tr>
            <td>${perVO.emp_id}</td>
            <td>${perVO.fun_id}</td>
            
            
   
            <td>
              <FORM METHOD="post" ACTION="per.do" style="margin-bottom: 0px;">
                 <input type="submit" value="刪除">
                 <input type="hidden" name="emp_id"  value="${perVO.emp_id}">
                 <input type="hidden" name="fun_id"  value="${perVO.fun_id}">
                 <input type="hidden" name="action" value="delete"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

					

<!-- *********************************************************************************** -->
		</div>
      </div>
   </div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>