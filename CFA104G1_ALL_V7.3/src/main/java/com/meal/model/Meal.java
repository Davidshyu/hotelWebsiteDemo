package com.meal.model;

public class Meal implements java.io.Serializable{

	public Meal() {
		room_id = 0;
		meal_id = 0;
		meal_name = "";
		meal_description = "";
		meal_price = 0;
		meal_quantity = 0;
	}
	private int room_id;
	private int meal_id;
	private String meal_name;
	private String meal_description;
	private float meal_price;
	private int meal_quantity;
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(int meal_id) {
		this.meal_id = meal_id;
	}
	public String getMeal_name() {
		return meal_name;
	}
	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}
	public String getMeal_description() {
		return meal_description;
	}
	public void setMeal_description(String meal_description) {
		this.meal_description = meal_description;
	}
	public float getMeal_price() {
		return meal_price;
	}
	public void setMeal_price(int meal_price) {
		this.meal_price = meal_price;
	}
	public int getMeal_quantity() {
		return meal_quantity;
	}
	public void setMeal_quantity(int meal_quantity) {
		this.meal_quantity = meal_quantity;
	}

	
	

	
	
}