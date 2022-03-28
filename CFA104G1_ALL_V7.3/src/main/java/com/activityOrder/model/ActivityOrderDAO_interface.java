package com.activityOrder.model;

import java.util.*;
import com.activityOrder.model.ActivityOrderVO;

public interface ActivityOrderDAO_interface {
	void insert(ActivityOrderVO activityOrderVO);
	void update(ActivityOrderVO activityOrderVO);
	void delete(Integer activity_order_id);
	ActivityOrderVO findByPrimaryKey(Integer activity_order_id);
	List<ActivityOrderVO> getAll();
	List<ActivityOrderVO> getAllByMemId(Integer mem_id);

}