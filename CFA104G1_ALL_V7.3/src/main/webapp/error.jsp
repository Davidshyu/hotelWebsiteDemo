<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3;url=<%=request.getContextPath()%>/index.jsp" />
<title>web busy</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="favicon.ico">
  <link rel="stylesheet" href=" https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css ">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">

  <!-- Plugins -->
  <style>

#a1 {
      margin-top: 10%;
    }    
    .la-line-scale,
.la-line-scale > div {
    position: relative;
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
}
.la-line-scale {
    display: block;
    font-size: 0;
    color: #fff;
}
.la-line-scale.la-dark {
    color: #333;
}
.la-line-scale > div {
    display: inline-block;
    float: none;
    background-color: currentColor;
    border: 0 solid currentColor;
}
.la-line-scale {
    width: 40px;
    height: 32px;
}
.la-line-scale > div {
    width: 4px;
    height: 32px;
    margin: 2px;
    margin-top: 0;
    margin-bottom: 0;
    border-radius: 0;
    -webkit-animation: line-scale 1.2s infinite ease;
       -moz-animation: line-scale 1.2s infinite ease;
         -o-animation: line-scale 1.2s infinite ease;
            animation: line-scale 1.2s infinite ease;
}
.la-line-scale > div:nth-child(1) {
    -webkit-animation-delay: -1.2s;
       -moz-animation-delay: -1.2s;
         -o-animation-delay: -1.2s;
            animation-delay: -1.2s;
}
.la-line-scale > div:nth-child(2) {
    -webkit-animation-delay: -1.1s;
       -moz-animation-delay: -1.1s;
         -o-animation-delay: -1.1s;
            animation-delay: -1.1s;
}
.la-line-scale > div:nth-child(3) {
    -webkit-animation-delay: -1s;
       -moz-animation-delay: -1s;
         -o-animation-delay: -1s;
            animation-delay: -1s;
}
.la-line-scale > div:nth-child(4) {
    -webkit-animation-delay: -.9s;
       -moz-animation-delay: -.9s;
         -o-animation-delay: -.9s;
            animation-delay: -.9s;
}
.la-line-scale > div:nth-child(5) {
    -webkit-animation-delay: -.8s;
       -moz-animation-delay: -.8s;
         -o-animation-delay: -.8s;
            animation-delay: -.8s;
}
.la-line-scale.la-sm {
    width: 20px;
    height: 16px;
}
.la-line-scale.la-sm > div {
    width: 2px;
    height: 16px;
    margin: 1px;
    margin-top: 0;
    margin-bottom: 0;
}
.la-line-scale.la-2x {
    width: 80px;
    height: 64px;
}
.la-line-scale.la-2x > div {
    width: 8px;
    height: 64px;
    margin: 4px;
    margin-top: 0;
    margin-bottom: 0;
}
.la-line-scale.la-3x {
    width: 120px;
    height: 96px;
}
.la-line-scale.la-3x > div {
    width: 12px;
    height: 96px;
    margin: 6px;
    margin-top: 0;
    margin-bottom: 0;
}
/*
 * Animation
 */
@-webkit-keyframes line-scale {
    0%,
    40%,
    100% {
        -webkit-transform: scaleY(.4);
                transform: scaleY(.4);
    }
    20% {
        -webkit-transform: scaleY(1);
                transform: scaleY(1);
    }
}
@-moz-keyframes line-scale {
    0%,
    40%,
    100% {
        -webkit-transform: scaleY(.4);
           -moz-transform: scaleY(.4);
                transform: scaleY(.4);
    }
    20% {
        -webkit-transform: scaleY(1);
           -moz-transform: scaleY(1);
                transform: scaleY(1);
    }
}
@-o-keyframes line-scale {
    0%,
    40%,
    100% {
        -webkit-transform: scaleY(.4);
             -o-transform: scaleY(.4);
                transform: scaleY(.4);
    }
    20% {
        -webkit-transform: scaleY(1);
             -o-transform: scaleY(1);
                transform: scaleY(1);
    }
}
@keyframes line-scale {
    0%,
    40%,
    100% {
        -webkit-transform: scaleY(.4);
           -moz-transform: scaleY(.4);
             -o-transform: scaleY(.4);
                transform: scaleY(.4);
    }
    20% {
        -webkit-transform: scaleY(1);
           -moz-transform: scaleY(1);
             -o-transform: scaleY(1);
                transform: scaleY(1);
    }
}
  </style>
</head>

<body>




  <section class="pt-5 pb-5">
    <div id="a1" class="container">
      <div class="row justify-content-center align-middle">
        <div class="col-12 col-md-8 text-center">
          <div class="row justify-content-center pb-md-1">
            <div class="col mx-auto">
            <div style="color: #64d6e2" class="la-line-scale la-3x mx-auto">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
            <div>
                <br>
            </div>
            <h1>
                網路通訊忙碌中，正在幫你努力載入中!
            </h1>
        </div>
        </div>
      </div>
    </div>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
    crossorigin="anonymous"></script>
    
  
</body>
</html>