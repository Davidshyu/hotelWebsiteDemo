<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>忘記密碼</title>

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
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<style>
a {
	text-decoration: none;
}
</style>
</head>

<body>

	<section class="pt-5 pb-5 bg-light">
		<div id="layoutAuthentication">
			<div id="layoutAuthentication_content">
				<main>
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-5">
								<div class="card shadow-lg border-0 rounded-lg mt-5">
									<div class="card-header">
										<h3 class="text-center font-weight-light my-4">恢復您的密碼</h3>
									</div>
									<div>
										<%-- 錯誤表列 --%>
										<c:if test="${not empty errorMsgs}">
											<font style="color: red">請修正以下錯誤:</font>
											<ul>
												<c:forEach var="message" items="${errorMsgs}">
													<li style="color: red">${message}</li>
												</c:forEach>
											</ul>
										</c:if>
									</div>
									<div class="card-body">
										<div class="small mb-3 text-muted">輸入您的電子信箱地址，我們將協助您重設您的密碼!</div>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/mem/mem.do"
											name="form1">
											<div class="form-floating mb-3">
												<input class="form-control" id="inputEmail" type="email"
													placeholder="name@example.com" name="mem_email" /> <label
													for="inputEmail">電子信箱地址</label>
											</div>
											<div
												class="d-flex align-items-center justify-content-between mt-4 mb-0">
												<a class="small"
													href="<%=request.getContextPath()%>/front-end/mem/login.jsp">回到登入</a>
												<input type="hidden" name="action" value="resetpwd">
												<button id="btn1" type="submit" class="btn btn-primary">重設密碼</button>
											</div>
										</FORM>
									</div>
									<div class="card-footer text-center py-3">
										<div class="small">
											<a
												href="<%=request.getContextPath()%>/front-end/mem/signup.jsp">還沒有會員?現在就加入吧!</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</main>
			</div>
			<div id="layoutAuthentication_footer">
				<footer class="py-4 bg-light mt-auto"> </footer>
			</div>
		</div>
	</section>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
		crossorigin="anonymous"></script>
	<script>
// 	$(document).ready(function(){
// 		$(#btn1).click(function(){
// 			aler("已發送密碼");
// 		})
// 	}
	</script>


</body>

</html>