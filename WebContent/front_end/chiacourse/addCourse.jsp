<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>
<%
	CouVO couVO = (CouVO) request.getAttribute("couVO");
	String mem_acct = (String) session.getAttribute("mem_acct");
	session.getAttribute("memVO");
%>
<!-- EL寫法可省去很多雜項 -->
<%-- ${couVO.cou_id}; --%>

<!DOCTYPE html>
<head>
	<title>Fit Style</title>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />

<style>
	.textsize {
		background-color:#fff;
		font-size: 18px;
		border: 2px solid	#FFF;
		border-radius: 15px;
		box-shadow: 2.5px 5.5px 2.5px #000;
		width:60%;
		margin-left:auto; 
		margin-right:auto;
		margin-top:80px;
		
	}
	#up {
	  padding: 10px;
	  margin-bottom: 50px;
	}
	#o1 {
	  width: 200px;
	  height: 200px;
	}
	body {
    height: 100%;
    margin: 0;
    padding: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 400px;
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
				新增課程
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/plan/myplan.jsp">我的計畫</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/front_end/chiacourse/memCourse.jsp">編輯課程動作</a>
			</li>
			<li>
			<a href="<%=request.getContextPath()%>/front_end/chiacourse/listmemcoumovajax.jsp">前往觀看課程動作</a>
<%-- 			<a href="<%=request.getContextPath()%>/front_end/chiacourse/playlist.jsp">課程動作明細</a> --%>
			</li>
		</ol>
	</div>
	
<div class="container">
	<div class="row textsize" id="up">
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/cou/cou.do" name="form1" enctype="multipart/form-data" class="form-horizontal">
		<input type="hidden" name="cou_cat_id" size="45" value="<%=(couVO == null) ? "C0005" : couVO.getCou_cat_id()%>" /> 
		<input type="hidden" name="mem_id" size="45" value="<%=memVO.getMem_id()%>" /> 
		<input type="hidden" name="cou_permi" size="45" value="parivate" /> 
		<input type="hidden" name="dis_state" size="45" value="open" />
		<input type="hidden" name="cou_int" size="45" value="<%=(couVO == null) ? "20" : couVO.getCou_int()%>" /> 
		<input type="hidden" name="cited_count" size="45" value="<%=(couVO == null) ? "Easy" : couVO.getCited_count()%>" />
	    <input type="hidden" name="cou_cal_cns" size="45" value="<%=(couVO == null) ? "100" : couVO.getCou_cal_cns()%>" /> 
		<input type="hidden" name="cou_time_length" size="45" value="<%=(couVO == null) ? "1000" : couVO.getCou_time_length()%>" />	
			<table class="table table-hover">
				<thead>
						<tr>
							<th>
								<div class="col-xs-12 col-sm-10 col-sm-offset-5">請輸入資料</div>
							</th>
						</tr>
				</thead>
				<tbody>	
				<tr>
					<td>
						<label for="aa" class="col-sm-3 control-label">課程名稱：</label>
						<div class="col-sm-5">
							<input type="TEXT" name="cou_name" size="31"
							value="<%=(couVO == null) ? "" : couVO.getCou_name()%>" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label for="aa" class="col-sm-3 control-label">課程介紹：</label>
						<div class="col-sm-5">
							<textarea rows="4" cols="30" name="cou_intor"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label for="aa" class="col-sm-3 control-label">課程圖片：</label>
						<div class="col-sm-5">
							<input type="file" class="touch" name="cou_img" size="50" accept="images/*"/>
							<img class="preview" src="<%=request.getContextPath()%>/DBGifReader_Cou.do?cou_id=${ couVO.cou_id }" id="o1">
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;">
					<div class="col-sm-9">
					 	<input type="hidden" name="action" value="insert2"> 
					 	<input type="submit" class="btn btn-primary" value="送出新增">
					</div>
					</td>
				</tr>
			</tbody>
		</table>
	</FORM>
</div>

			<div class="col-sm-12">
				<button class="btn btn-success" id="sb">btn</button>
			</div>
</div>
	
<%@ include file="/front_end/footer.jsp" %>
</body>
<script>

//圖片預覽 開始 ---------------------------------------------------------------
$(document).ready( function() {
	$(".touch").change( function() {
		// 用 jQuery 選擇該 input 由哪一個觸發，再去選擇他的兄弟元素
		var previewplace = $(this).siblings().attr('id');
		preview(this, previewplace); // 呼叫 preview function，帶入參數
	});

//		$(".touch").change(function() {
//			$(this).next().attr("src", $(this).val()); // jquery簡短寫法，只適用IE瀏覽
//		});

	/* 預覽圖 input 輸入 input[type=file] 的 this */
	function preview(input, previewplace) {
		// 若有選取檔案
		if (input.files && input.files[0]) {
			// 建立一個物件，使用 Web APIs 的檔案讀取器(FileReader 物件) 來讀取使用者選取電腦中的檔案
			var reader = new FileReader();
			// 事先定義好，當讀取成功後會觸發的事情
			reader.onload = function (e) {
				console.log(e);
	            // 這裡看到的 e.target.result 物件，是使用者的檔案被 FileReader 轉換成 base64 的字串格式，
	            // 在這裡我們選取圖檔，所以轉換出來的，會是如 『data:image/jpeg;base64,.....』這樣的字串樣式。
	            // 我們用它當作圖片路徑就對了。
				//input.attr('src', e.target.result);
	            var showphoto = '#'+ previewplace; // '#'+ previewplace -> '#o1'(id="o1")
	            $(showphoto).attr('src', e.target.result); // 將圖片位址塞入 src 內
			}
			// 因為上面定義好讀取成功的事情，所以這裡可以放心讀取檔案
			reader.readAsDataURL(input.files[0]);
		}
	}
});
// 圖片預覽 結束 ---------------------------------------------------------------



$('#sb').click(function(){
	$('[name=cou_name]').val('123456');
	$('[name=cou_intor]').val('11111');
});

</script>
</body>
</html>