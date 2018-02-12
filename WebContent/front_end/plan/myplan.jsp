<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan_detail.model.*"%>
<%@ page import="com.plan.model.*"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ include file ="/front_end/navbar.jsp" %>

<jsp:useBean id="planSvc" scope="page" class="com.plan.model.PlanService" />
<jsp:useBean id="plan_detailSvc" scope="page" class="com.plan_detail.model.Plan_DetailService" />
<!DOCTYPE html>


<%
    PlanVO planVO = (PlanVO) request.getAttribute("planVO");
	session.getAttribute("memVO");
	
// List<PlanVO> list = planSvc.getfindByPK(7002);
// pageContext.setAttribute("list",list);
// 	pageContext.setAttribute("mem_id", 7002);
	
%>

<%--   <%

// 	Plan_DetailService plan_detailSvc = new Plan_DetailService();
// 	Plan_DetailVO plan_detailVO = (Plan_DetailVO) request.getAttribute("plan_detailVO");
	Plan_DetailVO plan_detailVO = new Plan_DetailVO();
    List<Plan_DetailVO> list = plan_detailSvc.getByPK("P00001");
    pageContext.setAttribute("list",list);
    
%> --%>

<html>
<head>
	<title>Fit Style</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
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
/* .zindex{ */
/* 		z-index:9999; */
/* 	}	 */
</style>	
	
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
		<div class="col-xs-12 col-sm-5">
			<input type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" value="建立計畫">
				<div class="btn-group">
				  <button type="button" class="btn btn-primary">選擇計畫</button>
  				  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
  					<span class="caret"></span>
  				  </button>
				  	  <ul class="dropdown-menu" role="menu">
						 <c:forEach var="planVO" items="${planSvc.getfindByPK(memVO.mem_id)}">
					    	<li style="list-style-type: none;"><a class="dropdown-item" href="<%= request.getContextPath() %>/front_end/plan/plan.do?plan_id=${ planVO.plan_id }&mem_id=${ memVO.mem_id }&action=addcou_toplan">${planVO.plan_name}</a></li>
					 	</c:forEach>
					 </ul>
				</div>
		</div>
			
			
		<!--  新增計畫 -->		
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">輸入資料</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/plan/plan.do" name="form1">
		          <div class="form-group">
		            <label for="recipient-name" class="col-form-label">${memVO.mem_name}</label>
		            
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="col-form-label">計畫名稱:</label>
		            <input type="TEXT" name="plan_name" size="20"
						 value="<%= (planVO==null)? "" : planVO.getPlan_name()%>" />
		          </div>
		          	  <input type="hidden" name="mem_id" value="${memVO.mem_id}" />
			          <input type="hidden" name="shareplan" value="on" />
			          <input type="hidden" name="interval" size="45" value="0" />
			          <input type="hidden" name="cycle" size="45" value="0" />
			      <div class="modal-footer">
			        <input type="hidden" name="action" value="insert">
			        <input type="hidden" name="plan_id" value="${planVO.plan_id}">
					<input type="submit" class="btn-primary" value="新增">
			      </div>
			    </form>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- 行事曆 -->
		<div class="col-xs-12 col-sm-7">
			<div id='calendar'></div>
			<div style='clear:both'></div>
		</div>

	</div>
</div>
	
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

<%@ include file="/front_end/footer.jsp" %>	
<% 
  java.sql.Date startdate = null;
  try {
	  startdate = planVO.getStartdate();
   } catch (Exception e) {
	  startdate = new java.sql.Date(System.currentTimeMillis());
   }
%>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=startdate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

//              1.以下為某一天之前的日期無法選擇
             var somedate1 = new Date();
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
</script>
</body>
	<link href="<%= request.getContextPath() %>/front_end/css/nav-bar.css" rel="stylesheet" type="text/css"/>
	<link href='<%= request.getContextPath() %>/front_end/css/fullcalendar.min.css' rel='stylesheet' />
	<link href='<%= request.getContextPath() %>/front_end/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='<%= request.getContextPath() %>/front_end/js/moment.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/jquery.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/jquery-ui.min.js'></script>
	<script src='<%= request.getContextPath() %>/front_end/js/fullcalendar.min.js'></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</html>
