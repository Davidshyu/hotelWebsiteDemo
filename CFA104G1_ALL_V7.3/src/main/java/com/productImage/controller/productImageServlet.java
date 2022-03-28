package com.productImage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.productImage.model.productImageService;
import com.productImage.model.productImageVO;

@javax.servlet.annotation.MultipartConfig
public class productImageServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("showImages".equals(action)) {
			Integer pi_no = new Integer(req.getParameter("pi_no"));
			
			productImageService productImageSvc = new productImageService();
			List<productImageVO> productImageVO = productImageSvc.getAll();
			
			List<productImageVO> Images = productImageVO.stream()
																.filter(i -> i.getPi_no().intValue() ==  pi_no.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			req.setAttribute("pi_no", pi_no);  
			
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/productImg/showImages.jsp");	////////////////////////////////
			successView.forward(req, res);
			return;
		}
		
		if("delete".equals(action)) {
			Integer pim_no = new Integer(req.getParameter("pim_no"));
			Integer pi_no = new Integer(req.getParameter("pi_no"));

			System.out.println("pim_no : " + pim_no);
			
			/****1.������****/
			productImageService productImageSvc = new productImageService();
			productImageSvc.deleteProductImage(pim_no);
			
			/****2.��������閰ｇ�蒂撠���req****/
			List<productImageVO> productImageVO = productImageSvc.getAll();
			List<productImageVO> Images = productImageVO.stream()
																.filter(i -> i.getPi_no().intValue() ==  pi_no.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			req.setAttribute("pi_no", pi_no);   //���i_no 摮絲靘����������瘜憓�
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/productImg/showImages.jsp");	//////////////////////////////////
			successView.forward(req, res);
			return;
			
		}
		
		if("insert".equals(action)) {
			System.out.println("enter insert");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer pi_no = new Integer(req.getParameter("pi_no"));
			
			req.setAttribute("pi_no", pi_no);  //���i_no 摮絲靘����������瘜憓�


			productImageService productImageSvc = new productImageService();

			/****1.�憓���****/ 
			Collection<Part> parts = req.getParts();

			String filename = null;
			for(Part part : parts) {
				InputStream in = part.getInputStream();				
				

				if(getFileNameFromPart(part)!=null && part.getContentType()!=null) {	
					filename = getFileNameFromPart(part);
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					productImageSvc.insertProductImage(pi_no, buf);
				}
			}
			
			if(filename == null) {
				errorMsgs.add("隢�����!!");
			}
			
			/****2.�憓�����閰ｇ�蒂撠���req****/
			List<productImageVO> productImageVO = productImageSvc.getAll();
			List<productImageVO> Images = productImageVO.stream()
																.filter(i -> i.getPi_no().intValue() == pi_no.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/productImg/showImages.jsp");	////////////////////
				failView.forward(req, res);
				return;
			}
			
			/****4.嚙踝蕭嚙踝蕭嚙踝蕭V****/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/productImg/showImages.jsp");	/////////////////////
			successView.forward(req, res);
			return;
		}
		
		if("ExportImages".equals(action)) {
			res.setContentType("image/jpeg");
			Integer pim_no= new Integer(req.getParameter("pim_no"));
			productImageService productImageSvc = new productImageService();
			productImageVO productImageVO = productImageSvc.getOneProductImage(pim_no);
			byte[] imgArry = productImageVO.getPim_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();
			return;
		}
		
		if("showShopImage".equals(action)) {
			res.setContentType("image/jpeg");
			
			/****1.嚙踝蕭嚙踝蕭嚙諸潘蕭****/
			Integer pi_no =new Integer(req.getParameter("pi_no"));
			
			/****2.嚙範嚙賠照歹蕭****/
			productImageService productImageSvc = new productImageService();
			List<productImageVO> imageList = productImageSvc.getAll();
			
			/****3.嚙盤嚙緻嚙諉歹蕭****/
			Optional<productImageVO> firstImages = null;
			
			firstImages = imageList.stream()
								   .filter(i -> i.getPi_no().intValue() == pi_no.intValue())
								   .findFirst();
			
			/****4.嚙踝蕭PK嚙踝蕭嚙緻嚙誕歹蕭****/
			productImageVO OutImages = productImageSvc.getOneProductImage(firstImages.get().getPim_no());
			
			/****5.嚙踝蕭X嚙誕歹蕭****/
			byte[] imgArry = OutImages.getPim_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();
			return;
		}
		
	}
	
	// 嚙踝蕭嚙碼嚙磕嚙褒迎蕭嚙褕案名嚙踝蕭 (嚙稽嚙踝蕭API嚙踝蕭嚙踝蕭嚙踝蕭method,嚙課以嚙踝蕭嚙踝蕭嚙諛行撰嚙篇)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 嚙踝蕭嚙調伐蕭
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 嚙踝蕭嚙調伐蕭
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
