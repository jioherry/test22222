<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="java.util.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
		  <div class="col-xs-12 col-sm-9">	
<!-- 	****************** -->
				
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_id()%></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="mem_acct" size="45" 
			 value="<%= (memVO==null)? "mana" : memVO.getMem_acct()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="PASSWORD" name="mem_psw" size="45"
			 value="<%= (memVO==null)? "MANAGER" : memVO.getMem_psw()%>" /></td>
	</tr>
	
	<tr>
		<td>電子信箱:</td>
		<td><input type="TEXT" name="mem_email" size="45"
			 value="<%= (memVO==null)? "abc@gmail.com" : memVO.getMem_email()%>" /></td>
	</tr>
	<tr>
		<td>手機號碼:</td>
		<td><input type="TEXT" name="mem_phone" size="45"
			 value="<%= (memVO==null)? "0912345678" : memVO.getMem_phone()%>" /></td>
	</tr>
	<tr>
		<td>照片:</td>
		<td><input type="file" name="mem_pic" size="45"
			 value="<%= (memVO==null)? memVO.getMem_pic() : memVO.getMem_pic()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="mem_name" size="45"
			 value="<%= (memVO==null)? "王想名" : memVO.getMem_name()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="radio" name="mem_gender" value="男" checked>男
  			<input type="radio" name="mem_gender" value="女" >女<br>
			
			 </td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="mem_add" size="45"
			 value="<%= (memVO==null)? "一條路3號" : memVO.getMem_add()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td></td>
	</tr>

	

</table>

<input type="hidden" name="mem_bonus" size="45" value="<%= (memVO==null)? "0" : memVO.getMem_bonus()%>" />
<input type="hidden" name="mem_title" size="45" value="<%= (memVO==null)? "初學者" : memVO.getMem_title()%>" />
<input type="hidden" name="mem_exp" size="45" value="<%= (memVO==null)? "0" : memVO.getMem_exp()%>" />
<input type="hidden" name="mem_repno" size="45" value="<%= (memVO==null)? "0" : memVO.getMem_repno()%>" />
<input type="hidden" name="mem_status" size="45" value="<%= (memVO==null)? "正常" : memVO.getMem_status()%>" />



<br>
<input type="hidden" name="action" value="update_back">
<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
<input type="submit" value="送出修改"></FORM>


					

<!-- **************************** -->
		</div>
      </div>
   </div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>