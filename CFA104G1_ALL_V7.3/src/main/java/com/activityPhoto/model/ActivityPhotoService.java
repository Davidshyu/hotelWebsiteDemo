package com.activityPhoto.model;

import java.util.List;

public class ActivityPhotoService {
	private ActivityPhotoDAO_interface dao;

	public ActivityPhotoService() {
		dao = new ActivityPhotoJNDIDAO();
	}

	public ActivityPhotoVO insertActivityPhoto(Integer activity_id,byte[] activity_photo_file) {
		ActivityPhotoVO activityPhotoVO = new ActivityPhotoVO();
		activityPhotoVO.setActivity_id(activity_id);
		activityPhotoVO.setActivity_photo_file(activity_photo_file);
		dao.insert(activityPhotoVO);
		return activityPhotoVO;
	}

	public ActivityPhotoVO updateActivityPhoto(Integer activity_photo_id,Integer activity_id,byte[] activity_photo_file) {
		ActivityPhotoVO activityPhotoVO = new ActivityPhotoVO();
		activityPhotoVO.setActivity_photo_id(activity_photo_id);
		activityPhotoVO.setActivity_id(activity_id);
		activityPhotoVO.setActivity_photo_file(activity_photo_file);
		dao.update(activityPhotoVO);
		return activityPhotoVO;
	}

	public void deleteActivityPhoto(Integer activity_photo_id) {
		dao.delete(activity_photo_id);
	}

	public ActivityPhotoVO getOneActivityPhoto(Integer activity_photo_id) {
		return dao.findByPrimaryKey(activity_photo_id);
	}

	public List<ActivityPhotoVO> getAllActivityPhoto() {
		return dao.getAll();
	}
	public List<ActivityPhotoVO> getFKPic(Integer room_type_id) {
		return dao.findByFK(room_type_id);
	}
	
	public ActivityPhotoVO addRoomTypePic( Integer activity_id, byte[] activity_photo_file) {
		ActivityPhotoVO activityPhotoVO = new ActivityPhotoVO();

		activityPhotoVO.setActivity_id(activity_id);;
		activityPhotoVO.setActivity_photo_file(activity_photo_file);;
		
		dao.insert(activityPhotoVO);

		return activityPhotoVO;
	}

}


