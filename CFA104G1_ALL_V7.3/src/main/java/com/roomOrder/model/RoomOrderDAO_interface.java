package com.roomOrder.model;

import java.util.List;

public interface RoomOrderDAO_interface {
	public void insert(RoomOrderVO roomOrderVO);
	public void update(RoomOrderVO roomOrderVO);
	public void delete(Integer room_order_id);
	public RoomOrderVO findByPrimaryKey(Integer room_order_id);
	public List<RoomOrderVO> getAll();
	public List<RoomOrderVO> getToday();
	public List<RoomOrderVO> getCheckinRoom();
	public void checkIn(RoomOrderVO roomOrderVO);
	public void checkOut(RoomOrderVO roomOrderVO);
	public RoomOrderVO findByMAXOrderID();
	public List<RoomOrderVO> getAllByMemId(Integer mem_id);

}
