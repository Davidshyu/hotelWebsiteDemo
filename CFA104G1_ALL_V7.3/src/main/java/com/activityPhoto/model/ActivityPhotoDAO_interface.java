package com.activityPhoto.model;

import java.util.*;
import com.activityPhoto.model.ActivityPhotoVO;

public interface ActivityPhotoDAO_interface {
	public void insert(ActivityPhotoVO activityPhotoVO);

	public void update(ActivityPhotoVO activityPhotoVO);

	public void delete(Integer activity_photo_id);

	public ActivityPhotoVO findByPrimaryKey(Integer activity_photo_id);

	public List<ActivityPhotoVO> getAll();
	
	List<ActivityPhotoVO> findByFK(Integer activity_id);

}
