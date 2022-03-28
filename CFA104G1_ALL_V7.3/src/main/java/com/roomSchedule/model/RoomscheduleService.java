package com.roomSchedule.model;

import java.sql.Date;
import java.util.List;

import com.roomType.model.RoomTypeVO;

public class RoomscheduleService {
	
	private RoomScheduleDAO_interface dao;
	
	public RoomscheduleService() {
		dao = new RoomScheduleDAO();
	}
	
	public RoomScheduleVO addRoomSchedule(Integer room_type_id,Date room_schedule_date,Integer room_amount,Integer room_rsv_booked) {
		
		RoomScheduleVO roomScheduleVO = new RoomScheduleVO();
		
		roomScheduleVO.setRoom_type_id(room_type_id);
		roomScheduleVO.setRoom_schedule_date(room_schedule_date);
		roomScheduleVO.setRoom_amount(room_amount);
		roomScheduleVO.setRoom_rsv_booked(room_rsv_booked);
		
		dao.insert(roomScheduleVO);
		
		return roomScheduleVO;		
		
	}
	
	
	public RoomScheduleVO updateRoomschedule(Integer room_type_id,Date room_schedule_date,Integer room_amount,Integer room_rsv_booked,Integer room_schedule_id) {
		
		RoomScheduleVO roomScheduleVO = new RoomScheduleVO();
		
		
		roomScheduleVO.setRoom_schedule_id(room_schedule_id);
		roomScheduleVO.setRoom_type_id(room_type_id);
		roomScheduleVO.setRoom_schedule_date(room_schedule_date);
		roomScheduleVO.setRoom_amount(room_amount);
		roomScheduleVO.setRoom_rsv_booked(room_rsv_booked);
		
		dao.update(roomScheduleVO);
		
		return roomScheduleVO;		
	}
        public RoomScheduleVO updateRoomschedulersv (RoomScheduleVO roomScheduleVO,Integer room_rsv_booked) {
		roomScheduleVO.setRoom_rsv_booked(room_rsv_booked);
		dao.update(roomScheduleVO);
		
		return roomScheduleVO;		
	}
	
	public void deleteRoomschedule(Integer room_schedule_id) {
		dao.delete(room_schedule_id);
	}
	
	
	public RoomScheduleVO getOneRoomschedule(Integer room_schedule_id) {
		return dao.findByPrimaryKey(room_schedule_id);
		
	}
	
	public List<RoomScheduleVO> getall(){
		return dao.getAll();
	}
	
	public RoomScheduleVO getOnebyDateandTypeID(Integer room_type_id, Date room_schedule_date) {
		return dao.findByRoomTypeAndDATE(room_type_id, room_schedule_date);
	}
	
	public List<RoomTypeVO> findEmptyRoomType(int totalrooms, int totaldays, Date arrival_date, int num_of_people){
		return dao.findEmptyRoomByDate(totalrooms, totaldays, arrival_date,num_of_people);
	};
	

}
