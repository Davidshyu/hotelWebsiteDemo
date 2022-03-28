package com.shopOrderList.model;

import java.util.List;

public interface productOrderListDAO_interface {
	public productOrderListVO insert(productOrderListVO secOrderList);
	public void update(productOrderListVO secOrderList);
	public void delete(Integer sol_no);
	public productOrderListVO findByPk(Integer sol_no);
	public List<productOrderListVO> getAll();
}
