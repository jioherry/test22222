<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%
  session.getAttribute("memVO");
%>
<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />
	<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<style type="text/css">
		/*表單置中*/
	.form-custom{
	    background-color: #f1f1f1;
	    padding: 1em;
	    margin-top: 6em;
	    border: 1px solid #A9A9A9;
	    border-radius: 1em;
	/*  width:400px; */
			}
			
	.isize {
	  
	  width:330px;
	  height:200px;
	  border
	
	}
</style>
<style>
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 600px;
	margin-left: 0px;
	background: -webkit-linear-gradient(left top,steelblue,paleturquoise);
	background: -o-linear-gradient(bottom right,steelblue,paleturquoise);
	background: -moz-linear-gradient(bottom right,steelblue,paleturquoise);
	background: linear-gradient(to bottom right,steelblue,paleturquoise);
	}
.breadcrumb{
	background:none;
	}
</style>
</head>
<body> 


<!-- navbar -->
	<nav class="navbar navbar-default" role="navigation">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
		
			<!-- 手機隱藏選單區 -->
			<div class="container">
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/index.jsp" style="padding-top: 0px;"><img
						src="<%=request.getContextPath()%>/front_end/images/fslogo.png"></a>
						<li><a href="<%=request.getContextPath()%>/front_end/chiacourse/listAllCourse.jsp">課程</a></li>
						<li><a href="<%=request.getContextPath()%>/inf/inf.do?action=list&inf_cat_id=1">相關資訊</a></li>
				</ul>

				
				</div>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>


<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-4 col-sm-offset-4">
			 				<!-- 以下表單欄位 -->
			<form class="form-custom" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1" method="post" enctype="multipart/form-data">
                  <div class="form-group">
                      <label for="acco">帳號</label>
                      <input  class="form-control" type="text" id="mem_acct" name="mem_acct">
                  </div>
                  <div class="form-group">
                      <label for="psd">密碼</label>
                      <input class="form-control" type="password" id="mem_psw" name="mem_psw">
                  </div>
                  <div class="form-group">
                      <label for="psd2">確認密碼</label>
                      <input class="form-control" type="password" id="mem_psw_chk" name="mem_psw_chk">
                  </div>
                  <div class="form-group">
                      <label for="mail">信箱</label>
                      <input  class="form-control" type="email" id="mem_email" name="mem_email">
                  </div>
                  <div class="form-group"> 	
                      <label for="name">姓名</label>
                      <input  class="form-control" type="text" id="mem_name" name="mem_name">
                  </div>
                  <div class="form-group">
                      <label for="phone">手機號碼</label>
                      <input  class="form-control" type="text" id="mem_phone" name="mem_phone">
                  </div>
                  <div class="form-group">
                      <label for="addr">地址</label>
                      <input  class="form-control" type="text" id="mem_add" name="mem_add">
                  </div>
				  <label>性別</label>
			      <div class="form-check form-check-inline">
					  <label class="form-check-label">
					    <input class="form-check-input" type="radio" name="mem_gender" id="inlineRadio1" value="男" checked> 男
			  		  </label>
					  <label class="form-check-label">
					    <input class="form-check-input" type="radio" name="mem_gender" id="inlineRadio2" value="女"> 女
					  </label>
				  </div>
                  <div class="checkbox">
					<label><input type="checkbox" value="yes" id="mem_agree" name="mem_agree">我同意
					<span class=pre-scrollable>這是條款 </sapn></label>
				  </div>
                  <div class="form-group">
					    <label for="upload">個人照片上傳</label>
					    <input type="file" class="form-control-file" id="upload" name="mem_pic" value="a" onchange="readURL(this);" >
				  </div>
				  <img class="isize" id="blah"  />
				  <input type="hidden" name="mem_bonus" value="0">
				  <input type="hidden" name="mem_title" value="初學者">
				  <input type="hidden" name="mem_exp" value="0">
				  <input type="hidden" name="mem_status" value="正常">
                  <input type="hidden" name="mem_repno" value="0">                 
                  <input type="hidden" name="action" value="register">
                  <br>
                  <button type="submit" class="btn btn-success">送出</button>
              </form>    
		</div>
	</div>    
</div>
<div>
      <button class="btn" onclick="myFunction()">
        <span>按我</span>
    </button>
</div>

<%@ include file="/front_end/footer.jsp" %>	
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>

function readURL(input){
	
	var reader = new FileReader();
	reader.onload = function(e){
		$("#blah").attr("src", e.target.result);
	}
	
	reader.readAsDataURL(input.files[0]);
}

</script>


<script type="text/JavaScript">
function myFunction() {
    document.getElementById("mem_acct").value = "ba105g3g3";
    document.getElementById("mem_psw").value = "ba105g3g3";
    document.getElementById("mem_psw_chk").value = "ba105g3g3";
    document.getElementById("mem_email").value = "ba105g3g3@gmail.com";
    document.getElementById("mem_name").value = "新會員";
    document.getElementById("mem_phone").value = "0912345678";
    document.getElementById("mem_add").value = "中壢區一條路77號";
    document.getElementById("mem_agree").checked = true;
}

</script>
</body>
</html>