<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="com.inf.model.*"%>
<%@ page import="java.util.*"%>
<%
    InfService infSvc = new InfService();
    List<InfVO> list = infSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/back_end/back_index.jsp" flush="true" />
<style type="text/css">

</style>
<body>
<!-- container -->
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-12">
      <div class="page-header">
        <h1>相關知識</h1>
      </div>
    </div>
  </div>
  
  <%@ include file="page1.file" %> 
  <div class="row" id="up">
    <div class="textsize">
    
      <table class="table table-hover">
        <thead>
          <tr>
            <th>標題</th>
            <th>圖片</th>
            <th>日期</th>
            <th>修改</th>
            <th>刪除</th>
          </tr>
        </thead>

        <tbody>
        <c:forEach var="infVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
          <tr>
            <td>
              ${infVO.inf_title}
            </td>
            <td>
              <img src="<%=request.getContextPath()%>/InfImage.do?inf_id=${ InfVO.inf_id }" class="img-fluid" width="40" height="40">
            </td>
            <td>
              <fmt:formatDate value="${infVO.inf_date}" pattern="yyyy-MM-dd"/>
            </td>
            <td>
              <form method="post" action="<%=request.getContextPath()%>/inf/inf.do" style="margin-bottom: 0px;">
                <input type="submit" value="修改" class="btn btn-success">
                <input type="hidden" name="inf_id"  value="${infVO.inf_id}">
                <input type="hidden" name="action" value="getOne_For_Update">
              </form>
            </td>
            <td>
              <form method="post" action="<%=request.getContextPath()%>/inf/inf.do" style="margin-bottom: 0px;">
                <input type="submit" value="刪除" class="btn btn-danger">
                <input type="hidden" name="inf_id"  value="${infVO.inf_id}">
                <input type="hidden" name="action" value="delete">
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