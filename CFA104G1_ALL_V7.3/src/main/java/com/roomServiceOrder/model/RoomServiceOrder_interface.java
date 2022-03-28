package com.roomServiceOrder.model;

import java.util.List;

import com.roomOrderList.model.RoomOrderListVO;
import com.roomServiceOrder.model.*;
import com.roomServiceOrderList.model.*;

public interface RoomServiceOrder_interface {
	public void insert(RoomServiceOrderVO roomServiceOrderVO);
    public void update(RoomServiceOrderVO roomServiceOrderVO);
    public void delete(Integer service_order_id);
    public RoomServiceOrderVO findByPrimaryKey(Integer service_order_id);
    public List<RoomServiceOrderVO> getAll();
    public void insertWithOrderList(RoomServiceOrderVO rsoVO , List<RoomServiceOrderListVO> list , RoomOrderListVO RoomOrderListVO);
    public RoomServiceOrderVO checkout(RoomServiceOrderVO roomServiceOrderVO);
    public List<RoomServiceOrderVO> getAllOrderByPK(Integer room_id);
}
