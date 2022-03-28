package com.productImage.model;
import java.util.List;

public class productImageService {
private productImageDAO_interface dao;
	
	public productImageService() {
		dao = new productImageJNDIDAO();
	}
	
	public productImageVO insertProductImage(Integer pi_no, byte[] pim_img) {
		
		productImageVO productImageVO = new productImageVO();
		
		productImageVO.setPi_no(pi_no);
		productImageVO.setPim_img(pim_img);
		productImageVO = dao.insert(productImageVO);
		return productImageVO;
	}

	public productImageVO updateProductImage(Integer pim_no, Integer pi_no, byte[] pim_img) {
		
		productImageVO productImageVO = new productImageVO();
		
		productImageVO.setPim_no(pim_no);
		productImageVO.setPi_no(pi_no);
		productImageVO.setPim_img(pim_img);
		dao.update(productImageVO);
		return productImageVO;
	}

	public void deleteProductImage(Integer pim_no) {
		dao.delete(pim_no);
	}

	public productImageVO getOneProductImage(Integer pim_no) {
		return dao.findByPk(pim_no);
	}

	public List<productImageVO> getAll() {
		return dao.getAll();
	}

}
