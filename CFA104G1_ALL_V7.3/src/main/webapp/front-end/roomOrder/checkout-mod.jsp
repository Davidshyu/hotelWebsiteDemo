<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomOrder.model.*"%>
<%
RoomOrderVO roomOrderVO = (RoomOrderVO) request.getAttribute("roomOrderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
<title>Checkout example · Bootstrap v5.1</title>
<%@ include file="/front-end/framework/include.file"%>
<link href='https://use.fontawesome.com/releases/v5.8.1/css/all.css'
	rel='stylesheet'>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.1/examples/checkout/">




<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

body {
	background: #f5f5f5
}

.rounded {
	border-radius: 1rem
}

.nav-pills .nav-link {
	color: #555
}

.nav-pills .nav-link.active {
	color: white
}

input[type="radio"] {
	margin-right: 5px
}

.bold {
	font-weight: bold
}
</style>


<!-- Custom styles for this template -->
<link
	href="<%=request.getContextPath()%>/front-end/roomOrder/form-validation.css"
	rel="stylesheet">
</head>
<body>
	<%@ include file="/front-end/framework/header.file"%>

	<div class="container">
		<main>
			<div class="py-5 text-center"></div>

			<div class="row g-5">

				<div class="m-3">
					<h4 class="mb-3">訂房資訊</h4>

					<form class="needs-validation" novalidate METHOD="post"
						ACTION="<%=request.getContextPath()%>/roomOrder/roomOrder.do"
						name="form1">
						<div class="row g-3">
							<div class="col-2">
								<label class="form-label">入住時間</label> 
								<input name="arrival_date" class="form-control" id="arrival_date"
									type="text" value="${roomOrderVO.arrival_date}" readonly>
							</div>
							<div class="col-2">
								<label class="form-label">退房時間</label> 
								<input name="departure_date" class="form-control" id="departure_date"
									type="text" value="${roomOrderVO.departure_date}" readonly>
							</div>
							<div class="col-md-2">
								<label for="inputAddress" class="form-label">房型</label> 
								<input name="room_type_id" class="form-control" id="room_type_id"
									type="text" value="1" readonly>
							</div>
							<div class="col-md-2">
								<label for="inputAddress" class="form-label">住房人數</label> 
								<input name="number_of_people" class="form-control"
									id="number_of_people" type="text"
									value="${roomOrderVO.room_review}" readonly>
							</div>
							<div class="col-md-2">
								<label class="form-label">訂房數量</label> 
								<input	name="total_room_number" class="form-control"
									id="total_room_number" type="text"
									value="${roomOrderVO.room_charge}" readonly>
							</div>
							<div class="col-md-6">
								<label class="form-label">訂單總金額:</label> 
								<input type="text" class="form-control" name="room_charge" id="room_charge">
							</div>
							<div class="input-group">
								<span class="input-group-text">特殊需求</span>
								<textarea class="form-control" aria-label="With textarea"
									rows="6" name="special_req" id="special_req"></textarea>
							</div>
						</div>

						<h4 class="mb-3">付款方式</h4>
						<div class="row">
							<div>
								<div class="card ">
									<div class="card-header">
										<div class="bg-white shadow-sm pt-4 pl-2 pr-2 pb-2">
											<!-- Credit card form tabs -->
											<ul role="tablist"
												class="nav bg-light nav-pills rounded nav-fill mb-3">
												<li class="nav-item"><a data-toggle="pill"
													href="#credit-card" class="nav-link active "> 信用卡線上付款 </a>
												</li>
												<li class="nav-item"><a data-toggle="pill"
													href="#paypal" class="nav-link "> <i class="cash"></i>
														現場付款
												</a></li>

											</ul>
										</div>
										<!-- End -->
										<!-- Credit card form content -->
										<div class="tab-content">
											<!-- credit card info-->
											<div id="credit-card" class="tab-pane fade show active pt-3">

												<div class="form-group col-5">
													<label for="username">
														<h6>Card Owner</h6>
													</label> <input type="text" name="username"
														placeholder="Card Owner Name" required
														class="form-control ">
												</div>
												<div class="form-group col-6">
													<label for="cardNumber">
														<h6>Card number</h6>
													</label>
													<div class="input-group">
														<input type="text" name="cardNumber"
															placeholder="Valid card number" class="form-control "
															required>
														<div class="input-group-append">
															<span class="input-group-text text-muted"> <i
																class="fab fa-cc-visa mx-1"></i> <i
																class="fab fa-cc-mastercard mx-1"></i> <i
																class="fab fa-cc-amex mx-1"></i>
															</span>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-9">
														<div class="form-group col-8">
															<label><span class="hidden-xs">
																	<h6>Expiration Date</h6>
															</span></label>
															<div class="input-group">
																<input type="number" placeholder="MM" name=""
																	class="form-control" required> <input
																	type="number" placeholder="YY" name=""
																	class="form-control" required>
															</div>
														</div>
													</div>
													<div class="col-3">
														<div class="form-group mb-4">
															<label data-toggle="tooltip"
																title="Three digit CV code on the back of your card">
																<h6>
																	CVV <i class="fa fa-question-circle d-inline"></i>
																</h6>
															</label> <input type="text" required class="form-control">
														</div>
													</div>
												</div>
												<div class="card-footer"></div>
											</div>
											<!-- End -->
											<!-- Paypal info -->
											<div id="paypal" class="tab-pane fade pt-3">

												<ul>
													<li><span>以飯店當地貨幣付款給飯店</span></li>
													<li><span>在飯店的取消限期內都可以免費取消</span></li>
												</ul>
											</div>
											<!-- End -->
											<!-- bank transfer info -->

											<!-- End -->
										</div>
									</div>
								</div>
							</div>


						</div>

						<hr class="my-4">
						<input type="hidden" name="action" value="SaveOder"> 
						<input type="hidden" name="mem_id" size="45" value="1" /> 
						<input type="hidden" name="order_date" id="order_date" /> 
						<input type="hidden" name="room_order_status" id="room_order_status" value="1"> 
						<input type="hidden" name="room_review" id="room_review" value="0">
						<input type="hidden" name="room_id" id="room_id" value="0"> 
						<input type="hidden" name="room_price" id="room_price" value="0">
						<input type="hidden" name="service_order_id" id="service_order_id" value="0">
						<button class="btn btn-success btn-lg" type="submit">結帳</button>
					</form>
				</div>
			</div>
		</main>
	</div>



	<%@ include file="/front-end/framework/footer.file"%>
	<script type='text/Javascript'>
		$(function() {
			$('[data-toggle="tooltip"]').tooltip()
		})
	</script>

</body>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {

		var today = new Date();
		$('#arrival_date')
				.datetimepicker(
						{
							format : 'Y-m-d',
							timepicker : false,
							beforeShowDay : function(date) {
								if (date.getYear() < today.getYear()
										|| (date.getYear() == today.getYear() && date
												.getMonth() < today.getMonth())
										|| (date.getYear() == today.getYear()
												&& date.getMonth() == today
														.getMonth() && date
												.getDate() < today.getDate())) {
									return [ false, "" ]
								}
								return [ true, "" ];
							}
						});

		$('#departure_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							minDate : $('#arrival_date').val() ? $('#arrival_date').val() : false
						})
					},
					timepicker : false,
				});

		$('#order_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							minDate : $('#arrival_date').val() ? $('#arrival_date').val() : false
						})
					},
					timepicker : false,
					value : today
				});
	});
</script>
</html>
