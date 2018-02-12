<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan.model.*"%>


<%
  PlanVO planVO = (PlanVO) request.getAttribute("planVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%= request.getContextPath() %>/front_end/css/showplan.css" rel="stylesheet" type="text/css"/>
<title>計畫新增 - addPlan.jsp</title>
</head>
<body bgcolor='white'>


<nav class="navbar navbar-default" role="navigation">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				</a>
			</div>
		
			<!-- 手機隱藏選單區 -->
			<div class="container">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 左選單 -->
					<ul class="nav navbar-nav">
						<a class="navbar-brand" href="#"><img src="<%= request.getContextPath() %>/front_end/images/logo.png">
						<li><a href="#">課程</a></li>
						<li><a href="#">我的健身房</a></li>
						<li><a href="#">商城</a></li>
						<li><a href="#">相關資訊</a></li>
					</ul>
						
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">註冊</a></li>
						<li><a href="#">登入</a></li>
					</ul>
				</div>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
	<!-- 麵包屑 -->
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<ol class="breadcrumb">
			<li>
				<a href="#">我的課程</a>
			</li>
			<li>
				<a href="#">建立課程</a>
			</li>
			<li>
				<a href="#">我的計畫</a>
			</li>
			<li class="active">建立計畫</li>	
			<li>
				<input type="button" class="btn-primary" data-toggle="modal" data-target="#exampleModal" value="建立計畫">
			</li>			
		</ol>
	</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">輸入資料</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" name="form1">
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">會員標號:</label>
            <input type="TEXT" name="mem_id" size="20" 
				 value="<%= (planVO==null)? "7001" : planVO.getMem_id()%>" />
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">計畫名稱:</label>
            <input type="TEXT" name="plan_name" size="20"
				 value="<%= (planVO==null)? "ABC" : planVO.getPlan_name()%>" />
          </div>
          <input name="startdate" id="f_date1" type="hidden">
          <input type="hidden" name="shareplan" value="on" />
          <input type="hidden" name="interval" size="45" value="0" />
          <input type="hidden" name="cycle" size="45" value="0" />
      <div class="modal-footer">
        <input type="hidden" name="action" value="insert">
		<input type="submit" class="btn-primary" value="新增">
      </div>
        </form>
      </div>
      
    </div>
  </div>
</div>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" name="form1">
	<table align="center">
		<tr>
			<td>會員編號:</td>
			<td><input type="TEXT" name="mem_id" size="20" 
				 value="<%= (planVO==null)? "7001" : planVO.getMem_id()%>" /></td>
		</tr>
		<tr>
		<td>計畫名稱:</td>
			<td><input type="TEXT" name="plan_name" size="20"
				 value="<%= (planVO==null)? "ABC" : planVO.getPlan_name()%>" /></td>
		</tr>
		
		<tr>
<!-- 			<td>開始日期:</td> -->
			<td><input name="startdate" id="f_date1" type="hidden"></td>
		</tr>
		<tr>
		
<!-- 		<td>是否公開:</td> -->
<!-- 			<td> -->
<!-- 				<select name="shareplan" > -->
<!-- 		      	<option value="ON">ON</option> -->
<!-- 		      	<option value="OFF">OFF</option> -->
<!-- 		   		</select> -->
<!-- 	   		</td> -->
	   		<td><input type="hidden" name="shareplan" value="on" /></td>
		</tr>
		
		<tr>
<!-- 			<td>間隔天數:</td> -->
<!-- 			<td> -->
<!-- 				<select name="interval" > -->
<!-- 		      	<option value="0">0</option> -->
<!-- 		      	<option value="1">1</option> -->
<!-- 		      	<option value="2">2</option> -->
<!-- 		      	<option value="3">3</option> -->
<!-- 		   		</select> -->
<!-- 	   		</td> -->
		<td><input type="hidden" name="interval" size="45"
			 value="0" /></td>
		</tr>
		
		<tr>
<!-- 			<td>循環次數:</td> -->
<!-- 			<td> -->
<!-- 				<select name="cycle" > -->
<!-- 		      	<option value="0">0</option> -->
<!-- 		      	<option value="1">1</option> -->
<!-- 		      	<option value="2">2</option> -->
<!-- 		      	<option value="3">3</option> -->
<!-- 		   		</select> -->
<!-- 	   		</td> -->
		<td><input type="hidden" name="cycle" size="45"
			 value="0" /></td>
		</tr>
	
	
	</table>
	
	<input type="hidden" name="action" value="insert">
	<input type="submit" class="btn-primary" value="新增">
</FORM>
</div>

		<div class="container-fluid footer">
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<a href="#">關於我們</a>｜
					<a href="#">客服中心</a>
				</div>
				<div class="col-xs-12 col-sm-4">
					
				</div>
				<div class="col-xs-12 col-sm-4">
					
				</div>
			</div>
		</div>


</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date startdate = null;
  try {
	  startdate = planVO.getStartdate();
   } catch (Exception e) {
	  startdate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=startdate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

//              1.以下為某一天之前的日期無法選擇
             var somedate1 = new Date();
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>