<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
// 	pageContext.setAttribute("list",list);
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
		<th>員工帳號</th>
		<th>會員管理</th>
		<th>員工管理</th>
		<th>健身動作管理</th>
		<th>健身課程管理</th>
		<th>檢舉管理</th>
		<th>相關資訊管理</th>
	</tr>
<%--     <c:forEach var="empVO" items="${list}"> --%>

    <%for(EmpVO empVO:list){ 
    
        if(!empVO.getEmp_role().equals("有權限")){
    %>
    <FORM METHOD="post" ACTION="per.do" name="form1">
    <tr><td><%=empVO.getEmp_acct()%></td>
<%--     <tr><td>${empVO.emp_acct}</td> --%>
    
    
    <td><input type="checkbox" id="coding" name="fun_id" value="9001"></td>
    <td><input type="checkbox" id="coding" name="fun_id" value="9004"></td>
    <td><input type="checkbox" id="coding" name="fun_id" value="9005"></td>
    <td><input type="checkbox" id="coding" name="fun_id" value="9006"></td>
    <td><input type="checkbox" id="coding" name="fun_id" value="9003"></td>
    <td><input type="checkbox" id="coding" name="fun_id" value="9002"></td>
    <td><input type="submit" value="送出">
    <input type="hidden" name="emp_id" value="<%=empVO.getEmp_id()%>">
    <input type="hidden" name="action" value="addPer">
<!--     <input type="hidden" name="action" value="addPer"> -->
    </td>
    </tr>
    </form>
    <%}}%>
<%-- </c:forEach> --%>
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