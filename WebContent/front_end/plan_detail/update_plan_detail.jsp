<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan_detail.model.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>

<jsp:useBean id="planSvc" scope="page"
	class="com.plan.model.PlanService" />
<jsp:useBean id="plan_detailSvc" scope="page"
	class="com.plan_detail.model.Plan_DetailService" />
<jsp:useBean id="couSvc" scope="page" class="com.cou.model.CouService" />
<!DOCTYPE html>

<%
	// 	Plan_DetailService plan_detailSvc = new Plan_DetailService();
	// 	Plan_DetailVO plan_detailVO = (Plan_DetailVO) request.getAttribute("plan_detailVO");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Plan_DetailVO plan_detailVO = new Plan_DetailVO();
	String plan_id = (String) request.getAttribute("plan_id");
	List<Plan_DetailVO> list = plan_detailSvc.getByPK(plan_id);
	pageContext.setAttribute("list", list);
%>

<html>
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%=request.getContextPath()%>/front_end/css/showplan.css" rel="stylesheet" type="text/css" />
	<link href='<%=request.getContextPath()%>/front_end/css/fullcalendar.min.css' rel='stylesheet' />
	<link href='<%=request.getContextPath()%>/front_end/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='<%=request.getContextPath()%>/front_end/js/moment.min.js'></script>
	<script src='<%=request.getContextPath()%>/front_end/js/jquery.min.js'></script>
	<script src='<%=request.getContextPath()%>/front_end/js/jquery-ui.min.js'></script>
	<script src='<%=request.getContextPath()%>/front_end/js/fullcalendar.min.js'></script>
	<script>

		$(document).ready(function() {


			/* initialize the external events
			-----------------------------------------------------------------*/

			$('#external-events .fc-event').each(function() {

				// store data so the calendar knows to render an event upon drop
				$(this).data('event', {
					title: $.trim($(this).text()), // use the element's text as the event title
					stick: true // maintain when user navigates (see docs on the renderEvent method)
				});

				// make the event draggable using jQuery UI
				$(this).draggable({
					zIndex: 999,
					revert: true,      // will cause the event to go back to its
					revertDuration: 0  //  original position after the drag
				});
				
			});


			/* initialize the calendar
			-----------------------------------------------------------------*/

			$('#calendar').fullCalendar({
				header: {
					left: 'prev',
					center: 'title',
					right: 'next today'
				},
				editable: true,
				droppable: true, // this allows things to be dropped onto the calendar
				dragRevertDuration: 0,
				drop: function() {
					// is the "remove after drop" checkbox checked?
					if ($('#drop-remove').is(':checked')) {
						// if so, remove the element from the "Draggable Events" list
					}$(this).remove();
				},
				
				events:[
					<%for (int i = 0; i < list.size(); i++) {%>
                     {
					id:'<%=list.get(i).getPd_no()%>',
					start:'<%=list.get(i).getSelectdate()%>',
					title:'<%=couSvc.getOneCou(list.get(i).getCou_id()).getCou_name()%>',
					editable:true,
					color:'red',
					className:"myClass"
                     },
					<%}%>
						], 
				
				eventClick: function(event) {
					console.log(event);
					$.ajax({
						type:'POST',
						url:'plan_detail.do',
						data: {action : 'delete', pd_no: 'id'},
						cache: false,   //是否使用快取
						dataType : 'json',
						success: function(data){ 
							console.log(data);
						$('#calendar').fullCalendar('refetchEvents');
						},
						error: function(){
							alert('There was an error while fetching events!');
						},
					})
					},
				
// 				eventDragStop: function( event, jsEvent, ui, view ) {
                
//                 if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
//                     $('#calendar').fullCalendar('removeEvents', event._id);
//                     var el = $( "<div class='fc-event'>" ).appendTo( '#external-events-listing' ).text( event.title );
//                     el.draggable({
//                       zIndex: 999,
//                       revert: true, 
//                       revertDuration: 0 
//                     });
//                     el.data('event', { title: event.title, id :event.id, stick: true });
//                 }
//             }		
			});

// 			 var isEventOverDiv = function(x, y) {

//             var external_events = $( '#external-events' );
//             var offset = external_events.offset();
//             offset.right = external_events.width() + offset.left;
//             offset.bottom = external_events.height() + offset.top;

//             // Compare
//             if (x >= offset.left
//                 && y >= offset.top
//                 && x <= offset.right
//                 && y <= offset .bottom) { return true; }
//             return false;
//         }
			 
			

				var objArray=[];
				
				
				$("#myBtn").click(function(){
					
					var events = $('#calendar').fullCalendar('clientEvents');
					//var events2= $('#calendar').fullCalendar('getResourceEvents','早餐');
					//console.log(events);
					//console.log(events2);
					$.each( events, function( key, value ) {
						var aaa = {
								date:null,
								className: null,
								time:null,
								cou:null,
								id:null
							};
							aaa.date = moment(value.start).format('YYYY-MM-DD');
							aaa.cou = value.title;
							aaa.time = value.resourceId;
							aaa.id = value.id;
							aaa.className = value.className;
						  objArray.push(aaa);
						  console.log(aaa);
						  console.log(objArray);

//		 				  console.log(value.resourceId);
//		 				  console.log(value.title);
//		 				  console.log(moment(value.start).format('YYYY-MM-DD'));
						});
					
				     console.log(JSON.stringify(objArray));
				     console.log(typeof JSON.stringify(objArray) );
				     var jsonStr=JSON.stringify(objArray);
				    console.log(jsonStr);
				    $("input[name='cou_list']").val(jsonStr);
				    console.log($("input[name='cou_list']").val());
				    $("#form1").submit();
				});
				
			 
			 
		});
	</script>
</head>
<body>
	<!-- navbar -->
	<nav class="navbar navbar-default" role="navigation">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			</a>
		</div>

		<!-- 手機隱藏選單區 -->
		<div class="container">
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<a class="navbar-brand" href="#" style="padding-top: 0px;"><img
						src="<%=request.getContextPath()%>/front_end/images/fslogo.png">
						<li><a href="#">課程</a></li>
						<li><a href="#">相關資訊</a></li>
				</ul>

				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${memVO==null}">
						<li><a
							href="<%=request.getContextPath()%>/front_end/mem/register.jsp">註冊</a></li>
						<li><a
							href="<%=request.getContextPath()%>/front_end/login/login.jsp">登入</a></li>
					</c:if>

					<c:if test="${memVO!=null}">
						<li><a href="#">${memVO.mem_name} 您好</a></li>
						<li><a
							href="<%=request.getContextPath()%>/mem/mem.do?action=logout">登出</a></li>
						<li><a
							href="<%=request.getContextPath()%>/front_end/mem/updatemem.jsp">個人資料設定</a></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">我的健身房 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=request.getContextPath()%>/front_end/chiacourse/addCourse.jsp">建立課程</a></li>
								<li><a href="#">個人已有課程</a></li>
							</ul></li>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- 手機隱藏選單區結束 -->
	</nav>
	<!-- 麵包屑 -->
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<ol class="breadcrumb">
			<li><a href="#">我的課程</a></li>
			<li class="active">我的計畫</li>
		</ol>
	</div>



	<!-- 行事曆 -->
	<div>
		<div id='calendar'></div>
		<div style='clear: both'></div>
	</div>


	<form id="form1" method="post"
		action="<%=request.getContextPath()%>/plan_detail/plan_detail.do">
		<input type="hidden" name="action" value="update_calendar"> <input
			type="hidden" name="plan_id"
			value="<%=request.getAttribute("plan_id")%>"> <input
			type="hidden" name="cou_list"> <input id="myBtn"
			type="button" class="btn btn-primary" value="修改">
	</form>








	<!-- 套版CODE -->
	<!-- 	<div id='wrap'>
			<div id='external-events'>
				<h4>計畫一</h4>
				<div class='fc-event'>課程1</div>
				<div class='fc-event'>課程2</div>
				<div class='fc-event'>課程3</div>
				<div class='fc-event'>課程4</div>
				<div class='fc-event'>課程5</div>
				<div class='fc-event'>課程6</div>
				 <p>
					<input type='checkbox' id='drop-remove' />
					<label for='drop-remove'>remove after drop</label>
				</p>
			</div>
			<div id='calendar'></div>
			<div style='clear:both'></div>
	</div> -->
	<!-- 套版CODE結束 -->


	<!-- footer -->
	<div class="container-fluid footer">
		<div class="row">
			<div>
				<div class="col-xs-12 col-sm-8">
					<div class="col-xs-12 col-sm-2">
						<a href="#">關於我們<br></a> <a href="#">聯絡方式<br></a>
					</div>
					<div class="col-xs-12 col-sm-2">
						<a href="#">客服中心<br></a> <a href="#">訓練課程<br></a>
					</div>
					<div align=center>
						<h4>© Fit Style. 桃園市中壢區中大路300號</h4>
						<h4>技術總監:資策會最帥紀昌佑</h4>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4">
					<div class="col-xs-12 col-sm-2" align=right>
						<a href="#"><img
							src="<%=request.getContextPath()%>/front_end/images/instagram.png"
							width="30px"></a>
					</div>
					<div class="col-xs-12 col-sm-2" align=right>
						<a href="#"><img
							src="<%=request.getContextPath()%>/front_end/images/facebook.png"
							width="30px"></a>
					</div>
					<div class="col-xs-12 col-sm-2" align=right>
						<a href="#"><img
							src="<%=request.getContextPath()%>/front_end/images/line.png"
							width="30px"></a>
					</div>
					<div class="col-xs-12 col-sm-2" align=right>
						<a href="#"><img
							src="<%=request.getContextPath()%>/front_end/images/taiwan.png"
							width="30px"></a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
