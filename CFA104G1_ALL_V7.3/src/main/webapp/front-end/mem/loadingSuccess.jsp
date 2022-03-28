<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>變更密碼成功</title>

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

    .la-ball-beat,
        .la-ball-beat>div {
            position: relative;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        .la-ball-beat {
            display: block;
            font-size: 0;
            color: #fff;
        }

        .la-ball-beat.la-dark {
            color: #333;
        }

        .la-ball-beat>div {
            display: inline-block;
            float: none;
            background-color: currentColor;
            border: 0 solid currentColor;
        }

        .la-ball-beat {
            width: 54px;
            height: 18px;
        }

        .la-ball-beat>div {
            width: 10px;
            height: 10px;
            margin: 4px;
            border-radius: 100%;
            -webkit-animation: ball-beat .7s -.15s infinite linear;
            -moz-animation: ball-beat .7s -.15s infinite linear;
            -o-animation: ball-beat .7s -.15s infinite linear;
            animation: ball-beat .7s -.15s infinite linear;
        }

        .la-ball-beat>div:nth-child(2n-1) {
            -webkit-animation-delay: -.5s;
            -moz-animation-delay: -.5s;
            -o-animation-delay: -.5s;
            animation-delay: -.5s;
        }

        .la-ball-beat.la-sm {
            width: 26px;
            height: 8px;
        }

        .la-ball-beat.la-sm>div {
            width: 4px;
            height: 4px;
            margin: 2px;
        }

        .la-ball-beat.la-2x {
            width: 108px;
            height: 36px;
        }

        .la-ball-beat.la-2x>div {
            width: 20px;
            height: 20px;
            margin: 8px;
        }

        .la-ball-beat.la-3x {
            width: 200px;
            height: 54px;
        }

        .la-ball-beat.la-3x>div {
            width: 30px;
            height: 30px;
            margin: 12px;
        }

        /*
 * Animation
 */
        @-webkit-keyframes ball-beat {
            50% {
                opacity: .2;
                -webkit-transform: scale(.75);
                transform: scale(.75);
            }

            100% {
                opacity: 1;
                -webkit-transform: scale(1);
                transform: scale(1);
            }
        }

        @-moz-keyframes ball-beat {
            50% {
                opacity: .2;
                -moz-transform: scale(.75);
                transform: scale(.75);
            }

            100% {
                opacity: 1;
                -moz-transform: scale(1);
                transform: scale(1);
            }
        }

        @-o-keyframes ball-beat {
            50% {
                opacity: .2;
                -o-transform: scale(.75);
                transform: scale(.75);
            }

            100% {
                opacity: 1;
                -o-transform: scale(1);
                transform: scale(1);
            }
        }

        @keyframes ball-beat {
            50% {
                opacity: .2;
                -webkit-transform: scale(.75);
                -moz-transform: scale(.75);
                -o-transform: scale(.75);
                transform: scale(.75);
            }

            100% {
                opacity: 1;
                -webkit-transform: scale(1);
                -moz-transform: scale(1);
                -o-transform: scale(1);
                transform: scale(1);
            }
        }
  </style>
</head>

<body>




  <section class="pt-5 pb-5">
    <div id="a1" class="container">
      <div class="row justify-content-center align-middle">
        <div class="col-12 col-md-8 text-center">
          <div class="row justify-content-center pb-md-5">
            <div style="color: #6666FF" class="la-ball-beat la-3x">
              <div></div>
              <div></div>
              <div></div>
            </div>
          </div>
          <p class="fs-2">您已經成功啟用您的帳戶!</p>
        </div>
      </div>
    </div>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
    crossorigin="anonymous"></script>


</body>

</html>