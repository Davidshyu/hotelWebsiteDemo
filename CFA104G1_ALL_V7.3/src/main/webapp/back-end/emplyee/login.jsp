<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emplyee.model.*"%>

<!DOCTYPE html>
<html>

  <head>
    <meta charset="UTF-8">
    <title>員工登入</title>

    <meta charset="utf-8">
    <meta name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet"
      href=" https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css ">
    <link rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">

    <!-- Plugins -->
  </head>

  <body>

    <section class="pt-5 pb-5 bg-light">
      <div class="container pt-5 pb-5">
        <div
          class="row   justify-content-center header-h100 align-items-center">
          <div class="col-12 col-md-4 text-center">
            <form class="form-signin" data-bitwarden-watching="1" METHOD="post" ACTION="<%= request.getContextPath() %>/emplyee/emp.do" name="form1">
              <img class="ml-md-3 mr-3 mr-md-0 order-1 img-fluid rounded  mb-4"
                src="<%= request.getContextPath() %>/back-end/emplyee/images/icon.png""
                alt="Generic placeholder image"  width="150" height="150">
              <h1 class="h3 mb-3 fw-normal">親愛的夥伴歡迎回來</h1>
              <div>
              
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

              </div>
              <label class="sr-only">員工編號</label>
              <input type="text" id="empno" name="empno" class="form-control mt-3"
                placeholder="員工編號" required="" autofocus="">
              <label for="inputPassword" class="sr-only">密碼</label>
              <input type="password" id="inputPassword" name="emp_password"
                class="form-control mt-3" placeholder="密碼" required="">
              <div class="checkbox mt-3 mb-3">
                <!-- <label>
                  <input type="checkbox" value="remember-me"> Remember me
                </label> -->
              </div>
              <input type="hidden" name="action" value="login">
              <button class="btn btn-lg btn-primary w-100" type="submit">登入</button>
              <p class="mt-5 mb-3 text-muted">© 2021-2022</p>
            </form>
          </div>
        </div>
      </div>
    </section>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
      crossorigin="anonymous"></script>

  </body>

</html>