package com.productImage.model;

import java.util.List;

public interface productImageDAO_interface {
	public productImageVO insert(productImageVO productImage);
	public void update(productImageVO productImage);
	public void delete(Integer pim_no);
	public productImageVO findByPk(Integer pim_no);
	public List<productImageVO> getAll();

}
