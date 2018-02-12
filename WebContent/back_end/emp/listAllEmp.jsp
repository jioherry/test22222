<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/back_end/back_index.jsp" flush="true" />
<style type="text/css">

</style>
</head>
<body>
<!-- container -->
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-12">
      <div class="page-header">
        <h1>員工查詢</h1>
      </div>
    </div>
  </div>
  
  <%@ include file="page1.file" %> 
  <div class="row">
    <div class="textsize">
    
      <table class="table table-hover">
        <thead>
          <tr>
            <th>員工編號</th>
            <th>員工帳號</th>
            <th>員工密碼</th>
            <th>員工姓名</th>
            <th>員工信箱</th>
<!--             <th>員工身分</th> -->
            <th>資料修改</th>
          </tr>
        </thead>

        <tbody>
        <c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
          <tr>
            <td>${empVO.emp_id}</td>
            <td>${empVO.emp_acct}</td>
            <td>${empVO.emp_psw}</td>
            <td>${empVO.emp_name}</td>
            <td>${empVO.emp_email}</td>
<%--             <td>${empVO.emp_role}</td> --%>
            <td>
              <form method="post" action="<%=request.getContextPath()%>/back/emp/emp.do" style="margin-bottom: 0px;">
                <input type="submit" value="修改" class="btn btn-success">
                <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
                <input type="hidden" name="action" value="getOne_For_Update">
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      
    </div>
  </div>
  <%@ include file="page2.file" %>
  
</div>  
</body>
</html>