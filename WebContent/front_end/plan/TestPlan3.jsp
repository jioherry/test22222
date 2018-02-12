<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan_detail.model.*"%>

<jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" />
<jsp:useBean id="plan_detailSvc" scope="page" class="com.plan_detail.model.Plan_DetailService" />
<!DOCTYPE html>

<%--   <%
// 	Plan_DetailService plan_detailSvc = new Plan_DetailService();
// 	Plan_DetailVO plan_detailVO = (Plan_DetailVO) request.getAttribute("plan_detailVO");
	Plan_DetailVO plan_detailVO = new Plan_DetailVO();
    List<Plan_DetailVO> list = plan_detailSvc.getByPK("P00001");
    pageContext.setAttribute("list",list);
%> --%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="<%= request.getContextPath() %>/front_end/css/plan.css" rel="stylesheet" type="text/css"/>

	<link href='<%= request.getContextPath() %>/front_end/css/fullcalendar.min.css' rel='stylesheet' />
	<link href='<%= request.getContextPath() %>/front_end/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='<%= request.getContextPath() %>/front_end/js/moment.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/jquery.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/jquery-ui.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/fullcalendar.min.js'></script>
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
				
				<%--				events:[
					<%for (int i = 0; i < list.size(); i++) {%>
                     {
					id:'<%=i%>',
					start:'<%=list.get(i).getSelectdate()%>',
					title:'<%=list.get(i).getCou_id()%>',
					editable:true,
					color:'red',
					className:"myClass"
                     },
					<%}%>
				], --%>
				
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
					//console.log(events);
					//console.log(events2);
					$.each( events, function( key, value ) {
						var aaa = {
								date:null,
								time:null,
								cou:null
							};
							aaa.date = moment(value.start).format('YYYY-MM-DD');
							aaa.cou = value.title;
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
				    $("input[name=cou_id]").val(jsonStr);
				    console.log($("input[name=cou_id]").val());
				    $("#form1").submit();
				});
				
			 
			 
		});
	</script>
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
				</a>
			</div>
		
			<!-- 手機隱藏選單區 -->
			<div class="container">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 左選單 -->
					<ul class="nav navbar-nav">
						<a class="navbar-brand" href="#"><img src="<%= request.getContextPath() %>/front_end/images/logo.png">
						<li><a href="#">課程</a></li>
						<li><a href="#">我的健身房</a></li>
						<li><a href="#">商城</a></li>
						<li><a href="#">相關資訊</a></li>
					</ul>
						
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">註冊</a></li>
						<li><a href="#">登入</a></li>
					</ul>
				</div>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
	<!-- 麵包屑 -->
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<ol class="breadcrumb">
			<li>
				<a href="#">我的課程</a>
			</li>
			<li>
				<a href="#">建立課程</a>
			</li>
			<li class="active">我的計畫</li>
			<li>
				<a href="#">建立計畫</a>
			</li>				
		</ol>
	</div>

<!-- tabs -->
<div class="container">
	<div class="row">

		<div class="col-xs-12 col-sm-4">
			<div role="tabpanel">
			    <!-- 標籤面板：標籤區 -->
			    <ul class="nav nav-tabs" role="tablist">
			        <li role="presentation" class="active">
			            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">計畫清單</a>
			        </li>
			        <li role="presentation">
			            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">我的最愛清單</a>
			        </li>
			    </ul>
			
			    <!-- 計畫清單區 -->
			    <div class="tab-content">
			        <div role="tabpanel" class="tab-pane active" id="tab1">
			        	<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
			        	  <!-- 計畫1 -->
			        	  <c:forEach var="planVO" items="${planSvc.getfindByPK(7002)}" >
			        	  <div class="panel panel-default">
			        	    <div class="panel-heading" role="tab" id="panel1">
			        	      <h4 class="panel-title">
			        	        <a href="#${planVO.plan_id}" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
			        	          ${planVO.plan_name}
			        	        </a>
			        	      </h4>
			        	    </div>
			        	    <div id="${planVO.plan_id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel1">
			        	      <div class="panel-body">
			        	        <div id='external-events'>
			        	        	<div id='external-events-listing'>
			        	        		<c:forEach var="plan_detailVO" items="${plan_detailSvc.getByPK(planVO.plan_id)}"	>	
			        	        		<div class='fc-event'>${plan_detailVO.cou_id}</div>
										</c:forEach>
									</div>
									 <!-- <p>
										<input type='checkbox' id='drop-remove' />
										<label for='drop-remove'>remove after drop</label>
									</p> -->
								</div>
			        	      </div>
			        	    </div>
			        	  </div>
			        	  </c:forEach>
			        	</div>
			        </div>
			        <!-- 我的最愛計畫清單區 -->
			        <div role="tabpanel" class="tab-pane" id="tab2">
			        	<div class="panel-group" id="accordion22" role="tablist" aria-multiselectable="true">
			        	  <!-- 計畫1 -->
			        	  <div class="panel panel-default">
			        	    <div class="panel-heading" role="tab" id="panel11">
			        	      <h4 class="panel-title">
			        	        <a href="#aaaa" data-parent="#accordion22" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaaa">
			        	          計畫一
			        	        </a>
			        	      </h4>
			        	    </div>
			        	    <div id="aaaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel11">
			        	      <div class="panel-body">
			        	        <div id='external-events'>		
			        	        	<div id='external-events-listing'>
			        	        		<div class='fc-event'>課程1</div>
										<div class='fc-event'>課程2</div>
										<div class='fc-event'>課程3</div>
										<div class='fc-event'>課程4</div>
										<div class='fc-event'>課程5</div>
										<div class='fc-event'>課程6</div>
									</div>
									 <!-- <p>
										<input type='checkbox' id='drop-remove' />
										<label for='drop-remove'>remove after drop</label>
									</p> -->
								</div>
			        	      </div>
			        	    </div>
			        	  </div>
			        	  <!-- 計畫2 -->
			        	  <div class="panel panel-default">
			        	    <div class="panel-heading" role="tab" id="panel22">
			        	      <h4 class="panel-title">
			        	        <a href="#bbbb" data-parent="#accordion22" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbbb">
			        	          計畫二
			        	        </a>
			        	      </h4>
			        	    </div>
			        	    <div id="bbbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel22">
			        	      <div class="panel-body">
			        	        <div id='external-events'>
			        	       	 <div id='external-events-listing'>
		        	        		<div class='fc-event'>課程1</div>
									<div class='fc-event'>課程2</div>
									<div class='fc-event'>課程3</div>
									<div class='fc-event'>課程4</div>
									<div class='fc-event'>課程5</div>
									<div class='fc-event'>課程6</div>
								</div>	
									 <!-- <p>
										<input type='checkbox' id='drop-remove' />
										<label for='drop-remove'>remove after drop</label>
									</p> -->
								</div>
			        	      </div>2
			        	    </div>
			        	  </div>
			        	  <!-- 計畫3 -->
			        	  <div class="panel panel-default">
			        	    <div class="panel-heading" role="tab" id="panel33">
			        	      <h4 class="panel-title">
			        	        <a href="#cccc" data-parent="#accordion22" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="cccc">
			        	          計畫三
			        	        </a>
			        	      </h4>
			        	    </div>
			        	    <div id="cccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel33">
			        	      <div class="panel-body">
			        	        <div id='external-events'>	
			        	       		 <div id='external-events-listing'>
			        	        		<div class='fc-event'>課程1</div>
										<div class='fc-event'>課程2</div>
										<div class='fc-event'>課程3</div>
										<div class='fc-event'>課程4</div>
										<div class='fc-event'>課程5</div>
										<div class='fc-event'>課程6</div>
									</div>	
									 <!-- <p>
										<input type='checkbox' id='drop-remove' />
										<label for='drop-remove'>remove after drop</label>
									</p> -->
								</div>
			        	      </div>
			        	    </div>
			        	  </div>
			        	</div>
			        </div>
			    </div>
			</div>
		</div>
		
		<!-- 行事曆 -->
		<div class="col-xs-12 col-sm-8">
			<div id='calendar'></div>
			<div style='clear:both'></div>
		</div>

	</div>
</div>
	
<form id="form1" method="post" action="<%=request.getContextPath()%>/plan_detail/plan_detail.do">
			<input type="hidden" name="action" value="insert_calendar">
			<input type="hidden" name="plan_id" value="P00001">
			<input type="hidden" name="cou_id" val="json" />
			<input id="myBtn" type="button" value="新增">			
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
				<div class="col-xs-12 col-sm-4">
					<a href="#">關於我們</a>｜
					<a href="#">客服中心</a>
				</div>
				<div class="col-xs-12 col-sm-4">
					
				</div>
				<div class="col-xs-12 col-sm-4">
					
				</div>
			</div>
		</div>

</body>
</html>
