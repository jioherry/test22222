<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
<%
  session.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html lang="">
	<head>
		<title>Fit Style</title>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />


<style type="text/css">

/*表單置中*/
.form-custom{
    background-color: white;
    padding: 1em;
    margin-top: 1em;
    border: 1px solid #A9A9A9;
    border-radius: 1em;
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
</style>
</head>
<body>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-sm-offset-3">
			<!-- 以下表單欄位 -->
	                <form class="form-custom" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1" method="post" enctype="multipart/form-data">
	
	                    <div class="form-group">
	                        <label for="acco">帳號</label>
	                        <input  class="form-control" type="text" id="acco" name="mem_acct" placeholder="<%=memVO.getMem_acct()%>">
	                    </div>
	
	                    <div class="form-group">
	                        <label for="psd">密碼</label>
	                        <input class="form-control" type="password" id="psd" name="mem_psw" >
	                    </div>
	                                
	                    <div class="form-group">
	                        <label for="psd2">確認密碼</label>
	                        <input class="form-control" type="password" id="psd2" name="mem_psw_chk">
	                    </div>
	
	                    <div class="form-group">
	                        <label for="mail">信箱</label>
	                        <input  class="form-control" type="email" id="mail" name="mem_email" placeholder="<%=memVO.getMem_email()%>">
	                    </div>
	
	                    <div class="form-group"> 	
	                        <label for="name">姓名</label>
	                        <input  class="form-control" type="text" id="name" name="mem_name" placeholder="<%=memVO.getMem_name()%>">
	                    </div>
	
	                    <div class="form-group">
	                        <label for="phone">手機號碼</label>
	                        <input  class="form-control" type="text" id="phone" name="mem_phone" placeholder="<%=memVO.getMem_phone()%>">
	                    </div>
	
	
	                    <div class="form-group">
	                        <label for="addr">地址</label>
	                        <input  class="form-control" type="text" id="addr" name="mem_add" placeholder="<%=memVO.getMem_add()%>">
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
	
	

			                        
<!-- 				                                                            修改用不到 -->
<!-- 				                        <div class="checkbox"> -->
<!--   											<label><input type="checkbox" value="yes" name="mem_agree">我同意 -->
<!--   											<span class=pre-scrollable> -->
<!-- 				                                	這是條款 -->
<!-- 				                                </sapn> -->
<!-- 				                            </label> -->
<!-- 										</div> -->
				                        
                        

                        <div class="form-group">
						    <label for="upload">個人照片上傳</label>
						    <input type="file" class="form-control-file" id="upload" name="mem_pic" value="a" onchange="readURL(this);" >
						</div>
						
						<img id="blah"  />
						
						
						<input type="hidden" name="mem_bonus" value="<%=memVO.getMem_bonus()%>">
						<input type="hidden" name="mem_title" value="<%=memVO.getMem_title()%>">
						<input type="hidden" name="mem_exp" value="<%=memVO.getMem_exp()%>">
						<input type="hidden" name="mem_status" value="<%=memVO.getMem_status()%>">
                        <input type="hidden" name="mem_repno" value="<%=memVO.getMem_repno()%>">
                        <input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
                        
                        <input type="hidden" name="action" value="myupdate">
                        <br>
                        <button type="submit" class="btn btn-success">送出</button>


                                    
                    </form>    
                </div>
             </div>    
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
		
		
		
		
	</body>
</html>