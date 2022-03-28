<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
    String email = "";
    String pwd = "";
    Cookie[] cookiesList = request.getCookies();
    if(cookiesList != null){
    	for(Cookie cookie:cookiesList){
    		if ("mail".equals(cookie.getName())){
    			email = cookie.getValue();    			
    		}else if ("pwd".equals(cookie.getName())){
    			pwd = cookie.getValue();
    		}
    	}
    }
%>

<!DOCTYPE html>
<html>

  <head>
    <meta charset="UTF-8">
    <title>會員登入</title>

    <meta charset="utf-8">
    <meta name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
<%@ include file= "/front-end/framework/include.file" %>
<!-- 
    <link rel="stylesheet"
      href=" https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css ">
    <link rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
-->
    <!-- Plugins -->

    <style>
      a {
            text-decoration:none;
        }
    </style>
  </head>

  <body>
<%@ include file= "/front-end/framework/header.file" %>
	<!-- 
    <nav class="navbar">
      <div class="container text-center">
        <a class="ms-auto me-auto" href="<%= request.getContextPath() %>/fornt-end/mem/login">
          <img src="<%= request.getContextPath() %>/front-end/mem/images/logo.png"
            height="70 px" alt="image">
        </a>
      </div>
    </nav>

 	-->
    <section class="pt-5 pb-5 bg-light" >
      <div class="container pt-5 pb-5">
        <div
          class="row   justify-content-center header-h100 align-items-center">
          <div class="col-12 col-md-4 text-center">
            <form class="form-signin" data-bitwarden-watching="1" METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1">
              
              <h1 class="h3 mb-3 fw-normal">親愛的房客歡迎回來!</h1>
              
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

              <c:if test="${not empty errorMsgs.mem_login}">
                <p class="text-danger">${errorMsgs.mem_login}</p>
               </c:if>
               <c:if test="${not empty errorMsgs.mem_status}">
                <p class="text-danger">${errorMsgs.mem_status}</p>
               </c:if>
              <label for="inputEmail" class="sr-only">電子信箱地址</label>
              <input name="mem_email" type="email" id="inputEmail" class="form-control mt-3"
                placeholder="電子信箱地址" required="" autofocus="" value="<%=email%>">
                <c:if test="${errorMsgs.mem_email} != null">
                <p class="text-danger">${errorMsgs.mem_email}</p>
                </c:if>
              <label for="inputPassword" class="sr-only">密碼</label>
              <input name="mem_password" type="password" id="inputPassword"
                class="form-control mt-3" placeholder="密碼" required="" value="<%=pwd%>">
                 <c:if test="${errorMsgs.mem_password} != null">
                <p class="text-danger">${errorMsgs.mem_password}</p>
                </c:if>
              <div class="checkbox mt-3 mb-3">
                <label>
                  <input type="checkbox" name="save"> 記住我
                </label>
                <a id="forgetpwd" class="forgetpwd" href="<%= request.getContextPath() %>/front-end/mem/memResetPwd.jsp" style="margin-left: 200px;">忘記密碼?</a>
              </div>
              <input type="hidden" name="action" value="login">
              <button class="btn btn-lg btn-primary w-100" type="submit">登入</button>
              <a id="reg" class="repwd" href="<%= request.getContextPath() %>/front-end/mem/signup.jsp" style="margin-right: 150px;">還不是會員?加入會員</a>
            </form>
          </div>
        </div>
      </div>
    </section>
<%@ include file= "/front-end/framework/footer.file" %>
<!-- 
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
      crossorigin="anonymous"></script>
 -->

  </body>

</html>