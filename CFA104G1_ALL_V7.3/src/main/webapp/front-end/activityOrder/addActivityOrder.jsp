<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activityOrder.model.*"%>
<%-- <%@ page import="com.mem.model.*"%> --%>
<%@ page import="com.activityCategory.model.*"%>
<%@ page import="com.activitySession.model.*"%>
<%@ page import="com.mem.model.*"%>


<%
MemVO memVO = (MemVO) session.getAttribute("user");
%>

<% ActivityOrderVO activityOrderVO = (ActivityOrderVO) request.getAttribute("activityOrderVO"); %>
<%-- <% MemVO memVO = (MemVO) request.getAttribute("memVO"); %> --%>
<% ActivityCategoryVO activityCategoryVO = (ActivityCategoryVO) request.getAttribute("activityCategoryVO"); %>
<% ActivitySessionVO activitySessionVO = (ActivitySessionVO) request.getAttribute("activitySessionVO"); %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>彌生溫泉度假酒店 - 活動報名</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
<%@ include file= "/front-end/framework/include.file" %>

    <!-- Favicon -->
    <link href="<%=request.getContextPath()%>/resources/front/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

    <!-- Libraries Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/front/lib/animate/animate.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/front/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/front/css/style.css" rel="stylesheet">
</head>

<body>
<%@ include file= "/front-end/framework/header.file" %>
    <!-- Topbar Start -->
    <div class="container-fluid">
        
        <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
            <div class="col-lg-4">
                <a href="" class="text-decoration-none">
                    <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">活動專區</span>
                </a>
            </div>
            <div class="col-lg-4 col-6 text-left">
                <form action="">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for products">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-4 col-6 text-right">
                <p class="m-0">服務專線</p>
                <h5 class="m-0">+886-49-285-6788</h5>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid bg-dark mb-30">
        <div class="row px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a class="btn d-flex align-items-center justify-content-between bg-primary w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; padding: 0 30px;">
                    <h6 class="text-dark m-0"><i class="fa fa-bars mr-2"></i>功能選單</h6>
                    <i class="fa fa-angle-down text-dark"></i>
                </a>
                <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 999;">
                    <div class="navbar-nav w-100">
                        <a href="<%=request.getContextPath()%>/index.jsp" class="nav-item nav-link">彌生度假酒店首頁</a>
                        <a href="<%=request.getContextPath()%>/front-end/activity/activityIndex.jsp" class="nav-item nav-link">活動首頁</a>
                        <a href="<%=request.getContextPath()%>/front-end/mem/listAllMember.jsp" class="nav-item nav-link">會員專區</a>
                        <a href="" class="nav-item nav-link">報名須知</a>
                    </div>
                </nav>
            </div>
            <div class="col-lg-9">
                <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                    <a href="" class="text-decoration-none d-block d-lg-none">
                        <span class="h1 text-uppercase text-dark bg-light px-2">彌生度假酒店</span>
                        <span class="h1 text-uppercase text-light bg-primary px-2 ml-n1">活動專區</span>
                    </a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <a href="<%=request.getContextPath()%>/front-end/activity/shopListAllActivity.jsp" class="nav-item nav-link active">所有旅遊</a>
                            <a href="<%=request.getContextPath()%>/front-end/activity/cate1ListActivity.jsp" class="nav-item nav-link">水上悠遊</a>
                            <a href="<%=request.getContextPath()%>/front-end/activity/cate2ListActivity.jsp" class="nav-item nav-link">徜徉山林</a>
                            <a href="<%=request.getContextPath()%>/front-end/activity/cate3ListActivity.jsp" class="nav-item nav-link">農場風采</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <!-- Navbar End -->


    <!-- Breadcrumb Start -->
    <div class="container-fluid">
        <div class="row px-xl-5">
            <div class="col-12">
                <nav class="breadcrumb bg-light mb-30">
                    <a class="breadcrumb-item text-dark" href="<%=request.getContextPath()%>/front-end/activity/shopListAllActivity.jsp">返回活動商品頁面</a>
                    <span class="breadcrumb-item active">報名活動</span>
                </nav>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->


    <!-- Contact Start -->
    <div class="container-fluid">
        <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">報名活動</span></h2>
        <div class="row px-xl-5">
            <div class="col-lg-7 mb-5">
                <div class="contact-form bg-light p-30">
                    <div id="success"></div>
<!--                     <form name="sentMessage" id="contactForm" novalidate="novalidate"> -->
                    <FORM ID='actAmo' METHOD="post" ACTION="<%=request.getContextPath()%>/activityOrder.do" >
                        <div class="control-group">
                            會員編號:<br>
<%--                             <a>${memId}</a> --%>
                            <a>${user.mem_id}</a>
<%--                             <input type="hidden" name="mem_id" value="${memId}"> --%>
                            <input type="hidden" name="mem_id" value=${user.mem_id}>
                        </div>
                         <div class="control-group">
                            活動場次編號:<br>
                            <a name="activity_session_id" >${activitySessionVO.activity_session_id}</a>
                            <input type="hidden" name="activity_session_id" value='${activitySessionVO.activity_session_id}'>
                        </div>
                        <div class="control-group">
                        	訂單日期:<br>
                            <input type="date" name="order_time" class="form-control" id="order_time" readOnly="true"/>
                        </div>
                        <div class="control-group">
                        	報名人數:<br>
                            <input type='number' class='form-control' id='entered_number' min='1' max='5' autocomplete='off' name='entered_number'>
                        </div>
                        <div class="control-group">
                        	活動日期起:<br>
                            <a>${activitySessionVO.activity_started}</a>
                            <input type="hidden" name="activity_started" value="${activitySessionVO.activity_started}">
                        </div>
                        <div class="control-group">
                        	活動日期迄:<br>
                        	<a>${activitySessionVO.activity_end}</a>
                        	<input type="hidden" name="activity_end" value="${activitySessionVO.activity_end}">
                        </div>
                        <div class="control-group">
                        	訂單金額:<br>
                        	<a id='totalPrice'></a>
                        	<input type="hidden" name="order_amount" id="order_amount" value="">
                        </div>
                        <div class="control-group">
                        	訂單狀態:<br>
                            <input type="text" name="order_state" class="form-control" value="0" readOnly="true"/>
                        </div>
                        <div class="control-group">
                        	退款狀態:<br>
                            <input type="text" name="refund_state" class="form-control" value="0" readOnly="true"/>
                        </div>
                        <div class="control-group">
                        	訂單備註:<br>
                            <textarea class="form-control" rows="8" id="message" placeholder="Message"
                                required="required" name="order_memo" maxlength="50" rows="5" cols="80"></textarea>
                        </div>
                        <div>
                            <input type="hidden" name="action" value="insertFront">
                            <button name='amount' class="btn btn-primary py-2 px-4" type="submit" id="${activityOrderVO.order_amount}">立即報名</button><br>
                        </div>
                        <div>
                        <input id='actPrice' type='hidden' value='${activitySessionVO.activity_price}'/>
                    	<input type="hidden" name="order_amount" id="order_amount" value=""> 
                    	
                    	</div>
                    </FORM>
                </div>
            </div>
        </div>
    </div>
    <!-- Contact End -->

<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/front/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/front/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="<%=request.getContextPath()%>/resources/front/mail/jqBootstrapValidation.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/front/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath()%>/resources/front/js/main.js"></script>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<% 
  java.sql.Date order_time = null;
  try {
	  order_time = activityOrderVO.getOrder_time();
   } catch (Exception e) {
	   order_time = new java.sql.Date(System.currentTimeMillis());
   }
  %>
 <script>
		$('#order_time').val(new Date().toISOString().substring(0,10));
		$('#entered_number').blur(function(){
			$('#totalPrice').text($('#actPrice').val()*$(this).val());
			$('#order_amount').val($('#totalPrice').text());
		});
  </script>
</html>