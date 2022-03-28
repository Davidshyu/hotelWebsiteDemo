package com.shopOrder.model;
import java.sql.Timestamp;
import java.util.List;

import com.shopOrderList.model.productOrderListVO;

public class productOrderService {
	private productOrderDAO_interface dao;

	public productOrderService() {
		dao = new productOrderJDBCDAO();
	}
	
	public productOrderVO insertOrder(Timestamp o_purtime, Integer mem_no, String o_sta, String o_pay_sta, 
			String o_ship_sta, Integer o_totalpri, String o_prodel, String o_deladrs,
			String o_paymthd, Timestamp o_shipdate, Integer o_delcost) {
		productOrderVO productOrderVO = new productOrderVO();
		
		productOrderVO.setO_purtime(o_purtime);
		productOrderVO.setMem_no(mem_no);
		productOrderVO.setO_sta(o_sta);
		productOrderVO.setO_pay_sta(o_pay_sta);
		productOrderVO.setO_ship_sta(o_ship_sta);
		productOrderVO.setO_totalpri(o_totalpri);
		productOrderVO.setO_prodel(o_prodel);
		productOrderVO.setO_deladrs(o_deladrs);
		productOrderVO.setO_paymthd(o_paymthd);
		productOrderVO.setO_shipdate(o_shipdate);
		productOrderVO.setO_delcost(o_delcost);
		
		productOrderVO = dao.insert(productOrderVO);
	
		return productOrderVO;
	}

	public productOrderVO updateOrder(Integer o_no, Timestamp o_purtime, Integer mem_no, String o_sta, String o_pay_sta, 
			String o_ship_sta, Integer o_totalpri, String o_prodel, String o_deladrs,
			String o_paymthd, Timestamp o_shipdate, Integer o_delcost) {
		productOrderVO productOrderVO = new productOrderVO();
		
		productOrderVO.setO_no(o_no);
		productOrderVO.setO_purtime(o_purtime);
		productOrderVO.setMem_no(mem_no);
		productOrderVO.setO_sta(o_sta);
		productOrderVO.setO_pay_sta(o_pay_sta);
		productOrderVO.setO_ship_sta(o_ship_sta);
		productOrderVO.setO_totalpri(o_totalpri);
		productOrderVO.setO_prodel(o_prodel);
		productOrderVO.setO_deladrs(o_deladrs);
		productOrderVO.setO_paymthd(o_paymthd);
		productOrderVO.setO_shipdate(o_shipdate);
		productOrderVO.setO_delcost(o_delcost);
		dao.update(productOrderVO);
	
		return productOrderVO;		
	}

	public void deleteOrder(Integer o_no) {
		dao.delete(o_no);
	}

	public productOrderVO getOneOrder(Integer o_no) {
		return dao.findByPK(o_no);
	}

	public List<productOrderVO> getOneOrderByMem(Integer mem_no) {
		return dao.findByMem_NO(mem_no);
	}

	public List<productOrderVO> getAll() {
		return dao.getAll();
	}
	
	public productOrderVO insertOrderWithList(Timestamp o_purtime, Integer mem_no, String o_sta, String o_pay_sta, 
			String o_ship_sta, Integer o_totalpri, String o_prodel, String o_deladrs,
			String o_paymthd, Timestamp o_shipdate, Integer o_delcost,
			List<productOrderListVO> productOrderListVO) {
		
		productOrderVO productOrderVO = new productOrderVO();
		
		productOrderVO.setO_purtime(o_purtime);
		productOrderVO.setMem_no(mem_no);
		productOrderVO.setO_sta(o_sta);
		productOrderVO.setO_pay_sta(o_pay_sta);
		productOrderVO.setO_ship_sta(o_ship_sta);
		productOrderVO.setO_totalpri(o_totalpri);
		productOrderVO.setO_prodel(o_prodel);
		productOrderVO.setO_deladrs(o_deladrs);
		productOrderVO.setO_paymthd(o_paymthd);
		productOrderVO.setO_shipdate(o_shipdate);
		productOrderVO.setO_delcost(o_delcost);
		
//		productOrderVO = dao.insertWithList(productOrderVO, productOrderListVO);//////////////////////////////
		productOrderVO = dao.insert(productOrderVO);//////////////////////////////

		return productOrderVO;
	
	}

}
