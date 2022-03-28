<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.hotnews.model.*"%>

<%
HotNewsService hotNewsService = new HotNewsService();
List<HotNewsVO> list = hotNewsService.getAllOn();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Yayoi Hot spring resort | 彌生溫泉度假酒店</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
<%@ include file= "/front-end/framework/include.file" %>

<style>
.img-col img {
    border-radius: 30px;
    transition: 0.3s;
    width: 100%;
}
</style>
</head>

<body>
 <!-- Header Section Start -->
<!-- Header Section Start -->
<%@ include file= "/front-end/framework/header.file" %>
<div id="news">
<!-- Content Section Start -->
    <div class="container">
        <div class="section-header">
            <h2>最新消息</h2>
        </div>
        <c:forEach var="hotNewsVO" items="${list}" varStatus="aaa">
        <c:if test="${aaa.index%2 == 0}">
	        <div class="row">
	            <div class="col-md-6 img-cols">
	                <div class="img-col">
	                    <img class="img-fluid" src="<%=request.getContextPath() %>/hotnews/DBGifReader4?HotNewsId=${hotNewsVO.hot_news_id}">
	                </div>
	            </div>
	            <div class="col-md-6 content-cols">
	                <div class="content-col">
	                    <h3>${hotNewsVO.hot_news_title}</h3>
	                    <p>${hotNewsVO.hot_news_description}</p>
	                    
	                </div>
	            </div>
	        </div>
        </c:if>
        <c:if test="${aaa.index%2 == 1}">    
        
            <div class="row">
	            <div class="col-md-6 img-cols d-block d-md-none">
	                <div class="img-col">
	                    <img class="img-fluid" src="<%=request.getContextPath() %>/hotnews/DBGifReader4?HotNewsId=${hotNewsVO.hot_news_id}">
	                </div>
	            </div>
	            <div class="col-md-6 content-cols">
	                <div class="content-col">
	                    <h3>${hotNewsVO.hot_news_title}</h3>
	                    <p>${hotNewsVO.hot_news_description}</p>
	                   
	                </div>
	            </div>
	            <div class="col-md-6 img-cols d-none d-md-block">
	                <div class="img-col">
	                    <img class="img-fluid" src="<%=request.getContextPath() %>/hotnews/DBGifReader4?HotNewsId=${hotNewsVO.hot_news_id}">
	                </div>
	            </div>
	        </div>
        
        </c:if>
        </c:forEach>
        
        
    </div>
</div>

<!-- Content Section End -->
	<br \>
<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>
</body>
</html>
