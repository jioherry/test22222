<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan_detail.model.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>

<jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" />
<jsp:useBean id="plan_detailSvc" scope="page" class="com.plan_detail.model.Plan_DetailService" />
<jsp:useBean id="couSvc" scope="page" class="com.cou.model.CouService" />
<!DOCTYPE html>

<%--   <%
// 	Plan_DetailService plan_detailSvc = new Plan_DetailService();
// 	Plan_DetailVO plan_detailVO = (Plan_DetailVO) request.getAttribute("plan_detailVO");
	Plan_DetailVO plan_detailVO = new Plan_DetailVO();
    List<Plan_DetailVO> list = plan_detailSvc.getByPK("P00001");
    pageContext.setAttribute("list",list);
%> --%>

<%
	session.getAttribute("memVO");
	String plan_id = (String) request.getAttribute("plan_id");
	Integer mem_id = (Integer) request.getAttribute("mem_id");
%>

<html>
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%=request.getContextPath()%>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css" />
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
.zindex{
		z-index:9999;
	}	
</style>	
	<script>

		$(document).ready(function() {


			/* initialize the external events
			-----------------------------------------------------------------*/

			$('#external-events .fc-event').each(function() {

				// store data so the calendar knows to render an event upon drop
				$(this).data('event', {
					title: $.trim($(this).text()), // use the element's text as the event title
					cou_id: $.trim($(this).find("input[name=cou_id]").val()),
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
				validRange: function(nowDate) {
			        return {
			            start: nowDate,
			        };
			    },
				eventDragStop: function( event, jsEvent, ui, view ) {
                
                if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
                    $('#calendar').fullCalendar('removeEvents', event._id);
                    var el = $( "<div class='fc-event'>" ).appendTo( '#external-events-listing' ).text( event.title );
                    el.draggable({
                      zIndex: 999,
                      revert: true, 
                      revertDuration: 0 
                    });
                    el.data('event', { title: event.title, id :event.id, stick: true });
	                }
	            }		
				});

				var isEventOverDiv = function(x, y) {
	            var external_events = $( '#external-events' );
	            var offset = external_events.offset();
	            offset.right = external_events.width() + offset.left;
	            offset.bottom = external_events.height() + offset.top;
	            // Compare
	            if (x >= offset.left
	                && y >= offset.top
	                && x <= offset.right
	                && y <= offset .bottom) { return true; }
	            return false;
	        	}
				var objArray=[];
				
				$("#myBtn").click(function(){
					var events = $('#calendar').fullCalendar('clientEvents');
					//var events2= $('#calendar').fullCalendar('getResourceEvents','早餐');
// 					alert(events);
					console.log(events);
					console.log("0000000000000000000");
					//console.log(events2);
					$.each( events, function( key, value ) {
						var aaa = {
								date:null,
								time:null,
								cou_id:null
							};
							aaa.date = moment(value.start).format('YYYY-MM-DD');
							aaa.cou_id = value.cou_id;
							aaa.time = value.resourceId;
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
				    console.log(JSON.stringify(objArray));
				    $("input[name=cou_id]").val(jsonStr);
				    console.log($("input[name=cou_id]").val());
				    $("#form1").submit();
				});	 
		});
	</script>
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
				我的計畫
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

	<!-- tabs -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4">
				<div id='external-events'>
					<c:forEach var="couVO" items="${couSvc.getfindByPK(mem_id)}">
						<div id='external-events-listing'>
							<div class='fc-event'>
								<input type="hidden" name="cou_id" value="${couVO.cou_id}">${couVO.cou_name}
							</div>
						</div>
					</c:forEach>
					<!-- <p>
				 	<input type='checkbox' id='drop-remove' />
				 	<label for='drop-remove'>remove after drop</label>
				 </p> -->
				</div>
			</div>

			<!-- 行事曆 -->
			<div class="col-xs-12 col-sm-8">
				<div id='calendar'></div>
				<div style='clear: both'></div>
			</div>
		</div>
	</div>

	<form id="form1" method="post" action="<%=request.getContextPath()%>/plan_detail/plan_detail.do">
		<input type="hidden" name="action" value="insert_calendar"> 
		<input type="hidden" name="plan_id" value="${ plan_id }"> 
		<input type="hidden" name="cou_id" value="json" /> 
		<input id="myBtn" type="button" class="btn btn-primary" value="新增">
	</form>
<%@ include file="/front_end/footer.jsp" %>
	
</body>
	<link href='<%=request.getContextPath()%>/front_end/css/fullcalendar.min.css' rel='stylesheet' />
	<link href='<%=request.getContextPath()%>/front_end/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='<%=request.getContextPath()%>/front_end/js/moment.min.js'></script>
	<script src='<%=request.getContextPath()%>/front_end/js/jquery.min.js'></script>
	<script src='<%=request.getContextPath()%>/front_end/js/jquery-ui.min.js'></script>
	<script src='<%=request.getContextPath()%>/front_end/js/fullcalendar.min.js'></script>
</html>
