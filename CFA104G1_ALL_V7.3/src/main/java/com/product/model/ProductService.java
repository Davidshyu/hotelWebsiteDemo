package com.product.model;
import java.util.List;

public class ProductService {
private productDAO_interface dao;
	
	public ProductService() {
		dao = new productJNDIDAO();
	}
	
	public ProductVO insertProductInform(String pi_name, Integer pc_no, String pi_content, Integer pi_pri, Integer pi_stock, String pi_sta) {
		ProductVO productVO = new ProductVO();
		
		productVO.setPiName(pi_name);
		productVO.setPcNo(pc_no);
		productVO.setPiContent(pi_content);
		productVO.setPiPri(pi_pri);
		productVO.setPiStock(pi_stock);
		productVO.setPiSta(pi_sta);
		productVO = dao.insert(productVO);
		
		return productVO;
	}

	public ProductVO updateProductInform(Integer pi_no, String pi_name, Integer pc_no, String pi_content, Integer pi_pri, 
						Integer pi_stock, String pi_sta) {
		ProductVO productVO = new ProductVO();
		
		productVO.setPiNo(pi_no);
		productVO.setPiName(pi_name);
		productVO.setPcNo(pc_no);
		productVO.setPiContent(pi_content);
		productVO.setPiPri(pi_pri);
		productVO.setPiStock(pi_stock);
		productVO.setPiSta(pi_sta);
		dao.update(productVO);
		
		return productVO;
	}

	public void deleteProductInform(Integer pi_no) {
		dao.delete(pi_no);
	}

	public ProductVO getOneProductInform(Integer pi_no) {
		return dao.findByPK(pi_no);
	}

	public ProductVO getOneProductInformByName(String pi_name) {
		return dao.findByPI_Name(pi_name);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	

}
