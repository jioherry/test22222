<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="qsutf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>FitStyle Backstage</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/back_end/css/back_index.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#loginlogo {
	margin-top: 150px;
}
</style>
</head>
<body>
<!-- 後台 logo 區 -->
<div class="text-center" id="loginlogo">
	<img class="img-center" src="<%=request.getContextPath()%>/back_end/images/fslogo.png" width="300px">
</div>

<!-- 這是後端登入 -->
<div class="container col-xs-12 col-sm-4 col-sm-offset-4">
	<div class="row">
		<form action="<%=request.getContextPath()%>/backlogin/backlogin.do" name="form1" method="post">
			<table width="400" height="300" border="0" align="center" cellpadding="0">
				<tr>
					<td>
						<img src="<%=request.getContextPath()%>/back_end/images/lock1.png" height="50%">
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 20px;">
							<tr>
								<td>
									<div class="form-group form-group-lg">
										<label for="aa">帳號</label>
										<input type="text" name="emp_acct" placeholder="請輸入帳號" class="form-control" id="loginid">
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="form-group form-group-lg">
										<label for="aa">密碼</label>
										<input type="password" name="emp_psw" placeholder="請輸入密碼" class="form-control" id="password">
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="in">
										<button class="btn btn-info" type="submit" value="登入">登入</button>
									</div>
									<div>
										<!-- 神奇小按鈕 -->
										<button class="btn btn-info" type="submit" value="登入" id="magicBtn1"></button>
										<button class="btn btn-info" type="submit" value="登入" id="magicBtn2"></button>
										<button class="btn btn-info" type="submit" value="登入" id="magicBtn3"></button>
										<!-- 神奇小按鈕 -->
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
</div>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
// 神奇小按鈕
	$(document).ready(function() {
		$("#magicBtn1").click(function() {
			$("#loginid").val("EMP1");
			$("#password").val("EMP1");
		});
		$("#magicBtn2").click(function() {
			$("#loginid").val("EMP2");
			$("#password").val("EMP2");
		});
		$("#magicBtn3").click(function() {
			$("#loginid").val("ROOT");
			$("#password").val("ROOT");
		});
	});
// 神奇小按鈕
</script>
</body>
</html>