package com.activityCategory.model;

import java.util.*;

public interface ActivityCategoryDAO_interface {
	public void insert(ActivityCategoryVO activityCategoryVO);

	public void update(ActivityCategoryVO activityCategoryVO);

	public void delete(Integer activity_category_id);

	public ActivityCategoryVO findByPrimaryKey(Integer activity_category_id);

	public List<ActivityCategoryVO> getAll();
}