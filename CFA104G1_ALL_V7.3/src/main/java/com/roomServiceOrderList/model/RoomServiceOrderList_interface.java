package com.roomServiceOrderList.model;

import java.util.List;


public interface RoomServiceOrderList_interface {
	public void insert(RoomServiceOrderListVO roomServicelRoomServiceOrderListVO);
    public void update(RoomServiceOrderListVO roomServicelRoomServiceOrderListVO);
      public void delete(Integer service_order_id);
    public RoomServiceOrderListVO findByPrimaryKey(Integer service_order_id);
    public List<RoomServiceOrderListVO> getAll();
  //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insert2 (RoomServiceOrderListVO rsolVO , java.sql.Connection con);
	public List<RoomServiceOrderListVO> getAllListByPK(Integer service_order_id);
}
