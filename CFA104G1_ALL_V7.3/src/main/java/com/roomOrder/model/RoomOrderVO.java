package com.roomOrder.model;

import java.sql.Date;

public class RoomOrderVO {
	private Integer room_order_id; 
	private Integer mem_id;
	private Date order_date;
	private Integer room_order_status;
	private Integer room_charge;
	private Integer room_review;
	private Date arrival_date;
	private Date departure_date;
	private Integer num_of_people; 
	private Integer totalrooms;
	private Integer totaldays;
	
	public Integer getTotaldays() {
		return totaldays;
	}
	public void setTotaldays(Integer totaldays) {
		this.totaldays = totaldays;
	}
	public Integer getNum_of_people() {
		return num_of_people;
	}
	public void setNum_of_people(Integer num_of_people) {
		this.num_of_people = num_of_people;
	}
	public Integer getTotalrooms() {
		return totalrooms;
	}
	public void setTotalrooms(Integer totalrooms) {
		this.totalrooms = totalrooms;
	}
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	public Date getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}
	public Integer getRoom_order_id() {
		return room_order_id;
	}
	public void setRoom_order_id(Integer room_order_id) {
		this.room_order_id = room_order_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Integer getRoom_order_status() {
		return room_order_status;
	}
	public void setRoom_order_status(Integer room_order_status) {
		this.room_order_status = room_order_status;
	}
	public Integer getRoom_charge() {
		return room_charge;
	}
	public void setRoom_charge(Integer room_charge) {
		this.room_charge = room_charge;
	}
	public Integer getRoom_review() {
		return room_review;
	}
	public void setRoom_review(Integer room_review) {
		this.room_review = room_review;
	}
	
	

}
