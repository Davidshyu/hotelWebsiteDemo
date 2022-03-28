<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>彌生溫泉度假酒店 - 活動報名首頁</title>
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


    <!-- Carousel Start -->
    <div class="container-fluid mb-3">
        <div class="row px-xl-5">
            <div class="col-lg-8">
                <div id="header-carousel" class="carousel slide carousel-fade mb-30 mb-lg-0" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#header-carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#header-carousel" data-slide-to="1"></li>
                        <li data-target="#header-carousel" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item position-relative active" style="height: 430px;">
                            <img class="position-absolute w-100 h-100" src="<%=request.getContextPath()%>/resources/front/img/carousel-1.jpg" style="object-fit: cover;">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                <div class="p-3" style="max-width: 700px;">
                                    <h1 class="display-4 text-white mb-3 animate__animated animate__fadeInDown">水上悠遊</h1>
                                    <p class="mx-md-5 px-5 animate__animated animate__bounceIn">雖然南投縣四周不靠海成為全台唯一群山圍繞的縣市，但也擁有全台最大的天然湖泊—『日月潭』，雖然沒有海灘可以戲水曬日光浴，但日月潭的水上體驗其實比你想像的還要豐富喔！</p>
                                    <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" href="#">查看詳情</a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item position-relative" style="height: 430px;">
                            <img class="position-absolute w-100 h-100" src="<%=request.getContextPath()%>/resources/front/img/carousel-2.jpg" style="object-fit: cover;">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                <div class="p-3" style="max-width: 700px;">
                                    <h1 class="display-4 text-white mb-3 animate__animated animate__fadeInDown">徜徉山林</h1>
                                    <p class="mx-md-5 px-5 animate__animated animate__bounceIn">森林不僅是島嶼最令人忘返的美景，更蘊含著心靈的解藥。到山野洗滌身心，由專業團隊帶你走進山林，透過呼吸調節、孤獨體驗等方式，讓森林帶走你的疲倦與壓力。</p>
                                    <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" href="#">查看詳情</a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item position-relative" style="height: 430px;">
                            <img class="position-absolute w-100 h-100" src="<%=request.getContextPath()%>/resources/front/img/carousel-3.jpg" style="object-fit: cover;">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                <div class="p-3" style="max-width: 700px;">
                                    <h1 class="display-4 text-white mb-3 animate__animated animate__fadeInDown">農場風采</h1>
                                    <p class="mx-md-5 px-5 animate__animated animate__bounceIn">不絕於耳的大樹蟬鳴及此起彼落如同交響樂般的滿池蛙聲，是鄉野田間常見的景緻，帶孩子探索自然與小動物互動。>讓孩子們可以親身融入大自然的氛圍中，用心去感受大自然的美好。</p>
                                    <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" href="#">查看詳情</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="product-offer mb-30" style="height: 200px;">
                    <img class="img-fluid" src="<%=request.getContextPath()%>/resources/front/img/offer-1.jpg" alt="">
                    <div class="offer-text">
                        <h6 class="text-white text-uppercase">人氣活動</h6>
                        <h3 class="text-white mb-3">日月潭SUP立式划槳體驗</h3>
                        <a href="" class="btn btn-primary">查看詳情</a>
                    </div>
                </div>
                <div class="product-offer mb-30" style="height: 200px;">
                    <img class="img-fluid" src="<%=request.getContextPath()%>/resources/front/img/offer-2.jpg" alt="">
                    <div class="offer-text">
                        <h6 class="text-white text-uppercase">兒童最愛</h6>
                        <h3 class="text-white mb-3">綿羊羊毛氈手作體驗活動</h3>
                        <a href="" class="btn btn-primary">查看詳情</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Carousel End -->


    <!-- Featured Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5 pb-3">
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                    <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">戶外活動保險</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                    <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                    <h5 class="font-weight-semi-bold m-0">部分活動含<br>館內接駁車</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                    <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">指定活動日<br>一週前可免費取消</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                    <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">24小時內訂單確認</h5>
                </div>
            </div>
        </div>
    </div>
    <!-- Featured End -->


<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>
    <!-- JavaScript Libraries -->
    <script src="<%=request.getContextPath()%>/resources/front/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/front/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="<%=request.getContextPath()%>/resources/front/mail/jqBootstrapValidation.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/front/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath()%>/resources/front/js/main.js"></script>
</body>
</html>