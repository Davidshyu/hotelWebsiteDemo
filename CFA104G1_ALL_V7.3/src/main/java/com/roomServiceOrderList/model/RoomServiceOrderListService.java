package com.roomServiceOrderList.model;

import java.util.List;

import com.roomServiceOrder.model.RoomServiceOrderVO;


public class RoomServiceOrderListService {
	private RoomServiceOrderList_interface dao;

	public RoomServiceOrderListService() {
		dao = new RoomServiceOrderListJNDIDAO();
	}

	public RoomServiceOrderListVO addRoomServiceOrderList( Integer meal_id,Integer meal_price,Integer meal_quantity, Integer meal_total) {

		RoomServiceOrderListVO RoomServiceOrderListVO = new RoomServiceOrderListVO();

		RoomServiceOrderListVO.setMeal_id(meal_id);
		RoomServiceOrderListVO.setMeal_price(meal_price);
		RoomServiceOrderListVO.setMeal_quantity(meal_quantity);
		RoomServiceOrderListVO.setMeal_total(meal_total);
		dao.insert(RoomServiceOrderListVO);

		return RoomServiceOrderListVO;
	}

	public RoomServiceOrderListVO updateRoomServiceOrderList(Integer service_order_id,Integer meal_id,Integer meal_price,Integer meal_quantity, Integer meal_total) {

		RoomServiceOrderListVO RoomServiceOrderListVO = new RoomServiceOrderListVO();

		RoomServiceOrderListVO.setService_order_id(service_order_id);
		RoomServiceOrderListVO.setMeal_id(meal_id);
		RoomServiceOrderListVO.setMeal_price(meal_price);
		RoomServiceOrderListVO.setMeal_quantity(meal_quantity);
		RoomServiceOrderListVO.setMeal_total(meal_total);
		dao.update(RoomServiceOrderListVO);

		return RoomServiceOrderListVO;
	}

	public void deleteRoomServiceOrderList(Integer service_order_id) {
		dao.delete(service_order_id);
	}

	public RoomServiceOrderListVO getOneRoomServiceOrderList(Integer service_order_id) {
		return dao.findByPrimaryKey(service_order_id);
	}

	public List<RoomServiceOrderListVO> getAll() {
		return dao.getAll();
	}
	public void insert2 (RoomServiceOrderListVO rsolVO , java.sql.Connection con) {
		dao.insert2(rsolVO, con);
	}
	public List<RoomServiceOrderListVO> getAllListByPK(Integer service_order_id){
		return dao.getAllListByPK(service_order_id);
	}
}
