<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>交通資訊</title>
<%@ include file= "/front-end/framework/include.file" %>
  <style>
    #contatti{
      letter-spacing: 0px;
      }
    #contatti a{
      color: #fff;
      text-decoration: none;
    }
    
    
    @media (max-width: 575.98px) {
    
      #contatti{padding-bottom: 800px;}
      #contatti .maps iframe{
        width: 100%;
        height: 450px;
      }
     }
    
    
    @media (min-width: 576px) {
    
       #contatti{padding-bottom: 800px;}
    
       #contatti .maps iframe{
         width: 100%;
         height: 450px;
       }
     }
    
    @media (min-width: 768px) {
    
      #contatti{padding-bottom: 350px;}
    
      #contatti .maps iframe{
        width: 100%;
        height: 850px;
      }
    }
    
    @media (min-width: 992px) {
      #contatti{padding-bottom: 200px;}
    
       #contatti .maps iframe{
         width: 100%;
         height: 700px;
       }
    }
    
    
    #author a{
      color: #fff;
      text-decoration: none;
        
    }
  </style>  

</head>
<body>
<!-- Header Section Start -->
<%@ include file= "/front-end/framework/header.file" %>

<!-- Content Section Start -->
<div class="container">
     <div class="section-header">
         <h2>交通資訊</h2>
     </div>

<div class="row" id="contatti">
  <div class="container mt-5" >

    <div class="row" style="height:550px;">
      <div class="col-md-6 maps" >
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6136.0270505802555!2d120.92115654580246!3d23.871747063818695!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3468d66bcbaf5f5f%3A0xb2978a42c211e576!2z6Zuy5ZOB5rqr5rOJ6YWS5bqX5pel5pyI5r2tIEZsZXVyIGRlIENoaW5lIEhvdGVs!5e0!3m2!1szh-TW!2stw!4v1645691233822!5m2!1szh-TW!2stw" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
      </div>
      <div class="col-md-6">
        
        
          <div class="row">
            <div class="col-lg-12">
              <div>
                <h4>台鐵</h4>
                <p>
                  抵達台中車站後，請至車站前方的「南投客運」或「仁友客運」搭乘前往日月潭班車
                </p>
              </div>
            </div>
            <div class="col-lg-12">
              <div>
                <h4>高鐵</h4>
                <p>
                  抵達高鐵烏日站後，請至出口5的「南投客運高鐵快捷國道6號直達車」搭乘前往日月潭班車
                </p>
              </div>
            </div>
            <div class="col-lg-12">
              <div>
                <h4>客運</h4>
                <ol style="margin-left: -20px;line-height: 30px;">
                  <li>由台北出發：於「國光客運」台北西站B棟搭乘前往日月潭班車，可直達日月潭；至車站前方的「南投客運」或「仁友客運」搭乘前往日月潭班車</li>
                  <li>由高雄出發：搭乘「總達客運」前往埔里班車，再轉乘「南投客運」日月潭班車；至車站前方的「南投客運」或「仁友客運」搭乘前往日月潭班車</li>
              </ol>
              </div>
            </div>
            <div class="col-lg-12">
              <div>
                <h4>自行開車</h4>
                <ol style="margin-left: -20px;line-height: 30px;">
                  <li>【由國六進入】由國道三號南下，至(214公里處)霧峰系統交流道往 草屯/埔里 方向接上國道六號，再於愛蘭交流道往 埔里/魚池 方向下來左轉接台14線。行約2公里，於埔里愛蘭橋前右轉接台21線，約500公尺抵達彌生酒店。</li>
                  <li>【由國六進入】由國道三號北上，至(214公里處)霧峰系統交流道往 草屯/埔里 方向接上國道六號，再於愛蘭交流道往 埔里/魚池 方向下來左轉接台14線。行約2公里，於埔里愛蘭橋前右轉接台21線，約500公尺抵達彌生酒店。</li>
              </ol>
              </div>
            </div>
            
          </div>
    </div>
  </div>
</div>

</div>

<!-- Content Section End -->

<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>

</body>
</html>