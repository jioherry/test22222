<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>


<%
  session.getAttribute("memVO");
%>
<!DOCTYPE html>
<html lang="">
<head>
	<title>Fit Style</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
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
   #dsize {
    height: 450px;
    width: 350px;
    background-color: #b0b7b036;
    border:1px solid #9e9ebb;
    
}
</style>
<style>
body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 300px;
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
<body >

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

		<!-- 這是前端登入 -->
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-sm-offset-4">
					<form ACTION="<%=request.getContextPath()%>/login/login.do" name="form1" method="post">
						<div id="dsize" style="margin-top: 50px;">
						    
							<div class="form-group form-group-lg" style="margin-top: 100px">
								<label for="aa">帳號</label>
								<input type="text" name="mem_acct" id="ac" placeholder="請輸入帳號" class="form-control">
							</div>
                       
							<div class="form-group form-group-lg">
								<label for="aa">密碼</label>
								<input type="password" name="mem_psw" id="psw" placeholder="請輸入密碼" class="form-control">
							</div>
							<div>
								<button class="btn btn-info" name="action" value="login">送出</button>
	<!-- 							<button class="btn btn-info btn-right" name="action" value="forpsw">忘記密碼</button> -->
								<a href='#modal-id' data-toggle="modal">忘記密碼</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

<!-- 		忘記密碼 -->

<!-- 		<a class="btn btn-primary">跳出視窗</a>不知是啥 -->
		<div class="modal fade" id="modal-id">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Fit Style</h4>
					</div>
					<div class="modal-body">
						<form action="<%=request.getContextPath()%>/login/login.do" method="post">
						<div class="form-group form-group-lg">
							<label for="acct">帳號</label>
							<input type="text" name="acct" id="acct" placeholder="請輸入帳號" class="form-control">
						</div>	

						<div class="form-group form-group-lg">
							<label for="mem_email">信箱</label>
							<input type="text" name="mem_email" id="mem_email" placeholder="請輸入信箱" class="form-control">
						</div>
						<div>				
							<button class="btn btn-info " name="action" value="forpsw">確定送出</button>
<!-- 							<button class="btn btn-info btn-right">忘記密碼</button> -->						
						</div>
					</form>
					</div>
<!-- 					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
						<button type="button" class="btn btn-primary">Save changes</button>
					</div> -->
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
	 document.getElementById("ac").value = "ba105g3g3";
     document.getElementById("psw").value = "ba105g3g3";
}

</script>

<%@ include file="/front_end/footer.jsp" %>	
</body>
</html>