$(document).ready(function(){
//	 $('#grade').change(function(){
//		 $.ajax({ // 想像成 form 表單
//			 type: "GET", // form 的 mtehod
//			 url: "ajaxResponse.do", // form 的 action 要傳到哪個 controller
//			 data: creatQueryString($(this).val(), ""), // 要傳送的資料
//			 dataType: "json", // 要用 JSON 傳，或可改用 XML
//			 success: function (data){ // 本行以下是傳回來成功還是失敗
//				clearSelect();
//				$.each(data, function(i, item){
//					$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//				});
//// 					$(data).each(function(i, item){
//// 						$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//// 					});
//// 					jQuery.each(data, function(i, item){
//// 						$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//// 					});
//		     },
//             error: function(){alert("AJAX-grade發生錯誤囉!")} // 失敗時回傳錯誤
//         })
//	 })
	
	 $('#class').change(function(){
		$.ajax({ // 想像成 form 表單
			 type: "POST", // form 的 mtehod
			 url: "ajaxResponse.do", // form 的 action 要傳到哪個 controller
			 data: creatQueryString($('#grade').val(), $(this).val()), // 要傳送的資料
			 dataType: "json", // 要用 JSON 傳，或可改用 XML
			 success: function (data){ // 本行以下是傳回來成功時
				 clearSelect2();
				 $.each(data, function(i, item){
					 $('#name').append("<option value='"+data[i].nameId+"'>"+data[i].name+"</option>");
				 });
		     },
            error: function(){alert("AJAX-class發生錯誤囉!")} // 失敗時
        })
	})
})

function creatQueryString(paramGrade, paramClass){
	console.log("paramGrade:"+paramGrade+"; paramClass:"+paramClass);
						// key : value
	var queryString= {"action":"getSelect", "gradeId":paramGrade, "classId":paramClass};

	return queryString;
}
function clearSelect(){
	$('#class').empty();
	$('#class').append("<option value='-1'>請選擇</option>");
	$('#name').empty();
	$('#name').append("<option value='-1'>請選擇</option>");
}
function clearSelect2(){
	$('#name').empty();
	$('#name').append("<option value='-1'>請選擇</option>");
}