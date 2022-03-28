<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.roomTypePic.model.*"%>

<%
RoomTypeService roomTypeService = new RoomTypeService();
List<RoomTypeVO> list = roomTypeService.getAll();
pageContext.setAttribute("list", list);
%>
<jsp:useBean id="roomPic" scope="page"
	class="com.roomTypePic.model.RoomTypePicService" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>room type introduction</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicons -->
    <link href="<%=request.getContextPath() %>/resources/<%=request.getContextPath() %>/resources/img/favicon.ico" rel="icon">
    <link href="<%=request.getContextPath() %>/resources/<%=request.getContextPath() %>/resources/img/apple-favicon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700|Raleway:100,200,300,400,500,600,700,800,900" rel="stylesheet">

    <!-- Vendor CSS File -->
    <link href="<%=request.getContextPath() %>/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/vendor/slick/slick.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/vendor/slick/slick-theme.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/vendor/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Main Stylesheet File -->
    <link href="<%=request.getContextPath() %>/resources/css/hover-style.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/style.css" rel="stylesheet">
</head>

<body>
<!-- Header Section Start -->
<%@ include file= "/front-end/framework/header.file" %>
<!-- Header Section End -->

<!-- Search Section Start -->
<div id="search" style="background: #f2f2f2;">
    <div class="container">
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomSchedule/roomSchedule.do" name="form1">
        <div class="form-row">
              <div class="control-group col-md-3">
                  <label>入住日期</label>
                  <div class="form-group">
                      <div class="input-group date"  data-target-input="nearest">
                          <input name="arrival_date" id="arrival_date" class="form-control" type="text">
                          <div class="input-group-append" >
                              <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="control-group col-md-3">
                  <label>退房日期</label>
                  <div class="form-group">
                      <div class="input-group date" id="date-4" data-target-input="nearest">
                          <input name="departure_date" id="departure_date" class="form-control" type="text"  value= "${roomOrderVO.departure_date}">
                          <div class="input-group-append" >
                              <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="control-group col-md-3">
                  <div class="form-row">
                      <div class="control-group col-md-6">
                          <label>房間數量</label>
                          <select class="custom-select" name="totalrooms" id="totalrooms">
                              <option value="1">1</option>
                              <option value="2">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                              <option value="7">7</option>
                              <option value="8">8</option>
                              <option value="9">9</option>
                              <option value="10">10</option>
                          </select>
                      </div>
                      <div class="control-group col-md-6 control-group-kid">
                          <label>人數</label>
                          <select class="custom-select" name="num_of_people" id="num_of_people" >
                              <option value="1">1</option>
                              <option value="2">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                              <option value="7">7</option>
                              <option value="8">8</option>
                              <option value="9">9</option>
                              <option value="10">10</option>
                          </select>
                      </div>
                  </div>
            </div>
            <div class="control-group col-md-3">
              	  <input type="hidden" name="action" value="searchEmptyRoomByDate">
                  <button class="btn btn-block" type="submit">Search</button>
            </div>
        </div>
      </form>
    </div>
</div>
<!-- Search Section End -->

<!-- Room Section Start -->
<div id="rooms">
    <div class="container">
        <div class="section-header">
            <h2>房型介紹</h2>          
        </div>
        <div class="row">
            <c:forEach var="roomTypeVO" items="${list}" varStatus="aaa">
            
            <c:if test="${aaa.index%2 == 0}">
        	<div class="col-md-12 room">
                <div class="row">
                    <div class="col-md-6">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()%>/roomType/roomTypePic.do?room_type_id=${roomTypeVO.room_type_id}"
                                   width="600px" height="400px">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="room-des">
                            <h3>${roomTypeVO.room_type_name}</h3>
                            <h1>NT\$${roomTypeVO.room_type_price}<span>/ Night</span></h1>
                             <div class="room-size">
                                <p class="font-weight-light explainP">${roomTypeVO.room_type_content}</p>
                            </div>
                            <ul class="room-icon">
                                <li class="icon-1"></li>
                                <li class="icon-2"></li>
                                <li class="icon-3"></li>
                                <li class="icon-4"></li>
                                <li class="icon-5"></li>
                                <li class="icon-6"></li>
                                <li class="icon-7"></li>
                                <li class="icon-8"></li>
                                <li class="icon-9"></li>
                                <li class="icon-10"></li>
                            </ul>
                            <div class="room-link">
                                <a href="#">Book Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>
            
            <c:if test="${aaa.index%2 == 1}">
            <div class="col-md-12 room">
                <div class="row">
                    <div class="col-md-6 d-block d-md-none">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()%>/roomType/roomTypePic.do?room_type_id=${roomTypeVO.room_type_id}"
                                 width="600px" height="400px">>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="room-des">
                            <h3>${roomTypeVO.room_type_name}</h3>
                            <h1>NT\$${roomTypeVO.room_type_price}<span>/ Night</span></h1>
                            <div class="room-size">
                                <p class="font-weight-light explainP">${roomTypeVO.room_type_content}</p>
                            </div>
                            <ul class="room-icon">
                                <li class="icon-1"></li>
                                <li class="icon-2"></li>
                                <li class="icon-3"></li>
                                <li class="icon-4"></li>
                                <li class="icon-5"></li>
                                <li class="icon-6"></li>
                                <li class="icon-7"></li>
                                <li class="icon-8"></li>
                                <li class="icon-9"></li>
                                <li class="icon-10"></li>
                            </ul>
                            <div class="room-link">
                                <a href="#">Book Now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 d-none d-md-block">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()%>/roomType/roomTypePic.do?room_type_id=${roomTypeVO.room_type_id}"
                                 width="600px" height="400px">
                        </div>
                    </div>
                </div>
            </div>        	
            </c:if>
            
            </c:forEach>
        </div>
    </div>
</div>
<!-- Room Section End -->



<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>
<%@ include file= "/resources/datetimepicker/dateTimepickerSettingForSeearch.file" %>
<!-- Footer Section End -->

<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

<!-- Vendor JavaScript File -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/jquery/jquery-migrate.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/easing/easing.min.js"></script>
<script src="vendor/stickyjs/sticky.js"></script>
<script src="vendor/superfish/hoverIntent.js"></script>
<script src="vendor/superfish/superfish.min.js"></script>
<script src="vendor/wow/wow.min.js"></script>
<script src="vendor/slick/slick.min.js"></script>
<script src="vendor/tempusdominus/js/moment.min.js"></script>
<script src="vendor/tempusdominus/js/moment-timezone.min.js"></script>
<script src="vendor/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Booking Javascript File -->
<script src="js/booking.js"></script>
<script src="js/jqBootstrapValidation.min.js"></script>

<!-- Main Javascript File -->
<script src="js/main.js"></script>
</body>
</html>
