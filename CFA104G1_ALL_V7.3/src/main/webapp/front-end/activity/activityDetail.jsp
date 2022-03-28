<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.activitySession.model.*"%>
<%
ActivityService activityService = new ActivityService();
List<ActivityVO> list = activityService.getCate1Act();
pageContext.setAttribute("list", list);
%>
<%
ActivitySessionService activitySessionService = new ActivitySessionService();
List<ActivitySessionVO> listActSession = activitySessionService.getAllActivitySession();
pageContext.setAttribute("listActSession", listActSession);
%>
<%
ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>彌生溫泉度假酒店 - 活動詳情</title>
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
                        <span class="h1 text-uppercase text-dark bg-light px-2">彌生溫泉度假酒店</span>
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

    <!-- Shop Detail Start -->
    <div class="container-fluid pb-5">
        <div class="row px-xl-5">
            <div class="col-lg-5 mb-30">
                <div id="product-carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner bg-light">
                        <div class="carousel-item active">
                            <img class="w-100 h-100" 
                            src="<%=request.getContextPath()%>/ActivityPicReader.do?activity_id=${activityVO.activity_id}" alt="Image">
                        </div>
                        ${activityVO.activity_id}
                    </div>
                    <a class="carousel-control-prev" href="#product-carousel" data-slide="prev">
                        <i class="fa fa-2x fa-angle-left text-dark"></i>
                    </a>
                    <a class="carousel-control-next" href="#product-carousel" data-slide="next">
                        <i class="fa fa-2x fa-angle-right text-dark"></i>
                    </a>
                </div>
            </div>

            <div class="col-lg-7 h-auto mb-30">
                <div class="h-100 bg-light p-30">
                    <h3>${activityVO.activity_name}</h3>
				     <table>
						<tr>
							<th>活動場次編號</th>
							<th>活動編號</th>
							<th>活動場次名稱</th>
							<th>報名開始日期</th>
							<th>報名截止日期</th>
							<th>活動日期起</th>
							<th>活動日期迄</th>
							<th>活動定價</th>
							<th>活動狀態</th>
							<th>狀態備註</th>
							<th>活動人數上限</th>
							<th>活動人數下限</th>
							<th>報名總數</th>
							<th>商品狀態</th>
							<th>Click!</th>
						</tr>
						<c:forEach var="activitySessionVO" items="${listActSession}">
						
							<tr>
							<td>${activitySessionVO.activity_session_id}</td>
							<td>${activitySessionVO.activity_id}</td>
							<td>${activitySessionVO.activity_session_name}</td>
							<td>${activitySessionVO.entered_started}</td>
							<td>${activitySessionVO.entered_end}</td>
							<td>${activitySessionVO.activity_started}</td>
							<td>${activitySessionVO.activity_end}</td>
							<td>${activitySessionVO.activity_price}</td>
							<td>${activitySessionVO.activity_state}</td>
							<td>${activitySessionVO.status_note}</td>
							<td>${activitySessionVO.activity_max_part}</td>
							<td>${activitySessionVO.activity_min_part}</td>
							<td>${activitySessionVO.entered_total}</td>
							<td>${activitySessionVO.product_status}</td>
							<td>
								<input id='${activitySessionVO.activity_session_id}' name='entered' type="button" value="立即報名"> 
							</td>
							</tr>
						</c:forEach>
						</table>
                </div>
            </div>
        </div>
        <FORM ID='actSesDetail' METHOD="post" ACTION="<%=request.getContextPath()%>/transOrder.do"
			style="margin-bottom: 0px;">
			<input type="hidden" name="activity_session_id" id="activity_session_id" value=""> 
<!-- 			<input type="hidden" name="activity_max_part" id="activity_max_part" value="">  -->
<!-- 			<input type="hidden" name="entered_total" id="entered_total" value="">  -->
		</FORM>
        <div class="row px-xl-5">
            <div class="col">
                <div class="bg-light p-30">
                    <div class="nav nav-tabs mb-4">
                        <a class="nav-item nav-link text-dark active" data-toggle="tab" href="#tab-pane-1">活動資訊</a>
                    </div>
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="tab-pane-1">
                            <p>${activityVO.activity_info}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Shop Detail End -->


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
<script>
	$('input[name="entered"]').click(function(){
		$('#activity_session_id').val($(this).attr('id'));
// 		$('#activity_max_part').val($(this).attr('max'));
// 		$('#entered_total').val($(this).attr('total'));
		$('#actSesDetail').submit();
	})
</script>
</html>