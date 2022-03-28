package com.shopOrderList.model;
import java.util.List;

public class productOrderListService {
private productOrderListDAO_interface dao;
	
	public productOrderListService() {
		dao = new productOrderListJDBCDAO();
	}
	
	public productOrderListVO insertOrderList(Integer o_no, Integer pi_no, Integer ol_proamot, Integer ol_pri) {
		productOrderListVO productOrderListVO = new productOrderListVO();
		
		productOrderListVO.setO_no(o_no);
		productOrderListVO.setPi_no(pi_no);
		productOrderListVO.setOl_proamot(ol_proamot);
		productOrderListVO.setOl_pri(ol_pri);
		productOrderListVO = dao.insert(productOrderListVO);
		
		return productOrderListVO;
	}

	public productOrderListVO updateOrderList(Integer ol_no, Integer o_no, Integer pi_no, Integer ol_proamot, Integer ol_pri) {
		productOrderListVO productOrderListVO = new productOrderListVO();
		
		productOrderListVO.setOl_no(ol_no);
		productOrderListVO.setO_no(o_no);
		productOrderListVO.setPi_no(pi_no);
		productOrderListVO.setOl_proamot(ol_proamot);
		productOrderListVO.setOl_pri(ol_pri);
		dao.update(productOrderListVO);
		
		return productOrderListVO;
	}

	public void deleteOrderList(Integer sol_no) {
		dao.delete(sol_no);
	}

	public productOrderListVO getOneOrderList(Integer ol_no) {
		return dao.findByPk(ol_no);
	}

	public List<productOrderListVO> getAll() {
		return dao.getAll();
	}

}
