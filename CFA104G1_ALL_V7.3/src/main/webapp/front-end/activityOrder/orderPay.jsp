<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.activityOrder.model.*"%>

    <%
    ActivityOrderVO activityOrderVO = (ActivityOrderVO) request.getAttribute("activityOrderVO") ;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>彌生溫泉度假酒店 - 付款頁面</title>
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
                    <span class="breadcrumb-item active">付款資訊</span>
                    
                </nav>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
<div class="container-fluid">
		<div class="row px-xl-5">
            <div class="col-lg-8">
                <h4 class=" position-relative text-uppercase mb-3">訂單已成立<br>*付款方式請擇一，並在24小時內付款</h4>
            </div>
         </div>
</div>
    <!-- Checkout Start -->
    <div class="container-fluid">
        <div class="row px-xl-5">
            <div class="col-lg-8">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">信用卡付款</span></h5>
                <div class="bg-light p-30 mb-5">
                		<form action="<%=request.getContextPath()%>/front-end/activityOrder/paySuccess.jsp">
                        <div class="col-md-6 form-group">
                                <label>卡號</label>
                                <input  type="text" maxlength="4"  size="3">
                                <input type="text" maxlength="4"  size="3">
                                <input  type="text" maxlength="4"  size="3">
                                <input  type="text" maxlength="4"  size="3">
                        </div>
                        <div class="col-md-6 form-group">
                                三碼檢查碼<br>
                                <input class="form-control" type="password" maxlength="3"  size="15">
                       </div>
                       <div class="col-md-6 form-group">
                                到期日<br>
                                <input class="inputCard form-control" type="month" required/>
                        </div>
                        
                     	 <button type="submit" class="btn btn-block btn-primary font-weight-bold py-3">確認送出</button>
                        </form>
                </div>
            </div>
            <div class="col-lg-4">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">匯款資訊</span></h5>
                <div class="bg-light p-30 mb-5">
                    <div class="border-bottom">
                        <div class="d-flex justify-content-between">
                            <p>收款人:王曉明</p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>土地銀行</p>
                            <p>銀行代號:007</p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>收款帳號</p>
                            <p>034-234-603172</p>
                        </div>
                        <jsp:useBean id="activityOrderService" scope="page" class="com.activityOrder.model.ActivityOrderService" />
                        
                        <div class="d-flex justify-content-between">
                            <p>轉帳金額</p>
                            <p>${activityOrderVO.order_amount}</p>
                        </div>
                        <input id='actPrice' type='hidden' value='${activityOrderVO.order_amount}'/>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Checkout End -->


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
		$("input.card").on("keydown", function(e){
		    //console.log(e.which);
		    if((e.which >= 48 && e.which <= 57) || e.which == 8){
		      
		      //console.log(e.target.value.length);
		      if(e.target.value.length == 0 && e.which == 8){
		        $(this).prev().focus();
		      }
		    }else{
		      e.preventDefault();
		    }
		});

		$("input.card").on("keyup", function(e){
		  
		  // \D 代表非數字字元，g 代表全域尋找
		  //let str = e.target.value;
		  //console.log(e.target.value);
		  let str = (e.target.value).replace(/\D/g, "");
		  
		  $(this).val(str);
		  
		  
		  //console.log(str.length);
		  if(str.length == 4){
		    $(this).next().focus();
		  }
		});
</script>

</html>