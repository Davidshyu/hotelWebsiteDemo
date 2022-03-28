<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>變更大頭貼</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->



<!-- Customized Bootstrap Stylesheet -->


<!-- Template Stylesheet -->
<style>
#d1{
margin-left:30%;
}
</style>


<%@ include file= "/front-end/memFramework/include.file" %>

</head>

<body>

<%@ include file= "/front-end/memFramework/header.file" %>

	<div id="d1" class="container-xxl position-relative bg-white d-flex p-0" style="width:400px;height:500px;">  
            <!-- Form Start -->
            <div class="container-fluid pt-4 px-4">
                <div  class="row g-4" style="width:400px;height:450px">
                        <div  class="bg-light rounded h-100 p-4" style="width:380px;height:430px;">
                            <h5 class="mb-4">大頭貼上傳</h5>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
                            <div style="width:150px;height:150px;">
                                <img id="avator" src="<%=request.getContextPath()%>/mem/mempic.do?mem_id=<%=memVO.getMem_id()%>" width="150" height="150" alt="">
                            </div>
                            <div>
                                <label for="formFileLg" class="form-label"></label>
                                <FORM ACTION="<%= request.getContextPath() %>/mem/mem.do" method=post enctype="multipart/form-data">
                                <input class="form-control form-control-lg" id="formFileLg" name="mem_pic" type="file">
                                <br>
                                <input type="hidden" name="action" value="updateAvtor">
                                <input type="hidden" name="mem_email" value="<%=memVO.getMem_email()%>">
                                <input class="form-control form-control-lg" id="formFileLg" type="submit" value="上傳大頭貼">
                                </FORM>
                            </div>
                        </div>
                </div>
            </div>
		<!-- Form End -->
		<!-- Content End -->
	</div>
	
<%@ include file= "/front-end/memFramework/footer.file" %>

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	

	<!-- Template Javascript -->
	
	<script>
    let input = document.querySelector('#formFileLg');
    let img = document.querySelector('#avator');
    input.onchange = () => {
        const url = URL.createObjectURL(input.files[0]);
        img.src = url
    }
    </script>
</body>

</html>