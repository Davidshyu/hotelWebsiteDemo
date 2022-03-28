package com.meal.model;

import java.util.List;


public class MealService {
	private MealDAO_interface dao;

	public MealService() {
		dao = new MealJNDIDAO();
	}

	public MealVO addMeal(Integer meal_id, Integer meal_category_id, String meal_description,
			Integer meal_price, String meal_name, Integer meal_quantity,Boolean meal_status,byte[] meal_photo ) {

		MealVO MealVO = new MealVO();

		MealVO.setMeal_id(meal_id);
		MealVO.setMeal_category_id(meal_category_id);
		MealVO.setMeal_description(meal_description);
		MealVO.setMeal_price(meal_price);
		MealVO.setMeal_name(meal_name);
		MealVO.setMeal_quantity(meal_quantity);
		MealVO.setMeal_status(meal_status);
		MealVO.setMeal_photo(meal_photo);
		dao.insert(MealVO);

		return MealVO;
	}

	public MealVO updateMeal(Integer meal_id, Integer meal_category_id, String meal_description,
			Integer meal_price, String meal_name, Integer meal_quantity,Boolean meal_status,byte[] meal_photo) {

		MealVO MealVO = new MealVO();

		MealVO.setMeal_id(meal_id);
		MealVO.setMeal_category_id(meal_category_id);
		MealVO.setMeal_description(meal_description);
		MealVO.setMeal_price(meal_price);
		MealVO.setMeal_name(meal_name);
		MealVO.setMeal_quantity(meal_quantity);
		MealVO.setMeal_status(meal_status);
		MealVO.setMeal_photo(meal_photo);
		dao.update(MealVO);

		return MealVO;
	}

	public void deleteMeal(Integer mealid) {
		dao.delete(mealid);
	}

	public MealVO getOneMeal(Integer mealid) {
		return dao.findByPrimaryKey(mealid);
	}

	public List<MealVO> getAll() {
		return dao.getAll();
	}
	
	public List<MealVO> getMealCategory(Integer meal_id){
		return dao.getMealCategory(meal_id);
	}
}
