<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emplyee.model.*"%>
<%
if (session != null)
{
	session.setAttribute("username", null);
}
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>彌生酒店管理後台</title>
	<link rel="stylesheet"
    href=" https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css ">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            background-image: url('<%=request.getContextPath()%>/resources/img/slider/header-slider-3.jpg');
            font-family: arial,"Microsoft JhengHei","微軟正黑體",sans-serif !important;
        }

        img {
            max-width: 100%;
        }

		div.content {
			margin-left: auto;
			margin-right: auto;
			width: 80%;
			/* border: 1px solid blue; */
			min-width: 760px;
			position: relative;
            top: 100px;
            text-align: center;
		}
		
		@media screen and (max-width: 767.98px) {
			div.content {
				width: 90%;
				min-width: 90%;
			}
		}
		
		div.content > h1{
			text-align: center;
			white-space: nowrap;
		}
		@media screen and (max-width: 767.98px) {
			div.content > h1{
				word-wrap: normal;
				white-space: normal;
			}
		}
		table.loginblock{
			margin-left: auto;
  			margin-right: auto;
  			border: 3px solid #C99A5B;
  			background-color: #f2f2f2;
  			
		}
		td.logo{
			/*border-color: #0F0;*/
			min-width: 200px;
			background-color:#C99A5B;
			text-align: center;
		}
		@media screen and (max-width: 767.98px) {
			td.logo{
				display: none;
			}
		}
		
		td.login{
			padding: 30px;
			min-width: 100%;
		}
		@media screen and (max-width: 767.98px) {
			td.login{
				min-width: 100%;
			}
		}
		
		table.tabl{
			margin-left:auto; 
			margin-right:auto;
			max-width: 100%;
			padding: 10px;
			/*border:3px #FFD382 dashed;*/
			display:inline-block
		}
		label{
			display:inline-block;
			width: 150px;
		}
	</style>
</head>
<body>
<div class="content">
	
	<table class="loginblock">
		<tr >
		<td colspan=2 style="background-color: #ffffff;"><img src="<%=request.getContextPath()%>/resources/img/logo.png" alt="logo"></td>
		</tr>
		<tr>
			<td class="logo">
			彌生酒店<br>管理後台
			</td>
			<td class="login">
				<form METHOD="post" ACTION="<%= request.getContextPath() %>/emplyee/emp.do">
					<table class="tabl">
					<c:if test="${not empty errorMsgs.emp_login}">
					<tr>
						<td>
							<p class="text-danger">${errorMsgs.emp_login}</p>
						</td>
					</tr>                	
               		</c:if>
               		
               		<c:if test="${not empty errorMsgs.emp_status}">
               		<tr>
						<td>
							<p class="text-danger">${errorMsgs.emp_status}</p>
						</td>
					</tr>     
               		</c:if>
               		
               		<c:if test="${not empty errorMsgs.Exception}">
               		<tr>
						<td>
							<p class="text-danger">${errorMsgs.Exception}</p>
						</td>
					</tr>     
               		</c:if>
               		
               		<c:if test="${not empty errorMsgs.empno}">
               		<tr>
						<td>
							<p class="text-danger">${errorMsgs.empno}</p>
						</td>
					</tr>     
               		</c:if>
               		
               		<c:if test="${not empty errorMsgs.emp_password}">
               		<tr>
						<td>
							<p class="text-danger">${errorMsgs.emp_password}</p>
						</td>
					</tr>     
               		</c:if>
               		
					<tr>
						<td>
							<label for="empno">帳號</label>
							<input type="text" id="empno" name="empno"  placeholder="員工編號" required="">
						</td>
					</tr>
					<tr>
						<td>
							<label>密碼</label>
							<input type="password" name="emp_password"  placeholder="密碼" required="">
						</td>
					</tr>
					<tr>
						<td><br>
							<input type="hidden" name="action" value="login">
							<input type="submit" value="登入">
						</td>
					</tr>
					</table>
				</form>
			</td>
		</tr>	
	</table>
</div>	
</body>
</html>