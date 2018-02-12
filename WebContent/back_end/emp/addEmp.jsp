<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<!DOCTYPE html>
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<!-- navbar用include的 -->
<jsp:include page="/back_end/back_index.jsp" flush="true" />
<!-- container -->
<div class="container">
  <div class="page-header">
    <h1>新增員工</h1>
  </div>

  <div class="row" id="up">
    <div class="textsize">
      
      <form method="post" action="<%=request.getContextPath()%>/back/emp/emp.do" class="form-horizontal">
      <input type="hidden" name="action" value="insert">
        <table class="table table-hover">
          <thead>
            <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">員工帳號：</label>
                <div class="col-sm-9">
                  <input type="text" name="emp_acct" id="acct" size="50" value="<%= (empVO==null)? "" : empVO.getEmp_acct()%>" class="from-control"/>
                </div>
              </td>
            </tr>
          </thead>
          <tbody>
            <jsp:useBean id="inf_catSvc" scope="page" class="com.inf_cat.model.Inf_catService" />
              <tr>
                <td>
                  <label for="aa" class="col-sm-3 control-label">員工姓名：</label>
                  <div class="col-sm-9">
                    <input type="text" name="emp_name" id="name" size="50" value="<%= (empVO==null)? "" : empVO.getEmp_name()%>" class="from-control"/>
                  </div>
                </td>
              </tr>
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">員工信箱：</label>
                <div class="col-sm-9">
                  <input type="text" name="emp_email" id="email" size="50" value="<%= (empVO==null)? "" : empVO.getEmp_email()%>" class="form-control"/>
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
      <input type="hidden" name="emp_role" size="45" value="<%=(empVO==null)?"無權限":empVO.getEmp_role()%>"/>
      <!-- 沒用到，有欄位，不送會報錯 -->
      </form>

    </div>
  </div>

</div>
<div>
                <button class="btn" onclick="myFunction()">

                    <span>按我</span>

                </button>
            </div>
            
            
            <script type="text/JavaScript">
function myFunction() {
	 document.getElementById("acct").value = "NEW";
     document.getElementById("name").value = "新員工";
     document.getElementById("email").value = "ba105g3g3@gmail.com";
}

</script>
</body>
</html>






