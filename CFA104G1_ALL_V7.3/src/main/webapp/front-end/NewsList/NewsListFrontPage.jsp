<%@ page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.newsList.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<%
List<NewsListVO> list = (List<NewsListVO>) request.getAttribute("newsListVO_after");
if (list == null) {
	NewsListService newsListSvc = new NewsListService();
	list = newsListSvc.getAll().stream()
	.collect(Collectors.toList());
}
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<!--*******************	
		Start Include CSS File  
		******************* -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">
<link rel="stylesheet"
	href=" https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css ">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<!--*******************	
		End Include CSS File  
		******************* -->

<title>關於彌生&媒體報導</title>

<%@ include file= "/front-end/framework/include.file" %>

	<!--*******************	
		Start Include CSS File  
		******************* -->
        <%@ include file="../front_include_page/CSS_link.jsp"%>
	<!--*******************	
		End Include CSS File  
		******************* -->  

</head>

<body>
<%@ include file= "/front-end/framework/header.file" %>

<!--*******************	
		Start 關於彌生  
		******************* -->
		
	<section class="pt-5 pb-0">
		<div class="col-lg-3 col-sm-6">
			<div class="single-shop-products">
			</div>
		</div>

		<div class="container">
			<div class="row align-items-center ">
				<div class="col-12 col-md-6   mt-4 mt-md-0">
					<div id="carousel-40987"
						class="carousel slide text-center slider-custom">
						<ol class="carousel-indicators">
							<li data-target="#carousel-40987" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-40987" data-slide-to="1" class="">
							</li>
						</ol>
						<div class="carousel-inner text-center">
							<div class="item carousel-item active" style="left: -17.5001px;">
								<img class="img-fluid"
									src=" https://www.fleurdechinehotel.com/images/about/theme1.jpg"
									alt="">
							</div>
							<div class="item carousel-item" style="left: -17.5001px;">
								<img class="img-fluid"
									src=" https://www.fleurdechinehotel.com/images/about/theme1.jpg"
									alt="">
							</div>
						</div>
					</div>
				</div>
				<div class="col-12 col-md-4 offset-md-1 ">
					<h1 class="  text-center  mb-3 mt-5">彌生故事</h1>
					<p class="lead  text-center">位於日月潭北邊的雲品溫泉酒店，明亮恬靜的空間，融入日月潭豐富多變的景致，每個角落都能一覽這幅生動的自然畫作，美不勝收。
						酒店精心規畫211間客房，每房皆設有天然溫泉池，讓您自在地享受專屬的放鬆時刻。館內六間餐廳提供多樣化的中、西式美食，邀您前來盡情品味完美的度假體驗。</p>
				</div>
			</div>
		</div>
	</section>
	<section class="pt-5 pb-0">
		<div class="container">
			<div class="row align-items-center justify-content-between">
				<div class="col-12 col-md-4 offset-md-1 col-lg-5">
					<h1 class="  text-center  mb-3 mt-5">溫泉特色</h1>
					<p class="lead  text-center">位於日月潭北邊的雲品溫泉酒店，明亮恬靜的空間，融入日月潭豐富多變的景致，每個角落都能一覽這幅生動的自然畫作，美不勝收。
						酒店精心規畫211間客房，每房皆設有天然溫泉池，讓您自在地享受專屬的放鬆時刻。館內六間餐廳提供多樣化的中、西式美食，邀您前來盡情品味完美的度假體驗。</p>
				</div>
				<div class="col-12 col-md-6   mt-4 mt-md-0">
					<div id="carousel-40983"
						class="carousel slide text-center slider-custom">
						<ol class="carousel-indicators">
							<li data-target="#carousel-40983" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-40983" data-slide-to="1" class="">
							</li>
						</ol>
						<div class="carousel-inner text-center">
							<div class="item carousel-item active" style="left: -17.5001px;">
								<img class="img-fluid"
									src="https://blog.asiayo.com/wp-content/uploads/1601459867939_76325867_xl.jpg"
									alt="">
							</div>
							<div class="item carousel-item" style="left: -17.5001px;">
								<img class="img-fluid"
									src="https://blog.asiayo.com/wp-content/uploads/1601459867939_76325867_xl.jpg"
									alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="pt-5 pb-0">
		<div class="container">
			<div class="row align-items-center ">
				<div class="col-12 col-md-6   mt-4 mt-md-0">
					<div id="carousel-40987"
						class="carousel slide text-center slider-custom">
						<ol class="carousel-indicators">
							<li data-target="#carousel-40987" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-40987" data-slide-to="1" class="">
							</li>
						</ol>
						<div class="carousel-inner text-center">
							<div class="item carousel-item active" style="left: -17.5001px;">
								<img class="img-fluid"
									src=" https://tw.wamazing.com/media/wp-content/uploads/sites/4/2019/05/tendoonsen-ichiraku3.jpg"
									alt="">
							</div>
							<div class="item carousel-item" style="left: -17.5001px;">
								<img class="img-fluid"
									src=" https://tw.wamazing.com/media/wp-content/uploads/sites/4/2019/05/tendoonsen-ichiraku3.jpg"
									alt="">
							</div>
						</div>
					</div>
				</div>
				<div class="col-12 col-md-4 offset-md-1 ">
					<h1 class="  text-center  mb-3 mt-5">彌生觀光</h1>
					<p class="lead  text-center">目前集團旗下所屬酒店品牌：君品酒店、雲品溫泉酒店、翰品酒店、兆品酒店、品文旅、頤璽、君品Collection、羅馬大飯店、雲水之都、聖莊、嵐莊、蓉莊以及館務外餐飲事業(頤品大飯店、品中信茶樓）。</p>
					
				</div>
			</div>
		</div>
		
		<!--*******************	
		End 關於彌生
		******************* -->


	<!--*******************	
		Start 媒體報導  
		******************* -->
		
		<section class="pt-5 pb-5">
      <div class="container">
        <div class="row">
          <div class="col-6">
            <h3 class="mb-3" >媒體報導</h3>
          </div>
          <div class="col-6 text-md-end">
            <a class="btn btn-primary mb-3 me-1"
              href="#carouselExampleIndicators2" role="button"
              data-slide="prev">
              <i class="fa fa-arrow-left"></i>
            </a>
            <a class="btn btn-primary mb-3 " href="#carouselExampleIndicators2"
              role="button" data-slide="next">
              <i class="fa fa-arrow-right"></i>
            </a>
          </div>
          
          <div class="col-12">
            <div id="carouselExampleIndicators2" class="carousel slide"
              data-ride="carousel">
              <div class="carousel-inner">
                
                <div class="carousel-item">
                  <div class="row">
                    
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                        <a href="<%=request.getContextPath()%>/front-end/NewsList/didi.jsp">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1532781914607-2031eca2f00d?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=7c625ea379640da3ef2e24f20df7ce8d">
                        <div class="card-body">
                          <h4 class="card-title">DiDi趣旅行</h4>
                          <p class="card-text"> 品。旅行  參加趣旅行乙次，價值每位$350。週一、三、五、日 →走訪暨大賞櫻。 週二、四</p>
                        </div>
                        </a>
                      </div>
                    </div>
                    
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1517760444937-f6397edcbbcd?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=42b2d9ae6feb9c4ff98b9133addfb698">
                        <div class="card-body">
                          <h4 class="card-title">樂活 -暢遊山水</h4>
                          <p class="card-text">第二晚房價折扣，再送趣旅行行程，三天兩夜帶您遊玩雲品特選景點。</p>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1532763303805-529d595877c5?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=5ee4fd5d19b40f93eadb21871757eda6">
                        <div class="card-body">
                          <h4 class="card-title">三養湯屋</h4>
                          <p class="card-text">獨享半露天的35坪湯泉，竹籬笆與石燈籠交織出的日式禪風造景，提供旅人喘息沉靜的舒心</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                
                
                
                <div class="carousel-item active">
                  <div class="row">
                  
                    <div class="col-md-4 mb-3">
                      <div class="card">
                      <a href="<%=request.getContextPath()%>/front-end/NewsList/fun.jsp">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1532715088550-62f09305f765?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=ebadb044b374504ef8e81bdec4d0e840">
                        <div class="card-body">
                          <h4 class="card-title">樂活 -暢遊山水</h4>
                          <p class="card-text">第二晚房價折扣，再送趣旅行行程，三天兩夜帶您遊玩雲品特選景點。</p>
                        </div>
                         </a>
                      </div>
                    </div>
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1532771098148-525cefe10c23?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=3f317c1f7a16116dec454fbc267dd8e4">
                        <div class="card-body">
                          <h4 class="card-title">低碳假期</h4>
                          <p class="card-text">低碳做環保，邀您簽署雲品永續宣言，響應Gogreen 愛地球！與您一同守護地球</p>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1506197603052-3cc9c3a201bd?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=0754ab085804ae8a3b562548e6b4aa2e">
                        <div class="card-body">
                          <h4 class="card-title">大人放風趣</h4>
                          <p class="card-text">持著環湖巴士一日劵，搭乘南投客運遊訪日月潭週邊景點，免費使用健身房、親水主題館、雲遊...</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                
                
                
                <div class="carousel-item">
                  <div class="row">
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                      <a href="<%=request.getContextPath()%>/front-end/NewsList/promotion.jsp">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=ee8417f0ea2a50d53a12665820b54e23">
                        <div class="card-body">
                          <h4 class="card-title">早鳥優惠</h4>
                          <p class="card-text">早起的鳥兒有蟲吃，提早預訂享房價優惠。專案內容品。好眠 雲品客房住宿一晚...</p>
                        </div>
                         </a>
                      </div>
                    </div>
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1532777946373-b6783242f211?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=8ac55cf3a68785643998730839663129">
                        <div class="card-body">
                          <h4 class="card-title">夜間表演節目表</h4>
                          <p class="card-text">每日20:50-21:40 提供精彩夜間表演 。 遇雨或氣候不佳場地將移至室內空間演出...</p>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-md-4 mb-3">
                      <div class="card">
                        <img class="img-fluid" alt="100%x280"
                          src="https://images.unsplash.com/photo-1532712938310-34cb3982ef74?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=3d2e8a2039c06dd26db977fe6ac6186a">
                        <div class="card-body">
                          <h4 class="card-title">美國親子部落客旅遊記</h4>
                          <p class="card-text">溫泉酒店很榮幸能邀請到美國親子部落客The FUNemployed Family 體驗我們的住宿以...</p>
                        </div>
                      </div>
                    </div>
                    
                  </div>
                </div>
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
		<!--*******************	
		End 媒體報導
		******************* -->


	<!--*******************	
		Start Footer Area  
		******************* -->			
		<%@ include file="../front_include_page/footer.jsp"%>
	<!--*******************	
		End Footer Area  
		******************* -->	
	
	<!--*******************	
		Start Go Top Area  
		******************* -->				
		<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--*******************	
		End Go Top Area  
		******************* -->			

	<!--*******************	
		Start Include JS File  
		******************* -->	
		<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include JS File  
		******************* -->	
		
			
<%@ include file= "/front-end/framework/footer.file" %>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
			crossorigin="anonymous">
		</script>
</body>
</html>