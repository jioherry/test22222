<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html lang="">
<head>

</head>
<body>
<!-- navbar用include的 -->
<jsp:include page="/back_end/back_index.jsp" flush="true" />
<!-- container -->
<div class="container">
  <div class="page-header">
    <h1>員工：<%=empVO.getEmp_id()%></h1>
  </div>

  <div class="row" id="up">
    <div class="textsize">
      
      <form method="post" action="<%=request.getContextPath()%>/back/emp/emp.do" class="form-horizontal">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="emp_id" value="<%=empVO.getEmp_id()%>">
		<input type="hidden" name="emp_role" value="<%=empVO.getEmp_role()%>">
        <table class="table table-hover">
          <thead>
            <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">員工帳號：</label>
                <div class="col-sm-9">
                  <input type="text" name="emp_acct" size="50" value="<%=empVO.getEmp_acct()%>" class="from-control"/>
                </div>
              </td>
            </tr>
          </thead>
          <tbody>
       	<jsp:useBean id="inf_catSvc" scope="page" class="com.inf_cat.model.Inf_catService" />
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">員工密碼：</label>
                <div class="col-sm-9">
                  <input type="text" name="emp_psw" size="50" value="<%=empVO.getEmp_psw()%>" class="from-control"/>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">員工姓名：</label>
                <div class="col-sm-9">
                  <input type="text" name="emp_name" size="50" value="<%=empVO.getEmp_name()%>" class="from-control"/>
                
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">員工信箱：</label>
                <div class="col-sm-9">
                  <input type="text" name="emp_email" size="50" value="<%=empVO.getEmp_email()%>" class="form-control"/>
                </div>
              </td>
            </tr>
            <tr>
              <td style="padding-top: 20px;">
                <div class="col-sm-3 control-label">
                  <input class="btn btn-success" type="submit" value="送出新增">
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </form>

    </div>
  </div>

</div>
</body>
</html>