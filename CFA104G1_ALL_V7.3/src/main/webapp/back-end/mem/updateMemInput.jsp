<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料修改</title>
<%@ include file= "/back-end/framework/include.file" %>

</head>
<body bgcolor='white'>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">會員資料修改</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/mem/listAllMem.jsp">會員管理</a></li>
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
			<div class="card-body">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do"
				name="form1">
				<table class="table table-head-fixed text-nowrap">
					<tr>
						<td>會員編號:<font color=red><b>*</b></font></td>
						<td><%=memVO.getMem_id()%></td>
					</tr>
					<tr>
						<td>會員信箱:<font color=red><b>*</b></font></td>
						<td><%=memVO.getMem_email()%></td>
					</tr>
					<tr>
						<td>會員姓名:</td>
						<td><input type="TEXT" name="mem_name" size="45"
							value="<%=memVO.getMem_name()%>" /></td>
					</tr>
					<tr>
						<td>會員電話:</td>
						<td><input type="TEXT" name="mem_phone" size="45"
							value="<%=memVO.getMem_phone()%>" /></td>
					</tr>
					<tr>
						<td>會員地址:</td>
						<td><input type="TEXT" name="mem_address" size="45"
							value="<%=memVO.getMem_address()%>" /></td>
					</tr>
					<tr>
						<td>會員狀態:</td>
						<td><select size="1" name="mem_status">
								<option value= 0
									<c:if test="${memVO.mem_status == 0}">selected</c:if>>未啟用</option>
								<option value= 1
									<c:if test="${memVO.mem_status == 1}">selected</c:if>>啟用</option>
								<option value= 2
									<c:if test="${memVO.mem_status == 2}">selected</c:if>>停權</option>
						</select></td>
					</tr>
		
				</table>
				
				<br> <input type="hidden" name="action" value="updateMemAll">
				<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
				<input type="hidden" name="mem_email" value="<%=memVO.getMem_email()%>">
				<input type="submit" value="送出修改">
				
			</FORM>
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
</body>

</html>