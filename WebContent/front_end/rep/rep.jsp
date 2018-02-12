    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.inf.model.*"%>




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
<link href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" type="text/css" />


<style type="text/css">
		.size {
			height:290px;
			width:295px;
		
		}
		
		
		*{
		 outline:1px solid red;
		}
	
	</style>



</head>



<body>

<!-- 		modal測試按鈕 -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter" id="rep">檢舉</button>

<!-- 			modal畫面 -->

			<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLongTitle">請輸入檢舉原因</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <textarea rows="5" cols="10" id="rep_cont" style="margin: 0px; height: 78px; width: 567px;"></textarea>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="nomodal">取消</button>
			        <button type="button" class="btn btn-primary" id="btnrep">送出</button>
			      </div>
			    </div>
			  </div>
			</div>
<div class="container">
            <div class="row" id="showPanel">

	<div id="showPanel"></div>
	
	 </div>
        </div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">

		$(document).ready(
				function() {
					$('#btnrep').click(
							function() {
								$.ajax({
									type : "GET",
									url : "<%=request.getContextPath()%>/ajaxper.do",
									data : creatQueryString($('#rep_cont').val()),
									dataType : "json",
									contentType: 'application/x-www-form-urlencoded;charset=UTF-8', 
									success : function(data) {
										$('#nomodal').trigger('click');
										$('#rep').attr('disabled', true);
										showEmployee(data);
										console.log(data);
										
// 										$('#rep').hide();
// 										alert("檢舉成功")
									},
									error : function() {
										alert("AJAX-grade發生錯誤囉!")
										
									}
								})
							})
					
				})

		function creatQueryString(rep_cont) {
			console.log("rep_cont:" + rep_cont);
			var queryString = {
				"action" : "insert",
				"rep_cont" : rep_cont,
				
			};
			console.log(queryString);
			return queryString;
		}
		
		function showEmployee(jsonStr){   
			  //剖析json字串,將其轉成jsob物件
// 			  var emp = JSON.parse( jsonStr );
				
// 			  var str = "<table class='empTable' align='center'>";
// 			  str += "<tr><td class='mainTitle'>姓名</td><td class='mainData'>"+ jsonStr.infList[0].inf_id+"</td></tr>";
// 			  str += "<tr><td class='mainTitle'>職稱</td><td class='mainData'>"+ jsonStr.infList[0].emp_id +"</td></tr>";
<%-- 			  str += "<tr><td class='mainTitle'>到職日</td><td class='mainData'>"+"<img src='<%=request.getContextPath()%>/inf/InfImage?inf_id="+jsonStr.infList[0].inf_id+"\' class='img-fluid'>"+"</td></tr>"; --%>
// 			  str += "</table>"; 
			 
			  var test = JSON.stringify(jsonStr.infList[0].inf_date);
			  
// 			  轉日期

			
			  var str="";
			  for(var i=0; i<jsonStr.infList.length;i++){
			  
			  
			  var timestamp = jsonStr.infList[i].inf_date.time;
			  date = new Date(timestamp );
			  
			  str +="<div class='col-xs-12 col-sm-4'>";
			  str += "<a href='<%=request.getContextPath()%>/inf/inf.do?action=content&inf_id="+jsonStr.infList[i].inf_id+"\' class='thumbnail size'>";
			  str += "<img src='<%=request.getContextPath()%>/inf/InfImage?inf_id="+jsonStr.infList[i].inf_id+"\' class='img-fluid img-thumbnail' width='200' height='100'>";
			  str += "<div class='caption'>";
			  str += "<h5>"+jsonStr.infList[i].inf_title+"</h5>";
			  str += " <p>"+ date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"</p>";
			  str += "</div></a></div>";
			  console.log(str);

			  }
			  console.log(str);
			  document.getElementById("showPanel").innerHTML = str;

			  
			}
		
		
	</script>

</body>
</html>