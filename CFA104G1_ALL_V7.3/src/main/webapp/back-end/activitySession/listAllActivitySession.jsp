<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activitySession.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
ActivitySessionService activitySessionService = new ActivitySessionService();
List<ActivitySessionVO> list = activitySessionService.getAllActivitySession();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有活動場次資料-listAllActivitySession.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>
<%@ include file= "/back-end/framework/includeTableCss.file" %>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">所有活動場次資料</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activitySession/listAllActivitySession.jsp">活動場次列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activitySession/select_page.jsp">活動場次查詢</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="col-lg-12">
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


		<div class="card mb-4 a1">
			<div class="card-header">
				<i class="fas fa-table me-1"></i>所有活動場次資料
			</div>
			<div class="card-body">
				<a class="b1 btn btn-outline-secondary" href="<%= request.getContextPath() %>/back-end/activitySession/addActivitySession.jsp" role="button">新增活動場次</a>

				<table id="datatablesSimple" class="table table-bordered table-hover">
						<tr>
							<th>活動場次編號</th>
							<th>活動編號</th>
							<th>活動場次名稱</th>
							<th>報名開始日期</th>
							<th>報名截止日期</th>
							<th>活動日期起</th>
							<th>活動日期迄</th>
							<th>活動定價</th>
							<th>活動狀態</th>
							<th>狀態備註</th>
							<th>活動人數上限</th>
							<th>活動人數下限</th>
							<th>報名總數</th>
							<th>商品狀態</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
						<c:forEach var="activitySessionVO" items="${list}">
						
						<tr>
							<td>${activitySessionVO.activity_session_id}</td>
							<td>${activitySessionVO.activity_id}</td>
							<td>${activitySessionVO.activity_session_name}</td>
							<td>${activitySessionVO.entered_started}</td>
							<td>${activitySessionVO.entered_end}</td>
							<td>${activitySessionVO.activity_started}</td>
							<td>${activitySessionVO.activity_end}</td>
							<td>${activitySessionVO.activity_price}</td>
							<td>${activitySessionVO.activity_state}</td>
							<td>${activitySessionVO.status_note}</td>
							<td>${activitySessionVO.activity_max_part}</td>
							<td>${activitySessionVO.activity_min_part}</td>
							<td>${activitySessionVO.entered_total}</td>
							<td>${activitySessionVO.product_status}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/activitySession.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> 
									<input type="hidden" name="activity_session_id" value="${activitySessionVO.activity_session_id}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/activitySession.do"
									style="margin-bottom: 0px;">
									<input type="hidden" name="activity_session_id" value="${activitySessionVO.activity_session_id}">
									<input type="hidden" name="action" value="delete">
									<input type="submit" value="刪除"> 
								</FORM>
							</td>
						</tr>
					</c:forEach>
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
<%@ include file= "/back-end/framework/includeTableJs.file" %>

</body>
</html>