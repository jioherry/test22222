<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.per.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="com.inf.model.*"%>
<%@ page import="java.util.*"%>

<%
  InfVO infVO = (InfVO) request.getAttribute("infVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/back_end/back_index.jsp" flush="true" />
<style type="text/css">

</style>
</head>
<body>
<!-- container -->
<div class="container">
  <div class="row">
  	<div class="col-xs-12 col-sm-12">
  		<div class="page-header">
  			<h1>修改資訊</h1>
  		</div>
  	</div>
  </div>

  <div class="row" id="up">
    <div class="textsize">
      <form method="post" action="<%=request.getContextPath()%>/inf/inf.do" enctype="multipart/form-data" class="form-horizontal">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="inf_id" value="<%=infVO.getInf_id()%>">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>
                <div class="col-xs-12 col-sm-10 col-sm-offset-5"><%=infVO.getInf_id()%></div>
              </th>
            </tr>
          </thead>
        <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
          <tbody>
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">員工姓名：</label>
                <div class="col-sm-9">
                  <select size="1" name="emp_id">
                    <c:forEach var="empVO" items="${empSvc.all}">
                      <option value="${empVO.emp_id}" >${empVO.emp_name}
                    </c:forEach>    
                  </select>
                </div>
              </td>
            </tr>
          <jsp:useBean id="inf_catSvc" scope="page" class="com.inf_cat.model.Inf_catService" />
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">資訊類別編號：</label>
                <div class="col-sm-9">
                  <select size="1" name="inf_cat_id">
                    <c:forEach var="inf_catVO" items="${inf_catSvc.all}">
                      <option value="${inf_catVO.inf_cat_id}" >${inf_catVO.inf_cat_name}
                    </c:forEach>
                  </select>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">標題：</label>
                <div class="col-sm-9">
                  <input type="text" name="inf_title" size="50" value="<%= (infVO==null)? "請輸入標題" : infVO.getInf_title()%>" class="form-control"/>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">內容：</label>
                <div class="col-sm-9">
                  <textarea name="inf_text" rows="6" cols="62" placeholder="<%= (infVO==null)? "請輸入內容" : infVO.getInf_text()%>"></textarea>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <label for="aa" class="col-sm-3 control-label">圖片：</label>
                <div class="col-sm-9">
                  <input type="file" class="touch" name="inf_pic" size="50" value="<%= (infVO==null)? "" : infVO.getInf_pic()%>" class="form-control"/>
                  <img class="preview" src="<%=request.getContextPath()%>/InfImage.do?inf_id=${ InfVO.inf_id }" id="o1">
                </div>
              </td>
            </tr>
            <tr>
              <td style="padding-top: 20px;">
                <div class="col-sm-3 control-label">
                  <input class="btn btn-success" type="submit" value="送出修改">
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </form>

    </div>
  </div>

</div>
<script type="text/javascript">
// 圖片預覽 開始 ---------------------------------------------------------------
$(document).ready( function() {
    $(".touch").change( function() {
        // 用 jQuery 選擇該 input 由哪一個觸發，再去選擇他的兄弟元素
        var previewplace = $(this).siblings().attr('id');
        preview(this, previewplace); // 呼叫 preview function，帶入參數
    });

//      $(".touch").change(function() {
//          $(this).next().attr("src", $(this).val()); // jquery簡短寫法，只適用IE瀏覽
//      });

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
</script>
</body>
</html>
