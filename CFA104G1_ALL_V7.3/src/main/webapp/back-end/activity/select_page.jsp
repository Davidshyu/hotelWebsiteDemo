<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Activity: Home</title>
</head>
<body>
<table>
<tr><td><h3>Activity: Home</h3><h4>( MVC )</h4></td></tr>
</table>
<h3>資料查詢:</h3>
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
  <li><a href='listAllActivity.jsp'>List</a> all Activities.  <br></li>
 
  <li>
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity.do">
  <b>輸入活動編號:</b>
  <input type="text" name="activity_id">
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  
  <jsp:useBean id="activityService" scope="page" class="com.activity.model.ActivityService" />
  <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity.do">
  <b>選擇活動編號:</b>
  <select name="activity_id">
	  <c:forEach var="activityVO" items="${activityService.allActivity}" > <!-- servlet P.231 -->
	 	 <option value="${activityVO.activity_id}">${activityVO.activity_id}
	  </c:forEach>
  </select>
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  
  <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity.do">
  <b>選擇活動名稱:</b>
  <select name="activity_id">
	  <c:forEach var="activityVO" items="${activityService.allActivity}"> 
	 	 <option value="${activityVO.activity_id}">${activityVO.activity_name}
	  </c:forEach>
  </select>
  <input type="hidden" name="action" value="getOne_For_Display">
  <input type="submit" value="送出">
  </FORM>
  </li>
  </ul>
  
  <h3>活動管理</h3>
<ul>
  <li><a href='addActivity.jsp'>Add</a> a new Activity.</li>
</ul>
  
</body>
</html>