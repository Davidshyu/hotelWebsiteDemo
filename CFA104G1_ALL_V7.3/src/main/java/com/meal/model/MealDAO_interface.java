package com.meal.model;

import java.util.List;

public interface MealDAO_interface {
	public void insert(MealVO mealVO);
    public void update(MealVO mealVO);
    public void delete(Integer meal_id);
    public MealVO findByPrimaryKey(Integer meal_id);
    public List<MealVO> getAll();
    public List<MealVO> getMealCategory(Integer meal_id);
}
