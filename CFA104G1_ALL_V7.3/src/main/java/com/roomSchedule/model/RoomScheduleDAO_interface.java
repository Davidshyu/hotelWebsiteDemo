package com.roomSchedule.model;

import java.sql.Date;
import java.util.List;

import com.roomType.model.RoomTypeVO;


public interface RoomScheduleDAO_interface {
	public void insert(RoomScheduleVO room_scheduleVO);
	public void update(RoomScheduleVO room_scheduleVO);
	public void delete(Integer room_schedule_id);
	public RoomScheduleVO findByPrimaryKey(Integer room_schedule_id);
	public List<RoomScheduleVO> getAll();
	public RoomScheduleVO findByRoomTypeAndDATE(Integer room_type_id, Date room_schedule_date);
	public List<RoomTypeVO> findEmptyRoomByDate (int totalrooms, int totaldays, Date arrival_date,int num_of_people);

}
