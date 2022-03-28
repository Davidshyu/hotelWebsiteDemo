package com.mealCategory.model;

import java.util.List;

public interface MealCategoryDAO_interface {
	public void insert(MealCategoryVO mealCategoryVO);
    public void update(MealCategoryVO mealCategoryVO);
    public void delete(Integer meal_category_id);
    public MealCategoryVO findByPrimaryKey(Integer meal_category_id);
    public List<MealCategoryVO> getAll();
}
