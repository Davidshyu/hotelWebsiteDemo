package com.meal.model;

public class MealVO {
	private Integer meal_id;
	private Integer meal_category_id;	
	private String meal_description;
	private Integer meal_price;
	private String meal_name;
	private Integer meal_quantity;	
	private Boolean meal_status;
	private byte[] meal_photo;
	
	public Integer getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(Integer meal_id) {
		this.meal_id = meal_id;
	}
	public Integer getMeal_category_id() {
		return meal_category_id;
	}
	public void setMeal_category_id(Integer meal_category_id) {
		this.meal_category_id = meal_category_id;
	}
	public String getMeal_description() {
		return meal_description;
	}
	public void setMeal_description(String meal_description) {
		this.meal_description = meal_description;
	}
	public Integer getMeal_price() {
		return meal_price;
	}
	public void setMeal_price(Integer meal_price) {
		this.meal_price = meal_price;
	}
	public String getMeal_name() {
		return meal_name;
	}
	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}
	public Integer getMeal_quantity() {
		return meal_quantity;
	}
	public void setMeal_quantity(Integer meal_quantity) {
		this.meal_quantity = meal_quantity;
	}
	public Boolean getMeal_status() {
		return meal_status;
	}
	public void setMeal_status(Boolean meal_status) {
		this.meal_status = meal_status;
	}
	public byte[] getMeal_photo() {
		return meal_photo;
	}
	public void setMeal_photo(byte[] meal_photo) {
		this.meal_photo = meal_photo;
	}
	
	
}
