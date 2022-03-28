package com.activitySession.model;

import java.sql.Date;
import java.util.List;


public class ActivitySessionService {
	private ActivitySessionDAO_interface dao;

	public ActivitySessionService() {
		dao = new ActivitySessionJNDIDAO();
	}

	public ActivitySessionVO insertActivitySession(Integer activity_id, String activity_session_name, Date activity_started, Date activity_end,
			Integer activity_price, Integer activity_state, String status_note, Integer activity_max_part, Integer activity_min_part,
			Integer entered_total, Date entered_started,Date entered_end,Integer product_status) {
		ActivitySessionVO activitySessionVO = new ActivitySessionVO();
		activitySessionVO.setActivity_id(activity_id);
		activitySessionVO.setActivity_session_name(activity_session_name);
		activitySessionVO.setActivity_started(activity_started);
		activitySessionVO.setActivity_end(activity_end);
		activitySessionVO.setActivity_price(activity_price);
		activitySessionVO.setActivity_state(activity_state);
		activitySessionVO.setStatus_note(status_note);
		activitySessionVO.setActivity_max_part(activity_max_part);
		activitySessionVO.setActivity_min_part(activity_min_part);
		activitySessionVO.setEntered_total(entered_total);
		activitySessionVO.setEntered_started(entered_started);
		activitySessionVO.setEntered_end(entered_end);
		activitySessionVO.setProduct_status(product_status);

		dao.insert(activitySessionVO);
		return activitySessionVO;
	}

	public ActivitySessionVO updateActivitySession(Integer activity_session_id,Integer activity_id, String activity_session_name, Date activity_started, Date activity_end,
			Integer activity_price, Integer activity_state, String status_note, Integer activity_max_part, Integer activity_min_part,
			Integer entered_total, Date entered_started,Date entered_end,Integer product_status) {
		ActivitySessionVO activitySessionVO = new ActivitySessionVO();
		activitySessionVO.setActivity_session_id(activity_session_id);
		activitySessionVO.setActivity_id(activity_id);
		activitySessionVO.setActivity_session_name(activity_session_name);
		activitySessionVO.setActivity_started(activity_started);
		activitySessionVO.setActivity_end(activity_end);
		activitySessionVO.setActivity_price(activity_price);
		activitySessionVO.setActivity_state(activity_state);
		activitySessionVO.setStatus_note(status_note);
		activitySessionVO.setActivity_max_part(activity_max_part);
		activitySessionVO.setActivity_min_part(activity_min_part);
		activitySessionVO.setEntered_total(entered_total);
		activitySessionVO.setEntered_started(entered_started);
		activitySessionVO.setEntered_end(entered_end);
		activitySessionVO.setProduct_status(product_status);

		dao.update(activitySessionVO);
		return activitySessionVO;
	}

	public void deleteActivitySession(Integer activity_session_id) {
		dao.delete(activity_session_id);
	}

	public ActivitySessionVO getOneActivitySession(Integer activity_session_id) {
		return dao.findByPrimaryKey(activity_session_id);
	}

	public List<ActivitySessionVO> getAllActivitySession() {
		return dao.getAll();
	}
}
