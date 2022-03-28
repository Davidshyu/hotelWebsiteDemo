<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>


<%
MemVO memVO = (MemVO) session.getAttribute("user");
MemVO memVOreq = (MemVO) request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>修改會員資料</title>

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
							<form data-bitwarden-watching="1" METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do">
								<div class="form-group mt-2 must">
									<label>電子郵件</label>
									<div><%=memVO.getMem_email()%></div>
								</div>
								<div class="form-group must">
									<label>您的姓名<span class="s1">*</span></label> <p class="text-danger">${errorMsgs.mem_name}</p>
									<input name="mem_name"
										class="form-control mt-2" placeholder="姓名" type="text"
										value="<%=(memVOreq==null)? memVO.getMem_name() : memVOreq.getMem_name()%>">
								</div>
								<div class="form-group must">
									<label>您的手機號碼<span class="s1">*</span></label> <p class="text-danger">${errorMsgs.mem_phone}</p>
									<input name="mem_phone"
										class="form-control mt-2" placeholder="手機號碼" type="text"
										value="<%=(memVOreq==null)? memVO.getMem_phone() : memVOreq.getMem_phone()%>">
								</div>
								<div class="form-group">
									<label>您的地址</label> <input name="mem_address" class="form-control mt-2"
										placeholder="地址" type="text"
										value="<%=(memVOreq==null)? memVO.getMem_address() : memVOreq.getMem_address()%>">
								</div>

								<div class="form-group mt-2">
									<!-- <div class="checkbox">
                      <label>
                        <input type="checkbox" class="  me-2">Subscribe to
                        updates</label>
                    </div> -->
								</div>
								<div class="form-group mt-3">
									<input type="hidden" name="action" value="update"> 
									<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
									<button type="submit" class="btn btn-primary w-100">送出</button>
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