<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.newsList.model.*"%>
<%@ page import="com.newsListImage.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>

		<!--*******************	
		Start Include CSS File  
		******************* -->
<%@ include file= "/back-end/framework/include.file" %>
		<!--*******************	
		End Include CSS File  
		******************* -->
<meta charset="UTF-8">
<title>後台管理</title>

</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
				<a href="<%=request.getContextPath()%>/back_end/newsList/NewsList_select_page.jsp">管理報導</a>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/newsList/NewsList_select_page.jsp">查詢報導</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/newsList/listAllNewsList.jsp">新聞列表</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">報導列表</h3>
						</div>

						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th>新聞編號</th>
										<th>新聞標題</th>
										<th>新聞狀態</th>
										<th>報導出處</th>
										<th>詳細內容</th>
										<th>圖片</th>
										<th>修改</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="newsListInfoVO" items="${newsListVOs}" varStatus="s">
										<tr>
											<td>${newsListInfoVO.newsId}</td>
											<td>${newsListInfoVO.title}</td>
											<td>${newsListInfoVO.status}</td>
											<td>${newsListInfoVO.newsComeFrom}</td>
											
<%-- 													var="prodcutClassVO" items="${productClassSvc.all}"> --%>
<%-- 													<c:if --%>
<%-- 														test="${productInfoVO.pcNo == prodcutClassVO.pcNo}">  --%>
<%-- 															${prodcutClassVO.pcName} --%>
<%-- 														</c:if> --%>
<%-- 												</c:forEach> --%>
<!-- 											</td> -->
<%-- 											<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${productInfoVO.piPri}"/></td> --%>
<%-- 											<td>${productInfoVO.piSta}</td> --%>
											<td>
												<A HREF="javascript:presses${s.count}()" class="btn btn-outline-secondary">查看詳情</a>
											</td>
											<td>										
												<A HREF="javascript:presses_img${s.count}()" class="btn btn-outline-secondary">查看圖片</a>
											</td>
											<td>
												<form
													action="<%=request.getContextPath()%>/newsList/NewsList.do"
													method="post">
													<input type="submit" class="btn btn-outline-secondary" value="修改">
													<input type="hidden" name="newsId" value="${newsListInfoVO.newsId}">
													<input type="hidden" name="action" value="getOneForUpdate">
												</form>
											</td>
										</tr>
										<script>
         									function presses${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/newsList/NewsList.do?newsId=${newsListInfoVO.newsId}&action=getOneForDiplay", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         									}
         									function presses_img${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/newsListImage/NewsListImage.do?newsID=${newsListInfoVO.newsId}&action=showImages", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         									}
        								</script>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
		</div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
		<!--*******************	
		End Include JS File  
		******************* -->
		
	<script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
</body>
</html>