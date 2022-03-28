package com.shopOrder.controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.shopOrder.model.productOrderService;
import com.shopOrder.model.productOrderVO;
import com.shopOrderList.model.productOrderListService;
import com.shopOrderList.model.productOrderListVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;


/**
 * Application Lifecycle Listener implementation class checkPay_Sta
 *
 */
@WebListener
public class checkPay_Sta implements ServletContextListener {
	Timer timer;
	final static long secondsInDay = 86400000; //一天的ms

    public void contextDestroyed(ServletContextEvent sce)  { 
    	timer.cancel();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
        timer = new Timer();
        timer.schedule(new checkOrder(), 1000 , secondsInDay); //86400000 = 24*60*60*1000(1天檢查一次)
         
    }
    
    class checkOrder extends TimerTask{
		public void run() {
			productOrderService productOrderSvc = new productOrderService();
			productOrderListService productOrderListSvc = new productOrderListService();
			ProductService productSvc = new ProductService();
			
			//取出付款狀態為"待付款"，且訂單狀態不是"取消訂單"的訂單。
			List<productOrderVO> productOrderVOs = productOrderSvc.getAll()
													  .stream()
													  .filter(s -> s.getO_pay_sta().equals("待付款"))
													  .filter(s -> !(s.getO_sta().equals("取消訂單")))
													  .collect(Collectors.toList());
			
			for(productOrderVO productOrderVO : productOrderVOs) {
				long currentTime = System.currentTimeMillis();
				long orderTime =   productOrderVO.getO_purtime().getTime();
				
				//檢查訂單是否逾期付款(24小時)
				if( (currentTime - orderTime) >= secondsInDay) {					
					//取出該筆訂單的商品明細
					Integer o_no = productOrderVO.getO_no();
					List<productOrderListVO> productOrderListVOs = productOrderListSvc.getAll()
																		  .stream()
																		  .filter(s -> s.getO_no().equals(o_no))
																		  .collect(Collectors.toList());
					//更新每個商品的狀態
					for(productOrderListVO productOrderListVO : productOrderListVOs) {
						Integer pi_no = productOrderListVO.getPi_no(); //商品編號
						Integer quantity = productOrderListVO.getOl_proamot(); //購買數量
						
						ProductVO productVO = productSvc.getOneProductInform(pi_no);
						
						if(productVO.getPiSta().equals("下架中")) {
							productVO.setPiSta("上架中"); //更新狀態
						}
						
						Integer currentStock = productVO.getPiStock();
						productVO.setPiStock(currentStock+quantity); //更新庫存
						
						productSvc.updateProductInform(
								productVO.getPiNo(), productVO.getPiName(), productVO.getPcNo(), 
								productVO.getPiContent(), productVO.getPiPri(), productVO.getPiStock(), 
								productVO.getPiSta());
					}
					
					//更新訂單狀態為"取消訂單"
					productOrderVO.setO_sta("取消訂單");
					
					productOrderSvc.updateOrder(
							productOrderVO.getO_no(), productOrderVO.getO_purtime(), productOrderVO.getMem_no(), productOrderVO.getO_sta(), 
							productOrderVO.getO_pay_sta(), productOrderVO.getO_ship_sta(), productOrderVO.getO_totalpri(), 
							productOrderVO.getO_prodel(), productOrderVO.getO_deladrs(), productOrderVO.getO_paymthd(), productOrderVO.getO_shipdate(), 
							productOrderVO.getO_delcost()/* productOrderVO.getSo_discount_price()*/);
				}
			}
		}
    }
}
