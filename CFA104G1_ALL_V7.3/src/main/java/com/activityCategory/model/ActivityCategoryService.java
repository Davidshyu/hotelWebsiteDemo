package com.activityCategory.model;

import java.util.List;

public class ActivityCategoryService {
	private ActivityCategoryDAO_interface dao;

	public ActivityCategoryService() {
		dao = new ActivityCategoryJNDIDAO();
	}

	public ActivityCategoryVO insertActivityCategory(String activity_category_name,String activity_category_info) {
		ActivityCategoryVO activityCategoryVO = new ActivityCategoryVO();
		activityCategoryVO.setActivity_category_name(activity_category_name);
		activityCategoryVO.setActivity_category_info(activity_category_info);
		dao.insert(activityCategoryVO);
		return activityCategoryVO;
	}

	public ActivityCategoryVO updateActivityCategory(Integer activity_category_id,String activity_category_name,String activity_category_info) {
		ActivityCategoryVO activityCategoryVO = new ActivityCategoryVO();
		activityCategoryVO.setActivity_category_id(activity_category_id);
		activityCategoryVO.setActivity_category_name(activity_category_name);
		activityCategoryVO.setActivity_category_info(activity_category_info);
		dao.update(activityCategoryVO);
		return activityCategoryVO;
	}

	public void deleteActivityCategory(Integer activity_category_id) {
		dao.delete(activity_category_id);
	}

	public ActivityCategoryVO getOneActivityCategory(Integer activity_category_id) {
		return dao.findByPrimaryKey(activity_category_id);
	}

	public List<ActivityCategoryVO> getAllActivityCategory() {
		return dao.getAll();
	}
	
}
