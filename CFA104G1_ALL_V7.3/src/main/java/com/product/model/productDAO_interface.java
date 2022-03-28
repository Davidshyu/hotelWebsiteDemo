package com.product.model;

import java.util.List;

public interface productDAO_interface {
	public ProductVO insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(Integer pi_no);
	public ProductVO findByPK(Integer pi_no);
	public ProductVO findByPI_Name(String pi_name);
	public List<ProductVO> getAll();

}
