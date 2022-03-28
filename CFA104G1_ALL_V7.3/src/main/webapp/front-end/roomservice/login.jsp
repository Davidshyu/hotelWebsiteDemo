<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>客房服務登入</title>
<%@ include file= "/front-end/framework/include.file" %>
</head>
<body>
<%@ include file= "/front-end/framework/header.file" %>
<div class="container">
     <div class="section-header">
         <h2>客房服務登入</h2>
     </div>
     <div class="row" style="min-height: 45vh;">
        <div class="container">
          <div class="d-flex justify-content-center mb-md-0 fs-1 p-3">
			<form name="loginForm" action="<%=request.getContextPath()%>/Shopping.do" method="POST">
				請輸入房號:<input name="roomid" type="text" value="">
				<input type="submit"  value="登入">
				<input type="hidden" name="action" value="login">
			</form>
		</div>
		</div>
	</div>
</div>     
     
	
<%@ include file= "/front-end/framework/footer.file" %>
	
</body>
</html>