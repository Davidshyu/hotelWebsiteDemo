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
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
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
									<div id="err">
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
												<button id="btn1" type="button" class="btn btn-primary">重設密碼</button>
											</div>
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
	$(document).ready(function(){
		$('#btn1').click(function(){
			var obj = {"mem_email": $('#inputEmail').val()}
	console.log(obj);
			$.ajax({
				 type: "POST",
				 url: "<%= request.getContextPath() %>/MemResetPwd",
				 data: obj,
				 dataType: "json",
				 success: function (response){
					 if(response.error == null){
						 alert(response.scucess)
					 }else{
						 alert(response.error);
					 }					 
			     },
	             error: function(){alert("AJAX-class發生錯誤囉!")}
	        })
		})
	})
	
	
// 	function clearSelect2(){
// 		$('#inputEmail').empty();
// 		$('#err').append("<font color="red">請輸入Email</font>");
// 	}
	</script>
	


</body>

</html>