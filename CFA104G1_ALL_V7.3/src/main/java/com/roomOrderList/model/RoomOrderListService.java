package com.roomOrderList.model;

import java.sql.Date;
import java.util.List;

public class RoomOrderListService {
	
	private RoomOrderListDAO_interface dao;
	
	public RoomOrderListService() {
		dao = new RoomOrderListDAO();
	}
	
	public RoomOrderListVO addRoom_order_list(Integer room_id,Integer room_order_id,Integer number_of_people,
			String special_req,Integer room_price,Integer service_order_id,Integer room_type_id) {
		RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
		
		roomOrderListVO.setRoom_id(room_id);
		roomOrderListVO.setRoom_order_id(room_order_id);
		roomOrderListVO.setNumber_of_people(number_of_people);
		roomOrderListVO.setSpecial_req(special_req);
		roomOrderListVO.setRoom_price(room_price);
		roomOrderListVO.setService_order_id(service_order_id);
		roomOrderListVO.setRoom_type_id(room_type_id);
		
		dao.insert(roomOrderListVO);
		
		return roomOrderListVO;		
	}
	
	public RoomOrderListVO updateRoom_order_list(Integer room_order_list_id,Integer room_id,Integer room_order_id,Integer number_of_people,
			String special_req,Integer room_price,Integer service_order_id,Integer room_type_id) {
		RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
		
		roomOrderListVO.setRoom_order_list_id(room_order_list_id);
		roomOrderListVO.setRoom_id(room_id);
		roomOrderListVO.setRoom_order_id(room_order_id);
		roomOrderListVO.setNumber_of_people(number_of_people);
		roomOrderListVO.setSpecial_req(special_req);
		roomOrderListVO.setRoom_price(room_price);
		roomOrderListVO.setService_order_id(service_order_id);
		roomOrderListVO.setRoom_type_id(room_type_id);
		
		dao.update(roomOrderListVO);
	
		return roomOrderListVO;		
	}
	
	public void deleteRoom_order_list(Integer room_order_list_id) {
		dao.delete(room_order_list_id);
	}
	
	public RoomOrderListVO getOneRoom_order_list(Integer room_order_list_id) {
		return dao.findByPrimaryKey(room_order_list_id);
	}
	
	public List<RoomOrderListVO> getall(){
		return dao.getAll();
	}

	public RoomOrderListVO backUpdateRoom_order_list(Integer room_order_list_id,String special_req) {
		RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
		
		roomOrderListVO.setRoom_order_list_id(room_order_list_id);
		roomOrderListVO.setSpecial_req(special_req);
		
		dao.backUpdate(roomOrderListVO);
	
		return roomOrderListVO;		
	}
	
	public List<RoomOrderListVO> getOrderId(Integer room_order_id){
		return dao.getOderId(room_order_id);
	}
	
	public RoomOrderListVO checkUpdateRoomID(Integer room_order_list_id,Integer room_id) {
		RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
		
		roomOrderListVO.setRoom_order_list_id(room_order_list_id);
		roomOrderListVO.setRoom_id(room_id);
		
		dao.checkINUpdate(roomOrderListVO);
	
		return roomOrderListVO;		
	}
	
	public RoomOrderListVO updateservice(Integer room_id, Integer service_order_id) {
		RoomOrderListVO RoomOrderListVO = new RoomOrderListVO();
		
		RoomOrderListVO.setRoom_id(room_id);
		RoomOrderListVO.setService_order_id(service_order_id);
		dao.updateservice(RoomOrderListVO);
		return RoomOrderListVO;
	}	
}
