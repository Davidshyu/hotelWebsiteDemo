package com.activity.model;

import java.util.List;


public class ActivityService {
	private ActivityDAO_interface dao;
	
	public ActivityService() {
		dao = new ActivityJNDIDAO();
	}
	public ActivityVO insertActivity(Integer activity_category_id, String activity_name, String activity_info) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivity_category_id(activity_category_id);
		activityVO.setActivity_name(activity_name);
		activityVO.setActivity_info(activity_info);
		int id = dao.insert(activityVO);
		activityVO.setActivity_id(id);
		return activityVO;
	}
	public ActivityVO updateActivity(Integer activity_id,Integer activity_category_id, String activity_name, String activity_info) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivity_id(activity_id);
		activityVO.setActivity_category_id(activity_category_id);
		activityVO.setActivity_name(activity_name);
		activityVO.setActivity_info(activity_info);
		dao.update(activityVO);
		return activityVO;
	}
	public void deleteActivity(Integer activity_id) {
        dao.delete(activity_id);
    }
	public ActivityVO getOneActivity(Integer activity_id) {
        return dao.findByPrimaryKey(activity_id);
    }
	 public List<ActivityVO> getAllActivity() {
	        return dao.getAll();
   }
	 public List<ActivityVO> getCate1Act() {
	        return dao.getCate1Act();
}
	 public List<ActivityVO> getCate2Act() {
	        return dao.getCate2Act();
}
	 public List<ActivityVO> getCate3Act() {
	        return dao.getCate3Act();
}
	 
}
