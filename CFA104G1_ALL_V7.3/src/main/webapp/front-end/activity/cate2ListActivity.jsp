<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%
ActivityService activityService = new ActivityService();
List<ActivityVO> list = activityService.getCate2Act();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>彌生溫泉度假酒店 - 徜徉山林</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
<%@ include file= "/front-end/framework/include.file" %>

    <!-- Favicon -->
    <link href="<%=request.getContextPath()%>/resources/front/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

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
                    <a class="breadcrumb-item text-dark" href="#">返回活動首頁</a>
                    <a class="breadcrumb-item text-dark" href="#">所有活動清單</a>
                    <span class="breadcrumb-item active">徜徉山林</span>
                </nav>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->


    <!-- Shop Start -->
    <div class="container-fluid">
        <div class="row px-xl-5">
            <!-- Shop Product Start -->
            <div class="col-lg-9 col-md-8">
                <div class="row pb-3">
                    <div class="col-12 pb-1">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <div>
                                <button class="btn btn-sm btn-light"><i class="fa fa-th-large"></i></button>
                                <button class="btn btn-sm btn-light ml-2"><i class="fa fa-bars"></i></button>
                            </div>
                            <div class="ml-2">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Sorting</button>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item" href="#">Latest</a>
                                        <a class="dropdown-item" href="#">Popularity</a>
                                        <a class="dropdown-item" href="#">Best Rating</a>
                                    </div>
                                </div>
                                <div class="btn-group ml-2">
                                    <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Showing</button>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item" href="#">10</a>
                                        <a class="dropdown-item" href="#">20</a>
                                        <a class="dropdown-item" href="#">30</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
				<c:forEach var="activityVO" items="${list}">
					<div class="col-lg-4 col-md-6 col-sm-6 pb-1">
						<div id="${activityVO.activity_id}" class="product-item bg-light mb-4 clickArea">
							<div class="product-img position-relative overflow-hidden">
								<img class="img-fluid w-100"
									src="<%=request.getContextPath()%>/ActivityPicReader?activity_id=${activityVO.activity_id}" alt=""
									width="100px" height="100px">
								<div class="product-action">
									<a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>
								</div>
							</div>
							<div class="text-center py-4">
                                <a class="h6 text-decoration-none text-truncate" href="">
                                	${activityVO.activity_name}
                                </a>
                            </div>
						</div>
					</div>
				</c:forEach>
				<div class="col-12">
					<nav>
						<ul class="pagination justify-content-center">
                            <li class="page-item disabled"><a class="page-link" href="#">Previous</span></a></li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
						</ul>
					</nav>
                    </div>
                </div>
            </div>
            <!-- Shop Product End -->
        </div>
    </div>
    <!-- Shop End -->

	<FORM ID='actDetail' METHOD="post" ACTION="<%=request.getContextPath()%>/pageTransition.do" 
		style="margin-bottom: 0px;">
		<input type="hidden" name="activityId" id="activityId" value=""> 
	</FORM>
	

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
	$('.clickArea').click(function(){
		$('#activityId').val($(this).attr('id'));
		$('#actDetail').submit();
	})
</script>
</html>