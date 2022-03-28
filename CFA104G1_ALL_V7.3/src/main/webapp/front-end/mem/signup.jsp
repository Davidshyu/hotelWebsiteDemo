<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html>

  <head>
    <meta charset="UTF-8">
    <title>會員註冊</title>
<%@ include file= "/front-end/framework/include.file" %>

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

    <style>
      span {
        color: red;
      }

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
        <a class="ms-auto me-auto" href="#">
          <img src="<%= request.getContextPath() %>/front-end/mem/images/logo.png"
            height="100 px" alt="image">
        </a>
      </div>
    </nav>

 	-->

    <section class="pt-5 pb-5">
      <div class="container">
        <div class="row justify-content-center equal">
          <div class="col-12 col-md-5  ">
            <div class="card shadow h-100">
              <article class="card-body">
                <h2 class="card-title fw-bold   mb-4 mt-1">加入會員</h2>
                
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

                <c:if test="${not empty errorMsgs.Exception}">
                <p class="text-danger">${errorMsgs.Exception}</p>
               </c:if>
                <form data-bitwarden-watching="1" METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1">
                  <div class="form-group mt-2 must">
                    <label>您的電子郵件<span>*</span></label><p class="text-danger">${errorMsgs.mem_email}</p>
                    <input name="mem_email" class="form-control mt-2" placeholder="電子郵件"
                      type="email" value="<%= (memVO==null)? "" : memVO.getMem_email()%>" />
                  </div>
                  <div class="form-group mt-2 must">
                    <label>您的密碼<span>*</span></label><p class="text-danger">${errorMsgs.mem_password}</p>
                    <input name="mem_password" class="form-control mt-2" placeholder="******"
                      type="password">
                  </div>
                  <div class="form-group mt-2">
                    <label>重複一次密碼<span>*</span></label><p class="text-danger">${errorMsgs.remem_password}</p>
                    <input name="remem_password" class="form-control mt-2" placeholder="******"
                      type="password">
                  </div>
                  <div class="form-group must">
                    <label>您的姓名<span>*</span></label><p class="text-danger">${errorMsgs.mem_name}</p>
                    <input name="mem_name" class="form-control mt-2" placeholder="姓名"
                      type="text" value="<%= (memVO==null)? "" : memVO.getMem_name()%>">
                  </div>
                  <div class="form-group must">
                    <label>您的手機號碼<span>*</span></label><p class="text-danger">${errorMsgs.mem_phone}</p>
                    <input name="mem_phone" class="form-control mt-2" placeholder="手機號碼"
                      type="text" value="<%= (memVO==null)? "" : memVO.getMem_phone()%>">
                  </div>
                  <div class="form-group">
                    <label>您的地址</label>
                    <div role="tw-city-selector"></div>
                    <input name="mem_address" class="form-control mt-2" placeholder="地址"
                      type="text" value="<%= (memVO==null)? "" : memVO.getMem_address()%>">
                  </div>

                  <div class="form-group mt-2">
                    <!-- <div class="checkbox">
                      <label>
                        <input type="checkbox" class="  me-2">Subscribe to
                        updates</label>
                    </div> -->
                  </div>
                  <div class="form-group mt-3">
                    <input type="hidden" name="action" value="signup">
                    <button type="submit" class="btn btn-primary w-100">送出</button>
                  </div>
                  <a id="reg" class="repwd" href="<%= request.getContextPath() %>/front-end/mem/login.jsp" style="margin-right: 200px;">已經有會員?現在就登入!</a>
                </form>
                
              </article>
            </div>
          </div>
        </div>
      </div>
    </section>

<%@ include file= "/front-end/framework/footer.file" %>

       
  </body>

</html>