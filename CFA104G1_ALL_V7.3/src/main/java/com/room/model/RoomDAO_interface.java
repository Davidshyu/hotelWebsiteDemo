package com.room.model;

import java.util.List;

public interface RoomDAO_interface {
	public void insert(RoomVO roomVo);
	public void update(RoomVO roomVo);
	public void delete(Integer room_id);
	public RoomVO findByPK(Integer room_id);
	public List<RoomVO> getAll();
	public List<RoomVO> getOneTypeAll(Integer room_type_id);
	public void checkINStatus(RoomVO roomVo);
	public void checkOutStatus(RoomVO roomVo);
	public void clean(RoomVO roomVO);

}
