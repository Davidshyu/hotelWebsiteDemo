package com.roomServiceOrder.model;

import java.sql.Date;
import java.util.List;

import com.roomOrderList.model.RoomOrderListVO;
import com.roomServiceOrderList.model.RoomServiceOrderListVO;

public class RoomServiceOrderService {

	private RoomServiceOrder_interface dao;

	public RoomServiceOrderService() {
		dao = new RoomServiceOrderJNDIDAO();
	}

	public RoomServiceOrderVO addRoomServiceOrder(Integer room_id, boolean service_order_status,Date service_order_date) {

		RoomServiceOrderVO RoomServiceOrderVO = new RoomServiceOrderVO();

		RoomServiceOrderVO.setRoom_id(room_id);
		RoomServiceOrderVO.setService_order_status(service_order_status);
		RoomServiceOrderVO.setService_order_date(service_order_date);
		dao.insert(RoomServiceOrderVO);

		return RoomServiceOrderVO;
	}

	public RoomServiceOrderVO updateRoomServiceOrder(Integer service_order_id,Integer room_id, boolean service_order_status,Date service_order_date) {

		RoomServiceOrderVO RoomServiceOrderVO = new RoomServiceOrderVO();

		RoomServiceOrderVO.setService_order_id(service_order_id);
		RoomServiceOrderVO.setRoom_id(room_id);
		RoomServiceOrderVO.setService_order_status(service_order_status);
		RoomServiceOrderVO.setService_order_date(service_order_date);
		dao.update(RoomServiceOrderVO);

		return RoomServiceOrderVO;
	}

	public void deleteRoomServiceOrder(Integer service_order_id) {
		dao.delete(service_order_id);
	}

	public RoomServiceOrderVO getOneRoomServiceOrder(Integer service_order_id) {
		return dao.findByPrimaryKey(service_order_id);
	}

	public List<RoomServiceOrderVO> getAll() {
		return dao.getAll();
	}
	public RoomServiceOrderVO insertWithOrderList(Integer room_id, boolean service_order_status,Date service_order_date , List<RoomServiceOrderListVO> list , RoomOrderListVO RoomOrderListVO) {
		RoomServiceOrderVO RoomServiceOrderVO = new RoomServiceOrderVO();
		RoomServiceOrderVO.setRoom_id(room_id);
		RoomServiceOrderVO.setService_order_status(service_order_status);
		RoomServiceOrderVO.setService_order_date(service_order_date);
		dao.insertWithOrderList(RoomServiceOrderVO, list , RoomOrderListVO);
		return RoomServiceOrderVO;
	}
	
	public RoomServiceOrderVO checkout(Integer service_order_id, boolean service_order_status) {

		RoomServiceOrderVO RoomServiceOrderVO = new RoomServiceOrderVO();

		RoomServiceOrderVO.setService_order_id(service_order_id);
		RoomServiceOrderVO.setService_order_status(service_order_status);
		dao.checkout(RoomServiceOrderVO);

		return RoomServiceOrderVO;
	}
	
	public List<RoomServiceOrderVO> getAllOrderByPK(Integer room_id){
		return dao.getAllOrderByPK(room_id);
	}
}
