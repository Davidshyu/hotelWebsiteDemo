<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hotnews.model.*"%>

<%
  HotNewsVO HotNewsVO = (HotNewsVO) request.getAttribute("HotNewsVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>最新消息相片更新</title>
<%@ include file= "/back-end/framework/include.file" %>

</head>
<body bgcolor='white'>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">最新消息圖片更新</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/hotnews/listAllHotNews.jsp">最新消息列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/hotnews/select_page.jsp">最新消息查詢</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
			<div class="card">
				<div class="card-body">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
				<c:if test="${not empty okMsgs}">
					<ul>
						<c:forEach var="message" items="${okMsgs}">
							<li style="color:blue">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotnews/HotNews.do" name="form1" enctype="multipart/form-data">
				<table class="table">
					<tr>
						<td>最新消息標題:</td>
						<td><%= (HotNewsVO==null)? "沒有最新消息標題" : HotNewsVO.getHot_news_title()%></td>
					</tr>
					<tr>
						<td>最新消息圖片:</td>
						<td>
						<img src="<%=request.getContextPath()%>/hotnews/DBGifReader4?HotNewsId=${HotNewsVO.hot_news_id}" width="120" border="0">
					</tr>
					
					<tr>
						<td>指定更新圖片:</td>
						<td>
						<div class="row">
						<div class="p-1">
						<img src="<%=request.getContextPath()%>/back-end/hotnews/images/pr.png" width="100" border="0" id="showimg">
						</div>
						<div class="p-1"><input type="file" name="hot_news_photo" id="flie" onchange="show(this)"></td>
						</div>
					</tr>
				</table>
				</div>
				<div class="card-footer">
					<input type="hidden" name="action" value="updatephoto">
					<input type="hidden" name="hot_news_id" value="<%=HotNewsVO.getHot_news_id()%>">
					<input type="hidden" name="hot_news_title" value="<%=HotNewsVO.getHot_news_title()%>">
					<input class="btn btn-outline-success" type="submit" value="送出更新">
					<a class="btn btn-outline-secondary" 
					   href="<%=request.getContextPath()%>/back-end/hotnews/listAllHotNews.jsp">放棄更新</a>					
				</div>
		   </FORM>
   </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>

<script>
        
function show(thisimg) {
	var file = thisimg.files[0];
	console.log("show image");
	if(window.FileReader) {
		var fr = new FileReader();
		
		var showimg = document.getElementById('showimg');
		fr.onloadend = function(e) {
		showimg.src = e.target.result;
	};
	fr.readAsDataURL(file);
	showimg.style.display = 'block';
	}
}
      
</script>

</body>

</html>