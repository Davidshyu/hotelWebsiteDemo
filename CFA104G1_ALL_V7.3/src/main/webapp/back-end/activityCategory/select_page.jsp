<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動類別查詢</title>
<%@ include file= "/back-end/framework/include.file" %>

</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">資料查詢</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityCategory/listAllActivityCategory.jsp">活動類別列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityCategory/select_page.jsp">活動類別查詢</a></li>
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
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllActivityCategory.jsp'>List</a> all ActivityCategories.  <br><br></li>
 
  <li>
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activityCategory.do">
  <b>輸入活動類別編號:</b>
  <input type="text" name="activity_category_id">
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  <jsp:useBean id="activityCategoryService" scope="page" class="com.activityCategory.model.ActivityCategoryService" />
  <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activityCategory.do">
  <b>選擇活動類別編號:</b>
  <select name="activity_category_id">
	  <c:forEach var="activityCategoryVO" items="${activityCategoryService.allActivityCategory}" > <!-- servlet P.231 -->
	 	 <option value="${activityCategoryVO.activity_category_id}">${activityCategoryVO.activity_category_id}
	  </c:forEach>
  </select>
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  
  <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activityCategory.do">
  <b>選擇活動類別名稱:</b>
  <select name="activity_category_id">
	  <c:forEach var="activityCategoryVO" items="${activityCategoryService.allActivityCategory}"> 
	 	 <option value="${activityCategoryVO.activity_category_id}">${activityCategoryVO.activity_category_name}
	  </c:forEach>
  </select>
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  </ul>
  
  <h3>活動類別管理</h3>
<ul>
  <li><a href='addActivityCategory.jsp'>Add</a> a new Activity Category.</li>
</ul>
		</div>
	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
  
</body>
</html>