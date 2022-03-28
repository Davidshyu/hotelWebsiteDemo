package com.productClass.model;

import java.util.List;

public interface productClassDAO_interface {
	public ProductClassVO insert(ProductClassVO productClassVO);
	public void update(ProductClassVO productClassVO);
	public void delete(Integer pc_no);
	public ProductClassVO findByPK(Integer pc_no);
	public List<ProductClassVO> getAll();
}
