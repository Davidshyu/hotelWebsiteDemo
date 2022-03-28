package com.activitySession.model;

import java.util.*;
import com.activitySession.model.ActivitySessionVO;

public interface ActivitySessionDAO_interface {
	public void insert(ActivitySessionVO activitySessionVO);

	public void update(ActivitySessionVO activitySessionVO);

	public void delete(Integer activity_session_id);

	public ActivitySessionVO findByPrimaryKey(Integer activity_session_id);

	public List<ActivitySessionVO> getAll();
}
