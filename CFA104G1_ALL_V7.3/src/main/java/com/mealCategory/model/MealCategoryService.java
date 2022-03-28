package com.mealCategory.model;

import java.util.List;


public class MealCategoryService {
	private MealCategoryDAO_interface dao;

	public MealCategoryService() {
		dao = new MealCategoryJNDIDAO();
	}

	public MealCategoryVO addMealCategory(Integer meal_category_id, String meal_category_name) {

		MealCategoryVO mealCategoryVO = new MealCategoryVO();

		mealCategoryVO.setMeal_category_id(meal_category_id);
		mealCategoryVO.setMeal_category_name(meal_category_name);
		dao.insert(mealCategoryVO);

		return mealCategoryVO;
	}

	public MealCategoryVO updateMealCategory(Integer meal_category_id, String meal_category_name) {

		MealCategoryVO mealCategoryVO = new MealCategoryVO();

		mealCategoryVO.setMeal_category_id(meal_category_id);
		mealCategoryVO.setMeal_category_name(meal_category_name);
		dao.update(mealCategoryVO);

		return mealCategoryVO;
	}

	public void deleteMealCategory(Integer meal_category_id) {
		dao.delete(meal_category_id);
	}

	public MealCategoryVO getOneMealCategory(Integer meal_category_id) {
		return dao.findByPrimaryKey(meal_category_id);
	}

	public List<MealCategoryVO> getAll() {
		return dao.getAll();
	}
}
