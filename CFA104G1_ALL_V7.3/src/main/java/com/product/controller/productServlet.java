package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.productImage.model.productImageService;
import com.productImage.model.productImageVO;
import com.product.model.*;

import oracle.net.aso.i;
import oracle.net.aso.k;
import oracle.net.aso.l;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productImage.model.productImageService;
import com.productImage.model.productImageVO;
import com.productClass.model.*;
import com.productImage.*;

@javax.servlet.annotation.MultipartConfig
public class productServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {	
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("Action : " + action);

		
if("insert".equals(action)) {  // 來自addproduct.jsp的請求   //2個jsp： addproduct.jsp & listAllproduct.jsp
	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String name = req.getParameter("pi_name");
				if(name == null || name.trim().length()==0) {
					errorMsgs.add("請輸入商品名稱!!");
				}
				
				
				//此欄位無須檢查
				Integer productClass = new Integer(req.getParameter("pc_no").trim());
			
				
				String productContent = req.getParameter("pi_content");
				if(productContent == null || productContent.trim().length()==0) {
					errorMsgs.add("請輸入商品內容!!");
				}
				
				
				Integer productPrice=null;
				try {
					productPrice = new Integer(req.getParameter("pi_pri").trim());
				} catch (NumberFormatException e) {
					productPrice = 0;
					errorMsgs.add("商品價格請輸入數字!!");
				}
				
				Integer productStock=null;
				try {
					productStock = new Integer(req.getParameter("pi_stock").trim());
				} catch (NumberFormatException e) {
					productStock = 0;
					errorMsgs.add("商品庫存請輸入數字!!");
				}

				//此欄位無須檢查
				String productSta = req.getParameter("pi_sta");
				
				ProductVO productVO1 = new ProductVO();
				productVO1.setPiName(name);
				productVO1.setPcNo(productClass);
				productVO1.setPiContent(productContent);
				productVO1.setPiPri(productPrice);
				productVO1.setPiStock(productStock);
				productVO1.setPiSta(productSta);
				
				// Send the use back to the form, if there were errors
				req.setAttribute("productVO1", productVO1); // 含有輸入格式錯誤的empVO物件,也存入req
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failView.forward(req, res);
					return;
				}
			
				/***************************2.開始新增商品***************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.insertProductInform(name, productClass, productContent, productPrice, productStock, productSta);
				System.out.println("新增商品成功!!!");
				

				/***************************3.新增商品照片***************************************/
				Collection<Part> parts = req.getParts();
				System.out.println("total parts : " + parts.size());

				for(Part part : parts) {
					InputStream in = part.getInputStream();
					if(in.available()!=0 && part.getContentType()!=null) {
						productImageService productImageSvc = new productImageService();
						byte[] img = new byte[in.available()];
						in.read(img);
						in.close();
						productImageSvc.insertProductImage(productVO.getPiNo(), img);
					}
				}

				/***************************4.新增完成，頁面轉向***************************************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				successView.forward(req, res);
				return;
				
			} catch (Exception e) {
				/****5.新增失敗****/
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failView.forward(req, res);
					return;
				}
			}
			
		}
		
if("getOneForDiplay".equals(action)){  //jsp：listOneproduct
			System.out.println("Enter getOneForDiplay!!!"); 
			/****1.取值****/
			Integer pi_no = new Integer(req.getParameter("pi_no"));

			/****2.從資料庫取出****/
			ProductService productSvc = new ProductService();
			ProductVO productVO1  = productSvc.getOneProductInform(pi_no);
			
			/***取出圖片***/
			productImageService productImageSvc = new productImageService();
			List<productImageVO> imagesList = productImageSvc.getAll();
			
			List<productImageVO> filterImagesList= imagesList.stream()
																 .filter(i -> i.getPi_no().intValue() == pi_no.intValue())
																 .collect(Collectors.toList());
			
			System.out.println("filterImagesList:" + filterImagesList.isEmpty());
			
			req.setAttribute("filterImagesList", filterImagesList);
			req.setAttribute("productVO", productVO1);
			
			/****3.設定燈箱顯示****/
//			boolean openLightBox = true;
//			req.setAttribute("openLightBox", openLightBox);
//			
//			System.out.println("openLightBox : " + openLightBox);

			/****4.頁面轉向 ****/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listOneProductInfo.jsp");
			successView.forward(req, res);
			return;
			
		}
		
if("getOneForUpdate".equals(action)) {  // jsp:updateproduct
			
			System.out.println("enter getOneForUpdate!!");
			/****1.接收請求參數****/
			Integer pi_no = new Integer(req.getParameter("pi_no"));
			/****2.取得資料****/
			ProductService productSvc = new ProductService();
			ProductVO productVO1  = productSvc.getOneProductInform(pi_no);
			req.setAttribute("productVO", productVO1);
			/****3.轉向至update頁面****/
			System.out.println("頁面轉向!!!");
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/updateProductInfo.jsp");
			successView.forward(req, res);
			return;
			
		}
		
if("update".equals(action)) { //jsp:updateproduct listAllproduct(2) updateproduct(2)

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.檢查輸入變數******/
			try {
				String name = req.getParameter("pi_name");
				if(name == null || name.trim().length()==0) {
					errorMsgs.add("請輸入商品名稱!!");
				}
				
				Integer pi_no = new Integer(req.getParameter("pi_no"));
				
				//此欄位無須檢查
				Integer productClass = new Integer(req.getParameter("pc_no").trim());
			
				String productContent = req.getParameter("pi_content");
				if(productContent == null || productContent.trim().length()==0) {
					errorMsgs.add("請輸入商品內容!!");
				}
				
				
				Integer productPrice =null;
				try {
					productPrice = new Integer(req.getParameter("pi_pri").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格請輸入數字!!");
				}
				
				Integer productStock =null;
				try {
					productStock = new Integer(req.getParameter("pi_stock").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品庫存請輸入數字!!");
				}

				//此欄位無須檢查
				String productSta = req.getParameter("pi_sta");
				
				ProductVO productVO1 = new ProductVO();
				
				productVO1.setPiNo(pi_no);
				productVO1.setPiName(name);
				productVO1.setPcNo(productClass);
				productVO1.setPiContent(productContent);
				productVO1.setPiPri(productPrice);
				productVO1.setPiStock(productStock);
				productVO1.setPiSta(productSta);
				
				req.setAttribute("productVO1", productVO1);
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/product/updateProductInfo.jsp");
					failView.forward(req, res);
					return;
				}
				
				/****2.開始新增商品****/
				ProductService productSvc = new ProductService();
				productSvc.updateProductInform(pi_no, name, productClass, productContent, productPrice, productStock, productSta);
				System.out.println("修改商品成功!!!");
				
				/****3.新增完成，頁面轉向***/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("更新失敗!!!");
				/****4.更新失敗****/
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/product/updateProductInfo.jsp");
					failView.forward(req, res);
					return;
				}
			}
		}
		
if("FindByPK".equals(action)) {		//jsp:select_page afterSearch
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			try {
				
			/****1.檢查輸入參數****/
			Integer pi_no = new Integer(req.getParameter("pi_no"));
			
			
			/****2.查詢資料****/
			ProductService productSvc = new ProductService();
			ProductVO productVO1 = productSvc.getOneProductInform(pi_no);
			
			if(productVO1==null) {
				errorMsgs.add("查無商品");	
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/product/select_Page.jsp");
				failView.forward(req, res);
				return;
			}
			
			//配合前台頁面EL取值，將VO存入List中
			List<ProductVO> productVOs = new LinkedList<ProductVO>();
			productVOs.add(productVO1);
			req.setAttribute("productVOs", productVOs);	
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
								req.getRequestDispatcher("/back-end/product/afterSearch.jsp");
			successView.forward(req, res);
			return;
			
			}catch (NumberFormatException e) {
				errorMsgs.add("請輸入數字!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/product/select_Page.jsp");
				failView.forward(req, res);
				return;
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/product/select_Page.jsp");
				failView.forward(req, res);
				return;
			}	
		}
		
if("ExportImages".equals(action)) {
			res.setContentType("image/jpeg");
			Integer pim_no= new Integer(req.getParameter("pim_no"));
			System.out.println("Integer >> pim_no : " + pim_no);
			productImageService productImagesSvc = new productImageService();
			productImageVO productImageVO1 = productImagesSvc.getOneProductImage(pim_no);
			byte[] imgArry = productImageVO1.getPim_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();	
			return;
		}
		
if("showAllProduct".equals(action)) { //jsp:productShopPage
			HttpSession session = req.getSession();
			Integer global_pc_no = (Integer) session.getAttribute("global_pc_no");
						
			/****1.取得變數****/
			try {
				Integer pc_no = new Integer( req.getParameter("pc_no") );
				global_pc_no = pc_no;	
			} 
			//若沒有取得pc_no
			catch (Exception e) {
				if(global_pc_no == null)
					//預設為顯示全部商品
					global_pc_no = 0;
			}
			session.setAttribute("global_pc_no", global_pc_no);

			/****2.透過searchProductList()取得該類別的商品列表****/
			List<ProductVO> afterFiterProduct = searchProductList(global_pc_no);
					
			req.setAttribute("afterFiterProduct", afterFiterProduct);
						
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/front-end/Product/productShopPage.jsp");
			successView.forward(req, res);
			return;
		}
		
if("showProductDetail".equals(action)) {			
			/****1.取得參數****/
			Integer pi_no = new Integer(req.getParameter("pi_no"));
			
			/****2.開始查詢商品****/
			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProductInform(pi_no);
			
			req.setAttribute("productVO", productVO);
			
			/****3.開始查詢商品圖片****/
			productImageService productImageSvc = new productImageService();
			List<productImageVO> productImageVOs = productImageSvc.getAll();
			
			List<productImageVO> afterFilterImages = null;
			afterFilterImages = productImageVOs.stream()
							 					   .filter(i -> i.getPi_no().equals(pi_no))
							 					   .collect(Collectors.toList());
			req.setAttribute("afterFilterImages", afterFilterImages);
			
			/****4.頁面轉向****/
			RequestDispatcher successView = 
					 req.getRequestDispatcher("/front-end/Product/productDetail.jsp");
			successView.forward(req, res);
			return;
		}
		
		
		
/*購物車>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>購物車*/
if("cart".equals(action)) {

			HttpSession session = req.getSession();
			Vector<ProductVO> productList = (Vector<ProductVO>) session.getAttribute("shoppingCart");
			String cart_action = req.getParameter("cart_action");
			/*使用HashMap儲存商品數量 (key:pi_no商品編號, value: quantity購買數量)*/
			Map<Integer, Integer> Quamap = (Map<Integer, Integer>) session.getAttribute("Quamap");
			String forwardStr = null;
			
if(!cart_action.equals("checkout")) {
				
				//刪除購物車商品
if(cart_action.equals("delete")) { //jsp:cart
					String delete = req.getParameter("index");
					int del = Integer.parseInt(delete);
					productList.removeElementAt(del);
					//刪除完商品回到購物車頁面
					forwardStr = "/front-end/Product/cart.jsp";
				}
				//刪除購物車中所有商品
				else if(cart_action.equals("deleteAll")) {
					Quamap.clear();
					productList.removeAllElements();
					//刪除完商品回到購物車頁面
					forwardStr = "/front-end/Product/cart.jsp";
				}
				
				//新增商品到購物車中
				else if(cart_action.equals("add")) {
					Integer pi_no = new Integer(req.getParameter("pi_no"));
					Integer quantity = new Integer(req.getParameter("quantity"));
					boolean match = false;
					
					/*從新將商品查詢出來並存入req，forward回原本商品頁面才有資料 >>>>*/
					/****開始查詢商品****/
					ProductService productSvc = new ProductService();
					ProductVO productVO = productSvc.getOneProductInform(pi_no);
					req.setAttribute("productVO", productVO);
										
					/****開始查詢商品圖片****/
					productImageService productImageSvc = new productImageService();
					List<productImageVO> productImageVOs = productImageSvc.getAll();
					
					List<productImageVO> afterFilterImages = null;
					afterFilterImages = productImageVOs.stream()
									 					   .filter(i -> i.getPi_no().equals(pi_no))
									 					   .collect(Collectors.toList());
					req.setAttribute("afterFilterImages", afterFilterImages);
					/*<<<<從新將商品查詢出來並存入req，forward回原本商品頁面才有資料 */
					
					
					//新增第一個商品到購物車
					if(productList==null) {
						productList = new Vector<ProductVO>();
						productList.add(productVO);
						Quamap = new HashMap<Integer, Integer>();
						Quamap.put(pi_no, quantity);
					}else {
						//檢查是否為重複的商品
						for(int i=0 ; i<productList.size() ; i++ ) {
							ProductVO productVO2 = productList.get(i);
							if(productVO2.getPiNo().equals(productVO.getPiNo())) {
								/*直接將原本的數量覆蓋掉*/
								Quamap.put(pi_no, quantity);
								match = true;
							}
						}
						
						//如果不是重複商品則新增
						if(!match) {
							productList.add(productVO);
							Quamap.put(pi_no, quantity);
						}
					}
					//新增完商品回到商品頁面
					forwardStr = "/front-end/Product/productDetail.jsp";
				}
				
				/*計算購物車內商品價格*/
				int subTotal = 0;
				for (ProductVO product : productList) {
					subTotal += product.getPiPri() * Quamap.get(product.getPiNo());
				}
				Quamap.put(999, subTotal);
				
				/*計算購物車內商品數量*/
				Integer amount = (int) productList.size();
				Quamap.put(998, amount);

				session.setAttribute("Quamap", Quamap);
				session.setAttribute("shoppingCart", productList);
				RequestDispatcher successVisw = 
						req.getRequestDispatcher(forwardStr);
				successVisw.forward(req, res);
				return;
			}
		}
		/*購物車<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<購物車*/
		
//		//依類別編號查詢
//		if("findBySpcNo".equals(action)) {
//			
//			/****1.取得參數****/
//			Integer spc_no = new Integer(req.getParameter("spc_no"));
//			
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpc_no().equals(spc_no))
//																	 .collect(Collectors.toList());
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back-end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//			
//		}
//		
//		//依商品名稱查詢
//		if("findBySpiName".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/****1.取得參數****/
//			String spi_name = req.getParameter("spi_name");
//
//			if(spi_name.trim().length()==0) {
//				errorMsgs.add("請輸入商品名稱!!");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back-end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpi_name()   /*不區分大小寫*/
//																			 	   .toLowerCase()
//																			 	   .contains(spi_name.toLowerCase()))
//																	 .collect(Collectors.toList());
//			if(productInformVOs.size()==0) {
//				errorMsgs.add("查無商品");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back-end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back-end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//		}
//		
//		//依商品狀態查詢
//		if("findBySpiSta".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/****1.取得參數****/
//			String spi_sta = req.getParameter("spi_sta");
//						
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpi_sta().equals(spi_sta))
//																	 .collect(Collectors.toList());
//			if(productInformVOs.size()==0) {
//				errorMsgs.add("查無商品");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back-end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back-end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//		}
		
		//複合查詢
if("compoundQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);		
			
			/****1.取得參數****/
			String pi_no_str = req.getParameter("pi_no");
			Integer pi_no = null;
			if(pi_no_str.trim().length() != 0) {
				try {
					pi_no = new Integer(req.getParameter("pi_no"));
				} catch (NumberFormatException e) {
					errorMsgs.add("商品編號請輸入數字");
					
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/product/select_Page.jsp");
					failView.forward(req, res);
					return;
				}
			}

			String pi_name = req.getParameter("pi_name");
			String pc_no = req.getParameter("pc_no");
			String pi_sta = req.getParameter("pi_sta");
			
			Map<String, String> condititon = new HashMap<String, String>();
			condititon.put("pi_no_str", pi_no_str);
			condititon.put("pi_name", pi_name);
			condititon.put("pc_no", pc_no);
			condititon.put("pi_sta", pi_sta);
			
			/****2.開始查詢****/
			ProductService productSvc = new ProductService();
			List<ProductVO> productVOs = productSvc.getAll();
			Set<String> key = condititon.keySet();
			
			key.stream()
			   .forEach(k -> compoundQuery(productVOs, k, condititon.get(k)));
			
if(productVOs.isEmpty()) {
				errorMsgs.add("查無商品!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/product/select_Page.jsp");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("productVOs", productVOs);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/product/afterSearch.jsp");
			successView.forward(req, res);
			return;
		}
	}
	
	/*此方法根據輸入的商品類別編號搜尋並過濾，回傳過濾後的商品列表*/
	private List<ProductVO> searchProductList(Integer pc_no) {
		List<ProductVO> afterFiterProduct = null;
				
		/****1.開始查詢****/
		ProductService productSvc = new ProductService();
		List<ProductVO> productVOs = productSvc.getAll();
				
		/****2.過濾商品****/
		if(pc_no == 0) {
			/****pc_no=0 表示要查詢所有的商品，並去除狀態為"下架"或庫存為0的商品****/
			afterFiterProduct = productVOs.stream()
												.filter(i -> i.getPiStock().intValue() > 0)
												.filter(i -> i.getPiSta().equals("上架中"))
												.collect(Collectors.toList());
		}else {
			/****依據pc_no，開始過濾商品，並去除狀態為"下架"或庫存為0的商品****/
			afterFiterProduct = productVOs.stream()
												.filter(i -> i.getPcNo().equals(pc_no))
												.filter(i -> i.getPiStock().intValue() > 0)
												.filter(i -> i.getPiSta().equals("上架中"))
												.collect(Collectors.toList());	
		}
		return afterFiterProduct;
	}
	
	//此方法根據輸入的key和value過濾productVOs內的VO
	private void compoundQuery (List<ProductVO> productVOs, String key ,String value) {
		List<ProductVO> productVO_after = null;
		
		if(value.trim().length()!=0) {
			switch (key) {
			case "pi_no_str":
		
				productVO_after = productVOs.stream()
														.filter(i -> i.getPiNo().equals(new Integer(value)))
														.collect(Collectors.toList());
				productVOs.clear();				
				productVOs.addAll(productVO_after);
				break;
			case "pi_name":
				productVO_after = productVOs.stream()
														.filter(i -> i.getPiName()	/*不區分大小寫*/
																	  .toLowerCase()
																	  .contains(value.toLowerCase()))
														.collect(Collectors.toList());
				productVOs.clear();
				productVOs.addAll(productVO_after);
				break;
			case "pc_no":
				productVO_after = productVOs.stream()
														.filter(i -> i.getPcNo().equals(new Integer(value)))
														.collect(Collectors.toList());
				productVOs.clear();
				productVOs.addAll(productVO_after);
				break;
			case "pi_sta":
				productVO_after = productVOs.stream()
														.filter(i -> i.getPiSta().equals(value))
														.collect(Collectors.toList());
				productVOs.clear();
				productVOs.addAll(productVO_after);
				break;
			default:
				break;
			}
		}
	}
}
