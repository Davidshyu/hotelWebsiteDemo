<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
成功

<form data-bitwarden-watching="1" METHOD="post" ACTION="<%= request.getContextPath() %>/emplyee/emp.do"
		name="form1">
		<button type=submit name="action" value="logout">登出</button>
</form>
</body>
</html>