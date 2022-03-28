package com.activity.model;

import java.util.*;
import com.activity.model.ActivityVO;

public interface ActivityDAO_interface {
	public int insert(ActivityVO activityVO);

	public void update(ActivityVO activityVO);

	public void delete(Integer activity_id);

	public ActivityVO findByPrimaryKey(Integer activity_id);

	public List<ActivityVO> getAll();
	
	public List<ActivityVO> getCate1Act();
	public List<ActivityVO> getCate2Act();
	public List<ActivityVO> getCate3Act();
}
