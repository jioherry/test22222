<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.movement.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>

<%	
	session.getAttribute("memVO");
	CouService couSvc = new CouService();
	Map<String, String[]> map = new TreeMap<String, String[]>();
	String mem_id = memVO.getMem_id().toString();
    map.put("mem_id", new String[] { mem_id });
    List<CouVO> list = couSvc.oneMemberCous(map);
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
<head>
	<title>Fit Style</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link href="<%= request.getContextPath() %>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />

<style>
.semi-square {
   -webkit-border-radius: 5px;
   -moz-border-radius: 5px;
   border-radius: 5px;
 
}
.blue  { background-color: #3b8ec2;}
.blue select { color: #fff;}
.blue option { color: #000;}
.styled-select select {
   background: transparent;
   border: none;
   font-size: 14px;
   height: 29px;
   padding: 5px; /* If you add too much padding here, the options won't show in IE */
   width: 100%;
}
</style>
<style>
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	background: -webkit-linear-gradient(left top,steelblue,paleturquoise);
	background: -o-linear-gradient(bottom right,steelblue,paleturquoise);
	background: -moz-linear-gradient(bottom right,steelblue,paleturquoise);
	background: linear-gradient(to bottom right,steelblue,paleturquoise);
	
	}
.breadcrumb{
	background:none;
	}
	.zindex{
		z-index:9999;
	}	
	
	.size {  
 	width:250px; 
 	height:350px; 
	
 }  
</style>
</head>
<body>

	<!-- 麵包屑 -->
	<div class="col-xs-12 col-sm-10 col-sm-offset-1 zindex">
		<ol class="breadcrumb">
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/MyGYM.jsp">我的課程</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/addCourse.jsp">新增課程</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/memCourse.jsp">編輯課程動作</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/plan/myplan.jsp">我的計畫</a>
			</li>
			<li>
			<a href="<%=request.getContextPath()%>/front_end/chiacourse/listmemcoumovajax.jsp">前往觀看課程動作</a>
<%-- 			<a href="<%=request.getContextPath()%>/front_end/chiacourse/playlist.jsp">課程動作明細</a> --%>
			</li>
		</ol>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3 ">
			<div class="styled-select blue semi-square">
				<select id="couid" onchange="ajaxUpdate(this)">
					<option value="-1">請選擇</option>
					<c:forEach var="couVO" items="${list}">
						<option value="${couVO.cou_id}">${couVO.cou_name}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			<div class="col-xs-12 col-sm-9">
				<div id="showPanel"></div>
			</div>
			
		</div>
	</div>

	<%@ include file="/front_end/footer.jsp" %>	
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
			function ajaxUpdate(select) {
				console.log("fffffffffffeweeeeeeeeeeeee");
		    var xhr;
		    if (window.XMLHttpRequest) {
		        xhr = new XMLHttpRequest();
		    } else if (window.ActiveXObject) {
		        xhr = new ActiveXObject('Microsoft.XMLHTTP');
		    }
		    xhr.onreadystatechange = function() {
                console.log(xhr.readyState);
                console.log(xhr.status);
		    	if (xhr.readyState == 4) {
		            if (xhr.status == 200) {
// 		            	console.log(xhr.status);
		            	var data = JSON.parse(xhr.responseText);
		            	
		            	showData(data, $(select).val());
// 		                document.getElementById('serverPush_Data').innerHTML = xhr.responseText;
		            }
		        }
		    };
		    xhr.open('GET', '<%=request.getContextPath()%>/back_end/cou/cou.do?action=memcou');
		    xhr.send(null);
		}
		
		function showData(jsonStr, cou_id){   
// 		列出課程清單	 
			  var str="";
			  for(var i=0; i<jsonStr.infList.length;i++){
			  
//				  轉日期
// 			  var timestamp = jsonStr.infList[i].inf_date.time;
 			 // date = new Date(timestamp );
			  
<%-- 			  <form method="post" action="<%=request.getContextPath()%>/back_end/cou/cou.do"> --%>
			  
// 			  	<input text="hidden" name="mov_id" value="jsonStr.infList[i].mov_id">
// 			  	<input text="hidden" name="cou_id" value="C000001">
// 			  	<input text="hidden" name="action" value="insert">
// 			  </form>
			  
			  str +="<div class='col-xs-12 col-sm-4'>";
			  str +="<div class='thumbnail size'>";
			  str +="<img src=''#' alt=''>";
			  str +="<div class='caption'>";
			  str +="<form>";
// 			  str +="<input type='hidden' name='cou_id' value='C000001'>";
			  str +="<input type='hidden' name='cou_id' value='" + cou_id + "'>";
			  str +="<input type='hidden' name='mov_id' value='"+jsonStr.infList[i].mov_id+"'>";
			  str +="<h2>"+jsonStr.infList[i].mov_name+"</h2>";
			  str +="<p>"+jsonStr.infList[i].mov_info+"</p>";
			  
			  str +="<p>";
			  str +="<input type='button' class='btn btn-info' onclick='addCou(this);' value='加入課程清單'>";
			  
			 
			  str +="<div>動作順序<input name='mov_order'></div>";
			  str +="<div>播放次數<input name='mov_play_time'></div>";
			  str+="<input type='hidden' name='action' value='insert_chia'>";
			  
			  str += "</form>";
// 			  str +="<a href='#' class='btn btn-default'>下次再買</a>";
			  str +="</p>";
			  str +="</div>";
			  str +="</div>";
			  str +="</div>";
			  }
			  console.log(str);
			  document.getElementById("showPanel").innerHTML = str;
			}
		
// 		function myFunction() {
// 		    alert();
// 		}
		
// 		foreach (var item in ${list})
	    
// 		{
// 	      sb.AppendFormat("<option value=\"{0}\">{1}</option>",
// 	        item.POSTAL_CODE.ToString(),
// 	        string.Concat(item.POSTAL_CODE.ToString(), " ", item.NAME)
// 	      );
// 	    }

		//取名addCou(button)比較好
		 function addCou(form){
			 $.ajax({
				 type: "post",
				 url: "<%=request.getContextPath()%>/back_end/cou_det/cou_det.do",
				 data: $(form).parents("form").serialize(),
				 dataType: "json",
				 success: function (data){
					 console.log(data);
					 swal("Good job!", "動作已加入", "success");
// 					 alert("動作已加入");	
			     },
	             error: function(){alert("請輸入動作順序及播放次數")}
	         })
				console.log($(form).parents("form").serialize());
		 }
// 	function sele() {
// 			 $('#couid').change(ajaxUpdate())
		
// 	}
</script>

</body>
</html>