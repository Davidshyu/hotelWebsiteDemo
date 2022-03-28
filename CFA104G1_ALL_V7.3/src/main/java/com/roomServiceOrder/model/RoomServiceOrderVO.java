package com.roomServiceOrder.model;
import java.sql.Date;

public class RoomServiceOrderVO implements java.io.Serializable{
	private Integer service_order_id;
	private Integer room_id;
	private boolean service_order_status;
	private Date service_order_date;
	public Integer getService_order_id() {
		return service_order_id;
	}
	public void setService_order_id(Integer service_order_id) {
		this.service_order_id = service_order_id;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public boolean getService_order_status() {
		return service_order_status;
	}
	public void setService_order_status(boolean service_order_status) {
		this.service_order_status = service_order_status;
	}
	public Date getService_order_date() {
		return service_order_date;
	}
	public void setService_order_date(Date service_order_date) {
		this.service_order_date = service_order_date;
	}
	
	
	
}
