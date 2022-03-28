<%@page import="com.qalist.model.QAListVO"%>
<%@page import="com.qalist.model.QAListService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
QAListService qaListService = new QAListService();
List<QAListVO> list = qaListService.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>QA管理</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet" />
<%@ include file= "/back-end/framework/include.file" %>
	
<style>
button, input {
	background-color: rgb(167, 243, 227);
	border-radius: 20em;
}

#big {
	width: 80%;
	padding: auto;
}
</style>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">QA管理</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">QA管理</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
			<div class="card mb-4">
					<div class="card-header">
						<input type="Submit"  value="新增QA" onclick="location.href='<%=request.getContextPath()%>/back-end/qaList/addQA.jsp'" />
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>問答編號</th>
									<th>顯示順序</th>
									<th>問題</th>
									<th>答覆</th>
									<th>狀態</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="qaListVO" items="${list}">
									<tr>
										<td>${qaListVO.qa_id}</td>
										<td>${qaListVO.no}</td>
										<td>${qaListVO.question}</td>
										<td>${qaListVO.answer}</td>
										<c:set var="status" scope="session" value="${qaListVO.status}"/>
										<td><c:choose>
											    <c:when test="${status == true}">
											       已上架
											    </c:when>
											    <c:when test="${status == false}">
											       未上架
											    </c:when>
											</c:choose> 
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/qaList/qaList.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="qa_id" value="${qaListVO.qa_id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/qaList/qaList.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> <input
													type="hidden" name="qa_id" value="${qaListVO.qa_id}">
												<input type="hidden" name="action" value="delete">
											</FORM>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
		</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script>
        window.addEventListener('DOMContentLoaded', event => {
            // Simple-DataTables
            // https://github.com/fiduswriter/Simple-DataTables/wiki

            const datatablesSimple = document.getElementById('datatablesSimple');
            if (datatablesSimple) {
                new simpleDatatables.DataTable(datatablesSimple);
            }
        });
    </script>
</body>
</html>