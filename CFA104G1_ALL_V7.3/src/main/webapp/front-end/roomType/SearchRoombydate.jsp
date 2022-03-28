<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.roomTypePic.model.*"%>
<%@ page import="com.roomSchedule.model.*"%>
<%@ page import="com.roomOrder.model.*"%>

<%

%>



<!DOCTYPE html>
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Favicons -->
<link href="/resources/img/favicon.ico" rel="icon">
<link href="/resources/img/apple-favicon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700|Raleway:100,200,300,400,500,600,700,800,900" rel="stylesheet"> 

    <!-- Vendor CSS File -->
<link href="<%=request.getContextPath()+"/resources/vendor/font-awesome/css/font-awesome.min.css" %>" rel="stylesheet">
<link href="<%=request.getContextPath()+"/resources/vendor/slick/slick.css" %>" rel="stylesheet">
<link href="<%=request.getContextPath()+"/resources/vendor/slick/slick-theme.css" %>" rel="stylesheet">
<link href="<%=request.getContextPath()+"/resources/vendor/tempusdominus/css/tempusdominus-bootstrap-4.min.css" %>" rel="stylesheet" />

    <!-- Main Stylesheet File -->
<link href="<%=request.getContextPath()+"/resources/css/hover-style.css" %>" rel="stylesheet">
<link href="<%=request.getContextPath()+"/resources/css/style.css" %>" rel="stylesheet">

    <!--載入其他-->
    <!-- Icon Font Stylesheet -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<style type="text/css">
 	.chat-room {
 		position: fixed;
	    background: #222222;
	    color: #C99A5B;
	    width: 44px;
	    height: 44px;
	    text-align: center;
	    line-height: 1;
	    font-size: 18px;
	    border-radius: 30px;
	    right: 15px;
	    bottom: 75px;
	    transition: background 0.5s;
	    z-index: 11;
 	}
 	.chat-room i {
	    padding-top: 12px;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    background: #eee
}

.ratings i {
    font-size: 16px;
    color: red
}

.strike-text {
    color: red;
    text-decoration: line-through
}

.product-image {
    width: 100%
}

.dot {
    height: 7px;
    width: 7px;
    margin-left: 6px;
    margin-right: 6px;
    margin-top: 3px;
    background-color: blue;
    border-radius: 50%;
    display: inline-block
}

.spec-1 {
    color: #938787;
    font-size: 15px
}

h5 {
    font-weight: 400
}

.para {
    font-size: 16px
}
</style>
</head>
<body>
<%@ include file= "/front-end/framework/header.file" %>
<div class="alert alert-success" role="alert">
  <h4 class="table-warning">下列是符合需求的房型</h4>
</div>
<div class="container mt-5 mb-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-10">
        <c:forEach var="roomTypeVO" items="${list}">
			    <form METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrder/roomOrder.do" name="form1">
			        <div class="row p-2 bg-white border rounded mt-2" >
			            <div class="col-md-3 mt-1">
			                <div id="carouselExampleControls${roomTypeVO.room_type_id}" class="carousel slide"
			                    data-bs-ride="carousel">
			                    <div class="carousel-inner" >
			                 
			                            <jsp:useBean id="roomTypePicSvc" scope="page"
			                                class="com.roomTypePic.model.RoomTypePicService" />
			                            <c:forEach var="picVO" items="${roomTypePicSvc.getAllbytypeid(roomTypeVO.room_type_id)}"
			                                varStatus="aaa">
			                                <c:if test="${aaa.index == 0 }">
			                                    <div class="carousel-item active">
			                                        <img src="<%=request.getContextPath()%>/roomType/roomTypePicId.do?room_photo_id=${picVO.room_photo_id}"
			                                            class="d-block w-100 " style=" height: 170px" >
			                                    </div>
			                                </c:if>
			                                <c:if test="${aaa.index != 0 }">
			                                    <div class="carousel-item">
			                                        <img src="<%=request.getContextPath()%>/roomType/roomTypePicId.do?room_photo_id=${picVO.room_photo_id}"
			                                            class="d-block w-100 " style=" height: 170px">
			                                    </div>
			                                </c:if>
			
			                            </c:forEach>
			
			                    </div>
			                    <button class="carousel-control-prev" type="button"
			                        data-bs-target="#carouselExampleControls${roomTypeVO.room_type_id}" data-bs-slide="prev">
			                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			                        <span class="visually-hidden">Previous</span>
			                    </button>
			                    <button class="carousel-control-next" type="button"
			                        data-bs-target="#carouselExampleControls${roomTypeVO.room_type_id}" data-bs-slide="next">
			                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
			                        <span class="visually-hidden">Next</span>
			                    </button>
			                </div>
			
			
			            </div>
			            <div class="col-md-6 mt-1">
			                <h5>${roomTypeVO.room_type_name}</h5>
			                <div class="d-flex flex-row">
			  
			                </div>
			                <p class="text-justify text-truncate para mb-0">${roomTypeVO.room_type_content}<br><br></p>
			            </div>
			            <div class="align-items-center align-content-center col-md-3 border-left mt-1">
			                <div class="d-flex flex-row align-items-center">
			                    <h4 class="mr-1">NT\$ ${roomTypeVO.room_type_price}</h4>
			                </div>
			                <div class="d-flex flex-column mt-4"><input class="btn btn-primary btn-warning" type="submit"
			                        value="預訂房間">
			                </div>
			            </div>
			        </div>
			        <input type="hidden" name="room_type_id" id="room_type_id" value="${roomTypeVO.room_type_id}">
			        <input type="hidden" name="room_type_price" id="room_type_price"  value="${roomTypeVO.room_type_price}">
			        <input type="hidden" name="room_type_name" id="room_type_name"  value="${roomTypeVO.room_type_name}">
			        <input type="hidden" name="action" value="SaveDateAndShowCheckOut">
			    </form>
			</c:forEach>
          
        </div>
    </div>
</div>

<%@ include file= "/front-end/framework/footer.file" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
<%--          <% RoomTypePicService roomTypePicSvc = new RoomTypePicService(); --%>
<%-- //             List<RoomTypePicVO> listPic = roomTypePicSvc.getAllbytypeid(${roomTypeVO.room_type_id}); -->%
<%--             pageContext.setAttribute("listPic", listPic);%> --%>
<!--             <div class="row p-2 bg-white border rounded mt-2"> -->
<%--             	<div id="carouselExampleControls${roomTypeVO.room_type_id}" class="carousel slide" data-bs-ride="carousel"> --%>
<!--   					<div class="carousel-inner"> -->
<!--    						 <div class="carousel-item active"> -->
<%--      						 <img src="<%=request.getContextPath()%>/roomType/roomTypePic.do?room_type_id=1" class="d-block w-100" > --%>
<!--     					</div> -->
<%--     				<c:forEach var="picVO" items="${listPic}"> --%>
<!--     					<div class="carousel-item"> -->
<%--      						 <img src="<%=request.getContextPath()%>/roomType/roomTypePicId2.do?room_photo_id=${picVO.room_photo_id}&room_type_id=${roomTypeVO.room_type_id}" class="d-block w-100" > --%>
<!--     					</div> -->
<%--     			    </c:forEach> --%>

<!--  				    </div> -->
<%--                     <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls${roomTypeVO.room_type_id}" data-bs-slide="prev"> --%>
<!--                        <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
<!--                        <span class="visually-hidden">Previous</span> -->
<!--                     </button> -->
<%--                     <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls${roomTypeVO.room_type_id}" data-bs-slide="next"> --%>
<!--                        <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
<!--                        <span class="visually-hidden">Next</span> -->
<!--                    </button> -->
<!--                 </div> -->