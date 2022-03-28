package com.roomOrder.model;

import java.sql.Date;
import java.util.List;

public class RoomOrderService {
	
	private RoomOrderDAO_interface dao;
	
	public RoomOrderService() {
		dao = new RoomOrderDAO();
	}
	
	public RoomOrderVO addRoomOrder( Integer mem_id,Date order_date,Integer room_order_status,
			Integer room_charge, Integer room_review,Date arrival_date ,Date departure_date) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		
		roomOrderVO.setMem_id(mem_id);
		roomOrderVO.setOrder_date(order_date);
		roomOrderVO.setRoom_order_status(room_order_status);
		roomOrderVO.setRoom_charge(room_charge);
		roomOrderVO.setRoom_review(room_review);
		roomOrderVO.setArrival_date(arrival_date);
		roomOrderVO.setDeparture_date(departure_date);
		
		dao.insert(roomOrderVO);
		
		return roomOrderVO;		
	}
	
	public RoomOrderVO updateRoomOder(Integer room_order_id, Integer mem_id, Date order_date,Integer room_order_status, Integer room_charge,Integer room_review,Date arrival_date ,Date departure_date) {
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setMem_id(mem_id);
		roomOrderVO.setOrder_date(order_date);
		roomOrderVO.setRoom_order_status(room_order_status);
		roomOrderVO.setRoom_charge(room_charge);
		roomOrderVO.setRoom_review(room_review);
		roomOrderVO.setArrival_date(arrival_date);
		roomOrderVO.setDeparture_date(departure_date);
		roomOrderVO.setRoom_order_id(room_order_id);
		
		dao.update(roomOrderVO);
		return roomOrderVO;
		
	}
	
	public void deleteRoomOrder(Integer room_order_id) {
		dao.delete(room_order_id);		
	} 
	
	public RoomOrderVO getOneRoomOrder(Integer room_order_id ) {
		return dao.findByPrimaryKey(room_order_id);		
	}
	
	public List<RoomOrderVO> getall(){
		return dao.getAll();
	}
	

	public List<RoomOrderVO> getToday(){
		return dao.getToday();
	}

	public RoomOrderVO getRoomOrderbyMAXID() {
		return dao.findByMAXOrderID();		
	}
	

	
	public List<RoomOrderVO> getCheckinRoom(){
		return dao.getCheckinRoom();
	}
	
	public RoomOrderVO checkIn(Integer room_order_id) {
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setRoom_order_id(room_order_id);
		
		dao.checkIn(roomOrderVO);
		return roomOrderVO;
	}
	
	public RoomOrderVO checkOut(Integer room_order_id) {
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setRoom_order_id(room_order_id);
		
		dao.checkOut(roomOrderVO);
		return roomOrderVO;
	}
	
	public List<RoomOrderVO> getRoomOrderByMemId(Integer mem_id){
		return dao.getAllByMemId(mem_id);
	}

}
