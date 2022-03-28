<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ActivitySession: Home</title>
<%@ include file= "/back-end/framework/include.file" %>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動場次資料查詢</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activitySession/listAllActivitySession.jsp">活動場次列表</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="col-lg-12 p-2">



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<ul>
  <li><a href='listAllActivitySession.jsp'>List</a> all Activity Session.  <br></li>

<li>
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activitySession.do">
  <b>輸入活動場次編號:</b>
  <input type="text" name="activity_session_id">
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  
    <jsp:useBean id="activitySessionService" scope="page" class="com.activitySession.model.ActivitySessionService" />
   <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activitySession.do">
  <b>選擇活動場次編號:</b>
  <select name="activity_session_id">
	  <c:forEach var="activitySessionVO" items="${activitySessionService.allActivitySession}" > <!-- servlet P.231 -->
	 	 <option value="${activitySessionVO.activity_session_id}">${activitySessionVO.activity_session_id}
	  </c:forEach>
  </select>
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  
  <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activitySession.do">
  <b>選擇活動場次名稱:</b>
  <select name="activity_session_id">
	  <c:forEach var="activitySessionVO" items="${activitySessionService.allActivitySession}"> 
	 	 <option value="${activitySessionVO.activity_session_id}">${activitySessionVO.activity_session_name}
	  </c:forEach>
  </select>
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  </ul>
  
  <h3>活動場次管理</h3>
<ul>
  <li><a href='addActivitySession.jsp'>Add</a> a new ActivitySession.</li>
</ul>
  
	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
  
</body>
</html>