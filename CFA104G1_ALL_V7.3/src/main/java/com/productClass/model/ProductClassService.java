package com.productClass.model;
import java.util.List;

public class ProductClassService {
private productClassDAO_interface dao;
	
	public ProductClassService() {
		dao = new productClassJNDIDAO();
	}

	public ProductClassVO addProductClass(String pc_name) {
		
		ProductClassVO productClassVO = new ProductClassVO();
		productClassVO.setPcName(pc_name);
		productClassVO = dao.insert(productClassVO);
		return productClassVO;
	}

	public ProductClassVO updateProductClass(Integer pc_no, String pc_name) {
		ProductClassVO productClassVO = new ProductClassVO();
		productClassVO.setPcNo(pc_no);
		productClassVO.setPcName(pc_name);
		dao.update(productClassVO);
		return productClassVO;
	}

	public void deleteProductClass(Integer pc_no) {
		dao.delete(pc_no);
	}

	public ProductClassVO getOneProductClass(Integer pc_no) {
		return dao.findByPK(pc_no);
	}

	public List<ProductClassVO> getAll() {
		return dao.getAll();
	}
}
