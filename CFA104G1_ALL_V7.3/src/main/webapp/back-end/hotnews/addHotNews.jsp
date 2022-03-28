<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hotnews.model.*"%>

<%
  HotNewsVO HotNewsVO = (HotNewsVO) request.getAttribute("HotNewsVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>穝糤程穝</title>
<%@ include file= "/back-end/framework/include.file" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
<link href="<%=request.getContextPath()%>/resources/plugins/summernote/summernote-bs4.min.css" rel="stylesheet">

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">程穝戈穝糤</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/hotnews/listAllHotNews.jsp">程穝</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/hotnews/select_page.jsp">程穝琩高</a></li>
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
	
					<!-- 岿粇 -->
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">叫タ岿粇:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotnews/HotNews.do" name="form1" enctype="multipart/form-data">
					<table class="table">
						<tr>
							<td>程穝夹肈:</td>
							<td><input type="TEXT" name="hot_news_title" size="45" placeholder="叫块程穝夹肈" /></td>
						</tr>
					
						<tr>
							<td>程穝ず甧:</td>
							<td>
							 	<textarea id="hot_news_description" name="hot_news_description" rows="5" class="form-control">
								</textarea>
							 </td>
						</tr>
						<tr>
							<td>程穝ら戳:</td>
							<td><input type="TEXT" id="hot_news_date" name="hot_news_date" size="45"></td>
						</tr>
						<tr>
							<td>程穝篈:</td>
							<td><select name="hot_news_status" size="0">
								<option value="true">琜</option>
								<option value="false">琜</option>
							</select></td>
						</tr>
						
						<tr>
							<td>程穝:</td>
							<td>
							<div class="row">
							<div class="p-1">
								<img src="images/pr.png" width="100" border="0" id="showimg">
							</div>
							<div class="p-1">
								<input type="file" name="hot_news_photo" size="45" id="flie" onchange="show(this)"/>
							</div>
							</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="card-footer">
					<input type="hidden" name="action" value="insert">
					<input class="btn btn-outline-success" type="submit" value="癳穝糤"></FORM>        
					<a class="btn btn-outline-secondary" 
					   href="<%=request.getContextPath()%>/back-end/hotnews/listAllHotNews.jsp">斌穝糤</a>					
				</div>
			</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>
<script src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.full.js"></script>
<!-- Summernote -->
<script src="<%=request.getContextPath()%>/resources/plugins/summernote/summernote-bs4.min.js"></script>
        
<script>  
$('document').ready(function()
	{
	    $('textarea').each(function(){
            $(this).val($(this).val().trim());
        }
    );
});
$(function () {
    // Summernote
    $('#hot_news_description').summernote({
        height: 120,
        placeholder: '叫块程穝ず甧'
      });
  });
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 var today = new Date();
  		$('#hot_news_date').datetimepicker({
  		format:'Y-m-d',
  		value:  new Date(),
  	    timepicker:false
  	});
});
function show(thisimg) {
	var file = thisimg.files[0];
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