<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>


<%
MemVO memVO = (MemVO) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>

  <head>
    <meta charset="UTF-8">
    <title>變更密碼</title>

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
      .s1 {
        color: red;
      }
    </style>
    
    <%@ include file= "/front-end/memFramework/include.file" %>
  </head>

  <body>

<%@ include file= "/front-end/memFramework/header.file" %>

    <nav class="navbar">
      <div class="container text-center">
        
      </div>
    </nav>


    <section class="pt-5 pb-5">
      <div class="container">
        <div class="row justify-content-center equal">
          <div class="col-12 col-md-5  ">
            <div class="card shadow h-100">
              <article class="card-body">
             
                
                
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
                    <label>您的舊密碼<span class="s1">*</span></label><p class="text-danger">${errorMsgs.mem_password}</p>
                    <input class="form-control mt-2" placeholder="******"
                      type="password" name="mem_password">
                  </div>
                  <div class="form-group mt-2 must">
                    <label>您的新密碼<span class="s1">*</span></label><p class="text-danger">${errorMsgs.newmem_password}</p>
                    <input class="form-control mt-2" placeholder="******"
                      type="password" name="mem_password_new">
                  </div>
                  <div class="form-group mt-2">
                    <label>重複一次密碼<span class="s1">*</span></label><p class="text-danger">${errorMsgs.remem_password}</p>
                    <input class="form-control mt-2" placeholder="******"
                      type="password" name="remem_password_new">
                  </div>

                  <div class="form-group mt-2">
                    <!-- <div class="checkbox">
                      <label>
                        <input type="checkbox" class="  me-2">Subscribe to
                        updates</label>
                    </div> -->
                  </div>
                  <div class="form-group mt-3">
                    <input type="hidden" name="action" value="alterpwd">
                    <input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
                    <button id="btn1" type="submit" class="btn btn-primary w-100">送出</button>
                  </div>
                </form>
              </article>
            </div>
          </div>
        </div>
      </div>
    </section>
<%@ include file= "/front-end/memFramework/footer.file" %>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
      crossorigin="anonymous"></script>
   


  </body>

</html>