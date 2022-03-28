package com.shopOrder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;


import com.mem.model.MemVO;
import com.shopOrder.model.productOrderService;
import com.shopOrder.model.productOrderVO;
import com.shopOrderList.model.productOrderListService;
import com.shopOrderList.model.productOrderListVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

import oracle.net.aso.s;

public class orderServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action : " + action);
		
		if("getOneForDiplay".equals(action)) {
			
			/****1.取得參數****/
			Integer o_no = new Integer(req.getParameter("o_no"));
			
			/****2.開始查詢****/
			
			/****查詢訂單****/
			productOrderService productOrderSvc = new productOrderService();
			productOrderVO productOrderVO = productOrderSvc.getOneOrder(o_no);
			req.setAttribute("OrderVO", productOrderVO);
			
			/****查詢訂單明細****/
			productOrderListService productOrderListSvc = new productOrderListService();
			List<productOrderListVO> productOrderListVOs = productOrderListSvc.getAll()
																  .stream()
																  .filter(s -> s.getO_no().equals(o_no)) /*篩選出此訂單的明細*/
																  .collect(Collectors.toList());
			req.setAttribute("OrderListVOs", productOrderListVOs);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/listOneOrder.jsp");////////////////////////////
			successView.forward(req, res);
			return;
			
		}
		
		if("getOneForUpdate".equals(action)) {
			/****1.取的參數****/
			Integer o_no = new Integer(req.getParameter("o_no"));
			
			/****2.開始查詢****/
			productOrderService productOrderSvc = new productOrderService();
			productOrderVO productOrderVO = productOrderSvc.getOneOrder(o_no);
			req.setAttribute("productOrderVO", productOrderVO);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/updateOrder.jsp");////////////////////////////////
			successView.forward(req, res);
			return;
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			Integer o_no = new Integer(req.getParameter("o_no"));//訂單編號
			String o_sta = req.getParameter("o_sta"); //訂單狀態
			String o_pay_sta = req.getParameter("o_pay_sta"); //付款狀態
			String o_ship_sta = req.getParameter("o_ship_sta"); //出貨狀態
			String o_prodel = req.getParameter("o_prodel"); //運送方式
			String str = req.getParameter("o_deladrs"); //配送地址
			System.out.println("o_shipdate : " + req.getParameter("o_shipdate"));
						
			Timestamp  o_shipdate = Timestamp.valueOf(new StringBuilder(req.getParameter("o_shipdate"))
																			.append(" 00:00:00.123")
																			.toString());  //出貨日期
			
			/**********************************************************/

			Timestamp  o_purtime = Timestamp.valueOf(req.getParameter("o_purtime")); //訂購日期
			
			Integer mem_no =new Integer(req.getParameter("mem_no")); //會員編號
			Integer o_totalpri =new Integer(req.getParameter("o_totalpri")); //訂單總價格 
			String o_paymthd = req.getParameter("o_paymthd"); //付款方式 
			Integer o_delcost =new Integer(req.getParameter("o_delcost")); //運費
//			Integer o_discount_price =new Integer(req.getParameter("o_discount_price")); //訂單總價格 
						
			if(str.trim().length()==0) {
				errorMsgs.add("請輸入配送地址!!!");
			}
			
			productOrderService productOrderSvc_1 = new productOrderService();
			productOrderVO productOrderVO = productOrderSvc_1.getOneOrder(o_no);
			productOrderVO.setO_sta(o_sta);
			productOrderVO.setO_pay_sta(o_pay_sta);
			productOrderVO.setO_ship_sta(o_ship_sta);
			productOrderVO.setO_prodel(o_prodel);
			productOrderVO.setO_deladrs(str);
			req.setAttribute("productOrderVO", productOrderVO);
			
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/productOrder/updateOrder.jsp");///////////////////////////////
				failView.forward(req, res);
				return;
			}
			
			String o_deladrs = str;
			
			/****2.更新資料****/
			productOrderService productOrderSvc = new productOrderService();
			productOrderSvc.updateOrder(o_no, o_purtime, mem_no, o_sta, o_pay_sta, o_ship_sta, o_totalpri, 
										o_prodel, o_deladrs, o_paymthd, o_shipdate, o_delcost /*o_discount_price*/);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/listAllOrder.jsp");
			successView.forward(req, res);
			return;
			
		}
		
		if("insert".equals(action)) {
			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//取得會員資訊
			MemVO memberVO = (MemVO) session.getAttribute("user");
			//取得購物車內的商品
			Vector<ProductVO> productInformList = (Vector<ProductVO>) session.getAttribute("shoppingCart");///////////////////////
			//取得商品購買數量
			Map<Integer, Integer> Quamap = (Map<Integer, Integer>) session.getAttribute("Quamap");

			/****1.取得參數****/
			String o_prodel = req.getParameter("o_prodel"); //運送方式 >> 自取or宅配
			String o_deladrs = req.getParameter("o_deladrs");//配送地址 >>需要檢查此欄位!!!
			String o_paymthd = req.getParameter("o_paymthd");//付款方式 >> 匯款or信用卡
			
			
			if("宅配".equals(o_prodel)) {
				//檢查配送地址是否為空
				if(o_deladrs.trim().length()==0) {
					errorMsgs.add("請輸入配送地址!!");
				}
			}
			
			//檢查購物車內是否有商品
			if( !(productInformList!=null && productInformList.size()>0) ) {
				errorMsgs.add("請加入要購買的商品");
			}
			
			req.setAttribute("o_prodel", o_prodel);
			req.setAttribute("o_deladrs", o_deladrs);
			req.setAttribute("o_paymthd", o_paymthd);
			
			if(!errorMsgs.isEmpty()) {				
				RequestDispatcher failView =
						req.getRequestDispatcher("/front-end/Order/checkOut.jsp");
				failView.forward(req, res);
				return;
			}
										
			/****2.給予初始值****/
			Timestamp o_purtime = new Timestamp(System.currentTimeMillis());  //訂購時間 >>預設為下單當下時間
			Integer mem_no = new Integer(memberVO.getMem_id()); //會員編號							/////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////
			String o_sta = "備貨中";	//訂單狀態 >> 預設為備貨中
			String o_pay_sta = "待付款"; //付款狀態 >> 先寫死!!
			String o_ship_sta = "未出貨"; //出貨狀態 >> 預設為未出貨
//			Timestamp o_shipdate = null; //出貨日期 >> 預設為空
			Timestamp o_shipdate = new Timestamp(System.currentTimeMillis()+432000000);
			Integer o_delcost = new Integer( o_prodel.equals("自取")?"0":"100" ); //配送費用 >> 自取運費為0，宅配100
			Integer o_totalpri = Quamap.get(999) + o_delcost; //訂單總價格 >> 商品費用+配送費用
//			Integer so_discount_price = so_totalpri - discount;//訂單優惠價格 >> 訂單總價格-優惠券折扣費用
			
			/****3.將購物車內商品加入"商品明細"中****/
			List<productOrderListVO> productOrderListVO = new LinkedList<productOrderListVO>();
			productOrderListVO productOrderVO = null;
			for(ProductVO productVO :productInformList) {
				productOrderVO = new productOrderListVO();
				Integer pi_no = productVO.getPiNo();//取得商品編號
				productOrderVO.setPi_no(pi_no); //加入商品編號
				productOrderVO.setOl_proamot(Quamap.get(pi_no));//加入商品數量
				
				//計算商品明細總價(商品價格 * 購買數量)
				Integer ol_pri = productVO.getPiPri() * Quamap.get(pi_no);
				productOrderVO.setOl_pri(ol_pri);
				productOrderListVO.add(productOrderVO);
			}
			
			try {
				/****4.開始新增商品訂單、商品明細****/
				productOrderService productOrderSvc = new productOrderService();
				productOrderVO productOrderVO2 = null;
				productOrderVO2 = productOrderSvc.insertOrderWithList(o_purtime, mem_no, o_sta, o_pay_sta, o_ship_sta, 
											o_totalpri, o_prodel, o_deladrs, o_paymthd, o_shipdate, 
											o_delcost, productOrderListVO);///////////////////////////////////////////////////////
				
				req.setAttribute("productOrderVO", productOrderVO2);
							
			} catch (Exception e) {
				errorMsgs.add("結帳失敗");
				RequestDispatcher failView =
						req.getRequestDispatcher("/front-end/Order/checkOut.jsp");////////////////////////////////////////////////////////
				failView.forward(req, res);
				return;
			}finally {
				//結帳完成，更新商品庫存與商品狀態。
				for(ProductVO productVO : productInformList) {
					int newStock = productVO.getPiStock() - Quamap.get(productVO.getPiNo());
					productVO.setPiStock(new Integer(newStock));
					
					//檢查商品庫存為0，則下架
					if(newStock == 0) {
						productVO.setPiSta("下架中");
					}
					
					//更新資料庫
					ProductService productSvc = new ProductService();
					productSvc.updateProductInform(
							productVO.getPiNo(), productVO.getPiName(), productVO.getPcNo(), 
							productVO.getPiContent(), productVO.getPiPri(), productVO.getPiStock(), 
							productVO.getPiSta());
				}
				
				//結帳完成，清空購物車內商品
				productInformList.removeAllElements();
				Quamap.clear();
				
				session.setAttribute("Quamap", Quamap);
				session.setAttribute("shoppingCart", productInformList);//////////////////////////////////////////////////////
				
				String str = null;
				if(o_paymthd.equals("信用卡")) {
					str = "/front-end/Order/creditCardPay.jsp";
				}else {
					str = "/front-end/Order/transferPay.jsp";
				}
				//轉至付款頁面
				RequestDispatcher successView = 
						req.getRequestDispatcher(str);
				successView.forward(req, res);
			}
			return;
		}
		
		if("confirmPay".equals(action)) {
			/*預設皆為付款成功*/
			
			/****1.取得訂單編號****/
			Integer o_no = new Integer(req.getParameter("o_no"));
			
			/****2.查詢訂單****/
			productOrderService productOrderSvc = new productOrderService();
			productOrderVO productOrderVO = productOrderSvc.getOneOrder(o_no);
			
			String str = null;
			if( !productOrderVO.getO_sta().equals("取消訂單") ) {
				
				/****3.修改訂單狀態 (待付款更改為已付款)****/
				productOrderVO.setO_pay_sta("已付款");
				
				/****4.更新訂單****/
				productOrderSvc.updateOrder( productOrderVO.getO_no(),		productOrderVO.getO_purtime(),	 productOrderVO.getMem_no(), 
						productOrderVO.getO_sta(),		productOrderVO.getO_pay_sta(),	 productOrderVO.getO_ship_sta(), 
						productOrderVO.getO_totalpri(), productOrderVO.getO_prodel(), 
						productOrderVO.getO_deladrs(),	productOrderVO.getO_paymthd(),  productOrderVO.getO_shipdate(), 
						productOrderVO.getO_delcost()	/*productOrderVO.getO_discount_price() */);	//////////////////////////////////////////////////////////////////////
				/****5.設置付款成功頁面路徑****/
				str = "/front-end/Order/paymentSuccess.jsp";
			}else {
				//訂單狀態為"取消訂單"則無法付款
				str = "/front-end/Order/paymentFail.jsp";
			}
			
			
			/****6.頁面轉向****/
			req.setAttribute("productOrderVO", productOrderVO);

			RequestDispatcher view = 
					req.getRequestDispatcher(str);
			view.forward(req, res);
			return;
		}
		
		//檢查購物車內商品與優惠券
		if("checkCart".equals(action)) {
			HttpSession session = req.getSession();
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****0.檢查是否登入會員****/
			MemVO memberVO = (MemVO) session.getAttribute("user");
			if(memberVO==null) {
				errorMsgs.add("溫馨提醒：請先登入會員!");
				RequestDispatcher failView =
						req.getRequestDispatcher("/front-end/Product/cart.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****1.檢查購物車****/
			//取得購物車內的商品
			Vector<ProductVO> productInformList = (Vector<ProductVO>) session.getAttribute("shoppingCart");/////////////////////////////////////////
			
			//檢查購物車內是否有商品
			if( !(productInformList!=null && productInformList.size()>0) ) {
				errorMsgs.add("請加入要購買的商品");
			}
			
			if(!errorMsgs.isEmpty()) {				
				RequestDispatcher failView =
						req.getRequestDispatcher("/front-end/Product/cart.jsp");
				failView.forward(req, res);
				return;
			}
			
			/****2.檢查優惠券****/
//			//取得優惠券編碼
//			String ci_code = req.getParameter("ci_code");
//			
//			//檢查優惠券
//			if(ci_code.trim().length()!=0) {
//				//確認編碼是否存在
//				Coupon_InformationService coupon_InformationSvc = new Coupon_InformationService();
//				List<Coupon_InformationVO> list = coupon_InformationSvc.getAll()
//																		.stream()
//																		.filter(c -> c.getCi_code().equals(ci_code))
//																		.collect(Collectors.toList());
//				if(list.size()==0) {
//					errorMsgs.add("您輸入的優惠券無效!");
//				}
//				
//				if(!errorMsgs.isEmpty()) {				
//					RequestDispatcher failView =
//							req.getRequestDispatcher("/front-end/secProductInfo/cart.jsp");
//					failView.forward(req, res);
//					return;
//				}
//				
//				//檢查優惠券時效
//				Coupon_InformationVO coupon_VO = list.get(0);
//				
//				Timestamp currentTime = new Timestamp(System.currentTimeMillis()); //取得當前時間
//				Timestamp start_time = coupon_VO.getCi_start_time();
//				Timestamp end_time =coupon_VO.getCi_end_time();
//				if( !(currentTime.compareTo(start_time)>0 && currentTime.compareTo(end_time)<0) ) {
//					errorMsgs.add("您輸入的優惠券無效!");
//				}
//											
//				if(!errorMsgs.isEmpty()) {				
//					RequestDispatcher failView =
//							req.getRequestDispatcher("/front-end/secProductInfo/cart.jsp");
//					failView.forward(req, res);
//					return;
//				}
//				
//				//取的優惠券編碼與折扣金額
//				Integer ci_no = coupon_VO.getCi_no();
//				Integer discount = coupon_VO.getDiscount();
//				
//				req.setAttribute("ci_no", ci_no);
//				req.setAttribute("discount", discount);
//			}
//			
			/****3.轉向至結帳頁面****/
			RequestDispatcher successView =
					req.getRequestDispatcher("/front-end/Order/checkOut.jsp");////////////////////////////////////////////
			successView.forward(req, res);
			return;			
		}
		
		
		if("findByPK".equals(action)) {
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String o_no_str = req.getParameter("o_no");
			if(o_no_str.trim().length()==0) {
				errorMsgs.add("請輸入訂單編號!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/productOrder/Order_select_page.jsp");/////////////////////////////////////////////////
				failView.forward(req, res);
				return;
			}
			
			Integer o_no = null;
			try {
				o_no = new Integer(o_no_str);
			} catch (Exception e) {
				errorMsgs.add("請輸入數字!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/productOrder/Order_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			
			/****2.開始查詢****/
			productOrderService productOrderSvc = new productOrderService();
			List<productOrderVO> order_list = new LinkedList<productOrderVO>();
			productOrderVO productOrderVO = productOrderSvc.getOneOrder(o_no);
			
			order_list.add(productOrderVO);
		
//			session.setAttribute("order_list_search", order_list);
			req.setAttribute("order_list_search", order_list);///////////////////////////////////////////////////////////////////
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/afterSearch.jsp");
			successView.forward(req, res);
			return;
		}
		
		//依會員編號查詢
		if("findByMemNO".equals(action)) {
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String mem_no_str = req.getParameter("mem_no");
			if(mem_no_str.trim().length()==0) {
				errorMsgs.add("請輸入會員編號!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/productOrder/Order_select_page.jsp");/////////////////////////////////////////////////////
				failView.forward(req, res);
				return;
			}
			
			Integer mem_no = null;
			try {
				mem_no = new Integer(mem_no_str);
			} catch (Exception e) {
				errorMsgs.add("請輸入數字!!");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/productOrder/Order_select_page.jsp");////////////////////////////////////////////////
				failView.forward(req, res);
				return;
			}
			
			/****2.開始查詢****/
			productOrderService productOrderSvc = new productOrderService();
			List<productOrderVO> order_list = productOrderSvc.getOneOrderByMem(mem_no);
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_search", order_list);/////////////////////////////////////////////////////////////
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/afterSearch.jsp");///////////////////////////////////////////////////
			successView.forward(req, res);
			return;
		}
		
		//依訂單狀態查詢
		if("findByO_Sta".equals(action)) {	///////////////////////////////////////////////////////////////////////////////
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String o_sta = req.getParameter("o_sta");

			/****2.開始查詢****/
			productOrderService productOrderSvc = new productOrderService();
			List<productOrderVO> order_list = productOrderSvc.getAll()
													 .stream()
													 .filter(s -> s.getO_sta().equals(o_sta))
													 .collect(Collectors.toList());
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_search", order_list);///////////////////////////////////////////////////////////////////
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/afterSearch.jsp");///////////////////////////////////////
			successView.forward(req, res);
			return;
		}
		
		//依付款狀態查詢
		if("findByO_PaySta".equals(action)) {///////////////////////////////////////////////////////////////////////
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String o_pay_sta = req.getParameter("o_pay_sta");

			/****2.開始查詢****/
			productOrderService productOrderSvc = new productOrderService();
			List<productOrderVO> order_list = productOrderSvc.getAll()
													 .stream()
													 .filter(s -> s.getO_pay_sta().equals(o_pay_sta))
													 .collect(Collectors.toList());
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_search", order_list);////////////////////////////////////////////////////////
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/afterSearch.jsp");//////////////////////////////////
			successView.forward(req, res);
			return;
		}
		
		//依出貨狀態查詢
		if("findByO_ShipSta".equals(action)) {//////////////////////////////////////////////////////////////////
//			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			String o_ship_sta = req.getParameter("o_ship_sta");
			/****2.開始查詢****/
			productOrderService productOrderSvc = new productOrderService();
			List<productOrderVO> order_list = productOrderSvc.getAll()
													 .stream()
													 .filter(s -> s.getO_ship_sta().equals(o_ship_sta))
													 .collect(Collectors.toList());
			
//			session.setAttribute("order_list_seach", order_list);
			req.setAttribute("order_list_search", order_list);////////////////////////////////////////////////////////////////
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/afterSearch.jsp");
			successView.forward(req, res);
			return;
		}
		
		//前台會員中心，顯示訂單詳情
		if("front_getOneForDiplay".equals(action)) {
			
			/****1.取得參數****/
			Integer o_no = new Integer(req.getParameter("o_no"));
			
			/****2.開始查詢****/
			
			/****查詢訂單****/
			productOrderService productOrderSvc = new productOrderService();
			productOrderVO productOrderVO = productOrderSvc.getOneOrder(o_no);
			req.setAttribute("productOrderVO", productOrderVO);
			
			/****查詢訂單明細****/
			productOrderListService productOrderListSvc = new productOrderListService();
			List<productOrderListVO> productOrderListVOs = productOrderListSvc.getAll()
																  .stream()
																  .filter(s -> s.getO_no().equals(o_no)) /*篩選出此訂單的明細*/
																  .collect(Collectors.toList());
			req.setAttribute("productOrderListVOs", productOrderListVOs);//////////////////////////////////////////////
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/front-end/Order/listOneOrder.jsp");/////////////////////////////////////////
			successView.forward(req, res);
			return;
			
		}
		
		//前台會員中心取消訂單
		if("front_cancelOrder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			Integer o_no = new Integer(req.getParameter("o_no"));
			
			/****2.開始查詢 查詢訂單****/
			productOrderService productOrderSvc = new productOrderService();
			productOrderVO productOrderVO = productOrderSvc.getOneOrder(o_no);
			
			/****3.檢查訂單狀態****/
			if("已付款".equals(productOrderVO.getO_pay_sta())) {
				errorMsgs.add("此訂單已付款，無法取消!!");
			}else {
				/****4.更新訂單狀態為"取消訂單"****/
				productOrderVO.setO_sta("取消訂單");
				
				productOrderSvc.updateOrder(
						productOrderVO.getO_no(), productOrderVO.getO_purtime(), productOrderVO.getMem_no(), productOrderVO.getO_sta(), 
						productOrderVO.getO_pay_sta(), productOrderVO.getO_ship_sta(), productOrderVO.getO_totalpri(), 
						productOrderVO.getO_prodel(), productOrderVO.getO_deladrs(), productOrderVO.getO_paymthd(), productOrderVO.getO_shipdate(), 
						productOrderVO.getO_delcost() /*productOrderVO.getSo_discount_price()*/);
			}
			/****5.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/front-end/Order/MemberCentreOrder.jsp");/////////////////////////////////
			successView.forward(req, res);
			return;
			
			
		}
		
		//前台會員中心付款
		if("Payment".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.取得參數****/
			Integer o_no = new Integer(req.getParameter("o_no"));
			
			/****2.取得VO****/
			productOrderService productOrderSvc = new productOrderService();
			productOrderVO productOrderVO =  productOrderSvc.getOneOrder(o_no);
			
			/****3.確認訂單狀態****/
			if(productOrderVO.getO_sta().equals("取消訂單")) {
				errorMsgs.add("此訂單已取消， 請重新下標!!");
			}else if(productOrderVO.getO_pay_sta().equals("已付款")) {
				errorMsgs.add("此訂單已付款!!");
			}

			req.setAttribute("productOrderVO", productOrderVO);
			
			/****3.設置轉向頁面****/
			String str = null;
			if(productOrderVO.getO_paymthd().equals("信用卡")) {
				str = "/front-end/Order/creditCardPay.jsp";/////////////////////////////////////
			}else {
				str = "/front-end/Order/transferPay.jsp";////////////////////////////////////////////////
			}
			//轉至付款頁面
			RequestDispatcher successView = 
					req.getRequestDispatcher(str);
			successView.forward(req, res);
			return;
		}
		
		//複合查詢
		if("compoundQuery".equals(action)) {			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);	
						
			/****1.取得參數****/
			String o_no_str = req.getParameter("o_no");
			Integer o_no = null;
			if(o_no_str.trim().length() != 0) {
				try {
					o_no = new Integer(req.getParameter("o_no"));
				} catch (NumberFormatException e) {
					errorMsgs.add("訂單編號請輸入數字");
				}
			}
			
			String mem_no_str = req.getParameter("mem_no");
			Integer mem_no = null;
			if(mem_no_str.trim().length() != 0) {
				try {
					mem_no = new Integer(req.getParameter("mem_no"));
				} catch (NumberFormatException e) {
					errorMsgs.add("會員編號請輸入數字");
				}
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/productOrder/Order_select_page.jsp");//////////////////////////////////
				failView.forward(req, res);
				return;
			}
			
			String o_sta = req.getParameter("o_sta");
			String o_pay_sta = req.getParameter("o_pay_sta");
			String o_ship_sta = req.getParameter("o_ship_sta");
			
			Map<String, String> condititon = new HashMap<String, String>();
			condititon.put("o_no_str", o_no_str);
			condititon.put("mem_no_str", mem_no_str);
			condititon.put("o_sta", o_sta);
			condititon.put("o_pay_sta", o_pay_sta);
			condititon.put("o_ship_sta", o_ship_sta);
			
			/****2.開始查詢****/
			productOrderService productOrderSvc = new productOrderService();
			List<productOrderVO> productOrderVOs = productOrderSvc.getAll();
			
			Set<String> key = condititon.keySet();
			
			key.stream()
			   .forEach(k -> compoundQuery(productOrderVOs, k, condititon.get(k)));
			
			if(productOrderVOs.isEmpty()) {
				errorMsgs.add("查無訂單!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/productOrder/Order_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("order_list_search", productOrderVOs);///////////////////////////////////////////////////
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/productOrder/afterSearch.jsp");//////////////////////////////////////
			successView.forward(req, res);
			return;
		}

	}
	
	//此方法根據輸入的key和value過濾productInformVOs內的VO
	private void compoundQuery (List<productOrderVO> productOrderVOs, String key, String value) {
		List<productOrderVO> productOrderVO_after = null;
		
		if(value.trim().length()!=0) {
			switch (key) {
			case "o_no_str":
				productOrderVO_after = productOrderVOs.stream()
											  .filter(i -> i.getO_no().equals(new Integer(value)))
											  .collect(Collectors.toList());
				productOrderVOs.clear();
				productOrderVOs.addAll(productOrderVO_after);
				break;
			case "mem_no_str":
				productOrderVO_after = productOrderVOs.stream()
											  .filter(i -> i.getMem_no().equals(new Integer(value)))
											  .collect(Collectors.toList());
				productOrderVOs.clear();
				productOrderVOs.addAll(productOrderVO_after);
				break;
			case "o_sta":
				productOrderVO_after = productOrderVOs.stream()
											  .filter(i -> i.getO_sta().equals(value))
											  .collect(Collectors.toList());
				productOrderVOs.clear();				
				productOrderVOs.addAll(productOrderVO_after);
				break;
			case "o_pay_sta":
				productOrderVO_after = productOrderVOs.stream()
											  .filter(i -> i.getO_pay_sta().equals(value))
											  .collect(Collectors.toList());
				productOrderVOs.clear();
				productOrderVOs.addAll(productOrderVO_after);
				break;	
			case "o_ship_sta":
				productOrderVO_after = productOrderVOs.stream()
											  .filter(i -> i.getO_ship_sta().equals(value))
											  .collect(Collectors.toList());
				productOrderVOs.clear();
				productOrderVOs.addAll(productOrderVO_after);
				break;
			default:
				break;
			}
		}
	}
	
}
