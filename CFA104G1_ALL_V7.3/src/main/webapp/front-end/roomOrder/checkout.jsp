<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomOrder.model.*"%>    
<%
RoomOrderVO roomOrderVO1 = new RoomOrderVO();
roomOrderVO1.setMem_id(1);
session.setAttribute("user", roomOrderVO1);
%>   
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
<%@ include file= "/front-end/framework/include.file" %>
    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet'>
    <link href='https://use.fontawesome.com/releases/v5.8.1/css/all.css' rel='stylesheet'>
    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <title>Checkout example · Bootstrap v5.1</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/checkout/">

    


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
       body {
            background: #f5f5f5
        }

        .rounded {
            border-radius: 1rem
        }

        .nav-pills .nav-link {
            color: #555
        }

        .nav-pills .nav-link.active {
            color: white
        }

        input[type="radio"] {
            margin-right: 5px
        }

        .bold {
            font-weight: bold
        }
    </style>

    
    <!-- Custom styles for this template -->
    
  </head>
  <body>
<%@ include file= "/front-end/framework/header.file" %>
    
<div class="container">
  <main>
    <div class="py-5 text-center">
    </div>

    <div class="row g-5">
     				
		<div >
		<h4 class="mb-3">訂房資訊</h4>
            
        <form class="needs-validation" novalidate METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrder/roomOrder.do" name="form1">
		     <div class="row g-3">
		 			<div class="col-2">
		    			<label class="form-label">入住時間</label>
		    			<input name="arrival_date" class="form-control"  id="arrival_date" type="text" value="${roomOrderVO.arrival_date}" readonly>
		  			</div>
					<div class="col-2">
					    <label class="form-label">退房時間</label>
					    <input name="departure_date" class="form-control"  id="departure_date" type="text" value="${roomOrderVO.departure_date}" readonly> 
					</div>
					<div class="col-md-2">
					    <label for="inputAddress" class="form-label">房型</label>
					    <input name="room_type_name" class="form-control"  id="room_type_name" type="text" value="${roomTypeVO.room_type_name}" readonly>   
					</div>
					<div class="col-md-2">
					    <label for="inputAddress" class="form-label">住房人數</label>
					    <input name="number_of_people" class="form-control"  id="number_of_people"type="text" value = "${roomOrderVO.num_of_people}" readonly>
					</div>
					<div class="col-md-2">
					    <label class="form-label">訂房數量</label>
					    <input name="total_room_number" class="form-control"  id="total_room_number" type="text" value="${roomOrderVO.totalrooms}" readonly>       
					</div>
					<div class="col-md-6">
					    <label class="form-label">訂單總金額:</label>
					    <input type="text" class="form-control"  name="room_charge" id="room_charge" value="${roomOrderVO.totalrooms*roomTypeVO.room_type_price*roomOrderVO.totaldays}" readonly>
					</div>
					<div class="col-md-8 g-3 input-group">
			  			<span class="input-group-text">特殊需求</span>
			  			<textarea class="form-control" aria-label="With textarea" rows="6" name="special_req" id="special_req" ></textarea>
					</div>
		     </div>
		     <h4>付款方式</h4>
		     <div class="row">
              <div class="col-5 col-sm-3">
                <div class="nav flex-column nav-tabs h-100" id="vert-tabs-tab" role="tablist" aria-orientation="vertical">
                  <a class="nav-link active" id="vert-tabs-home-tab" data-toggle="pill" href="#vert-tabs-home" role="tab" aria-controls="vert-tabs-home" aria-selected="true">現場付款</a>
                  <a class="nav-link" id="vert-tabs-profile-tab" data-toggle="pill" href="#vert-tabs-profile" role="tab" aria-controls="vert-tabs-profile" aria-selected="false">信用卡線上付款</a>
                  
                </div>
              </div>
              <div class="col-7 col-sm-9">
                <div class="tab-content" id="vert-tabs-tabContent">
                  <div class="tab-pane text-left fade show active" id="vert-tabs-home" role="tabpanel" aria-labelledby="vert-tabs-home-tab">
                        <ul ><li><span>以飯店當地貨幣付款給飯店</span></li><li><span>在飯店的取消限期內都可以免費取消</span></li></ul>
                  </div>
                  <div class="tab-pane fade" id="vert-tabs-profile" role="tabpanel" aria-labelledby="vert-tabs-profile-tab">
  						
  						 <div class="form-group col-5"> <label for="username">
                                            <h6>Card Owner</h6>
                                        </label> <input type="text" name="username" placeholder="Card Owner Name"
                                            required class="form-control "> </div>
                                    <div class="form-group col-6"> <label for="cardNumber">
                                            <h6>Card number</h6>
                                        </label>
                                        <div class="input-group"> <input type="text" name="cardNumber"
                                                placeholder="Valid card number" class="form-control " required>
                                            <div class="input-group-append"> <span class="input-group-text text-muted">
                                                    <i class="fab fa-cc-visa mx-1"></i> <i
                                                        class="fab fa-cc-mastercard mx-1"></i> <i
                                                        class="fab fa-cc-amex mx-1"></i> </span> </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-9">
                                            <div class="form-group col-8"> <label><span class="hidden-xs">
                                                        <h6>Expiration Date</h6>
                                                    </span></label>
                                                <div class="input-group"> <input type="number" placeholder="MM" name=""
                                                        class="form-control" required> <input type="number"
                                                        placeholder="YY" name="" class="form-control" required> </div>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <div class="form-group mb-4"> <label data-toggle="tooltip"
                                                    title="Three digit CV code on the back of your card">
                                                    <h6>CVV <i class="fa fa-question-circle d-inline"></i></h6>
                                                </label> <input type="text" required class="form-control"> </div>
                                        </div>
                                    </div>
                </div>
              </div>
            </div>
		     
		     
		
		         
		      
		
		  
		            <input type="hidden" name="action" value="SaveOder">
		            <input type="hidden" name="mem_id" size="45"value="${user.mem_id}" />
		            <input type="hidden" name="order_date" id="order_date"/>
		            <input type="hidden" name="room_order_status" id="room_order_status" value= "1">
		            <input type="hidden" name="room_review" id="room_review"value = "0">
					<input type="hidden" name="room_id" id="room_id" value = "0">
					<input type="hidden" name="room_price" id="room_price" value = "${roomTypeVO.room_type_price}">
					<input type="hidden" name="service_order_id" id="service_order_id" value = "0">
					<input type="hidden" name="room_type_id" id="room_type_id" value="${roomTypeVO.room_type_id}">   
		          <button class="w-20 btn btn-success btn-lg col-3" type="submit">結帳</button>
        </form>
      </div>
    </div>
  </main>


</div>

<%@ include file= "/front-end/framework/footer.file" %>

<script src="<%=request.getContextPath()%>/front-end/roomOrder/form-validation.js"></script>
<script type='text/javascript' src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>
<script type='text/javascript' src=''></script>
<script type='text/javascript' src=''></script>
<script type='text/Javascript'>$(function() {$('[data-toggle="tooltip"]').tooltip()})</script>
</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.full.js"></script>
 <style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style> 
<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	
	 	var today = new Date();
  		$('#arrival_date').datetimepicker({
  		format:'Y-m-d',
  	    timepicker:false,
        beforeShowDay: function(date) {
    	  if (  date.getYear() <  today.getYear() || 
		           (date.getYear() == today.getYear() && date.getMonth() <  today.getMonth()) || 
		           (date.getYear() == today.getYear() && date.getMonth() == today.getMonth() && date.getDate() < today.getDate())
          ) {
               return [false, ""]
          }
          return [true, ""];
  		}});  
	
	 
	 	$('#departure_date').datetimepicker({
		  format:'Y-m-d',
	 	  onShow:function(){
	 	  this.setOptions({
	   	  minDate:$('#arrival_date').val()?$('#arrival_date').val():false
	   	  })
	  	  },
	  	  timepicker:false,
	    });
	 	
	 	$('#order_date').datetimepicker({
			  format:'Y-m-d',
		 	  onShow:function(){
		 	  this.setOptions({
		   	  minDate:$('#arrival_date').val()?$('#arrival_date').val():false
		   	  })
		  	  },
		  	  timepicker:false,
		  	  value:today
		    });
});
</script>
</html>
