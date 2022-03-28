package com.shopOrder.model;

import java.util.List;

import com.shopOrderList.model.productOrderListVO;

public interface productOrderDAO_interface {
	public productOrderVO insert(productOrderVO secOrder);
	public void update(productOrderVO secOrder);
	public void delete(Integer so_no);
	public productOrderVO findByPK(Integer so_no);
	public List<productOrderVO> findByMem_NO(Integer mem_no);
	public List<productOrderVO> getAll();
	public productOrderVO insertWithList(productOrderVO productOrder, List<productOrderListVO> productOrderListVOs);
	
}
