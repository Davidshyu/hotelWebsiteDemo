<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emplyee.model.*"%>
<%
  EmplyeeVO emplyeeVO = (EmplyeeVO) request.getAttribute("emplyeeVO");
%>
<!DOCTYPE html>
<html>

  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>新增員工</title>
<%@ include file= "/back-end/framework/include.file" %>

    <!-- Plugins -->

    <style>
      span {
        color: red;
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
            <h1 class="m-0">員工管理</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/emplyee/listAllEmp.jsp">員工列表</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
            <div class="card shadow h-100">
              <div class="card-header">
                <h2 class="card-title">新增員工</h2>
              </div>
              <article class="card-body">
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
                
                <FORM data-bitwarden-watching="1" METHOD="post" ACTION="<%=request.getContextPath()%>/emplyee/emp.do" name="form1">
                  <div class="form-group mt-2 must">
                    <label>員工編號<span>*</span></label>
                    <input name="empno" class="form-control mt-2" type="text" value="<%= (emplyeeVO==null)? "7999" : emplyeeVO.getEmpno()%>">
                  </div>
                  
                  <div class="form-group mt-2 must">
                    <label>員工角色<span>*</span></label>
                    
                    <jsp:useBean id="rolesSvc" scope="page" class="com.roles.model.RolesService" />

		            <select class="form-select  mb-2" name="roles_id">
			             <c:forEach var="rolesVO" items="${rolesSvc.all}">
				         <option value="${rolesVO.roles_id}" ${(emplyeeVO.roles_id==rolesVO.roles_id)?'selected':'' } >${rolesVO.roles_name}
			            </c:forEach>
		            </select>
                  </div>
                  
                  <div class="form-group must">
                    <label>員工姓名<span>*</span></label>
                    <input name="emp_name" class="form-control mt-2" type="text" value="<%= (emplyeeVO==null)? "吳大衛" : emplyeeVO.getEmp_name()%>">
                  </div>
                  
                  <div class="form-group mt-2 must">
                    <label>密碼<span>*</span></label>
                    <input class="form-control mt-2" type="password" name="emp_password">
                  </div>
                  
                  <div class="form-group mt-2 must">
                    <label>密碼重複<span>*</span></label>
                    <input class="form-control mt-2" type="password" name="reemp_password">
                  </div>

                  <div class="form-group mt-2">
                    
                  </div>
                  <div class="form-group mt-3">
                    <input type="hidden" name="action" value="insert">
                    <button type="submit" class="btn btn-primary w-100">送出</button>
                  </div>
                </FORM>
                
              </article>
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